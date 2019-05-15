package com.qhit.baseFunction.service.impl;

import com.qhit.baseFunction.service.IBaseFunctionService;
import java.util.List;
import com.qhit.baseFunction.dao.IBaseFunctionDao;
import com.qhit.baseFunction.dao.impl.BaseFunctionDaoImpl;
import com.qhit.baseFunction.pojo.BaseFunction;

/**
* Created by GeneratorCode on 2018/11/27
*/

public class BaseFunctionServiceImpl  implements IBaseFunctionService {

    IBaseFunctionDao dao = new BaseFunctionDaoImpl();

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
        BaseFunction baseFunction = findById(id); 
        return dao.delete(baseFunction); 
    } 


    @Override 
    public List findAll() {
        String sql="SELECT * from base_function f JOIN base_module m ON f.mid=m.mid";
        return dao.freeFind(sql);
    } 


    @Override 
    public BaseFunction findById(Object id) { 
        List<BaseFunction> list = dao.findById(id); 
        return  list.get(0); 
    }

    @Override
    public List<BaseFunction> findmid( Integer mid,Integer userId) {
        String sql = "SELECT bf.*\n" +
                "from base_function bf JOIN base_role_function brf ON bf.fid=brf.fid\n" +
                "\t\t      JOIN base_role br ON brf.rid=br.rid\n" +
                "\t\t      JOIN base_user_role bur ON br.rid=bur.rid\n" +
                "\t\t      JOIN base_user bu ON bur.uid=bu.user_id\n" +
                "\t\t      JOIN base_module bm ON bm.mid=bf.mid\n" +
                "\t\t      AND bu.user_id="+userId+"\n" +
                "\t\t      AND bm.mid="+mid;
        return dao.freeFind(sql);
    }


}