package com.qhit.doctorVisitRecord.dao.impl;

import com.qhit.doctorVisitRecord.dao.IDoctorVisitRecordDao;
import com.qhit.utils.BaseDao;
import java.util.List;

/**
* Created by GeneratorCode on 2018/12/26
*/

public class DoctorVisitRecordDaoImpl extends BaseDao implements IDoctorVisitRecordDao {

    @Override 
    public List findAll() { 
        String sql = "select * from doctor_visit_record"; 
        return freeFind(sql); 
    } 


    @Override 
    public List findById(Object id) { 
        String sql = "select * from doctor_visit_record where vr_id='"+id+"'"; 
        return freeFind(sql); 
    } 


    @Override 
    public List findByPatientId(Object patientId) { 
        String sql = "select * from doctor_visit_record where patient_id='"+patientId+"'"; 
        return freeFind(sql); 
    } 


    @Override 
    public List findByDoctorId(Object doctorId) { 
        String sql = "select * from doctor_visit_record where doctor_id='"+doctorId+"'"; 
        return freeFind(sql); 
    } 


    @Override 
    public List findByVisitDate(Object visitDate) { 
        String sql = "select * from doctor_visit_record where visit_date='"+visitDate+"'"; 
        return freeFind(sql); 
    } 


    @Override 
    public List findBySymptom(Object symptom) { 
        String sql = "select * from doctor_visit_record where symptom='"+symptom+"'"; 
        return freeFind(sql); 
    } 


    @Override 
    public List findByAdvice(Object advice) { 
        String sql = "select * from doctor_visit_record where advice='"+advice+"'"; 
        return freeFind(sql); 
    } 


    @Override 
    public List findByTimes(Object times) { 
        String sql = "select * from doctor_visit_record where times='"+times+"'"; 
        return freeFind(sql); 
    } 


    @Override 
    public List findByStatus(Object status) { 
        String sql = "select * from doctor_visit_record where status='"+status+"'"; 
        return freeFind(sql); 
    } 




}