package com.wuxue.model;

import com.wuxue.base.BaiscPage;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class JhQuestion extends BaiscPage {
    private String pkQuestion;

    private String pkExamination;

    private String caption;

    private String creator;

    private Date creationDate;

    private String modifier;

    private Date lasteditDate;

    private Integer status;

    private Integer isdel;

    private Integer sort;

    private String datas;

    private Integer types;

    private String pkSysUser;

    private String others;

    private String name;

    private List<JhOption> jhOptionList;

    private List<JhQuestion> otherList;

    public Integer getOther() {
        return other;
    }

    public void setOther(Integer other) {
        this.other = other;
    }

    private Integer other;

    public String getPkQuestion() {
        return pkQuestion;
    }

    public void setPkQuestion(String pkQuestion) {
        this.pkQuestion = pkQuestion;
    }

    public String getPkExamination() {
        return pkExamination;
    }

    public void setPkExamination(String pkExamination) {
        this.pkExamination = pkExamination;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public Date getLasteditDate() {
        return lasteditDate;
    }

    public void setLasteditDate(Date lasteditDate) {
        this.lasteditDate = lasteditDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIsdel() {
        return isdel;
    }

    public void setIsdel(Integer isdel) {
        this.isdel = isdel;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getTypes() {
        return types;
    }

    public void setTypes(Integer types) {
        this.types = types;
    }

    public List<JhOption> getJhOptionList() {
        return jhOptionList;
    }

    public void setJhOptionList(List<JhOption> jhOptionList) {
        this.jhOptionList = jhOptionList;
    }

    public String getDatas() {
        return datas;
    }

    public void setDatas(String datas) {
        this.datas = datas;
    }

    public String getPkSysUser() {
        return pkSysUser;
    }

    public void setPkSysUser(String pkSysUser) {
        this.pkSysUser = pkSysUser;
    }

    public String getOthers() {
        return others;
    }

    public void setOthers(String others) {
        this.others = others;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<JhQuestion> getOtherList() {
        return otherList;
    }

    public void setOtherList(List<JhQuestion> otherList) {
        this.otherList = otherList;
    }

}