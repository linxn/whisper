package com.linxn.dao;

import com.linxn.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    int deleteByPrimaryKey(Integer uId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer uId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    //自己添加
    User selectByNameAndPwd(User record);
    List<User> selectOnLineUser(Integer u_id);
    List<User> selectFriendList(Integer u_id);
    List<User> selectDesireFriendList(Integer u_id);
}