package com.wuxue.model;

import com.wuxue.base.BaiscPage;

public class SysAutocodeCounter extends BaiscPage {
    private String pkSysAutocodeCounter;

    private String pkSysAutocode;

    private String date;

    private Integer num;

    public String getPkSysAutocodeCounter() {
        return pkSysAutocodeCounter;
    }

    public void setPkSysAutocodeCounter(String pkSysAutocodeCounter) {
        this.pkSysAutocodeCounter = pkSysAutocodeCounter;
    }

    public String getPkSysAutocode() {
        return pkSysAutocode;
    }

    public void setPkSysAutocode(String pkSysAutocode) {
        this.pkSysAutocode = pkSysAutocode;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}