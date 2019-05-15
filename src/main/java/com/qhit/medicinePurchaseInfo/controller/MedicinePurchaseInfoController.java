package com.qhit.medicinePurchaseInfo.controller; 

import com.qhit.baseUser.pojo.BaseUser;
import com.qhit.baseUser.service.IBaseUserService;
import com.qhit.baseUser.service.impl.BaseUserServiceImpl;
import com.qhit.medicineCode.pojo.MedicineCode;
import com.qhit.medicineCode.service.IMedicineCodeService;
import com.qhit.medicineCode.service.impl.MedicineCodeServiceImpl;
import com.qhit.medicinePurchaseInfo.pojo.MedicinePurchaseInfo;
import com.qhit.medicinePurchaseInfo.service.IMedicinePurchaseInfoService; 
import com.qhit.medicinePurchaseInfo.service.impl.MedicinePurchaseInfoServiceImpl; 
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
* Created by GeneratorCode on 2018/12/03
*/
@Controller 
@RequestMapping("/medicinePurchaseInfo") 
public class MedicinePurchaseInfoController { 

    IMedicinePurchaseInfoService medicinePurchaseInfoService = new MedicinePurchaseInfoServiceImpl();
    private IBaseUserService service=new BaseUserServiceImpl();
    IMedicineCodeService medicineCodeService = new MedicineCodeServiceImpl();;

    @RequestMapping("/insert") 
    public String insert(MedicinePurchaseInfo medicinePurchaseInfo, BaseUser baseUser, HttpSession session, HttpServletRequest request) {
        medicinePurchaseInfo.setStatus(1);
        Date date=new Date();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("YYYY-MM-dd");
        String format = simpleDateFormat.format(date);
        medicinePurchaseInfo.setPchDate(format);
//        service.findById(medicinePurchaseInfo.getPchUserid());

//        request.setAttribute("BaseUsera",byId);
//        Object sessionUser = session.getAttribute("sessionUser");
//        medicinePurchaseInfo.setPchUserid(sessionUser);

        medicinePurchaseInfoService.insert(medicinePurchaseInfo);
        List medicinePurchaseInfoServiceAll = medicinePurchaseInfoService.findAll();
        request.setAttribute("medicinePurchaseInfo",medicinePurchaseInfoServiceAll);
        return "forward:list.action"; 
    } 
 
    @RequestMapping("/delete") 
    public String delete(Integer pchId, HttpServletResponse response) throws IOException { 
        medicinePurchaseInfoService.delete(pchId); 
        return "forward:list.action"; 
    } 
 
    @RequestMapping("/update") 
    public String update(MedicinePurchaseInfo medicinePurchaseInfo) { 
        medicinePurchaseInfoService.update(medicinePurchaseInfo); 
        return "forward:list.action"; 
    } 
 
    @RequestMapping("/updateSelective") 
    public String updateSelective(MedicinePurchaseInfo medicinePurchaseInfo) { 
        medicinePurchaseInfoService.updateSelective(medicinePurchaseInfo); 
        return "forward:list.action"; 
    } 
 
    @RequestMapping("/load") 
    public String load(Integer pchId, Model model) { 
        MedicinePurchaseInfo medicinePurchaseInfo = medicinePurchaseInfoService.findById(pchId); 
        model.addAttribute("medicinePurchaseInfo",medicinePurchaseInfo); 
        return "medicinePurchaseInfo/edit"; 
    } 
 
    @RequestMapping("/list") 
    public String list(Model model) throws IOException {
        //汇总
        Date date=new Date();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("YYYY-MM-dd");
        String format = simpleDateFormat.format(date);
//        MedicinePurchaseInfo medicinePurchaseInfo=new MedicinePurchaseInfo();
//        medicinePurchaseInfo.setStatus(3);
//        medicinePurchaseInfoService.updateSelective(medicinePurchaseInfo);
        List<MedicineCode> list = medicineCodeService.findall(format);
        for(MedicineCode s :list){
            s.getMedicineReqPlan().setStatus(3);
        }
        String s = JSON.toJSONString(list);
        System.out.println(s);
        model.addAttribute("list",list);
        model.addAttribute("format",format);
        return "medicinePurchaseInfo/list"; 
    } 
 
    @RequestMapping("/ajaxList") 
    public void ajaxList(HttpServletResponse response) throws IOException { 
        List<MedicinePurchaseInfo> list = medicinePurchaseInfoService.findAll(); 
        String s = JSON.toJSONString(list); 
        response.getWriter().write(s); 
    } 
 
    @RequestMapping("/search") 
    public String search(MedicinePurchaseInfo medicinePurchaseInfo,Model model) { 
        List<MedicinePurchaseInfo> list = medicinePurchaseInfoService.search(medicinePurchaseInfo); 
        model.addAttribute("list",list); 
        model.addAttribute("searchObject",medicinePurchaseInfo); 
        return "medicinePurchaseInfo/list"; 
    } 
 
} 
