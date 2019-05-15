package com.qhit.baseUserRole.controller;

import com.qhit.baseUser.pojo.BaseUser;
import com.qhit.baseUserRole.pojo.BaseUserRole;
import com.qhit.baseUserRole.service.IBaseUserRoleService;
import com.qhit.baseUserRole.service.impl.BaseUserRoleServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by tp on 2018/11/29.
 */
@Controller
@RequestMapping("baseUserRole")
public class baseUserRolecontroller {
    @Resource

     IBaseUserRoleService baseUserRoleService;

    @RequestMapping("/checkox")
    public String details (BaseUserRole baseUserRole, Model model, BaseUser baseUser){
        List<BaseUserRole> list=baseUserRoleService.findrid( baseUserRole.getRid());
        model.addAttribute("list",list);
        return "baseUserRole/baseUserRole";
    }
}
