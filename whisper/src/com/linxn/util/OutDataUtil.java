package com.linxn.util;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by linxn on 2018/4/13.
 */
public class OutDataUtil implements GetConstantUtil {
    public static int outData(String data, HttpServletResponse response){
        int flag = 0;
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.println(data);
            //刷新关闭流
            flag = 1;
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            out.flush();
            out.close();
        }
        return flag;
    }
}
