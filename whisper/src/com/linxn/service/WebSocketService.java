package com.linxn.service;

/**
 * Created by linxn on 2018/4/9.
 *
 * 个人理解：Controller 接受请求，调用Service然后返回视图（视图也可以是数据）
 * 后端websocket与前端websocket直接相连进行数据交互，所以后端websocket算是controller不算service，
 * 还需要专门调用service来请求服务
 *
 * 在这里前期被当做Service层开发，后期重构可以将该类当做controller，然后把接收消息的处理过程分离出来，成为单独的websocketService
 * 由于这里功能比较简单，只提供上面思路，后期不进行重构
 */



import com.linxn.domain.Friend;
import com.linxn.domain.Message;
import com.linxn.domain.User;
import com.linxn.util.GetConstantUtil;
import com.linxn.util.MessageLogUtil;
import net.sf.json.JSONObject;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/websocket/{username}")
public class WebSocketService implements GetConstantUtil {

    private static int onlineCount = 0;
    private static Map<String, WebSocketService> clients = new ConcurrentHashMap<String, WebSocketService>();
    private Session session;
    private String username;

    @OnOpen
    public void onOpen(@PathParam("username") String username, Session session) throws IOException {

        this.username = username;
        this.session = session;

        addOnlineCount();
        clients.put(username, this);
        System.out.println("已连接");
    }

    @OnClose
    public void onClose() throws IOException {
        clients.remove(username);
        subOnlineCount();
    }

