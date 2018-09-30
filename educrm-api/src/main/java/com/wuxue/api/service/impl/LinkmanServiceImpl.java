package com.wuxue.api.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wuxue.api.mapper.EmployeeMapper;
import com.wuxue.api.mapper.LinkmanMapper;
import com.wuxue.api.mapper.StudentLinkmanMapper;
import com.wuxue.api.mapper.StudentMapper;
import com.wuxue.api.service.LinkmanService;
import com.wuxue.api.service.SysAutoCodeService;
import com.wuxue.api.service.UtilsService;
import com.wuxue.api.utils.GuidUtils;
import com.wuxue.model.*;
import com.wuxue.utils.contract.LinkEntity;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.*;

//import com.wuxue.api.utils.GuidUtils;

@Service("linkmanService")
public class LinkmanServiceImpl implements LinkmanService{
    @Autowired
    LinkmanMapper linkmanMapper;
    @Autowired
    UtilsService utilsService;
    @Autowired
    StudentLinkmanMapper studentLinkmanMapper;
    @Autowired
    StudentMapper studentMapper;
    @Autowired
    EmployeeMapper employeeMapper;
    @Autowired
    SysAutoCodeService sysAutoCodeService;

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
            iReuslt=linkmanMapper.deleteByPrimaryKey(primaryKey);
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
    public Response find(Request<Linkman> tParams) {
        Response response = Response.newResponse();
        Linkman linkman = tParams.getData();

        if(linkman== null){
            return  response.PARAMS_ISNULL();
        }
        String primaryKey = linkman.getPkLinkman();
        if(primaryKey !=null && !primaryKey.equals("")){
            Linkman byPrimaryKey = linkmanMapper.selectByPrimaryKey(primaryKey);
            utilsService.setSysUserKeyValue(byPrimaryKey,byPrimaryKey.getCreator(), LinkEntity.CREATOR_ENTITY);
            utilsService.setSysUserKeyValue(byPrimaryKey,byPrimaryKey.getModifier(),LinkEntity.MODIFIER_ENTITY);
            response.setData(byPrimaryKey);
        }else{
            PageHelper.startPage(linkman.getPageNo(),linkman.getPageSize());
            List<Linkman> linkmanList = linkmanMapper.select(linkman);
            PageInfo page = new PageInfo(linkmanList);
            response.setTotal(page.getTotal());
            if (linkmanList.size() > 0) {
                for (Linkman list : linkmanList) {
                    utilsService.setSysUserKeyValue(list, list.getCreator(), LinkEntity.CREATOR_ENTITY);
                    utilsService.setSysUserKeyValue(list, list.getModifier(), LinkEntity.MODIFIER_ENTITY);
                }
            }
            response.setData(linkmanList);
            //response.setTotal(linkmanMapper.countBy(linkman));

        }
        return response;
    }

    @Override
    @Transactional
    public Response save(Request<Linkman> tParams) {
        Response response = Response.newResponse();
        Linkman linkman = tParams.getData();

        if(linkman== null){
            return  response.PARAMS_ISNULL();
        }
        String primaryKey = linkman.getPkLinkman();
        Linkman select = null;
        if (primaryKey != null && !primaryKey.equals("")) {
            select = linkmanMapper.selectByPrimaryKey(primaryKey);
        }
        int iReuslt = 1;
        String message= "";
        try {
            if (select != null) {
                linkman.setLasteditDate(new Date());
                iReuslt = linkmanMapper.updateByPrimaryKeySelective(linkman);
            } else {
                linkman.setPkLinkman(GuidUtils.getGuid());
                linkman.setCode(sysAutoCodeService.getCode("linkman"));
                linkman.setCreationDate(new Date());
                linkman.setLasteditDate(new Date());
                iReuslt = linkmanMapper.insertSelective(linkman);
            }

            if(linkman.getPkStudent() != null && linkman.getPkLinkman() != null && !linkman.getPkStudent().equals("") && !linkman.getPkLinkman().equals("")){
                StudentLinkmanKey studentLinkmanKey = new StudentLinkmanKey();
                studentLinkmanKey.setPkLinkman(linkman.getPkLinkman());
                studentLinkmanKey.setPkStudent(linkman.getPkStudent());
                List<StudentLinkmanKey> linkmanKeys = studentLinkmanMapper.select(studentLinkmanKey);
                if(linkmanKeys.size() == 0){
                    studentLinkmanMapper.insertSelective(studentLinkmanKey);
                }
            }else{
                return  response.PARAMS_ISNULL();
            }
        }catch (Exception ex){
            message = ex.getMessage();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return response.SAVE_FAIL(message);
        }
        if(iReuslt>0) {
            return response;
        }
        return response.SAVE_FAIL(message);
    }

    @Override
    public Response selectStudentList(Linkman linkman) {
        Response response = Response.newResponse();
//        Linkman linkman = tParams.getData();

        if(linkman== null){
            return  response.PARAMS_ISNULL();
        }

        String message;
        try {
            StudentLinkmanKey studentLinkmanKey = new StudentLinkmanKey();
            studentLinkmanKey.setPkLinkman(linkman.getPkLinkman());
            List<StudentLinkmanKey> linkmanKeys = studentLinkmanMapper.select(studentLinkmanKey);
            List<Student> studentList = new ArrayList<>();
            if (linkmanKeys.size() > 0) {
                for (StudentLinkmanKey linkmanKey : linkmanKeys) {
                    Student student = studentMapper.selectByPrimaryKey(linkmanKey.getPkStudent());
                    studentList.add(student);
                }
            }
            response.setData(studentList);
        }catch (Exception ex){
            message = ex.getMessage();
            return response.FIND_FAIL(message);
        }

        return response;
    }

    @Override
    public Response getStudentLinkMansInfo(Request<String> request) {
        Response response =Response.newResponse();
        String studentId = request.getData();
        if (null == studentId || "".equals(studentId)){
            return response.PARAMS_ISNULL();
        }
        //获取家长信息
        List<Linkman> linkmans = linkmanMapper.selectByStudent(studentId);
        //获取班主任信息
        Employee employee = employeeMapper.findEmployeeByPkStudent(studentId);

        Map<String,Object> linkmanMap =new HashMap<>();
        linkmanMap.put("家长信息",linkmans);
        linkmanMap.put("班主任信息",employee);
        response.setData(linkmanMap);
        return response;
    }

    @Override
    public Response findByStudent(Request<Linkman> tParams) {
        Response response = Response.newResponse();
        Linkman linkman = tParams.getData();

        if(linkman.getPkStudent()== null){
            return  response.PARAMS_ISNULL();
        }

        StudentLinkmanKey studentLinkmanKey = new StudentLinkmanKey();
        studentLinkmanKey.setPkStudent(linkman.getPkStudent());
        List<StudentLinkmanKey> select = studentLinkmanMapper.select(studentLinkmanKey);
        List<Linkman> linkmanList = new ArrayList<>();
        if (select.size()>0) {
            for (StudentLinkmanKey linkmanKey : select) {
                Linkman key = linkmanMapper.selectByPrimaryKey(linkmanKey.getPkLinkman());
                linkmanList.add(key);
            }
        }

        response.setData(linkmanList);
        return response;
    }
}
