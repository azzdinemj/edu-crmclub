package com.wuxue.api.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wuxue.api.mapper.StudentMapper;
import com.wuxue.api.mapper.TkPayRecordsMapper;
import com.wuxue.api.mapper.TkSetMealMapper;
import com.wuxue.api.service.TkPayRecordsService;
import com.wuxue.api.service.UtilsService;
import com.wuxue.api.utils.GuidUtils;
import com.wuxue.model.Student;
import com.wuxue.model.TkPayRecords;
import com.wuxue.model.TkSetMeal;
import com.wuxue.utils.contract.Light;
import com.wuxue.utils.contract.LinkEntity;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service("tkPayRecordsService")
public class TkPayRecordsServiceImpl implements TkPayRecordsService{
    @Autowired
    TkPayRecordsMapper tkPayRecordsMapper;
    @Autowired
    UtilsService utilsService;
    @Autowired
    TkSetMealMapper tkSetMealMapper;
    @Autowired
    private StudentMapper studentMapper;

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
            TkPayRecords tkPayRecords = tkPayRecordsMapper.selectByPrimaryKey(primaryKey);
            if (tkPayRecords != null){
                iReuslt=tkPayRecordsMapper.deleteByPrimaryKey(primaryKey);
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
    public Response find(Request<TkPayRecords> tParams) {
        Response response = Response.newResponse();
        TkPayRecords tkPayRecords = tParams.getData();

        if(tkPayRecords== null){
            return  response.PARAMS_ISNULL();
        }
        String primaryKey = tkPayRecords.getPkPayRecords();
        if(primaryKey !=null && !primaryKey.equals("")){
            TkPayRecords byPrimaryKey = tkPayRecordsMapper.selectByPrimaryKey(primaryKey);
            if (byPrimaryKey != null){
                utilsService.setSysUserKeyValue(byPrimaryKey,byPrimaryKey.getCreator(), LinkEntity.CREATOR_ENTITY);
                utilsService.setSysUserKeyValue(byPrimaryKey,byPrimaryKey.getModifier(),LinkEntity.MODIFIER_ENTITY);
                utilsService.setSetMeal(byPrimaryKey,byPrimaryKey.getPkSetMeal(),LinkEntity.TKSETMEAL_ENTITY);
                utilsService.setStudentKeyValue(byPrimaryKey,byPrimaryKey.getPkStudent(),LinkEntity.STUDENT_ENTITY);
                TkSetMeal tkSetMeal = tkSetMealMapper.selectByPrimaryKey(byPrimaryKey.getPkSetMeal());
                List<TkSetMeal> list = new ArrayList<>();
                if (byPrimaryKey.getPkSetMeal() != null  &&!"".equals(byPrimaryKey.getPkSetMeal())){
                    String[] split = byPrimaryKey.getPkSetMeal().split(",");
                    for (String s : split) {
                        if(s != null && !"".equals(s)){
                            TkSetMeal tkSetMeal1 = tkSetMealMapper.selectByPrimaryKey(s);
                            list.add(tkSetMeal1);
                        }
                    }
                    byPrimaryKey.put(Light.TKSETMEAL,list);
                }

            }


            response.setData(byPrimaryKey);
        }else{
            // bug修复， 查询学生的支付记录，只需要传入学生主键即可！ pkStudent
//            String currentUser = tParams.getCurrentUser();
//            Student student = studentMapper.selectByPhone(currentUser);
//            PageHelper.startPage(tkPayRecords.getPageNo(),tkPayRecords.getPageSize());
//            if(student != null ){
//                tkPayRecords.setPkStudent(student.getPkStudent());
//            }
            List<TkPayRecords> timeList = tkPayRecordsMapper.select(tkPayRecords);
            PageInfo page = new PageInfo(timeList);
            response.setTotal(page.getTotal());
            if (timeList.size() > 0) {
                for (TkPayRecords list : timeList) {
                    utilsService.setSysUserKeyValue(list, list.getCreator(), LinkEntity.CREATOR_ENTITY);
                    utilsService.setSysUserKeyValue(list, list.getModifier(), LinkEntity.MODIFIER_ENTITY);
                    utilsService.setSetMeal(list,list.getPkSetMeal(),LinkEntity.TKSETMEAL_ENTITY);
                    utilsService.setStudentKeyValue(list,list.getPkStudent(),LinkEntity.STUDENT_ENTITY);
//                    TkSetMeal tkSetMeal = tkSetMealMapper.selectByPrimaryKey(list.getPkSetMeal());
//                    if (tkSetMeal != null){
//                        list.put(Light.TKSETMEAL,tkSetMeal.getNumber());
//                    }

                    //一个支付记录内包含多个套餐，计算这些套餐的总课时 Number
                    if (list.getPkSetMeal() != null  &&!"".equals(list.getPkSetMeal())){
                        String[] split = list.getPkSetMeal().split(",");
                        int i=0;
                        for (String s : split) {
                            if(s != null && !"".equals(s)){
                                TkSetMeal tkSetMeal1 = tkSetMealMapper.selectByPrimaryKey(s);
                                i=i+tkSetMeal1.getNumber();
                            }
                        }
                        list.put(Light.TKSETMEAL,i);
                    }

                }
            }
            response.setData(timeList);
            //response.setTotal(tkPayRecordsMapper.countBy(tkPayRecords));
        }
        return response;
    }

    @Override
    public Response save(Request<TkPayRecords> tParams) {
        Response response = Response.newResponse();
        TkPayRecords tkPayRecords = tParams.getData();

        if(tkPayRecords== null){
            return  response.PARAMS_ISNULL();
        }
        String primaryKey = tkPayRecords.getPkPayRecords();
        TkPayRecords select = null;
        if (primaryKey != null && !primaryKey.equals("")) {
            select = tkPayRecordsMapper.selectByPrimaryKey(primaryKey);
        }
        int iReuslt = 1;
        String message= "";
        try {
            if (select != null) {
                tkPayRecords.setLasteditDate(new Date());
                iReuslt = tkPayRecordsMapper.updateByPrimaryKeySelective(tkPayRecords);
                response.setData(tkPayRecords);
            } else {
                tkPayRecords.setPkPayRecords(GuidUtils.getGuid());
                tkPayRecords.setCreationDate(new Date());
                tkPayRecords.setLasteditDate(new Date());
                iReuslt = tkPayRecordsMapper.insertSelective(tkPayRecords);
                response.setData(tkPayRecords);
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
    public Response audit(Request<TkPayRecords> tParams) {
        return null;
    }

    @Override
    public Response cancel(Request<TkPayRecords> tParams) {
        return null;
    }

    @Override
    public Response submit(Request<TkPayRecords> tParams) {
        return null;
    }

    @Override
    public void updateStatus(TkPayRecords tkPayRecords) {
        TkPayRecords byPrimaryKey = tkPayRecordsMapper.selectByPrimaryKey(tkPayRecords.getPkPayRecords());
        if(byPrimaryKey != null){
            byPrimaryKey.setStatus(tkPayRecords.getStatus());
            tkPayRecordsMapper.updateByPrimaryKeySelective(byPrimaryKey);
        }
    }
}
