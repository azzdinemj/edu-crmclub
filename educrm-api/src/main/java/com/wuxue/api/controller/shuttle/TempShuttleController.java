package com.wuxue.api.controller.shuttle;

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
 * Created by jie 2018/07/10  特别接送申请(临时接送)
 */
@RestController
@RequestMapping(value = "api/shuttle/tempShuttle")
public class TempShuttleController {

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

        deliveryApplication.setStatus(3);
        List<DeliveryApplication> apply = deliveryApplicationService.findApply(deliveryApplication);

        Response response = Response.newResponse();
        response.setData(apply);

        return response;
    }
    @RequestMapping(value = "/apply",method = RequestMethod.POST)
    public Response apply(HttpServletRequest request, String pkLinkman){


        List<Student> studentList = studentService.selectByParent(pkLinkman);

        Response response = Response.newResponse();
        response.setData(studentList);

        return response;
    }

    /**
     * 提交申请委托接送
     * @param linkman , String deliveryDateTime, DeliveryApplication deliveryApplication
     * @return
     */
    @RequestMapping(value = "/applyIssubmit",method = RequestMethod.POST)
    public Response applyIssubmit(@RequestBody Linkman linkman){

//        List<String> studentList = ids.getIds();

//        Date date = DateTimeUtils.stringToDate(deliveryDateTime);
        Date date = DateTimeUtils.getStringToDate(linkman.getDeliveryDateTime(),"yyyy-MM-dd hh:mm");

//        String pkLinkman = request.getParameter("pkLinkman");
//        linkman.put("linkmanId",pkLinkman);
        List<Student> studentList = new ArrayList<>();
        if(linkman.getMap() != null) {
            studentList = DataUtils.objectToList(linkman.getMap().get(Light.STUDENT_LIST), Student.class);
            if(studentList == null){
                studentList = new ArrayList<>();
            }
        }
        DeliveryApplication deliveryApplication = new DeliveryApplication();
        deliveryApplication.setStatus(3);
        deliveryApplication.setNotes(linkman.getNotes());
        Response response = deliveryApplicationService.applyIssubmitTemp(linkman,studentList,date, deliveryApplication);

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
