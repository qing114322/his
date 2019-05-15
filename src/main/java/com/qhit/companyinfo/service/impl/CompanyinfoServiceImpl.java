package com.qhit.companyinfo.service.impl;

import com.qhit.companyinfo.service.ICompanyinfoService;
import java.util.List;
import com.qhit.companyinfo.dao.ICompanyinfoDao;
import com.qhit.companyinfo.pojo.Companyinfo;
import org.springframework.stereotype.Service;
import javax.annotation.Resource; 

/**
* Created by GeneratorCode on 2018/12/24
*/

@Service 
public class CompanyinfoServiceImpl  implements ICompanyinfoService {

    @Resource 
    ICompanyinfoDao dao;

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
        Companyinfo companyinfo = findById(id); 
        return dao.delete(companyinfo); 
    } 


    @Override 
    public List findAll() { 
        return dao.findAll(); 
    } 


    @Override 
    public Companyinfo findById(Object id) { 
        List<Companyinfo> list = dao.findById(id); 
        return  list.get(0); 
    } 



}