package com.wuxue.api.controller.shuttle;

import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageInfo;
import com.wuxue.api.service.DeliveryApplicationService;
import com.wuxue.api.service.StudentService;
import com.wuxue.model.Linkman;
import com.wuxue.model.LinkmanList;
import com.wuxue.model.Student;
import com.wuxue.model.shuttle.DeliveryApplication;
import com.wuxue.utils.common.DataUtils;
import com.wuxue.utils.common.DateTimeUtils;
import com.wuxue.utils.contract.Light;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by jie 2018/07/10  特别接送申请
 */
@RestController
@RequestMapping(value = "api/shuttle/deliveryApplication")
public class DeliveryApplicationController {

    @Autowired
    private DeliveryApplicationService deliveryApplicationService;
    @Autowired
    private StudentService studentService;

    /**
     *  委托接送首页
     * @param request
     * @param deliveryApplication
     * @return
     */
    @RequestMapping(value = "/findApply",method = RequestMethod.POST)
    public Response findApply(HttpServletRequest request, @RequestBody DeliveryApplication deliveryApplication){
        Response response = Response.newResponse();


        if (deliveryApplication.getStatus()==2){
            deliveryApplication.setEntrustmentId(deliveryApplication.getPkLinkman());
            deliveryApplication.setStatus(1);
            deliveryApplication.setPkLinkman(null);
        }

        List<DeliveryApplication> apply = deliveryApplicationService.findApply(deliveryApplication);
        PageInfo page = new PageInfo(apply);
        response.setTotal(page.getTotal());


        response.setData(apply);

        return response;
    }

    /**
     * 申请委托接送
     * @param request
     * @param pkLinkman
     * @return
     */
    @RequestMapping(value = "/apply",method = RequestMethod.POST)
    public Response apply(HttpServletRequest request, String pkLinkman){


        List<Student> studentList = studentService.selectByParent(pkLinkman);

        Response response = Response.newResponse();
        response.setData(studentList);


        return response;
    }

    /**
     * 提交申请委托接送
     * @return
     */
    @RequestMapping(value = "/applyIssubmit",method = RequestMethod.POST)
    public Response applyIssubmit(@RequestBody DeliveryApplication deliveryApplication){
        List<String> strings = new ArrayList<>();
        if (deliveryApplication.getMap() != null && deliveryApplication.getMap().size()>0){
            strings = DataUtils.objectToList(deliveryApplication.getMap().get(Light.STUDENT_LIST), String.class);
            if (strings == null){
                strings= new ArrayList<>();
            }
        }


        Date date = DateTimeUtils.getStringToDate(deliveryApplication.getDeliveryDateTime(),"yyyy-MM-dd hh:mm");

        Response response = deliveryApplicationService.applyIssubmit(strings,date,deliveryApplication);

        return response;
    }

    /**
     * 详情
     * @param request
     * @param pkDelivery
     * @return
     */
    @RequestMapping(value = "/findEntrust",method = RequestMethod.POST)
    public Response findEntrust(HttpServletRequest request, String pkDelivery){

        DeliveryApplication deliveryApplication = deliveryApplicationService.selectByKey(pkDelivery);
        Response response = Response.newResponse();

        response.setData(deliveryApplication);

        return response;
    }
    /**
     * 取消申请
     * @param request
     * @param pkDelivery
     * @return
     */
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public Response delete(HttpServletRequest request, String pkDelivery){

        Response response =  deliveryApplicationService.delete(pkDelivery);


        return response;
    }

    /**
     * 审核
     * @param request
     * @param deliveryApplication
     * @return
     */
    @RequestMapping(value = "/audit",method = RequestMethod.POST)
    public Response audit(HttpServletRequest request, DeliveryApplication deliveryApplication){

        Response response = deliveryApplicationService.audit(deliveryApplication);


        return response;
    }

}
