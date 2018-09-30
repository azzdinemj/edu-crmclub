package com.wuxue.model.shuttle;

public class SchoolbusLine extends SchoolbusLineKey {
    private Long indexno;

    private Byte stationtype;

    private String shortCode;

    private String lng;

    private String lat;

    private Integer distance;

    private Integer isvalid;

    //********
    private String pkNoticeRecord;//通知记录主键

    private Boolean sendMessage =false;//是否发送通知 true:已发送

    public String getPkNoticeRecord() {
        return pkNoticeRecord;
    }

    public void setPkNoticeRecord(String pkNoticeRecord) {
        this.pkNoticeRecord = pkNoticeRecord;
    }

    public Boolean getSendMessage() {
        return sendMessage;
    }

    public void setSendMessage(Boolean sendMessage) {
        this.sendMessage = sendMessage;
    }

    public Long getIndexno() {
        return indexno;
    }

    public void setIndexno(Long indexno) {
        this.indexno = indexno;
    }

    public Byte getStationtype() {
        return stationtype;
    }

    public void setStationtype(Byte stationtype) {
        this.stationtype = stationtype;
    }

    public String getShortCode() {
        return shortCode;
    }

    public void setShortCode(String shortCode) {
        this.shortCode = shortCode;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public Integer getIsvalid() {
        return isvalid;
    }

    public void setIsvalid(Integer isvalid) {
        this.isvalid = isvalid;
    }
}