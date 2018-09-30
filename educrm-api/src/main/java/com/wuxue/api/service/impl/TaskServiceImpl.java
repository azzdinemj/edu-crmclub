package com.wuxue.api.service.impl;

import com.wuxue.api.mapper.TaskMapper;
import com.wuxue.api.service.TaskService;
import com.wuxue.api.service.UtilsService;
import com.wuxue.api.utils.GuidUtils;
import com.wuxue.model.Task;
import com.wuxue.utils.contract.LinkEntity;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service("taskService")
public class TaskServiceImpl implements TaskService {
    @Autowired
    TaskMapper taskMapper;

    @Autowired
    UtilsService utilsService;

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
            iReuslt=taskMapper.deleteByPrimaryKey(primaryKey);
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
    public Response find(Request<Task> tParams) {
        Response response = Response.newResponse();
        Task task = tParams.getData();

        if(task== null){
            return  response.PARAMS_ISNULL();
        }
        String primaryKey = task.getPkTask();
        if(primaryKey !=null && !primaryKey.equals("")){
            task = taskMapper.selectByPrimaryKey(primaryKey);
            utilsService.setSysUserKeyValue(task,task.getCreator(), LinkEntity.CREATOR_ENTITY);
            utilsService.setSysUserKeyValue(task,task.getModifier(), LinkEntity.MODIFIER_ENTITY);
            response.setData(task);
        }else{
            List<Task> list = taskMapper.select(task);
            for (Task taskEntity : list) {
                utilsService.setSysUserKeyValue(taskEntity,taskEntity.getCreator(), LinkEntity.CREATOR_ENTITY);
                utilsService.setSysUserKeyValue(taskEntity,taskEntity.getModifier(),LinkEntity.MODIFIER_ENTITY);
            }
            response.setData(list);
        }
        return response;
    }

    @Override
    public Response save(Request<Task> tParams) {
        Response response = Response.newResponse();
        Task task = tParams.getData();

        if(task== null){
            return  response.PARAMS_ISNULL();
        }
        String primaryKey = task.getPkTask();
        Task select = null;
        if (primaryKey != null && !primaryKey.equals("")) {
            select = taskMapper.selectByPrimaryKey(primaryKey);
        }
        int iReuslt = 1;
        String message= "";
        try {
            if (select != null) {
                task.setLasteditDate(new Date());
                iReuslt = taskMapper.updateByPrimaryKeySelective(task);
            } else {
                task.setPkTask(GuidUtils.getGuid());
                task.setCreationDate(new Date());
                task.setLasteditDate(new Date());
                task.setIsdel(1);
                iReuslt = taskMapper.insertSelective(task);
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
