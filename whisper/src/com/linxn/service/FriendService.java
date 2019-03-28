package com.linxn.service;

import com.linxn.dao.FriendMapper;
import com.linxn.domain.Friend;
import com.linxn.util.GetConstantUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by linxn on 2018/4/16.
 */
@Transactional
@Service
public class FriendService implements GetConstantUtil{
    @Autowired
    private FriendMapper friendMapper;

    public Friend doCheckFriend(int f_from_id, int f_to_id){
        Friend f = new Friend(f_from_id, f_to_id);
        return friendMapper.queryAlreadyFriend(f);
    }

    public int doAddFriend(int f_from_id, int f_to_id){
        Friend f = new Friend(f_from_id, f_to_id);
        int r = 0;
        try{
            r = friendMapper.insertSelective(f);
        }catch (Exception e){
            System.out.println("catch exception when add friend...");
        }
        return r;
    }

    //检查用户好友是否已满
    public int doQueryFriendFull(int uId){
        //1 未满 0 已满
        if(friendMapper.queryFriendNum(uId) > LIMIT_FRIEND_NUM){
            return 0;
        }else {
            return 1;
        }
    }

    public int doDelFriend(int f_from_id, int f_to_id){
        Friend f = new Friend(f_from_id, f_to_id);
        int r = 0;
        try{
            r = friendMapper.deleteByFromIdAndToId(f);
        }catch (Exception e){
            System.out.println("catch exception when delete friend...");
        }
        return r;
    }

    public int queryDesireFriendNum(int uId){
        return friendMapper.queryDesireFriendNum(uId);
    }

    public int insertDesireFriend(int f_from_id, int f_to_id){
        Friend f = new Friend(f_from_id, f_to_id);
        f.setfType(DESIRE_FRIEND);
        int r = 0;
        try{
            r = friendMapper.insertSelective(f);
        }catch (Exception e){
            System.out.println("catch exception when insert desire friend...");
            e.printStackTrace();
        }
        return r;
    }
}
