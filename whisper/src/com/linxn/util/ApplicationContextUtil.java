package com.linxn.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by linxn on 2017/5/31.
 */
public class ApplicationContextUtil {
    private static ApplicationContext applicationContext = null;
    private ApplicationContextUtil(){};
    static {
        applicationContext = new ClassPathXmlApplicationContext("resource/applicationContext.xml");
    }
    public static ApplicationContext getApplicationContext(){
        return applicationContext;
    }
}
