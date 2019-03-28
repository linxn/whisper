package com.linxn.dao;

import com.linxn.domain.Desire;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DesireMapper {
    int deleteByPrimaryKey(Integer dId);

    int insert(Desire record);

    int insertSelective(Desire record);

    Desire selectByPrimaryKey(Integer dId);

    int updateByPrimaryKeySelective(Desire record);

    int updateByPrimaryKey(Desire record);

    //自己添加
    List<Desire> selectLimitRandomDesire(Integer limit);
}