package com.wuxue.model;

import com.wuxue.base.BaiscPage;

public class SysSetKey extends BaiscPage {
    private String pkSysSet;

    private String pkDomain;

    public String getPkSysSet() {
        return pkSysSet;
    }

    public void setPkSysSet(String pkSysSet) {
        this.pkSysSet = pkSysSet;
    }

    public String getPkDomain() {
        return pkDomain;
    }

    public void setPkDomain(String pkDomain) {
        this.pkDomain = pkDomain;
    }
}