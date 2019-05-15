package com.qhit.netstorage.service.impl;

import com.qhit.netstorage.service.INetstorageService;
import java.util.List;
import com.qhit.netstorage.dao.INetstorageDao;
import com.qhit.netstorage.dao.impl.NetstorageDaoImpl;
import com.qhit.netstorage.pojo.Netstorage;

/**
* Created by GeneratorCode on 2018/12/21
*/

public class NetstorageServiceImpl  implements INetstorageService {

    INetstorageDao dao = new NetstorageDaoImpl();

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
        Netstorage netstorage = findById(id); 
        return dao.delete(netstorage); 
    } 


    @Override 
    public List findAll() { 
        return dao.findAll(); 
    } 


    @Override 
    public Netstorage findById(Object id) { 
        List<Netstorage> list = dao.findById(id); 
        return  list.get(0); 
    } 


    @Override 
    public boolean freeUpdate(String sql) {
        return dao.freeUpdate(sql);
    }


    @Override 
    public List<Netstorage> search(Netstorage netstorage) {
            String sql = "select * from netstorage where 1=1 "; 
            if (netstorage.getFileaname()!=null && !"".equals(netstorage.getFileaname())){        
                sql+=" and fileaName like '%"+netstorage.getFileaname()+"%' ";        
            } 
            if (netstorage.getFilesize()!=null && !"".equals(netstorage.getFilesize())){        
                sql+=" and fileSize like '%"+netstorage.getFilesize()+"%' ";        
            } 
            if (netstorage.getUploaddate()!=null && !"".equals(netstorage.getUploaddate())){        
                sql+=" and uploadDate like '%"+netstorage.getUploaddate()+"%' ";        
            } 
            if (netstorage.getUid()!=null && !"".equals(netstorage.getUid())){        
                sql+=" and uid like '%"+netstorage.getUid()+"%' ";        
            } 
            List<Netstorage> list = dao.freeFind(sql);        
            return list;        
    }


}