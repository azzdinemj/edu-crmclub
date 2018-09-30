package com.wuxue.api.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wuxue.api.mapper.AskForLeaveMapper;
import com.wuxue.api.mapper.SchoolBusMapper;
import com.wuxue.api.mapper.SchoolBusStudentMapper;
import com.wuxue.api.mapper.StudentMapper;
import com.wuxue.api.service.*;
import com.wuxue.api.service.junhwa.ParentShuttleService;
import com.wuxue.api.utils.DateUtil;
import com.wuxue.api.utils.GuidUtils;
import com.wuxue.api.utils.dozer.BeanMapperUtil;
import com.wuxue.base.ResultEntity;
import com.wuxue.model.SchoolBus;
import com.wuxue.model.SchoolBusStudent;
import com.wuxue.model.Student;
import com.wuxue.model.shuttle.BoardingRecord;
import com.wuxue.model.shuttle.DebusRecord;
import com.wuxue.utils.contract.Light;
import com.wuxue.utils.contract.LinkEntity;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import com.wuxue.vo.BusStudentVO;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service("schoolBusService")
public class SchoolBusServiceImpl extends BeanMapperUtil implements SchoolBusService {
    @Autowired
    SchoolBusMapper schoolBusMapper;
    @Autowired
    UtilsService utilsService;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private SchoolBusStudentMapper schoolBusStudentMapper;
    @Autowired
    private AskForLeaveMapper askForLeaveMapper;
    @Autowired
    private ParentShuttleService parentShuttleService;
    @Autowired
    private SchoolBusStudentService schoolBusStudentService;
    @Autowired
    private BoardingRecordService boardingRecordService;
    @Autowired
    private DebusRecordService debusRecordService;

