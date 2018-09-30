package com.wuxue.api.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wuxue.api.mapper.TkSetMealMapper;
import com.wuxue.api.service.TkSetMealService;
import com.wuxue.api.service.UtilsService;
import com.wuxue.api.utils.GuidUtils;
import com.wuxue.model.TkSetMeal;
import com.wuxue.utils.contract.LinkEntity;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service("tkSetMealService")
public class TkSetMealServiceImpl implements TkSetMealService{
    @Autowired
    TkSetMealMapper tkSetMealMapper;
    @Autowired
    UtilsService utilsService;

    @Override
    public Response delete(Request<String> tParams) {
        Response response = Response.newResponse();
        String primaryKey = tParams.getData();
        if(primaryKey== null || primaryKey.equals("")){
            return  response.PARAMS_ISNULL();
        }
        int iReuslt = 0;
        String message= "";
        try {
            TkSetMeal tkSetMeal = tkSetMealMapper.selectByPrimaryKey(primaryKey);
            if (tkSetMeal != null){
                tkSetMeal.setStatus(0);
                iReuslt=tkSetMealMapper.updateByPrimaryKeySelective(tkSetMeal);
            }

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
    public Response find(Request<TkSetMeal> tParams) {
        Response response = Response.newResponse();
        TkSetMeal tkSetMeal = tParams.getData();

        if(tkSetMeal== null){
            return  response.PARAMS_ISNULL();
        }
        String primaryKey = tkSetMeal.getPkSetMeal();
        if(primaryKey !=null && !primaryKey.equals("")){
            TkSetMeal byPrimaryKey = tkSetMealMapper.selectByPrimaryKey(primaryKey);
            utilsService.setSysUserKeyValue(byPrimaryKey,byPrimaryKey.getCreator(), LinkEntity.CREATOR_ENTITY);
            utilsService.setSysUserKeyValue(byPrimaryKey,byPrimaryKey.getModifier(),LinkEntity.MODIFIER_ENTITY);
            response.setData(byPrimaryKey);
        }else{
            PageHelper.startPage(tkSetMeal.getPageNo(),tkSetMeal.getPageSize());
            List<TkSetMeal> timeList = tkSetMealMapper.select(tkSetMeal);
            PageInfo page = new PageInfo(timeList);
            response.setTotal(page.getTotal());
            if (timeList.size() > 0) {
                for (TkSetMeal list : timeList) {
                    utilsService.setSysUserKeyValue(list, list.getCreator(), LinkEntity.CREATOR_ENTITY);
                    utilsService.setSysUserKeyValue(list, list.getModifier(), LinkEntity.MODIFIER_ENTITY);
                }
            }
            response.setData(timeList);
            //response.setTotal(tkSetMealMapper.countBy(tkSetMeal));
        }
        return response;
    }

    @Override
    public Response save(Request<TkSetMeal> tParams) {
        Response response = Response.newResponse();
        TkSetMeal tkSetMeal = tParams.getData();

        if(tkSetMeal== null){
            return  response.PARAMS_ISNULL();
        }
        String primaryKey = tkSetMeal.getPkSetMeal();
        TkSetMeal select = null;
        if (primaryKey != null && !primaryKey.equals("")) {
            select = tkSetMealMapper.selectByPrimaryKey(primaryKey);
        }
        int iReuslt = 1;
        String message= "";
        try {
            if (select != null) {
                tkSetMeal.setLasteditDate(new Date());
                iReuslt = tkSetMealMapper.updateByPrimaryKeySelective(tkSetMeal);
            } else {
                tkSetMeal.setPkSetMeal(GuidUtils.getGuid());
                tkSetMeal.setCreationDate(new Date());
                tkSetMeal.setLasteditDate(new Date());
                tkSetMeal.setStatus(1);
                iReuslt = tkSetMealMapper.insertSelective(tkSetMeal);
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
    public Response audit(Request<TkSetMeal> tParams) {
        return null;
    }

    @Override
    public Response cancel(Request<TkSetMeal> tParams) {
        return null;
    }

    @Override
    public Response submit(Request<TkSetMeal> tParams) {
        return null;
    }
}
