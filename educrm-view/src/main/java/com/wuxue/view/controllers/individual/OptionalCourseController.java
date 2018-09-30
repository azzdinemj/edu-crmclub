package com.wuxue.view.controllers.individual;

import com.wuxue.model.Linkman;
import com.wuxue.model.OptionalCourse;
import com.wuxue.model.Schedule;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.course.OptionalCourseClient;
import com.wuxue.view.client.student.LinkManClient;
import com.wuxue.view.client.system.SysUserClient;
import com.wuxue.view.controllers.BaseController;
import com.wuxue.view.interfaces.*;
import com.wuxue.view.utils.AutoCodeEnum;
import com.wuxue.view.utils.GuidUtils;
import com.wuxue.view.utils.SessionCache;
import com.wuxue.view.utils.SysAutoCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 管理
 */
@Controller
@RequestMapping(value = "/individual/optionalCourse")
public class OptionalCourseController extends BaseController implements IQueryController<OptionalCourse, String>,
        IQueryByPagingController<OptionalCourse, Map<String, Object>>, ICreateController<OptionalCourse, String>,
        IEditController<OptionalCourse, String>, ISaveController<Schedule, String> {


    @InitBinder("linkman")
    public void initBinder(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("man.");
    }

    @Autowired
    private OptionalCourseClient optionalCourseClient;
    @Autowired
    private SysUserClient sysUserClient;
    @Autowired
    private SysAutoCodeUtils sysAutoCodeUtils;

    /**
     * 列表页面
     *
     * @param model
     * @return
     * @throws java.text.ParseException
     */
    @Override
    public String query(HttpServletRequest request, Model model, OptionalCourse optionalCourse) {




        return "/individual/optionalCourseList";
    }

    @Override
    public String edit(HttpServletRequest request, Model model, OptionalCourse linkman) {

        return "/individual/individual";
    }


    @Override
    public String create(HttpServletRequest request, Model model, OptionalCourse optionalCourse) {
        return null;
    }

    @Override
    @ResponseBody
    public Map<String, Object> queryByPaging(HttpServletRequest request, HttpServletResponse response, OptionalCourse optionalCourse, Integer sEcho, Integer start, Integer length) {

        optionalCourse.setPageNo((start/length)+1);
        optionalCourse.setPageSize(length);
        Response<List<OptionalCourse>> listResponse = optionalCourseClient.find(optionalCourse);
        List<OptionalCourse> data = listResponse.getData();
        if(data==null) {
            data = new ArrayList<>();
        }
        return convertToPaging(listResponse.getTotal(),listResponse.getTotal(),data);


    }

    @Override
    @ResponseBody
    public String save(HttpServletRequest request, Model model, Schedule schedule) throws ParseException {

        String pkScheduls = request.getParameter("pkScheduls");
        List<Schedule> list = new ArrayList<>();
        if (pkScheduls != null && !"".equals(pkScheduls)){
            String[] split = pkScheduls.split(",");
            for (String s : split) {
                Schedule course = new Schedule();
                course.setPkDomain(SessionCache.getPkdomain());
                course.setPkSchedule(GuidUtils.getGuid());
                course.setStartTime(null);
                course.setEndTime(null);
                course.setCreator(SessionCache.getUserCode());
                course.setModifier(SessionCache.getUserCode());
                list.add(course);
            }


        }

        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/verification",method = RequestMethod.GET)
    public String verification(HttpServletRequest request, Model model, OptionalCourse optionalCourse) throws ParseException {

        if(optionalCourse.getPkSchedul() != null){

        }
        return "{\"code\":0,\"message\":\"该课程不可选 \"}";

    }
}
