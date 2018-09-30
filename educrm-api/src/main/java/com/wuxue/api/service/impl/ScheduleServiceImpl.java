package com.wuxue.api.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wuxue.api.mapper.*;
import com.wuxue.api.service.ScheduleService;
import com.wuxue.api.service.UtilsService;
import com.wuxue.api.utils.GuidUtils;
import com.wuxue.model.*;
import com.wuxue.utils.common.DateTimeUtils;
import com.wuxue.utils.common.DateUtils;
import com.wuxue.utils.contract.Light;
import com.wuxue.utils.contract.LinkEntity;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.text.SimpleDateFormat;
import java.util.*;

//import com.wuxue.api.utils.GuidUtils;
@Service("scheduleService")
public class ScheduleServiceImpl implements ScheduleService {
    @Autowired
    ScheduleMapper scheduleMapper;
    @Autowired
    UtilsService utilsService;
    @Autowired
    StudentMapper studentMapper;
    @Autowired
    ProductMapper productMapper;
    @Autowired
    EmployeeMapper employeeMapper;
    @Autowired
    ClassRoomMapper classRoomMapper;
    @Autowired
    SysLogMapper syslogMapper;
    @Autowired
    private ClassTimeMapper classTimeMapper;
    @Autowired
    private ClassinfoMapper classinfoMapper;
    @Autowired
    private LinkmanMapper linkmanMapper;
    @Autowired
    private ClassinfoStudentMapper classinfoStudentMapper;

    @Autowired
    private TkProductOrderMapper tkProductOrderMapper;
    @Autowired
    private ScheduleConstraintMapper scheduleConstraintMapper;
    @Autowired
    private StudentLinkmanMapper studentLinkmanMapper;
    @Autowired
    private WeekTableDoubleMapper weekTableDoubleMapper;
    @Autowired
    private DivisionGradeMapper divisionGradeMapper;

    @Override
    public Response delete(Request<String> tParams) {
        Response response = Response.newResponse();
        String primaryKey = tParams.getData();
        if (primaryKey == null || primaryKey.equals("")) {
            return response.PARAMS_ISNULL();
        }
        int iReuslt = 1;
        String message = "";
        try {
            iReuslt = scheduleMapper.deleteByPrimaryKey(primaryKey);
        } catch (Exception ex) {
            message = ex.getMessage();
            return response.SAVE_FAIL(message);
        }
        if (iReuslt > 0) {
            return response;
        }
        return response.DELETE_FAIL(message);
    }

    @Override
    public Response find(Request<Schedule> tParams) {
        Response response = Response.newResponse();
        Schedule schedule = tParams.getData();

        if (schedule == null) {
            return response.PARAMS_ISNULL();
        }
        String primaryKey = schedule.getPkSchedule();
        if (primaryKey != null && !primaryKey.equals("")) {
            Schedule byPrimaryKey = scheduleMapper.selectByPrimaryKey(primaryKey);

            Student student = studentMapper.selectByPrimaryKey(byPrimaryKey.getPkStudent());
            if (student != null) {
                byPrimaryKey.put(Light.STUDENTOBJ, student.getCaption());
            } else {
                byPrimaryKey.put(Light.STUDENTOBJ, null);
            }

            Product product = productMapper.selectByPrimaryKey(byPrimaryKey.getPkProduct());
            if (product != null) {
                byPrimaryKey.put(Light.PRODUCTOBJ, product.getCaption());
            } else {
                byPrimaryKey.put(Light.PRODUCTOBJ, null);
            }

            Employee employee = employeeMapper.selectByPrimaryKey(byPrimaryKey.getPkEmployee());
            if (employee != null) {
                byPrimaryKey.put(Light.EMPLOYEEOBJ, employee.getCaption());
            } else {
                byPrimaryKey.put(Light.EMPLOYEEOBJ, null);
            }

            ClassRoom classRoom = classRoomMapper.selectByPrimaryKey(byPrimaryKey.getPkClassRoom());
            if (classRoom != null) {
                byPrimaryKey.put(Light.CLASSROOMOBJ, classRoom.getCaption());
            } else {
                byPrimaryKey.put(Light.CLASSROOMOBJ, null);
            }

            response.setData(byPrimaryKey);
        } else {
            Map<String, Object> map = schedule.getMap();
            Object obj = map.get(Light.COURSE);
            String listkey = "";
            if (obj != null) {
                listkey = obj.toString();
            }

            List<Schedule> scheduleList = new ArrayList<>();
            if ("listkey".equals(listkey)) {
                Date startTime = schedule.getStartTime();
                Date endTime = schedule.getEndTime();
                for (int i = 0; i < i + 1; i++) {
                    if (startTime.getTime() > endTime.getTime()) {
                        break;
                    }

                    schedule.setStartTime(startTime);
                    schedule.setEndTime(DateTimeUtils.getSplicingTime(startTime, endTime));

                    if (schedule.getCycle() == 1) {
                        startTime = DateUtils.getAfterDate(7, startTime);
                    }
                    if (schedule.getCycle() == 2) {
                        startTime = DateUtils.getAfterMonthDate(1, startTime);
                    }

                    List<Schedule> list = scheduleMapper.select(schedule);
                    scheduleList.addAll(list);
                }
            } else {
                scheduleList = scheduleMapper.select(schedule);
            }

            if (scheduleList.size() > 0) {
                for (Schedule list : scheduleList) {
                    Student student = studentMapper.selectByPrimaryKey(list.getPkStudent());
                    if (student != null) {
                        list.put(Light.STUDENTOBJ, student.getCaption());
                    } else {
                        list.put(Light.STUDENTOBJ, "无数据");
                    }
                    Product product = productMapper.selectByPrimaryKey(list.getPkProduct());
                    if (product != null) {
                        list.put(Light.PRODUCTOBJ, product.getCaption());
                    } else {
                        list.put(Light.PRODUCTOBJ, "无数据");
                    }
                    Employee employee = employeeMapper.selectByPrimaryKey(list.getPkEmployee());
                    if (employee != null) {
                        list.put(Light.EMPLOYEEOBJ, employee.getCaption());
                    } else {
                        list.put(Light.EMPLOYEEOBJ, "无数据");
                    }
                    ClassRoom classRoom = classRoomMapper.selectByPrimaryKey(list.getPkClassRoom());
                    if (classRoom != null) {
                        list.put(Light.CLASSROOMOBJ, classRoom.getCaption());
                    } else {
                        list.put(Light.CLASSROOMOBJ, "无数据");
                    }
                }
            }
            response.setData(scheduleList);
            //response.setTotal(paymentMapper.countBy(payment));

        }
        return response;
    }


