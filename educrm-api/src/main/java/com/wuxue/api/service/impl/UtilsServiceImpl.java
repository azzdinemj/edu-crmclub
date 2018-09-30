package com.wuxue.api.service.impl;

import com.wuxue.api.mapper.*;
import com.wuxue.api.service.SysLogService;
import com.wuxue.api.service.UtilsService;
import com.wuxue.api.utils.GuidUtils;
import com.wuxue.base.KeyValue;
import com.wuxue.base.Page;
import com.wuxue.model.*;
import com.wuxue.model.shuttle.NoticeRecord;
import com.wuxue.model.shuttle.SchoolbusLine;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by Jamie on 2018/1/21.
 */
@Service("utilsService")
public class UtilsServiceImpl implements UtilsService {

    @Autowired
    SysUserMapper sysUserMapper;

    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    SysDictMapper sysDictMapper;

    @Autowired
    SysDictValuesMapper sysDictValuesMapper;

    @Autowired
    DomainMapper domainMapper;

    @Autowired
    ClassRoomMapper classRoomMapper;

    @Autowired
    ClassTimeMapper classTimeMapper;

    @Autowired
    DepartmentMapper departmentMapper;

    @Autowired
    LinkmanMapper linkmanMapper;

    @Autowired
    SysSetMapper sysSetMapper;

    @Autowired
    StudentMapper studentMapper;

    @Autowired
    ReceiptMapper receiptMapper;

    @Autowired
    ClassinfoMapper classinfoMapper;

    @Autowired
    ActivityStudentMapper activityStudentMapper;

    @Autowired
    ScheduleMapper scheduleMapper;

    @Autowired
    PaymentMapper paymentMapper;

    @Autowired
    SysLogService sysLogService;
    @Autowired
    private StudentPlansCaptionMapper studentPlansCaptionMapper;

    @Autowired
    private TkSetMealMapper tkSetMealMapper;

    @Autowired
    private ExpenseItemMapper expenseItemMapper;

    @Autowired
    private JhQuestionMapper jhQuestionMapper;

    @Autowired
    private JhAnswerdetailMapper jhAnswerdetailMapper;

    @Autowired
    private JhAnswerMapper jhAnswerMapper;

    @Autowired
    private SetMealMapper setMealMapper;
    @Autowired
    private SchoolBusMapper schoolBusMapper;
    @Autowired
    private SchoolbusLineMapper schoolbusLineMapper;

//    private HashMap<String,SysUser> sysUserMap;

    private SysUser getSysUserCache(String pkId){

        SysUser sysUser = sysUserMapper.selectByPrimaryKey(pkId);
        return sysUser;
    }

    @Override
    public void setSysUserKeyValue(Page object, String pkId, String linkEntityName) {
        SysUser sysUserEntity =  getSysUserCache(pkId);//sysUserMapper.selectByPrimaryKey(pkId);
        KeyValue keyValue = new KeyValue();
        if (sysUserEntity != null) {
            keyValue.setId(sysUserEntity.getPkSysUser());
            keyValue.setCode(sysUserEntity.getPkSysUser());
            keyValue.setCaption(sysUserEntity.getCaption());
        }
        object.put(linkEntityName, keyValue);
    }
//    private HashMap<String,Department> departmentMap;
    private Department getDepartmentCache(String pkId){

        Department department = departmentMapper.selectByPrimaryKey(pkId);

        return department;
    }

    @Override
    public void setDepartmentKeyValue(Page object, String pkId, String linkEntityName) {
        Department departmentEntity = getDepartmentCache(pkId);//departmentMapper.selectByPrimaryKey(pkId);
        KeyValue keyValue = new KeyValue();
        if (departmentEntity != null) {
            keyValue.setId(departmentEntity.getPkDepartment());
            keyValue.setCode(departmentEntity.getCode());
            keyValue.setCaption(departmentEntity.getCaption());
        }
        object.put(linkEntityName, keyValue);
    }

//    private HashMap<String,Employee> employeeMap;
    private Employee getEmployeeCache(String pkId){
        Employee employee = employeeMapper.selectByPrimaryKey(pkId);
        return employee;
    }

