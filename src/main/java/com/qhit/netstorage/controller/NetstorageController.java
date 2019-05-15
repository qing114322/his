package com.qhit.netstorage.controller; 

import com.qhit.baseUser.pojo.BaseUser;
import com.qhit.netstorage.pojo.Netstorage;
import com.qhit.netstorage.service.INetstorageService; 
import com.qhit.netstorage.service.impl.NetstorageServiceImpl; 
import org.springframework.stereotype.Controller; 
import org.springframework.ui.Model; 
import org.springframework.web.bind.annotation.RequestMapping; 
import javax.annotation.Resource; 
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSON;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
* Created by GeneratorCode on 2018/12/21
*/
@Controller 
@RequestMapping("/netstorage") 
public class NetstorageController { 

    INetstorageService netstorageService = new NetstorageServiceImpl();; 

    @RequestMapping("/insert") 
    public String insert(HttpServletRequest request) {
        Date date=new Date();
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        String format1 = format.format(date);
        String[] fileanames = request.getParameterValues("fileaname");
        Netstorage netstorage=new Netstorage();
        //获取uoload文件下的实际路径
        String path = request.getServletContext().getRealPath("upload");
        //获取用户id
        HttpSession session = request.getSession();
        BaseUser sessionUser = (BaseUser) session.getAttribute("sessionUser");
        //遍历数组
        for(String s:fileanames){
            //添加文件名称
            netstorage.setFileaname(s);
            //上传大小
            File file=new File(path+"/"+s);
            netstorage.setFilesize(s.length());
            //上传日期
            netstorage.setUploaddate(format1);
            netstorageService.insert(netstorage);

        }

        return "forward:list.action";
    } 
 
    @RequestMapping("/delete") 
    public String delete(Integer fileid, HttpServletResponse response) throws IOException { 
        netstorageService.delete(fileid); 
        return "forward:list.action"; 
    } 
 
    @RequestMapping("/update") 
    public String update(Netstorage netstorage) { 
        netstorageService.update(netstorage); 
        return "forward:list.action"; 
    } 
 
    @RequestMapping("/updateSelective") 
    public String updateSelective(Netstorage netstorage) { 
        netstorageService.updateSelective(netstorage); 
        return "forward:list.action"; 
    } 
 
    @RequestMapping("/load") 
    public String load(Integer fileid, Model model) { 
        Netstorage netstorage = netstorageService.findById(fileid); 
        model.addAttribute("netstorage",netstorage); 
        return "netstorage/edit"; 
    } 
 
    @RequestMapping("/list") 
    public String list(Model model) throws IOException { 
        List<Netstorage> list = netstorageService.findAll(); 
        model.addAttribute("list",list); 
        return "netstorage/list"; 
    } 
 
    @RequestMapping("/ajaxList") 
    public void ajaxList(HttpServletResponse response) throws IOException { 
        List<Netstorage> list = netstorageService.findAll(); 
        String s = JSON.toJSONString(list); 
        response.getWriter().write(s); 
    } 
 
    @RequestMapping("/search") 
    public String search(Netstorage netstorage,Model model) { 
        List<Netstorage> list = netstorageService.search(netstorage); 
        model.addAttribute("list",list); 
        model.addAttribute("searchObject",netstorage); 
        return "netstorage/list"; 
    } 
 
} 
