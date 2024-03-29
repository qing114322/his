package com.qhit.baseRole.service.impl;

import com.qhit.baseRole.service.IBaseRoleService;
import java.util.List;
import com.qhit.baseRole.dao.IBaseRoleDao;
import com.qhit.baseRole.dao.impl.BaseRoleDaoImpl;
import com.qhit.baseRole.pojo.BaseRole;
import com.qhit.baseRoleFunction.pojo.BaseRoleFunction;
import com.qhit.baseRoleFunction.service.IBaseRoleFunctionService;
import com.qhit.baseRoleFunction.service.impl.BaseRoleFunctionServiceImpl;
import com.qhit.baseUserRole.pojo.BaseUserRole;
import com.qhit.utils.BaseDao;

/**
* Created by GeneratorCode on 2018/11/29
*/

public class BaseRoleServiceImpl  implements IBaseRoleService {

    IBaseRoleDao dao = new BaseRoleDaoImpl();

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
        BaseRole baseRole = findById(id); 
        return dao.delete(baseRole); 
    } 


    @Override 
    public List findAll() { 
        return dao.findAll(); 
    } 


    @Override 
    public BaseRole findById(Object id) { 
        List<BaseRole> list = dao.findById(id); 
        return  list.get(0); 
    }

    @Override
    public void distributeUpdate(Integer rid, String[] fid) {
        //        删除base_role_function表中所有userId记录
        IBaseRoleFunctionService baseRoleFunctionService = new BaseRoleFunctionServiceImpl();
        String  delete = "delete from base_role_function where rid = "+rid;
        BaseDao baseDao=new BaseDao();
        baseDao.freeUpdate(delete);
//        批量插入
        for(String f:fid){
            BaseRoleFunction baseRoleFunction = new BaseRoleFunction();
            baseRoleFunction.setFid(Integer.parseInt(f));
            baseRoleFunction.setRid(rid);
            baseRoleFunctionService.insert(baseRoleFunction);
        }
    }

    @Override
    public List<BaseUserRole> finddetails(Integer rid) {
        String sql = "select * from base_function bf where bf.fid not in\n" +
                "(select brf.fid from base_role br join base_role_function brf" +
                " on br.rid = brf.rid and br.rid = '"+rid+"' )";
//        String jsonString = JSON.toJSONString(list);
//        System.out.println(jsonString);
        return dao.freeFind(sql);
    }

    @Override
    public List<BaseRole> findnodetails(Integer rid) {
        String sql = "select * from base_function bf where bf.fid  in\n" +
                "(select brf.fid from base_role br join base_role_function brf" +
                " on br.rid = brf.rid and br.rid = '"+rid+"' )";
        return dao.freeFind(sql);
    }


}