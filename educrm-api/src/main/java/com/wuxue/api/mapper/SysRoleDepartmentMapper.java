package com.wuxue.api.mapper;
import com.wuxue.api.interfaces.*;
import com.wuxue.model.SysRoleDepartment;
import com.wuxue.model.SysRoleDepartmentKey;
import java.util.List;

public interface SysRoleDepartmentMapper extends IInsertMapper<SysRoleDepartment>,ICountMapper<SysRoleDepartment,Integer>,
        IUpdateMapper<SysRoleDepartmentKey>,IDeleteByPrimaryKeyMapper<SysRoleDepartmentKey>,
        ISelectByPrimaryKeyMapper<SysRoleDepartmentKey,SysRoleDepartment>,ISelectMapper<SysRoleDepartment,List<SysRoleDepartment>> {
}
