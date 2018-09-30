package com.wuxue.model;

import com.wuxue.base.BaiscPage;
import com.wuxue.base.BusinessPage;
import com.wuxue.base.Page;

import java.util.Date;

public class TeacherCourseReport extends BaiscPage {
    private String pkEmployee;

    private Long num;

    private Long overNum;

    private Long residueNum;

    private Long feedbackNum;

    private String caption;

    private Date date;

    public String getPkEmployee() {
        return pkEmployee;
    }

    public void setPkEmployee(String pkEmployee) {
        this.pkEmployee = pkEmployee;
    }

    public Long getNum() {
        return num;
    }

    public void setNum(Long num) {
        this.num = num;
    }

    public Long getOverNum() {
        return overNum;
    }

    public void setOverNum(Long overNum) {
        this.overNum = overNum;
    }

    public Long getResidueNum() {
        return residueNum;
    }

    public void setResidueNum(Long residueNum) {
        this.residueNum = residueNum;
    }

    public Long getFeedbackNum() {
        return feedbackNum;
    }

    public void setFeedbackNum(Long feedbackNum) {
        this.feedbackNum = feedbackNum;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}