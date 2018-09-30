package com.wuxue.api.controller.canteen;

import com.wuxue.api.interfaces.IDeleteController;
import com.wuxue.api.interfaces.IFindController;
import com.wuxue.api.interfaces.ISaveController;
import com.wuxue.api.service.CanteenService;
import com.wuxue.api.service.MealCountService;
import com.wuxue.api.service.ParentOrderService;
import com.wuxue.api.service.ParentPayService;
import com.wuxue.model.Canteen;
import com.wuxue.model.MealCount;
import com.wuxue.model.ParentOrder;
import com.wuxue.model.ParentPay;
import com.wuxue.utils.common.DateTimeUtils;
import com.wuxue.utils.common.DateUtils;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jie 2018/07/10  选餐统计
 */
@RestController
@RequestMapping(value = "api/canteen/mealCount")
public class MealCountController implements IFindController<ParentOrder> {
    @Autowired
    private MealCountService mealCountService;
    @Autowired
    private ParentPayService parentPayService;
    @Autowired
    private ParentOrderService parentOrderService;

    @Override
    public Response Find(@RequestBody Request<ParentOrder> parentOrderRequest) {

        return mealCountService.find(parentOrderRequest);
    }

    /**
     * 餐厅小程序端选餐统计首页
     * @return
     */
    @RequestMapping(value = "/findByWeek",method = RequestMethod.POST)
    public Response findByWeek(HttpServletRequest request,String pkCanteen,String todayDate){

        Map map = DateUtils.dateForWeek(todayDate);
        map.put("pkCanteen",pkCanteen);
        Map<String,List<ParentOrder>> forCanteen = parentOrderService.findForCanteen(map);

        Response response = Response.newResponse();
        response.setData(forCanteen);
        return response;
    }

    /**
     * 餐厅小程序端选餐统计详情首页
     * @return
     */
    @RequestMapping(value = "/findByType",method = RequestMethod.POST)
    public Response findByType(HttpServletRequest request,Integer type,String mealDateTime,String pkCanteen){

        Date date = DateTimeUtils.stringToDate(mealDateTime);

        Map map = new HashMap();
        map.put("type",type);
        map.put("pkCanteen",pkCanteen);
        map.put("fromDate",date);
        map.put("toDate",DateUtils.getAfterDate(1,date));
        
        List<MealCount> list = parentOrderService.selectByType(map);
        Response response = Response.newResponse();
        response.setData(list);
        return response;
    }


}
