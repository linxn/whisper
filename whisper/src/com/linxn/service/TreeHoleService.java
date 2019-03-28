package com.linxn.service;

import com.linxn.dao.TreeholeMapper;
import com.linxn.domain.Message;
import com.linxn.domain.Treehole;
import com.linxn.util.GetConstantUtil;
import com.linxn.util.SystemLogUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by linxn on 2018/4/24.
 */

@Service
public class TreeHoleService implements GetConstantUtil {
    @Autowired
    TreeholeMapper treeholeMapper;

    //获取悄悄话列表
    public String doSelectLimitRandomTH(Message getTreeHoleMess){
        SystemLogUtil.doSystemLog(getTreeHoleMess);
        ArrayList<Treehole> treeholeArrayList = (ArrayList<Treehole>) treeholeMapper.selectLimitRandomTH(LIMIT_TH_NUM);
        JSONArray jsonArray = JSONArray.fromObject(treeholeArrayList);
        getTreeHoleMess.setmContent(jsonArray.toString());
        JSONObject jsonObject = JSONObject.fromObject(getTreeHoleMess);
        return jsonObject.toString();
    }

    //更新点赞
    public int doUpdateDoLike(Message updateDoLikeMess){
        SystemLogUtil.doSystemLog(updateDoLikeMess);
        Treehole treehole = new Treehole();
        treehole.settId(updateDoLikeMess.getmToId());
        treehole.settLikesCount(Integer.valueOf(updateDoLikeMess.getmContent()));
        int result = 0;
        try{
            result = treeholeMapper.updateByPrimaryKeySelective(treehole);
        }catch (Exception e){
            System.out.println("catch exception when update treehole do-likes...");
        }
        return result;
    }

    public int doPublicTreehole(Message publicTreeholeMess){
        SystemLogUtil.doSystemLog(publicTreeholeMess);
        Treehole treehole = new Treehole();
        treehole.settPublisherId(publicTreeholeMess.getmFromId());
        treehole.settContent(publicTreeholeMess.getmContent());
        treehole.settPublishTime(new Timestamp(new Date().getTime()));
        int result = 0;
        try{
            result = treeholeMapper.insertSelective(treehole);
        }catch (Exception e){
            System.out.println("catch exception when public treehole...");
        }
        return result;
    }
}
