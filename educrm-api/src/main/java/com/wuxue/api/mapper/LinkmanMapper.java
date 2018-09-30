package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.Linkman;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LinkmanMapper extends IInsertMapper<Linkman>,ICountMapper<Linkman,Integer>,
        IUpdateMapper<Linkman>,IDeleteByPrimaryKeyMapper<String>,
        ISelectByPrimaryKeyMapper<String,Linkman>,ISelectMapper<Linkman,List<Linkman>> {

        Linkman selectByPhone(String phone);
        List<Linkman> selectByStudent(String pkStudent);

    List<Linkman> selectByPhoneAndIdCard(Linkman linkman);

    /**
     * 根据id集合查询家长信息
     * @param ids
     * @return
     */
    List<Linkman> selectLinkMansByIds(@Param("ids") List<String> ids);

    int insertSelectiveCollect(Linkman linkman);
}