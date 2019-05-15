package com.qhit.doctorVisitRecord.controller; 

import com.qhit.basePatientInfo.pojo.BasePatientInfo;
import com.qhit.basePatientInfo.service.IBasePatientInfoService;
import com.qhit.basePatientInfo.service.impl.BasePatientInfoServiceImpl;
import com.qhit.baseUser.pojo.BaseUser;
import com.qhit.doctorMenu.pojo.DoctorMenu;
import com.qhit.doctorMenu.service.IDoctorMenuService;
import com.qhit.doctorMenu.service.impl.DoctorMenuServiceImpl;
import com.qhit.doctorVisitRecord.pojo.DoctorVisitRecord;
import com.qhit.doctorVisitRecord.service.IDoctorVisitRecordService; 
import com.qhit.doctorVisitRecord.service.impl.DoctorVisitRecordServiceImpl;
import com.qhit.patientRegisterRecord.pojo.PatientRegisterRecord;
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
* Created by GeneratorCode on 2018/12/26
*/
@Controller 
@RequestMapping("/doctorVisitRecord") 
public class DoctorVisitRecordController { 

    IDoctorVisitRecordService doctorVisitRecordService = new DoctorVisitRecordServiceImpl();;
    IBasePatientInfoService basePatientInfoService = new BasePatientInfoServiceImpl();;
    @RequestMapping("/insert") 
    public String insert(Integer patientId,String symptom,String advice,Model model) {
        Date date=new Date();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        String format = simpleDateFormat.format(date);
        //通过病人id来查找病人详细信息，并把症状和嘱托加入
       BasePatientInfo basePatientInfo=basePatientInfoService.findBypatientId(patientId);
        DoctorVisitRecord doctorVisitRecord=new DoctorVisitRecord();
        //插入病人id
        doctorVisitRecord.setPatientId(basePatientInfo.getPatientId());
        //插入就诊医生
//        doctorVisitRecord.setDoctorId(1);
        //插入就诊时间
        doctorVisitRecord.setVisitDate(format);
        //插入症状
        doctorVisitRecord.setSymptom(symptom);
        //插入医生建议
        doctorVisitRecord.setAdvice(advice);
        //插入就诊次数
//        doctorVisitRecord.setTimes();
        //插入装态（1：已就诊  2：已缴费  3:已领药）
        doctorVisitRecord.setStatus(1);
//        doctorVisitRecord.setPatientRegisterRecord(null);
        String s = JSON.toJSONString(doctorVisitRecord);
        System.out.println(s);
        doctorVisitRecordService.insert(doctorVisitRecord);

        //转发前的数据 可用套餐  已拥有药品  未拥有药品
        //可用套餐
        IDoctorMenuService doctorMenuService = new DoctorMenuServiceImpl();
        List<DoctorMenu> listLef = doctorMenuService.findAll();
        model.addAttribute("listLef",listLef);
        //已拥有药品


        return "doctorVisitRecord/distribute";
    } 
 
    @RequestMapping("/delete") 
    public String delete(Integer vrId, HttpServletResponse response) throws IOException { 
        doctorVisitRecordService.delete(vrId); 
        return "forward:list.action"; 
    } 
 
    @RequestMapping("/update") 
    public String update(DoctorVisitRecord doctorVisitRecord) { 
        doctorVisitRecordService.update(doctorVisitRecord); 
        return "forward:list.action"; 
    } 
 
    @RequestMapping("/updateSelective") 
    public String updateSelective(DoctorVisitRecord doctorVisitRecord) { 
        doctorVisitRecordService.updateSelective(doctorVisitRecord); 
        return "forward:list.action"; 
    } 
 
    @RequestMapping("/load") 
    public String load(Integer vrId, Model model) { 
        DoctorVisitRecord doctorVisitRecord = doctorVisitRecordService.findById(vrId); 
        model.addAttribute("doctorVisitRecord",doctorVisitRecord); 
        return "doctorVisitRecord/edit"; 
    }
    //通过医生id查找挂号在这个医生名下的病人
    @RequestMapping("/list")
    public String list(Model model,HttpServletRequest request) throws IOException {
        HttpSession session = request.getSession();
        BaseUser sessionUser = (BaseUser) session.getAttribute("sessionUser");
        List<DoctorVisitRecord> list = doctorVisitRecordService.findFourAll(sessionUser.getUserId());
        model.addAttribute("list",list);
        return "doctorVisitRecord/distribute";
    }
    //查询有多少可以就诊的病人(前面的手续已办完)按医生id
    @RequestMapping("/listuserId")
    public void listuserId(Model model,HttpServletResponse response,Integer userId) throws IOException {
        List<DoctorVisitRecord> list = doctorVisitRecordService.findAllUserId(userId);
        String s = JSON.toJSONString(list);
        System.out.println(s);
        response.getWriter().write(s);

    }

    @RequestMapping("/ajaxList") 
    public void ajaxList(HttpServletResponse response) throws IOException { 

    } 
 
    @RequestMapping("/search") 
    public String search(DoctorVisitRecord doctorVisitRecord,Model model) { 
        List<DoctorVisitRecord> list = doctorVisitRecordService.search(doctorVisitRecord); 
        model.addAttribute("list",list); 
        model.addAttribute("searchObject",doctorVisitRecord); 
        return "doctorVisitRecord/list"; 
    } 
 
} 
