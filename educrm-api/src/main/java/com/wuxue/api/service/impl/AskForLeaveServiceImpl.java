package com.wuxue.api.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wuxue.api.mapper.*;
import com.wuxue.api.service.AskForLeaveService;
import com.wuxue.api.service.UtilsService;
import com.wuxue.api.utils.GuidUtils;
import com.wuxue.model.*;
import com.wuxue.model.shuttle.RelayControl;
import com.wuxue.utils.contract.Light;
import com.wuxue.utils.contract.LinkEntity;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service("askForLeaveService")
public class AskForLeaveServiceImpl implements AskForLeaveService {
    @Autowired
    AskForLeaveMapper askForLeaveMapper;

    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    StudentMapper studentMapper;

    @Autowired
    SysDictValuesMapper sysDictValuesMapper;
    @Autowired
    private UtilsService utilsService;
    @Autowired
    private AskLeaveStudentMapper askLeaveStudentMapper;

    @Override
    public Response find(Request<AskForLeave> tParams) {
        Response response = Response.newResponse();
        AskForLeave askForLeave = tParams.getData();
        if (askForLeave == null) {
            return response.PARAMS_ISNULL();
        }
        if (askForLeave.getPkAskForLeave() != null && askForLeave.getPkAskForLeave() != "") {
            AskForLeave byPrimaryKey = askForLeaveMapper.selectByPrimaryKey(askForLeave.getPkAskForLeave());
//            if(byPrimaryKey.getTypes().equals("0")){
//                Employee employee=employeeMapper.selectByPrimaryKey(byPrimaryKey.getPkStudentEmployee());
//                if(employee!=null){
//                    byPrimaryKey.put("empCaption",employee.getCaption());
//                }
//            }else{
//                Student student=studentMapper.selectByPrimaryKey(byPrimaryKey.getPkStudentEmployee());
//                if(student!=null){
//                    byPrimaryKey.put("stuCaption",student.getCaption());
//                }
//            }
            response.setData(byPrimaryKey);
        } else {
            PageHelper.startPage(askForLeave.getPageNo(), askForLeave.getPageSize());
            List<AskForLeave> askForLeaveList = askForLeaveMapper.select(askForLeave);
            PageInfo page = new PageInfo(askForLeaveList);
            if (askForLeaveList.size() > 0) {
                for (AskForLeave askF : askForLeaveList) {
                    if (askF.getTypes().equals("1")) { //学生
                        SysDictValues sysDictValues = sysDictValuesMapper.selectByPrimaryKey(askF.getPkSubject());
                        if (sysDictValues != null) {
                            askF.put("sysDictValues", sysDictValues);
                        }
                    }
                }
            }
//            if(askForLeaveList.size()>0){
//                for (AskForLeave askF: askForLeaveList) {
//                    if(askF.getTypes().equals("0")){
//                        Employee employee=employeeMapper.selectByPrimaryKey(askF.getPkStudentEmployee());
//                        if(employee!=null){
//                         askF.put("empCaption",employee.getCaption());
//                        }
//                    }else{
//                        Student student=studentMapper.selectByPrimaryKey(askF.getPkStudentEmployee());
//                        if(student!=null){
//                            askF.put("stuCaption",student.getCaption());
//                        }
//                    }
//                }
//
//            }

            response.setData(askForLeaveList);
            response.setTotal(page.getTotal());
        }

        return response;
    }

    @Override
    public Response save(Request<AskForLeave> tParams) {
        Response response = Response.newResponse();
        AskForLeave askForLeave = tParams.getData();
        if (askForLeave == null) {
            return response.PARAMS_ISNULL();
        }
        int iReuslt = 1;
        String message = "";
        AskForLeave select = null;
        if (askForLeave.getPkAskForLeave() != null) {
            select = askForLeaveMapper.selectByPrimaryKey(askForLeave.getPkAskForLeave());
        }
        try {
            if (select != null) {
                iReuslt = askForLeaveMapper.updateByPrimaryKeySelective(askForLeave);
            } else {
                askForLeave.setPkAskForLeave(GuidUtils.getGuid());
                askForLeave.setCreationDate(new Date());
                askForLeave.setLasteditDate(new Date());
                iReuslt = askForLeaveMapper.insertSelective(askForLeave);
            }

        } catch (Exception ex) {
            message = ex.getMessage();
            return response.SAVE_FAIL(message);
        }
        if (iReuslt > 0) {
            return response;
        }
        return response.SAVE_FAIL(message);
    }


