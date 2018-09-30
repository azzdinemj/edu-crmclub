package com.wuxue.model.shuttle;

import com.wuxue.base.BusinessPage;

import java.util.Date;

public class TurnRecorder extends BusinessPage {
    private String pkTurnRecorder;

    private String pkDomain;

    private String code;

    private String employeeId;

    private Integer trafficType;

    private Date trafficDate;

    private String trafficDirection;

    public String getPkTurnRecorder() {
        return pkTurnRecorder;
    }

    public void setPkTurnRecorder(String pkTurnRecorder) {
        this.pkTurnRecorder = pkTurnRecorder;
    }

    public String getPkDomain() {
        return pkDomain;
    }

    public void setPkDomain(String pkDomain) {
        this.pkDomain = pkDomain;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getTrafficType() {
        return trafficType;
    }

    public void setTrafficType(Integer trafficType) {
        this.trafficType = trafficType;
    }

    public Date getTrafficDate() {
        return trafficDate;
    }

    public void setTrafficDate(Date trafficDate) {
        this.trafficDate = trafficDate;
    }

    public String getTrafficDirection() {
        return trafficDirection;
    }

    public void setTrafficDirection(String trafficDirection) {
        this.trafficDirection = trafficDirection;
    }
}