    @Override
    public void setEmployeeKeyValue(Page object, String pkId, String linkEntityName) {
        Employee employee = getEmployeeCache(pkId);//employeeMapper.selectByPrimaryKey(pkId);
        KeyValue keyValue = new KeyValue();
        if (employee != null) {
            keyValue.setId(employee.getPkEmployee());
            keyValue.setCode(employee.getCode());
            keyValue.setCaption(employee.getCaption());
        }
        object.put(linkEntityName, keyValue);
    }

//    private HashMap<String,SysDictValues> sysDictValueMap;
    private SysDictValues getSysDictValueCache(String pkId){

        SysDictValues sysDictValues = sysDictValuesMapper.selectByPrimaryKey(pkId);
        return sysDictValues;
    }


    @Override
    public void setSysDictKeyValue(Page object, String pkId, String linkEntityName) {
        SysDictValues sysDict = getSysDictValueCache(pkId);// sysDictMapper.selectByPrimaryKey(pkId);
        KeyValue keyValue = new KeyValue();
        if (sysDict != null) {
            keyValue.setId(sysDict.getPkSysDictValues());
            keyValue.setCode(sysDict.getPkSysDictValues());
            keyValue.setCaption(sysDict.getCaption());
        }
        object.put(linkEntityName, keyValue);
    }

//    private HashMap<String,Domain> domainMap;
    private Domain getDomainCache(String pkId){
        Domain domain = domainMapper.selectByPrimaryKey(pkId);
        return domain;
    }

    @Override
    public void setDomainKeyValue(Page object, String pkId, String linkEntityName) {
        Domain domain = getDomainCache(pkId);//domainMapper.selectByPrimaryKey(pkId);
        KeyValue keyValue = new KeyValue();
        if (domain != null) {
            keyValue.setId(domain.getPkDomain());
            keyValue.setCode(domain.getPkDomain());
            keyValue.setCaption(domain.getCaption());
        }
        object.put(linkEntityName, keyValue);
    }

//    private HashMap<String,ClassRoom> classRoomMap;
    private ClassRoom getClassRoomCache(String pkId){
        ClassRoom classRoom = classRoomMapper.selectByPrimaryKey(pkId);
        return classRoom;
    }

    @Override
    public void setClassRoomKeyValue(Page object, String pkId, String linkEntityName) {
        ClassRoom classRoom = classRoomMapper.selectByPrimaryKey(pkId);//getClassRoomCache(pkId);
        KeyValue keyValue = new KeyValue();
        if (classRoom != null) {
            keyValue.setId(classRoom.getPkClassRoom());
            keyValue.setCode(classRoom.getCode());
            keyValue.setCaption(classRoom.getCaption());
        }
        object.put(linkEntityName, keyValue);
    }
//    private HashMap<String,ExpenseItem> expenseItemHashMap;
    private ExpenseItem getExpenseItem(String pkId){
        ExpenseItem expenseItem = expenseItemMapper.selectByPrimaryKey(pkId);
        return expenseItem;
    }

    @Override
    public void setExpenseItemKeyValue(Page object, String pkId, String linkEntityName) {
        ExpenseItem expenseItem = getExpenseItem(pkId);//classRoomMapper.selectByPrimaryKey(pkId);
        KeyValue keyValue = new KeyValue();
        if (expenseItem != null) {
            keyValue.setId(expenseItem.getPkExpenseItem());
            keyValue.setCode(expenseItem.getCode());
            keyValue.setCaption(expenseItem.getCaption());
        }
        object.put(linkEntityName, keyValue);
    }

    @Override
    public void setExaminationObject(JhExamination jhExamination, String object, String linkEntityName) {
        String list = "";
        KeyValue keyValue = new KeyValue();
        if (object != null && !object.equals("")) {
        String[] split = object.split(",");
        if (split.length > 0) {
            for (int i = 0; i < split.length; i++) {
                SysDictValues dict = sysDictValuesMapper.selectByPrimaryKey(split[i]);
                list = list + dict.getCaption() + "/";
            }
            list = list.substring(0,list.length() - 1);
        }

        }
        keyValue.setCaption(list);
        keyValue.setId(object);
        jhExamination.put(linkEntityName,keyValue);
    }

    @Override
    public void setQuestionNum(JhExamination jhExamination, String pkExamination, String linkEntityName) {
        JhQuestion jhQuestion = new JhQuestion();
        jhQuestion.setPkExamination(pkExamination);
        Integer countBy = jhQuestionMapper.countBy(jhQuestion);
        KeyValue keyValue = new KeyValue();
        keyValue.setCaption(countBy.toString());
        jhExamination.put(linkEntityName,keyValue);
    }

