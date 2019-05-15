package com.qhit.baseRole.service;

import java.util.List;
import com.qhit.baseRole.pojo.BaseRole;
import com.qhit.baseUserRole.pojo.BaseUserRole;

/**
* Created by GeneratorCode on 2018/11/29
*/

public interface IBaseRoleService {

    boolean insert(Object object);

    boolean  update(Object object);

    boolean  updateSelective(Object object);

    boolean delete(Object id);

    List findAll();

    BaseRole findById(Object id);

    void distributeUpdate(Integer rid, String[] fid);

    List<BaseUserRole> finddetails(Integer rid);

    List<BaseRole> findnodetails(Integer rid);
}