package com.wuxue.api.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wuxue.api.mapper.*;
import com.wuxue.api.service.UtilsService;
import com.wuxue.model.*;
import com.wuxue.api.service.DormRoomService;
import com.wuxue.api.utils.GuidUtils;
import com.wuxue.utils.common.DataUtils;
import com.wuxue.utils.contract.Light;
import com.wuxue.utils.contract.LinkEntity;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Service("dormRoomService")
public class DormRoomServiceImpl implements DormRoomService{
    @Autowired
    DormRoomMapper dormRoomMapper;
    @Autowired
    UtilsService utilsService;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private DormRoomStudentMapper dormRoomStudentMapper;
    @Autowired
    private DormRoomEmployeeMapper dormRoomEmployeeMapper;
    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private DormRoomHousemasterMapper dormRoomHousemasterMapper;

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
            DormRoom dormRoom = new DormRoom();
            dormRoom.setIsvalid(0);
            dormRoom.setPkDormRoom(primaryKey);
            iReuslt=dormRoomMapper.updateByPrimaryKeySelective(dormRoom);
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
    public Response find(Request<DormRoom> tParams) {
        Response response = Response.newResponse();
        DormRoom dormRoom = tParams.getData();

        if(dormRoom== null){
            return  response.PARAMS_ISNULL();
        }
        String primaryKey = dormRoom.getPkDormRoom();
        if(primaryKey !=null && !primaryKey.equals("")){
            DormRoom byPrimaryKey = dormRoomMapper.selectByPrimaryKey(primaryKey);
            //学生信息
            List<Student> studentList = new ArrayList<>();
            List<Employee> employeeList = new ArrayList<>();
            if (byPrimaryKey.getKind() !=null &&byPrimaryKey.getKind()==0){
                DormRoomStudent roomStudent = new DormRoomStudent();
                roomStudent.setPkDormRoom(byPrimaryKey.getPkDormRoom());
                roomStudent.setIsvalid(1);
                List<DormRoomStudent> dormRoomStudents = dormRoomStudentMapper.selectByDSPrimaryKey(roomStudent);
                if(dormRoomStudents != null && dormRoomStudents.size()>0){
                    for (DormRoomStudent dormRoomStudent : dormRoomStudents) {
                        Student student = studentMapper.selectByPrimaryKey(dormRoomStudent.getPkStudent());
                        if(student != null){
                            utilsService.setSysDictKeyValue(student, student.getGrade(), LinkEntity.GTADE_ENTITY);
                            utilsService.setSysDictKeyValue(student, student.getNation(), LinkEntity.NATION_ENTITY);
                            student.put(Light.DORMROOM,dormRoomStudent.getPkDormStudent());
                            studentList.add(student);
                        }
                    }
                }
            }
            //老师信息
            if (byPrimaryKey.getKind() !=null &&byPrimaryKey.getKind()==1){
                DormRoomEmployee roomEmployee = new DormRoomEmployee();
                roomEmployee.setPkDormRoom(byPrimaryKey.getPkDormRoom());
                roomEmployee.setIsvalid(1);
                List<DormRoomEmployee> dormRoomEmployees = dormRoomEmployeeMapper.selectByREPrimaryKey(roomEmployee);
                if(dormRoomEmployees != null && dormRoomEmployees.size()>0){
                    for (DormRoomEmployee dormRoomEmployee : dormRoomEmployees) {

                        Employee employee = employeeMapper.selectByPrimaryKey(dormRoomEmployee.getPkEmployee());
                        if (employee != null){
                            utilsService.setSysDictKeyValue(employee, employee.getNation(), LinkEntity.NATION_ENTITY);
                            employee.put(Light.DORMROOM,dormRoomEmployee.getPkDormEmployee());
                            employeeList.add(employee);
                        }
                    }
                }

            }

            //宿管老师信息
            String employeeIds = dormRoomHousemasterMapper.selectByDormRoom(byPrimaryKey.getPkDormRoom());
            byPrimaryKey.put(Light.EMPLOYEE_LIFE,employeeIds);

            byPrimaryKey.put(Light.STUDENT_LIST,studentList);
            byPrimaryKey.put(Light.EMPLOYEE,employeeList);
            utilsService.setSysUserKeyValue(byPrimaryKey,byPrimaryKey.getCreator(), LinkEntity.CREATOR_ENTITY);
            utilsService.setSysUserKeyValue(byPrimaryKey,byPrimaryKey.getModifier(),LinkEntity.MODIFIER_ENTITY);
            response.setData(byPrimaryKey);
        }else{
            PageHelper.startPage(dormRoom.getPageNo(),dormRoom.getPageSize());
            List<DormRoom> dormRoomList = dormRoomMapper.select(dormRoom);
            PageInfo page = new PageInfo(dormRoomList);
            response.setTotal(page.getTotal());
            if (dormRoomList.size() > 0) {
                for (DormRoom list : dormRoomList) {
                    utilsService.setSysUserKeyValue(list, list.getCreator(), LinkEntity.CREATOR_ENTITY);
                    utilsService.setSysUserKeyValue(list, list.getModifier(), LinkEntity.MODIFIER_ENTITY);
                }
            }

            response.setData(dormRoomList);
            //response.setTotal(dormRoomMapper.countBy(dormRoom));

        }
        return response;
    }

    @Override
    public Response save(Request<DormRoom> tParams) {
        Response response = Response.newResponse();
        DormRoom dormRoom = tParams.getData();


        if(dormRoom== null){
            return  response.PARAMS_ISNULL();
        }
        String primaryKey = dormRoom.getPkDormRoom();
        DormRoom select = null;
        if (primaryKey != null && !primaryKey.equals("")) {
            select = dormRoomMapper.selectByPrimaryKey(primaryKey);
        }
        int iReuslt = 0;
        String message= "";
        try {
            if (select != null) {
                dormRoom.setLasteditDate(new Date());
                iReuslt = dormRoomMapper.updateByPrimaryKeySelective(dormRoom);
            } else {
                dormRoom.setPkDormRoom(GuidUtils.getGuid());
                dormRoom.setCreationDate(new Date());
                dormRoom.setLasteditDate(new Date());
                dormRoom.setIsvalid(1);
                iReuslt = dormRoomMapper.insertSelective(dormRoom);
            }
            Map map = dormRoom.getMap();
            if (map != null){
                dormRoomHousemasterMapper.deleteByRoomId(dormRoom.getPkDormRoom());
                List<String> pkEmployees = DataUtils.objectToList(map.get(Light.EMPLOYEE),String.class);
                if (pkEmployees != null && pkEmployees.size()>0){
                    for (String pkEmployee : pkEmployees) {
                        DormRoomHousemaster dormRoomHousemaster = new DormRoomHousemaster();
                        dormRoomHousemaster.setDormRoomId(dormRoom.getPkDormRoom());
                        dormRoomHousemaster.setEmployeeId(pkEmployee);
                        dormRoomHousemaster.setIsvalid(1);
                        dormRoomHousemasterMapper.insertSelective(dormRoomHousemaster);
                    }

                }
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



}
