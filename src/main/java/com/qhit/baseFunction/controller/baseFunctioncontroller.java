package com.qhit.baseFunction.controller;

import com.alibaba.fastjson.JSON;
import com.qhit.baseFunction.dao.IBaseFunctionDao;
import com.qhit.baseFunction.dao.impl.BaseFunctionDaoImpl;
import com.qhit.baseFunction.pojo.BaseFunction;
import com.qhit.baseFunction.service.IBaseFunctionService;
import com.qhit.baseFunction.service.impl.BaseFunctionServiceImpl;
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
@RequestMapping("baseFunction")
public class baseFunctioncontroller {
    private IBaseFunctionDao dao=new BaseFunctionDaoImpl();
    private IBaseFunctionService service=new BaseFunctionServiceImpl();
    @RequestMapping("/baseFunctionLeftlist")
    public void baseModulelist (BaseFunction baseFunction, HttpServletResponse response,Integer mid,Integer userId) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        if(mid==null){
            mid=1;
        }
        List<BaseFunction> list = service.findmid(mid,userId);
        String jsonString = JSON.toJSONString(list);
        response.getWriter().write(jsonString);
    }
    @RequestMapping("/baseFunctionUrllist")
    public void baseFunctionUrllist (BaseFunction baseFunction, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        List<BaseFunction> list = service.findAll();
        String jsonString = JSON.toJSONString(list);
        response.getWriter().write(jsonString);
    }
    @RequestMapping("/list")
    public String list (BaseFunction baseFunction, Model model) throws IOException {
        List<BaseFunction> list = service.findAll();
        model.addAttribute("list",list);
        return "baseFunction/list";
    }
    @RequestMapping("/insert")
    public String insert (BaseFunction baseFunction, Model model) throws IOException {
        service.insert(baseFunction);
        return "baseUser/list";
    }
    @RequestMapping("/update")
    public String update (BaseFunction baseFunction, Model model){
        BaseFunction user = service.findById(baseFunction.getFid());
        model.addAttribute("baseFunction",user);
        return "baseFunction/update";
    }
    @RequestMapping("/update1")
    public String update1 (BaseFunction baseFunction, Model model){
        service.update(baseFunction);
        return "forward:list.action";
    }
    @RequestMapping("/delete")
    public String delete (BaseFunction baseFunction, Model model){
        service.delete(baseFunction.getFid());
        return "forward:list.action";
    }
    @RequestMapping("/search")
    public String search (BaseFunction baseFunction, Model model){
        String sql="SELECT * from base_function WHERE 1=1 ";
        if(baseFunction.getFname()!=null && !"".equals(baseFunction.getFname())){
            sql+="AND fname LIKE '%"+baseFunction.getFname()+"%' ";
        }
        if(baseFunction.getMid()!=null&&!"".equals(baseFunction.getMid())){
            sql+="AND mid = '"+baseFunction.getMid()+"' ";
        }
        model.addAttribute("searchObject",baseFunction);
        List<BaseFunction> list = dao.freeFind(sql);
        model.addAttribute("list",list);
        return "baseFunction/list";
    }

}
