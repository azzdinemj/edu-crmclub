package com.wuxue.api.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wuxue.api.mapper.*;
import com.wuxue.api.service.ClassinfoStudentService;
import com.wuxue.api.service.SysAutoCodeService;
import com.wuxue.api.service.UtilsService;
import com.wuxue.api.utils.GuidUtils;
import com.wuxue.api.utils.StudentUtiles;
import com.wuxue.model.*;
import com.wuxue.utils.contract.Light;
import com.wuxue.utils.contract.LinkEntity;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

//import com.wuxue.api.utils.GuidUtils;

@Service("classinfoStudentService")
public class ClassinfoStudentServiceImpl implements ClassinfoStudentService{
    @Autowired
    ClassinfoStudentMapper classinfoStudentMapper;
    @Autowired
    StudentSignupMapper studentSignupMapper;
    @Autowired
    StudentMapper studentMapper;
    @Autowired
    UtilsService utilsService;
    @Autowired
    private SysAutoCodeService sysAutoCodeService;
    @Autowired
    private StudentPhaseRecordMapper studentPhaseRecordMapper;
    @Autowired
    private StudentLinkmanMapper studentLinkmanMapper;
    @Autowired
    private LinkmanMapper linkmanMapper;
    @Autowired
    private ClassinfoMapper classinfoMapper;
    @Autowired
    private StudentUtiles studentUtiles;

