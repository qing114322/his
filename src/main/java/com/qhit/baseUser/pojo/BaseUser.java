package com.qhit.baseUser.pojo;


import com.qhit.baseDept.pojo.BaseDept;
import com.qhit.baseFunction.pojo.BaseFunction;
import com.qhit.baseModule.pojo.BaseModule;
import com.qhit.baseRole.pojo.BaseRole;
import com.qhit.baseRoleFunction.pojo.BaseRoleFunction;
import com.qhit.baseUserRole.pojo.BaseUserRole;
import com.sun.org.glassfish.gmbal.Description;

import java.util.List;

/**
* Created by GeneratorCode on 2018/11/26
*/

public class BaseUser {

    private Integer userId;    //主键
    private String userName;    //用户名
    private String password;    //密码
    private String cname;    //中文名字
    private String sex;    //性别
    private Integer deptId;    //部门ID
    private Integer postId;    //岗位ID
    @Description("list")
    private List<BaseRole>baseRoleList;
    @Description("list")
    private List<BaseFunction>baseFunctionList;
    @Description("bean")
    private BaseDept baseDept;

    public BaseDept getBaseDept() {
        return baseDept;
    }

    public void setBaseDept(BaseDept baseDept) {
        this.baseDept = baseDept;
    }

    public List<BaseRole> getBaseRoleList() {
        return baseRoleList;
    }

    public void setBaseRoleList(List<BaseRole> baseRoleList) {
        this.baseRoleList = baseRoleList;
    }

    public List<BaseFunction> getBaseFunctionList() {
        return baseFunctionList;
    }

    public void setBaseFunctionList(List<BaseFunction> baseFunctionList) {
        this.baseFunctionList = baseFunctionList;
    }

    @Description("bean")
    private BaseUser baseUser;


    @Description("bean")
    private BaseRole baseRole;
    @Description("bean")
    private BaseUserRole baseUserRole;
    @Description("bean")
    private BaseRoleFunction baseRoleFunction;
    @Description("bean")
    private BaseFunction baseFunction;
    @Description("bean")
    private BaseModule baseModule;

    public BaseRoleFunction getBaseRoleFunction() {
        return baseRoleFunction;
    }

    public void setBaseRoleFunction(BaseRoleFunction baseRoleFunction) {
        this.baseRoleFunction = baseRoleFunction;
    }

    public BaseFunction getBaseFunction() {
        return baseFunction;
    }

    public void setBaseFunction(BaseFunction baseFunction) {
        this.baseFunction = baseFunction;
    }

    public BaseModule getBaseModule() {
        return baseModule;
    }

    public void setBaseModule(BaseModule baseModule) {
        this.baseModule = baseModule;
    }

    public BaseUserRole getBaseUserRole() {
        return baseUserRole;
    }

    public void setBaseUserRole(BaseUserRole baseUserRole) {
        this.baseUserRole = baseUserRole;
    }

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

    public Integer getUserId() {
        return userId;
    }
 
    public void setUserId(Integer userId) { 
        this.userId = userId;
    }
 
    public String getUserName() { 
        return userName;
    }
 
    public void setUserName(String userName) { 
        this.userName = userName;
    }
 
    public String getPassword() { 
        return password;
    }
 
    public void setPassword(String password) { 
        this.password = password;
    }
 
    public String getCname() { 
        return cname;
    }
 
    public void setCname(String cname) { 
        this.cname = cname;
    }
 
    public String getSex() { 
        return sex;
    }
 
    public void setSex(String sex) { 
        this.sex = sex;
    }
 
    public Integer getDeptId() { 
        return deptId;
    }
 
    public void setDeptId(Integer deptId) { 
        this.deptId = deptId;
    }
 
    public Integer getPostId() { 
        return postId;
    }
 
    public void setPostId(Integer postId) { 
        this.postId = postId;
    }

    @Override
    public String toString() {
        return "BaseUser{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", cname='" + cname + '\'' +
                ", sex='" + sex + '\'' +
                ", deptId=" + deptId +
                ", postId=" + postId +
                ", baseRoleList=" + baseRoleList +
                ", baseFunctionList=" + baseFunctionList +
                ", baseUser=" + baseUser +
                ", baseRole=" + baseRole +
                ", baseUserRole=" + baseUserRole +
                '}';
    }
}