    @Override
    public Response delete(Request<String> tParams) {
        Response response = Response.newResponse();
        String primaryKey = tParams.getData();
        if (primaryKey == null || primaryKey.equals("")) {
            return response.PARAMS_ISNULL();
        }
        int iReuslt = 0;
        String message = "";
        try {
            SchoolBus schoolBus = schoolBusMapper.selectByPrimaryKey(primaryKey);
            schoolBus.setIsvalid(0);
            iReuslt = schoolBusMapper.updateByPrimaryKeySelective(schoolBus);
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
    public Response find(Request<SchoolBus> tParams) {
        Response response = Response.newResponse();
        SchoolBus schoolBus = tParams.getData();

        if (schoolBus == null) {
            return response.PARAMS_ISNULL();
        }
        String primaryKey = schoolBus.getPkSchoolBus();
        if (primaryKey != null && !primaryKey.equals("")) {
            SchoolBus byPrimaryKey = schoolBusMapper.selectByPrimaryKey(primaryKey);

            List<Student> studentList = new ArrayList<>();
            SchoolBusStudent busStudent = new SchoolBusStudent();
            busStudent.setPkSchoolBus(byPrimaryKey.getPkSchoolBus());
            busStudent.setIsvalid(1);
            List<SchoolBusStudent> schoolBusStudentList = schoolBusStudentMapper.selectBySSPrimaryKey(busStudent);
            if (schoolBusStudentList != null && schoolBusStudentList.size() > 0) {
                for (SchoolBusStudent schoolBusStudent : schoolBusStudentList) {
                    Student student = studentMapper.selectByPrimaryKey(schoolBusStudent.getPkStudent());
                    if (student != null) {
                        utilsService.setSysDictKeyValue(student, student.getGrade(), LinkEntity.GTADE_ENTITY);
                        utilsService.setSysDictKeyValue(student, student.getNation(), LinkEntity.NATION_ENTITY);
                        student.put(Light.BUSSTUDENT, schoolBusStudent.getPkSchooBusStudent());
                        studentList.add(student);
                    }
                }
            }

            utilsService.setSysUserKeyValue(byPrimaryKey, byPrimaryKey.getCreator(), LinkEntity.CREATOR_ENTITY);
            utilsService.setSysUserKeyValue(byPrimaryKey, byPrimaryKey.getModifier(), LinkEntity.MODIFIER_ENTITY);
            byPrimaryKey.put(Light.STUDENT_LIST, studentList);
            response.setData(byPrimaryKey);
        } else {
            PageHelper.startPage(schoolBus.getPageNo(), schoolBus.getPageSize());
            List<SchoolBus> schoolBusList = schoolBusMapper.select(schoolBus);
            PageInfo pageInfo = new PageInfo(schoolBusList);
            response.setTotal(pageInfo.getTotal());
            if (schoolBusList.size() > 0) {
                for (SchoolBus list : schoolBusList) {
                    utilsService.setSysUserKeyValue(list, list.getCreator(), LinkEntity.CREATOR_ENTITY);
                    utilsService.setSysUserKeyValue(list, list.getModifier(), LinkEntity.MODIFIER_ENTITY);
                    utilsService.setSysDictKeyValue(list, list.getKind(), LinkEntity.SCHOOL_BUS_KIND_ENTITY);
                    utilsService.setEmployeeKeyValue(list, list.getDriver(), LinkEntity.DRIVER_ENTITY);
                    utilsService.setEmployeeKeyValue(list, list.getGuardianTeacher(), LinkEntity.GUARDIAN_TEACHER_ENTITY);
                }
            }

            response.setData(schoolBusList);
        }
        return response;
    }

    @Override
    public Response save(Request<SchoolBus> tParams) {
        Response response = Response.newResponse();
        SchoolBus schoolBus = tParams.getData();


        if (schoolBus == null) {
            return response.PARAMS_ISNULL();
        }
        String primaryKey = schoolBus.getPkSchoolBus();
        SchoolBus select = null;
        if (primaryKey != null && !primaryKey.equals("")) {
            select = schoolBusMapper.selectByPrimaryKey(primaryKey);
        }
        int iReuslt = 0;
        String message = "";
        try {
            if (select != null) {
                schoolBus.setLasteditDate(new Date());
                iReuslt = schoolBusMapper.updateByPrimaryKeySelective(schoolBus);
            } else {
                if (schoolBus.getPkSchoolBus() ==null || "".equals(schoolBus.getPkSchoolBus())){
                    schoolBus.setPkSchoolBus(GuidUtils.getGuid());
                }
                schoolBus.setCreationDate(new Date());
                schoolBus.setLasteditDate(new Date());
                iReuslt = schoolBusMapper.insertSelective(schoolBus);
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
    public Response findStudentInfosByBusId(ResultEntity resultEntity) {
        Response response = Response.newResponse();
        String schoolbusId = resultEntity.getSchoolbusId();
        if (null == schoolbusId || "".equals(schoolbusId)) {
            return response.PARAMS_ISNULL();
        }
        Byte direction = resultEntity.getDirection();
        if (null == direction || "".equals(direction)) {
            return response.PARAMS_ISNULL();
        }
        Integer onOffBus = resultEntity.getOnOffBus();
        if (null == onOffBus) {
            return response.PARAMS_ISNULL();
        }
        SchoolBus schoolBus = schoolBusMapper.selectByPrimaryKey(schoolbusId);
        if (null == schoolBus) {
            return response.PARAMS_ISNULL();
        }

        Date departDate;
        Date startTime;
        Date endTime;
        //'方向，1=校外开往校内，2=校内开往校外'
        if (1 == direction) {
            if (null == schoolBus.getOffschDepartDate()) {
                return response.PARAMS_ISNULL();
            }
            departDate = DateUtil.changeToCurrentTime(schoolBus.getOffschDepartDate());
            startTime = DateUtil.getCurrentDayStartTime();
            endTime = DateUtil.getForenoonTime();
        } else if (2 == direction) {
            if (null == schoolBus.getSchoolDepartDate()) {
                return response.PARAMS_ISNULL();
            }

            startTime = DateUtil.getAfternoonTime();
            endTime = DateUtil.getCurrentDayEndTime();
            departDate = DateUtil.changeToCurrentTime(schoolBus.getSchoolDepartDate());
        } else {
            return response.PARAMS_ISNULL();
        }
        List<String> studentIds = schoolBusStudentService.getStudentIdsByBusId(schoolbusId);
        List<ResultEntity> entityList = studentMapper.selectStudentInfoByBusIds(studentIds);
        List<String> askForLeaveStudentList = askForLeaveMapper.selectAskForLeaveStudentList(studentIds, departDate);
        Iterator<ResultEntity> iterator = entityList.iterator();
        while (iterator.hasNext()) {
            ResultEntity next = iterator.next();
            if (askForLeaveStudentList.contains(next.getStudentId())) {
                iterator.remove();
            }
        }
        List<String> pkStudentIds = new ArrayList<>();
        for (ResultEntity entity : entityList) {
            pkStudentIds.add(entity.getStudentId());
        }

        Map<String, Integer> studentRecordMap;
        if (0 == onOffBus) {
            studentRecordMap = getTodayBoardingStudentRecordMap(pkStudentIds, startTime, endTime);
        } else if (1 == onOffBus) {
            studentRecordMap = getTodayStudentDeBusRecordMap(pkStudentIds, startTime, endTime);
        } else {
            return response.NO_CONTEN();
        }
        for (ResultEntity entity : entityList) {
            String studentId = entity.getStudentId();
            if (studentRecordMap.containsKey(studentId)) {
                entity.setStatus(studentRecordMap.get(studentId));
            } else {
                entity.setStatus(-1);
            }
        }
        response.setData(toList(entityList, BusStudentVO.class));
        response.setTotal(entityList.size());
        return response;
    }

    @Override
    public Response findBusIdByUserId(SchoolBus schoolBus) {
        Response response = Response.newResponse();
        if (null == schoolBus || "".equals(schoolBus)) {
            return response.PARAMS_ISNULL();
        }
        String userId = schoolBus.getGuardianTeacher();
        if (null == userId || "".equals(userId)) {
            return response.PARAMS_ISNULL();
        }
        ResultEntity entity = schoolBusMapper.findBusIdByUserId(userId);
        if (null == entity) {
            return response.NO_CONTEN();
        }
        response.setData(entity);
        return response;
    }

    /**
     * 獲取最新學生下車記錄
     *
     * @param pkStudentIds
     * @return
     */
    public Map<String, Integer> getTodayStudentDeBusRecordMap(List<String> pkStudentIds, Date startTime, Date endTime) {

        List<DebusRecord> debusRecords = debusRecordService.getLatestDebusRecordByPkStudent(pkStudentIds, startTime, endTime);
        Map<String, Integer> map = new HashMap<>();
        if (CollectionUtils.isEmpty(debusRecords)) {
            return map;
        }
        for (DebusRecord debusRecord : debusRecords) {
            map.put(debusRecord.getPkStudent(), debusRecord.getStatus());
        }
        return map;

    }

    /**
     * 獲取最新學生上車記錄
     *
     * @param pkStudentIds
     * @return
     */
    public Map<String, Integer> getTodayBoardingStudentRecordMap(List<String> pkStudentIds, Date startTime, Date endTime) {
        List<BoardingRecord> boardingRecords = boardingRecordService.getLatestBoardingRecordByPkStudent(pkStudentIds, startTime, endTime);
        Map<String, Integer> map = new HashMap<>();
        if (CollectionUtils.isEmpty(boardingRecords)) {
            return map;
        }
        for (BoardingRecord boardingRecord : boardingRecords) {
            map.put(boardingRecord.getPkStudent(), boardingRecord.getStatus());
        }
        return map;

    }
}
