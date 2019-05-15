package com.qhit.patientRegisterRecord.controller; 

import com.qhit.basePatientInfo.pojo.BasePatientInfo;
import com.qhit.basePatientInfo.service.IBasePatientInfoService;
import com.qhit.basePatientInfo.service.impl.BasePatientInfoServiceImpl;
import com.qhit.baseUser.pojo.BaseUser;
import com.qhit.patientRegisterRecord.pojo.PatientRegisterRecord;
import com.qhit.patientRegisterRecord.service.IPatientRegisterRecordService; 
import com.qhit.patientRegisterRecord.service.impl.PatientRegisterRecordServiceImpl; 
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
* Created by GeneratorCode on 2018/12/21
*/
@Controller 
@RequestMapping("/patientRegisterRecord") 
public class PatientRegisterRecordController { 

    IPatientRegisterRecordService patientRegisterRecordService = new PatientRegisterRecordServiceImpl();;
    IBasePatientInfoService basePatientInfoService = new BasePatientInfoServiceImpl();
    @RequestMapping("/insert") 
    public String insert(PatientRegisterRecord patientRegisterRecord) {

        patientRegisterRecordService.insert(patientRegisterRecord);
        return "forward:list.action"; 
    } 
 
    @RequestMapping("/delete") 
    public String delete(Integer registerId, HttpServletResponse response) throws IOException { 
        patientRegisterRecordService.delete(registerId); 
        return "forward:list.action"; 
    }
    @RequestMapping("/update")
    public String update(PatientRegisterRecord patientRegisterRecord) {
        patientRegisterRecordService.update(patientRegisterRecord);
        return "forward:listCon.action";
    }

    @RequestMapping("/updateCon")
    public String updateCon(PatientRegisterRecord patientRegisterRecord,HttpServletRequest request,Integer deptId) {
        //同过id查找要挂号的对象
        PatientRegisterRecord byId = patientRegisterRecordService.findById(patientRegisterRecord.getRegisterId());
        //添加挂号时间
        Date date=new Date();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        String format = simpleDateFormat.format(date);
        byId.setRegisterDate(format);
        //添加挂号部门

        byId.setDeptId(deptId);
        //修改挂号状态
        byId.setStatus(3);
        //添加挂号医生
        byId.setDoctorId(patientRegisterRecord.getDoctorId());
        //添加挂号人
        HttpSession session = request.getSession();
        BaseUser sessionUser = (BaseUser) session.getAttribute("sessionUser");
        byId.setRecordUser(sessionUser.getUserId());

        patientRegisterRecordService.updateSelective(byId);
        return "forward:listCon.action";
    } 
 
    @RequestMapping("/updateSelective") 
    public String updateSelective(PatientRegisterRecord patientRegisterRecord) { 
        patientRegisterRecordService.updateSelective(patientRegisterRecord); 
        return "forward:list.action"; 
    } 
 
    @RequestMapping("/load") 
    public String load(Integer registerId, Model model) { 
        PatientRegisterRecord patientRegisterRecord = patientRegisterRecordService.findById(registerId); 
        model.addAttribute("patientRegisterRecord",patientRegisterRecord); 
        return "patientRegisterRecord/edit"; 
    } 
 
    @RequestMapping("/list") 
    public String list(Model model) throws IOException { 
        List<PatientRegisterRecord> list = patientRegisterRecordService.findThreeAll();
        model.addAttribute("list",list); 
        return "patientRegisterRecord/list"; 
    }
    //办卡过后可以挂号的人（状态为2）
    @RequestMapping("/listCon")
    public String Twolist(Model model) throws IOException {
        List<PatientRegisterRecord> list = patientRegisterRecordService.findAllStatus();
        model.addAttribute("list",list);
        return "patientRegisterRecord/listCon";
    }

    @RequestMapping("/ajaxList") 
    public void ajaxList(HttpServletResponse response) throws IOException { 
        List<PatientRegisterRecord> list = patientRegisterRecordService.findAll(); 
        String s = JSON.toJSONString(list); 
        response.getWriter().write(s); 
    }
    @RequestMapping("/ajaxListLeft")
    public void ajaxListLeft(HttpServletResponse response) throws IOException {

        List<BasePatientInfo> list = basePatientInfoService.findAllLeft();
        String s = JSON.toJSONString(list);
        response.getWriter().write(s);
    }
    @RequestMapping("/ajaxListRight")
    public void ajaxListRight(HttpServletResponse response) throws IOException {
        List<PatientRegisterRecord> list = patientRegisterRecordService.findAllRight();
        String s = JSON.toJSONString(list);
        response.getWriter().write(s);
    }

    @RequestMapping("/search") 
    public String search(PatientRegisterRecord patientRegisterRecord,Model model) { 
        List<PatientRegisterRecord> list = patientRegisterRecordService.search(patientRegisterRecord); 
        model.addAttribute("list",list); 
        model.addAttribute("searchObject",patientRegisterRecord); 
        return "patientRegisterRecord/list"; 
    }
    @RequestMapping("/distributeUpdate")
    public String distributeUpdate(HttpServletRequest request,Model model) {
        //通过id来修改状态为一（未办卡）的人
        String str = request.getParameter("patientId");
        if(str!=null){
            String[] split = str.split(",");
            for(String s:split){
                PatientRegisterRecord patientRegisterRecord=patientRegisterRecordService.findpatientId(s);
                if(patientRegisterRecord!=null){
                    patientRegisterRecord.setStatus(2);
                    patientRegisterRecordService.updateSelective(patientRegisterRecord);
                }
                if(patientRegisterRecord==null){
                    //通过为null的病人id来查询病人数据
                    BasePatientInfo byId = basePatientInfoService.findById(s);
                    PatientRegisterRecord patientRegisterRecordnull =new PatientRegisterRecord();
                    //如果对象为空，则创建一个对象来存放数据
                    //添加病人id
                    patientRegisterRecordnull.setPatientId(Integer.valueOf(s));
                    //添加状态
                    patientRegisterRecordnull.setStatus(2);
                    patientRegisterRecordService.insert(patientRegisterRecordnull);
                }

            }
        }
        return "patientRegisterRecord/info";
    }

 
} 
