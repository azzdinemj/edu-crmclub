package com.wuxue.api.service.impl;

import com.wuxue.api.mapper.ParentPayMapper;
import com.wuxue.api.service.MealCountService;
import com.wuxue.api.service.UtilsService;
import com.wuxue.model.*;
import com.wuxue.utils.contract.LinkEntity;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("mealCountService")
public class MealCountServiceImpl implements MealCountService {

    @Autowired
    private ParentPayMapper parentPayMapper;
    @Autowired
    private UtilsService utilsService;


    @Override
    public Response delete(Request<String> tParams) {
        return null;
    }

    @Override
    public Response find(Request<ParentOrder> tParams) {

        Response response = Response.newResponse();

        ParentOrder parentOrder = tParams.getData();

        List<MealCount> list = parentPayMapper.selectCount(parentOrder);
        if (list.size() >0){
            for (MealCount mealCount : list) {
                utilsService.setMealKeyValue(mealCount,mealCount.getSetMealId(), LinkEntity.TKSETMEAL_ENTITY);
            }

        }

        response.setData(list);

        return response;
    }

    @Override
    public Response save(Request<MealCount> tParams) {
        return null;
    }
}
