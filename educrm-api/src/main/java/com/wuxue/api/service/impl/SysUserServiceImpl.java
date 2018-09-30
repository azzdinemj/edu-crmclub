package com.wuxue.api.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wuxue.api.mapper.*;
import com.wuxue.api.service.UtilsService;
import com.wuxue.base.KeyValue;
import com.wuxue.model.*;
import com.wuxue.utils.contract.Light;
import com.wuxue.api.service.SysUserService;
//import com.wuxue.api.utils.GuidUtils;
import com.wuxue.api.utils.GuidUtils;
import com.wuxue.utils.common.DataUtils;
import com.wuxue.utils.common.MD5Util;
import com.wuxue.utils.contract.LinkEntity;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("sysUserService")
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    SysUserMapper sysUserMapper;

    @Autowired
    UtilsService utilsService;

    @Autowired
    SysUserRoleMapper sysUserRoleMapper;

    @Autowired
    SysRoleMapper sysRoleMapper;

    @Autowired
    SysMenuMapper sysMenuMapper;
    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    DomainMapper domainMapper;

    @Override
    public Response delete(Request<String> tParams) {
        Response response = Response.newResponse();
        String primaryKey = tParams.getData();
        if (primaryKey == null || primaryKey.equals("")) {
            return response.PARAMS_ISNULL();
        }
        int iReuslt = 1;
        String message = "";
        try {
            SysUser sysUser = sysUserMapper.selectByPrimaryKey(primaryKey);
            if (sysUser !=null){
                SysUserRoleKey userRoleKey = new SysUserRoleKey();
                userRoleKey.setPkSysUser(primaryKey);
                List<SysUserRoleKey> sysUserRoleKeyList = sysUserRoleMapper.select(userRoleKey);
                if (sysUserRoleKeyList.size() > 0) {
                    for (SysUserRoleKey sysUserRoleKey : sysUserRoleKeyList) {
                        sysUserRoleMapper.deleteByPrimaryKey(sysUserRoleKey);
                    }
                }
                sysUser.setIsvalid(3);
                iReuslt = sysUserMapper.updateByPrimaryKeySelective(sysUser);
            }

//                删除之前用户角色绑定

        } catch (Exception ex) {
            message = ex.getMessage();
            return response.DELETE_FAIL(message);
        }
        if (iReuslt > 0) {
            return response;
        }
        return response.DELETE_FAIL(message);
    }

    @Override
    public Response find(Request<SysUser> tParams) {
        Response response = Response.newResponse();
        SysUser sysUser = tParams.getData();

        if (sysUser == null) {
            return response.PARAMS_ISNULL();
        }
        String primaryKey = sysUser.getPkSysUser();
        if (primaryKey != null && !primaryKey.equals("")) {
            SysUser user = sysUserMapper.selectByPrimaryKey(primaryKey);
            user.setPassword("******");
            utilsService.setSysUserKeyValue(user,user.getCreator(),LinkEntity.CREATOR_ENTITY);
            utilsService.setSysUserKeyValue(user,user.getModifier(),LinkEntity.MODIFIER_ENTITY);
            utilsService.setDepartmentKeyValue(user,user.getPkDepartment(),LinkEntity.DEPARTMENT_ENTITY);
//            查询用户角色关联表
            SysUserRoleKey sysUserRoleKey = new SysUserRoleKey();
            sysUserRoleKey.setPkSysUser(primaryKey);
            List<SysUserRoleKey> keyList = sysUserRoleMapper.select(sysUserRoleKey);

//            查询角色信息放入list
            List<SysRole> sysRoleList = new ArrayList<>();
            for (SysUserRoleKey userRoleKey : keyList) {
                SysRole sysRole = sysRoleMapper.selectByPrimaryKey(userRoleKey.getPkSysRole());
                if (sysRole != null) {
                    sysRoleList.add(sysRole);
                }
            }

//            把查询到的角色list放入用户表,并返回
            user.put(Light.ROLE_LIST, sysRoleList);
            response.setData(user);

        } else {
            PageHelper.startPage(sysUser.getPageNo(),sysUser.getPageSize());
            List<SysUser> list = sysUserMapper.select(sysUser);
            PageInfo page = new PageInfo(list);
            response.setTotal(page.getTotal());
            for (SysUser sysUserEntity : list) {
                sysUserEntity.setPassword("******");
                utilsService.setSysUserKeyValue(sysUserEntity,sysUserEntity.getCreator(),LinkEntity.CREATOR_ENTITY);
                utilsService.setSysUserKeyValue(sysUserEntity,sysUserEntity.getModifier(),LinkEntity.MODIFIER_ENTITY);
                utilsService.setDepartmentKeyValue(sysUserEntity,sysUserEntity.getPkDepartment(),LinkEntity.DEPARTMENT_ENTITY);
            }
            response.setData(list);
            //response.setTotal(sysUserMapper.countBy(sysUser));

        }
        return response;
    }

    @Override
    public Response save(Request<SysUser> tParams) {
        Response response = Response.newResponse();
        SysUser sysUser = tParams.getData();

        if (sysUser == null) {
            return response.PARAMS_ISNULL();
        }
        String primaryKey = sysUser.getPkSysUser();
        SysUser user = null;
        if (primaryKey != null && !primaryKey.equals("")) {
            user = sysUserMapper.selectByPrimaryKey(primaryKey);
        }
        int iReuslt = 1;
        String message = "";
        try {
            if (user != null) {
                sysUser.setLasteditDate(new Date());
                iReuslt = sysUserMapper.updateByPrimaryKeySelective(sysUser);

//                删除之前用户角色绑定
                SysUserRoleKey userRoleKey = new SysUserRoleKey();
                userRoleKey.setPkSysUser(primaryKey);
                List<SysUserRoleKey> sysUserRoleKeyList = sysUserRoleMapper.select(userRoleKey);
                if (sysUserRoleKeyList.size() > 0) {
                    for (SysUserRoleKey sysUserRoleKey : sysUserRoleKeyList) {
                        sysUserRoleMapper.deleteByPrimaryKey(sysUserRoleKey);
                    }
                }
            } else {
//                sysUser.setPkSysUser(GuidUtils.getGuid());
                sysUser.setCreationDate(new Date());
                sysUser.setLasteditDate(new Date());
                sysUser.setIsvalid(1);
//                sysUser.setPassword(MD5Util.string2MD5(sysUser.getPassword()));
                iReuslt = sysUserMapper.insertSelective(sysUser);
            }

//                判断用户角色是否为空
            Map<String, Object> map = sysUser.getMap();
            if (map != null) {
                List<SysUserRoleKey> sysUserRoleKeyList = DataUtils.objectToList(map.get(Light.SYS_USER_ROLE), SysUserRoleKey.class);
//                String jsonString = JSON.toJSONString(map.get("sysUserRole"));
//                ArrayList<SysUserRoleKey> sysUserRoleKeyArrayList =
//                        JSON.parseObject(jsonString, new TypeReference<ArrayList<SysUserRoleKey>>() {
//                        });

//                用户角色增加
                if (sysUserRoleKeyList != null && sysUserRoleKeyList.size() > 0) {
                    for (SysUserRoleKey userRoleKey : sysUserRoleKeyList) {
                        userRoleKey.setPkSysUser(sysUser.getPkSysUser());
                        sysUserRoleMapper.insertSelective(userRoleKey);
                    }
                }
            }

        } catch (Exception ex) {
            message = ex.getMessage();
            return response.SAVE_FAIL(message);
        }
        if (iReuslt > 0) {
            return response;
        }
        return response.SAVE_FAIL(message);
    }

    @Override
    public Response login(Request<SysUser> tParams) {
        if (tParams == null || tParams.getData() == null || tParams.getData().getPkSysUser() == null) {
            return Response.newResponse().PARAMS_ISNULL();
        }
        SysUser sysUserRequest = tParams.getData();
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(tParams.getData().getPkSysUser());
        if (sysUser == null || sysUser.getPkSysUser() == null || sysUser.getPkSysUser().equals("")) {
            return Response.newResponse().USER_NULL();
        }
        if(sysUser.getIsvalid() ==null || sysUser.getIsvalid() != 1){
            return Response.newResponse().USER_ISVALID();
        }
        if (!sysUser.getPassword().equals(MD5Util.string2MD5(sysUserRequest.getPassword()))) {
            return Response.newResponse().LOGIN_FAIL();
        }
        Response response = Response.newResponse();
        sysUser.setPassword("");
        Domain domain = domainMapper.selectByPrimaryKey(sysUser.getPkDomain());
        sysUser.put(Light.DOMAIN_CAPTION,domain.getCaption());
        sysUser.put(Light.SYS_MENU_LIST, sysMenuMapper.selectByUser(sysUser));
        sysUser.put(Light.SYS_EMPLOYEE, employeeMapper.selectByUser(sysUser.getPkSysUser()));
        response.setData(sysUser);
        return response;
    }

    @Override
    public Response updatePassword(Request<SysUser> tParams) {
        if (tParams == null || tParams.getData() == null || tParams.getData().getPkSysUser() == null) {
            return Response.newResponse().PARAMS_ISNULL();
        }

        String message = "";
        try {
            SysUser sysUser = tParams.getData();
            sysUser.setPassword(MD5Util.string2MD5(sysUser.getPassword()));
            sysUser.setModifier(tParams.getCurrentUser());
            sysUser.setLasteditDate(new Date());
            sysUserMapper.updateByPrimaryKeySelective(sysUser);
        }catch(Exception ex){
            message = ex.getMessage();
            return Response.newResponse().SAVE_FAIL(message);
        }

        return Response.newResponse();
    }

}
