package com.wuxue.api.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wuxue.api.mapper.ClassRoomMapper;
import com.wuxue.api.mapper.ClassRoomMapper;
import com.wuxue.api.service.UtilsService;
import com.wuxue.model.ClassRoom;
import com.wuxue.api.service.ClassRoomService;
import com.wuxue.api.service.ClassRoomService;
import com.wuxue.api.utils.GuidUtils;
import com.wuxue.utils.contract.LinkEntity;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service("classRoomService")
public class ClassRoomServiceImpl implements ClassRoomService {
    @Autowired
    ClassRoomMapper classRoomMapper;

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
            iReuslt=classRoomMapper.deleteByPrimaryKey(primaryKey);
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
    public Response find(Request<ClassRoom> tParams) {
        Response response = Response.newResponse();
        ClassRoom classRoom = tParams.getData();

        if(classRoom== null){
            return  response.PARAMS_ISNULL();
        }
        String primaryKey = classRoom.getPkClassRoom();
        if(primaryKey !=null && !primaryKey.equals("")){
            classRoom = classRoomMapper.selectByPrimaryKey(primaryKey);
            utilsService.setSysUserKeyValue(classRoom,classRoom.getCreator(), LinkEntity.CREATOR_ENTITY);
            utilsService.setSysUserKeyValue(classRoom,classRoom.getModifier(), LinkEntity.MODIFIER_ENTITY);
            response.setData(classRoom);
        }else{
            PageHelper.startPage(classRoom.getPageNo(), classRoom.getPageSize());
            List<ClassRoom> list = classRoomMapper.select(classRoom);
            PageInfo page = new PageInfo(list);
            response.setTotal(page.getTotal());
            for (ClassRoom classRoomEntity : list) {
                utilsService.setSysUserKeyValue(classRoomEntity,classRoomEntity.getCreator(), LinkEntity.CREATOR_ENTITY);
                utilsService.setSysUserKeyValue(classRoomEntity,classRoomEntity.getModifier(),LinkEntity.MODIFIER_ENTITY);
            }
            response.setData(list);
        }
        return response;
    }

    @Override
    public Response save(Request<ClassRoom> tParams) {
        Response response = Response.newResponse();
        ClassRoom classRoom = tParams.getData();

        if(classRoom== null){
            return  response.PARAMS_ISNULL();
        }
        String primaryKey = classRoom.getPkClassRoom();
        ClassRoom select = null;
        if (primaryKey != null && !primaryKey.equals("")) {
            select = classRoomMapper.selectByPrimaryKey(primaryKey);
        }
        int iReuslt = 1;
        String message= "";
        try {
            if (select != null) {
                classRoom.setLasteditDate(new Date());
                iReuslt = classRoomMapper.updateByPrimaryKeySelective(classRoom);
            } else {
                classRoom.setPkClassRoom(GuidUtils.getGuid());
                classRoom.setCreationDate(new Date());
                classRoom.setLasteditDate(new Date());
                classRoom.setIsvalid(1);
                iReuslt = classRoomMapper.insertSelective(classRoom);
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
    public Response selectBy(Request<ClassRoom> tParams) {
        Response response = Response.newResponse();
        ClassRoom classRoom = tParams.getData();

        if(classRoom== null){
            return  response.PARAMS_ISNULL();
        }
            List<ClassRoom> list = classRoomMapper.select(classRoom);
            for (ClassRoom classRoomEntity : list) {
                utilsService.setSysUserKeyValue(classRoomEntity,classRoomEntity.getCreator(), LinkEntity.CREATOR_ENTITY);
                utilsService.setSysUserKeyValue(classRoomEntity,classRoomEntity.getModifier(),LinkEntity.MODIFIER_ENTITY);
            }
            response.setData(list);
        return response;
    }
}