    @Override
    public List<AskForLeave> findByPar(AskForLeave askForLeave) {
        List<AskForLeave> select = askForLeaveMapper.select(askForLeave);
//        if (select.size()>0){
//            for (AskForLeave forLeave : select) {
//                utilsService.setStudentKeyValue(forLeave,forLeave.getPkStudentEmployee(), LinkEntity.STUDENT_ENTITY);
//            }
//        }
        return select;
    }

    @Override
    public AskForLeave findByKey(String pkAskForLeave) {
        AskForLeave select = askForLeaveMapper.selectByPrimaryKey(pkAskForLeave);

        if (select != null) {
            List<AskLeaveStudent> askLeaveStudentList = askLeaveStudentMapper.selectByPkAskForLeave(select.getPkAskForLeave());

            List<Map> list = new ArrayList();

            if (askLeaveStudentList.size() > 0) {
                for (AskLeaveStudent askLeaveStudent : askLeaveStudentList) {
                    Student student = studentMapper.selectByPrimaryKey(askLeaveStudent.getPkStudent());
                    if (student != null) {
                        Map<String, Object> map = new HashMap<>();

                        map.put("id", student.getPkStudent());
                        map.put("code", student.getStudentId());
                        map.put("img", student.getImg());
                        map.put("caption", student.getCaption());
                        list.add(map);
                    }
                }
            }

            select.put(Light.STUDENT_LIST, list);
        }


        return select;
    }

    @Override
    public Response deleteByLeaveId(String pkAskForLeave) {
        Response response = Response.newResponse();

        String message = "";
        int iReuslt = 0;
        if (pkAskForLeave == null || "".equals(pkAskForLeave)) {
            return response.PARAMS_ISNULL();
        }
        try {

            AskForLeave askForLeave = askForLeaveMapper.selectByPrimaryKey(pkAskForLeave);
            if (askForLeave == null) {
                return response.SYSAUTOCODE_ISNULL();
            }
            askForLeave.setIsaudit(3);
            askForLeaveMapper.updateByPrimaryKeySelective(askForLeave);
        } catch (Exception ex) {
            message = ex.getMessage();
            return response.DELETE_FAIL(message);
        }

        if (iReuslt > 0) {
            return response;
        }

        return response.DELETE_FAIL(message);
    }

    @Override
    public Response applyLeaveSubmit(AskForLeave askForLeave, List<String> studentList) {

        Response response = Response.newResponse();

        String message = "";

        if (askForLeave == null) {
            return response.PARAMS_ISNULL();
        }
        String primaryKey = askForLeave.getPkAskForLeave();

        AskForLeave select = null;

        if (primaryKey != null && !"".equals(primaryKey)) {
            select = askForLeaveMapper.selectByPrimaryKey(primaryKey);
        }
        int iResult = 0;


        try {
            if (select != null) {
                askForLeave.setLasteditDate(new Date());
                iResult = askForLeaveMapper.updateByPrimaryKeySelective(askForLeave);
            } else {
                if (primaryKey == null || "".equals(primaryKey)) {
                    askForLeave.setPkAskForLeave(GuidUtils.getGuid());
                }
                askForLeave.setCreator(askForLeave.getPkLinkman());
                askForLeave.setModifier(askForLeave.getPkLinkman());
                askForLeave.setCreationDate(new Date());
                askForLeave.setLasteditDate(new Date());
                askForLeave.setIsvalid(1);
                askForLeave.setIsaudit(0);
                iResult = askForLeaveMapper.insertSelective(askForLeave);
                if (iResult > 0 && studentList.size() > 0) {
                    for (String s : studentList) {
                        AskLeaveStudent askLeaveStudent = new AskLeaveStudent();
                        askLeaveStudent.setPkStudentLeave(GuidUtils.getGuid());
                        askLeaveStudent.setPkAskForLeave(askForLeave.getPkAskForLeave());
                        askLeaveStudent.setPkLinkman(askForLeave.getPkLinkman());
                        askLeaveStudent.setPkStudent(s);
                        iResult = askLeaveStudentMapper.insertSelective(askLeaveStudent);
                    }
                }
                if (iResult > 0) {
                    RelayControl relayControl = new RelayControl();
                    relayControl.setPkDomain("1");
                    relayControl.setPkRelayControl(GuidUtils.getGuid());
                    relayControl.setPersonId(askForLeave.getPkLinkman());
                    relayControl.setStatus(0);
                    relayControl.setType(0);
                    relayControl.setNotice(askForLeave.getRemark());
                }
            }
        } catch (Exception ex) {
            message = ex.getMessage();
            return response.SAVE_FAIL(message);
        }


        if (iResult > 0) {
            return response;
        }
        return response.SAVE_FAIL(message);
    }
}
