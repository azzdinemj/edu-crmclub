package com.wuxue.api.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wuxue.api.mapper.ClassinfoStudentMapper;
import com.wuxue.api.mapper.HolidayMapper;
import com.wuxue.api.service.HolidayService;
import com.wuxue.api.service.UtilsService;
import com.wuxue.model.junhwa.Holiday;
import com.wuxue.utils.common.DataUtils;
import com.wuxue.utils.contract.Light;
import com.wuxue.utils.contract.LinkEntity;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("holidayService")
public class HolidayServiceImpl implements HolidayService {

    @Autowired
    private HolidayMapper holidayMapper;
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
            holidayMapper.deleteByPrimaryKey(primaryKey);


        }catch (Exception e){
            message = e.getMessage();
            return response.DELETE_FAIL(message);
        }


        return response;
    }

    @Override
    public Response find(Request<Holiday> tParams) {
        Response response = Response.newResponse();
        Holiday data = tParams.getData();

        if (data== null){
            return response.PARAMS_ISNULL();
        }
        String message = "";
        Integer id = data.getId();
        try {
            if (id != null){
                Holiday holiday = holidayMapper.selectByPrimaryKey(id);
                utilsService.setSysDictKeyValue(holiday,holiday.getHolidayId(),LinkEntity.SYSDIC_ENTITY);
                response.setData(holiday);

            }else {
                PageHelper.startPage(data.getPageNo(),data.getPageSize());
                List<Holiday> select = holidayMapper.select(data);
                if (select != null && select.size()>0){
                    for (Holiday holiday : select) {
                        utilsService.setSysDictKeyValue(holiday,holiday.getHolidayId(),LinkEntity.SYSDIC_ENTITY);
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
    public Response save(Request<Holiday> tParams) {
        Response response = Response.newResponse();
        Holiday holiday = tParams.getData();
        if (holiday == null) {
            return response.PARAMS_ISNULL();
        }
        Integer primaryKey = holiday.getId();
        String message = "";
        Holiday select = null;
        if (primaryKey != null) {
            select = holidayMapper.selectByPrimaryKey(primaryKey);
        }

        try {
            if (select != null) {
                holidayMapper.updateByPrimaryKeySelective(holiday);
            } else {
                holidayMapper.insertSelective(holiday);

            }

        } catch (Exception ex) {
            message = ex.getMessage();
            return response.SAVE_FAIL(message);
        }

        return response;
    }

    @Transactional
    public Response saveAll(Request<Holiday> tParams) {
        Response response = Response.newResponse();
        Holiday holiday = tParams.getData();
        if (holiday == null) {
            return response.PARAMS_ISNULL();
        }

        Map<String, Object> map = holiday.getMap();

        String message = "";
        try {
            if (map !=null){
                List<Date> list = DataUtils.objectToList(map.get(Light.DATELIST), Date.class);
                if (list.size() ==0){
                    return response.PARAMS_ISNULL();
                }
                for (Date date : list) {
                    holiday.setHolidayDay(date);
                    holidayMapper.insertSelective(holiday);

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