    @Autowired
    TalkCloudRoomMapper talkCloudRoomMapper;
    @Autowired
    SysDictValuesMapper sysDictValuesMapper;

    /**
     * guanqiao  查找排课记录
     *
     * @param tParams
     * @return
     */
    @Override
    public Response findguanqiao(Request<Schedule> tParams) {
        Response response = Response.newResponse();
        Schedule schedule = tParams.getData();
        if (schedule == null) {
            return response.PARAMS_ISNULL();
        }
        String primaryKey = schedule.getPkSchedule();
        if (primaryKey != null && !primaryKey.equals("")) {
            Schedule byPrimaryKey = scheduleMapper.selectByPrimaryKey(primaryKey);

            Employee employee = employeeMapper.selectByPrimaryKey(byPrimaryKey.getPkEmployee());
            if (!"".equals(employee.getNationality()) && employee.getNationality() != null) {
                SysDictValues sysDictValues = sysDictValuesMapper.selectByPrimaryKey(employee.getNationality());
                employee.put("country", sysDictValues.getCaption());
            }

            Product product = productMapper.selectByPrimaryKey(byPrimaryKey.getPkProduct());
            TalkCloudRoom talkCloudRoom = talkCloudRoomMapper.selectByPrimaryKey(byPrimaryKey.getPkTalkCloudRoom());
            TkProductOrder tkProductOrder = new TkProductOrder();
            tkProductOrder.setPkSchedule(primaryKey);
            List<TkProductOrder> select = tkProductOrderMapper.select(tkProductOrder);
            byPrimaryKey.put("productOrder", select);

            byPrimaryKey.put("employee", employee);
            byPrimaryKey.put("product", product);
            byPrimaryKey.put("talkCloudRoom", talkCloudRoom);
            response.setData(byPrimaryKey);
        } else {
            PageHelper.startPage(schedule.getPageNo(), schedule.getPageSize());
            List<Schedule> scheduleList = scheduleMapper.select(schedule);

            for (Schedule schedule1 : scheduleList) {
                Employee employee = employeeMapper.selectByPrimaryKey(schedule1.getPkEmployee());
                if (!"".equals(employee.getNationality()) && employee.getNationality() != null) {
                    SysDictValues sysDictValues = sysDictValuesMapper.selectByPrimaryKey(employee.getNationality());
                    employee.put("country", sysDictValues.getCaption());
                }
                Product product = productMapper.selectByPrimaryKey(schedule1.getPkProduct());
                TalkCloudRoom talkCloudRoom = talkCloudRoomMapper.selectByPrimaryKey(schedule1.getPkTalkCloudRoom());
                TkProductOrder tkProductOrder = new TkProductOrder();
                tkProductOrder.setPkSchedule(schedule1.getPkSchedule());
                List<TkProductOrder> select = tkProductOrderMapper.select(tkProductOrder);
                schedule1.put("productOrder", select);
                schedule1.put("employee", employee);
                schedule1.put("product", product);
                schedule1.put("talkCloudRoom", talkCloudRoom);
            }

            PageInfo page = new PageInfo(scheduleList);
            response.setData(scheduleList);
            response.setTotal(page.getTotal());
        }
        return response;
    }


    /**
     * （专业，必修，选修）
     * guanqiao  查找排课记录
     * <p>
     * 根据课程查找出所有的老师，
     * 老师排重组建新集合，根据分页信息 查找老师列表 及该老师所有排课信息。
     *
     * @param tParams
     * @return
     */
    @Override
    public Response findguanqiaoBy(Request<Schedule> tParams) {
        Response response = Response.newResponse();
        Schedule schedule = tParams.getData();
        if (schedule == null) {
            return response.PARAMS_ISNULL();
        }
        String primaryKey = schedule.getPkProduct();
        if (primaryKey != null && !primaryKey.equals("")) {

            List<Employee> list = new LinkedList<Employee>();

            List<Schedule> schedule1 = scheduleMapper.select(schedule); //该课程的所有排课记录，
            for (Schedule s : schedule1) {
                Employee employee = employeeMapper.selectByPrimaryKey(s.getPkEmployee());
                if (schedule.getNationality() == null || schedule.getNationality().equals("")) {
                    //该课程的所有老师集合，无国籍条件
                    list.add(employee);
                } else if (schedule.getNationality().equals(employee.getNationality())) {
                    //该课程的所有老师集合，指定国籍条件
                    list.add(employee);
                }
            }

            list = removeDuplicteUsers(list);//去重

            Integer min = 0;
            Integer max = 0;

            if (schedule.getPageNo() == 1) {
                min = 0;
                max = schedule.getPageSize();
            } else {
                min = schedule.getPageSize() * schedule.getPageNo() - schedule.getPageSize();// 页码*单页数量-单页数量
                max = schedule.getPageSize() * schedule.getPageNo(); // 页码*单页数量
            }

            List<Employee> employeeList = new ArrayList<>();
            for (int j = min; j < max; j++) {
                Employee employee = new Employee();
                if (list.size() >= j + 1) {
                    employee = list.get(j);
                    Schedule schedule2 = new Schedule();
                    schedule2.setPkEmployee(employee.getPkEmployee());
                    schedule2.setPkProduct(schedule.getPkProduct());//该老师的所有排课

                    List<Schedule> scheduleList = scheduleMapper.select(schedule2);
                    employee.put("scheduleList", scheduleList);
                }
                if (employee.getPkEmployee() != null) {
                    employeeList.add(employee);
                }
            }
            response.setData(employeeList);
            response.setTotal(list.size());
        }
        return response;
    }

