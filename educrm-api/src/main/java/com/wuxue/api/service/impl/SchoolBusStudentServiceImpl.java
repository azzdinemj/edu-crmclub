package com.wuxue.api.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wuxue.api.mapper.*;
import com.wuxue.api.service.SchoolBusStudentService;
import com.wuxue.api.service.SysAutoCodeService;
import com.wuxue.api.service.UtilsService;
import com.wuxue.api.utils.GuidUtils;
import com.wuxue.model.*;
import com.wuxue.utils.common.DataUtils;
import com.wuxue.utils.common.DateUtils;
import com.wuxue.utils.contract.Light;
import com.wuxue.utils.contract.LinkEntity;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("schoolBusStudentService")
public class SchoolBusStudentServiceImpl implements SchoolBusStudentService {

    @Autowired
    private SchoolBusMapper schoolBusMapper;
    @Autowired
    private SchoolBusStudentMapper schoolBusStudentMapper;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private SysAutoCodeService sysAutoCodeService;
    @Autowired
    private ReceivableMapper receivableMapper;
    @Autowired
    private PayablesMapper payablesMapper;
    @Autowired
    private TaskMapper taskMapper;
    @Autowired
    private UtilsService utilsService;


    @Override
    public Response delete(Request<SchoolBusStudent> tParams) {
        Response response = Response.newResponse();
        SchoolBusStudent schooBusStudent = tParams.getData();
        if (schooBusStudent == null) {

        }
        int iReuslt = 0;
        String message = "";
        try {
            String primaryKey = schooBusStudent.getPkSchooBusStudent();
            if (primaryKey == null || "".equals(primaryKey)) {
                return response.PARAMS_ISNULL();
            }
            SchoolBusStudent select = schoolBusStudentMapper.selectByPrimaryKey(primaryKey);
            SchoolBus schoolBus = schoolBusMapper.selectByPrimaryKey(select.getPkSchoolBus());
            if (select != null) {
                select.setIsvalid(0);
                select.setLasteditDate(new Date());
                select.setExpireDate(new Date());
                select.setModifier(schooBusStudent.getModifier());
                iReuslt = schoolBusStudentMapper.updateByPrimaryKeySelective(select);
                if (iReuslt == 1) {
                    //生成退费单
                    Payables payables = new Payables();
                    payables.setPkDomain(select.getPkDomain());
                    payables.setPkPayables(GuidUtils.getGuid());
                    payables.setCode(Light.PAYABLES_TCODE);
                    payables.setPkStudent(select.getPkStudent());
                    payables.setDate(new Date());
                    payables.setCost(schoolBus.getExpenses());
                    payables.setNotes("校车退费");
                    payables.setCreator(schooBusStudent.getModifier());
                    payables.setModifier(schooBusStudent.getModifier());
                    payables.setCreationDate(new Date());
                    payables.setLasteditDate(new Date());
                    iReuslt = payablesMapper.insertSelective(payables);

                    //删除学生校车号
                    Student student = studentMapper.selectByPrimaryKey(select.getPkStudent());
                    student.setSchoolBus(null);
                    iReuslt = studentMapper.updateByPrimaryKey(student);

                    //校车人数改变
                    if (schoolBus != null) {
                        schoolBus.setCurrentNum(schoolBus.getCurrentNum() - 1);
                        iReuslt = schoolBusMapper.updateByPrimaryKeySelective(schoolBus);
                    }
                    //生成任务
                    Task task = new Task();
                    task.setPkTask(GuidUtils.getGuid());
                    task.setContent("学生校车退费");
                    task.setEndDate(DateUtils.getAfterDate(3, new Date()));
                    task.setType(0);
                    task.setCreator(schooBusStudent.getModifier());
                    task.setModifier(schooBusStudent.getModifier());
                    task.setCreationDate(new Date());
                    task.setLasteditDate(new Date());
                    taskMapper.insertSelective(task);


                }
            }

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
    public Response find(Request<SchoolBusStudent> tParams) {
        Response response = Response.newResponse();
        SchoolBusStudent schoolBusStudent = tParams.getData();
        if (schoolBusStudent == null) {
            return response.PARAMS_ISNULL();
        }
//        String primaryKey = schoolBusStudent.getPkSchooBusStudent();
        String primaryKey = schoolBusStudent.getPkSchoolBus();
        if (primaryKey != null && !"".equals(primaryKey)) {
            schoolBusStudent.setIsvalid(1);
            PageHelper.startPage(schoolBusStudent.getPageNo(), schoolBusStudent.getPageSize());
            List<SchoolBusStudent> select = schoolBusStudentMapper.select(schoolBusStudent);
            PageInfo pageInfo = new PageInfo(select);
            response.setTotal(pageInfo.getTotal());
            if (select.size() > 0) {
                for (SchoolBusStudent busStudent : select) {
                    Student student = studentMapper.selectByPrimaryKey(busStudent.getPkStudent());
                    if (student != null) {
                        utilsService.setSysDictKeyValue(student, student.getGrade(), LinkEntity.GTADE_ENTITY);
                        utilsService.setSysDictKeyValue(student, student.getNation(), LinkEntity.NATION_ENTITY);
                        busStudent.put(LinkEntity.STUDENT_ENTITY, student);
                    }

                }

            }

            response.setData(select);


        }

        return response;
    }

    @Override
    public Response save(Request<SchoolBusStudent> tParams) {

        Response response = new Response();
        SchoolBusStudent schoolBusStudent = tParams.getData();

        if (schoolBusStudent == null) {
            return response.PARAMS_ISNULL();
        }
        String primaryKey = schoolBusStudent.getPkSchoolBus();
        SchoolBus select = null;
        if (primaryKey != null && !primaryKey.equals("")) {
            select = schoolBusMapper.selectByPrimaryKey(primaryKey);
        }


        String message = "";
        int iReuslt = 0;


        //学生入住
        Map<String, Object> map = schoolBusStudent.getMap();
        if (map != null) {
            List<Student> students = DataUtils.objectToList(map.get(Light.STUDENT_LIST), Student.class);
            try {
                for (Student student : students) {
                    Student student1 = studentMapper.selectByPrimaryKey(student.getPkStudent());
                    if (student1 != null) {

                        if (student1.getIsSchoolrool() != null && student1.getIsSchoolrool() == 3 && (student1.getSchoolBus() == null || "".equals(student1.getSchoolBus()))) {
                            student1.setIsSchoolrool(1);
                            student1.setSchoolBus(select.getPkSchoolBus());
                            iReuslt = studentMapper.updateByPrimaryKeySelective(student1);
                        } else {
                            student1.setSchoolBus(select.getPkSchoolBus());
                            student1.setIsboarding(2);//设置学生状态
                            iReuslt = studentMapper.updateByPrimaryKeySelective(student1);

                            //生成应收单
                            Receivable receivable = new Receivable();
                            receivable.setPkReceivable(GuidUtils.getGuid());
                            receivable.setPkDomain(select.getPkDomain());
                            receivable.setCode(sysAutoCodeService.getCode(Light.RECEIVABLE_TCODE));
                            receivable.setCost(select.getExpenses());
                            receivable.setPkStudent(student1.getPkStudent());
                            receivable.setCreator(schoolBusStudent.getCreator());
                            receivable.setCreationDate(new Date());
                            receivable.setModifier(schoolBusStudent.getCreator());
                            receivable.setLasteditDate(new Date());
                            receivable.setCurrency("CNY");
                            receivable.setNotes("校车费");
                            iReuslt = receivableMapper.insertSelective(receivable);

                            //生成任务
                            Task task = new Task();
                            task.setPkTask(GuidUtils.getGuid());
                            task.setContent("学生校车缴费");
                            task.setEndDate(DateUtils.getAfterDate(3, new Date()));
                            task.setType(0);
                            task.setCreator(schoolBusStudent.getCreator());
                            task.setModifier(schoolBusStudent.getModifier());
                            task.setCreationDate(new Date());
                            task.setLasteditDate(new Date());
                            taskMapper.insertSelective(task);
                        }


                        //设置校车学生关系

                        schoolBusStudent.setPkStudent(student1.getPkStudent());
                        schoolBusStudent.setPkSchooBusStudent(GuidUtils.getGuid());
                        schoolBusStudent.setCreationDate(new Date());
                        schoolBusStudent.setLasteditDate(new Date());
                        schoolBusStudent.setCode(sysAutoCodeService.getCode(Light.SCHOOL_BUS_STUDENT_TCODE));
                        schoolBusStudent.setIsvalid(1);
                        schoolBusStudent.setDate(new Date());
                        iReuslt = schoolBusStudentMapper.insertSelective(schoolBusStudent);


                    }
                }
                Integer currentNum = select.getCurrentNum();
                if (currentNum == null) {
                    select.setCurrentNum(students.size());
                } else {
                    select.setCurrentNum(currentNum + students.size());
                }

                //反写校车入住人数
                iReuslt = schoolBusMapper.updateByPrimaryKeySelective(select);
                if (iReuslt > 0) {
                    return response;
                }
            } catch (Exception e) {
                message = e.getMessage();
            }
            return response.SAVE_FAIL(message);

        }
        return response.SAVE_FAIL(message);
    }

    @Override
    public Response audit(Request<SchoolBusStudent> tParams) {
        return null;
    }

    @Override
    public Response findAll(Request<SchoolBusStudent> tParams) {
        return null;
    }

    @Override
    public List<String> getStudentIdsByBusId(String busId) {
        SchoolBusStudent busStudent = new SchoolBusStudent();
        busStudent.setPkSchoolBus(busId);
        List<SchoolBusStudent> busStudents = schoolBusStudentMapper.select(busStudent);
        List<String> studentIds = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(busStudents)) {
            for (SchoolBusStudent student : busStudents) {
                studentIds.add(student.getPkStudent());
            }
        }
        return studentIds;
    }
}
