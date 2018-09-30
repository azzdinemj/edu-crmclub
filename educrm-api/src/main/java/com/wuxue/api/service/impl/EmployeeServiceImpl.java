package com.wuxue.api.service.impl;

import com.fasterxml.jackson.databind.util.StdDateFormat;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wuxue.api.mapper.*;
import com.wuxue.api.service.SysAutoCodeService;
import com.wuxue.api.service.UtilsService;
import com.wuxue.model.*;
import com.wuxue.api.service.EmployeeService;
import com.wuxue.api.utils.GuidUtils;
import com.wuxue.utils.common.ArrifRepeat;
import com.wuxue.utils.common.DataUtils;
import com.wuxue.utils.common.SmsUtil;
import com.wuxue.utils.contract.Light;
import com.wuxue.utils.contract.LinkEntity;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import com.wuxue.utils.https.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

//import com.wuxue.api.utils.GuidUtils;

@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    EmployeeMapper employeeMapper;
    @Autowired
    EmployeeCertificateMapper employeeCertificateMapper;
    @Autowired
    EmployeeHomeInfoMapper employeeHomeInfoMapper;
    @Autowired
    EmployeeJobExpMapper employeeJobExpMapper;
    @Autowired
    UtilsService utilsService;
    @Autowired
    SysAutoCodeService sysAutoCodeService;
    @Autowired
    private SysDictValuesMapper sysDictValuesMapper;

    @Autowired
    StudentMapper studentMapper;

    @Autowired
    ScheduleMapper scheduleMapper;
    @Autowired
    ProductMapper productMapper;
    @Autowired
    ClassRoomMapper classRoomMapper;
    @Autowired
    WxOpenidMapper wxOpenidMapper;
    @Autowired
    DatavalidMapper datavalidMapper;
    @Autowired
    LinkmanMapper linkmanMapper;
    @Autowired
    StudentLinkmanMapper studentLinkmanMapper;
    @Autowired
    WxUnionidMapper wxUnionidMapper;
    @Autowired
    ClassinfoStudentMapper classinfoStudentMapper;
    @Autowired
    ClassinfoMapper classinfoMapper;
    @Autowired
    DivisionGradeMapper divisionGradeMapper;

    @Override
    public Response delete(Request<Employee> tParams) {
        Response response = Response.newResponse();
        Employee employee = tParams.getData();
        if (employee == null) {
            return response.PARAMS_ISNULL();
        }
        int iReuslt = 1;
        String message = "";
        try {
            employee.setIsvalid(0);
            iReuslt = employeeMapper.updateByPrimaryKeySelective(employee);
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
    public Response find(Request<Employee> tParams) {
        Response response = Response.newResponse();
        Employee employee = tParams.getData();

        if (employee == null) {
            return response.PARAMS_ISNULL();
        }
        String primaryKey = employee.getPkEmployee();
        if (primaryKey != null && !primaryKey.equals("")) {
            Employee byPrimaryKey = employeeMapper.selectByPrimaryKey(primaryKey);
            if (byPrimaryKey != null) {

//            获取员工家庭资料
                EmployeeHomeInfo employeeHomeInfo = new EmployeeHomeInfo();
                employeeHomeInfo.setPkEmployee(byPrimaryKey.getPkEmployee());
                List<EmployeeHomeInfo> employeeHomeInfoList = employeeHomeInfoMapper.select(employeeHomeInfo);

//            获取员工证书
                EmployeeCertificate employeeCertificate = new EmployeeCertificate();
                employeeCertificate.setPkEmployeeCertificate(byPrimaryKey.getPkEmployee());
                List<EmployeeCertificate> employeeCertificateList = employeeCertificateMapper.select(employeeCertificate);

//            获取员工工作经历资料
                EmployeeJobExp employeeJobExp = new EmployeeJobExp();
                employeeJobExp.setPkEmployee(byPrimaryKey.getPkEmployee());
                List<EmployeeJobExp> employeeJobExpList = employeeJobExpMapper.select(employeeJobExp);

                //获取老师任教课程信息
                employee.setIsvalid(1);
                List<SysDictValues> sysDictValues1 = sysDictValuesMapper.selectCourse(employee);
                byPrimaryKey.put(Light.EMPLOYEE_COURSE, sysDictValues1);


                byPrimaryKey.put(Light.EMPLOYEE_JOB_EXP, employeeJobExpList);
                byPrimaryKey.put(Light.EMPLOYEE_HOME_INFO, employeeHomeInfoList);
                byPrimaryKey.put(Light.EMPLOYEE_CERTIFICATE, employeeCertificateList);
            } else {
                byPrimaryKey = new Employee();
            }
            utilsService.setSysUserKeyValue(byPrimaryKey, byPrimaryKey.getCreator(), LinkEntity.CREATOR_ENTITY);
            utilsService.setSysUserKeyValue(byPrimaryKey, byPrimaryKey.getModifier(), LinkEntity.MODIFIER_ENTITY);
            utilsService.setSysDictKeyValue(byPrimaryKey, byPrimaryKey.getNation(), LinkEntity.NATION_ENTITY);
            utilsService.setSysDictKeyValue(byPrimaryKey, byPrimaryKey.getJobPost(), LinkEntity.JOBPOST_ENTITY);
            utilsService.setDepartmentKeyValue(byPrimaryKey, byPrimaryKey.getPkDepartment(), LinkEntity.DEPARTMENT_ENTITY);

            response.setData(byPrimaryKey);
        } else {
            PageHelper.startPage(employee.getPageNo(), employee.getPageSize());
            List<Employee> employeeList = employeeMapper.select(employee);
            PageInfo pageInfo = new PageInfo(employeeList);
            response.setTotal(pageInfo.getTotal());
            if (employeeList.size() > 0) {
                for (Employee list : employeeList) {
                    utilsService.setSysUserKeyValue(list, list.getCreator(), LinkEntity.CREATOR_ENTITY);
                    utilsService.setSysUserKeyValue(list, list.getModifier(), LinkEntity.MODIFIER_ENTITY);
                    utilsService.setSysDictKeyValue(list, list.getNation(), LinkEntity.NATION_ENTITY);
                    utilsService.setSysDictKeyValue(list, list.getJobPost(), LinkEntity.JOBPOST_ENTITY);
                    utilsService.setDepartmentKeyValue(list, list.getPkDepartment(), LinkEntity.DEPARTMENT_ENTITY);
                }
            }
            response.setData(employeeList);
            //response.setTotal(employeeMapper.countBy(employee));

        }
        return response;
    }

    @Override
    public Response save(Request<Employee> tParams) {
        Response response = Response.newResponse();
        Employee employee = tParams.getData();

        if (employee == null) {
            return response.PARAMS_ISNULL();
        }
        String primaryKey = employee.getPkEmployee();
        Employee select = null;
        if (primaryKey != null && !primaryKey.equals("")) {
            select = employeeMapper.selectByPrimaryKey(primaryKey);
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        int iReuslt = 1;
        String message = "";
        try {
            if (select != null) {
                employee.setLasteditDate(new Date());
                iReuslt = employeeMapper.updateByPrimaryKeySelective(employee);

//            删除员工家庭资料关联
                EmployeeHomeInfo employeeHomeInfo = new EmployeeHomeInfo();
                employeeHomeInfo.setPkEmployee(primaryKey);
                List<EmployeeHomeInfo> employeeHomeInfos = employeeHomeInfoMapper.select(employeeHomeInfo);
                for (EmployeeHomeInfo homeInfo : employeeHomeInfos) {
                    employeeHomeInfoMapper.deleteByPrimaryKey(homeInfo.getPkEmployeeHomeInfo());
                }

//            删除员工证书
                EmployeeCertificate employeeCertificate = new EmployeeCertificate();
                employeeCertificate.setPkEmployeeCertificate(primaryKey);
                List<EmployeeCertificate> employeeCertificates = employeeCertificateMapper.select(employeeCertificate);
                for (EmployeeCertificate certificate : employeeCertificates) {
                    employeeCertificateMapper.deleteByPrimaryKey(certificate.getPkEmployeeCertificate());
                }
//            删除员工工作经历资料
                EmployeeJobExp employeeJobExp = new EmployeeJobExp();
                employeeJobExp.setPkEmployee(primaryKey);
                List<EmployeeJobExp> employeeJobExps = employeeJobExpMapper.select(employeeJobExp);
                for (EmployeeJobExp jobExp : employeeJobExps) {
                    employeeJobExpMapper.deleteByPrimaryKey(jobExp.getPkEmployeeJobExp());
                }

            } else {
                employee.setPkEmployee(GuidUtils.getGuid());
                employee.setCreationDate(new Date());
                employee.setLasteditDate(new Date());
                if (employee.getCode() == null || "".equals(employee.getCode())){
                    employee.setCode(sysAutoCodeService.getCode("employee"));
                }
                if (employee.getEnterdates() != null && !employee.getEnterdates().equals("")) {
                    employee.setEnterdate(sdf.parse(employee.getEnterdates()));
                }
//                employee.setIsvalid(1);
                employee.setKind(1);
                employee.setLeavedate(null);
                employee.setIsvalid(1);
                iReuslt = employeeMapper.insertSelective(employee);
            }

//            获取传的教职工绑定信息
            Map<String, Object> map = employee.getMap();
            if (map != null) {

                List<EmployeeCertificate> employeeCertificateList = DataUtils.objectToList(map.get(Light.EMPLOYEE_CERTIFICATE), EmployeeCertificate.class);
                List<EmployeeHomeInfo> employeeHomeInfoList = DataUtils.objectToList(map.get(Light.EMPLOYEE_HOME_INFO), EmployeeHomeInfo.class);
                List<EmployeeJobExp> employeeJobExpList = DataUtils.objectToList(map.get(Light.EMPLOYEE_JOB_EXP), EmployeeJobExp.class);


//            新增教职工证书资料
                if (employeeCertificateList != null && employeeCertificateList.size() > 0) {
                    for (EmployeeCertificate employeeCertificate : employeeCertificateList) {
                        employeeCertificate.setPkEmployee(employee.getPkEmployee());
                        employeeCertificate.setCreationDate(new Date());
                        employeeCertificate.setLasteditDate(new Date());
//                        employeeCertificate.setPkEmployeeCertificate(GuidUtils.getGuid());
                        employeeCertificateMapper.insertSelective(employeeCertificate);
                    }
                }


//            新增教职工家庭资料
                if (employeeHomeInfoList != null && employeeHomeInfoList.size() > 0) {
                    for (EmployeeHomeInfo employeeHomeInfo : employeeHomeInfoList) {
//                        employeeHomeInfo.setPkEmployeeHomeInfo(GuidUtils.getGuid());
                        employeeHomeInfo.setPkEmployee(employee.getPkEmployee());
                        employeeHomeInfo.setCreationDate(new Date());
                        employeeHomeInfo.setLasteditDate(new Date());
                        employeeHomeInfoMapper.insertSelective(employeeHomeInfo);
                    }
                }

//            新增教职工工作经历资料
                if (employeeJobExpList != null && employeeJobExpList.size() > 0) {
                    for (EmployeeJobExp employeeJobExp : employeeJobExpList) {
//                        employeeJobExp.setPkEmployeeJobExp(GuidUtils.getGuid());
                        employeeJobExp.setPkEmployee(employee.getPkEmployee());
                        employeeJobExp.setCreationDate(new Date());
                        employeeJobExp.setLasteditDate(new Date());
                        employeeJobExpMapper.insertSelective(employeeJobExp);
                    }
                }
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
    public Response quit(Request<Employee> tParams) {
        Response response = Response.newResponse();
        Employee employee = tParams.getData();

        if (employee == null) {
            return response.PARAMS_ISNULL();
        }

        int iReuslt = 1;
        String message = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (employee.getPkEmployee() != null && employee.getLeavedate() != null && employee.getPkEmployee() != null) {
            Employee byPrimaryKey = employeeMapper.selectByPrimaryKey(employee.getPkEmployee());
            byPrimaryKey.setKind(2);
            byPrimaryKey.setLasteditDate(new Date());
            try {
                byPrimaryKey.setLeavedate(sdf.parse(byPrimaryKey.getLeavedates()));
                employeeMapper.updateByPrimaryKeySelective(byPrimaryKey);
            } catch (Exception ex) {
                message = ex.getMessage();
                return response.SAVE_FAIL(message);
            }
        } else {
            return response.PARAMS_ISNULL();
        }
        if (iReuslt > 0) {
            return response;
        }
        return response.SAVE_FAIL(message);
    }

    /**
     * 根据电话查找
     *
     * @param tParams
     * @return
     */
    @Override
    public Response findEmployeeByPhone(Request<String> tParams) {
        Response response = Response.newResponse();
        String primaryPhone = tParams.getData();
        if (primaryPhone == null) {
            return response.PARAMS_ISNULL();
        }
        String message;
        try {
            Employee employee = new Employee();
            employee.setMobilePhone(primaryPhone);
            employee.setIsvalid(1);//未删除
            employee.setIsleave(1);//未离职
            List<Employee> studentList = employeeMapper.select(employee);
            if (studentList.size() > 0) {
                response.setData(studentList.get(0));
            } else {
                response.setData(null);
            }
        } catch (Exception ex) {
            message = ex.getMessage();
            return Response.newResponse().REGISTER_FAIL(message);
        }
        return response;
    }

    /**
     * 中航油老师列表
     *
     * @param tParams
     * @return
     */
    @Override
    public Response findzhyou(Request<Employee> tParams) {
        Response response = Response.newResponse();
        Employee student = tParams.getData();
        if (student == null) {
            return response.PARAMS_ISNULL();
        }
        if (student.getPkEmployee() != null && student.getPkEmployee() != "") {
            Employee byPrimaryKey = employeeMapper.selectByPrimaryKey(student.getPkEmployee());
            response.setData(byPrimaryKey);
        } else {
            PageHelper.startPage(student.getPageNo(), student.getPageSize());
            List<Employee> studentList = employeeMapper.select(student);
            PageInfo<Employee> pageInfo = new PageInfo(studentList);
            response.setData(pageInfo);
        }

        return response;
    }


    @Override
    public Response savezhyou(Request<Employee> tParams) {
        Response response = Response.newResponse();
        Employee employee = tParams.getData();

        if (employee == null) {
            return response.PARAMS_ISNULL();
        }
        String primaryKey = employee.getPkEmployee();
        Employee select = null;
        if (primaryKey != null && !primaryKey.equals("")) {
            select = employeeMapper.selectByPrimaryKey(primaryKey);
        }
        int iReuslt = 1;
        String message = "";
        try {
            if (select != null) {
                employee.setLasteditDate(new Date());
                iReuslt = employeeMapper.updateByPrimaryKeySelective(employee);
            } else {
                employee.setPkEmployee(GuidUtils.getGuid());
                employee.setCreationDate(new Date());
                employee.setLasteditDate(new Date());
                iReuslt = employeeMapper.insertSelective(employee);
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


    /**
     * jiedian  返回老师详情页
     *
     * @param tParams
     * @return
     */
    @Override
    public Response findjiedian(Request<Employee> tParams) {
        Response response = Response.newResponse();
        Employee employee = tParams.getData();
        if (employee == null) {
            return response.PARAMS_ISNULL();
        }
        if (employee.getPkEmployee() != null && employee.getPkEmployee() != "") {
            Employee byPrimaryKey = employeeMapper.selectByPrimaryKey(employee.getPkEmployee());
            List<Student> studentList = new ArrayList<>();

            //老师的所有学生
            Schedule schedule = new Schedule();
            schedule.setPkEmployee(byPrimaryKey.getPkEmployee());
            List<Schedule> list = scheduleMapper.select(schedule);

            if (list.size() > 0) {
                //学生去重
                Object[] strarr = new Object[60];
                for (int i = 0; i < list.size(); i++) {
                    strarr[i] = list.get(i).getPkStudent();
                }
                Object[] stra = ArrifRepeat.unique(strarr);

                for (Object str : stra) {
                    Student stu = studentMapper.selectByPrimaryKey((String) str);
                    if (stu != null) {
                        studentList.add(stu);
                    }
                }
                byPrimaryKey.put(Light.EMP_STU, studentList);
            }

            //老师的所有课程
            Schedule schedule2 = new Schedule();
            schedule2.setPkEmployee(byPrimaryKey.getPkEmployee());
            List<Schedule> listschedul = scheduleMapper.select(schedule2);

            if (listschedul.size() > 0) {
                for (Schedule lists : listschedul) {
                    Student student = studentMapper.selectByPrimaryKey(lists.getPkStudent());
                    if (student != null) {
                        lists.put(Light.STUDENTOBJ, student.getCaption());
                        lists.put("Img", student.getImg());
                    } else {
                        lists.put(Light.STUDENTOBJ, null);
                    }
                    Product product = productMapper.selectByPrimaryKey(lists.getPkProduct());
                    if (product != null) {
                        lists.put(Light.PRODUCTOBJ, product.getCaption());
                    } else {
                        lists.put(Light.PRODUCTOBJ, null);
                    }
                    ClassRoom classRoom = classRoomMapper.selectByPrimaryKey(lists.getPkClassRoom());
                    if (classRoom != null) {
                        lists.put(Light.CLASSROOMOBJ, classRoom.getCaption());
                    } else {
                        lists.put(Light.CLASSROOMOBJ, null);
                    }
                }
            }

            byPrimaryKey.put(Light.EMP_SCH, listschedul);

            response.setData(byPrimaryKey);
        } else {
//            PageHelper.startPage(student.getPageNo(), student.getPageSize());
//            List<Employee> studentList = employeeMapper.select(student);
//            PageInfo<Employee> pageInfo = new PageInfo(studentList);
//            response.setData(pageInfo);
        }

        return response;
    }

    @Override
    public Response selectBy(Request<Employee> tParams) {
        Response response = Response.newResponse();
        Employee employee = tParams.getData();

        if (employee == null) {
            return response.PARAMS_ISNULL();
        }

        List<Employee> employeeList = employeeMapper.select(employee);
        if (employeeList.size() > 0) {
            for (Employee list : employeeList) {
                utilsService.setSysUserKeyValue(list, list.getCreator(), LinkEntity.CREATOR_ENTITY);
                utilsService.setSysUserKeyValue(list, list.getModifier(), LinkEntity.MODIFIER_ENTITY);
                utilsService.setSysDictKeyValue(list, list.getNation(), LinkEntity.NATION_ENTITY);
                utilsService.setSysDictKeyValue(list, list.getJobPost(), LinkEntity.JOBPOST_ENTITY);
                utilsService.setDepartmentKeyValue(list, list.getPkDepartment(), LinkEntity.DEPARTMENT_ENTITY);
            }
        }
        response.setData(employeeList);

        return response;
    }

    @Override
    public Employee selectByUser(String sysUser) {
        Employee employee = employeeMapper.selectByUser(sysUser);
        return employee;
    }

    @Override
    public Response selectOpenid(Request<WxOpenid> tParams) {
        Response response = Response.newResponse();
        WxOpenid wxOpenid = tParams.getData();

        if (wxOpenid == null && wxOpenid.getType() == null) {
            return response.PARAMS_ISNULL();
        }

        //先查询 该openid 是否已经存在
        List<WxOpenid> wxOpenidList = wxOpenidMapper.select(wxOpenid);
        if (wxOpenidList.size() == 0) {
            return response.OPENID_NULL();
        }

        if (wxOpenid.getType() == 0) {
            Employee employee = employeeMapper.selectByPrimaryKey(wxOpenidList.get(0).getPkEmpstu());
            if (employee == null) {
                return response.USER_NULL();
            }
            response.setData(employee);
        } else {
            Linkman linkman = linkmanMapper.selectByPrimaryKey(wxOpenidList.get(0).getPkEmpstu());
            StudentLinkmanKey studentLinkmanKey = new StudentLinkmanKey();
            studentLinkmanKey.setPkLinkman(linkman.getPkLinkman());
            List<StudentLinkmanKey> linkmanKeyList = studentLinkmanMapper.select(studentLinkmanKey);
            List<Student> studentList = new ArrayList<>();
            if (linkmanKeyList.size() > 0) {
                for (StudentLinkmanKey linkmanKey : linkmanKeyList) {
                    Student student = studentMapper.selectByPrimaryKey(linkmanKey.getPkStudent());

                    ClassinfoStudent classinfoStudent = new ClassinfoStudent();
                    classinfoStudent.setPkStudent(linkmanKey.getPkStudent());
                    List<ClassinfoStudent> classinfoStudentList = classinfoStudentMapper.select(classinfoStudent);
                    if (classinfoStudentList.size() > 0) {
                        for (ClassinfoStudent classinfoStudent1 : classinfoStudentList) {
                            Classinfo classinfo = classinfoMapper.selectByPrimaryKey(classinfoStudent1.getPkClassinfo());
                            if(classinfo != null && classinfo.getDivision() != null && classinfo.getType() == 0){
                                DivisionGrade divisionGrade = divisionGradeMapper.selectByPrimaryKey(Integer.valueOf(classinfo.getGrade()));
                                student.put("grades",divisionGrade.getSort());
                            }
                        }
                    }
                    studentList.add(student);
                }
            } else {
                return response.USER_NULL();
            }

            linkman.put(Light.STUDENT_LIST, studentList);
            response.setData(linkman);
        }
        return response;
    }

    @Override
    public Response getPhoneCode(Request<Employee> tParams) {
        Response response = Response.newResponse();
        Employee employee = tParams.getData();

        if (employee == null && employee.getPhone() == null && employee.getPhone().equals("")) {
            return response.PARAMS_ISNULL();
        }

        Response response1 = SmsUtil.sendMessage(employee.getPhone());
        if (response1.getCode() == 0) {
            Datavalid datavalid = new Datavalid();
            datavalid.setId(GuidUtils.getGuid());
            datavalid.setType(1);
            datavalid.setPhone(employee.getPhone());
            datavalid.setPkempstu(response1.getData().toString());
            datavalid.setCreateDate(new Date());
            datavalidMapper.insertSelective(datavalid);
        }

        return response1;
    }

    @Override
    public Response bindWx(Request<WxOpenid> tParams) {
        Response response = Response.newResponse();
        WxOpenid wxOpenid = tParams.getData();

        if (wxOpenid == null && wxOpenid.getType() == null) {
            return response.PARAMS_ISNULL();
        }

        try {
            //判断验证码是否正确
            Datavalid datavalid1 = new Datavalid();
            datavalid1.setType(1);
            datavalid1.setPkempstu(wxOpenid.getCode());
            datavalid1.setPhone(wxOpenid.getPhone());
            List<Datavalid> datavalidList = datavalidMapper.select(datavalid1);

            if (datavalidList.size() == 0) {
                return response.PHONE_CODE_FAIL();
            }

            //wxOpenid.setType(1);
            //判断是否查询到教职工 是（返回教师信息） 否（查询学生）
            if (wxOpenid.getType() == 1) {

                //判断是否查询到家长  是（返回学生信息）  否（提示报错信息）
                Linkman linkman = new Linkman();
                linkman.setPhone(wxOpenid.getPhone());
                List<Linkman> linkmanList = linkmanMapper.selectByPhoneAndIdCard(linkman);
                if (linkmanList.size() == 0) {
                    return response.USER_NULL();
                } else {
                    linkman = linkmanList.get(0);
                    wxOpenid.setPkEmpstu(linkman.getPkLinkman());

                    StudentLinkmanKey studentLinkmanKey = new StudentLinkmanKey();
                    studentLinkmanKey.setPkLinkman(linkmanList.get(0).getPkLinkman());
                    List<StudentLinkmanKey> linkmanKeyList = studentLinkmanMapper.select(studentLinkmanKey);
                    List<Student> studentList = new ArrayList<>();
                    if (linkmanKeyList.size() > 0) {
                        for (StudentLinkmanKey linkmanKey : linkmanKeyList) {
                            Student student = studentMapper.selectByPrimaryKey(linkmanKey.getPkStudent());
                            studentList.add(student);
                        }
                    }

                    linkman.put(Light.STUDENT_LIST, studentList);
                    response.setData(linkman);
                }

            } else {
                Employee employee = new Employee();
                employee.setPhone(wxOpenid.getPhone());
                List<Employee> employeeList = employeeMapper.select(employee);
                if (employeeList.size() == 0) {
                    return response.USER_NULL();
                } else {
                    wxOpenid.setPkEmpstu(employeeList.get(0).getPkEmployee());
                    response.setData(employeeList.get(0));
                }

            }
        } catch (Exception ex) {
            return response.SAVE_FAIL(ex.getMessage());
        }

        //绑定微信号
        WxOpenid wx = new WxOpenid();
        wx.setOpenid(wxOpenid.getOpenid());
        wx.setType(Integer.valueOf(wxOpenid.getType()));
        List<WxOpenid> wxOpenidList = wxOpenidMapper.select(wx);
        if (wxOpenidList.size() == 0) {
            wxOpenid.setId(GuidUtils.getGuid());
            wxOpenidMapper.insertSelective(wxOpenid);
        } else {
            return response.OPENID_DOUBLE();
        }

//        绑定unionid
        WxUnionid wxUnionid = new WxUnionid();
        wxUnionid.setUnionid(wxOpenid.getUnionid());
        List<WxUnionid> select = wxUnionidMapper.select(wxUnionid);
        wxUnionid.setXcxOpenid(wxOpenid.getOpenid());
        if (select.size() > 0) {
            wxUnionid.setPkUnionid(select.get(0).getPkUnionid());
            wxUnionidMapper.updateByPrimaryKeySelective(wxUnionid);
        } else {
            wxUnionid.setPkUnionid(GuidUtils.getGuid());
            wxUnionidMapper.insertSelective(wxUnionid);
        }

        return response;
    }

    @Override
    public Response getOpenid(Request<WxOpenid> tParams) {
        Response response = Response.newResponse();
        WxOpenid wxOpenid = tParams.getData();

        if (wxOpenid == null && wxOpenid.getCode() == null && wxOpenid.getCode().equals("")) {
            return response.PARAMS_ISNULL();
        }
        String sign = "appid=" + wxOpenid.getAppid() + "&secret=" + wxOpenid.getSecret() + "&grant_type=authorization_code&js_code=" + wxOpenid.getCode();
        String string = HttpRequest.sendGet("https://api.weixin.qq.com/sns/jscode2session", sign);

        response.setData(string);
        return response;
    }

    @Override
    public Employee findById(String pkEmployee) {
        Employee employee = employeeMapper.selectByPrimaryKey(pkEmployee);

        return employee;
    }

    @Override
    public Response getCode() {
        Response response = Response.newResponse();

        String code = employeeMapper.getCode();
        int i = Integer.parseInt(code);
        String str = String.format("%06d", i+1);
        response.setData(str);

        return response;
    }

    @Override
    public Response findForMap(Request<Employee> tParm) {
        Response response = Response.newResponse();
        Employee data = tParm.getData();
        List<Map<String,Object>> map = employeeMapper.selectForMap(data);

        response.setData(map);


        return response;
    }


}
