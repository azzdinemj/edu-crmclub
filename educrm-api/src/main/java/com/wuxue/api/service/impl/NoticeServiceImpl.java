package com.wuxue.api.service.impl;

import com.wuxue.api.mapper.*;
import com.wuxue.api.service.NoticeService;
import com.wuxue.api.service.UtilsService;
import com.wuxue.api.utils.GuidUtils;
import com.wuxue.model.*;
import com.wuxue.model.shuttle.DeliveryApplication;
import com.wuxue.model.shuttle.DeliveryStudent;
import com.wuxue.model.shuttle.NoticeRecord;
import com.wuxue.utils.contract.LinkEntity;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import com.wuxue.vo.NoticeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.ClassInfo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service("noticeService")
public class NoticeServiceImpl implements NoticeService {
    @Autowired
    NoticeMapper noticeMapper;

    @Autowired
    UtilsService utilsService;
    @Autowired
    NoticeRecordMapper noticeRecordMapper;
    @Autowired
    HomeworkMapper homeworkMapper;
    @Autowired
    ClassinfoStudentMapper classinfoStudentMapper;
    @Autowired
    ClassinfoMapper classinfoMapper;
    @Autowired
    DeliveryApplicationMapper deliveryApplicationMapper;
    @Autowired
    AskForLeaveMapper askForLeaveMapper;
    @Autowired
    ClassinfoEmployeeMapper classinfoEmployeeMapper;
    @Autowired
    AskLeaveStudentMapper askLeaveStudentMapper;
    @Autowired
    StudentLinkmanMapper studentLinkmanMapper;
    @Autowired
    DeliveryStudentMapper deliveryStudentMapper;
    @Autowired
    StudentMapper studentMapper;

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
            iReuslt=noticeMapper.deleteByPrimaryKey(primaryKey);
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
    public Response find(Request<Notice> tParams) {
        Response response = Response.newResponse();
        Notice notice = tParams.getData();

        if(notice== null){
            return  response.PARAMS_ISNULL();
        }
        String primaryKey = notice.getPkNotice();
        if(primaryKey !=null && !primaryKey.equals("")){
            notice = noticeMapper.selectByPrimaryKey(primaryKey);
            utilsService.setSysUserKeyValue(notice,notice.getCreator(), LinkEntity.CREATOR_ENTITY);
            utilsService.setSysUserKeyValue(notice,notice.getModifier(), LinkEntity.MODIFIER_ENTITY);
            utilsService.setSysUserKeyValue(notice,notice.getPkUser(),LinkEntity.USER_ENTITY);

            response.setData(notice);
        }else{
            List<Notice> list = noticeMapper.select(notice);
            for (Notice noticeEntity : list) {
                utilsService.setSysUserKeyValue(noticeEntity,noticeEntity.getCreator(), LinkEntity.CREATOR_ENTITY);
                utilsService.setSysUserKeyValue(noticeEntity,noticeEntity.getModifier(),LinkEntity.MODIFIER_ENTITY);
                utilsService.setSysUserKeyValue(noticeEntity,noticeEntity.getPkUser(),LinkEntity.USER_ENTITY);
            }

//            校车通知
            List<NoticeRecord> select = noticeRecordMapper.select(new NoticeRecord());

            response.setData(list);
            response.setTotal(noticeMapper.countBy(notice));
        }
        return response;
    }

