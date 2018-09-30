package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.ClassinfoActivityDetails;
import com.wuxue.model.SysMenu;
import com.wuxue.model.SysUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysMenuMapper extends IInsertMapper<SysMenu>,ICountMapper<SysMenu,Integer>,
        IUpdateMapper<SysMenu>,IDeleteByPrimaryKeyMapper<String>,
        ISelectByPrimaryKeyMapper<String,SysMenu>,ISelectMapper<SysMenu,List<SysMenu>> {

    /**
     *父主键不为空的数据   、数量
     * @param sysMenu
     * @return
     */
    List<SysMenu> selectparentisnotNull(SysMenu sysMenu);

    Integer countByparentisnotNull(SysMenu sysMenu);

    /**
     *父主键为空的数据   、数量
     * @param sysMenu
     * @return
     */
    List<SysMenu> selectparentisNull(SysMenu sysMenu);

    Integer countByparentisNull(SysMenu sysMenu);

    List<SysMenu> selectByUser(SysUser sysUser);
    SysMenu selectByUrl(String url);
}