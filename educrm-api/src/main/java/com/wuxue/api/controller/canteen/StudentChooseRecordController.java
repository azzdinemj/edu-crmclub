package com.wuxue.api.controller.canteen;

import com.wuxue.api.service.ParentOrderService;
import com.wuxue.api.service.ParentPayService;
import com.wuxue.api.service.SetMealService;
import com.wuxue.model.ParentOrder;
import com.wuxue.model.ParentPay;
import com.wuxue.model.SetMeal;
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
 * Created by jie 2018/07/10  学生个人选餐记录
 */
@RestController
@RequestMapping(value = "api/canteen/studentChooseRecord")
public class StudentChooseRecordController extends SetMealController{

    @Autowired
    private ParentPayService parentPayService;

    @RequestMapping(value = "/findByStudent")
    public Response findByStudent(Request<ParentPay> parentPayRequest) {
        return parentPayService.find(parentPayRequest);
    }
}
