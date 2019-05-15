package com.qhit.medicineReqPlan.controller; 

import com.qhit.baseUser.pojo.BaseUser;
import com.qhit.baseUser.service.IBaseUserService;
import com.qhit.baseUser.service.impl.BaseUserServiceImpl;
import com.qhit.medicineReqPlan.pojo.MedicineReqPlan;
import com.qhit.medicineReqPlan.service.IMedicineReqPlanService; 
import com.qhit.medicineReqPlan.service.impl.MedicineReqPlanServiceImpl; 
import org.springframework.stereotype.Controller; 
import org.springframework.ui.Model; 
import org.springframework.web.bind.annotation.RequestMapping; 
import javax.annotation.Resource; 
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSON; 
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
* Created by GeneratorCode on 2018/12/05
*/
@Controller 
@RequestMapping("/medicineReqPlan") 
public class MedicineReqPlanController { 

    IMedicineReqPlanService medicineReqPlanService = new MedicineReqPlanServiceImpl();
    IBaseUserService service=new BaseUserServiceImpl();
    @RequestMapping("/insert") 
    public String insert(MedicineReqPlan medicineReqPlan, Model model, HttpSession session) {
//        List<BaseUser>list = service.findByuid(medicineReqPlan.getAppUserid());
//        medicineReqPlanService.insert(medicineReqPlan);
        //状态默认值
        BaseUser sessionUser = (BaseUser) session.getAttribute("sessionUser");
        medicineReqPlan.setStatus(1);
        //申请日期默认值
        Date date=new Date();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        String format = simpleDateFormat.format(date);
        medicineReqPlan.setAppDate(format);
//        if(sessionUser)
        medicineReqPlanService.insert(medicineReqPlan);
//        model.addAttribute("list",list);
        return "forward:list.action"; 
    } 
 
    @RequestMapping("/delete") 
    public String delete(Integer reqplnno, HttpServletResponse response) throws IOException { 
        medicineReqPlanService.delete(reqplnno); 
        return "forward:list.action"; 
    } 
 
    @RequestMapping("/update") 
    public String update(MedicineReqPlan medicineReqPlan) { 
        medicineReqPlanService.update(medicineReqPlan); 
        return "forward:list.action"; 
    } 
 
    @RequestMapping("/updateSelective") 
    public String updateSelective(MedicineReqPlan medicineReqPlan) { 
        medicineReqPlanService.updateSelective(medicineReqPlan); 
        return "forward:list.action"; 
    } 
 
    @RequestMapping("/load") 
    public String load(Integer reqplnno, Model model) { 
        MedicineReqPlan medicineReqPlan = medicineReqPlanService.findById(reqplnno); 
        model.addAttribute("medicineReqPlan",medicineReqPlan); 
        return "medicineReqPlan/edit"; 
    }
    @RequestMapping("/loadCon")
    public String loadCon(String reqplnno, Model model,Integer apprvUserid) {
        Date date=new Date();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        String format = simpleDateFormat.format(date);
        String[] split = reqplnno.split(",");
        for (int i = 0; i <split.length ; i++) {
            MedicineReqPlan byId = medicineReqPlanService.findById(split[i]);
            byId.setStatus(2);
            byId.setApprvUserid(apprvUserid);
            byId.setApprvDate(format);
            medicineReqPlanService.updateSelective(byId);
        }
        return "forward:listCon.action";
    }

    @RequestMapping("/list") 
    public String list(Model model) throws IOException { 
        List<MedicineReqPlan> list = medicineReqPlanService.findAll(); 
        model.addAttribute("list",list); 
        return "medicineReqPlan/list"; 
    }
    @RequestMapping("/listCon")
    public String listCon(Model model) throws IOException {
        List<MedicineReqPlan> list = medicineReqPlanService.findAll();
        model.addAttribute("list",list);
        return "medicineReqPlan/listCon";
    }

    @RequestMapping("/ajaxList") 
    public void ajaxList(HttpServletResponse response) throws IOException { 
        List<MedicineReqPlan> list = medicineReqPlanService.findAll(); 
        String s = JSON.toJSONString(list); 
        response.getWriter().write(s); 
    } 
 
    @RequestMapping("/search") 
    public String search(MedicineReqPlan medicineReqPlan,Model model) { 
        List<MedicineReqPlan> list = medicineReqPlanService.search(medicineReqPlan); 
        model.addAttribute("list",list); 
        model.addAttribute("searchObject",medicineReqPlan); 
        return "medicineReqPlan/list"; 
    } 
 
} 
