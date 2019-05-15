package com.qhit.doctorMenuMedicine.service.impl;

import com.qhit.doctorMenuMedicine.service.IDoctorMenuMedicineService;
import java.util.List;
import com.qhit.doctorMenuMedicine.dao.IDoctorMenuMedicineDao;
import com.qhit.doctorMenuMedicine.dao.impl.DoctorMenuMedicineDaoImpl;
import com.qhit.doctorMenuMedicine.pojo.DoctorMenuMedicine;

/**
* Created by GeneratorCode on 2018/12/19
*/

public class DoctorMenuMedicineServiceImpl  implements IDoctorMenuMedicineService {

    IDoctorMenuMedicineDao dao = new DoctorMenuMedicineDaoImpl();

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
        DoctorMenuMedicine doctorMenuMedicine = findById(id); 
        return dao.delete(doctorMenuMedicine); 
    } 


    @Override 
    public List findAll() { 
        return dao.findAll(); 
    } 


    @Override 
    public DoctorMenuMedicine findById(Object id) { 
        List<DoctorMenuMedicine> list = dao.findById(id); 
        return  list.get(0); 
    } 


    @Override 
    public boolean freeUpdate(String sql) {
        return dao.freeUpdate(sql);
    }


    @Override 
    public List<DoctorMenuMedicine> search(DoctorMenuMedicine doctorMenuMedicine) {
            String sql = "SELECT * from doctor_menu_medicine dmm JOIN medicine_code mc ON dmm.code_id=mc.code_id" +
                    " JOIN doctor_menu dm  ON dmm.menu_id=dm.menu_id where 1=1 ";
            if (doctorMenuMedicine.getMenuId()!=null && !"".equals(doctorMenuMedicine.getMenuId())){        
                sql+=" and dmm.menu_id like '%"+doctorMenuMedicine.getMenuId()+"%' ";
            } 
            if (doctorMenuMedicine.getCodeId()!=null && !"".equals(doctorMenuMedicine.getCodeId())){        
                sql+=" and dmm.code_id like '%"+doctorMenuMedicine.getCodeId()+"%' ";
            } 
            if (doctorMenuMedicine.getAmt()!=null && !"".equals(doctorMenuMedicine.getAmt())){        
                sql+=" and dmm.amt like '%"+doctorMenuMedicine.getAmt()+"%' ";
            } 
            List<DoctorMenuMedicine> list = dao.freeFind(sql);        
            return list;        
    }

    @Override
    public List<DoctorMenuMedicine> findMenuId(Integer menuId) {

        String sql="SELECT * from doctor_menu_medicine dmm JOIN medicine_code mc ON dmm.code_id=mc.code_id\n" +
                "                                       JOIN doctor_menu dm  ON dmm.menu_id=dm.menu_id\n" +
                " WHERE dmm.menu_id='"+menuId+"'";
        List<DoctorMenuMedicine> list = dao.freeFind(sql);
        return list;
    }

    @Override
    public List<DoctorMenuMedicine> findNewAll() {
            String sql=" SELECT * from doctor_menu_medicine dmm LEFT JOIN medicine_code mc ON dmm.code_id=mc.code_id\n" +
                    "                                      LEFT  JOIN doctor_menu dm  ON dmm.menu_id=dm.menu_id";
            List<DoctorMenuMedicine> list = dao.freeFind(sql);
            return list;
        }

    @Override
    public List<DoctorMenuMedicine> findajaxListLeft(Integer menuId) {
        String sql="SELECT * from doctor_menu_medicine dmm JOIN medicine_code mc ON dmm.code_id=mc.code_id\n" +
                "WHERE dmm.menu_id='"+menuId+"'";
        List<DoctorMenuMedicine> list = dao.freeFind(sql);
        return list;
    }


}