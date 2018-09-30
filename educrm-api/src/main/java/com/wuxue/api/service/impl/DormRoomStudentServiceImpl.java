package com.wuxue.api.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wuxue.api.mapper.*;
import com.wuxue.api.service.DormRoomStudentService;
import com.wuxue.api.service.SysAutoCodeService;
import com.wuxue.api.service.UtilsService;
import com.wuxue.api.utils.GuidUtils;
import com.wuxue.base.KeyValue;
import com.wuxue.model.*;
import com.wuxue.utils.common.DataUtils;
import com.wuxue.utils.common.DateUtils;
import com.wuxue.utils.contract.Light;
import com.wuxue.utils.contract.LinkEntity;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
@Service("dormRoomStudentService")
public class DormRoomStudentServiceImpl implements DormRoomStudentService {

    @Autowired
    private DormRoomMapper dormRoomMapper;
    @Autowired
    private DormRoomStudentMapper dormRoomStudentMapper;
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
    public Response delete(Request<DormRoomStudent> tParams) {
        Response response = Response.newResponse();
        DormRoomStudent dormRoomStudent = tParams.getData();
        if (dormRoomStudent == null){
            return response.PARAMS_ISNULL();
        }
        int iReuslt = 0;
        String message = "";
        try {
            DormRoomStudent select = dormRoomStudentMapper.selectByPrimaryKey(dormRoomStudent.getPkDormStudent());
            DormRoom dormRoom = dormRoomMapper.selectByPrimaryKey(select.getPkDormRoom());
            if(select != null){
                select.setIsvalid(0);
                select.setLasteditDate(new Date());
                select.setExpireTime(new Date());
                select.setModifier(dormRoomStudent.getModifier());
                iReuslt = dormRoomStudentMapper.updateByPrimaryKeySelective(select);

                if (iReuslt == 1){
                    //生成退费单
                    Payables payables = new Payables();
                    payables.setPkDomain(select.getPkDomain());
                    payables.setPkPayables(GuidUtils.getGuid());
                    payables.setCode(Light.PAYABLES_TCODE);
                    payables.setPkStudent(select.getPkStudent());
                    payables.setDate(new Date());
                    payables.setCost(dormRoom.getExpenses());
                    payables.setNotes("宿舍退宿");
                    payables.setCreator(dormRoomStudent.getModifier());
                    payables.setModifier(dormRoomStudent.getModifier());
                    payables.setCreationDate(new Date());
                    payables.setLasteditDate(new Date());
                    iReuslt = payablesMapper.insertSelective(payables);

                    //删除学生宿舍号
                    Student student = studentMapper.selectByPrimaryKey(select.getPkStudent());
                    student.setDormRoom(null);
                    iReuslt = studentMapper.updateByPrimaryKey(student);

                    //宿舍人数改变
                    dormRoom.setCurrentNum(dormRoom.getCurrentNum()-1);
                    iReuslt=dormRoomMapper.updateByPrimaryKeySelective(dormRoom);
                    //生成任务
                    Task task = new Task();
                    task.setPkTask(GuidUtils.getGuid());
                    task.setContent("学生宿舍退费");
                    task.setEndDate(DateUtils.getAfterDate(3,new Date()));
                    task.setType(0);
                    task.setCreator(dormRoomStudent.getModifier());
                    task.setModifier(dormRoomStudent.getModifier());
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
    public Response save(Request<DormRoomStudent> tParams) {

        Response response = new Response();
        DormRoomStudent dormRoomStudent = tParams.getData();

        if (dormRoomStudent == null){
            return  response.PARAMS_ISNULL();
        }
        String primaryKey = dormRoomStudent.getPkDormRoom();
        DormRoom select = null;
        if (primaryKey != null && !primaryKey.equals("")) {
            select = dormRoomMapper.selectByPrimaryKey(primaryKey);
        }


        String message = "";
        int iReuslt = 0;


        //学生入住
        Map<String, Object> map = dormRoomStudent.getMap();
        if (map != null){
            List<Student> students = DataUtils.objectToList(map.get(Light.STUDENT_LIST), Student.class);
            try {
                for (Student student : students) {
                    Student student1 = studentMapper.selectByPrimaryKey(student.getPkStudent());
                    if (student1 != null){
                        //判断是否生成应收单
                        if(student1.getIsboarding() != null && student1.getIsboarding() ==3 && (student1.getDormRoom()==null || "".equals(student1.getDormRoom()))){
                            student1.setIsboarding(1);
                            student1.setDormRoom(select.getPkDormRoom());
                            iReuslt = studentMapper.updateByPrimaryKeySelective(student1);
                        }else {
                            student1.setDormRoom(select.getPkDormRoom());
                            student1.setIsboarding(2);//设置学生状态
                            iReuslt = studentMapper.updateByPrimaryKeySelective(student1);
                            //生成应收单
                            Receivable receivable = new Receivable();
                            receivable.setPkReceivable(GuidUtils.getGuid());
                            receivable.setPkDomain(select.getPkDomain());
                            receivable.setCode(sysAutoCodeService.getCode(Light.RECEIVABLE_TCODE));
                            receivable.setCost(select.getExpenses());
                            receivable.setPkStudent(student1.getPkStudent());
                            receivable.setCreator(dormRoomStudent.getCreator());
                            receivable.setCreationDate(new Date());
                            receivable.setModifier(dormRoomStudent.getCreator());
                            receivable.setLasteditDate(new Date());
                            receivable.setCurrency("CNY");
                            iReuslt = receivableMapper.insertSelective(receivable);
                            //生成任务
                            Task task = new Task();
                            task.setPkTask(GuidUtils.getGuid());
                            task.setContent("学生住宿缴费");
                            task.setEndDate(DateUtils.getAfterDate(3,new Date()));
                            task.setType(0);
                            task.setCreator(dormRoomStudent.getCreator());
                            task.setModifier(dormRoomStudent.getModifier());
                            task.setCreationDate(new Date());
                            task.setLasteditDate(new Date());
                            taskMapper.insertSelective(task);


                        }

                        //设置宿舍学生关系

                        dormRoomStudent.setPkStudent(student1.getPkStudent());
                        dormRoomStudent.setPkDormStudent(GuidUtils.getGuid());
                        dormRoomStudent.setCreationDate(new Date());
                        dormRoomStudent.setLasteditDate(new Date());
                        dormRoomStudent.setCode(sysAutoCodeService.getCode(Light.ROOMSTUDENTCODE_TCODE));
                        dormRoomStudent.setIsvalid(0);
                        dormRoomStudent.setDate(new Date());
                        iReuslt = dormRoomStudentMapper.insertSelective(dormRoomStudent);



                    }
                }
                Integer currentNum = select.getCurrentNum();
                if (currentNum == null){
                    select.setCurrentNum(students.size());
                }else {
                    select.setCurrentNum(currentNum+students.size());
                }

                //反写宿舍入住人数
                iReuslt = dormRoomMapper.updateByPrimaryKeySelective(select);
                if(iReuslt >0){
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
    public Response find(Request<DormRoomStudent> tParams) {
        Response response = Response.newResponse();
        DormRoomStudent dormRoomStudent = tParams.getData();
        if (dormRoomStudent == null){
            return response.PARAMS_ISNULL();
        }
//        String primaryKey = schoolBusStudent.getPkSchooBusStudent();
        String primaryKey = dormRoomStudent.getPkDormRoom();
        if (primaryKey != null &&!"".equals(primaryKey)){
            dormRoomStudent.setIsvalid(1);
            PageHelper.startPage(dormRoomStudent.getPageNo(),dormRoomStudent.getPageSize());
            List<DormRoomStudent> select = dormRoomStudentMapper.select(dormRoomStudent);
            PageInfo pageInfo = new PageInfo(select);
            response.setTotal(pageInfo.getTotal());
            if (select.size()>0){
                for (DormRoomStudent roomStudent : select) {
                    Student student = studentMapper.selectByPrimaryKey(roomStudent.getPkStudent());
                    if (student != null){
                        utilsService.setSysDictKeyValue(student,student.getGrade(), LinkEntity.GTADE_ENTITY);
                        utilsService.setSysDictKeyValue(student,student.getNation(),LinkEntity.NATION_ENTITY);

                    }else {
                        student = new Student();
                        student.put(LinkEntity.GTADE_ENTITY,new KeyValue());
                        student.put(LinkEntity.NATION_ENTITY,new KeyValue());
                    }
                    roomStudent.put(LinkEntity.STUDENT_ENTITY,student);

                }

            }

            response.setData(select);


        }

        return response;
    }
}
