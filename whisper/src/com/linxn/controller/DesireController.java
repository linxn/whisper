package com.linxn.controller;

import com.linxn.domain.Message;
import com.linxn.service.DesireService;
import com.linxn.util.GetConstantUtil;
import com.linxn.util.OutDataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by linxn on 2018/5/9.
 */
@Controller
@RequestMapping("/desire")
public class DesireController implements GetConstantUtil {
    @Autowired
    DesireService desireService;

    @ResponseBody
    @RequestMapping("/desireList")
    public void selectLimitRandomTH(Message getDesireMess, HttpServletResponse response){
        if(getDesireMess.getmType() == GET_LIMIT_DESIRE){
            String resultJ = desireService.doSelectLimitRandomDesire(getDesireMess);
            OutDataUtil.outData(resultJ, response);
        }
    }

    @ResponseBody
    @RequestMapping("/updateDoLike")
    public void updateDoLike(Message updateDoLikeMess, HttpServletResponse response){
        if(updateDoLikeMess.getmType() == UPDATE_DESIRE_DO_LIKES){
            desireService.doUpdateDoLike(updateDoLikeMess);
        }
    }

    @ResponseBody
    @RequestMapping("/publicDesire")
    public void publicTreehole(Message publicDesireMess){
        if(publicDesireMess.getmType() == PUBLIC_DESIRE){
            desireService.doPublicDesire(publicDesireMess);
        }
    }

    @ResponseBody
    @RequestMapping("/addDesireFriend")
    public void addDesireFriend(Message addDesireFriMess, HttpServletResponse response){
        if(addDesireFriMess.getmType() == ADD_DESIRE_FRIEND){
            String resultJ = desireService.doAddDesireFriend(addDesireFriMess);
            OutDataUtil.outData(resultJ, response);
        }
    }
}
