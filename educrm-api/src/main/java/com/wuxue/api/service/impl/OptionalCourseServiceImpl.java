package com.wuxue.api.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wuxue.api.mapper.OptionalCourseMapper;
import com.wuxue.api.service.OptionalCourseService;
import com.wuxue.api.service.UtilsService;
import com.wuxue.model.OptionalCourse;
import com.wuxue.utils.contract.LinkEntity;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service("optionalCourseService")
public class OptionalCourseServiceImpl implements OptionalCourseService {
    @Autowired
    OptionalCourseMapper optionalCourseMapper;

    @Autowired
    UtilsService utilsService;

    @Override
    public Response delete(Request<OptionalCourse> tParams) {
        Response response = Response.newResponse();
        OptionalCourse primaryKey = tParams.getData();
        if(primaryKey== null || primaryKey.equals("")){
            return  response.PARAMS_ISNULL();
        }
        int iReuslt = 1;
        String message= "";
        try {
            iReuslt=optionalCourseMapper.deleteByPrimaryKey(primaryKey);
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
    public Response find(Request<OptionalCourse> tParams) {
        Response response = Response.newResponse();
        OptionalCourse optionalCourse = tParams.getData();

        if(optionalCourse== null){
            return  response.PARAMS_ISNULL();
        }
        String primaryKey = optionalCourse.getPkSchedul();
        if(primaryKey !=null && !primaryKey.equals("")){
            optionalCourse = optionalCourseMapper.selectByPrimaryKey(primaryKey);
            utilsService.setSysUserKeyValue(optionalCourse,optionalCourse.getCreator(), LinkEntity.CREATOR_ENTITY);
            utilsService.setSysUserKeyValue(optionalCourse,optionalCourse.getModifier(), LinkEntity.MODIFIER_ENTITY);
            response.setData(optionalCourse);
        }else{
            PageHelper.startPage(optionalCourse.getPageNo(),optionalCourse.getPageSize());
            List<OptionalCourse> list = optionalCourseMapper.select(optionalCourse);
            PageInfo page = new PageInfo(list);
            response.setTotal(page.getTotal());
            for (OptionalCourse optionalCourseEntity : list) {
                utilsService.setSysUserKeyValue(optionalCourseEntity,optionalCourseEntity.getCreator(), LinkEntity.CREATOR_ENTITY);
                utilsService.setSysUserKeyValue(optionalCourseEntity,optionalCourseEntity.getModifier(),LinkEntity.MODIFIER_ENTITY);
                utilsService.setSysDictKeyValue(optionalCourseEntity,optionalCourseEntity.getSchedulCaption(),LinkEntity.COURSE_ENTITY);
            }
            response.setData(list);
        }
        return response;
    }

    @Override
    public Response save(Request<OptionalCourse> tParams) {
        Response response = Response.newResponse();
        OptionalCourse optionalCourse = tParams.getData();


        return null;
    }
}
