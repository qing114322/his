package com.qhit.patientRegisterRecord.service;

import java.util.List;
import com.qhit.patientRegisterRecord.pojo.PatientRegisterRecord;
/**
* Created by GeneratorCode on 2018/12/21
*/

public interface IPatientRegisterRecordService {

    boolean insert(Object object);

    boolean  update(Object object);

    boolean  updateSelective(Object object);

    boolean delete(Object id);

    List findAll();

    PatientRegisterRecord findById(Object id);

    boolean freeUpdate(String sql);

    List<PatientRegisterRecord> search(PatientRegisterRecord patientRegisterRecord);



    List<PatientRegisterRecord> findAllRight();

    PatientRegisterRecord findpatientId(String s);

    List<PatientRegisterRecord> findThreeAll();

    List<PatientRegisterRecord> findAllStatus();
}