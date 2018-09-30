package com.wuxue.api.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wuxue.api.mapper.DomainMapper;
import com.wuxue.api.mapper.EmployeeMapper;
import com.wuxue.api.mapper.ScheduleConstraintMapper;
import com.wuxue.api.service.DomainService;
import com.wuxue.api.service.ScheduleConstraintService;
import com.wuxue.api.utils.GuidUtils;
import com.wuxue.model.Domain;
import com.wuxue.model.Employee;
import com.wuxue.model.ScheduleConstraint;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("scheduleConstraintService")
public class ScheduleConstraintServiceImpl implements ScheduleConstraintService {

    @Autowired
    private ScheduleConstraintMapper scheduleConstraintMapper;
    @Autowired
    DomainMapper domainMapper;
    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public Response delete(Request<Long> tParams) {
        Response response = Response.newResponse();

        Long primaryKey = tParams.getData();
        if (primaryKey == null || "".equals(primaryKey)) {
            return response.PARAMS_ISNULL();
        }
        String message = "";
        try {
            ScheduleConstraint scheduleConstraint = scheduleConstraintMapper.selectByPrimaryKey(primaryKey);
            if (scheduleConstraint != null) {
                scheduleConstraint.setIsvalid(0);
                scheduleConstraintMapper.updateByPrimaryKeySelective(scheduleConstraint);
            }


        } catch (Exception ex) {
            message = ex.getMessage();
            return response.DELETE_FAIL(message);
        }


        return response;
    }

    @Override
    public Response find(Request<ScheduleConstraint> tParams) {
        Response response = Response.newResponse();
        ScheduleConstraint scheduleConstraint = tParams.getData();
        if (scheduleConstraint == null) {
            return response.PARAMS_ISNULL();
        }
        Long primaryKey = scheduleConstraint.getConstraintId();
        if (primaryKey != null) {
            ScheduleConstraint scheduleConstraint1 = scheduleConstraintMapper.selectByPrimaryKey(primaryKey);
            if (scheduleConstraint1.getType()==0){
                Domain domain = domainMapper.selectByPrimaryKey(scheduleConstraint1.getTargetId());
                if (domain != null){
                    scheduleConstraint1.setTargetName(domain.getCaption());
                }
            }else if(scheduleConstraint1.getType()==1){
                Employee employee = employeeMapper.selectByPrimaryKey(scheduleConstraint1.getTargetId());
                if(employee != null){
                    scheduleConstraint1.setTargetName(employee.getCaption());
                }

            }

            response.setData(scheduleConstraint1);
        } else {
            PageHelper.startPage(scheduleConstraint.getPageNo(), scheduleConstraint.getPageSize());
            List<ScheduleConstraint> list = scheduleConstraintMapper.select(scheduleConstraint);
            if (list.size() >0){
                for (ScheduleConstraint constraint : list) {
                    if (constraint.getType()==0){
                        Domain domain = domainMapper.selectByPrimaryKey(constraint.getTargetId());
                        if (domain != null){
                            constraint.setTargetName(domain.getCaption());
                        }
                    }else if(constraint.getType()==1){
                        Employee employee = employeeMapper.selectByPrimaryKey(constraint.getTargetId());
                        if(employee != null){
                            constraint.setTargetName(employee.getCaption());
                        }

                    }
                }
            }
            PageInfo page = new PageInfo(list);
            response.setTotal(page.getTotal());
            response.setData(list);
        }


        return response;
    }

    @Override
    public Response save(Request<ScheduleConstraint> tParams) {
        Response response = Response.newResponse();
        ScheduleConstraint scheduleConstraint = tParams.getData();
        if (scheduleConstraint == null) {
            return response.PARAMS_ISNULL();
        }
        Long primaryKey = scheduleConstraint.getConstraintId();
        String message = "";
        ScheduleConstraint select = null;
        if (primaryKey != null && !"".equals(primaryKey)) {
            select = scheduleConstraintMapper.selectByPrimaryKey(primaryKey);
        }

        try {
            if (select != null) {
                scheduleConstraint.setLasteditDate(new Date());
                scheduleConstraintMapper.updateByPrimaryKeySelective(scheduleConstraint);
            } else {
                scheduleConstraint.setCreationDate(new Date());
                scheduleConstraint.setLasteditDate(new Date());
                scheduleConstraint.setIsvalid(1);
                scheduleConstraintMapper.insertSelective(scheduleConstraint);
            }

        } catch (Exception ex) {
            message = ex.getMessage();
            return response.SAVE_FAIL(message);
        }

        return response;
    }
}
