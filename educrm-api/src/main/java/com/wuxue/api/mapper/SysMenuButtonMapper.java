package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.ClassinfoActivityDetails;
import com.wuxue.model.SysMenuButton;
import com.wuxue.model.SysMenuButtonKey;

import java.util.List;

public interface SysMenuButtonMapper extends IInsertMapper<SysMenuButton>,ICountMapper<SysMenuButton,Integer>,
        IUpdateMapper<SysMenuButtonKey>,IDeleteByPrimaryKeyMapper<SysMenuButtonKey>,
        ISelectByPrimaryKeyMapper<SysMenuButtonKey,SysMenuButton>,ISelectMapper<SysMenuButton,List<SysMenuButton>> {
}