package com.wuxue.api.service.impl;

import com.wuxue.api.mapper.ClassinfoStudentMapper;
import com.wuxue.api.mapper.StudentShiftMapper;
import com.wuxue.api.service.UtilsService;
import com.wuxue.model.ClassinfoStudent;
import com.wuxue.model.StudentShift;
import com.wuxue.api.service.StudentShiftService;
import com.wuxue.api.utils.GuidUtils;
import com.wuxue.utils.contract.LinkEntity;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

//import com.wuxue.api.utils.GuidUtils;

@Service("studentShiftService")
public class StudentShiftServiceImpl implements StudentShiftService{
    @Autowired
    StudentShiftMapper studentShiftMapper;
    @Autowired
    UtilsService utilsService;
    @Autowired
    ClassinfoStudentMapper classinfoStudentMapper;

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
            iReuslt=studentShiftMapper.deleteByPrimaryKey(primaryKey);
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
    public Response find(Request<StudentShift> tParams) {
        Response response = Response.newResponse();
        StudentShift studentShift = tParams.getData();

        if(studentShift== null){
            return  response.PARAMS_ISNULL();
        }
        String primaryKey = studentShift.getPkStudentShift();
        if(primaryKey !=null && !primaryKey.equals("")){
            StudentShift byPrimaryKey = studentShiftMapper.selectByPrimaryKey(primaryKey);
            utilsService.setClassInfoKeyValue(byPrimaryKey, byPrimaryKey.getPkParentClassinfo(), LinkEntity.PARENT_CLASS_INFO_ENTITY);
            utilsService.setClassInfoKeyValue(byPrimaryKey, byPrimaryKey.getPkClassinfo(), LinkEntity.CLASS_INFO_ENTITY);
            utilsService.setStudentKeyValue(byPrimaryKey, byPrimaryKey.getPkStudent(), LinkEntity.STUDENT_ENTITY);
            utilsService.setSysUserKeyValue(byPrimaryKey,byPrimaryKey.getCreator(), LinkEntity.CREATOR_ENTITY);
            utilsService.setSysUserKeyValue(byPrimaryKey,byPrimaryKey.getModifier(),LinkEntity.MODIFIER_ENTITY);
            response.setData(byPrimaryKey);
        }else{
            List<StudentShift> studentShiftList = studentShiftMapper.select(studentShift);
            if (studentShiftList.size() > 0) {
                for (StudentShift list : studentShiftList) {
                    utilsService.setClassInfoKeyValue(list, list.getPkParentClassinfo(), LinkEntity.PARENT_CLASS_INFO_ENTITY);
                    utilsService.setClassInfoKeyValue(list, list.getPkClassinfo(), LinkEntity.CLASS_INFO_ENTITY);
                    utilsService.setStudentKeyValue(list, list.getPkStudent(), LinkEntity.STUDENT_ENTITY);
                    utilsService.setSysUserKeyValue(list, list.getCreator(), LinkEntity.CREATOR_ENTITY);
                    utilsService.setSysUserKeyValue(list, list.getModifier(), LinkEntity.MODIFIER_ENTITY);
                }
            }

            response.setData(studentShiftList);
            //response.setTotal(studentShiftMapper.countBy(studentShift));

        }
        return response;
    }

    @Override
    public Response save(Request<StudentShift> tParams) {
        Response response = Response.newResponse();
        StudentShift studentShift = tParams.getData();

        if(studentShift== null){
            return  response.PARAMS_ISNULL();
        }
        String primaryKey = studentShift.getPkStudentShift();
        StudentShift select = null;
        if (primaryKey != null && !primaryKey.equals("")) {
            select = studentShiftMapper.selectByPrimaryKey(primaryKey);
        }
        String message= "";
        try {
            if (select != null) {
                studentShift.setLasteditDate(new Date());
                studentShiftMapper.updateByPrimaryKeySelective(studentShift);
            } else {
                studentShift.setCreationDate(new Date());
                studentShift.setLasteditDate(new Date());
                studentShift.setCreator(tParams.getCurrentUser());
                studentShift.setModifier(tParams.getCurrentUser());
                studentShift.setIsvalid(1);
                studentShiftMapper.insertSelective(studentShift);

                ClassinfoStudent classinfoStudent = new ClassinfoStudent();
                classinfoStudent.setPkStudent(studentShift.getPkStudent());
                classinfoStudent.setPkClassinfo(studentShift.getPkParentClassinfo());
                classinfoStudentMapper.deleteByPrimaryKey(classinfoStudent);

                classinfoStudent.setPkClassinfo(studentShift.getPkClassinfo());
                classinfoStudent.setIsvalid(1);
                classinfoStudent.setCreator(tParams.getCurrentUser());
                classinfoStudent.setModifier(tParams.getCurrentUser());
                classinfoStudent.setCreationDate(new Date());
                classinfoStudent.setLasteditDate(new Date());
                classinfoStudentMapper.insertSelective(classinfoStudent);
            }
        }catch (Exception ex){
            message = ex.getMessage();
            return response.SAVE_FAIL(message);
        }
        return response;
    }
}
