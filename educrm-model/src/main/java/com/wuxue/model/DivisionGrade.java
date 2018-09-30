package com.wuxue.model;

import com.wuxue.base.BusinessPage;

import java.util.Date;

public class DivisionGrade extends BusinessPage{
    private Integer pkDivisionGrade;

    private String divisionId;

    private String gradeName;

    private Integer sort;

    private String divisionName;

    public Integer getPkDivisionGrade() {
        return pkDivisionGrade;
    }

    public void setPkDivisionGrade(Integer pkDivisionGrade) {
        this.pkDivisionGrade = pkDivisionGrade;
    }

    public String getDivisionId() {
        return divisionId;
    }

    public void setDivisionId(String divisionId) {
        this.divisionId = divisionId;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getDivisionName() {
        return divisionName;
    }

    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }
}