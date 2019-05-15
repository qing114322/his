package com.qhit.doctorVisitRecord.dao;

import com.qhit.utils.BaseDao;
import java.util.List;

/**
* Created by GeneratorCode on 2018/12/26
*/

public interface IDoctorVisitRecordDao {

    boolean insert(Object object);

    boolean  update(Object object);

    boolean  updateSelective(Object object);

    boolean delete(Object object);

    List freeFind(String sql);

    List findAll();

    List findById(Object id);

    boolean freeUpdate(String sql);

    List findByPatientId(Object patientId);

    List findByDoctorId(Object doctorId);

    List findByVisitDate(Object visitDate);

    List findBySymptom(Object symptom);

    List findByAdvice(Object advice);

    List findByTimes(Object times);

    List findByStatus(Object status);

}