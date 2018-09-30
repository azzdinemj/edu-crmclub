package com.wuxue.api.service.impl;

import com.wuxue.api.mapper.*;
import com.wuxue.api.service.DatavalidService;
import com.wuxue.api.service.ScheduleService;
import com.wuxue.api.service.UtilsService;
import com.wuxue.api.utils.GuidUtils;
import com.wuxue.model.*;
import com.wuxue.utils.contract.Light;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

//import com.wuxue.api.utils.GuidUtils;

@Service("datavalidService")
public class DatavalidServiceImpl implements DatavalidService{

    @Autowired
    DatavalidMapper datavalidMapper;

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
            iReuslt=datavalidMapper.deleteByPrimaryKey(primaryKey);
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
    public Response find(Request<Datavalid> tParams) {
        Response response = Response.newResponse();
        Datavalid datavalid = tParams.getData();

        if(datavalid== null){
            return  response.PARAMS_ISNULL();
        }
        String primaryKey = datavalid.getId();
        if(primaryKey !=null && !primaryKey.equals("")){
        	Datavalid byPrimaryKey = datavalidMapper.selectByPrimaryKey(primaryKey);
            response.setData(byPrimaryKey);
        }else{
            List<Datavalid> scheduleList = datavalidMapper.select(datavalid);
            if(scheduleList.size()>0) {
                if (datavalid.getType() == 1) {//验证码
                    response.setData(scheduleList.get(0));
                }else if(datavalid.getType() == 0){//登录身份验证码
                    response.setData(scheduleList.get(0));
                }
            }else{
                response.setData(null);
            }
            //response.setTotal(paymentMapper.countBy(payment));

        }
        return response;
    }

    @Override
    public Response save(Request<Datavalid> tParams) {
        Response response = Response.newResponse();
        Datavalid datavalid = tParams.getData();

        if(datavalid== null){
            return  response.PARAMS_ISNULL();
        }
//        String primaryKey = datavalid.getId();
//        Datavalid select = null;
//        if (primaryKey != null && !primaryKey.equals("")) {
//            select = datavalidMapper.selectByPrimaryKey(primaryKey);
//        }

        int iReuslt = 1;
        String message= "";
        try {
//            if (select != null) {
//                //若存在记录，则更新记录  （删除后重新添加）
//                if(select.getPhone().equals(datavalid.getPhone())){
//                    datavalidMapper.deleteByPrimaryKey(select.getId());
//                }
//                datavalid.setCreateDate(new Date());
//                datavalid.setId(GuidUtils.getGuid());
//                iReuslt = datavalidMapper.insertSelective(datavalid);
//                response.setData(datavalid.getId()+","+datavalid.getPkempstu());
//            } else {

                //插入验证码
                if(datavalid.getType()==1){
                    iReuslt=datavalidMapper.countByphone24(datavalid);
                    if(iReuslt==5){
                        response.setCode(1);
                        response.setMessage("每日获取验证码次数上限");
                        return response;
                    }else{
                        datavalid.setCreateDate(new Date());
                        datavalid.setId(GuidUtils.getGuid());
                        iReuslt = datavalidMapper.insertSelective(datavalid);
                    }
                }else{  //登录返回码
                    List<Datavalid> list=datavalidMapper.select(datavalid);
                    if(list.size()>0){
                        datavalidMapper.deleteByPrimaryKey(list.get(0).getId()) ;
                    }
                    datavalid.setCreateDate(new Date());
                    datavalid.setId(GuidUtils.getGuid());
                    iReuslt = datavalidMapper.insertSelective(datavalid);
                    response.setData(datavalid.getId()+","+datavalid.getPkempstu());
                }

          //  }
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
