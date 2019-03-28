package com.linxn.util;

import com.linxn.dao.SystemMessageLogMapper;
import com.linxn.domain.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by linxn on 2018/4/18.
 *
 * 感觉这个类当成Util或者Service都可以  在这里功能简单，并且不属于model层，当成Util
 */
public class SystemLogUtil {

    private static SystemMessageLogMapper systemMessageLogMapper;

    static{
        systemMessageLogMapper = (SystemMessageLogMapper) ApplicationContextUtil.getApplicationContext().getBean("systemMessageLogMapper");
    }

    public static int doSystemLog(Message logMess){
        logMess.setmTime(new Timestamp(new Date().getTime()));
        int result = 0;
        try{
            //开发过程先把写日志功能关闭 之后再开启
            result = systemMessageLogMapper.insertSelective(logMess);
        }catch(Exception e){
            System.out.println("catch exception when insert system log...");
            e.printStackTrace();
        }
        return result;
    }

}
