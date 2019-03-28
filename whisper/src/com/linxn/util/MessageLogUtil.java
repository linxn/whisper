package com.linxn.util;

import com.linxn.dao.MessageMapper;
import com.linxn.domain.Message;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by linxn on 2018/4/18.
 */
public class MessageLogUtil {
    private static MessageMapper messageMapper;

    static{
        messageMapper = (MessageMapper) ApplicationContextUtil.getApplicationContext().getBean("messageMapper");
    }

    public static int doMessageLog(Message logMess){
        int result = 0;
        try{
            //开发过程先把保存聊天记录功能关闭 之后再开启
            result = messageMapper.insertSelective(logMess);
        }catch(Exception e){
            System.out.println("catch exception when insert message log...");
            e.printStackTrace();
        }
        return result;
    }
}
