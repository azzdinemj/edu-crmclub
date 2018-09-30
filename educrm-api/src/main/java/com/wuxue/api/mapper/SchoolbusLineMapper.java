package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.shuttle.SchoolbusLine;

import java.util.List;

public interface SchoolbusLineMapper extends IInsertMapper<SchoolbusLine>,ICountMapper<SchoolbusLine,Integer>,
        IUpdateMapper<SchoolbusLine>,IDeleteByPrimaryKeyMapper<String>,
        ISelectByPrimaryKeyMapper<SchoolbusLine,SchoolbusLine>,ISelectMapper<SchoolbusLine,List<SchoolbusLine>>{
    /**
     * 根据校车id方向查询行车路线
     * @param schoolbusLine#schoolBusId 校车id
     * @param schoolbusLine#direction 方向 1：上学路线。2，放学路线
     * @return 站点集合
     */
    List<SchoolbusLine> findBusLineByBusIdAndDirection(SchoolbusLine schoolbusLine);

    int updateStationid(SchoolbusLine schoolbusLine);

    int updateAddStationid(SchoolbusLine schoolbusLine);

    List<SchoolbusLine> findByBusId(SchoolbusLine schoolbusLine);

    SchoolbusLine selectById(Long data);

    void updateIndexnoSelective(SchoolbusLine data);

    SchoolbusLine selectByType(SchoolbusLine se);

    int updateByBusId(SchoolbusLine schoolbusLine);

    int deleteByBusId(SchoolbusLine schoolbusLine1);
}