package com.qhit.baseRole.controller;

import com.alibaba.fastjson.JSON;
import com.qhit.baseDept.pojo.BaseDept;
import com.qhit.baseRole.dao.IBaseRoleDao;
import com.qhit.baseRole.dao.impl.BaseRoleDaoImpl;
import com.qhit.baseRole.pojo.BaseRole;
import com.qhit.baseRole.service.IBaseRoleService;
import com.qhit.baseRole.service.impl.BaseRoleServiceImpl;
import com.qhit.baseUser.pojo.BaseUser;
import com.qhit.baseUserRole.pojo.BaseUserRole;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by tp on 2018/11/29.
 */
@Controller
@RequestMapping("baseRole")
public class baseRolecontroller {
    private IBaseRoleDao dao=new BaseRoleDaoImpl();
    private IBaseRoleService service=new BaseRoleServiceImpl();
    @RequestMapping("/list")
    public String list (BaseRole baseRole, Model model) throws IOException {
        List<BaseRole> list = service.findAll();
        model.addAttribute("list",list);
        return "baseRole/list";
    }
    @RequestMapping("/listCon")
    public String listCon (BaseRole baseRole, Model model) throws IOException {
        List<BaseRole> list = service.findAll();
        model.addAttribute("list",list);
        return "baseRole/list";
    }
    @RequestMapping("/ajaxList")
    public void ajaxList(HttpServletResponse response) throws IOException {
        List<BaseRole> list = service.findAll();
        String s = JSON.toJSONString(list);
        response.getWriter().write(s);
    }
    @RequestMapping("/insert")
    public String insert (BaseRole baseRole, Model model) throws IOException {
        service.insert(baseRole);
        return "baseRole/list";
    }
    @RequestMapping("/update")
    public String update (BaseRole baseRole, Model model){
        BaseRole user = service.findById(baseRole.getRid());
        model.addAttribute("baseRole",user);
        return "baseRole/update";
    }
    @RequestMapping("/update1")
    public String update1 (BaseRole baseRole, Model model){
        service.update(baseRole);
        return "forward:list.action";
    }
    @RequestMapping("/delete")
    public String delete (BaseRole baseRole, Model model,HttpServletRequest request){
        boolean flag = (boolean) request.getAttribute("qx");
        if(!flag){
            return "error/authority";
        }
        service.delete(baseRole.getRid());
        return "forward:list.action";
    }
    @RequestMapping("/details")
    public String details (BaseRole baseRole, Model model, BaseUser baseUser){
        List<BaseUserRole> leftList = service.finddetails(baseRole.getRid());
        List<BaseRole> rightList = service.findnodetails(baseRole.getRid());
        model.addAttribute("leftList",leftList);
        model.addAttribute("rightList",rightList);
        model.addAttribute("rid",baseRole.getRid());
        model.addAttribute("rid",baseRole.getRid());
        return "baseRole/distribute";

    }
//    @RequestMapping("/Rolesearch")
//    public String search (BaseRole baseRole, Model model){
//        String sql="SELECT * from base_function WHERE 1=1 ";
//        if(baseRole.getFname()!=null && !"".equals(baseRole.getFname())){
//            sql+="AND fname LIKE '%"+baseRole.getFname()+"%' ";
//        }
//        if(baseRole.getMid()!=null&&!"".equals(baseRole.getMid())){
//            sql+="AND mid = '"+baseRole.getMid()+"' ";
//        }
//        model.addAttribute("searchObject",baseRole);
//        List<BaseRole> list = dao.freeFind(sql);
//        model.addAttribute("list",list);
//        return "baseFunction/list";
//    }
@RequestMapping("/distributeUpdate")
public String detailsUpdate (BaseRole baseRole, HttpServletRequest request, Model model, BaseUser baseUser){
        String[] fid = request.getParameterValues("fid");
        service.distributeUpdate(baseRole.getRid(),fid);
        return "forward:list.action";
}

}
