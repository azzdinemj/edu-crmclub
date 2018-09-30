package com.wuxue.model;

import java.util.Date;

public class SysUser extends SysUserRoleKey{
    private String pkSysUser;

    private String pkDomain;

    private String caption;

    private String shortCode;

    private String password;

    private String pkDepartment;

    private Integer sex;

    private Integer kind;

    private Integer isvalid;

    private String memo;

    private String userType;

    private String roleName;

    public String getPkSysUser() {
        return pkSysUser;
    }

    public void setPkSysUser(String pkSysUser) {
        this.pkSysUser = pkSysUser;
    }

    public String getPkDomain() {
        return pkDomain;
    }

    public void setPkDomain(String pkDomain) {
        this.pkDomain = pkDomain;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getShortCode() {
        return shortCode;
    }

    public void setShortCode(String shortCode) {
        this.shortCode = shortCode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPkDepartment() {
        return pkDepartment;
    }

    public void setPkDepartment(String pkDepartment) {
        this.pkDepartment = pkDepartment;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getKind() {
        return kind;
    }

    public void setKind(Integer kind) {
        this.kind = kind;
    }

    public Integer getIsvalid() {
        return isvalid;
    }

    public void setIsvalid(Integer isvalid) {
        this.isvalid = isvalid;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}