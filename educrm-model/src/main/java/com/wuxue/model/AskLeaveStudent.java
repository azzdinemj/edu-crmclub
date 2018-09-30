package com.wuxue.model;

import com.wuxue.base.BusinessPage;

public class AskLeaveStudent extends BusinessPage{
    private String pkStudentLeave;

    private String pkAskForLeave;

    private String pkStudent;

    private String pkLinkman;

    public String getPkStudentLeave() {
        return pkStudentLeave;
    }

    public void setPkStudentLeave(String pkStudentLeave) {
        this.pkStudentLeave = pkStudentLeave;
    }

    public String getPkAskForLeave() {
        return pkAskForLeave;
    }

    public void setPkAskForLeave(String pkAskForLeave) {
        this.pkAskForLeave = pkAskForLeave;
    }

    public String getPkStudent() {
        return pkStudent;
    }

    public void setPkStudent(String pkStudent) {
        this.pkStudent = pkStudent;
    }

    public String getPkLinkman() {
        return pkLinkman;
    }

    public void setPkLinkman(String pkLinkman) {
        this.pkLinkman = pkLinkman;
    }
}