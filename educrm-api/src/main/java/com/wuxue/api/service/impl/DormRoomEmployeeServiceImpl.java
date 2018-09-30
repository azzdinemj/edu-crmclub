package com.wuxue.api.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wuxue.api.mapper.*;
import com.wuxue.api.service.DormRoomEmployeeService;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("dormRoomEmployeeService")
public class DormRoomEmployeeServiceImpl implements DormRoomEmployeeService {

    @Autowired
    private DormRoomMapper dormRoomMapper;
    @Autowired
    private DormRoomEmployeeMapper dormRoomEmployeeMapper;
    @Autowired
    private EmployeeMapper employeeMapper;
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
    public Response delete(Request<DormRoomEmployee> tParams) {
        Response response = Response.newResponse();
        DormRoomEmployee dormRoomEmployee = tParams.getData();
        if (dormRoomEmployee == null){
            return response.PARAMS_ISNULL();
        }
        int iReuslt = 0;
        String message = "";
        try {

            DormRoomEmployee select = dormRoomEmployeeMapper.selectByPrimaryKey(dormRoomEmployee.getPkDormEmployee());
            DormRoom dormRoom = dormRoomMapper.selectByPrimaryKey(select.getPkDormRoom());
            if(select != null){
                select.setIsvalid(1);
                select.setLasteditDate(new Date());
                select.setExpireTime(new Date());
                select.setModifier(dormRoomEmployee.getModifier());
                iReuslt = dormRoomEmployeeMapper.updateByPrimaryKeySelective(select);
                if (iReuslt ==1){
                    //生成退费单
                    BigDecimal bigDecimal = new BigDecimal("0");
                    if (dormRoom.getExpenses() != null && dormRoom.getExpenses().compareTo(bigDecimal)>0){
                        Payables payables = new Payables();
                        payables.setPkDomain(select.getPkDomain());
                        payables.setPkPayables(GuidUtils.getGuid());
                        payables.setCode(Light.PAYABLES_TCODE);
                        payables.setPkStudent(select.getPkEmployee());
                        payables.setDate(new Date());
                        payables.setCost(dormRoom.getExpenses());
                        payables.setNotes("宿舍退宿");
                        payables.setCreator(dormRoomEmployee.getModifier());
                        payables.setModifier(dormRoomEmployee.getModifier());
                        payables.setCreationDate(new Date());
                        payables.setLasteditDate(new Date());
                        iReuslt = payablesMapper.insertSelective(payables);
                    }


                    //删除员工宿舍号
                    Employee employee = employeeMapper.selectByPrimaryKey(select.getPkEmployee());
                    employee.setDormRoom(null);
                    iReuslt = employeeMapper.updateByPrimaryKey(employee);

                    //宿舍人数改变
                    dormRoom.setCurrentNum(dormRoom.getCurrentNum()-1);
                    iReuslt=dormRoomMapper.updateByPrimaryKeySelective(dormRoom);
                    //生成任务
                    Task task = new Task();
                    task.setPkTask(GuidUtils.getGuid());
                    task.setContent("学生宿舍退费");
                    task.setEndDate(DateUtils.getAfterDate(3,new Date()));
                    task.setType(0);
                    task.setCreator(dormRoomEmployee.getModifier());
                    task.setModifier(dormRoomEmployee.getModifier());
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
    public Response find(Request<DormRoomEmployee> tParams) {
        Response response = Response.newResponse();
        DormRoomEmployee dormRoomEmployee = tParams.getData();
        if (dormRoomEmployee == null){
            return response.PARAMS_ISNULL();
        }
//        String primaryKey = schoolBusStudent.getPkSchooBusStudent();
        String primaryKey = dormRoomEmployee.getPkDormRoom();
        if (primaryKey != null &&!"".equals(primaryKey)){
            dormRoomEmployee.setIsvalid(1);
            PageHelper.startPage(dormRoomEmployee.getPageNo(),dormRoomEmployee.getPageSize());
            List<DormRoomEmployee> select = dormRoomEmployeeMapper.select(dormRoomEmployee);
            PageInfo pageInfo = new PageInfo(select);
            response.setTotal(pageInfo.getTotal());
            if (select.size()>0){
                for (DormRoomEmployee roomEmployee : select) {
                    Employee employee = employeeMapper.selectByPrimaryKey(roomEmployee.getPkEmployee());
                    if (employee != null){
                        utilsService.setSysDictKeyValue(employee,employee.getNation(),LinkEntity.NATION_ENTITY);
                        roomEmployee.put(LinkEntity.EMP_ENTITY,employee);
                    }

                }

            }

            response.setData(select);
        }

        return response;
    }


    @Override
    public Response save(Request<DormRoomEmployee> tParams) {

        Response response = new Response();
        DormRoomEmployee dormRoomEmployee = tParams.getData();

        if (dormRoomEmployee == null){
            return  response.PARAMS_ISNULL();
        }
        String primaryKey = dormRoomEmployee.getPkDormRoom();
        DormRoom select = null;
        if (primaryKey != null && !primaryKey.equals("")) {
            select = dormRoomMapper.selectByPrimaryKey(primaryKey);
        }


        String message = "";
        int iReuslt = 0;


        //学生入住
        Map<String, Object> map = dormRoomEmployee.getMap();
        if (map != null){
            List<Employee> employeeList = DataUtils.objectToList(map.get(Light.EMPLOYEE), Employee.class);
            try {
                for (Employee employee : employeeList) {
                    Employee employee1 = employeeMapper.selectByPrimaryKey(employee.getPkEmployee());
                    if (employee1 != null){
                        employee1.setDormRoom(select.getPkDormRoom());
                        employee1.setIsstay(1);//设置员工状态
                        iReuslt = employeeMapper.updateByPrimaryKeySelective(employee1);

                        //设置宿舍员工关系

                        dormRoomEmployee.setPkEmployee(employee1.getPkEmployee());
                        dormRoomEmployee.setPkDormEmployee(GuidUtils.getGuid());
                        dormRoomEmployee.setCreationDate(new Date());
                        dormRoomEmployee.setLasteditDate(new Date());
                        dormRoomEmployee.setCode(sysAutoCodeService.getCode(Light.SCHOOL_BUS_STUDENT_TCODE));
                        dormRoomEmployee.setIsvalid(0);
                        dormRoomEmployee.setDate(new Date());
                        iReuslt = dormRoomEmployeeMapper.insertSelective(dormRoomEmployee);

//                        生成应收单
                        BigDecimal bigDecimal = new BigDecimal("0");
                        if (select.getExpenses() != null && select.getExpenses().compareTo(bigDecimal)>0){
                            Receivable receivable = new Receivable();
                            receivable.setPkReceivable(GuidUtils.getGuid());
                            receivable.setPkDomain(select.getPkDomain());
                            receivable.setCode(sysAutoCodeService.getCode(Light.RECEIVABLE_TCODE));
                            receivable.setCost(select.getExpenses());
                            receivable.setPkStudent(employee1.getPkEmployee());
                            receivable.setCreator(dormRoomEmployee.getCreator());
                            receivable.setCreationDate(new Date());
                            receivable.setModifier(dormRoomEmployee.getCreator());
                            receivable.setLasteditDate(new Date());
                            receivable.setCurrency("CNY");
                            iReuslt = receivableMapper.insertSelective(receivable);
                            //生成任务
                            Task task = new Task();
                            task.setPkTask(GuidUtils.getGuid());
                            task.setContent("员工住宿缴费");
                            task.setEndDate(DateUtils.getAfterDate(3,new Date()));
                            task.setType(0);
                            task.setCreator(dormRoomEmployee.getCreator());
                            task.setModifier(dormRoomEmployee.getModifier());
                            task.setCreationDate(new Date());
                            task.setLasteditDate(new Date());
                            taskMapper.insertSelective(task);
                        }


                    }
                }
                Integer currentNum = select.getCurrentNum();
                if (currentNum == null){
                    select.setCurrentNum(employeeList.size());
                }else {
                    select.setCurrentNum(currentNum+employeeList.size());
                }

                //反写员工入住人数
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


}
