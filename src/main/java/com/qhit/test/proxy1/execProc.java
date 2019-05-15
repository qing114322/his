package com.qhit.test.proxy1;

import com.qhit.utils.BaseDao;

/**
 * Created by tp on 2018/12/11.
 */
public class execProc {
    public static void main(String[] args) {
        BaseDao baseDao=new BaseDao();
        baseDao.execProc("collect");
    }
}
