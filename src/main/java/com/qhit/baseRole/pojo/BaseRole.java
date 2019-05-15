package com.qhit.baseRole.pojo;


/**
* Created by GeneratorCode on 2018/11/29
*/

public class BaseRole {


    private Integer rid;    //角色ID
    private String rname;    //角色名称

    public Integer getRid() { 
        return rid;
    }
 
    public void setRid(Integer rid) { 
        this.rid = rid;
    }
 
    public String getRname() { 
        return rname;
    }
 
    public void setRname(String rname) { 
        this.rname = rname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BaseRole)) return false;

        BaseRole baseRole = (BaseRole) o;

        if (getRid() != null ? !getRid().equals(baseRole.getRid()) : baseRole.getRid() != null) return false;
        return getRname() != null ? getRname().equals(baseRole.getRname()) : baseRole.getRname() == null;
    }

    @Override
    public int hashCode() {
        int result = getRid() != null ? getRid().hashCode() : 0;
        result = 31 * result + (getRname() != null ? getRname().hashCode() : 0);
        return result;
    }
}