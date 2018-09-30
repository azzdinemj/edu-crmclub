package com.wuxue.model;

import java.util.Date;

import com.wuxue.base.BaiscPage;

public class SysLog extends BaiscPage {
    private String pkSysLog;

    private String request;

    private String response;

    private String oldField;

    private String oldFieldValue;

    private String newField;

    private String newFieldValue;

    private Integer kind;

    private String operator;

    private Date datetime;

    private String tableName;
    private String pkId;
    public String getPkSysLog() {
        return pkSysLog;
    }

    public void setPkSysLog(String pkSysLog) {
        this.pkSysLog = pkSysLog;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getOldField() {
        return oldField;
    }

    public void setOldField(String oldField) {
        this.oldField = oldField;
    }

    public String getOldFieldValue() {
        return oldFieldValue;
    }

    public void setOldFieldValue(String oldFieldValue) {
        this.oldFieldValue = oldFieldValue;
    }

    public String getNewField() {
        return newField;
    }

    public void setNewField(String newField) {
        this.newField = newField;
    }

    public String getNewFieldValue() {
        return newFieldValue;
    }

    public void setNewFieldValue(String newFieldValue) {
        this.newFieldValue = newFieldValue;
    }

    public Integer getKind() {
        return kind;
    }

    public void setKind(Integer kind) {
        this.kind = kind;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
    public String getpkId() {
        return pkId;
    }

    public void setpkId(String pkId) {
        this.pkId = pkId;
    }
}