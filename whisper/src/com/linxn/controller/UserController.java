package com.linxn.controller;
import com.linxn.domain.Message;
import com.linxn.service.UserService;
import com.linxn.util.GetConstantUtil;
import com.linxn.util.OutDataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by linxn on 2018/4/13.
 *
 * 所有的接受请求，发送请求包括登录注册等，全部用message包进行包装
 * 普通消息存入message表，系统消息存入systemMessageLog表
 *
 * 目前只是普通的将系统日志存入数据库, 日志也有很多混乱的地方没有进行处理，项目重构时可以尝试spring的 AOP 功能进行日志的写入
 */
@Controller
@RequestMapping("/user")
public class UserController implements GetConstantUtil {

    @Autowired
    private UserService userService;

    //@ResponseBody 该注解将函数返回值作为请求返回值，即不用配置视图解析器  也不用返回页面
    //前端都是用ajax做，所以不用跳转到某页面
    @ResponseBody
    @RequestMapping("/checkUser")
    public void checkUser(Message loginMess, HttpServletResponse response){
        //重构时可以将用户名和用户昵称分开, 用户密码采用md5加密，salt用注册的timestamp
        if(loginMess.getmType() == USER_LOGIN){
            String resultMess = userService.doCheckUser(loginMess);
            OutDataUtil.outData(resultMess, response);

            /*返回的方式可以有多种
            ajax可以用printwriter返回
            也可以用request进行跳转或者重定向
            也可以直接return返回视图  返回的视图可以是jsp、html、json数据（需要注解）等

            这里是ajax调用  所以直接用printwriter返回数据*/
        }
    }

    @ResponseBody
    @RequestMapping("/register")
    public void registerUser(Message registerMess, HttpServletResponse response){
        if(registerMess.getmType() == USER_REGISTER){
            String resultMess = userService.doRegisterUser(registerMess);
            OutDataUtil.outData(resultMess, response);
        }
    }

    @ResponseBody
    @RequestMapping("/onlineUser")
    public void getOnlineUser(Message getOnLineUserMess, HttpServletResponse response){
        if(getOnLineUserMess.getmType() == GET_ONLINE_USER_LIST){
            String resultJ = userService.doGetOnlineUser(getOnLineUserMess);
            OutDataUtil.outData(resultJ, response);
        }
    }

    @ResponseBody
    @RequestMapping("/changeState")
    public void changeUserOnlineState(Message onOffLineMess){
        if(onOffLineMess.getmType() == USER_ON_OFF_LINE){
            userService.doChangeUserOnlineState(onOffLineMess);
        }
    }

    @ResponseBody
    @RequestMapping("/friendList")
    public void getFriendList(Message getFriendListMess, HttpServletResponse response){
        if(getFriendListMess.getmType() == GET_FRIEND_LIST){
            String resultJ = userService.doGetFriendList(getFriendListMess);
            System.out.println("bbb"+resultJ);
            OutDataUtil.outData(resultJ, response);
        }
    }

    @ResponseBody
    @RequestMapping("/desireFriendList")
    public void getDesireFriendList(Message getDesireFriendListMess, HttpServletResponse response){
        if(getDesireFriendListMess.getmType() == GET_DESIRE_FRIEND_LIST){
            String resultJ = userService.doGetDesireFriendList(getDesireFriendListMess);
            System.out.println("ddd"+resultJ);
            OutDataUtil.outData(resultJ, response);
        }
    }
}
