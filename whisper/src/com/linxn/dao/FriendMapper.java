package com.linxn.dao;

import com.linxn.domain.Friend;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendMapper {
    int deleteByPrimaryKey(Integer fId);

    int insert(Friend record);

    int insertSelective(Friend record);

    Friend selectByPrimaryKey(Integer fId);

    int updateByPrimaryKeySelective(Friend record);

    int updateByPrimaryKey(Friend record);

    //自己添加
    //查询用户好友数
    int queryFriendNum(Integer uId);
    //查询是否已经为好友
    Friend queryAlreadyFriend(Friend record);
    //删除好友
    int deleteByFromIdAndToId(Friend record);

    //查询心愿好友数量
    int queryDesireFriendNum(Integer uId);
}