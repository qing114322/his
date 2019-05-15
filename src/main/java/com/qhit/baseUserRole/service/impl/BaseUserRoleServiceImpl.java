package com.qhit.baseUserRole.service.impl;

import com.qhit.baseUserRole.service.IBaseUserRoleService;
import java.util.List;
import com.qhit.baseUserRole.dao.IBaseUserRoleDao;
import com.qhit.baseUserRole.dao.impl.BaseUserRoleDaoImpl;
import com.qhit.baseUserRole.pojo.BaseUserRole;
import org.springframework.stereotype.Service;

/**
* Created by GeneratorCode on 2018/11/29
*/
@Service
public class BaseUserRoleServiceImpl  implements IBaseUserRoleService {

    IBaseUserRoleDao baseUserRoleDao;

    @Override 
    public boolean insert(Object object) { 
        return baseUserRoleDao.insert(object);
    } 


    @Override 
    public boolean update(Object object) { 
        return baseUserRoleDao.update(object);
    } 


    @Override 
    public boolean updateSelective(Object object) { 
        return baseUserRoleDao.updateSelective(object);
    } 


    @Override 
    public boolean delete(Object id) { 
        BaseUserRole baseUserRole = findById(id); 
        return baseUserRoleDao.delete(baseUserRole);
    } 


    @Override 
    public List findAll() { 
        return baseUserRoleDao.findAll();
    } 


    @Override 
    public BaseUserRole findById(Object id) { 
        List<BaseUserRole> list = baseUserRoleDao.findById(id);
        return  list.get(0); 
    }

    @Override
    public List<BaseUserRole> finddetails(Integer userId) {
        String sql="SELECT  * from base_user_role ro " +
                "JOIN base_user us ON ro.uid=us.user_id\n" +
                "JOIN base_role le ON ro.rid=le.rid WHERE uid =1";
        List<BaseUserRole> list = baseUserRoleDao.freeFind(sql);
        return list;
    }

    @Override
    public List<BaseUserRole> findrid(Integer rid) {
        String sql="UPDATE  base_user_role SET uid = NULL WHERE rid ='"+rid+"' ";
        List<BaseUserRole> list = baseUserRoleDao.freeFind(sql);
        return list;
    }


}