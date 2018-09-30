package com.wuxue.model.shuttle;

import com.wuxue.base.BusinessPage;

public class SchoolbusLineKey extends BusinessPage {
    private String schoolbusId;

    private Byte direction;//1=校外开往校内，2=校内开往校外

    private Byte stationid;

    private String stationname;

    public String getSchoolbusId() {
        return schoolbusId;
    }

    public void setSchoolbusId(String schoolbusId) {
        this.schoolbusId = schoolbusId;
    }

    public Byte getDirection() {
        return direction;
    }

    public void setDirection(Byte direction) {
        this.direction = direction;
    }

    public Byte getStationid() {
        return stationid;
    }

    public void setStationid(Byte stationid) {
        this.stationid = stationid;
    }

    public String getStationname() {
        return stationname;
    }

    public void setStationname(String stationname) {
        this.stationname = stationname;
    }
}