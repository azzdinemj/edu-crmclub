package com.wuxue.api.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wuxue.api.mapper.ClassRoomMapper;
import com.wuxue.api.mapper.EmployeeMapper;
import com.wuxue.api.mapper.ProductMapper;
import com.wuxue.api.mapper.StudentMapper;
import com.wuxue.api.mapper.SysLogMapper;
import com.wuxue.api.service.SysLogService;
import com.wuxue.api.service.UtilsService;
import com.wuxue.model.*;
import com.wuxue.utils.contract.Light;
import com.wuxue.utils.contract.LinkEntity;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Jamie on 2018/4/15.
 */
@Service("sysLogService")
public class SysLogServiceImpl implements SysLogService {

    @Autowired
    private SysLogMapper sysLogMapper;
    @Autowired
    StudentMapper studentMapper;
    @Autowired
    ProductMapper productMapper;
    @Autowired
    EmployeeMapper employeeMapper;
    @Autowired
    ClassRoomMapper classRoomMapper;
    @Autowired
    UtilsService utilsService;

    @Override
    public void insert(List<SysLog> list) {
        for (SysLog sysLog : list) {
            try{
                sysLogMapper.insertSelective(sysLog);
            }catch (Exception ex){

            }
        }
    }
    
    @Override
    public Response find(Request<SysLog> tParams) {
        Response response = Response.newResponse();
        SysLog syslog = tParams.getData();

        if(syslog== null){
            return  response.PARAMS_ISNULL();
        }
        String primaryKey = syslog.getPkSysLog();
        if(primaryKey !=null && !primaryKey.equals("")){
        	SysLog byPrimaryKey = sysLogMapper.selectByPrimaryKey(primaryKey);

          


            response.setData(byPrimaryKey);
        }else{
//            PageHelper.startPage(syslog.getPageNo(), syslog.getPageSize());
            List<SysLog> syslogList = sysLogMapper.select(syslog);
//            PageInfo<SysLog> pageInfo = new PageInfo<>(syslogList);
//            response.setData(pageInfo);
            for (SysLog list : syslogList) {
            	if(list.getTableName().equals("schedule")) {
                switch (list.getOldField()) {
                    case "pkProduct":
                        Product oldp = productMapper.selectByPrimaryKey(list.getOldFieldValue());
                        Product newp = productMapper.selectByPrimaryKey(list.getNewFieldValue());
                        if (oldp != null) {
                            list.put("oldvalue", oldp.getCaption());
                        } else {
                            list.put("oldvalue", "");
                        }
                        if (newp != null) {
                            list.put("newvalue", newp.getCaption());
                        } else {
                            list.put("newvalue", "");
                        }
                        break;
                    case "pkStudent":
                        Student olds = studentMapper.selectByPrimaryKey(list.getOldFieldValue());
                        Student news = studentMapper.selectByPrimaryKey(list.getNewFieldValue());
                        if (olds != null) {
                            list.put("oldvalue", olds.getCaption());
                        } else {
                            list.put("oldvalue", "");
                        }
                        if (news != null) {
                            list.put("oldvalue", news.getCaption());
                        } else {
                            list.put("oldvalue", "");
                        }
                        break;
                    case "pkEmployee":
                        Employee olde = employeeMapper.selectByPrimaryKey(list.getOldFieldValue());
                        Employee newe = employeeMapper.selectByPrimaryKey(list.getNewFieldValue());
                        if (olde != null) {
                            list.put("oldvalue", olde.getCaption());
                        } else {
                            list.put("oldvalue", "");
                        }
                        if (newe != null) {
                            list.put("newvalue", newe.getCaption());
                        } else {
                            list.put("newvalue", "");
                        }
                        break;
                    case "pkClassRoom":
                        ClassRoom oldc = classRoomMapper.selectByPrimaryKey(list.getOldFieldValue());
                        ClassRoom newc = classRoomMapper.selectByPrimaryKey(list.getNewFieldValue());
                        if (oldc != null) {
                            list.put("oldvalue", oldc.getCaption());
                        } else {
                            list.put("oldvalue", "");
                        }
                        if (newc != null) {
                            list.put("newvalue", newc.getCaption());
                        } else {
                            list.put("newvalue", "");
                        }
                        break;
                }
                utilsService.setSysUserKeyValue(list, list.getOperator(), LinkEntity.CREATOR_ENTITY);
            }
            }
            response.setData(syslogList);
            //response.setTotal(paymentMapper.countBy(payment));

        }
        return response;
    }
}
