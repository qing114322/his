package com.qhit.baseFunction.service;

import java.util.List;
import com.qhit.baseFunction.pojo.BaseFunction;
/**
* Created by GeneratorCode on 2018/11/27
*/

public interface IBaseFunctionService {

    boolean insert(Object object);

    boolean  update(Object object);

    boolean  updateSelective(Object object);

    boolean delete(Object id);

    List findAll();

    BaseFunction findById(Object id);

    List<BaseFunction> findmid(Integer mid,Integer userId);
}