package com.wuxue.api.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wuxue.api.mapper.StudentLessonMapper;
import com.wuxue.api.service.StudentLessonService;
import com.wuxue.api.service.UtilsService;
import com.wuxue.api.utils.GuidUtils;
import com.wuxue.model.StudentAssign;
import com.wuxue.model.StudentLesson;
import com.wuxue.utils.contract.LinkEntity;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

//import com.wuxue.api.utils.GuidUtils;

@Service("studentLessonService")
public class StudentLessonServiceImpl implements StudentLessonService{
    @Autowired
    StudentLessonMapper studentLessonMapper;
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
            iReuslt=studentLessonMapper.deleteByPrimaryKey(primaryKey);
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
    public Response find(Request<StudentLesson> tParams) {
        Response response = Response.newResponse();
        StudentLesson studentLesson = tParams.getData();

        if(studentLesson== null){
            return  response.PARAMS_ISNULL();
        }
        String primaryKey = studentLesson.getPkStudentLesson();
        if(primaryKey !=null && !primaryKey.equals("")){
            StudentLesson byPrimaryKey = studentLessonMapper.selectByPrimaryKey(primaryKey);
            response.setData(byPrimaryKey);
        }else{
            PageHelper.startPage(studentLesson.getPageNo(),studentLesson.getPageSize());
            List<StudentLesson> timeList = studentLessonMapper.select(studentLesson);
            PageInfo<StudentLesson> pageInfo = new PageInfo<>(timeList);
            response.setData(pageInfo);
        }
        return response;
    }

    @Override
    public Response save(Request<StudentLesson> tParams) {
        Response response = Response.newResponse();
        StudentLesson studentLesson = tParams.getData();

        if(studentLesson== null){
            return  response.PARAMS_ISNULL();
        }
        String primaryKey = studentLesson.getPkStudentLesson();
        StudentLesson select = null;
        if (primaryKey != null && !primaryKey.equals("")) {
            select = studentLessonMapper.selectByPrimaryKey(primaryKey);
        }
        int iReuslt = 1;
        String message= "";
        try {
            if (select != null) {
                studentLesson.setLasteditDate(new Date());
                iReuslt = studentLessonMapper.updateByPrimaryKeySelective(studentLesson);
            } else {
                studentLesson.setPkStudentLesson(GuidUtils.getGuid());
                studentLesson.setCreationDate(new Date());
                studentLesson.setLasteditDate(new Date());
                iReuslt = studentLessonMapper.insertSelective(studentLesson);
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