    @Override
    public Response selectByNotOrder(Request<Schedule> tParams) {
        Response response = Response.newResponse();
        Schedule schedule = tParams.getData();
        if (schedule == null) {
            return response.PARAMS_ISNULL();
        }
        List<Schedule> scheduleList = scheduleMapper.selectByNotOrder(schedule);
        response.setData(scheduleList);
        return response;
    }

    @Override
    public Response checkScheduleConstraint(Request<List<Schedule>> request) {
        Response response = Response.newResponse();

        List<Schedule> list = request.getData();
        checkScheduleConstraint(list);
//        response.setData(returnList);
        return response;
    }

    @Override
    public Response findElecSchedule(Request<Schedule> scheduleRequest) {

        Response response = Response.newResponse();
        Schedule data = scheduleRequest.getData();
        List<Schedule> list = scheduleMapper.selectElecSchedule(data);
        String pkStudent = "";
        Student student = studentMapper.selectByPrimaryKey(scheduleRequest.getCurrentUser());
        if (student != null) {
            pkStudent = student.getPkStudent();
        } else {
            StudentLinkmanKey studentLinkmanKey = studentLinkmanMapper.selectStuByParPhone(scheduleRequest.getCurrentUser());
            if (studentLinkmanKey != null) {
                pkStudent = studentLinkmanKey.getPkStudent();
            } else {
                Linkman linkman = linkmanMapper.selectByPhone(scheduleRequest.getCurrentUser());
                if (linkman != null) {
                    pkStudent = linkman.getPkStudent();
                }
            }
        }

        List<ClassinfoStudent> list1 = classinfoStudentMapper.selectByStudent(pkStudent);

        if (list != null && list.size() > 0) {
            for (Schedule schedule : list) {
                if (list1 != null && list1.size() > 0) {
                    for (ClassinfoStudent classinfoStudent : list1) {
                        if (schedule.getPkStudent().equals(classinfoStudent.getPkClassinfo())) {
                            schedule.put("isType", 1);
                        }
                    }
                }
                Classinfo classinfo = classinfoMapper.selectByPrimaryKey(schedule.getPkStudent());
                if (classinfo != null) {
                    schedule.put(LinkEntity.CLASS_INFO_ENTITY, classinfo);
                } else {
                    schedule.put(LinkEntity.CLASS_INFO_ENTITY, new Classinfo());
                }
                utilsService.setEmployeeKeyValue(schedule, schedule.getPkEmployee(), LinkEntity.EMP_ENTITY);
            }
        }

        response.setData(list);

        return response;
    }


    /**
     * 将列表中重复的用户移除，重复指的是pk相同
     *
     * @param userList
     * @return
     */
    public static ArrayList<Employee> removeDuplicteUsers(List<Employee> userList) {
        Set<Employee> s = new TreeSet<Employee>(new Comparator<Employee>() {

            @Override
            public int compare(Employee o1, Employee o2) {
                return o1.getPkEmployee().compareTo(o2.getPkEmployee());
            }
        });
        s.addAll(userList);
        return new ArrayList<Employee>(s);
    }

    @Override
    public Response selectBy(Request<Schedule> tParams) {
        Response response = Response.newResponse();
        Schedule schedule = tParams.getData();

        if (schedule == null) {
            return response.PARAMS_ISNULL();
        }

        Map<String, Object> map = schedule.getMap();
        Object obj = map.get(Light.COURSE);
        String listkey = "";
        if (obj != null) {
            listkey = obj.toString();
        }

        List<Schedule> scheduleList = new ArrayList<>();
        if ("listkey".equals(listkey)) {
            Date startTime = schedule.getStartTime();
            Date endTime = schedule.getEndTime();
            for (int i = 0; i < i + 1; i++) {
                if (startTime.getTime() > endTime.getTime()) {
                    break;
                }

                schedule.setStartTime(startTime);
                schedule.setEndTime(DateTimeUtils.getSplicingTime(startTime, endTime));

                if (schedule.getCycle() == 1) {
                    startTime = DateUtils.getAfterDate(7, startTime);
                }
                if (schedule.getCycle() == 2) {
                    startTime = DateUtils.getAfterMonthDate(1, startTime);
                }

                List<Schedule> list = scheduleMapper.select(schedule);
                scheduleList.addAll(list);
            }
        } else {
            PageHelper.startPage(schedule.getPageNo(), schedule.getPageSize());
            scheduleList = scheduleMapper.select(schedule);
            PageInfo pageInfo = new PageInfo(scheduleList);
            response.setTotal(pageInfo.getTotal());
        }

        if (scheduleList.size() > 0) {
            for (Schedule list : scheduleList) {
                Student student = studentMapper.selectByPrimaryKey(list.getPkStudent());
                if (student != null) {
                    list.put(Light.STUDENTOBJ, student.getCaption());
                } else {
                    list.put(Light.STUDENTOBJ, "无数据");
                }
                Product product = productMapper.selectByPrimaryKey(list.getPkProduct());
                if (product != null) {
                    list.put(Light.PRODUCTOBJ, product.getCaption());
                } else {
                    list.put(Light.PRODUCTOBJ, "无数据");
                }
                Employee employee = employeeMapper.selectByPrimaryKey(list.getPkEmployee());
                if (employee != null) {
                    list.put(Light.EMPLOYEEOBJ, employee.getCaption());
                    utilsService.setEmployeeKeyValue(list, list.getPkEmployee(), LinkEntity.EMP_ENTITY);
                } else {
                    list.put(Light.EMPLOYEEOBJ, "无数据");
                }
                ClassRoom classRoom = classRoomMapper.selectByPrimaryKey(list.getPkClassRoom());
                if (classRoom != null) {
                    list.put(Light.CLASSROOMOBJ, classRoom.getCaption());
                    utilsService.setClassRoomKeyValue(list, list.getPkClassRoom(), LinkEntity.CLASSROOM_ENTITY);
                } else {
                    list.put(Light.CLASSROOMOBJ, "无数据");
                }
            }
        }
        response.setData(scheduleList);

        return response;
    }

