package com.wuxue.api.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wuxue.api.mapper.*;
import com.wuxue.api.service.UtilsService;
import com.wuxue.model.*;
import com.wuxue.api.service.SysRoleService;
//import com.wuxue.api.utils.GuidUtils;
import com.wuxue.api.utils.GuidUtils;
import com.wuxue.utils.common.DataUtils;
import com.wuxue.utils.contract.Light;
import com.wuxue.utils.contract.LinkEntity;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("sysRoleService")
public class SysRoleServiceImpl implements SysRoleService{
    @Autowired
    SysRoleMapper sysRoleMapper;
    @Autowired
    SysPowerMapper sysPowerMapper;
    @Autowired
    SysMenuButtonMapper sysMenuButtonMapper;
    @Autowired
    SysMenuMapper sysMenuMapper;
    @Autowired
    UtilsService utilsService;
    @Autowired
    SysRoleDepartmentMapper sysRoleDepartmentMapper;
    @Autowired
    SysUserRoleMapper sysUserRoleMapper;

    @Override
    public Response delete(Request<String> tParams) {
        Response response = Response.newResponse();
        String primaryKey = tParams.getData();
        if(primaryKey== null || primaryKey.equals("")){
            return  response.PARAMS_ISNULL();
        }
        int iReuslt = 1;
        String message= "";
        try {
            SysRole sysRole = sysRoleMapper.selectByPrimaryKey(primaryKey);
            if (sysRole != null){
                sysRole.setIsvalid(0);
                iReuslt=sysRoleMapper.updateByPrimaryKeySelective(sysRole);
            }

        }catch (Exception ex){
            message = ex.getMessage();
            return response.DELETE_FAIL(message);
        }
        if(iReuslt>0) {
            return response;
        }
        return response.DELETE_FAIL(message);
    }

    @Override
    public Response find(Request<SysRole> tParams) {
        Response response = Response.newResponse();
        SysRole sysRole = tParams.getData();

        if(sysRole== null){
            return  response.PARAMS_ISNULL();
        }
        String primaryKey = sysRole.getPkSysRole();
        if(primaryKey !=null && !primaryKey.equals("")){
            SysRole role = sysRoleMapper.selectByPrimaryKey(primaryKey);

//          获取角色的菜单按钮和菜单权限
            SysPowerKey sysPowerKey = new SysPowerKey();
            sysPowerKey.setPkSysRole(primaryKey);
            List<SysPowerKey> sysPowerKeyList = sysPowerMapper.select(sysPowerKey);

            List<SysMenu> sysMenuList = new ArrayList<>();
            List<SysMenuButton> sysMenuButtonList = new ArrayList<>();
            SysRoleDepartment sysRoleDepartment=new SysRoleDepartment();
            sysRoleDepartment.setPkSysRole(primaryKey);
            //List<SysRoleDepartment> sysRoleDepartmentList = sysRoleDepartmentMapper.select(sysRoleDepartment);
            SysMenuButtonKey sysMenuButtonKey;

            if (sysPowerKeyList.size() > 0) {
//                获取角色的权限菜单和菜单按钮
                for (SysPowerKey powerKey : sysPowerKeyList) {
                    SysMenu sysMenu = sysMenuMapper.selectByPrimaryKey(powerKey.getPkSysMenu());
                    if(sysMenu ==null){
                        continue;
                    }
//                    得到菜单主键从而获取菜单按钮
                    sysMenuButtonKey = new SysMenuButtonKey();
                    sysMenuButtonKey.setPkSysMenu(sysMenu.getPkSysMenu());
                    sysMenuButtonKey.setPkSysButton(powerKey.getPkSysButton());
                    SysMenuButton sysMenuButton = sysMenuButtonMapper.selectByPrimaryKey(sysMenuButtonKey);

                    sysMenuList.add(sysMenu);
                    sysMenuButtonList.add(sysMenuButton);
                }
            }
            List<SysRoleDepartment> sysRoleDepartmentList = sysRoleDepartmentMapper.select(sysRoleDepartment);

            role.put(Light.SYS_POWER_LIST,sysPowerKeyList);
            role.put(Light.SYS_MENU_LIST,sysMenuList);
            role.put(Light.SYS_MENU_BUTTON_LIST,sysMenuButtonList);
            role.put(Light.SYS_DEPT_LIST,sysRoleDepartmentList);

            utilsService.setSysUserKeyValue(role,role.getCreator(), LinkEntity.CREATOR_ENTITY);
            utilsService.setSysUserKeyValue(role,role.getModifier(),LinkEntity.MODIFIER_ENTITY);
            response.setData(role);
        }else{
            PageHelper.startPage(sysRole.getPageNo(), sysRole.getPageSize());
            List<SysRole> roleList = sysRoleMapper.select(sysRole);
            PageInfo pageInfo = new PageInfo(roleList);
            response.setTotal(pageInfo.getTotal());
            if (roleList.size() > 0) {
                for (SysRole list : roleList) {
                    utilsService.setSysUserKeyValue(list, list.getCreator(), LinkEntity.CREATOR_ENTITY);
                    utilsService.setSysUserKeyValue(list, list.getModifier(), LinkEntity.MODIFIER_ENTITY);
                }
            }
            response.setData(roleList);
            //response.setTotal(sysRoleMapper.countBy(sysRole));

        }
        return response;
    }

