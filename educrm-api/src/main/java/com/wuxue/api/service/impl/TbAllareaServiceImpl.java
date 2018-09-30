package com.wuxue.api.service.impl;

import com.wuxue.api.mapper.TbAllareaMapper;
import com.wuxue.api.service.TbAllareaService;
import com.wuxue.model.TbAllarea;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("tbAllareaService")
public class TbAllareaServiceImpl implements TbAllareaService {

    @Autowired
    private TbAllareaMapper tbAllareaMapper;


    @Override
    public Response delete(Request<String> tParams) {
        return null;
    }

    @Override
    public Response find(Request<TbAllarea> tParams) {
        Response response = Response.newResponse();
        TbAllarea tbAllarea = tParams.getData();

        if (tbAllarea == null){
            return response.PARAMS_ISNULL();
        }

        Integer primaryKey = tbAllarea.getId();
        if(primaryKey != null){
            TbAllarea byPrimaryKey = tbAllareaMapper.selectByPrimaryKey(primaryKey);
            response.setData(byPrimaryKey);
        }else {
            List<TbAllarea> tbAllareaList = tbAllareaMapper.select(tbAllarea);
            response.setData(tbAllareaList);
        }


        return response;
    }

    @Override
    public Response save(Request<TbAllarea> tParams) {
        return null;
    }
}
