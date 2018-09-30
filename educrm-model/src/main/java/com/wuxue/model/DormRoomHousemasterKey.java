package com.wuxue.model;

import com.wuxue.base.BusinessPage;

public class DormRoomHousemasterKey extends BusinessPage{
    private String employeeId;

    private String dormRoomId;

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getDormRoomId() {
        return dormRoomId;
    }

    public void setDormRoomId(String dormRoomId) {
        this.dormRoomId = dormRoomId;
    }
}