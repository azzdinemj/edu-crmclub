package com.wuxue.api.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wuxue.api.mapper.CourseEvaluateMapper;
import com.wuxue.api.service.CourseEvaluateService;
import com.wuxue.api.service.UtilsService;
import com.wuxue.api.utils.GuidUtils;
import com.wuxue.model.CourseEvaluate;
import com.wuxue.utils.contract.LinkEntity;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

//import com.wuxue.api.utils.GuidUtils;

@Service("courseEvaluateService")
public class CourseEvaluateServiceImpl implements CourseEvaluateService{
    @Autowired
    CourseEvaluateMapper courseEvaluateMapper;
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
            iReuslt=courseEvaluateMapper.deleteByPrimaryKey(primaryKey);
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
    public Response find(Request<CourseEvaluate> tParams) {
        Response response = Response.newResponse();
        CourseEvaluate courseEvaluate = tParams.getData();

        if(courseEvaluate== null){
            return  response.PARAMS_ISNULL();
        }
        String primaryKey = courseEvaluate.getPkCourseEvaluate();
        if(primaryKey !=null && !primaryKey.equals("")){
            CourseEvaluate byPrimaryKey = courseEvaluateMapper.selectByPrimaryKey(primaryKey);
            utilsService.setSysUserKeyValue(byPrimaryKey,byPrimaryKey.getCreator(), LinkEntity.CREATOR_ENTITY);
            utilsService.setSysUserKeyValue(byPrimaryKey,byPrimaryKey.getModifier(),LinkEntity.MODIFIER_ENTITY);
            response.setData(byPrimaryKey);
        }else{
            PageHelper.startPage(courseEvaluate.getPageNo(),courseEvaluate.getPageSize());
            List<CourseEvaluate> timeList = courseEvaluateMapper.select(courseEvaluate);
            if (timeList.size() > 0) {
                for (CourseEvaluate list : timeList) {
                    utilsService.setSysUserKeyValue(list, list.getCreator(), LinkEntity.CREATOR_ENTITY);
                    utilsService.setSysUserKeyValue(list, list.getModifier(), LinkEntity.MODIFIER_ENTITY);
                }
            }
            PageInfo<CourseEvaluate> pageInfo = new PageInfo<>(timeList);
            response.setData(pageInfo);
            //response.setTotal(courseEvaluateMapper.countBy(courseEvaluate));
        }
        return response;
    }

    @Override
    public Response save(Request<CourseEvaluate> tParams) {
        Response response = Response.newResponse();
        CourseEvaluate courseEvaluate = tParams.getData();

        if(courseEvaluate== null){
            return  response.PARAMS_ISNULL();
        }
        String primaryKey = courseEvaluate.getPkCourseEvaluate();
        CourseEvaluate select = null;
        if (primaryKey != null && !primaryKey.equals("")) {
            select = courseEvaluateMapper.selectByPrimaryKey(primaryKey);
        }
        int iReuslt = 1;
        String message= "";
        try {
            if (select != null) {
                courseEvaluate.setLasteditDate(new Date());
                iReuslt = courseEvaluateMapper.updateByPrimaryKeySelective(courseEvaluate);
            } else {
                courseEvaluate.setPkCourseEvaluate(GuidUtils.getGuid());
                courseEvaluate.setCreationDate(new Date());
                courseEvaluate.setLasteditDate(new Date());
                courseEvaluate.setIsdel(0);
                courseEvaluate.setStatus(1);
                iReuslt = courseEvaluateMapper.insertSelective(courseEvaluate);
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