    @Override
    public void setNumber(JhExamination jhExamination, String pkExamination, String linkEntityName) {
        JhAnswer jhAnswer = new JhAnswer();
        jhAnswer.setPkExamination(pkExamination);
        Integer countBy = jhAnswerMapper.countBy(jhAnswer);
        KeyValue keyValue = new KeyValue();
        keyValue.setCaption(countBy.toString());
        jhExamination.put(linkEntityName,keyValue);
    }

    /**
     * 后勤套餐
     * @param object
     * @param pkId
     * @param linkEntityName
     */
    @Override
    public void setMealKeyValue(Page object, String pkId, String linkEntityName) {
        SetMeal setMeal = setMealMapper.selectByPrimaryKey(pkId);
        KeyValue keyValue = new KeyValue();
        if(setMeal != null){
            keyValue.setId(setMeal.getPkSetMeal());
            keyValue.setCode(setMeal.getCode());
            keyValue.setCaption(setMeal.getSetMealName());
        }
        object.put(linkEntityName,keyValue);
    }

    @Override
    public void setSchoolBusKeyValue(Page object, String pkSchoolBus, String schoolBusKindEntity) {
        SchoolBus schoolBus = schoolBusMapper.selectByPrimaryKey(pkSchoolBus);
        KeyValue keyValue = new KeyValue();
        if (schoolBus != null) {
            keyValue.setId(schoolBus.getPkSchoolBus());
            keyValue.setCode(schoolBus.getBusCode());
            keyValue.setCaption(schoolBus.getCaption());
        }
        object.put(schoolBusKindEntity, keyValue);
    }

    @Override
    public void setSchoolBusLineKeyValue(Page object, NoticeRecord noticeRecord, String schoolBusLineKindEntity) {
        SchoolbusLine schoolbusLine = schoolbusLineMapper.selectById(Long.valueOf(noticeRecord.getLineNum()));
        KeyValue keyValue = new KeyValue();
        if (schoolbusLine != null) {
            keyValue.setId(schoolbusLine.getPkNoticeRecord());
            keyValue.setCode(schoolbusLine.getSchoolbusId());
            keyValue.setCaption(schoolbusLine.getStationname());
        }
        object.put(schoolBusLineKindEntity, keyValue);
    }

    //    private HashMap<String,ClassTime> classTimeMap;
    private ClassTime getClassTimeCache(String pkId){
        ClassTime classTime = new ClassTime();
        classTime.setPkClassTime(pkId);
        ClassTime classTime1 = classTimeMapper.selectByPrimaryKey(classTime);
        return classTime1;
    }

    @Override
    public void setClassTimeKeyValue(Page object, String pkId, String linkEntityName) {
        ClassTime classTime =  getClassTimeCache(pkId);//classTimeMapper.selectByPrimaryKey(pkId);
        KeyValue keyValue = new KeyValue();
        if (classTime != null) {
            keyValue.setId(classTime.getPkClassTime());
            keyValue.setCode(classTime.getPkClassTime());
            keyValue.setCaption(classTime.getCaption());
        }
        object.put(linkEntityName, keyValue);
    }

    @Override
    public void setLinkManKeyValue(Page object, String pkId, String linkEntityName) {
//        Linkman linkman = new Linkman();
//        linkman.setPkStudent(pkId);
        List<Linkman> linkmanList = linkmanMapper.selectByStudent(pkId);
        if(linkmanList.size() > 0){
            object.put(linkEntityName, linkmanList.get(0));
        }else{
            object.put(linkEntityName, new Linkman());
        }
    }
    @Override
    public void setLinkManByKeyValue(Page object, String pkId, String linkEntityName) {
//        Linkman linkman = new Linkman();
//        linkman.setPkStudent(pkId);
        Linkman linkman = linkmanMapper.selectByPrimaryKey(pkId);
        KeyValue keyValue = new KeyValue();
        if (linkman != null) {
            keyValue.setId(linkman.getPkLinkman());
            keyValue.setCode(linkman.getCode());
            keyValue.setCaption(linkman.getCaption());
        }
        object.put(linkEntityName, keyValue);
    }

    @Override
    public void setStudentKeyValue(Page object, String pkId, String linkEntityName) {
//        Student studentEntity =  getStudentCache(pkId);//sysUserMapper.selectByPrimaryKey(pkId);
        Student studentEntity =  studentMapper.selectByPrimaryKey(pkId);
        KeyValue keyValue = new KeyValue();
        if (studentEntity != null) {
            keyValue.setId(studentEntity.getPkStudent());
            keyValue.setCode(studentEntity.getCode());
            keyValue.setCaption(studentEntity.getCaption());
        }
        object.put(linkEntityName, keyValue);
    }

