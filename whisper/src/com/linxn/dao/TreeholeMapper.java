package com.linxn.dao;

import com.linxn.domain.Treehole;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TreeholeMapper {
    int deleteByPrimaryKey(Integer tId);

    int insert(Treehole record);

    int insertSelective(Treehole record);

    Treehole selectByPrimaryKey(Integer tId);

    int updateByPrimaryKeySelective(Treehole record);

    int updateByPrimaryKey(Treehole record);

    //自己添加
    List<Treehole> selectLimitRandomTH(Integer limit);
}