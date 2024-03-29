package com.qhit.basePatientInfo.service;

import java.util.List;
import com.qhit.basePatientInfo.pojo.BasePatientInfo;
import com.qhit.patientRegisterRecord.pojo.PatientRegisterRecord;

/**
* Created by GeneratorCode on 2018/12/21
*/

public interface IBasePatientInfoService {

    boolean insert(Object object);

    boolean  update(Object object);

    boolean  updateSelective(Object object);

    boolean delete(Object id);

    List findAll();

    BasePatientInfo findById(Object id);

    boolean freeUpdate(String sql);

    List<BasePatientInfo> search(BasePatientInfo basePatientInfo);

    List<BasePatientInfo> findNameAll();

    List<BasePatientInfo> findAllLeft();


    BasePatientInfo findpatientId(Integer patientId);

    BasePatientInfo findBypatientId(Integer patientId);
}