    @Override
    public void setStudentValue(Page object, String pkId, String linkEntityName) {
        Student studentEntity =  studentMapper.selectByPrimaryKey(pkId);
        Map<String,Object> map = new HashMap<>();
        if (studentEntity != null) {
            map.put("id",studentEntity.getPkStudent());
            map.put("code",studentEntity.getStudentId());
            map.put("img",studentEntity.getImg());
            map.put("caption",studentEntity.getCaption());
        }
        object.put(linkEntityName, map);
    }

    /**
     * 学号
     * @param object
     * @param pkId
     * @param linkEntityName
     */
    @Override
    public void setStudentIdValue(Page object, String pkId, String linkEntityName) {
//        Student studentEntity =  getStudentCache(pkId);//sysUserMapper.selectByPrimaryKey(pkId);
        Student studentEntity =  studentMapper.selectByPrimaryKey(pkId);
        KeyValue keyValue = new KeyValue();
        if (studentEntity != null) {
            if (studentEntity.getStudentId() != null){
                keyValue.setCode(studentEntity.getStudentId());
            }
            keyValue.setId(studentEntity.getPkStudent());
            keyValue.setCaption(studentEntity.getCaption());
        }
        object.put(linkEntityName, keyValue);
    }

//    private HashMap<String,Student> studentMap;
    private Student getStudentCache(String pkId) {

        Student student = studentMapper.selectByPrimaryKey(pkId);
        return student;
//        Student student = new Student();
//        student.setPkStudent(pkId);
//        Student select = studentMapper.selectByPrimaryKey(pkId);
//        return select;
    }

    @Override
    public String getSysSetValue(String pkDomain, String pkSysSet) {
        Response response = Response.newResponse();
        SysSetKey sysSetKey = new SysSetKey();
        sysSetKey.setPkDomain(pkDomain);
        sysSetKey.setPkSysSet(pkSysSet);
        SysSet sysSet = sysSetMapper.selectByPrimaryKey(sysSetKey);
        if (sysSet != null) {
            return sysSet.getValue();
        }
        return "";
    }

    @Override
    public void setStudentScoreKeyValue(Page object, String pkClassinfo, String pkStudent, String studentScoreEntity) {
        ActivityStudent activityStudent = new ActivityStudent();
        activityStudent.setPkClassinfo(pkClassinfo);
        activityStudent.setPkStudent(pkStudent);
        List<ActivityStudent> select = activityStudentMapper.select(activityStudent);
        KeyValue keyValue = new KeyValue();
        if (select.size() > 0) {
            if(select.get(0).getScore()==null){
                keyValue.setCode(null);
            }else {
                keyValue.setCode(String.valueOf(select.get(0).getScore()));
            }
            keyValue.setId(select.get(0).getPkActivityStudent());
            keyValue.setCaption(select.get(0).getEvaluate());
        }
        object.put(studentScoreEntity, keyValue);
    }

    @Override
    public void setReceiptMoney(Page object, String pkReceivable, String receiptMoney) {
        Receipt receipt = new Receipt();
        receipt.setPkParent(pkReceivable);
        BigDecimal countMoney = receiptMapper.countMoney(receipt);
        KeyValue keyValue = new KeyValue();
        if (countMoney != null) {
            keyValue.setCode(String.valueOf(countMoney));
        }else{
            keyValue.setCode("0");
        }
        object.put(receiptMoney, keyValue);
    }

    @Override
    public void setClassInfoKeyValue(Page object, String pkClassinfo, String classInfoEntity) {
        Classinfo classinfo = classinfoMapper.selectByPrimaryKey(pkClassinfo);
        KeyValue keyValue = new KeyValue();
        if (classinfo != null) {
            keyValue.setId(classinfo.getPkClassinfo());
            keyValue.setCode(classinfo.getCode());
            keyValue.setCaption(classinfo.getCaption());
        }
        object.put(classInfoEntity, keyValue);
    }

