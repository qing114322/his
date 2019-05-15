package com.qhit.baseUserRole.pojo;


import com.qhit.baseRole.pojo.BaseRole;
import com.qhit.baseUser.pojo.BaseUser;
import com.sun.org.glassfish.gmbal.Description;

/**
* Created by GeneratorCode on 2018/11/29
*/

public class BaseUserRole {

    private Integer urid;    //主键
    private Integer uid;    //用户id
    private Integer rid;    //角色id
    @Description("bean")
    private BaseUser baseUser;
    @Description("bean")
    private BaseRole baseRole;

    public BaseUser getBaseUser() {
        return baseUser;
    }

    public void setBaseUser(BaseUser baseUser) {
        this.baseUser = baseUser;
    }

    public BaseRole getBaseRole() {
        return baseRole;
    }

    public void setBaseRole(BaseRole baseRole) {
        this.baseRole = baseRole;
    }

    public Integer getUrid() {
        return urid;
    }
 
    public void setUrid(Integer urid) { 
        this.urid = urid;
    }
 
    public Integer getUid() { 
        return uid;
    }
 
    public void setUid(Integer uid) { 
        this.uid = uid;
    }
 
    public Integer getRid() { 
        return rid;
    }
 
    public void setRid(Integer rid) { 
        this.rid = rid;
    }
 

 }