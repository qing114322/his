package com.qhit.patientRegisterRecord.service.impl;

import com.qhit.patientRegisterRecord.service.IPatientRegisterRecordService;
import java.util.List;
import com.qhit.patientRegisterRecord.dao.IPatientRegisterRecordDao;
import com.qhit.patientRegisterRecord.dao.impl.PatientRegisterRecordDaoImpl;
import com.qhit.patientRegisterRecord.pojo.PatientRegisterRecord;

/**
* Created by GeneratorCode on 2018/12/21
*/

public class PatientRegisterRecordServiceImpl  implements IPatientRegisterRecordService {

    IPatientRegisterRecordDao dao = new PatientRegisterRecordDaoImpl();

    @Override 
    public boolean insert(Object object) { 
        return dao.insert(object); 
    } 


    @Override 
    public boolean update(Object object) { 
        return dao.update(object); 
    } 


    @Override 
    public boolean updateSelective(Object object) { 
        return dao.updateSelective(object); 
    } 


    @Override 
    public boolean delete(Object id) { 
        PatientRegisterRecord patientRegisterRecord = findById(id); 
        return dao.delete(patientRegisterRecord); 
    } 


    @Override 
    public List findAll() { 
        return dao.findAll(); 
    } 


    @Override 
    public PatientRegisterRecord findById(Object id) { 
        List<PatientRegisterRecord> list = dao.findById(id); 
        return  list.get(0); 
    } 


    @Override 
    public boolean freeUpdate(String sql) {
        return dao.freeUpdate(sql);
    }


    @Override 
    public List<PatientRegisterRecord> search(PatientRegisterRecord patientRegisterRecord) {
            String sql = "select * from patient_register_record where 1=1 "; 
            if (patientRegisterRecord.getPatientId()!=null && !"".equals(patientRegisterRecord.getPatientId())){        
                sql+=" and patient_id like '%"+patientRegisterRecord.getPatientId()+"%' ";        
            } 
            if (patientRegisterRecord.getDeptId()!=null && !"".equals(patientRegisterRecord.getDeptId())){        
                sql+=" and dept_id like '%"+patientRegisterRecord.getDeptId()+"%' ";        
            } 
            if (patientRegisterRecord.getRegisterDate()!=null && !"".equals(patientRegisterRecord.getRegisterDate())){        
                sql+=" and register_date like '%"+patientRegisterRecord.getRegisterDate()+"%' ";        
            } 
            if (patientRegisterRecord.getRecordUser()!=null && !"".equals(patientRegisterRecord.getRecordUser())){        
                sql+=" and record_user like '%"+patientRegisterRecord.getRecordUser()+"%' ";        
            } 
            if (patientRegisterRecord.getDoctorId()!=null && !"".equals(patientRegisterRecord.getDoctorId())){        
                sql+=" and doctor_id like '%"+patientRegisterRecord.getDoctorId()+"%' ";        
            } 
            if (patientRegisterRecord.getStatus()!=null && !"".equals(patientRegisterRecord.getStatus())){        
                sql+=" and status like '%"+patientRegisterRecord.getStatus()+"%' ";        
            } 
            List<PatientRegisterRecord> list = dao.freeFind(sql);        
            return list;        
    }



    @Override
    public List<PatientRegisterRecord> findAllRight() {
        String sql="SELECT * from patient_register_record prr  JOIN base_patient_info bpi ON prr.patient_id=bpi.patient_id WHERE prr.status=2";
        List <PatientRegisterRecord>list = dao.freeFind(sql);
        return list;
    }

    @Override
    public PatientRegisterRecord findpatientId(String s) {
        String sql="SELECT * from patient_register_record WHERE patient_id='"+s+"'";
        List<PatientRegisterRecord> list = dao.freeFind(sql);
        if(list!=null&&list.size()>0){
            return list.get(0);
        }
        return null;

    }
//查询办理就诊卡的信息的（三表连）
    @Override
    public List<PatientRegisterRecord> findThreeAll() {
        String sql=" SELECT * from patient_register_record prr LEFT JOIN base_patient_info bpi ON bpi.patient_id=prr.patient_id WHERE prr.status IN (2,3);";
        List<PatientRegisterRecord> list = dao.freeFind(sql);
        return list;
    }
    //办卡过后可以挂号的人（状态为2）
    @Override
    public List<PatientRegisterRecord> findAllStatus() {
        String sql=" SELECT * from patient_register_record prr left JOIN base_dept bd ON prr.dept_id=bd.dept_id\n" +
                "                                          JOIN base_patient_info bpi ON prr.patient_id=bpi.patient_id\n" +
                "                                          WHERE prr.status IN (2,3)\n";
        List<PatientRegisterRecord> list = dao.freeFind(sql);
        return list;
    }


}