    @OnMessage
    public void onMessage(String message) throws IOException {
        System.out.println("收到信息  " + message);
        Message commMess = json2Message(message);
        commMess.setmTime(new Timestamp(new Date().getTime()));

        int type = commMess.getmType();
        int from_id = commMess.getmFromId();
        String to_id = String.valueOf(commMess.getmToId());
        //不需要区分匹配聊天用户是否在线
        if(type == REFUSE_FOR_CHAT || type == DISCONNECT_CHAT || type == AGREE_ADD_FRIEND || type == DISAGREE_ADD_FRIEND || type == DEL_FRIEND){
            if(type == REFUSE_FOR_CHAT || type == DISCONNECT_CHAT || type == DISAGREE_ADD_FRIEND){
                sendMessage(message);
                System.out.println("发送成功1");
            }else if(type == AGREE_ADD_FRIEND){
                Friend checkFriend = CommonDAOService.doCheckFriend(from_id, Integer.parseInt(to_id));
                if(checkFriend != null){
                    //已经是好友
                    sendMessageBack(message, ALREADY_FRIEND);
                    System.out.println("发送成功7");
                }else {
                    //添加好友
                    CommonDAOService.doAddFriend(from_id, Integer.parseInt(to_id));
                    sendMessage(message);
                    System.out.println("发送成功10");
                }
            }else if(type == DEL_FRIEND){
                CommonDAOService.doDelFriend(commMess.getmFromId(), commMess.getmToId());
            }

        //需要区分匹配用户是否在线，不在线发送用户已离线包
        }else if(type == COMM_MESS || type == ACCEPT_FOR_CHAT || type == REQUEST_FOR_CHAT || type == REQUEST_ADD_FRIEND || type == FRIEND_MESS){
            if(clients.containsKey(to_id)){
                if(type == COMM_MESS || type == ACCEPT_FOR_CHAT){
                   sendMessage(message);
                    System.out.println("发送成功2");
                }else if(type == REQUEST_FOR_CHAT){
                    //获取用户信息
                    User u = CommonDAOService.doGetUserById(from_id);
                    User uR = new User();
                    uR.setuId(u.getuId());
                    uR.setuUsername(u.getuUsername());
                    uR.setuHeadPhoto(u.getuHeadPhoto());
                    JSONObject userJ = JSONObject.fromObject(uR);
                    sendMessage(message,userJ.toString());
                    System.out.println("发送成功3");
                }else if(type == REQUEST_ADD_FRIEND){
                    Friend checkFriend = CommonDAOService.doCheckFriend(from_id, Integer.parseInt(to_id));
                    if(checkFriend != null){
                        //已经是好友
                        sendMessageBack(message, ALREADY_FRIEND);
                        System.out.println("发送成功5");
                    }else if(CommonDAOService.doQueryFriendFull(from_id) == 0){
                        //用户好友已满
                        sendMessageBack(message, USER_FRIEND_FULL);
                        System.out.println("发送成功11");
                    }else if(CommonDAOService.doQueryFriendFull(Integer.parseInt(to_id)) == 0){
                        //对方好友已满
                        sendMessageBack(message, OTHER_FRIEND_FULL);
                        System.out.println("发送成功12");
                    }else{
                        sendMessage(message);
                        sendMessageBack(message, ALREADY_SEND_FRIEND_REQUEST);
                        System.out.println("发送成功6");
                    }
                }else if(type == FRIEND_MESS || type == DESIRE_FRIEND_MESS){
                    System.out.println(commMess.getmFromId()+" & "+commMess.getmToId());
                    if(CommonDAOService.doCheckFriend(commMess.getmFromId(), commMess.getmToId()) != null){
                        sendMessage(message);
                        System.out.println("发送成功13");
                    }else{
                        sendMessageBack(message,OTHER_AALREADY_DEL_FRIEND);
                        commMess.setmIfRead((byte) 0);
                    }
                }

            }else{
                //对方已下线
                if(type == FRIEND_MESS){
                    commMess.setmIfRead((byte) 0);
                }else{
                    sendMessageBack(message, CHAT_TO_OFFLINE);
                    System.out.println("发送成功4");
                    commMess.setmIfRead((byte) 0);
                }
            }
        }


        //写入数据库
        MessageLogUtil.doMessageLog(commMess);
    }

    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }

    public void sendMessageTo(String message, String To) throws IOException {
        // session.getBasicRemote().sendText(message);
        //session.getAsyncRemote().sendText(message);
        for (WebSocketService item : clients.values()) {
            if (item.username.equals(To) )
                item.session.getAsyncRemote().sendText(message);
        }
    }

    public void sendMessageAll(String message) throws IOException {
        for (WebSocketService item : clients.values()) {
            item.session.getAsyncRemote().sendText(message);
        }
    }



    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocketService.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocketService.onlineCount--;
    }

    public static synchronized Map<String, WebSocketService> getClients() {
        return clients;
    }

    public String message2Json(Message mess){
        return JSONObject.fromObject(mess).toString();
    }

    public Message json2Message(String messJ){
        JSONObject jsonObject=JSONObject.fromObject(messJ);
        return (Message) JSONObject.toBean(jsonObject, Message.class);
    }

    public void sendMessage(String messJ) throws IOException {
        Message message = json2Message(messJ);
        int to_id = message.getmToId();
        sendMessageTo(messJ, String.valueOf(to_id));
    }

    public void sendMessage(String messJ, int mType) throws IOException {
        Message message = json2Message(messJ);
        int to_id = message.getmToId();
        message.setmType(mType);
        messJ = message2Json(message);
        sendMessageTo(messJ, String.valueOf(to_id));
    }

    public void sendMessage(String messJ, String mContent) throws IOException {
        Message message = json2Message(messJ);
        int to_id = message.getmToId();
        message.setmContent(mContent);
        messJ = message2Json(message);
        sendMessageTo(messJ, String.valueOf(to_id));
    }


    //回送消息
    public void sendMessageBack(String messJ, int mType) throws IOException {
        Message message = json2Message(messJ);
        int to_id = message.getmFromId();
        int from_id = message.getmToId();
        message.setmType(mType);
        message.setmToId(to_id);
        message.setmFromId(from_id);
        messJ = message2Json(message);
        sendMessageTo(messJ, String.valueOf(to_id));
    }
}