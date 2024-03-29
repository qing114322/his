package com.qhit.netstorage.dao;

import com.qhit.utils.BaseDao;
import java.util.List;

/**
* Created by GeneratorCode on 2018/12/21
*/

public interface INetstorageDao {

    boolean insert(Object object);

    boolean  update(Object object);

    boolean  updateSelective(Object object);

    boolean delete(Object object);

    List freeFind(String sql);

    List findAll();

    List findById(Object id);

    boolean freeUpdate(String sql);

    List findByFileaname(Object fileaname);

    List findByFilesize(Object filesize);

    List findByUploaddate(Object uploaddate);

    List findByUid(Object uid);

}