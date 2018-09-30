package com.wuxue.model;

import com.wuxue.base.Page;

public class EnrolmentPerformanceReport extends Page{
    private String pkSysUser;

    private String caption;

    private Long studentnum;

    private Long membernum;

    public String getPkSysUser() {
        return pkSysUser;
    }

    public void setPkSysUser(String pkSysUser) {
        this.pkSysUser = pkSysUser;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public Long getStudentnum() {
        return studentnum;
    }

    public void setStudentnum(Long studentnum) {
        this.studentnum = studentnum;
    }

    public Long getMembernum() {
        return membernum;
    }

    public void setMembernum(Long membernum) {
        this.membernum = membernum;
    }
}