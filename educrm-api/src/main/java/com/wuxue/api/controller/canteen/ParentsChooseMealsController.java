package com.wuxue.api.controller.canteen;

import com.wuxue.api.service.ParentOrderService;
import com.wuxue.api.service.ParentPayService;
import com.wuxue.api.service.SetMealService;
import com.wuxue.model.*;
import com.wuxue.utils.common.DataUtils;
import com.wuxue.utils.common.DateTimeUtils;
import com.wuxue.utils.common.DateUtils;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by jie 2018/07/10  家长选餐
 */
@RestController
@RequestMapping(value = "api/canteen/parentsChooseMeals")
public class ParentsChooseMealsController extends SetMealController{

    @Autowired
    private ParentOrderService parentOrderService;
    @Autowired
    private SetMealService setMealService;

    @RequestMapping(value = "/findToChoose",method = RequestMethod.POST)
    public Response findToChoose(HttpServletRequest request, ParentOrder parentOrder,String pkStudent){

        ParentOrder parentOrder1 = parentOrderService.findToChoose(parentOrder);
        if(parentOrder1 != null){
            SetMeal setMeal = new SetMeal();
            setMeal.setPkSetMeal(parentOrder1.getSetMealId());
            Request<SetMeal> setMealRequest = DataUtils.objToRequest(setMeal);
            Response find = Find(setMealRequest);
            Object data = find.getData();
            SetMeal setMeal1 = DataUtils.objectToObject(data, SetMeal.class);
            parentOrder1.put("setMeal",setMeal1);
        }

        Response response = Response.newResponse();
        response.setData(parentOrder1);
        return response;
    }

    /**
     * 家长端每餐详情套餐列表
     * @param request
     * @param setMeal
     * @return
     */
    @RequestMapping(value = "/findMealBykey",method = RequestMethod.POST)
    public Response findMealBykey(HttpServletRequest request,SetMeal setMeal,String pkStudent){

        List<SetMeal> list=setMealService.findByStudent(setMeal,pkStudent);
        Response response = Response.newResponse();
        response.setData(list);

        return response;
    }

    /**
     * 家长选餐日历页
     * @param request
     * @param parentOrder
     * @param todayDate
     * @return
     */
    @RequestMapping(value = "/findChooseList",method = RequestMethod.POST)
    public Response findChooseList(HttpServletRequest request, ParentOrder parentOrder, String todayDate){

        Date date = DateTimeUtils.stringToDate(todayDate);

        if (date == null ){
            date = new Date();
        }
        Map<String, Date> map1 = DateTimeUtils.convertWeekByDate(date);
        if (map1.size() >0){
            parentOrder.setFromDate(map1.get("monday"));
            parentOrder.setToDate(map1.get("saturday"));
        }

//        int dayForWeek = 0;
//        //判断接收日期或今天是周几
//        if (todayDate ==null || "".equals(todayDate)){
//            dayForWeek = DateUtils.dayForWeek(new Date());
//        }else {
//            dayForWeek = DateUtils.dayForWeek(DateTimeUtils.stringToDate(todayDate));
//        }
//        if (dayForWeek >1 && dayForWeek <7){
//            //本周
////            Date weekDate = DateUtils.getWeekDate(1);
//            parentOrder.setFromDate(DateTimeUtils.getSplicingTime(DateUtils.getWeekDate(1),null));
////            parentOrder.setFromDate(DateUtils.getWeekDate(1));
//            parentOrder.setToDate(DateTimeUtils.getSplicingTime(DateUtils.getWeekDate(6),null));
//        }else {
//            //下周
//            Date nextWeekMonday = DateUtils.getNextWeekMonday(new Date());
//            parentOrder.setFromDate(DateTimeUtils.getSplicingTime(nextWeekMonday,null));
//            parentOrder.setToDate(DateTimeUtils.getSplicingTime(DateUtils.getAfterDate(5, nextWeekMonday),null));
//        }

        Map<String,List<ParentOrder>> map = parentOrderService.findChooseList(parentOrder);
        Response response = Response.newResponse();
        response.setData(map);
        return response;
    }

    @RequestMapping(value = "/findToCalendar",method = RequestMethod.POST)
    public Response findToCalendar(HttpServletRequest request, ParentOrder parentOrder, String todayDate){

        Response chooseList = findChooseList(request, parentOrder, todayDate);

        return chooseList;
    }



}
