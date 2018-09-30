package com.wuxue.api.service.impl;

import com.wuxue.api.mapper.DepartmentMapper;
import com.wuxue.api.service.UtilsService;
import com.wuxue.model.Department;
import com.wuxue.api.service.DepartmentService;
import com.wuxue.api.utils.GuidUtils;
import com.wuxue.utils.contract.LinkEntity;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

//import com.wuxue.api.utils.GuidUtils;

@Service("departmentService")
public class DepartmentServiceImpl implements DepartmentService{
    @Autowired
    DepartmentMapper departmentMapper;
    @Autowired
    UtilsService utilsService;

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
            iReuslt=departmentMapper.deleteByPrimaryKey(primaryKey);
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
    public Response find(Request<Department> tParams) {
        Response response = Response.newResponse();
        Department department = tParams.getData();

        if(department== null){
            return  response.PARAMS_ISNULL();
        }
        String primaryKey = department.getPkDepartment();
        if(primaryKey !=null && !primaryKey.equals("")){
            Department byPrimaryKey = departmentMapper.selectByPrimaryKey(primaryKey);
            utilsService.setSysUserKeyValue(byPrimaryKey,byPrimaryKey.getCreator(), LinkEntity.CREATOR_ENTITY);
            utilsService.setSysUserKeyValue(byPrimaryKey,byPrimaryKey.getModifier(),LinkEntity.MODIFIER_ENTITY);
            response.setData(byPrimaryKey);
        }else{
            department.setIsvalid(1);
            List<Department> departmentList = departmentMapper.select(department);
            if (departmentList.size() > 0) {
                for (Department list : departmentList) {
                    utilsService.setSysUserKeyValue(list, list.getCreator(), LinkEntity.CREATOR_ENTITY);
                    utilsService.setSysUserKeyValue(list, list.getModifier(), LinkEntity.MODIFIER_ENTITY);
                }
            }
            response.setData(departmentList);
            //response.setTotal(departmentMapper.countBy(department));
        }
        return response;
    }

    @Override
    public Response save(Request<Department> tParams) {
        Response response = Response.newResponse();
        Department department = tParams.getData();

        if(department== null){
            return  response.PARAMS_ISNULL();
        }
        String primaryKey = department.getPkDepartment();
        Department select = null;
        if (primaryKey != null && !primaryKey.equals("")) {
            select = departmentMapper.selectByPrimaryKey(primaryKey);
        }
        int iReuslt = 1;
        String message= "";
        try {
            if (select != null) {
                department.setLasteditDate(new Date());
                iReuslt = departmentMapper.updateByPrimaryKeySelective(department);
            } else {
                department.setPkDepartment(GuidUtils.getGuid());
                department.setCreationDate(new Date());
                department.setLasteditDate(new Date());
                department.setIsvalid(1);
                iReuslt = departmentMapper.insertSelective(department);
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
