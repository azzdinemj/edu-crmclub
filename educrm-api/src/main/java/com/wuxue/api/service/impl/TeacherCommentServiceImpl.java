package com.wuxue.api.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wuxue.api.mapper.TeacherCommentMapper;
import com.wuxue.api.service.TeacherCommentService;
import com.wuxue.api.service.UtilsService;
import com.wuxue.api.utils.GuidUtils;
import com.wuxue.model.TeacherComment;
import com.wuxue.utils.contract.LinkEntity;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

//import com.wuxue.api.utils.GuidUtils;

@Service("teacherCommentService")
public class TeacherCommentServiceImpl implements TeacherCommentService{
    @Autowired
    TeacherCommentMapper teacherCommentMapper;
    @Autowired
    UtilsService utilsService;

    @Override
    public Response delete(Request<String> tParams) {
        Response response = Response.newResponse();
        String primaryKey = tParams.getData();
        if(primaryKey== null || primaryKey.equals("")){
            return  response.PARAMS_ISNULL();
        }
        int iReuslt = 0;
        String message= "";
        try {
            TeacherComment byprimaryKey = new TeacherComment();
            TeacherComment teacherComment = teacherCommentMapper.selectByPrimaryKey(primaryKey);
            if (teacherComment != null){
                teacherComment.setStatus(0);
                iReuslt=teacherCommentMapper.updateByPrimaryKeySelective(teacherComment);
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
    public Response find(Request<TeacherComment> tParams) {
        Response response = Response.newResponse();
        TeacherComment teacherComment = tParams.getData();

        if(teacherComment== null){
            return  response.PARAMS_ISNULL();
        }
        String primaryKey = teacherComment.getPkTeacherComment();
        if(primaryKey !=null && !primaryKey.equals("")){
            TeacherComment byPrimaryKey = teacherCommentMapper.selectByPrimaryKey(primaryKey);
            utilsService.setSysUserKeyValue(byPrimaryKey, byPrimaryKey.getCreator(), LinkEntity.CREATOR_ENTITY);
            utilsService.setStudentKeyValue(byPrimaryKey, byPrimaryKey.getPkStudent(), LinkEntity.STUDENT_ENTITY);
            utilsService.setSysDictKeyValue(byPrimaryKey, byPrimaryKey.getSemester(), LinkEntity.SEMESTER_ENTITY);
            utilsService.setDivisionKeyValue(byPrimaryKey, byPrimaryKey.getDiscipline(), LinkEntity.DIVISION_ENTITY);
            utilsService.setSysUserKeyValue(byPrimaryKey, byPrimaryKey.getModifier(), LinkEntity.MODIFIER_ENTITY);
            response.setData(byPrimaryKey);
        }else{
            PageHelper.startPage(teacherComment.getPageNo(),teacherComment.getPageSize());
            List<TeacherComment> timeList = teacherCommentMapper.select(teacherComment);
            PageInfo page = new PageInfo(timeList);
            response.setTotal(page.getTotal());
            if (timeList.size() > 0) {
                for (TeacherComment list : timeList) {
                    utilsService.setSysUserKeyValue(list, list.getCreator(), LinkEntity.CREATOR_ENTITY);
                    utilsService.setStudentKeyValue(list, list.getPkStudent(), LinkEntity.STUDENT_ENTITY);
                    utilsService.setSysDictKeyValue(list, list.getSemester(), LinkEntity.SEMESTER_ENTITY);
                    utilsService.setDivisionKeyValue(list, list.getDiscipline(), LinkEntity.DIVISION_ENTITY);
                    utilsService.setSysUserKeyValue(list, list.getModifier(), LinkEntity.MODIFIER_ENTITY);
                }
            }
            response.setData(timeList);
            //response.setTotal(teacherCommentMapper.countBy(teacherComment));
        }
        return response;
    }

    @Override
    public Response save(Request<TeacherComment> tParams) {
        Response response = Response.newResponse();
        TeacherComment teacherComment = tParams.getData();

        if(teacherComment== null){
            return  response.PARAMS_ISNULL();
        }
        String primaryKey = teacherComment.getPkTeacherComment();
        TeacherComment select = null;
        if (primaryKey != null && !primaryKey.equals("")) {
            select = teacherCommentMapper.selectByPrimaryKey(primaryKey);
        }
        String message= "";
        try {
            if (select != null) {
                teacherComment.setLasteditDate(new Date());
                teacherComment.setModifier(tParams.getCurrentUser());
                teacherCommentMapper.updateByPrimaryKeySelective(teacherComment);
            } else {
                teacherComment.setPkTeacherComment(GuidUtils.getGuid());
                teacherComment.setPkDomain(tParams.getCurrendDomain());
                teacherComment.setStatus(1);
                List<TeacherComment> comments = teacherCommentMapper.select(teacherComment);
                if (comments.size() > 0) {
                    message = "不可重复评价";
                    return response.SAVE_FAIL(message);
                }
                teacherComment.setCreationDate(new Date());
                teacherComment.setLasteditDate(new Date());
                teacherComment.setCreator(tParams.getCurrentUser());
                teacherComment.setModifier(tParams.getCurrentUser());
                teacherCommentMapper.insertSelective(teacherComment);
            }
        }catch (Exception ex){
            message = ex.getMessage();
            return response.SAVE_FAIL(message);
        }
        return response;
    }
}
