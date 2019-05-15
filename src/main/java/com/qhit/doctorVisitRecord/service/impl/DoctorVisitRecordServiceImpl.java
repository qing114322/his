package com.qhit.doctorVisitRecord.service.impl;

import com.qhit.doctorVisitRecord.service.IDoctorVisitRecordService;
import java.util.List;
import com.qhit.doctorVisitRecord.dao.IDoctorVisitRecordDao;
import com.qhit.doctorVisitRecord.dao.impl.DoctorVisitRecordDaoImpl;
import com.qhit.doctorVisitRecord.pojo.DoctorVisitRecord;

import javax.print.Doc;

/**
* Created by GeneratorCode on 2018/12/26
*/

public class DoctorVisitRecordServiceImpl  implements IDoctorVisitRecordService {

    IDoctorVisitRecordDao dao = new DoctorVisitRecordDaoImpl();

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
        DoctorVisitRecord doctorVisitRecord = findById(id); 
        return dao.delete(doctorVisitRecord); 
    } 


    @Override 
    public List findAll() { 
        return dao.findAll(); 
    } 


    @Override 
    public DoctorVisitRecord findById(Object id) { 
        List<DoctorVisitRecord> list = dao.findById(id); 
        return  list.get(0); 
    } 


    @Override 
    public boolean freeUpdate(String sql) {
        return dao.freeUpdate(sql);
    }


    @Override 
    public List<DoctorVisitRecord> search(DoctorVisitRecord doctorVisitRecord) {
            String sql = "select * from doctor_visit_record where 1=1 "; 
            if (doctorVisitRecord.getPatientId()!=null && !"".equals(doctorVisitRecord.getPatientId())){        
                sql+=" and patient_id like '%"+doctorVisitRecord.getPatientId()+"%' ";        
            } 
            if (doctorVisitRecord.getDoctorId()!=null && !"".equals(doctorVisitRecord.getDoctorId())){        
                sql+=" and doctor_id like '%"+doctorVisitRecord.getDoctorId()+"%' ";        
            } 
            if (doctorVisitRecord.getVisitDate()!=null && !"".equals(doctorVisitRecord.getVisitDate())){        
                sql+=" and visit_date like '%"+doctorVisitRecord.getVisitDate()+"%' ";        
            } 
            if (doctorVisitRecord.getSymptom()!=null && !"".equals(doctorVisitRecord.getSymptom())){        
                sql+=" and symptom like '%"+doctorVisitRecord.getSymptom()+"%' ";        
            } 
            if (doctorVisitRecord.getAdvice()!=null && !"".equals(doctorVisitRecord.getAdvice())){        
                sql+=" and advice like '%"+doctorVisitRecord.getAdvice()+"%' ";        
            } 
            if (doctorVisitRecord.getTimes()!=null && !"".equals(doctorVisitRecord.getTimes())){        
                sql+=" and times like '%"+doctorVisitRecord.getTimes()+"%' ";        
            } 
            if (doctorVisitRecord.getStatus()!=null && !"".equals(doctorVisitRecord.getStatus())){        
                sql+=" and status like '%"+doctorVisitRecord.getStatus()+"%' ";        
            } 
            List<DoctorVisitRecord> list = dao.freeFind(sql);        
            return list;        
    }
    //查询有多少可以就诊的病人(前面的手续已办完)三表查
    @Override
    public List<DoctorVisitRecord> findTwoAll() {
        String sql="SELECT * from doctor_visit_record dvr  RIGHT JOIN base_patient_info bpi ON dvr.patient_id=bpi.patient_id\n" +
                "\t\t\t\t\t     JOIN patient_register_record prr ON prr.patient_id=bpi.patient_id\n" +
                "\t\t\t\t\t     WHERE prr.status=3";
        List<DoctorVisitRecord> list = dao.freeFind(sql);
        return list;
    }
    //查询有多少可以就诊的病人(前面的手续已办完)按医生id
    @Override
    public List<DoctorVisitRecord> findAllUserId(Integer userId) {
        String sql="SELECT * from doctor_visit_record dvr  RIGHT JOIN base_patient_info bpi ON dvr.patient_id=bpi.patient_id\n" +
                "\t\t\t\t\t     JOIN patient_register_record prr ON prr.patient_id=bpi.patient_id\n" +
                "\t\t\t\t\t     JOIN base_user bu ON  prr.dept_id=bu.dept_id\n" +
                "\t\t\t\t\t     WHERE prr.status=3\n" +
                "\t\t\t\t\t     AND bu.user_id='"+userId+"'";
        List<DoctorVisitRecord> list = dao.freeFind(sql);
        return list;
    }

    @Override
    public List<DoctorVisitRecord> findFourAll(Integer userId) {
        String sql="SELECT * from doctor_visit_record dvr  RIGHT JOIN base_patient_info bpi ON dvr.patient_id=bpi.patient_id\n" +
                "\t\t\t\t\t     JOIN patient_register_record prr ON prr.patient_id=bpi.patient_id\n" +
                "\t\t\t\t\t     JOIN base_user bu ON  prr.dept_id=bu.dept_id\n" +
                "\t\t\t\t\t     WHERE prr.status=3\n" +
                "\t\t\t\t\t     AND bu.user_id='"+userId+"'";
        List<DoctorVisitRecord> list = dao.freeFind(sql);
        return list;
    }


}