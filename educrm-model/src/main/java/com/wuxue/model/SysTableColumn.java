package com.wuxue.model;

import com.wuxue.base.BaiscPage;

import java.util.Date;

public class SysTableColumn  extends BaiscPage{
    private String pkSysTableColumn;

    private String code;

    private String caption;

    public String getPkSysTableColumn() {
        return pkSysTableColumn;
    }

    public void setPkSysTableColumn(String pkSysTableColumn) {
        this.pkSysTableColumn = pkSysTableColumn;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }
}