    @Override
    public Response save(Request<Schedule> tParams) {
        Response response = Response.newResponse();
        Schedule schedule = tParams.getData();

        if (schedule == null) {
            return response.PARAMS_ISNULL();
        }
        String primaryKey = schedule.getPkSchedule();
        Schedule select = null;
        if (primaryKey != null && !primaryKey.equals("")) {
            select = scheduleMapper.selectByPrimaryKey(primaryKey);
        }

        int iReuslt = 1;
        String message = "";
        try {
            if (select != null) {
                Schedule scheduleTmp = null;
                if (primaryKey != null && !primaryKey.equals("")) {

                    scheduleTmp = scheduleMapper.selectByPrimaryKey(primaryKey);
                }
                if (scheduleTmp != null) {
                    utilsService.saveLog(scheduleTmp, schedule, "schedule", 1, primaryKey, tParams.getCurrentUser());
                }

                schedule.setLasteditDate(new Date());

                iReuslt = scheduleMapper.updateByPrimaryKeySelective(schedule);

            } else {
                schedule.setPkSchedule(GuidUtils.getGuid());
                schedule.setCreationDate(new Date());
                schedule.setLasteditDate(new Date());


                iReuslt = scheduleMapper.insertSelective(schedule);
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
     * 检查冲突
     *
     * @param request 界面数据传入
     * @return 返回有冲突的界面数据
     */
    @Override
    public Response check(Request<List<Schedule>> request) {
        Response response = Response.newResponse();

        List<Schedule> list = request.getData();
//        response.setData(returnList);
        return check(list, request.getCurrendDomain());
    }


    /**
     * 检查冲突
     * sss
     *
     * @param list          对比数据
     * @param currentDomain 当前集团ID
     * @return 有冲突数据返回
     */
    Response check(List<Schedule> list, String currentDomain) {
        Response response = Response.newResponse();
        List<Schedule> returnList = new ArrayList<>();
//        if (list == null) {
//            return response;
//        }
        Date fromDate = null;
        Date toDate = null;
        Integer type = null;
        //获取排课范围
        for (Schedule schedule : list) {
            if (schedule.getPkSchedule() == null || schedule.getPkSchedule().equals("")) {
                schedule.setPkSchedule(GuidUtils.getGuid());
            }
            if (fromDate == null || fromDate.after(schedule.getStartTime())) {
                fromDate = schedule.getStartTime();
            }
            if (toDate == null || toDate.before(schedule.getEndTime())) {
                toDate = schedule.getEndTime();
            }
            if (type == null) {
                type = schedule.getType();
            }
        }
//        returnList = check(list, list, type); //自对比
//        if (returnList.size() < 1) {
        Schedule scheduleFind = new Schedule();
        scheduleFind.setPkDomain(currentDomain);
        scheduleFind.setFromDate(fromDate);
        scheduleFind.setToDate(toDate);
        scheduleFind.setgetType(type);
        List<Schedule> resultList = scheduleMapper.select(scheduleFind);

        response = check(list, resultList, type); //数据库中对比
//        }
        return response;
    }

    /**
     * 冲突对比
     *
     * @param requestSchedule  请求数据
     * @param responseSchedule Db数据
     * @param type             对比类型,目前有1:学生，2:班级
     * @return 有冲突的请求数据
     */
    Response check(List<Schedule> requestSchedule, List<Schedule> responseSchedule, Integer type) {
        Response response = Response.newResponse();
        String message = "";
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
//        List<Schedule> returnList = new ArrayList<Schedule>();
        for (Schedule schedule : requestSchedule) {
            for (Schedule resultSchedule : responseSchedule) {
                if (schedule.getPkSchedule().equals(resultSchedule.getPkSchedule())) {
                    continue;
                }
                if (type == 2) {
                    //不同班级不能同老师同教室同时间段
                    if ((schedule.getStartTime().before(resultSchedule.getEndTime()) && schedule.getEndTime().after(resultSchedule.getStartTime())) ||    //开始时间在区间内
                            (schedule.getEndTime().before(resultSchedule.getEndTime()) && schedule.getEndTime().after(resultSchedule.getStartTime()))        //结束时间在区间
                            ) {

                        if (schedule.getPkEmployee().equals(resultSchedule.getPkEmployee())) {
                            message = message + "周" + (DateUtils.dayForWeek(schedule.getStartTime()) - 1) + "," + sdf.format(schedule.getStartTime()) + "--" + sdf.format(schedule.getEndTime()) + "," + "老师已经有课,";
                            break;
                        }
                        if (schedule.getPkClassRoom().equals(resultSchedule.getPkClassRoom())) {
                            message = message + "周" + (DateUtils.dayForWeek(schedule.getStartTime()) - 1) + "," + sdf.format(schedule.getStartTime()) + "--" + sdf.format(schedule.getEndTime()) + "," + "教室已被占用,";
                            break;
                        }
                    }
                }
//                } else if (type.equals(1)) {
//                    //不同老师不能同教室同时间段
//                    if ((!schedule.getPkEmployee().equals(resultSchedule.getPkEmployee())) &&
//                            schedule.getPkClassRoom().equals(resultSchedule.getPkClassRoom()) &&
//                            (
//                                    (schedule.getStartTime().after(resultSchedule.getStartTime()) && schedule.getEndTime().before(resultSchedule.getStartTime())) ||    //开始时间在区间内
//                                            (schedule.getStartTime().after(resultSchedule.getEndTime()) && schedule.getEndTime().before(resultSchedule.getEndTime())) ||        //结束时间在区间
//                                            (schedule.getStartTime().before(resultSchedule.getStartTime()) && schedule.getEndTime().after(resultSchedule.getEndTime())) ||      //时间在区间内
//                                            (schedule.getStartTime().after(resultSchedule.getStartTime()) && schedule.getEndTime().before(resultSchedule.getEndTime()))         //在区间外
//                            )) {
//                        returnList.add(schedule);
//                        break;
//                    }
//                }
            }
        }
        if (!message.equals("")) {
            message = message + "请确认之后重新排课";
            response.setMessage(message);
            response.setCode(1026);
        }
        return response;
    }

    @Override
    public Response findClassDoubleSchodule(Request<ClassTime> scheduleRequest) {

        Response response = Response.newResponse();
        ClassTime classTime = scheduleRequest.getData();
        String pkClassinfo = "";
        Object obj = classTime.get(Light.CLASSINFO);
        if (obj != null) {
            pkClassinfo = obj.toString();
        }
        if (pkClassinfo == null || pkClassinfo.equals("")) {
            return response.PARAMS_ISNULL();
        }
        Classinfo classinfo = classinfoMapper.selectByPrimaryKey(pkClassinfo);
        if (classinfo.getDivision() == null || classinfo.getDivision().equals("")) {
            classTime.setType("201824828559806804");
        } else {
            classTime.setType(classinfo.getDivision());
        }
        List<ClassTime> classTimeList = classTimeMapper.select(classTime);
        if (classTimeList.size() > 0) {
//            for (int i = 0; i < classTimeList.size(); i++) {
            for (ClassTime classTime1 : classTimeList) {


                WeekTableDouble[] tableDoubles = new WeekTableDouble[7];
                WeekTableDouble weekTableDouble = new WeekTableDouble();
                weekTableDouble.setClassinfoid(pkClassinfo);
                weekTableDouble.setClasstimetype(classTime1.getSort());
                List<WeekTableDouble> select = weekTableDoubleMapper.select(weekTableDouble);
                if (select.size() > 0) {
                    for (WeekTableDouble tableDouble : select) {
                        tableDoubles[tableDouble.getWeekdaynum() - 1] = tableDouble;
                    }

                }
                for (int j = 0; j < tableDoubles.length; j++) {
                    if (tableDoubles[j] == null) {
                        tableDoubles[j] = new WeekTableDouble();
                    }
                }
                List<WeekTableDouble> list1 = Arrays.asList(tableDoubles);
                classTime1.put(LinkEntity.COURSE_ENTITY, list1);

            }
            response.setData(classTimeList);

        }


        return response;
    }

    @Override
    public Response findEmpDoubleSchodule(Request<ClassTime> scheduleRequest) {

        Response response = Response.newResponse();
        String currentUser = scheduleRequest.getCurrentUser();
//        String currentUser="liwunan";
        Employee employee = employeeMapper.selectByUser(currentUser);

        if (employee == null){
            return  response.PARAMS_ISNULL();
        }

        ClassTime classTime = scheduleRequest.getData();




//        List<ClassTime> classTimeList = classTimeMapper.selectMaxSort(classTime);
        classTime.setPkEmployee(employee.getPkEmployee());
        List<ClassTime> classTimeList = classTimeMapper.selectSort(classTime);

        if (classTimeList.size() > 0) {
//            for (int i = 0; i < classTimeList.size(); i++) {
            for (ClassTime classTime1 : classTimeList) {

                WeekTableDouble[] tableDoubles = new WeekTableDouble[7];
                WeekTableDouble weekTableDouble = new WeekTableDouble();
                weekTableDouble.setEmployeeid(employee.getPkEmployee());
                weekTableDouble.setClasstimetype(classTime1.getSort());
                List<WeekTableDouble> select = weekTableDoubleMapper.selectByEmployee(weekTableDouble);
                if (select.size() > 0) {
                    for (WeekTableDouble tableDouble : select) {
                        tableDoubles[tableDouble.getWeekdaynum() - 1] = tableDouble;
                    }

                }
                for (int j = 0; j < tableDoubles.length; j++) {
                    if (tableDoubles[j] == null) {
                        tableDoubles[j] = new WeekTableDouble();
                    }
                }
                List<WeekTableDouble> list1 = Arrays.asList(tableDoubles);
                classTime1.put(LinkEntity.COURSE_ENTITY, list1);

            }
            response.setData(classTimeList);

        }


        return response;
    }

    @Override
    public Response findElective(Request<Schedule> tParam) {
        Response response = Response.newResponse();
        Schedule data = tParam.getData();
        if (data == null){
            return response.PARAMS_ISNULL();
        }
        String pkStudent = data.getPkStudent();
        if (pkStudent == null || "".equals(pkStudent)){
            return response.PARAMS_ISNULL();
        }
        List<Map<String,Object>> list = scheduleMapper.findElective(pkStudent);
        if (list.size()>0){
            for (Map<String, Object> map : list) {
                Object pkClassinfo = map.get("pkClassinfo");
                if (pkClassinfo != null){
                    ClassinfoStudent classinfoStudent = new ClassinfoStudent();
                    classinfoStudent.setPkClassinfo(pkClassinfo.toString());
                    classinfoStudent.setPkStudent(pkStudent);
                    ClassinfoStudent classinfoStudent1 = classinfoStudentMapper.selectByPrimaryKey(classinfoStudent);
                    if (classinfoStudent1 != null){
                        map.put("isselect",1);
                    }
                }
                Object doubleGradeObj = map.get("doubleGrade");
                if (doubleGradeObj != null){
                    List<String> gradeName= new ArrayList<>();
                    String doubleGrade = doubleGradeObj.toString();
                    String[] split = doubleGrade.split(",");
                    if (split.length>0){
                        for (int i = 0; i < split.length; i++){
                            if (!"".equals(split[i])){
                                DivisionGrade divisionGrade = divisionGradeMapper.selectByPrimaryKey(Integer.parseInt(split[i]));
                                if (divisionGrade != null){
                                    gradeName.add(divisionGrade.getGradeName());
                                }
                            }
                        }
                    }
                    map.put("gradeName",gradeName);

                }
            }
        }
        response.setData(list);
        return response;
    }

    @Override
    public Response findElectiveintroduce(Request<Classinfo> classinfoRequest) {
        Response response = Response.newResponse();
        String message = "";
        Classinfo classinfo = classinfoRequest.getData();

        if (classinfo == null){
            return response.PARAMS_ISNULL();
        }
        String pkClassinfo = classinfo.getPkClassinfo();
        if (pkClassinfo ==null || "".equals(pkClassinfo)){
            return response.PARAMS_ISNULL();
        }
        String notes = classinfoMapper.selectNotesByPrimaryKey(pkClassinfo);
        response.setData(notes);
        return response;
    }

    @Override
    @Transactional
    public Response signUpElective(Request<ClassinfoStudent> request) {
        Response response = Response.newResponse();
        ClassinfoStudent data = request.getData();

        if (data == null || data.getPkClassinfo()== null || data.getPkStudent()==null || "".equals(data.getPkClassinfo()) || "".equals(data.getPkStudent())){
            return response.PARAMS_ISNULL();
        }

        try {

            int countNum = classinfoStudentMapper.selectCoountBykey(data);
            if (countNum >0){
                return response.SIGNUP_FAIL("您已选课程，请取消后再选");
            }

            ClassinfoStudent select = classinfoStudentMapper.selectByPrimaryKey(data);
            if (select != null && select.getIsvalid() == 1){
                return response.SIGNUP_FAIL("您已报名该课程");
            }else if (select != null && select.getIsvalid() != 1){
                data.setIsvalid(1);
                classinfoStudentMapper.updateByPrimaryKeySelective(data);
            }else {
                data.setIsvalid(1);
                data.setCreator(request.getCurrentUser());
                data.setCreationDate(new Date());
                data.setLasteditDate(new Date());
                data.setModifier(request.getCurrentUser());
                classinfoStudentMapper.insertSelective(data);
            }
            Classinfo classinfo = classinfoMapper.selectByPrimaryKey(data.getPkClassinfo());
            if (classinfo == null){
                return response.SIGNUP_FAIL("报名失败，该课程名额已满");
            }
            if (classinfo.getNum() == null){
                return response.SIGNUP_FAIL("报名失败，该课程名额已满");
            }
            int i = classinfoStudentMapper.selectCountByPkClassinfo(data.getPkClassinfo());
            if (i> classinfo.getNum()){
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return response.SIGNUP_FAIL("报名失败，该课程名额已满");
            }
            classinfo.setExistingNumbers(i);
            classinfoMapper.updateByPrimaryKeySelective(classinfo);

        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return response.SIGNUP_FAIL("报名失败，该课程名额已满");
        }



        return response;
    }

    @Override
    public Response deleteElective(Request<ClassinfoStudent> request) {
        Response response = Response.newResponse();
        ClassinfoStudent data = request.getData();
        String message = "";
        if (data== null || data.getPkClassinfo()==null || data.getPkStudent()== null || "".equals(data.getPkStudent()) || "".equals(data.getPkClassinfo())){
            return response.PARAMS_ISNULL();
        }
        try {
            int i = classinfoStudentMapper.deleteByPrimaryKey(data);
            if (i >0){
                Classinfo classinfo = classinfoMapper.selectByPrimaryKey(data.getPkClassinfo());
                if (classinfo != null && classinfo.getExistingNumbers() != null && classinfo.getExistingNumbers()>0){
                    classinfo.setExistingNumbers(classinfo.getExistingNumbers()-1);
                    classinfoMapper.updateByPrimaryKeySelective(classinfo);

                }
            }

        }catch (Exception e){
            message= e.getMessage();
            return response.DELETE_FAIL(message);
        }
        return response;
    }

    @Override
    public Response findClassSchodule(Request<ClassTime> request) {

        Response response = Response.newResponse();
        ClassTime classTime = request.getData();
        if (classTime == null) {
            return response.PARAMS_ISNULL();
        }
        String pkClassinfo = "";
        String pkEmployee = "";
        String pkStudent = "";
        Object obj = classTime.get(Light.CLASSINFO);
        if (obj != null) {
            pkClassinfo = obj.toString();
        }
        Object emp = classTime.get(Light.EMPLOYEE);

        if (emp != null) {
            pkEmployee = emp.toString();
        } else {
            String emp2 = classTime.getPkEmployee();
            if (emp2 != null && !"".equals(emp2)) {
                pkEmployee = emp2;
            }
        }

        String stu = classTime.getPkStudent();
        if (stu != null && !"".equals(stu)) {
            pkStudent = stu;
        }

//        String pkEmployee = classTime.get(Light.EMPLOYEE).toString();

        if (pkClassinfo != null && !"".equals(pkClassinfo)) {
            Classinfo classinfo = classinfoMapper.selectByPrimaryKey(pkClassinfo);
            String division = classinfo.getDivision();

            if (division == null) {
                classTime.setType("201824828559806804");
            } else {
                classTime.setType(classinfo.getDivision());
            }
            List<ClassTime> classTimeList = classTimeMapper.select(classTime);

            if (classTimeList.size() > 0) {
                List<Schedule> list = new ArrayList<>(7);

                for (ClassTime time : classTimeList) {
                    Schedule[] schedules = new Schedule[7];
                    Schedule schedule = new Schedule();
                    schedule.setPkClassTime(time.getPkClassTime());
                    schedule.setPkStudent(pkClassinfo);
                    List<Schedule> select = scheduleMapper.select(schedule);
                    if (select.size() > 0) {
                        for (Schedule schedule1 : select) {
                            if (schedule1.getSort() == time.getSort()) {
                                int i = DateUtils.dayForWeek(schedule1.getStartTime()) - 2;
                                if (i == -1) {
                                    i = 7;
                                }
                                utilsService.setSysDictKeyValue(schedule1, schedule1.getPkProduct(), LinkEntity.COURSE_ENTITY);
                                schedules[i] = schedule1;
                            }

                        }

                    }
                    for (int j = 0; j < 7; j++) {
                        if (schedules[j] == null) {
                            schedules[j] = new Schedule();
                        }
                    }
                    List<Schedule> list1 = Arrays.asList(schedules);
                    time.put(Light.COURSE_LESSON_CHAPTER, list1);
                }
            }
            response.setData(classTimeList);
        }
        if (pkEmployee != null && !"".equals(pkEmployee)) {
            Schedule[] schedules;
            classTime.setPkEmployee(pkEmployee);
            List<ClassTime> classTimeList = classTimeMapper.selectSort(classTime);

            if (classTimeList.size() > 0) {
                List<Schedule> list = new ArrayList<>(7);
                for (ClassTime time : classTimeList) {
                    schedules = new Schedule[7];
                    Schedule schedule = new Schedule();
                    schedule.setPkEmployee(pkEmployee);
                    List<Schedule> select = scheduleMapper.select(schedule);
                    if (select.size() > 0) {
                        for (Schedule schedule1 : select) {
                            if (time.getSort() == schedule1.getSort()) {
                                int i = DateUtils.dayForWeek(schedule1.getStartTime()) - 2;
                                if (i == -1) {
                                    i = 7;
                                }
                                utilsService.setSysDictKeyValue(schedule1, schedule1.getPkProduct(), LinkEntity.COURSE_ENTITY);

                                schedules[i] = schedule1;

                            }

                        }

                    }
                    for (int j = 0; j < 7; j++) {
                        if (schedules[j] == null) {
                            schedules[j] = new Schedule();
                        }
                    }
                    List<Schedule> list1 = Arrays.asList(schedules);
                    time.put(Light.COURSE_LESSON_CHAPTER, list1);
                }
            }

            response.setData(classTimeList);

        }

//        学生课表
        if (pkStudent != null && !"".equals(pkStudent)) {
            Classinfo classinfo = classinfoMapper.selectByPrimaryKey(pkClassinfo);
            ClassinfoStudent classinfoStudent = new ClassinfoStudent();
            classinfoStudent.setPkStudent(pkStudent);
            List<ClassinfoStudent> classinfoStudentList = classinfoStudentMapper.select(classinfoStudent);
            String pkclassinfo = "";
            if (classinfoStudentList.size() > 0) {
                String division = null;
                for (ClassinfoStudent classinfoStudent1 : classinfoStudentList) {
                    Classinfo byPrimaryKey = classinfoMapper.selectByPrimaryKey(classinfoStudent1.getPkClassinfo());
                    division = byPrimaryKey.getDivision();
                    pkclassinfo = pkclassinfo + "'" + classinfoStudent1.getPkClassinfo() + "'" + ",";
                }
                if (division == null) {
                    classTime.setType("201824828559806804");
                } else {
                    classTime.setType(division);
                }
                pkclassinfo = pkclassinfo.substring(0, pkclassinfo.length() - 1);
            } else {
                return response.SERVER_ERROR("无班级");
            }


            List<ClassTime> classTimeList = classTimeMapper.select(classTime);
            Schedule[] schedules;

            if (classTimeList.size() > 0) {
                List<Schedule> list = new ArrayList<>(7);
                for (ClassTime time : classTimeList) {
                    schedules = new Schedule[7];
                    Schedule schedule = new Schedule();
//                    schedule.setPkEmployee(pkEmployee);
                    schedule.setClassinfoList(pkclassinfo);
                    schedule.setStatus(999);
                    List<Schedule> select = scheduleMapper.select(schedule);
                    if (select.size() > 0) {
                        for (Schedule schedule1 : select) {
                            if (time.getSort() == schedule1.getSort()) {
                                int i = DateUtils.dayForWeek(schedule1.getStartTime()) - 2;
                                if (i == -1) {
                                    i = 7;
                                }
                                utilsService.setSysDictKeyValue(schedule1, schedule1.getPkProduct(), LinkEntity.COURSE_ENTITY);

                                schedules[i] = schedule1;

                            }

                        }

                    }
                    for (int j = 0; j < 7; j++) {
                        if (schedules[j] == null) {
                            schedules[j] = new Schedule();
                        }
                    }
                    List<Schedule> list1 = Arrays.asList(schedules);
                    time.put(Light.COURSE_LESSON_CHAPTER, list1);
                }
            }

            response.setData(classTimeList);

        }

        return response;
    }

    @Override
    @Transactional
    public Response saveAll(Request<List<Schedule>> tParams) {
        Response response = Response.newResponse();
        List<Schedule> scheduleList = tParams.getData();
        String pkClassinfo = "";
        if (scheduleList != null && scheduleList.size() > 0) {
            pkClassinfo = scheduleList.get(0).getPkClassinfo();
        }

        String pkStudent = "";
        Student student = studentMapper.selectByPrimaryKey(tParams.getCurrentUser());
        if (student != null) {
            pkStudent = student.getPkStudent();
        } else {
            StudentLinkmanKey studentLinkmanKey = studentLinkmanMapper.selectStuByParPhone(tParams.getCurrentUser());
            if (studentLinkmanKey != null) {
                pkStudent = studentLinkmanKey.getPkStudent();
            } else {
                Linkman linkman = linkmanMapper.selectByPhone(tParams.getCurrentUser());
                if (linkman != null) {
                    pkStudent = linkman.getPkStudent();
                }
            }
        }
        String message = "";
        try {

            scheduleMapper.deleteByPkClassinfo(pkClassinfo);
            Response check = check(scheduleList, tParams.getCurrendDomain());
            if (check.getCode() != 0) {
                return check;
            }
            Response response1 = checkScheduleConstraint(scheduleList);
            if (response1.getCode() != 0) {
                return response1;
            }


//            if (returnList.size() == 0) {
            if (scheduleList.size() > 0) {
                for (Schedule schedule : scheduleList) {

                    schedule.setCreator(tParams.getCurrentUser());
                    schedule.setModifier(tParams.getCurrentUser());
                    schedule.setCreationDate(new Date());
                    schedule.setLasteditDate(new Date());
                    schedule.setIsdel(0);
                    schedule.setStatus(0);
                    if (schedule.getPkClassinfo() == null) {
                        schedule.setPkStudent(pkStudent);
                    } else {
                        schedule.setPkStudent(schedule.getPkClassinfo());

                    }
                    scheduleMapper.insertSelective(schedule);
                }
//                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                weekTableDoubleMapper.deleteByPkClassinfo(pkClassinfo);
                weekTableDoubleMapper.insertBySchedule(pkClassinfo);

            } else {
                return response.PARAMS_ISNULL();
            }
//            } else {
//                response.setCode(500);
//                response.setData(returnList);
//                return response;
//            }
        } catch (Exception ex) {
            message = ex.getMessage();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return response.SAVE_FAIL(message);
        }


        return response;
    }


    @Override
    public Response saveLoop(Request<Schedule> tParams) {
        Response response = Response.newResponse();
        Schedule schedule = tParams.getData();
        if (schedule == null) {
            return response.PARAMS_ISNULL();
        }

        int iReuslt = 0;
        String message = "";
        try {
            Date startTime = schedule.getStartTime();
            Date endTime = schedule.getEndTime();
            Date fromTime = schedule.getFromTime();

            for (int i = 0; i < i + 1; i++) {
                if (fromTime.getTime() > schedule.getToTime().getTime()) {
                    break;
                }


                schedule.setStartTime(DateTimeUtils.getSplicingTime(fromTime, startTime));
                schedule.setEndTime(DateTimeUtils.getSplicingTime(fromTime, endTime));
                schedule.setPkSchedule(GuidUtils.getGuid());
                schedule.setCreationDate(new Date());
                schedule.setLasteditDate(new Date());
                schedule.setType(1);

                if (schedule.getCycle() == 1) {
                    fromTime = DateUtils.getAfterDate(7, fromTime);
                }
                if (schedule.getCycle() == 2) {
                    fromTime = DateUtils.getAfterMonthDate(1, fromTime);
                }

                iReuslt = scheduleMapper.insertSelective(schedule);
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
     * 约束验证
     */
    public Response checkScheduleConstraint(List<Schedule> scheduleList) {
        Response response = Response.newResponse();
        ScheduleConstraint scheduleConstraint = new ScheduleConstraint();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm");
        scheduleConstraint.setIsvalid(1);
        String messages = "";
        try {
            if (scheduleList.size() > 0) {
                List<ScheduleConstraint> constraints = scheduleConstraintMapper.select(scheduleConstraint);
                if (constraints.size() > 0) {
                    for (ScheduleConstraint constraint : constraints) {
                        for (Schedule schedule : scheduleList) {
                            if (constraint.getStartDate() != null && constraint.getEndDate() != null && constraint.getWeekday() != null && schedule.getStartTime() != null && schedule.getEndTime() != null) {
                                if ((DateUtils.dayForWeek(schedule.getStartTime()) - 1) == constraint.getWeekday()) {
                                    if (DateUtils.isInDates(sdf.format(schedule.getStartTime()), sdf.format(constraint.getStartDate()), sdf.format(constraint.getEndDate())) == true ||
                                            DateUtils.isInDates(sdf.format(schedule.getEndTime()), sdf.format(constraint.getStartDate()), sdf.format(constraint.getEndDate())) == true) {
                                        messages = messages + "周" + constraint.getWeekday() + "," + sdf1.format(constraint.getStartDate()) + "--" + sdf1.format(constraint.getEndDate()) + ",";
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception ex) {
            return response.SERVER_ERROR(ex.getMessage());
        }

        if (!messages.equals("")) {
            response.setCode(1025);
            messages = messages + "时间段不可排课";
            response.setMessage(messages);
        }

        return response;
    }
}
