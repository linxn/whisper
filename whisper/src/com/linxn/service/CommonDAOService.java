package com.linxn.service;

import com.linxn.dao.UserMapper;
import com.linxn.domain.Friend;
import com.linxn.domain.User;
import com.linxn.util.ApplicationContextUtil;
import org.springframework.context.ApplicationContext;

/**
 * Created by linxn on 2018/4/19.
 *
 * 不被Spring管理的Service使用DAO，则使用该类
 */
public class CommonDAOService {
    private static ApplicationContext applicationContext = ApplicationContextUtil.getApplicationContext();
    private static UserMapper userMapper = (UserMapper) applicationContext.getBean("userMapper");
    private static FriendService friendService = (FriendService) applicationContext.getBean("friendService");

    public static User doGetUserById(int uid){
        return userMapper.selectByPrimaryKey(uid);
    }

    public static int doAddFriend(int f_from_id, int f_to_id){
        return friendService.doAddFriend(f_from_id,f_to_id);
    }

    public static Friend doCheckFriend(int f_from_id, int f_to_id){
        return friendService.doCheckFriend(f_from_id, f_to_id);
    }

    public static int doQueryFriendFull(int uId){
        return friendService.doQueryFriendFull(uId);
    }

    public static int doDelFriend(int f_from_id, int f_to_id){
        return friendService.doDelFriend(f_from_id, f_to_id);
    }

    public static int doQueryDesireFriendNum(int uId){
        return friendService.queryDesireFriendNum(uId);
    }

    public static int doInsertDesireFriend(int f_from_id, int f_to_id){
        return friendService.insertDesireFriend(f_from_id, f_to_id);
    }
}
