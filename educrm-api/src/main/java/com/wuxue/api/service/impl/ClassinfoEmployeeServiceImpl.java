package com.wuxue.api.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wuxue.api.mapper.ClassinfoEmployeeMapper;
import com.wuxue.api.mapper.ClassinfoMapper;
import com.wuxue.api.mapper.StudentMapper;
import com.wuxue.api.mapper.StudentSignupMapper;
import com.wuxue.api.service.ClassinfoEmployeeService;
import com.wuxue.api.service.UtilsService;
import com.wuxue.model.Classinfo;
import com.wuxue.model.ClassinfoEmployee;
import com.wuxue.utils.contract.LinkEntity;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service("classinfoEmployeeService")
public class ClassinfoEmployeeServiceImpl implements ClassinfoEmployeeService{
    @Autowired
    ClassinfoEmployeeMapper classinfoEmployeeMapper;
    @Autowired
    StudentSignupMapper studentSignupMapper;
    @Autowired
    StudentMapper studentMapper;
    @Autowired
    UtilsService utilsService;
    @Autowired
    ClassinfoMapper classinfoMapper;

    @Override
    public Response delete(Request<ClassinfoEmployee> tParams) {
        Response response = Response.newResponse();
        ClassinfoEmployee classinfoEmployee = tParams.getData();

        if(classinfoEmployee.getPkClassinfo()== null || classinfoEmployee.getPkClassinfo().equals("") || classinfoEmployee.getPkEmployee()== null || classinfoEmployee.getPkEmployee().equals("")){
            return  response.PARAMS_ISNULL();
        }
        String message= "";
        try {
            classinfoEmployee.setIsvalid(0);
            classinfoEmployee.setLasteditDate(new Date());

            classinfoEmployeeMapper.updateByPrimaryKeySelective(classinfoEmployee);
        }catch (Exception ex){
            message = ex.getMessage();
            return response.SAVE_FAIL(message);
        }
        return response;
    }

    @Override
    public Response find(Request<ClassinfoEmployee> tParams) {
        Response response = Response.newResponse();
        ClassinfoEmployee classinfoEmployee = tParams.getData();

        if(classinfoEmployee== null){
            return  response.PARAMS_ISNULL();
        }
        String PkEmployee = classinfoEmployee.getPkEmployee();
        String classinfo = classinfoEmployee.getPkClassinfo();
        if(classinfo !=null && !classinfo.equals("") && PkEmployee !=null && !PkEmployee.equals("")){
            ClassinfoEmployee byPrimaryKey = classinfoEmployeeMapper.selectByPrimaryKey(classinfoEmployee);
            utilsService.setSysUserKeyValue(byPrimaryKey,byPrimaryKey.getCreator(), LinkEntity.CREATOR_ENTITY);
            utilsService.setSysUserKeyValue(byPrimaryKey,byPrimaryKey.getModifier(),LinkEntity.MODIFIER_ENTITY);
            response.setData(byPrimaryKey);
        }else{
            List<ClassinfoEmployee> studentList = classinfoEmployeeMapper.select(classinfoEmployee);
            if (studentList.size() > 0) {
                for (ClassinfoEmployee list : studentList) {
                    utilsService.setEmployeeDataValue(list, list.getPkEmployee(), LinkEntity.EMP_ENTITY);
                    utilsService.setSysUserKeyValue(list, list.getCreator(), LinkEntity.CREATOR_ENTITY);
                    utilsService.setSysUserKeyValue(list, list.getModifier(), LinkEntity.MODIFIER_ENTITY);
                }
            }
            response.setData(studentList);
            //response.setTotal(classinfoEmployeeMapper.countBy(classinfoEmployee));
        }
        return response;
    }

    @Override
    public Response save(Request<ClassinfoEmployee> tParams) {
        Response response = Response.newResponse();
        ClassinfoEmployee classinfoEmployee = tParams.getData();

        if(classinfoEmployee== null ){
            return  response.PARAMS_ISNULL();
        }

        ClassinfoEmployee employee = new ClassinfoEmployee();
        employee.setPkEmployee(classinfoEmployee.getPkEmployee());
        employee.setPkClassinfo(classinfoEmployee.getPkClassinfo());
        ClassinfoEmployee select = classinfoEmployeeMapper.selectByPrimaryKey(employee);
        String message= "";
        try {
            if (select != null) {
                classinfoEmployee.setCreator(null);
                classinfoEmployee.setLasteditDate(new Date());
                classinfoEmployeeMapper.updateByPrimaryKeySelective(classinfoEmployee);
            }else {
                classinfoEmployee.setCreationDate(new Date());
                classinfoEmployee.setLasteditDate(new Date());
                classinfoEmployeeMapper.insertSelective(classinfoEmployee);
            }

        }catch (Exception ex){
            message = ex.getMessage();
            return response.SAVE_FAIL(message);
        }
        return response;
    }

