package com.qhit.baseModule.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qhit.baseModule.pojo.BaseModule;
import com.qhit.baseModule.service.IBaseModuleService;
import com.qhit.baseModule.service.impl.BaseModuleServiceImpl;
import com.qhit.baseUser.pojo.BaseUser;
import netscape.javascript.JSObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by tp on 2018/11/27.
 */
@Controller
@RequestMapping("/baseModule")
public class baseModulecontroller {
    private IBaseModuleService service=new BaseModuleServiceImpl();
    @RequestMapping("/baseModuleToplist")
    public void baseModulelist (BaseModule baseModule, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        List<BaseModule> list = service.findAll();
        String jsonString = JSON.toJSONString(list);
        response.getWriter().write(jsonString);
    }
    @RequestMapping("/list")
    public String list (BaseModule baseModule, Model model) throws IOException {
        List<BaseModule> list = service.findAll();
        model.addAttribute("list",list);
        return "baseModule/list";
    }
    @RequestMapping("/insert")
    public String insert (BaseModule baseModule, Model model) throws IOException {
        service.insert(baseModule);
        return "baseModule/list";
    }
    @RequestMapping("/update")
    public String update (BaseModule baseModule, Model model){
        BaseModule user = service.findById(baseModule.getMid());
        model.addAttribute("baseModule",user);
        return "baseModule/update";
    }
    @RequestMapping("/update1")
    public String update1 (BaseModule baseModule, Model model){
        service.update(baseModule);
        return "forward:list.action";
    }
    @RequestMapping("/delete")
    public String delete (BaseModule baseModule, Model model){
        service.delete(baseModule.getMid());
        return "forward:list.action";
    }
}
