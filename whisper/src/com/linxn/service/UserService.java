package com.linxn.service;

import com.linxn.dao.MessageMapper;
import com.linxn.dao.UserMapper;
import com.linxn.domain.Message;
import com.linxn.domain.User;
import com.linxn.util.GetConstantUtil;
import com.linxn.util.Md5Util;
import com.linxn.util.SystemLogUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by linxn on 2018/4/13.
 */
@Transactional
@Service
public class UserService implements GetConstantUtil {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MessageMapper messageMapper;
    //登录Service
    public String doCheckUser(Message loginMess){
        //写入系统日志
        SystemLogUtil.doSystemLog(loginMess);
        JSONObject jsonObject=JSONObject.fromObject(loginMess.getmContent());
        User u = (User) JSONObject.toBean(jsonObject, User.class);
        String pwd = Md5Util.md5Password(u.getuUserpwd());
        u.setuUserpwd(pwd);
        User user = userMapper.selectByNameAndPwd(u);
        Message mess = new Message();
        mess.setmType(STATUS_CODE);
        if(user != null){
            mess.setmContent(JSONObject.fromObject(user).toString());
        }
        else {
            mess.setmContent(String.valueOf(COMMON_FAIL));
        }
        JSONObject messJ = JSONObject.fromObject(mess);
        return messJ.toString();
    }
    //注册Service
    public String doRegisterUser(Message registerMess){
        SystemLogUtil.doSystemLog(registerMess);
        JSONObject jsonObject=JSONObject.fromObject(registerMess.getmContent());
        User u = (User) JSONObject.toBean(jsonObject, User.class);
        String pwd = Md5Util.md5Password(u.getuUserpwd());
        u.setuUserpwd(pwd);
        u.setuReDate(new Timestamp(new Date().getTime()));
        String headPhoto = (Math.random() % 6) + ".jpg";
        u.setuHeadPhoto(headPhoto);
        Message mess = new Message();
        mess.setmType(STATUS_CODE);
        int r = 0;
        try{
            r = userMapper.insertSelective(u);
        }catch(Exception e){
            System.out.println("catch jdbc exception when register...");
            //e.printStackTrace();
        }
        if(r == 1){
            mess.setmContent(String.valueOf(COMMON_SUCCESS));
        }else {
            mess.setmContent(String.valueOf(COMMON_FAIL));
        }
        JSONObject messJ = JSONObject.fromObject(mess);
        return messJ.toString();
    }

    //获取在线用户Service
    public String doGetOnlineUser(Message getOnLineUserMess){
        SystemLogUtil.doSystemLog(getOnLineUserMess);
        ArrayList<User> userArrayList = (ArrayList<User>) userMapper.selectOnLineUser(getOnLineUserMess.getmFromId());
        //转换成json数据
        JSONArray jsonArray = JSONArray.fromObject(userArrayList);
        return jsonArray.toString();
    }

    //更改用户在线状态
    public int doChangeUserOnlineState(Message onOffLineMess){
        SystemLogUtil.doSystemLog(onOffLineMess);
        JSONObject jsonObject=JSONObject.fromObject(onOffLineMess.getmContent());
        User user = (User) JSONObject.toBean(jsonObject, User.class);
        int result = 0;
        try{
            result = userMapper.updateByPrimaryKeySelective(user);
        }catch(Exception e){
            System.out.println("catch jdbc exception on change user state...");
            e.printStackTrace();
        }
        return result;
    }

    public String doGetFriendList(Message getFriendListMess){
        SystemLogUtil.doSystemLog(getFriendListMess);
        //获取用户好友
        ArrayList<User> userArrayList = (ArrayList<User>) userMapper.selectFriendList(getFriendListMess.getmFromId());
        //获取用户好友未读消息返回前端  并置为已读
        ArrayList<Message> messageArrayList = (ArrayList<Message>) messageMapper.selectNotReadMessage(getFriendListMess.getmFromId());
        for (Message m:messageArrayList
             ) {
            m.setmIfRead((byte) 1);
            try{
                messageMapper.updateByPrimaryKeySelective(m);
            }catch(Exception e){
                System.out.println("catch exception when set message read...");
            }
        }
        ArrayList<Object> obj = new ArrayList<Object>();
        obj.add(userArrayList);
        obj.add(messageArrayList);
        //转换成json数据
        JSONArray jsonArray = JSONArray.fromObject(obj);
        return jsonArray.toString();
    }

    public String doGetDesireFriendList(Message getDesireFriendListMess){
        SystemLogUtil.doSystemLog(getDesireFriendListMess);
        //获取用户心愿好友
        ArrayList<User> userArrayList = (ArrayList<User>) userMapper.selectDesireFriendList(getDesireFriendListMess.getmFromId());
        //获取用户心愿好友未读消息返回前端  并置为已读
        ArrayList<Message> messageArrayList = (ArrayList<Message>) messageMapper.selectNotReadDesireMessage(getDesireFriendListMess.getmFromId());
        for (Message m:messageArrayList
                ) {
            m.setmIfRead((byte) 1);
            try{
                messageMapper.updateByPrimaryKeySelective(m);
            }catch(Exception e){
                System.out.println("catch exception when set desire message read...");
            }
        }
        ArrayList<Object> obj = new ArrayList<Object>();
        obj.add(userArrayList);
        obj.add(messageArrayList);
        //转换成json数据
        JSONArray jsonArray = JSONArray.fromObject(obj);
        return jsonArray.toString();
    }
}
