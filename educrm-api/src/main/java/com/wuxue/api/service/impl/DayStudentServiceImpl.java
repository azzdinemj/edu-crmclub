package com.wuxue.api.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wuxue.api.mapper.ClassinfoStudentMapper;
import com.wuxue.api.mapper.DayStudentMapper;
import com.wuxue.api.service.DayStudentService;
import com.wuxue.api.service.UtilsService;
import com.wuxue.model.junhwa.DayStudent;
import com.wuxue.utils.common.DataUtils;
import com.wuxue.utils.contract.Light;
import com.wuxue.utils.contract.LinkEntity;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;
import java.util.Map;

@Service("dayStudentService")
public class DayStudentServiceImpl implements DayStudentService {

    @Autowired
    private DayStudentMapper dayStudentMapper;
    @Autowired
    private ClassinfoStudentMapper classinfoStudentMapper;
    @Autowired
    private UtilsService utilsService;



    @Override
    public Response delete(Request<Integer> tParams) {
        Response response = Response.newResponse();
        String message = "";
        Integer primaryKey = tParams.getData();
        if (primaryKey == null){
            response.PARAMS_ISNULL();
        }
        try {
            DayStudent dayStudent = dayStudentMapper.selectByPrimaryKey(primaryKey);
            if (dayStudent == null){
                return response.PARAMS_ISNULL();
            }
            dayStudent.setIsvalid(0);
            dayStudentMapper.updateByPrimaryKeySelective(dayStudent);

        }catch (Exception e){
            message = e.getMessage();
            return response.DELETE_FAIL(message);
        }


        return response;
    }

    @Override
    public Response find(Request<DayStudent> tParams) {
        Response response = Response.newResponse();
        DayStudent data = tParams.getData();

        if (data== null){
            return response.PARAMS_ISNULL();
        }
        String message = "";
        Integer dayStudentId = data.getDayStudentId();
        try {
            if (dayStudentId != null){
                DayStudent dayStudent = dayStudentMapper.selectByPrimaryKey(dayStudentId);
                utilsService.setStudentIdValue(dayStudent,dayStudent.getPkStudent(), LinkEntity.STUDENT_ENTITY);
                utilsService.setClassInfoKeyValue(dayStudent,dayStudent.getPkClassinfo(),LinkEntity.CLASS_INFO_ENTITY);
                response.setData(dayStudent);

            }else {
                PageHelper.startPage(data.getPageNo(),data.getPageSize());
                List<DayStudent> select = dayStudentMapper.select(data);
                if (select != null && select.size()>0){
                    for (DayStudent dayStudent : select) {
                        utilsService.setStudentIdValue(dayStudent,dayStudent.getPkStudent(), LinkEntity.STUDENT_ENTITY);
                        utilsService.setClassInfoKeyValue(dayStudent,dayStudent.getPkClassinfo(),LinkEntity.CLASS_INFO_ENTITY);
                    }

                }
                PageInfo page = new PageInfo(select);
                response.setTotal(page.getTotal());
                response.setData(select);
            }

        }catch (Exception e){
            message = e.getMessage();
            return response.FIND_FAIL(message);
        }


        return response;
    }

    @Override
    public Response save(Request<DayStudent> tParams) {
        Response response = Response.newResponse();
        DayStudent dayStudent = tParams.getData();
        if (dayStudent == null) {
            return response.PARAMS_ISNULL();
        }
        Integer primaryKey = dayStudent.getDayStudentId();
        String message = "";
        DayStudent select = null;
        if (primaryKey != null) {
            select = dayStudentMapper.selectByPrimaryKey(primaryKey);
        }

        try {
            if (select != null) {
                dayStudentMapper.updateByPrimaryKeySelective(dayStudent);
            } else {
                dayStudent.setIsvalid(1);
                String s = classinfoStudentMapper.selectClassInfoIdByStudentId(dayStudent.getPkStudent());
                dayStudent.setPkClassinfo(s);
                dayStudentMapper.insertSelective(dayStudent);

            }

        } catch (Exception ex) {
            message = ex.getMessage();
            return response.SAVE_FAIL(message);
        }

        return response;
    }

    @Transactional
    public Response saveAll(Request<DayStudent> tParams) {
        Response response = Response.newResponse();
        DayStudent dayStudent = tParams.getData();
        if (dayStudent == null) {
            return response.PARAMS_ISNULL();
        }

        Map<String, Object> map = dayStudent.getMap();

        String message = "";
        try {
            if (map !=null){
                List<String> list = DataUtils.objectToList(map.get(Light.IDS), String.class);
                if (list.size() ==0){
                    return response.PARAMS_ISNULL();
                }
                for (String s : list) {
                    DayStudent data = new DayStudent();
                    data.setPkStudent(s);
                    data.setType(dayStudent.getType());
                    DayStudent select = dayStudentMapper.selectByUnique(data);
                    if (select != null){
                        dayStudent.setDayStudentId(select.getDayStudentId());
                        dayStudentMapper.updateByPrimaryKeySelective(dayStudent);
                    }else {
                        dayStudent.setPkStudent(s);
                        dayStudent.setIsvalid(1);
                        String pkClassinfo = classinfoStudentMapper.selectClassInfoIdByStudentId(s);
                        dayStudent.setPkClassinfo(pkClassinfo);
                        dayStudentMapper.insertSelective(dayStudent);
                    }

                }
            }

        } catch (Exception ex) {
            message = ex.getMessage();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return response.SAVE_FAIL(message);
        }

        return response;
    }
}
