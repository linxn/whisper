package com.linxn.dao;

import com.linxn.domain.Message;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemMessageLogMapper {
    int deleteByPrimaryKey(Integer smId);

    int insert(Message record);

    int insertSelective(Message record);

    Message selectByPrimaryKey(Integer smId);

    int updateByPrimaryKeySelective(Message record);

    int updateByPrimaryKey(Message record);
}