    @Override
    public Response save(Request<SysRole> tParams) {
        Response response = Response.newResponse();
        SysRole sysRole = tParams.getData();

        if(sysRole== null){
            return  response.PARAMS_ISNULL();
        }
        String primaryKey = sysRole.getPkSysRole();
        SysRole select = null;
        if (primaryKey != null && !primaryKey.equals("")) {
            select = sysRoleMapper.selectByPrimaryKey(primaryKey);
        }
        int iReuslt = 1;
        String message= "";
        try {
            if (select != null) {
                sysRole.setLasteditDate(new Date());
                iReuslt = sysRoleMapper.updateByPrimaryKeySelective(sysRole);

//            删除角色绑定的权限信息
                SysPowerKey powerKey = new SysPowerKey();
                powerKey.setPkSysRole(primaryKey);
                List<SysPowerKey> sysUserRoleKeyList = sysPowerMapper.select(powerKey);
                if (sysUserRoleKeyList.size() > 0) {
                    for (SysPowerKey sysPowerKey : sysUserRoleKeyList) {
                        sysPowerMapper.deleteByPrimaryKey(sysPowerKey);
                    }
                }
                //删除角色绑定部门
                SysRoleDepartment sysRoleDepetment=new SysRoleDepartment();
                sysRoleDepetment.setPkSysRole(primaryKey);
                List<SysRoleDepartment> sysRoleDepetmentList = sysRoleDepartmentMapper.select(sysRoleDepetment);
                if (sysRoleDepetmentList.size() > 0) {
                    for (SysRoleDepartment SysRoleDept : sysRoleDepetmentList) {
                    	sysRoleDepartmentMapper.deleteByPrimaryKey(SysRoleDept);
                    }
                }
            } else {
                sysRole.setPkSysRole(GuidUtils.getGuid());
                sysRole.setCreationDate(new Date());
                sysRole.setLasteditDate(new Date());
                sysRole.setIsvalid(1);
                iReuslt = sysRoleMapper.insertSelective(sysRole);
            }

//            获取传的角色权限信息
            Map<String, Object> map = sysRole.getMap();
            if(map != null) {

                List<SysPowerKey> sysPowerKeyList = DataUtils.objectToList(map.get(Light.SYS_POWER), SysPowerKey.class);

//            新增角色绑定的权限信息
                if (sysPowerKeyList != null && sysPowerKeyList.size() > 0) {
                    for (SysPowerKey sysPowerKey : sysPowerKeyList) {
                        sysPowerKey.setPkSysRole(sysRole.getPkSysRole());
                        sysPowerMapper.insertSelective(sysPowerKey);
                    }
                }
                List<SysRoleDepartment> sysRoleDepartmentList = DataUtils.objectToList(map.get(Light.SYS_DEPT), SysRoleDepartment.class);
               // 新增角色部门绑定的权限信息
                if (sysRoleDepartmentList != null && sysRoleDepartmentList.size() > 0) {
                    for (SysRoleDepartment sysRoleDept : sysRoleDepartmentList) {
                    	sysRoleDept.setPkSysRole(sysRole.getPkSysRole());
                    	sysRoleDepartmentMapper.insertSelective(sysRoleDept);
                    }
                }
            }
        }catch (Exception ex){
            message = ex.getMessage();
            return response.SAVE_FAIL(message);
        }
        if(iReuslt>0) {
            return response.SUCCESS();
        }
        return response.SAVE_FAIL(message);
    }


    @Override
    public Response domainRole(Request<String> tParams) {
        Response response = Response.newResponse();

//        获取用户的校区信息
        String primaryKey = tParams.getData();
        int iReuslt = 1;
        String message= "";
        try {
            if (primaryKey != null && !primaryKey.equals("")) {
//                根据用户中的校区信息查询所有角色信息
                SysRole sysRole = new SysRole();
                sysRole.setPkDomain(primaryKey);
                response.setData(sysRoleMapper.select(sysRole));
            }
        }catch (Exception ex){
            message = ex.getMessage();
            return response.FIND_FAIL(message);
        }
        if(iReuslt>0) {
            return response;
        }
        return response.FIND_FAIL(message);
    }

    @Override
    public Response getUserRole(Request<String> tParams) {
        Response response = Response.newResponse();

//        获取用户的主键信息
        String primaryKey = tParams.getData();
        String message= "";
        try {
            if (primaryKey != null && !primaryKey.equals("")) {
                SysUserRoleKey userRoleKey = new SysUserRoleKey();
                userRoleKey.setPkSysUser(primaryKey);
                List<SysUserRoleKey> keyList = sysUserRoleMapper.select(userRoleKey);
                String roleStr = "";
                if (keyList.size() > 0) {
                    for (SysUserRoleKey sysUserRoleKey : keyList) {
                        roleStr = roleStr + sysUserRoleKey.getPkSysRole() + ",";
                    }
                }

                SysRole sysRole = new SysRole();
                sysRole.setCode(roleStr);
                response.setData(sysRole);
            }
        }catch (Exception ex){
            message = ex.getMessage();
            return response.FIND_FAIL(message);
        }
        return response;
    }
}
