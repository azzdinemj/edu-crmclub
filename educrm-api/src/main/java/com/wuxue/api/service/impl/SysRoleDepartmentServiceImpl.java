package com.wuxue.api.service.impl;

import com.wuxue.api.mapper.SysRoleDepartmentMapper;
import com.wuxue.api.service.UtilsService;
import com.wuxue.model.SysRoleDepartment;
import com.wuxue.model.SysRoleDepartmentKey;
import com.wuxue.api.service.SysRoleDepartmentService;
import com.wuxue.api.utils.GuidUtils;
import com.wuxue.utils.contract.LinkEntity;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

//import com.wuxue.api.utils.GuidUtils;

@Service("SysRoleDepartmentService")
public class SysRoleDepartmentServiceImpl implements SysRoleDepartmentService{
    @Autowired
    SysRoleDepartmentMapper sysRoleDepartmentMapper;
    @Autowired
    UtilsService utilsService;

    @Override
    public Response delete(Request<SysRoleDepartmentKey> tParams) {
        Response response = Response.newResponse();
        SysRoleDepartmentKey sysRoleDepartmentKey = tParams.getData();
//        String primaryKey = tParams.getData();
        if(sysRoleDepartmentKey== null){
            return  response.PARAMS_ISNULL();
        }
        int iReuslt = 1;
        String message= "";
        try {
            iReuslt=sysRoleDepartmentMapper.deleteByPrimaryKey(sysRoleDepartmentKey);
        }catch (Exception ex){
            message = ex.getMessage();
            return response.SAVE_FAIL(message);
        }
        if(iReuslt>0) {
            return response;
        }
        return response.DELETE_FAIL(message);
    }

    @Override
    public Response find(Request<SysRoleDepartment> tParams) {
        Response response = Response.newResponse();
        SysRoleDepartment sysRoleDepartment = tParams.getData();

        if(sysRoleDepartment== null){
            return  response.PARAMS_ISNULL();
        }
        String primaryKey = sysRoleDepartment.getPkDepartment();
        String primaryKey2 = sysRoleDepartment.getPkSysRole();
        if(primaryKey !=null && !primaryKey.equals("") || primaryKey2 !=null && !primaryKey2.equals("")){
        	SysRoleDepartmentKey sysRoleDepartmentKey = new SysRoleDepartmentKey();
        	sysRoleDepartmentKey.setPkSysRole(primaryKey2);
        	sysRoleDepartmentKey.setPkDepartment(primaryKey);
        	SysRoleDepartment byPrimaryKey = sysRoleDepartmentMapper.selectByPrimaryKey(sysRoleDepartmentKey);
            response.setData(byPrimaryKey);
        }else{
            List<SysRoleDepartment> sysRoleDepartmentList = sysRoleDepartmentMapper.select(sysRoleDepartment);

            response.setData(sysRoleDepartmentList);
            //response.setTotal(sysMenuButtonMapper.countBy(sysMenuButton));

        }
        return response;
    }

    @Override
    public Response save(Request<SysRoleDepartment> tParams) {
        Response response = Response.newResponse();
        SysRoleDepartment sysRoleDepartment = tParams.getData();

        if(sysRoleDepartment== null){
            return  response.PARAMS_ISNULL();
        }
        String primaryKey1 = sysRoleDepartment.getPkDepartment();
        String primaryKey2 = sysRoleDepartment.getPkSysRole();
        SysRoleDepartment select = null;
        if (primaryKey1 != null && !primaryKey1.equals("") && primaryKey2 != null && !primaryKey2.equals("")) {
        	SysRoleDepartmentKey sysRoleDepartmentKey = new SysRoleDepartmentKey();
        	sysRoleDepartmentKey.setPkSysRole(primaryKey2);
        	sysRoleDepartmentKey.setPkDepartment(primaryKey1);
            select = sysRoleDepartmentMapper.selectByPrimaryKey(sysRoleDepartmentKey);
        }
        int iReuslt = 1;
        String message= "";
        try {
            if (select != null) {
                iReuslt = sysRoleDepartmentMapper.updateByPrimaryKeySelective(sysRoleDepartment);
            } else {
            	//sysRoleDepartment.setPkSysButton(GuidUtils.getGuid());
                iReuslt = sysRoleDepartmentMapper.insertSelective(sysRoleDepartment);
            }
        }catch (Exception ex){
            message = ex.getMessage();
            return response.SAVE_FAIL(message);
        }
        if(iReuslt>0) {
            return response;
        }
        return response.SAVE_FAIL(message);
    }
}