    @Override
    public Response saveAll(Request<ClassinfoEmployee> tParams) {
        Response response = Response.newResponse();
        ClassinfoEmployee classinfoEmployee = tParams.getData();

        if(classinfoEmployee== null){
            return  response.PARAMS_ISNULL();
        }

        ClassinfoEmployee employee;
        String[] employeeList = classinfoEmployee.getPkEmployee().split(",");
        List<Classinfo> classinfoList = classinfoMapper.select(new Classinfo());


        String message= "";
        try {
            if (!employeeList.equals("")) {
                for (int i = 0; i < employeeList.length; i++) {
                    classinfoEmployee.setPkEmployee(employeeList[i]);
                    if(classinfoEmployee.getPkClassinfo() == null || classinfoEmployee.getPkClassinfo().equals("")) {
                        for (Classinfo classinfo : classinfoList) {
                            employee = new ClassinfoEmployee();
                            employee.setPkEmployee(employeeList[i]);
                            employee.setPkClassinfo(classinfo.getPkClassinfo());
                            ClassinfoEmployee select = classinfoEmployeeMapper.selectByPrimaryKey(employee);
                            if (select == null) {
                                classinfoEmployee.setCreationDate(new Date());
                                classinfoEmployee.setLasteditDate(new Date());
                                classinfoEmployee.setCreator(tParams.getCurrentUser());
                                classinfoEmployee.setModifier(tParams.getCurrentUser());
                                classinfoEmployee.setPkClassinfo(classinfo.getPkClassinfo());
                                classinfoEmployeeMapper.insertSelective(classinfoEmployee);
                            }
                        }
                    }else{
                        employee = new ClassinfoEmployee();
                        employee.setPkEmployee(employeeList[i]);
                        employee.setPkClassinfo(classinfoEmployee.getPkClassinfo());
                        ClassinfoEmployee select = classinfoEmployeeMapper.selectByPrimaryKey(employee);
                        if (select == null) {
                            classinfoEmployee.setCreationDate(new Date());
                            classinfoEmployee.setLasteditDate(new Date());
                            classinfoEmployee.setCreator(tParams.getCurrentUser());
                            classinfoEmployee.setModifier(tParams.getCurrentUser());
                            classinfoEmployeeMapper.insertSelective(classinfoEmployee);
                        }
                    }
                }
            }
        }catch (Exception ex){
            message = ex.getMessage();
            return response.SAVE_FAIL(message);
        }
        return response;
    }


    @Override
    public Response findTeacher(Request<ClassinfoEmployee> tParams) {
        Response response = Response.newResponse();
        ClassinfoEmployee classinfoEmployee = tParams.getData();

        String pkClassinfo = classinfoEmployee.getPkClassinfo();
        String pkEmployee = classinfoEmployee.getPkEmployee();
        if ((pkClassinfo == null || "".equals(pkClassinfo)) && (pkEmployee == null || "".equals(pkEmployee))){
            PageHelper.startPage(classinfoEmployee.getPageNo(), classinfoEmployee.getPageSize());
            List<ClassinfoEmployee> select = classinfoEmployeeMapper.selectTeacher(classinfoEmployee);
            PageInfo page = new PageInfo(select);
            response.setTotal(page.getTotal());
            if (select.size() >0){
                for (ClassinfoEmployee employee : select) {
                    utilsService.setEmployeeKeyValue(employee,employee.getPkEmployee(),LinkEntity.EMP_ENTITY);
                    utilsService.setClassInfoKeyValue(employee,employee.getPkClassinfo(),LinkEntity.CLASS_INFO_ENTITY);
                }
            }
            response.setData(select);
        }
        return response;
    }

    @Override
    public Response findClassinfoTeacher(Request<ClassinfoEmployee> tParams) {
        Response response = Response.newResponse();
        ClassinfoEmployee classinfoEmployee = tParams.getData();

//            PageHelper.startPage(classinfoEmployee.getPageNo(), classinfoEmployee.getPageSize());
            List<ClassinfoEmployee> select = classinfoEmployeeMapper.selectClassinfoTeacher(classinfoEmployee);
//            PageInfo page = new PageInfo(select);
//            response.setTotal(page.getTotal());
            if (select.size() >0){
                for (ClassinfoEmployee employee : select) {
                    utilsService.setEmployeeKeyValue(employee,employee.getPkEmployee(),LinkEntity.EMP_ENTITY);
                    utilsService.setClassInfoKeyValue(employee,employee.getPkClassinfo(),LinkEntity.CLASS_INFO_ENTITY);
                }
            }
            response.setData(select);
        return response;
    }

    /**
     * 查询老师名下班级
     * @param pkEmployee
     * @return
     */
    @Override
    public List<ClassinfoEmployee> selectByTea(String pkEmployee) {

        List<ClassinfoEmployee> list = classinfoEmployeeMapper.selectByTea(pkEmployee);

        if (list.size() > 0){
            for (ClassinfoEmployee classinfoEmployee : list) {

                utilsService.setClassInfoKeyValue(classinfoEmployee,classinfoEmployee.getPkClassinfo(),LinkEntity.CLASS_INFO_ENTITY);
            }
        }


        return list;
    }

    @Override
    public List<String> findClassInfoIdsByEmployeeId(String employeeId) {
        List<String> classInfoIds = classinfoEmployeeMapper.findClassInfoIdsByEmployeeId(employeeId);
        return classInfoIds;
    }


}
