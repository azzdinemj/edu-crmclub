package com.wuxue.model;

import com.wuxue.base.BaiscPage;

public class SysRoleDepartmentKey  extends BaiscPage{
    private String pkSysRole;

    private String pkDepartment;

    public String getPkSysRole() {
        return pkSysRole;
    }

    public void setPkSysRole(String pkSysRole) {
        this.pkSysRole = pkSysRole;
    }

    public String getPkDepartment() {
        return pkDepartment;
    }

    public void setPkDepartment(String pkDepartment) {
        this.pkDepartment = pkDepartment;
    }
}