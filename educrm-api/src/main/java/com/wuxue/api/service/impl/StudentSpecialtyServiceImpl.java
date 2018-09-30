package com.wuxue.api.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wuxue.api.mapper.StudentSpecialtyMapper;
import com.wuxue.api.service.UtilsService;
import com.wuxue.model.StudentSpecialty;
import com.wuxue.api.service.StudentSpecialtyService;
import com.wuxue.api.utils.GuidUtils;
import com.wuxue.utils.contract.LinkEntity;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

//import com.wuxue.api.utils.GuidUtils;

@Service("studentSpecialtyService")
public class StudentSpecialtyServiceImpl implements StudentSpecialtyService{
    @Autowired
    StudentSpecialtyMapper studentSpecialtyMapper;
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
            iReuslt=studentSpecialtyMapper.deleteByPrimaryKey(primaryKey);
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
    public Response find(Request<StudentSpecialty> tParams) {
        Response response = Response.newResponse();
        StudentSpecialty studentSpecialty = tParams.getData();

        if(studentSpecialty== null){
            return  response.PARAMS_ISNULL();
        }
        String primaryKey = studentSpecialty.getPkStudentSpecialty();
        if(primaryKey !=null && !primaryKey.equals("")){
            StudentSpecialty byPrimaryKey = studentSpecialtyMapper.selectByPrimaryKey(primaryKey);
            utilsService.setSysUserKeyValue(byPrimaryKey,byPrimaryKey.getCreator(), LinkEntity.CREATOR_ENTITY);
            utilsService.setSysUserKeyValue(byPrimaryKey,byPrimaryKey.getModifier(),LinkEntity.MODIFIER_ENTITY);
            response.setData(byPrimaryKey);
        }else{
            PageHelper.startPage(studentSpecialty.getPageNo(),studentSpecialty.getPageSize());
            List<StudentSpecialty> specialtyList = studentSpecialtyMapper.select(studentSpecialty);
            PageInfo page = new PageInfo(specialtyList);
            response.setTotal(page.getTotal());
            if (specialtyList.size() > 0) {
                for (StudentSpecialty list : specialtyList) {
                    utilsService.setSysUserKeyValue(list, list.getCreator(), LinkEntity.CREATOR_ENTITY);
                    utilsService.setSysUserKeyValue(list, list.getModifier(), LinkEntity.MODIFIER_ENTITY);
                }
            }
            response.setData(specialtyList);
            //response.setTotal(studentSpecialtyMapper.countBy(studentSpecialty));

        }
        return response;
    }

    @Override
    public Response save(Request<StudentSpecialty> tParams) {
        Response response = Response.newResponse();
        StudentSpecialty studentSpecialty = tParams.getData();

        if(studentSpecialty== null){
            return  response.PARAMS_ISNULL();
        }
        String primaryKey = studentSpecialty.getPkStudentSpecialty();
        StudentSpecialty select = null;
        if (primaryKey != null && !primaryKey.equals("")) {
            select = studentSpecialtyMapper.selectByPrimaryKey(primaryKey);
        }
        int iReuslt = 1;
        String message= "";
        try {
            if (select != null) {
                studentSpecialty.setLasteditDate(new Date());
                studentSpecialty.setModifier(tParams.getCurrentUser());
                iReuslt = studentSpecialtyMapper.updateByPrimaryKeySelective(studentSpecialty);
            } else {
                studentSpecialty.setPkStudentSpecialty(GuidUtils.getGuid());
                studentSpecialty.setCreator(tParams.getCurrentUser());
                studentSpecialty.setModifier(tParams.getCurrentUser());
                studentSpecialty.setCreationDate(new Date());
                studentSpecialty.setLasteditDate(new Date());
                iReuslt = studentSpecialtyMapper.insertSelective(studentSpecialty);
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
