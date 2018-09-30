package com.wuxue.api.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wuxue.api.mapper.ExpenseItemMapper;
import com.wuxue.api.service.UtilsService;
import com.wuxue.base.Page;
import com.wuxue.model.ExpenseItem;
import com.wuxue.api.service.ExpenseItemService;
import com.wuxue.api.utils.GuidUtils;
import com.wuxue.utils.contract.LinkEntity;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

//import com.wuxue.api.utils.GuidUtils;

@Service("expenseItemService")
public class ExpenseItemServiceImpl implements ExpenseItemService{
    @Autowired
    ExpenseItemMapper expenseItemMapper;
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
            iReuslt=expenseItemMapper.deleteByPrimaryKey(primaryKey);
        }catch (Exception ex){
            message = ex.getMessage();
            return response.SAVE_FAIL(message);
        }
        if(iReuslt>0) {
            return response;
        }
        return response.DELETE_FAIL(message);
    }

    @Override
    public Response find(Request<ExpenseItem> tParams) {
        Response response = Response.newResponse();
        ExpenseItem expenseItem = tParams.getData();

        if(expenseItem== null){
            return  response.PARAMS_ISNULL();
        }
        String primaryKey = expenseItem.getPkExpenseItem();
        if(primaryKey !=null && !primaryKey.equals("")){
            ExpenseItem byPrimaryKey = expenseItemMapper.selectByPrimaryKey(primaryKey);
            utilsService.setSysUserKeyValue(byPrimaryKey,byPrimaryKey.getCreator(), LinkEntity.CREATOR_ENTITY);
            utilsService.setSysUserKeyValue(byPrimaryKey,byPrimaryKey.getModifier(),LinkEntity.MODIFIER_ENTITY);
            response.setData(byPrimaryKey);
        }else{
            PageHelper.startPage(expenseItem.getPageNo(),expenseItem.getPageSize());
            List<ExpenseItem> expenseItemList = expenseItemMapper.select(expenseItem);
            PageInfo pageInfo = new PageInfo(expenseItemList);
            response.setTotal(pageInfo.getTotal());
            if (expenseItemList.size() > 0) {
                for (ExpenseItem list : expenseItemList) {
                    utilsService.setSysUserKeyValue(list, list.getCreator(), LinkEntity.CREATOR_ENTITY);
                    utilsService.setSysUserKeyValue(list, list.getModifier(), LinkEntity.MODIFIER_ENTITY);
                }
            }
            response.setData(expenseItemList);
            //response.setTotal(expenseItemMapper.countBy(expenseItem));

        }
        return response;
    }

    @Override
    public Response save(Request<ExpenseItem> tParams) {
        Response response = Response.newResponse();
        ExpenseItem expenseItem = tParams.getData();

        if(expenseItem== null){
            return  response.PARAMS_ISNULL();
        }
        String primaryKey = expenseItem.getPkExpenseItem();
        ExpenseItem select = null;
        if (primaryKey != null && !primaryKey.equals("")) {
            select = expenseItemMapper.selectByPrimaryKey(primaryKey);
        }
        int iReuslt = 1;
        String message= "";
        try {
            if (select != null) {
                expenseItem.setLasteditDate(new Date());
                iReuslt = expenseItemMapper.updateByPrimaryKeySelective(expenseItem);
            } else {
//                expenseItem.setPkExpenseItem(GuidUtils.getGuid());
                expenseItem.setCreationDate(new Date());
                expenseItem.setLasteditDate(new Date());
                expenseItem.setIsvalid(1);
                iReuslt = expenseItemMapper.insertSelective(expenseItem);
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
