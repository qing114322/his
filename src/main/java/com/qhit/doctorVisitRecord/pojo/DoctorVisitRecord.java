package com.qhit.doctorVisitRecord.pojo;


import com.qhit.basePatientInfo.pojo.BasePatientInfo;
import com.qhit.baseUser.pojo.BaseUser;
import com.qhit.patientRegisterRecord.pojo.PatientRegisterRecord;
import com.sun.org.glassfish.gmbal.Description;

/**
* Created by GeneratorCode on 2018/12/28
*/

public class DoctorVisitRecord {

    private Integer vrId;    //主键
    private Integer patientId;    //病人
    private Integer doctorId;    //医生
    private String visitDate;    //就诊时间
    private String symptom;    //症状
    private String advice;    //医生建议
    private Integer times;    //就诊次数
    private Integer status;    //状态(1:已就诊2:已缴费3:已领药)
    @Description("bean")
    private PatientRegisterRecord patientRegisterRecord;
    @Description("bean")
    private BaseUser baseUser;
    @Description("bean")
    private BasePatientInfo basePatientInfo;

    public BasePatientInfo getBasePatientInfo() {
        return basePatientInfo;
    }

    public void setBasePatientInfo(BasePatientInfo basePatientInfo) {
        this.basePatientInfo = basePatientInfo;
    }

    public PatientRegisterRecord getPatientRegisterRecord() {
        return patientRegisterRecord;
    }

    public void setPatientRegisterRecord(PatientRegisterRecord patientRegisterRecord) {
        this.patientRegisterRecord = patientRegisterRecord;
    }

    public BaseUser getBaseUser() {
        return baseUser;
    }

    public void setBaseUser(BaseUser baseUser) {
        this.baseUser = baseUser;
    }

    public Integer getVrId() {
        return vrId;
    }
 
    public void setVrId(Integer vrId) { 
        this.vrId = vrId;
    }
 
    public Integer getPatientId() { 
        return patientId;
    }
 
    public void setPatientId(Integer patientId) { 
        this.patientId = patientId;
    }
 
    public Integer getDoctorId() { 
        return doctorId;
    }
 
    public void setDoctorId(Integer doctorId) { 
        this.doctorId = doctorId;
    }
 
    public String getVisitDate() { 
        return visitDate;
    }
 
    public void setVisitDate(String visitDate) { 
        this.visitDate = visitDate;
    }
 
    public String getSymptom() { 
        return symptom;
    }
 
    public void setSymptom(String symptom) { 
        this.symptom = symptom;
    }
 
    public String getAdvice() { 
        return advice;
    }
 
    public void setAdvice(String advice) { 
        this.advice = advice;
    }
 
    public Integer getTimes() { 
        return times;
    }
 
    public void setTimes(Integer times) { 
        this.times = times;
    }
 
    public Integer getStatus() { 
        return status;
    }
 
    public void setStatus(Integer status) { 
        this.status = status;
    }
 

 }