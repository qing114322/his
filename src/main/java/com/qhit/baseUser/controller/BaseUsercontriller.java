package com.qhit.baseUser.controller;

import com.alibaba.fastjson.JSON;
import com.qhit.baseRole.pojo.BaseRole;
import com.qhit.baseRole.service.IBaseRoleService;
import com.qhit.baseRole.service.impl.BaseRoleServiceImpl;
import com.qhit.baseUser.dao.IBaseUserDao;
import com.qhit.baseUser.dao.impl.BaseUserDaoImpl;
import com.qhit.baseUser.pojo.BaseUser;
import com.qhit.baseUser.service.IBaseUserService;
import com.qhit.baseUser.service.impl.BaseUserServiceImpl;
import com.qhit.baseUserRole.pojo.BaseUserRole;
import com.qhit.baseUserRole.service.IBaseUserRoleService;
import com.qhit.baseUserRole.service.impl.BaseUserRoleServiceImpl;
import com.qhit.utils.BaseDao;
import com.qhit.utils.MD5;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by tp on 2018/11/26.
 */
@Controller
@RequestMapping("/baseUser")
public class BaseUsercontriller {
    private IBaseUserDao dao = new BaseUserDaoImpl();
    private IBaseUserService service=new BaseUserServiceImpl();
    private IBaseRoleService baseRoleService = new BaseRoleServiceImpl();
    @RequestMapping("/list")
    public String list(HttpServletRequest request){
        List<BaseUser> list = service.findotwoAll();
        request.setAttribute("list",list);
        return "baseUser/list";
    }
    @RequestMapping("/insert")
    public String insert (BaseUser baseUser,Model model){
        int length = baseUser.getPassword().length();
        model.addAttribute("lenth",length);
        MD5 md5=new MD5();
        baseUser.setPassword(md5.getMD5ofStr(baseUser.getPassword()));
        service.insert(baseUser);
        return "forward:list.action";
    }
    @RequestMapping("/update")
    public String update (BaseUser baseUser, Model model){
        BaseUser user = service.findById(baseUser.getUserId());
        model.addAttribute("baseUser",user);
        return "baseUser/update";
    }
    @RequestMapping("/update1")
    public String update1 (BaseUser baseUser, Model model){
        service.updateSelective(baseUser);
        return "forward:list.action";
    }
    @RequestMapping("/delete")
    public String delete (BaseUser baseUser, Model model){
        service.delete(baseUser.getUserId());
        return "forward:list.action";
    }
    //通过部门id来查询部门里的医生
    @RequestMapping("/ajaxList")
    public void ajaxList(HttpServletResponse response,HttpServletRequest request) throws IOException {
        String deptId = request.getParameter("deptId");
        List<BaseUser> list = service.finddeptId(deptId);
        String s = JSON.toJSONString(list);
        response.getWriter().write(s);
    }
    @RequestMapping("/ajaxList2")
    public void ajaxList2(HttpServletResponse response,HttpServletRequest request) throws IOException {
        List<BaseUser> list = service.findAll();
        String s = JSON.toJSONString(list);
        response.getWriter().write(s);
    }
    @RequestMapping("/search")
    public String search (BaseUser baseUser, Model model){
        String sql="SELECT * from base_user WHERE 1=1 ";
        if(baseUser.getCname()!=null && !"".equals(baseUser.getCname())){
            sql+="AND cname LIKE '%"+baseUser.getCname()+"%' ";
        }
        if(baseUser.getSex()!=null&&!"".equals(baseUser.getSex())){
            sql+="AND sex = '"+baseUser.getSex()+"' ";
        }
        model.addAttribute("searchObject",baseUser);
        List<BaseUser> list = dao.freeFind(sql);
        model.addAttribute("list",list);
        return "baseUser/list";
    }
    @RequestMapping("/login")
    public String login (BaseUser baseUser, HttpServletRequest request){
        baseUser=  service.login(baseUser);
        if(baseUser!=null){
            HttpSession session = request.getSession();
            session.setAttribute("sessionUser",baseUser);
            Date date=new Date();
            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd hh:mm  E");
            String format = simpleDateFormat.format(date);
            session.setAttribute("format",format);
            return "index/home";
        }else {
            request.setAttribute("error","您输入的用户名或密码不正确!");
            return "baseUser/login";
        }
    }
    @RequestMapping("/logout")
    public String logout (BaseUser baseUser, HttpServletRequest request){
        HttpSession session = request.getSession();
        session.removeAttribute("sessionUser");
        return "baseUser/login";
    }
    @RequestMapping("/updatePassword")
    public void updatePassword (BaseUser baseUser, HttpServletResponse response) throws IOException {
       boolean flag= service.finPassword(baseUser);
       response.getWriter().write(flag?"T":"F");
    }
    @RequestMapping("/newPassword")
    public void newPassword (BaseUser baseUser, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        MD5 md5=new MD5();
        String password = md5.getMD5ofStr(baseUser.getPassword());
        baseUser.setPassword(password);
        boolean flag = service.updateSelective(baseUser);
//       response.sendRedirect("jsp/baseUser/login.jsp");
    }
    @RequestMapping("/details")
    public String details (BaseUserRole baseUserRole, Model model, BaseUser baseUser){
        List<BaseUserRole> list=service.finddetails( baseUser.getUserId());
        List<BaseUserRole> list1=service.findnodetails( baseUser.getUserId());
        model.addAttribute("list",list);
        model.addAttribute("list1",list1);
        model.addAttribute("userId",baseUser.getUserId());
        return "baseUser/distribute";
    }
    @RequestMapping("/distributeUpdate")
    public String distributeUpdate(BaseUser baseUser, HttpServletRequest request, Model model) {
        String[] rids = request.getParameterValues("rid");
        IBaseUserRoleService baseUserRoleService = new BaseUserRoleServiceImpl();
        String sql = "delete from base_user_role where uid=" + baseUser.getUserId();
        BaseDao baseDao=new BaseDao();
        baseDao.freeUpdate(sql);
        for (String rid : rids) {
            BaseUserRole baseUserRole = new BaseUserRole();
            baseUserRole.setRid(Integer.parseInt(rid));
            baseUserRole.setUid(baseUser.getUserId());
            baseUserRoleService.insert(baseUserRole);
        }
        return "forward:list.action";
    }
}
