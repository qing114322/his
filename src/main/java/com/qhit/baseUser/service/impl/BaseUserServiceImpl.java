package com.qhit.baseUser.service.impl;

import com.alibaba.fastjson.JSON;
import com.qhit.baseUser.service.IBaseUserService;
import java.util.List;
import com.qhit.baseUser.dao.IBaseUserDao;
import com.qhit.baseUser.dao.impl.BaseUserDaoImpl;
import com.qhit.baseUser.pojo.BaseUser;
import com.qhit.baseUserRole.pojo.BaseUserRole;
import com.qhit.utils.MD5;

import static org.apache.ibatis.ognl.DynamicSubscript.mid;

/**
* Created by GeneratorCode on 2018/11/26
*/

public class BaseUserServiceImpl  implements IBaseUserService {

    IBaseUserDao dao = new BaseUserDaoImpl();

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
        BaseUser baseUser = findById(id); 
        return dao.delete(baseUser);
    } 


    @Override 
    public List findAll() { 
        return dao.findAll();
    } 


    @Override 
    public BaseUser findById(Object id) { 
        List<BaseUser> list = dao.findById(id);
        return  list.get(0); 
    }

    @Override
    public BaseUser  login(BaseUser baseUser) {
        MD5 md5=new MD5();
        String password = md5.getMD5ofStr(baseUser.getPassword());
        String sql="SELECT * \n" +
                "from base_user bu \n" +
                "LEFT JOIN base_user_role bur ON bur.uid=bu.user_id\n" +
                "LEFT JOIN base_role br ON br.rid=bur.rid\n" +
                "LEFT JOIN base_role_function brf  ON brf.rid=br.rid\n" +
                "LEFT JOIN base_function bf ON bf.fid=brf.fid\n" +
                "LEFT JOIN base_module bm ON bm.mid=bf.mid WHERE bu.user_name = '"+baseUser.getUserName()+"' AND bu.PASSWORD ='"+password+"'";
        List<BaseUser> list = dao.freeFind(sql);
        if(list!=null&&list.size()>0){
            return list.get(0);
        }
        return null;
    }

    @Override
    public boolean finPassword(BaseUser baseUser) {
        MD5 md5=new MD5();
        String password = md5.getMD5ofStr(baseUser.getPassword());
        String sql="SELECT * from base_user WHERE user_id = '"+baseUser.getUserId()+"' AND PASSWORD ='"+password+"' ";
        List<BaseUser> list = dao.freeFind(sql);
        if(list!=null&&list.size()>0){
            return true;
        }
        return false;
    }

    @Override
    public List<BaseUserRole> finddetails(Integer userId) {
        System.out.println(userId);
        String sql="SELECT * from base_role  WHERE rid  IN(\n" +
                "SELECT ro.rid from base_user us \n" +
                "INNER JOIN base_user_role ro  \n" +
                "ON us.user_id=ro.uid \n" +
                "WHERE us.user_id='"+userId+"')";
        List<BaseUserRole> list = dao.freeFind(sql);
//        String jsonString = JSON.toJSONString(list);
//        System.out.println(jsonString);
        return list;
    }

    @Override
    public List<BaseUserRole> findnodetails(Integer userId) {
        System.out.println(userId);
        String sql="SELECT * from base_role  WHERE rid not IN(\n" +
                "SELECT ro.rid from base_user us \n" +
                "INNER JOIN base_user_role ro  \n" +
                "ON us.user_id=ro.uid \n" +
                "WHERE us.user_id='"+userId+"')";
        List<BaseUserRole> list = dao.freeFind(sql);
        return list;
    }

    @Override
    public List<BaseUser> findByuid(Integer appUserid) {
        String sql="SELECT * from base_user  WHERE user_id='"+appUserid+"'";
        List<BaseUser> list = dao.freeFind(sql);
        return list;
    }

    @Override
    public List<BaseUser> findotwoAll() {
        String sql="SELECT * from base_user bu LEFT JOIN base_dept bd ON bu.dept_id=bd.dept_id;";
        List<BaseUser> list = dao.freeFind(sql);
        return list;
    }

    @Override
    public List<BaseUser> finddeptId(String deptId) {
        String sql=" SELECT * \n" +
                " from base_user bu \n" +
                " JOIN base_user_role bur ON bur.uid=bu.user_id \n" +
                " JOIN base_role br ON br.rid=bur.rid \n" +
                "WHERE bu.dept_id='"+deptId+"' " +
                " GROUP BY bu.dept_id \n";
        List<BaseUser> list = dao.freeFind(sql);
        return list;
    }


}