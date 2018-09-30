package com.wuxue.api.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wuxue.api.mapper.*;
import com.wuxue.api.service.TkLearnRecordsService;
import com.wuxue.api.utils.GuidUtils;
import com.wuxue.model.*;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Auther: wh
 * @Date: 2018/6/13 14:32
 * @Description: 学习记录 service
 */
@Service("tkLearnRecordsService")
public class TkLearnRecordsServiceImpl implements TkLearnRecordsService {

    @Autowired
    TkLearnRecordsMapper tkLearnRecordsMapper;

    /**
     * 删除学习记录
     * @param tParams
     * @return
     */
    @Override
    public Response delete(Request<TkLearnRecords> tParams) {
        Response response = Response.newResponse();
        TkLearnRecords primaryKey = tParams.getData();
        if (primaryKey.getPkLearnRecords() == null || primaryKey.equals("")) {
            return response.PARAMS_ISNULL();
        }
        int iReuslt = 1;
        String message = "";
        try {
            iReuslt = tkLearnRecordsMapper.deleteByPrimaryKey(primaryKey.getPkLearnRecords());
        } catch (Exception ex) {
            message = ex.getMessage();
            return response.DELETE_FAIL(message);
        }
        if (iReuslt > 0) {
            return response;
        }
        return response.DELETE_FAIL(message);
    }


    @Autowired
    ScheduleMapper scheduleMapper;

    @Autowired
    ProductMapper productMapper;

    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    TalkCloudRoomMapper talkCloudRoomMapper;
    @Autowired
    SysDictValuesMapper sysDictValuesMapper;

    /**
     * 查找 收藏记录
     * @param tParams
     * @return
     */
    @Override
    public Response find(Request<TkLearnRecords> tParams) {
        Response response = Response.newResponse();
        TkLearnRecords tkLearnRecords = tParams.getData();

        if (tkLearnRecords == null) {
            return response.PARAMS_ISNULL();
        }
        String primaryKey = tkLearnRecords.getPkLearnRecords();
        if (primaryKey != null && !primaryKey.equals("")) {
            TkLearnRecords byPrimaryKey = tkLearnRecordsMapper.selectByPrimaryKey(primaryKey);

            Schedule schedule=scheduleMapper.selectByPrimaryKey(byPrimaryKey.getPkSchedule());//排课记录
            Product product=productMapper.selectByPrimaryKey(schedule.getPkProduct());//排课中的课程
            Employee employee=employeeMapper.selectByPrimaryKey(schedule.getPkEmployee());//排课中的老师
            TalkCloudRoom talkCloudRoom=talkCloudRoomMapper.selectByPrimaryKey(schedule.getPkTalkCloudRoom());


            if(product.getProductTypetalkcloudroom().equals(0)){
                SysDictValues sysDictValues= sysDictValuesMapper.selectByPrimaryKey(product.getProductType());
                byPrimaryKey.put("sysDictValues",sysDictValues.getCaption());
            }else{
                byPrimaryKey.put("sysDictValues","公开课");
            }
            byPrimaryKey.put("talkCloudRoom",talkCloudRoom);
            byPrimaryKey.put("schedule",schedule);
            byPrimaryKey.put("product",product);
            byPrimaryKey.put("employee",employee);
            response.setData(byPrimaryKey);
        } else {
            PageHelper.startPage(tkLearnRecords.getPageNo(), tkLearnRecords.getPageSize());
            List<TkLearnRecords> tkLearnRecordsList = tkLearnRecordsMapper.select(tkLearnRecords);

            for (TkLearnRecords t:tkLearnRecordsList) {
                Schedule schedule=scheduleMapper.selectByPrimaryKey(t.getPkSchedule());//排课记录
                Product product=productMapper.selectByPrimaryKey(schedule.getPkProduct());//排课中的课程
                Employee employee=employeeMapper.selectByPrimaryKey(schedule.getPkEmployee());//排课中的老师
                TalkCloudRoom talkCloudRoom=talkCloudRoomMapper.selectByPrimaryKey(schedule.getPkTalkCloudRoom());

                if(product.getProductTypetalkcloudroom().equals(0)){
                    SysDictValues sysDictValues= sysDictValuesMapper.selectByPrimaryKey(product.getProductType());
                    t.put("sysDictValues",sysDictValues.getCaption());
                }else{
                    t.put("sysDictValues","公开课");
                }
                t.put("talkCloudRoom",talkCloudRoom);
                t.put("schedule",schedule);
                t.put("product",product);
                t.put("employee",employee);
            }
            PageInfo page = new PageInfo(tkLearnRecordsList);
            response.setData(tkLearnRecordsList);
            response.setTotal(page.getTotal());
        }
        return response;
    }

    @Autowired
    TkProductOrderMapper tkProductOrderMapper;
    @Override
    public Response save(Request<TkLearnRecords> tParams) {
        Response response = Response.newResponse();
        TkLearnRecords tkLearnRecords = tParams.getData();
        if(tkLearnRecords== null){
            return  response.PARAMS_ISNULL();
        }
        int iReuslt = 1;
        String message= "";
        String pk=tkLearnRecords.getPkLearnRecords();
        try {
            if (pk != null&&!"".equals(pk)) {
                iReuslt = tkLearnRecordsMapper.updateByPrimaryKeySelective(tkLearnRecords);
            } else {
                List<TkLearnRecords> tkLearnRecordsList=tkLearnRecordsMapper.select(tkLearnRecords);
                if(tkLearnRecordsList.size()>0){
                    return response.SAVE_FAIL("已存在学习记录");
                }
                tkLearnRecords.setCreationDate(new Date());
                tkLearnRecords.setLasteditDate(new Date());
                tkLearnRecords.setPkLearnRecords(GuidUtils.getGuid());
                iReuslt = tkLearnRecordsMapper.insertSelective(tkLearnRecords);//插入学习记录
//                if(iReuslt>0){
//                    //修改schedule状态 为已上
//                    Schedule schedule=new Schedule();
//                    schedule.setPkSchedule(tkLearnRecords.getPkSchedule());
//                    schedule.setStatus(1);
//                    scheduleMapper.updateByPrimaryKeySelective(schedule);
//                }
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
