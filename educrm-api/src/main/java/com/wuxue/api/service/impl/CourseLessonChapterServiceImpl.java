package com.wuxue.api.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wuxue.api.mapper.CourseLessonChapterMapper;
import com.wuxue.api.service.CourseLessonChapterService;
import com.wuxue.api.service.UtilsService;
import com.wuxue.api.utils.GuidUtils;
import com.wuxue.model.CourseLessonChapter;
import com.wuxue.utils.contract.LinkEntity;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

//import com.wuxue.api.utils.GuidUtils;

@Service("courseLessonChapterService")
public class CourseLessonChapterServiceImpl implements CourseLessonChapterService{
    @Autowired
    CourseLessonChapterMapper courseLessonChapterMapper;
    @Autowired
    UtilsService utilsService;

    @Override
    public Response delete(Request<String> tParams) {
        Response response = Response.newResponse();
        String primaryKey = tParams.getData();
        if(primaryKey== null || primaryKey.equals("")){
            return  response.PARAMS_ISNULL();
        }
        String message= "";
        try {
            CourseLessonChapter courseLessonChapter = courseLessonChapterMapper.selectByPrimaryKey(primaryKey);
            courseLessonChapter.setIsdel(0);
            courseLessonChapterMapper.updateByPrimaryKeySelective(courseLessonChapter);
        }catch (Exception ex){
            message = ex.getMessage();
            return response.DELETE_FAIL(message);
        }
        return response;
    }

    @Override
    public Response find(Request<CourseLessonChapter> tParams) {
        Response response = Response.newResponse();
        CourseLessonChapter courseLessonChapter = tParams.getData();

        if(courseLessonChapter== null){
            return  response.PARAMS_ISNULL();
        }
        String primaryKey = courseLessonChapter.getPkCourseLesson();
        if(primaryKey !=null && !primaryKey.equals("")){
            CourseLessonChapter byPrimaryKey = courseLessonChapterMapper.selectByPrimaryKey(primaryKey);
            utilsService.setSysUserKeyValue(byPrimaryKey,byPrimaryKey.getCreator(), LinkEntity.CREATOR_ENTITY);
            utilsService.setSysUserKeyValue(byPrimaryKey,byPrimaryKey.getModifier(),LinkEntity.MODIFIER_ENTITY);
            response.setData(byPrimaryKey);
        }else{
           // PageHelper.startPage(courseLessonChapter.getPageNo(),courseLessonChapter.getPageSize());
            List<CourseLessonChapter> timeList = courseLessonChapterMapper.select(courseLessonChapter);
            if (timeList.size() > 0) {
                for (CourseLessonChapter list : timeList) {
                    utilsService.setSysUserKeyValue(list, list.getCreator(), LinkEntity.CREATOR_ENTITY);
                    utilsService.setSysUserKeyValue(list, list.getModifier(), LinkEntity.MODIFIER_ENTITY);
                }
            }
//            PageInfo<CourseLessonChapter> pageInfo = new PageInfo<>(timeList);
//            response.setData(pageInfo);
            response.setData(timeList);
            //response.setTotal(courseLessonChapterMapper.countBy(courseLessonChapter));
        }
        return response;
    }

    @Override
    public Response save(Request<CourseLessonChapter> tParams) {
        Response response = Response.newResponse();
        CourseLessonChapter courseLessonChapter = tParams.getData();

        if(courseLessonChapter== null){
            return  response.PARAMS_ISNULL();
        }
        String primaryKey = courseLessonChapter.getPkCourseLesson();
        CourseLessonChapter select = null;
        if (primaryKey != null && !primaryKey.equals("")) {
            select = courseLessonChapterMapper.selectByPrimaryKey(primaryKey);
        }
        int iReuslt = 1;
        String message= "";
        try {
            if (select != null) {
                courseLessonChapter.setLasteditDate(new Date());
                iReuslt = courseLessonChapterMapper.updateByPrimaryKeySelective(courseLessonChapter);
            } else {
//                courseLessonChapter.setPkCourseLesson(GuidUtils.getGuid());
                courseLessonChapter.setCreationDate(new Date());
                courseLessonChapter.setLasteditDate(new Date());
                List<CourseLessonChapter> list = courseLessonChapterMapper.select(courseLessonChapter);
                courseLessonChapter.setSort(list.size()+1);
                iReuslt = courseLessonChapterMapper.insertSelective(courseLessonChapter);
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

    /**
     * 章节排序，即时更新章节顺序
     * */
    @Override
    public Response updateSort(Request<String> tParams) {
        Response response = Response.newResponse();
        String pkList = tParams.getData();

        if(pkList == null){
            return  response.PARAMS_ISNULL();
        }
        String[] split = pkList.split(",");


        String message;
        try {
            for (int i = 0; i < split.length; i++) {
                CourseLessonChapter courseLessonChapter = courseLessonChapterMapper.selectByPrimaryKey(split[i]);
                courseLessonChapter.setSort(i+1);
                courseLessonChapterMapper.updateByPrimaryKeySelective(courseLessonChapter);
            }
        }catch (Exception ex){
            message = ex.getMessage();
            return response.SAVE_FAIL(message);
        }
        return response;
    }
}
