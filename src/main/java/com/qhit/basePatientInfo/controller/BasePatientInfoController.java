package com.qhit.basePatientInfo.controller; 

import com.qhit.basePatientInfo.pojo.BasePatientInfo; 
import com.qhit.basePatientInfo.service.IBasePatientInfoService; 
import com.qhit.basePatientInfo.service.impl.BasePatientInfoServiceImpl;
import com.qhit.doctorMenu.pojo.DoctorMenu;
import com.qhit.doctorMenu.service.IDoctorMenuService;
import com.qhit.doctorMenu.service.impl.DoctorMenuServiceImpl;
import netscape.javascript.JSObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model; 
import org.springframework.web.bind.annotation.RequestMapping; 
import javax.annotation.Resource; 
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse; 
import com.alibaba.fastjson.JSON; 
import java.io.IOException; 
import java.util.List; 

/**
* Created by GeneratorCode on 2018/12/21
*/
@Controller 
@RequestMapping("/basePatientInfo") 
public class BasePatientInfoController { 

    IBasePatientInfoService basePatientInfoService = new BasePatientInfoServiceImpl();;
    IDoctorMenuService doctorMenuService = new DoctorMenuServiceImpl();;

    @RequestMapping("/insert") 
    public String insert(BasePatientInfo basePatientInfo) { 
        basePatientInfoService.insert(basePatientInfo); 
        return "forward:list.action"; 
    } 
 
    @RequestMapping("/delete") 
    public String delete(Integer patientId, HttpServletResponse response) throws IOException { 
        basePatientInfoService.delete(patientId); 
        return "forward:list.action"; 
    } 
 
    @RequestMapping("/update") 
    public String update(BasePatientInfo basePatientInfo) { 
        basePatientInfoService.update(basePatientInfo); 
        return "forward:list.action"; 
    } 
 
    @RequestMapping("/updateSelective") 
    public String updateSelective(BasePatientInfo basePatientInfo) { 
        basePatientInfoService.updateSelective(basePatientInfo); 
        return "forward:list.action"; 
    } 
 
    @RequestMapping("/load") 
    public String load(Integer patientId, Model model) { 
        BasePatientInfo basePatientInfo = basePatientInfoService.findById(patientId); 
        model.addAttribute("basePatientInfo",basePatientInfo); 
        return "basePatientInfo/edit"; 
    } 
 
    @RequestMapping("/list") 
    public String list(Model model) throws IOException { 
        List<BasePatientInfo> list = basePatientInfoService.findAll();
        model.addAttribute("list",list); 
        return "basePatientInfo/list"; 
    } 
 
    @RequestMapping("/ajaxList") 
    public void ajaxList(HttpServletResponse response) throws IOException { 
        List<BasePatientInfo> list = basePatientInfoService.findAll(); 
        String s = JSON.toJSONString(list); 
        response.getWriter().write(s); 
    }
    @RequestMapping("/Listinfo")
    public String Listinfo(HttpServletResponse response,Model model,Integer patientId) throws IOException {
      //查询的在这个医生名下的病人
        BasePatientInfo basePatientInfo = basePatientInfoService.findBypatientId(patientId);
       model.addAttribute("basePatientInfo",basePatientInfo);
       //查询这个医生可用的套餐
        List<DoctorMenu> doctorMenuList=doctorMenuService.findType();
        model.addAttribute("doctorMenuList",doctorMenuList);
       return "doctorVisitRecord/distribute";
    }
    //-- 查询已登记但为办卡的病人
    @RequestMapping("/ajaxName")
    public void ajaxName(HttpServletResponse response) throws IOException {
        List<BasePatientInfo> list = basePatientInfoService.findNameAll();
        String s = JSON.toJSONString(list);
        response.getWriter().write(s);
    }
    //-- 通过病人id查询病人的详细信息
    @RequestMapping("/listpatientId")
    public String listpatientId(HttpServletResponse response,Integer patientId,Model model) throws IOException {
        BasePatientInfo basePatientInfo = basePatientInfoService.findpatientId(patientId);
        model.addAttribute("basePatientInfo",basePatientInfo);
     return "doctorVisitRecord/list";
    }

    @RequestMapping("/search") 
    public String search(BasePatientInfo basePatientInfo,Model model) {
        List<BasePatientInfo> list = basePatientInfoService.search(basePatientInfo); 
        model.addAttribute("list",list); 
        model.addAttribute("searchObject",basePatientInfo); 
        return "basePatientInfo/list"; 
    } 
 
} 
