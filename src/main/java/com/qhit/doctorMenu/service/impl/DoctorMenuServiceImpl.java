package com.qhit.doctorMenu.service.impl;

import com.alibaba.fastjson.JSON;
import com.qhit.doctorMenu.service.IDoctorMenuService;
import java.util.List;
import com.qhit.doctorMenu.dao.IDoctorMenuDao;
import com.qhit.doctorMenu.dao.impl.DoctorMenuDaoImpl;
import com.qhit.doctorMenu.pojo.DoctorMenu;
import com.qhit.doctorMenuMedicine.pojo.DoctorMenuMedicine;

/**
* Created by GeneratorCode on 2018/12/19
*/

public class DoctorMenuServiceImpl  implements IDoctorMenuService {

    IDoctorMenuDao dao = new DoctorMenuDaoImpl();

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
        DoctorMenu doctorMenu = findById(id); 
        return dao.delete(doctorMenu); 
    } 


    @Override 
    public List findAll() { 
        return dao.findAll(); 
    } 


    @Override 
    public DoctorMenu findById(Object id) { 
        List<DoctorMenu> list = dao.findById(id); 
        return  list.get(0); 
    } 


    @Override 
    public boolean freeUpdate(String sql) {
        return dao.freeUpdate(sql);
    }


    @Override 
    public List<DoctorMenu> search(DoctorMenu doctorMenu) {
            String sql = "select * from doctor_menu where 1=1 "; 
            if (doctorMenu.getMenuName()!=null && !"".equals(doctorMenu.getMenuName())){        
                sql+=" and menu_name like '%"+doctorMenu.getMenuName()+"%' ";        
            } 
            if (doctorMenu.getUserId()!=null && !"".equals(doctorMenu.getUserId())){        
                sql+=" and user_id like '%"+doctorMenu.getUserId()+"%' ";        
            } 
            if (doctorMenu.getDescription()!=null && !"".equals(doctorMenu.getDescription())){        
                sql+=" and description like '%"+doctorMenu.getDescription()+"%' ";        
            } 
            if (doctorMenu.getType()!=null && !"".equals(doctorMenu.getType())){        
                sql+=" and type like '%"+doctorMenu.getType()+"%' ";        
            } 
            List<DoctorMenu> list = dao.freeFind(sql);        
            return list;        
    }

    @Override
    public List<DoctorMenu> findTwoAll() {
        String sql="SELECT * from doctor_menu dm JOIN base_user bu ON dm.user_id=bu.user_id;";

        return dao.freeFind(sql);
    }

    @Override
    public boolean findMenuName(String menuName) {
        String sql="SELECT * from doctor_menu WHERE menu_name='"+menuName+"'";
        List list = dao.freeFind(sql);
        if(list!=null&&list.size()>0){
            return true;
        }

        return false;
    }

    @Override
    public List<DoctorMenu> findType() {
        String sql="SELECT * from doctor_menu dm WHERE dm.type IN (1,2)";
        List<DoctorMenu> list = dao.freeFind(sql);
        return list;
    }

    @Override
    public void distributeUpdate(Integer menuId, String[] medicineInfos) {
        //        根据menuId 删除中间表的记录
        List<DoctorMenuMedicine> doctorMenuMedicineList = dao.findByMenuId(menuId);
        for (DoctorMenuMedicine doctorMenuMedicine : doctorMenuMedicineList) {
            String s = JSON.toJSONString(doctorMenuMedicine);
            System.out.println(s);
            dao.delete(doctorMenuMedicine.getMdId());
        }
//        往中间表添加记录
        if (medicineInfos != null) {
            DoctorMenuMedicine doctorMenuMedicine = new DoctorMenuMedicine();
            doctorMenuMedicine.setMenuId(menuId);
            for (String medicineInfo : medicineInfos) {
                String[] arr = medicineInfo.split(" ");
                doctorMenuMedicine.setCodeId(Integer.parseInt(arr[0]));
                doctorMenuMedicine.setAmt(Integer.parseInt(arr[1]));
                dao.insert(doctorMenuMedicine);
            }
        }
    }

}