package com.wuxue.api.controller.canteen;

import com.wuxue.api.service.ClassinfoService;
import com.wuxue.api.service.ParentPayService;
import com.wuxue.model.Classinfo;
import com.wuxue.model.ParentPay;
import com.wuxue.utils.common.DateTimeUtils;
import com.wuxue.utils.common.DateUtils;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jie 2018/07/10  餐桌摆放
 */
@RestController
@RequestMapping(value = "api/canteen/mealPut")
public class MealPutController {
    @Autowired
    private ParentPayService parentPayService;
    @Autowired
    private ClassinfoService classinfoService;

    @RequestMapping(value = "/mealPutByClass",method = RequestMethod.POST)
    public Response Find(HttpServletRequest request, String pkClassinfo,String pkCanteen,Integer type,String todayDate) {
        Date date = DateTimeUtils.stringToDate(todayDate);

        Map map = new HashMap();
        map.put("type",type);
        map.put("pkCanteen",pkCanteen);
        map.put("fromDate",date);
        map.put("toDate", DateUtils.getAfterDate(1,date));
        map.put("pkClassinfo",pkClassinfo);

        List<ParentPay> list = parentPayService.selectByClassinfo(map);
        Response response = Response.newResponse();
        response.setData(list);

        return response;

    }

    /**
     * 获取班级列表
     * @param request
     * @return
     */
    @RequestMapping(value = "/findClass",method = RequestMethod.POST)
    public Response findClass(HttpServletRequest request,Classinfo classinfo){
        classinfo.setType(0);
        Response response =classinfoService.selectByType(classinfo);
        return response;
    }


}
