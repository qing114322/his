package com.qhit.companyinfo.service;

import java.util.List;
import com.qhit.companyinfo.pojo.Companyinfo;
/**
* Created by GeneratorCode on 2018/12/24
*/

public interface ICompanyinfoService {

    boolean insert(Object object);

    boolean  update(Object object);

    boolean  updateSelective(Object object);

    boolean delete(Object id);

    List findAll();

    Companyinfo findById(Object id);



}