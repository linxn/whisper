package com.linxn.controller;

import com.linxn.domain.Message;
import com.linxn.domain.Treehole;
import com.linxn.service.TreeHoleService;
import com.linxn.util.GetConstantUtil;
import com.linxn.util.OutDataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/**
 * Created by linxn on 2018/4/24.
 */
@Controller
@RequestMapping("/treehole")
public class TreeHoleController implements GetConstantUtil {
    @Autowired
    TreeHoleService treeHoleService;

    @ResponseBody
    @RequestMapping("/treeholeList")
    public void selectLimitRandomTH(Message getTreeHoleMess, HttpServletResponse response){
        if(getTreeHoleMess.getmType() == GET_LIMIT_TREEHOLE){
            String resultJ = treeHoleService.doSelectLimitRandomTH(getTreeHoleMess);
            OutDataUtil.outData(resultJ, response);
        }
    }

    @ResponseBody
    @RequestMapping("/updateDoLike")
    public void updateDoLike(Message updateDoLikeMess, HttpServletResponse response){
        if(updateDoLikeMess.getmType() == UPDATE_TH_DO_LIKES){
            treeHoleService.doUpdateDoLike(updateDoLikeMess);
        }
    }

    @ResponseBody
    @RequestMapping("/publicTreehole")
    public void publicTreehole(Message publicTreeholeMess, HttpServletResponse response){
        if(publicTreeholeMess.getmType() == PUBLIC_TREEHOLE){
            treeHoleService.doPublicTreehole(publicTreeholeMess);
        }
    }

}
