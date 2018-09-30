package com.wuxue.vo;

import lombok.Data;

@Data
public class SchoolBusLineVO {
    private Long indexno;

    private String schoolbusId;

    private Byte direction;//1=校外开往校内，2=校内开往校外

    private Byte stationid;

    private String stationname;

    private Integer distance;

    private Byte stationtype;

    private String shortCode;

    private Boolean sendMessage ;

}
