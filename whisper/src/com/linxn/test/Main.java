package com.linxn.test;

import com.linxn.dao.FriendMapper;
import com.linxn.dao.SystemMessageLogMapper;
import com.linxn.dao.TreeholeMapper;
import com.linxn.dao.UserMapper;
import com.linxn.domain.User;
import com.linxn.service.UserService;
import com.linxn.util.ApplicationContextUtil;
import com.linxn.util.Md5Util;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;

/**
 * Created by linxn on 2018/4/13.
 */
public class Main {
    public static void main(String[] args) {
        User u = new User();
        u.setuUsername("admin");
        u.setuUserpwd("admin");
        //测试spring  OK
        ApplicationContext applicationContext = ApplicationContextUtil.getApplicationContext();
//        UserService userService = (UserService) applicationContext.getBean("userService");
        UserMapper userMapper = (UserMapper) applicationContext.getBean("userMapper");
        /*SystemMessageLogMapper systemMessageLogMapper = (SystemMessageLogMapper) applicationContext.getBean("systemMessageLogMapper");
        FriendMapper friendMapper = (FriendMapper) applicationContext.getBean("friendMapper");
        TreeholeMapper treeholeMapper = (TreeholeMapper) applicationContext.getBean("treeholeMapper");*/
        ArrayList<User> userArrayList = (ArrayList<User>) userMapper.selectFriendList(7);
        System.out.println(userArrayList.size()==0?0:1);



        /*user = (User) applicationContext.getBean("user");
        System.out.println(user.getUsername());*/

        //测试mybatis DAO  OK
        /*UserMapper userMapper = (UserMapper) applicationContext.getBean("userMapper");
        user = new User();
        user.setUsername("admin");
        user.setUserpwd("1234567");
        //测试 selectByNameAndPwd  OK
        user = userMapper.selectByNameAndPwd(user);
        System.out.println(user.getuId());*/

        //测试userService SERVICE  OK
//        UserService userService = (UserService) applicationContext.getBean("userService");
        //System.out.println(userService.checkUser("admin","1234567"));

        //测试insertSelective User   OK，但是配置的spring事务无效
        /*user = new User();
        user.setReDate(new Date());
        user.setUsername("James");
        user.setUserpwd("123456");
        //UserMapper userMapper = (UserMapper) applicationContext.getBean("userMapper");
        int r = userService.registerUser(user);
        System.out.println(r);*/

        //测试selectByFromId
        /*UserMapper userMapper = (UserMapper) applicationContext.getBean("userMapper");
        ArrayList<User> ul = (ArrayList<User>) userMapper.selectByFromId(2);
        for (int i = 0; i < ul.size(); i++) {
            System.out.println(ul.get(i).getuId()+" "+ul.get(i).getUsername());
        }*/

        //测试selectOnlineUser & jsonArray  OK
//        UserMapper userMapper = (UserMapper) applicationContext.getBean("userMapper");
        /*ArrayList<User> ul = (ArrayList<User>) userMapper.selectOnLineUser(5);
        JSONArray jsonArray = JSONArray.fromObject(ul);
        System.out.println(jsonArray.toString());*/
        /*for (int i = 0; i < ul.size(); i++) {
            System.out.println(ul.get(i).getuId()+" "+ul.get(i).getUsername());
        }*/

/*        Message m = new Message();
        m.setmType(0);
        m.setmContent("test2...");
        System.out.println(SystemLogUtil.doSystemLog(m));*/

        /*ArrayList<User> userArrayList = (ArrayList<User>) userMapper.selectOnLineUser(1);
        JSONArray jsonArray = JSONArray.fromObject(userArrayList);
        System.out.println(jsonArray.toString());
        for (int i = 0; i < userArrayList.size(); i++) {
            System.out.println(userArrayList.get(i).getuId()+" "+userArrayList.get(i).getuUsername());
        }*/

        /*ArrayList<Treehole> treeholeArrayList = (ArrayList<Treehole>) treeholeMapper.selectLimitRandomTH(5);
        JSONArray jsonArray = JSONArray.fromObject(treeholeArrayList);
        System.out.println(jsonArray.toString());*/

        /*System.out.println(Md5Util.md5Password("admin"));
        System.out.println(Md5Util.md5Password("123"));*/
    }
}
