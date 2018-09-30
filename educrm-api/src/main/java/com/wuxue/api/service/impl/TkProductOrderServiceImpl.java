package com.wuxue.api.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wuxue.api.mapper.*;
import com.wuxue.api.service.TkProductOrderService;
import com.wuxue.api.utils.GuidUtils;
import com.wuxue.model.*;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Auther: wh
 * @Date: 2018/6/13 14:32
 * @Description: 产品（课程）预约 service
 */
@Service("tkProductOrderService")
public class TkProductOrderServiceImpl implements TkProductOrderService {

    @Autowired
    TkProductOrderMapper tkProductOrderMapper;


    @Autowired
    ProductMapper productMapper;

    @Autowired
    ScheduleMapper scheduleMapper;

    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    TalkCloudRoomMapper talkCloudRoomMapper;


    /**
     * 删除（取消）课程预约
     * @param tParams
     * @return
     */
    @Override
    public Response delete(Request<TkProductOrder> tParams) {
        Response response = Response.newResponse();
        TkProductOrder primaryKey = tParams.getData();
        if (primaryKey.getPkProductOrder() == null || primaryKey.equals("")) {
            return response.PARAMS_ISNULL();
        }
        int iReuslt = 1;
        String message = "";
        try {
            TkProductOrder tkProductOrder=tkProductOrderMapper.selectByPrimaryKey(primaryKey.getPkProductOrder());

            //预约的排课记录主键
            String sPk=tkProductOrder.getPkSchedule();

            Schedule schedule1=scheduleMapper.selectByPrimaryKey(sPk);
            Date date=new Date();

            long between=(date.getTime()-schedule1.getStartTime().getTime())/1000;//除以1000是为了转换成秒

            //如果betwwen 正数，则说明当前时间大于开始时间
            if(between>0){
                response.setCode(1);
                response.setMessage("不可取消");
                return response;
            }

           //如果betwwen 为负数，则说明当前时间小于开始时间
           if(between<0){
               long min=Math.abs(between)/60;
               if (min<120){
                   response.setCode(1);
                   response.setMessage("两小时内不可以取消");
                   return response;
               }
           }

            //删除预约记录
            iReuslt = tkProductOrderMapper.deleteByPrimaryKey(primaryKey.getPkProductOrder());
            if(iReuslt>0){
                //取消排课记录中的学生
                Schedule schedule=new Schedule();
                schedule.setPkSchedule(sPk);
                schedule.setPkStudent("");
                scheduleMapper.updateByPrimaryKeySelective(schedule);
            }

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
    SysDictValuesMapper sysDictValuesMapper;
    /**
     * 查找 预约记录
     * @param tParams
     * @return
     */
    @Override
    public Response find(Request<TkProductOrder> tParams) {
        Response response = Response.newResponse();
        TkProductOrder tkproductOrder = tParams.getData();

        if (tkproductOrder == null) {
            return response.PARAMS_ISNULL();
        }
        String primaryKey = tkproductOrder.getPkProductOrder();
        if (primaryKey != null && !primaryKey.equals("")) {
            TkProductOrder byPrimaryKey = tkProductOrderMapper.selectByPrimaryKey(primaryKey);

            Schedule schedule=scheduleMapper.selectByPrimaryKey(byPrimaryKey.getPkSchedule());//预约的排课记录
            Employee employee=employeeMapper.selectByPrimaryKey( schedule.getPkEmployee());//排课记录中的老师对象
            Product  product=productMapper.selectByPrimaryKey( schedule.getPkProduct());//排课记录中的产品对象
            TalkCloudRoom talkCloudRoom=talkCloudRoomMapper.selectByPrimaryKey(schedule.getPkTalkCloudRoom());

            if(product.getProductTypetalkcloudroom().equals(0)){
                SysDictValues sysDictValues= sysDictValuesMapper.selectByPrimaryKey(product.getProductType());
                byPrimaryKey.put("sysDictValues",sysDictValues.getCaption());
            }else{
                byPrimaryKey.put("sysDictValues","公开课");
            }

            byPrimaryKey.put("talkCloudRoom",talkCloudRoom);
            byPrimaryKey.put("employee",employee);
            byPrimaryKey.put("product",product);
            byPrimaryKey.put("schedule",schedule);
            response.setData(byPrimaryKey);
        } else {
            PageHelper.startPage(tkproductOrder.getPageNo(), tkproductOrder.getPageSize());
            List<TkProductOrder> tkProductOrderList = tkProductOrderMapper.select(tkproductOrder);

            List<TkProductOrder> listIsnotOverTime =new ArrayList<>(); //未上的预约
            for (TkProductOrder t:tkProductOrderList) {
                Schedule schedule=scheduleMapper.selectByPrimaryKey(t.getPkSchedule());
                Employee employee=employeeMapper.selectByPrimaryKey( schedule.getPkEmployee());//排课记录中的老师对象
                Product  product=productMapper.selectByPrimaryKey( schedule.getPkProduct());//排课记录中的产品对象
                TalkCloudRoom talkCloudRoom=talkCloudRoomMapper.selectByPrimaryKey(schedule.getPkTalkCloudRoom());

                if(product.getProductTypetalkcloudroom().equals(0)){//必修，专业，选修
                    SysDictValues sysDictValues= sysDictValuesMapper.selectByPrimaryKey(product.getProductType());
                    t.put("sysDictValues",sysDictValues.getCaption());
                }else{
                    t.put("sysDictValues","公开课");
                }

                t.put("talkCloudRoom",talkCloudRoom);
                t.put("employee",employee);//预约的排课记录
                t.put("product",product);//排课记录中的老师对象

                Date date=new Date();
                if(schedule.getEndTime().getTime()>date.getTime()){//过滤过期的预约课程
                t.put("schedule",schedule); //预约的排课记录
                    listIsnotOverTime.add(t);
                }

            }
            PageInfo page = new PageInfo(listIsnotOverTime);
            response.setData(listIsnotOverTime);
            response.setTotal(page.getTotal());
        }
        return response;
    }

    @Override
    public Response save(Request<TkProductOrder> tParams) {
        Response response = Response.newResponse();
        TkProductOrder tkProductOrder = tParams.getData();
        if(tkProductOrder== null){
            return  response.PARAMS_ISNULL();
        }
        int iReuslt = 1;
        String message= "";
        String pk=tkProductOrder.getPkProductOrder();
        try {
            if (pk != null&&!"".equals(pk)) {
                iReuslt = tkProductOrderMapper.updateByPrimaryKeySelective(tkProductOrder);
            } else {

                List<TkProductOrder> tkLearnRecords=tkProductOrderMapper.select(tkProductOrder);
                if(tkLearnRecords.size()>0){
                    return response.SAVE_FAIL("已存在预约记录");
                }
                tkProductOrder.setCreationDate(new Date());
                tkProductOrder.setLasteditDate(new Date());
                tkProductOrder.setPkProductOrder(GuidUtils.getGuid());
                iReuslt = tkProductOrderMapper.insertSelective(tkProductOrder);
                if(iReuslt>1){
                    if(tkProductOrder.getType().equals(0)){
                        // 学生预约了课程  一对一
                        Schedule schedule=new Schedule();
                        schedule.setPkStudent(tkProductOrder.getPkStudent());
                        schedule.setPkSchedule(tkProductOrder.getPkSchedule());
                        scheduleMapper.updateByPrimaryKeySelective(schedule);
                    }

                }

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