    @Override
    public Response save(Request<Notice> tParams) {
        Response response = Response.newResponse();
        Notice notice = tParams.getData();

        if(notice== null){
            return  response.PARAMS_ISNULL();
        }
        String primaryKey = notice.getPkNotice();
        Notice select = null;
        if (primaryKey != null && !primaryKey.equals("")) {
            select = noticeMapper.selectByPrimaryKey(primaryKey);
        }
        int iReuslt = 1;
        String message= "";
        try {
            if (select != null) {
                notice.setLasteditDate(new Date());
                notice.setModifier(tParams.getCurrentUser());
                iReuslt = noticeMapper.updateByPrimaryKeySelective(notice);
            } else {
                notice.setCreationDate(new Date());
                notice.setLasteditDate(new Date());
                notice.setCreator(tParams.getCurrentUser());
                notice.setModifier(tParams.getCurrentUser());
                notice.setPkUser(tParams.getCurrentUser());
                notice.setIsdel(1);
                iReuslt = noticeMapper.insertSelective(notice);
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

    @Override
    public Response findAll(Request<Notice> tParams) {
        Response response = Response.newResponse();
        Notice notice = tParams.getData();

        if(notice== null && notice.getType() == null){
            return response.PARAMS_ISNULL();
        }


//        获取学生班级
        ClassinfoStudent classinfoStudent = new ClassinfoStudent();
        classinfoStudent.setPkStudent(notice.getPkStudent());
        List<ClassinfoStudent> classinfoStudentList = classinfoStudentMapper.select(classinfoStudent);
        Classinfo classinfo = new Classinfo();
        if (classinfoStudentList.size() > 0) {
            for (ClassinfoStudent student : classinfoStudentList) {
                Classinfo classinfo1 = classinfoMapper.selectByPrimaryKey(student.getPkClassinfo());
                if(classinfo1 != null && classinfo1.getType() != null && classinfo1.getType() == 0){
                    classinfo = classinfo1;
                    break;
                }
            }
        }

        NoticeVO noticeVO = new NoticeVO();
        Integer type = notice.getType();
        notice.setType(null);
        List<Notice> list = noticeMapper.select(notice);
            if (list.size() > 0) {

                for (Notice noticeEntity : list) {
                    utilsService.setSysUserKeyValue(noticeEntity, noticeEntity.getCreator(), LinkEntity.CREATOR_ENTITY);
                    utilsService.setSysUserKeyValue(noticeEntity, noticeEntity.getModifier(), LinkEntity.MODIFIER_ENTITY);
                    utilsService.setSysUserKeyValue(noticeEntity, noticeEntity.getPkUser(), LinkEntity.USER_ENTITY);
                }
            }
//            校内家长通知
        Notice notice1 = new Notice();
        notice1.setType(type);
        List<Notice> notices = noticeMapper.select(notice1);
        if(type == 1){
//            校车通知
            List<NoticeRecord> select = noticeRecordMapper.select(new NoticeRecord());
            if (select.size() > 0) {
                for (NoticeRecord noticeRecord : select) {
                    utilsService.setSchoolBusKeyValue(noticeRecord, noticeRecord.getPkSchoolBus(), LinkEntity.SCHOOL_BUS_KIND_ENTITY);
                    utilsService.setSchoolBusLineKeyValue(noticeRecord, noticeRecord, LinkEntity.SCHOOL_BUS_LINE_KIND_ENTITY);
                }
            }

//            作业通知（homeWork）
            Homework homework = new Homework();
            homework.setClassinfo(classinfo.getPkClassinfo());
            List<Homework> homeworkList = homeworkMapper.select(new Homework());
            if (homeworkList.size() > 0) {
                for (Homework homework1 : homeworkList) {
                    utilsService.setSysDictKeyValue(homework1, homework1.getWorkType(), LinkEntity.DISCIPLINE_ENTITY);
                }
            }

//            委托接送通知(delivery_application)
                DeliveryApplication deliveryApplication = new DeliveryApplication();
                deliveryApplication.setPkLinkman(notice.getPkLinkman());
                List<DeliveryApplication> commissionedList = deliveryApplicationMapper.select(deliveryApplication);
                if (commissionedList.size() > 0) {
                    for (DeliveryApplication application : commissionedList) {
                        utilsService.setLinkManByKeyValue(application, application.getEntrustmentId(), LinkEntity.ENTRUST_ENTITY);
                        utilsService.setLinkManByKeyValue(application, application.getPkLinkman(), LinkEntity.COMMISSIONE_ENTITY);
                        String studentName = "";
                        DeliveryStudent deliveryStudent = new DeliveryStudent();
                        deliveryStudent.setPkDelivery(application.getPkDelivery());
                        List<DeliveryStudent> deliveryStudentList = deliveryStudentMapper.select(deliveryStudent);
                        if (deliveryStudentList.size() > 0) {
                            for (DeliveryStudent student : deliveryStudentList) {
                                Student byPrimaryKey = studentMapper.selectByPrimaryKey(student.getPkStudent());
                                if(byPrimaryKey != null && byPrimaryKey.getCaption() != null){
                                    studentName = studentName + byPrimaryKey.getCaption() + ",";
                                }
                            }
                        }
                        if(!studentName.equals("")) {
                            studentName = studentName.substring(0, studentName.length() - 1);
                        }
                        application.put("studentName",studentName);
                    }
                }
//            被委托接送通知
                deliveryApplication = new DeliveryApplication();
                deliveryApplication.setEntrustmentId(notice.getPkLinkman());
                List<DeliveryApplication> entrustList = deliveryApplicationMapper.select(deliveryApplication);
                if (entrustList.size() > 0) {
                    for (DeliveryApplication application1 : entrustList) {
                        utilsService.setLinkManByKeyValue(application1, application1.getEntrustmentId(), LinkEntity.ENTRUST_ENTITY);
                        utilsService.setLinkManByKeyValue(application1, application1.getPkLinkman(), LinkEntity.COMMISSIONE_ENTITY);
                        String studentName = "";
                        DeliveryStudent deliveryStudent = new DeliveryStudent();
                        deliveryStudent.setPkDelivery(application1.getPkDelivery());
                        List<DeliveryStudent> deliveryStudentList = deliveryStudentMapper.select(deliveryStudent);
                        if (deliveryStudentList.size() > 0) {
                            for (DeliveryStudent student : deliveryStudentList) {
                                Student byPrimaryKey = studentMapper.selectByPrimaryKey(student.getPkStudent());
                                if(byPrimaryKey != null && byPrimaryKey.getCaption() != null){

                                    studentName = studentName + byPrimaryKey.getCaption() + ",";
                                }
                            }
                        }
                        if(!studentName.equals("")) {
                            studentName = studentName.substring(0, studentName.length() - 1);
                        }
                        application1.put("studentName",studentName);
                    }
                }
                noticeVO.setEntrustList(entrustList);
                noticeVO.setCommissionedList(commissionedList);
                noticeVO.setNoticeRecordList(select);
                noticeVO.setHomeworkList(homeworkList);
            }

//            请假通知(ask_for_leave)
        if(type == 0) {
            List<AskForLeave> askForLeaveList = new ArrayList<>();
            List<DeliveryApplication> deliveryApplicationList = new ArrayList<>();
//            获取教师管理班级
            classinfo = new Classinfo();
            classinfo.setHeadTeacher(notice.getPkStudent());
            List<Classinfo> classinfoList = classinfoMapper.select(classinfo);
            if (classinfoList.size() > 0) {
                for (Classinfo classinfo1 : classinfoList) {
//                    获取班级关联学生
                    ClassinfoStudent classinfoStudent1 = new ClassinfoStudent();
                    classinfoStudent1.setPkClassinfo(classinfo1.getPkClassinfo());
                    List<ClassinfoStudent> studentList = classinfoStudentMapper.select(classinfoStudent1);
                    if (studentList.size() > 0) {
                        for (ClassinfoStudent student : studentList) {

//                            获取学生关联请假
                            AskLeaveStudent askLeaveStudent = new AskLeaveStudent();
                            askLeaveStudent.setPkStudent(student.getPkStudent());
                            List<AskLeaveStudent> leaveStudentList = askLeaveStudentMapper.select(askLeaveStudent);
                            if (leaveStudentList.size() > 0) {
                                for (AskLeaveStudent leaveStudent : leaveStudentList) {
//                                    获取学生请假详情
                                    AskForLeave askForLeave = askForLeaveMapper.selectByPrimaryKey(leaveStudent.getPkAskForLeave());
                                    if(askForLeave != null){
                                        utilsService.setStudentKeyValue(askForLeave, leaveStudent.getPkStudent(), LinkEntity.STUDENT_ENTITY);
                                        askForLeaveList.add(askForLeave);
                                    }
                                }
                            }

//                            获取学生关联家长
                            DeliveryStudent deliveryStudent = new DeliveryStudent();
                            deliveryStudent.setPkStudent(student.getPkStudent());
                            List<DeliveryStudent> deliveryStudentList = deliveryStudentMapper.select(deliveryStudent);
                            if (deliveryStudentList.size() > 0) {
                                for (DeliveryStudent deliveryStudent1 : deliveryStudentList) {
                                    DeliveryApplication deliveryApplication = deliveryApplicationMapper.selectByPrimaryKey(deliveryStudent1.getPkDelivery());
                                    if(deliveryApplication != null && (deliveryApplication.getIsaudit() == 1 || deliveryApplication.getIsaudit() == 3)){
                                        utilsService.setLinkManByKeyValue(deliveryApplication, deliveryApplication.getEntrustmentId(), LinkEntity.ENTRUST_ENTITY);
                                        utilsService.setLinkManByKeyValue(deliveryApplication, deliveryApplication.getPkLinkman(), LinkEntity.COMMISSIONE_ENTITY);
                                        utilsService.setStudentKeyValue(deliveryApplication, deliveryStudent1.getPkStudent(), LinkEntity.STUDENT_ENTITY);
                                        deliveryApplicationList.add(deliveryApplication);
                                    }
                                }
                            }
                        }
                    }
                }
            }
            noticeVO.setAskForLeavesList(askForLeaveList);
            noticeVO.setDeliveryApplicationList(deliveryApplicationList);
        }

            noticeVO.setNoticeList(notices);
            noticeVO.setNoticeParentList(list);
            response.setData(noticeVO);
//            response.setTotal(noticeMapper.countBy(notice));
            return response;
    }
}
