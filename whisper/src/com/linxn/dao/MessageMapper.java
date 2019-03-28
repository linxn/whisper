package com.linxn.dao;

import com.linxn.domain.Message;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageMapper {
    int deleteByPrimaryKey(Integer mId);

    int insert(Message record);

    int insertSelective(Message record);

    Message selectByPrimaryKey(Integer mId);

    int updateByPrimaryKeySelective(Message record);

    int updateByPrimaryKey(Message record);

    //自己添加
    List<Message> selectNotReadMessage(Integer uId);
    List<Message> selectNotReadDesireMessage(Integer uId);

}