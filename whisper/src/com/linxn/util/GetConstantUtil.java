package com.linxn.util;

/**
 * Created by linxn on 2018/4/13.
 * 常量接口
 */
public interface GetConstantUtil {
    //通用状态码
    public static final int COMMON_SUCCESS = 1;
    public static final int COMMON_FAIL = 0;

    //Message包类型
    public static final int TEST_CODE = 0;                   //测试状态码包
    public static final int STATUS_CODE = 1;                //返回状态码包

    //聊天消息码
    public static final int COMM_MESS = 2;                  //普通消息包 陌生人

    //登录注册消息码
    public static final int USER_LOGIN = 3;                 //用户登录包
    public static final int USER_REGISTER = 4;              //用户注册包
    public static final int USER_ON_OFF_LINE = 5;          //用户上下线

    //陌生人聊天状态消息码
    public static final int GET_ONLINE_USER_LIST = 6;      //获取在线用户列表
    public static final int REQUEST_FOR_CHAT = 7;           //请求聊天包
    public static final int ACCEPT_FOR_CHAT = 8;            //同意聊天
    public static final int REFUSE_FOR_CHAT = 9;            //拒绝聊天
    public static final int DISCONNECT_CHAT = 10;           //当前聊天连接已断开
    public static final int CHAT_TO_OFFLINE = 11;           //聊天对象已下线

    //陌生人添加好友消息码
    public static final int REQUEST_ADD_FRIEND = 12;        //请求添加好友
    public static final int AGREE_ADD_FRIEND = 13;          //同意添加好友请求
    public static final int DISAGREE_ADD_FRIEND = 14;       //拒绝添加好友请求
    public static final int USER_FRIEND_FULL = 15;          //用户好友已满
    public static final int OTHER_FRIEND_FULL = 16;         //对方好友已满
    public static final int ALREADY_FRIEND = 17;            //已经是好友
    public static final int ALREADY_SEND_FRIEND_REQUEST = 18;        //已发送添加好友请求

    //好友聊天消息码
    public static final int GET_FRIEND_LIST = 19;           //获取好友列表
    public static final int FRIEND_MESS = 20;                //好友消息包
    public static final int DEL_FRIEND = 21;                 //删除好友包
    public static final int OTHER_AALREADY_DEL_FRIEND = 22;          //对方已删除好友

    //悄悄话消息码
    public static final int GET_LIMIT_TREEHOLE = 23;        //获取悄悄话列表
    public static final int UPDATE_TH_DO_LIKES = 24;        //悄悄话点赞或取消
    public static final int PUBLIC_TREEHOLE = 25;           //发布悄悄话

    //心愿消息码
    public static final int GET_LIMIT_DESIRE = 26;         //获取悄悄话列表
    public static final int UPDATE_DESIRE_DO_LIKES = 27;  //悄悄话点赞或取消
    public static final int PUBLIC_DESIRE = 28;            //发布悄悄话
    public static final int ADD_DESIRE_FRIEND = 29;        //添加心愿好友
    public static final int ALREADY_HAS_DESIRE_FRI = 30;  //用户已经有心愿好友
    public static final int OTHER_ALREADY_HAS_DESIRE_FRI = 31;    //对方已有心愿好友
    public static final int ADD_DESIRE_FRIEND_SUCCESS = 32;       //成功添加心愿好友

    public static final int DESIRE_FRIEND_MESS = 33;       //心愿好友消息包
    public static final int GET_DESIRE_FRIEND_LIST = 34;  //获取心愿好友列表

    //数量限制
    public static final int LIMIT_USER_NUM = 5;            //获取在线用户数量
    public static final int LIMIT_FRIEND_NUM = 5;          //限制好友数量
    public static final int LIMIT_TH_NUM = 5;               //限制一次获取的悄悄话数量
    public static final int LIMIT_DESIRE_NUM = 5;          //限制一次获取的心愿数量
    public static final int LIMIT_DESIRE_FRIEND = 1;       //限制心愿好友数量

    //好友类型
    public static final int COMM_FRIEND = 1;                //普通好友 该值为默认值
    public static final int DESIRE_FRIEND = 2;              //心愿好友
}
