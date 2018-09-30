package com.wuxue.api.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wuxue.api.mapper.ClassinfoRoomMapper;
import com.wuxue.api.mapper.ClassinfoMapper;
import com.wuxue.api.mapper.StudentMapper;
import com.wuxue.api.mapper.StudentSignupMapper;
import com.wuxue.api.service.ClassinfoRoomService;
import com.wuxue.api.service.UtilsService;
import com.wuxue.model.Classinfo;
import com.wuxue.model.ClassinfoRoom;
import com.wuxue.model.ClassinfoRoom;
import com.wuxue.utils.contract.LinkEntity;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

//import com.wuxue.api.utils.GuidUtils;

@Service("classinfoRoomService")
public class ClassinfoRoomServiceImpl implements ClassinfoRoomService{
    @Autowired
    ClassinfoRoomMapper classinfoRoomMapper;
    @Autowired
    StudentSignupMapper studentSignupMapper;
    @Autowired
    StudentMapper studentMapper;
    @Autowired
    UtilsService utilsService;
    @Autowired
    ClassinfoMapper classinfoMapper;

    @Override
    public Response delete(Request<ClassinfoRoom> tParams) {
        Response response = Response.newResponse();
        ClassinfoRoom classinfoRoom = tParams.getData();

        if(classinfoRoom.getPkClassinfoRoom() ==null || "".equals(classinfoRoom.getPkClassinfoRoom())){
            return  response.PARAMS_ISNULL();
        }
        String message= "";
        try {
            classinfoRoom.setIsvalid(0);
            classinfoRoomMapper.updateByPrimaryKeySelective(classinfoRoom);
        }catch (Exception ex){
            message = ex.getMessage();
            return response.SAVE_FAIL(message);
        }
        return response;
    }

    @Override
    public Response find(Request<ClassinfoRoom> tParams) {
        Response response = Response.newResponse();
        ClassinfoRoom classinfoRoom = tParams.getData();

        String pkClassinfoRoom = classinfoRoom.getPkClassinfoRoom();
        if (pkClassinfoRoom == null || "".equals(pkClassinfoRoom)){
            PageHelper.startPage(classinfoRoom.getPageNo(), classinfoRoom.getPageSize());
            List<ClassinfoRoom> select = classinfoRoomMapper.select(classinfoRoom);
            PageInfo page = new PageInfo(select);
            response.setTotal(page.getTotal());
            if (select.size() >0){
                for (ClassinfoRoom employee : select) {
                    utilsService.setClassRoomKeyValue(employee,employee.getPkClassroom(),LinkEntity.CLASSROOM_ENTITY);
                    utilsService.setClassInfoKeyValue(employee,employee.getPkClassinfo(),LinkEntity.CLASS_INFO_ENTITY);
                }
            }
            response.setData(select);
        }
        return response;
    }

    @Override
    public Response save(Request<ClassinfoRoom> tParams) {
        Response response = Response.newResponse();
        ClassinfoRoom classinfoRoom = tParams.getData();

        if(classinfoRoom== null ){
            return  response.PARAMS_ISNULL();
        }

        ClassinfoRoom room = new ClassinfoRoom();
        room.setPkClassroom(classinfoRoom.getPkClassroom());
        room.setPkClassinfo(classinfoRoom.getPkClassinfo());
        room.setPkClassinfoRoom(classinfoRoom.getPkClassinfoRoom());
        ClassinfoRoom select = classinfoRoomMapper.selectByPrimaryKey(room);
        String message= "";
        try {
            if (select != null) {
                classinfoRoom.setCreator(null);
                classinfoRoom.setLasteditDate(new Date());
                classinfoRoomMapper.updateByPrimaryKeySelective(classinfoRoom);
            }else {
                classinfoRoom.setCreationDate(new Date());
                classinfoRoom.setLasteditDate(new Date());
                classinfoRoom.setIsvalid(1);
                classinfoRoomMapper.insertSelective(classinfoRoom);
            }

        }catch (Exception ex){
            message = ex.getMessage();
            return response.SAVE_FAIL(message);
        }
        return response;
    }

    @Override
    public Response saveAll(Request<ClassinfoRoom> tParams) {
        Response response = Response.newResponse();
        ClassinfoRoom classinfoRoom = tParams.getData();

        if(classinfoRoom== null){
            return  response.PARAMS_ISNULL();
        }

//        ClassinfoRoom employee;
//        String[] employeeList = classinfoRoom.getPkEmployee().split(",");
//        List<Classinfo> classinfoList = classinfoMapper.select(new Classinfo());
//
//
//        String message= "";
//        try {
//            if (!employeeList.equals("")) {
//                for (int i = 0; i < employeeList.length; i++) {
//                    for (Classinfo classinfo : classinfoList) {
//                        employee = new ClassinfoRoom();
//                        employee.setPkEmployee(employeeList[i]);
//                        employee.setPkClassinfo(classinfo.getPkClassinfo());
//                        ClassinfoRoom select = classinfoRoomMapper.selectByPrimaryKey(employee);
//                        if (select == null) {
//                            classinfoRoom.setCreationDate(new Date());
//                            classinfoRoom.setLasteditDate(new Date());
//                            classinfoRoom.setPkClassinfo(classinfo.getPkClassinfo());
//                            classinfoRoomMapper.insertSelective(classinfoRoom);
//                        }
//                    }
//                }
//            }
//        }catch (Exception ex){
//            message = ex.getMessage();
//            return response.SAVE_FAIL(message);
//        }
        return response;
    }


    @Override
    public Response findTeacher(Request<ClassinfoRoom> tParams) {
        Response response = Response.newResponse();
        ClassinfoRoom classinfoRoom = tParams.getData();

        String pkClassinfo = classinfoRoom.getPkClassinfo();
        String pkClassroom = classinfoRoom.getPkClassroom();
        String pkClassinfoRoom = classinfoRoom.getPkClassinfoRoom();
        if (pkClassinfoRoom == null || "".equals(pkClassinfoRoom)){
            PageHelper.startPage(classinfoRoom.getPageNo(), classinfoRoom.getPageSize());
            List<ClassinfoRoom> select = classinfoRoomMapper.select(classinfoRoom);
            PageInfo page = new PageInfo(select);
            response.setTotal(page.getTotal());
            if (select.size() >0){
                for (ClassinfoRoom employee : select) {
                    utilsService.setClassRoomKeyValue(employee,employee.getPkClassroom(),LinkEntity.CLASSROOM_ENTITY);
                    utilsService.setClassInfoKeyValue(employee,employee.getPkClassinfo(),LinkEntity.CLASS_INFO_ENTITY);
                }
            }
            response.setData(select);
        }
        return response;
    }

    @Override
    public Response findClassinfoRoom(Request<ClassinfoRoom> tParams) {
        Response response = Response.newResponse();
        ClassinfoRoom classinfoRoom = tParams.getData();

            List<ClassinfoRoom> select = classinfoRoomMapper.selectBy(classinfoRoom);
            if (select.size() >0){
                for (ClassinfoRoom employee : select) {
                    utilsService.setClassRoomKeyValue(employee,employee.getPkClassroom(),LinkEntity.CLASSROOM_ENTITY);
                    utilsService.setClassInfoKeyValue(employee,employee.getPkClassinfo(),LinkEntity.CLASS_INFO_ENTITY);
                }
            }
            response.setData(select);
        return response;
    }




}
