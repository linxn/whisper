package com.linxn.service;

import com.linxn.dao.DesireMapper;
import com.linxn.domain.Desire;
import com.linxn.domain.Message;
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
 * Created by linxn on 2018/5/9.
 */
@Service
public class DesireService implements GetConstantUtil {
    @Autowired
    DesireMapper desireMapper;

    //获取心愿列表
    public String doSelectLimitRandomDesire(Message getDesireMess){
        SystemLogUtil.doSystemLog(getDesireMess);
        ArrayList<Desire> desireArrayList = (ArrayList<Desire>) desireMapper.selectLimitRandomDesire(LIMIT_DESIRE_NUM);
        JSONArray jsonArray = JSONArray.fromObject(desireArrayList);
        getDesireMess.setmContent(jsonArray.toString());
        JSONObject jsonObject = JSONObject.fromObject(getDesireMess);
        return jsonObject.toString();
    }

    //更新点赞
    public int doUpdateDoLike(Message updateDoLikeMess){
        SystemLogUtil.doSystemLog(updateDoLikeMess);
        Desire desire = new Desire();
        desire.setdId(updateDoLikeMess.getmToId());
        desire.setdLikesCount(Integer.valueOf(updateDoLikeMess.getmContent()));
        int result = 0;
        try{
            result = desireMapper.updateByPrimaryKeySelective(desire);
        }catch (Exception e){
            System.out.println("catch exception when update desire do-likes...");
        }
        return result;
    }

    public int doPublicDesire(Message publicDesireMess){
        SystemLogUtil.doSystemLog(publicDesireMess);
        Desire desire = new Desire();
        desire.setdPublisherId(publicDesireMess.getmFromId());
        desire.setdContent(publicDesireMess.getmContent());
        int result = 0;
        try{
            result = desireMapper.insertSelective(desire);
        }catch (Exception e){
            System.out.println("catch exception when public desire...");
            e.printStackTrace();
        }
        return result;
    }

    public String doAddDesireFriend(Message addDesireFriMess){
        SystemLogUtil.doSystemLog(addDesireFriMess);
        int fromId = addDesireFriMess.getmFromId();
        int toId = addDesireFriMess.getmToId();
        int uDFNum = CommonDAOService.doQueryDesireFriendNum(fromId);
        int oDRNum = CommonDAOService.doQueryDesireFriendNum(toId);
        int fNum = (CommonDAOService.doCheckFriend(fromId,toId) != null ? 1 : 0);
        if(uDFNum >= LIMIT_DESIRE_FRIEND){
            addDesireFriMess.setmType(ALREADY_HAS_DESIRE_FRI);
        }else if(oDRNum >= LIMIT_DESIRE_FRIEND){
            addDesireFriMess.setmType(OTHER_ALREADY_HAS_DESIRE_FRI);
        }else if(fNum >= 1){
            addDesireFriMess.setmType(ALREADY_FRIEND);
        }else {
            //添加心愿好友
            CommonDAOService.doInsertDesireFriend(fromId, toId);
            //心愿设置为已实现
            Desire desire = desireMapper.selectByPrimaryKey(Integer.valueOf(addDesireFriMess.getmContent()));
            desire.setdRealize((byte) 1);
            try{
                desireMapper.updateByPrimaryKeySelective(desire);
            }catch (Exception e){
                System.out.println("catch jdbc exception when realize desire...");
            }
            addDesireFriMess.setmType(ADD_DESIRE_FRIEND_SUCCESS);
        }
        JSONObject jsonObject = JSONObject.fromObject(addDesireFriMess);
        return jsonObject.toString();
    }



}
