package com.linxn.util;

/**
 * Created by linxn on 2018/4/9.
 */
public class JSONSampleUtil {
    //只能解析一层json 业务不复杂
    public static String jsonSampleGet(String jstr, String key){
        int index = jstr.indexOf(key);
        if(index == -1) return "";
        //System.out.println(index);
        jstr = jstr.substring(index + key.length() + 3);
        index = jstr.indexOf('"');
        jstr = jstr.substring(0,index);
        return jstr;
    }
}