    private boolean checkType(String type){
        return true;
    }
    @Override
    public void saveLog(Object oldObj, Object newObj, String tableName, int kind,String pkId, String operator) {
        List<SysLog> list = new ArrayList<>();
        Class clas_old = oldObj.getClass();
        Class clas_new = newObj.getClass();
        if (!(clas_old.getSimpleName()).equals(clas_new.getSimpleName())) {
            System.out.println("传入的两个java对象类型不一致！");
            return;
        }
        Field[] fields = clas_old.getDeclaredFields();
        for (Field field : fields) {
            try {
                String name = field.getName();
                String type = field.getType().getName();
                //       System.out.println("========================"+name);
                field.setAccessible(true); //设置些属性是可以访问的

                Object val_old = field.get(oldObj);//得到此属性的修改前值
                Object val_new = field.get(newObj);//得到此属性的修改后值
                //bigdecimal 类型的数据要去掉小数点后尾部的0不一致造成数据比对差异
                if (!val_old.equals(val_new) && val_new != null && checkType(type)) {
                    SysLog sysLog = new SysLog();
                    sysLog.setPkSysLog(GuidUtils.getGuid());
                    sysLog.setOldField(name);
                    sysLog.setOldFieldValue(val_old.toString());
                    sysLog.setNewField(name);
                    sysLog.setNewFieldValue(val_new.toString());
                    sysLog.setTableName(tableName);
                    sysLog.setKind(kind);
                    sysLog.setOperator(operator);
                    sysLog.setDatetime(new Date());
                    sysLog.setpkId(pkId);
                    //System.out.println("原值：" + val_old + ",新值:" + val_new);
                    list.add(sysLog);
                }
            } catch (Exception ex) {

            }
        }
        sysLogService.insert(list);
    }
    /**
     * 考试名称
     * @param object
     */
    @Override
    public void setScoreCaptionKeyValue(Page object, String pkStudentTestPlans, String scoreCaptionEntity) {
//        StudentPlansCaption studentPlansCaption = new StudentPlansCaption();
//        studentPlansCaption.setPkStudentTestPlans(pk_student_test_plans);
        StudentPlansCaption select = studentPlansCaptionMapper.selectByPrimaryKey(pkStudentTestPlans);
        KeyValue keyValue = new KeyValue();
        if (select != null) {
            keyValue.setId(select.getPkStudentTestPlans());
            keyValue.setCode(select.getCode());
            keyValue.setCaption(select.getCaption());
        }
        object.put(scoreCaptionEntity, keyValue);
    }

    @Override
    public void setClassinfoScheduleStatus(Page object, String pkClassinfo, String classinfoScheduleStatus) {
        Schedule schedule = new Schedule();
        schedule.setPkStudent(pkClassinfo);
        List<Schedule> select = scheduleMapper.select(schedule);
        KeyValue keyValue = new KeyValue();
        if(select.size() > 0){
            keyValue.setCode("1");
        }else{
            keyValue.setCode("2");
        }
        object.put(classinfoScheduleStatus, keyValue);
    }

    @Override
    public void setEmployeeDataValue(Page object, String pkEmployee, String empEntity) {
        Employee employee = employeeMapper.selectByPrimaryKey(pkEmployee);
        object.put(empEntity,employee);
    }

    @Override
    public void setRefundStatusKeyValue(Page object, String pkPayables, String refundStatus) {
        Payment payment = new Payment();
        payment.setPkParent(pkPayables);
        List<Payment> paymentList = paymentMapper.select(payment);
        KeyValue keyValue = new KeyValue();
        if(paymentList.size()>0){
            if(paymentList.get(0).getIsaudit() == 1){
                keyValue.setCode("1");
                object.put(refundStatus,keyValue);
            }else{
                keyValue.setCode("0");
                object.put(refundStatus,keyValue);
            }
        }else{
            keyValue.setCode("0");
            object.put(refundStatus,keyValue);
        }

    }

    @Override
    public void setDivisionKeyValue(Page object, String type, String divisionEntity) {
        SysDictValues sysDictValues = new SysDictValues();
        SysDictValues select = sysDictValuesMapper.selectByPrimaryKey(type);
        KeyValue keyValue = new KeyValue();
        if (select != null) {
            keyValue.setId(select.getPkSysDict());
            keyValue.setCaption(select.getCaption());
        }
        object.put(divisionEntity, keyValue);
    }

    @Override
    public void setSetMeal(Page object, String type, String divisionEntity) {
        TkSetMeal tkSetMeal= new TkSetMeal();
        TkSetMeal select = tkSetMealMapper.selectByPrimaryKey(type);
        KeyValue keyValue = new KeyValue();
        if(select != null){
            keyValue.setId(select.getPkSetMeal());
            keyValue.setCaption(select.getCaption());
        }
        object.put(divisionEntity, keyValue);
    }

}