    @Override
    public Response delete(Request<ClassinfoStudentKey> tParams) {
        Response response = Response.newResponse();
        ClassinfoStudentKey classinfoStudentKey = tParams.getData();

        if(classinfoStudentKey.getPkClassinfo()== null || classinfoStudentKey.getPkClassinfo().equals("") || classinfoStudentKey.getPkStudent()== null || classinfoStudentKey.getPkStudent().equals("")){
            return  response.PARAMS_ISNULL();
        }
        int iReuslt = 1;
        String message= "";
        try {
            iReuslt=classinfoStudentMapper.deleteByPrimaryKey(classinfoStudentKey);
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
    public Response find(Request<ClassinfoStudent> tParams) {
        Response response = Response.newResponse();
        ClassinfoStudent classinfoStudent = tParams.getData();

        if(classinfoStudent== null){
            return  response.PARAMS_ISNULL();
        }
        String primaryKey = classinfoStudent.getPkClassinfo();
        if(primaryKey !=null && !primaryKey.equals("")){
            ClassinfoStudent byPrimaryKey = classinfoStudentMapper.selectByPrimaryKey(classinfoStudent);
            utilsService.setSysUserKeyValue(byPrimaryKey,byPrimaryKey.getCreator(), LinkEntity.CREATOR_ENTITY);
            utilsService.setSysUserKeyValue(byPrimaryKey,byPrimaryKey.getModifier(),LinkEntity.MODIFIER_ENTITY);
            response.setData(byPrimaryKey);
        }else{
            PageHelper.startPage(classinfoStudent.getPageNo(), classinfoStudent.getPageSize());
            List<ClassinfoStudent> studentList = classinfoStudentMapper.select(classinfoStudent);
            PageInfo page = new PageInfo(studentList);
            response.setTotal(page.getTotal());
            if (studentList.size() > 0) {
                for (ClassinfoStudent list : studentList) {
                    utilsService.setSysUserKeyValue(list, list.getCreator(), LinkEntity.CREATOR_ENTITY);
                    utilsService.setSysUserKeyValue(list, list.getModifier(), LinkEntity.MODIFIER_ENTITY);
                }
            }
            response.setData(studentList);
            //response.setTotal(classinfoStudentMapper.countBy(classinfoStudent));
        }
        return response;
    }

    @Override
    public Response save(Request<ClassinfoStudent> tParams) {
        Response response = Response.newResponse();
        ClassinfoStudent classinfoStudent = tParams.getData();
//        Map<String, Object> map = classinfoStudent.getMap();
//        List<ClassinfoStudent> list = DataUtils.objectToList(map.get(Light.CLASSINFO_STUENT), ClassinfoStudent.class);

        if(classinfoStudent== null && classinfoStudent.getPkStudent() == null && classinfoStudent.getPkClassinfo() == null && classinfoStudent.getPkStudentSignup() == null){
            return  response.PARAMS_ISNULL();
        }

        ClassinfoStudent student = new ClassinfoStudent();
        student.setPkStudent(classinfoStudent.getPkStudent());
        student.setPkClassinfo(classinfoStudent.getPkClassinfo());
        ClassinfoStudent select = classinfoStudentMapper.selectByPrimaryKey(student);
        /*String primaryKey1 = classinfoStudent.getPkClassinfo();
        String primaryKey2 = classinfoStudent.getPkStudent();
        ClassinfoStudent select = null;
        if (primaryKey1 != null && !primaryKey1.equals("") && primaryKey2 != null && !primaryKey2.equals("")) {
            ClassinfoStudentKey classinfoStudentKey = new ClassinfoStudentKey();
            classinfoStudentKey.setPkStudent(primaryKey2);
            classinfoStudentKey.setPkClassinfo(primaryKey1);
            select = classinfoStudentMapper.selectByPrimaryKey(classinfoStudentKey);
        }*/
        int iReuslt = 0;
        String message= "";
        try {
            if (select != null) {
                classinfoStudent.setCreator(null);
                classinfoStudent.setLasteditDate(new Date());
                classinfoStudentMapper.updateByPrimaryKeySelective(classinfoStudent);
            }else {
                ClassinfoStudent student1 = new ClassinfoStudent();
                student1.setPkStudent(classinfoStudent.getPkStudent());
                student1.setIsvalid(1);
                List<ClassinfoStudent> classinfoStudentList = classinfoStudentMapper.selectPkClassinfo(student1);

                classinfoStudent.setCreationDate(new Date());
                classinfoStudent.setLasteditDate(new Date());
                classinfoStudentMapper.insertSelective(classinfoStudent);
                if(classinfoStudentList.size() ==0){
                    //把学生阶段信息存储(升班)
                    StudentPhaseRecord studentPhaseRecord = new StudentPhaseRecord();
                    studentPhaseRecord.setPkDomain(tParams.getCurrendDomain());
                    studentPhaseRecord.setCode(sysAutoCodeService.getCode("phaserecordcode"));
                    studentPhaseRecord.setPkStudentPhaseRecord(GuidUtils.getGuid());
                    studentPhaseRecord.setPkClassinfo(classinfoStudent.getPkClassinfo());
                    studentPhaseRecord.setType(Light.ENTRANCE_CODE);
                    studentPhaseRecord.setStageTime(new Date());
                    studentPhaseRecordMapper.insertSelective(studentPhaseRecord);
                }

            }

            StudentSignup studentSignup = studentSignupMapper.selectByPrimaryKey(classinfoStudent.getPkStudentSignup());
            studentSignup.setPkClassinfo(classinfoStudent.getPkClassinfo());
            studentSignup.setModifier(classinfoStudent.getModifier());
            studentSignup.setLasteditDate(new Date());
            studentSignup.setStatus(2);
            studentSignupMapper.updateByPrimaryKeySelective(studentSignup);

            Student selectByPrimaryKey = studentMapper.selectByPrimaryKey(classinfoStudent.getPkStudent());
            selectByPrimaryKey.setIstype(1);
            selectByPrimaryKey.setModifier(classinfoStudent.getModifier());
            selectByPrimaryKey.setLasteditDate(new Date());
            studentMapper.updateByPrimaryKeySelective(selectByPrimaryKey);
           /* StudentSignup studentSignup = new StudentSignup();
            classinfo.setPkClassinfo(classinfoStudent.getPkClassinfo());
            classinfoStudent1.setLasteditDate(new Date());
            classinfoStudent1.setCreator(tParams.getCurrentUser());
            classinfoStudent1.setModifier(tParams.getCurrentUser());
            for (ClassinfoStudent classinfoStudent1 : list){
                classinfoStudent1.setCreationDate(new Date());
                classinfoStudent1.setLasteditDate(new Date());
                classinfoStudent1.setCreator(tParams.getCurrentUser());
                classinfoStudent1.setModifier(tParams.getCurrentUser());
                iReuslt = classinfoStudentMapper.insertSelective(classinfoStudent1);
            }
            if (select != null) {
                classinfoStudent.setLasteditDate(new Date());
                iReuslt = classinfoStudentMapper.updateByPrimaryKeySelective(classinfoStudent);
            } else {
                classinfoStudent.setPkClassinfo(GuidUtils.getGuid());
                classinfoStudent.setCreationDate(new Date());
                classinfoStudent.setLasteditDate(new Date());
                classinfoStudent.setCreator(tParams.getCurrentUser());
                classinfoStudent.setModifier(tParams.getCurrentUser());
                iReuslt = classinfoStudentMapper.insertSelective(classinfoStudent);
            }*/
        }catch (Exception ex){
            message = ex.getMessage();
            return response.SAVE_FAIL(message);
        }
        return response;
    }

    /**
     * 学生转班
     * @param tParams
     * @return
     */
    @Override
    public Response studentReturnClass(Request<ClassinfoStudent> tParams) {
        Response response = Response.newResponse();
        ClassinfoStudent classinfoStudent = tParams.getData();
        String oldPkClassinfo = String.valueOf(classinfoStudent.get(Light.OLD_PK_CLASSINFO));
        if(classinfoStudent== null ||oldPkClassinfo==null || "".equals(oldPkClassinfo)){
            return  response.PARAMS_ISNULL();
        }
        int iReuslt = 0;
        String message= "";
        try {
            ClassinfoStudent oldclassinfoStudent = new ClassinfoStudent();
            oldclassinfoStudent.setPkClassinfo(oldPkClassinfo);
            oldclassinfoStudent.setPkStudent(classinfoStudent.getPkStudent());
            oldclassinfoStudent.setIsvalid(1);
            iReuslt = classinfoStudentMapper.updateByPrimaryKeySelective(oldclassinfoStudent);
            classinfoStudent.setCreationDate(new Date());
            classinfoStudent.setLasteditDate(new Date());
            iReuslt = classinfoStudentMapper.insertSelective(classinfoStudent);

        }catch (Exception ex){
            message = ex.getMessage();
            return response.SAVE_FAIL(message);
        }
        if(iReuslt>0) {
            return response;
        }
        return response.SAVE_FAIL(message);
    }

    @Override
    public List<String> getStudentIdsByClassinfoIds(List<String> classIds) {
        return classinfoStudentMapper.selectStudentIdsByClassinfoIds(classIds);
    }

    @Override
    public Response deleteByClassAndStu(Request<String> tParams) {
        Response response = Response.newResponse();
        String pkClassinfo = tParams.getData();
        String message = "";

        if (pkClassinfo == null || "".equals(pkClassinfo)){
            return response.PARAMS_ISNULL();
        }
        try {

            String pkStudent = studentUtiles.getPkStudent(tParams.getCurrentUser());

            ClassinfoStudent classinfoStudent = new ClassinfoStudent();
            classinfoStudent.setPkClassinfo(pkClassinfo);
            classinfoStudent.setPkStudent(pkStudent);
            ClassinfoStudent classinfoStudent1 = classinfoStudentMapper.selectByPrimaryKey(classinfoStudent);
            classinfoStudent1.setIsvalid(0);
            classinfoStudentMapper.updateByPrimaryKeySelective(classinfoStudent1);

            Classinfo classinfo = classinfoMapper.selectByPrimaryKey(pkClassinfo);
            classinfo.setExistingNumbers(classinfo.getExistingNumbers()-1);
            classinfoMapper.updateByPrimaryKeySelective(classinfo);


        }catch (Exception ex){
            message = ex.getMessage();
            return response.DELETE_FAIL(message);
        }

        return response;
    }
}
