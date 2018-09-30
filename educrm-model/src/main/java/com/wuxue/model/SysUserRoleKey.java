package com.wuxue.model;

import com.wuxue.base.BaiscPage;

public class SysUserRoleKey extends BaiscPage {
    private String pkSysUser;

    private String pkSysRole;

    public String getPkSysUser() {
        return pkSysUser;
    }

    public void setPkSysUser(String pkSysUser) {
        this.pkSysUser = pkSysUser;
    }

    public String getPkSysRole() {
        return pkSysRole;
    }

    public void setPkSysRole(String pkSysRole) {
        this.pkSysRole = pkSysRole;
    }
}