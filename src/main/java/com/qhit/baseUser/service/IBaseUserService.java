package com.qhit.baseUser.service;

import java.util.List;
import com.qhit.baseUser.pojo.BaseUser;
import com.qhit.baseUserRole.pojo.BaseUserRole;

/**
* Created by GeneratorCode on 2018/11/26
*/

public interface IBaseUserService {

    boolean insert(Object object);

    boolean  update(Object object);

    boolean  updateSelective(Object object);

    boolean delete(Object id);

    List findAll();

    BaseUser findById(Object id);

    BaseUser login(BaseUser baseUser);

    boolean finPassword(BaseUser baseUser);

    List<BaseUserRole> finddetails(Integer userId);

    List<BaseUserRole> findnodetails(Integer userId);

    List<BaseUser> findByuid(Integer appUserid);

    List<BaseUser> findotwoAll();

    List<BaseUser> finddeptId(String deptId);
}