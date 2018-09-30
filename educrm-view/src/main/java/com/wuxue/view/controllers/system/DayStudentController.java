package com.wuxue.view.controllers.system;

import com.alibaba.fastjson.JSON;
import com.wuxue.base.KeyValue;
import com.wuxue.model.*;
import com.wuxue.model.junhwa.DayStudent;
import com.wuxue.utils.common.DataUtils;
import com.wuxue.utils.contract.Light;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.system.DayStudentClient;
import com.wuxue.view.controllers.BaseController;
import com.wuxue.view.interfaces.*;
import com.wuxue.view.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 走读配置
 */
@Controller
@RequestMapping(value = "/system/dayStudent")
public class DayStudentController extends BaseController
        implements IQueryController<DayStudent, String>, ISaveController<DayStudent, String>, IQueryByPagingController<DayStudent, Map<String, Object>>,
        ICreateController<DayStudent, String>, IEditController<DayStudent, String>, IDeleteController<DayStudent, String> {


    @Autowired
    private DayStudentClient dayStudentClient;
    @Autowired
    private ClassinfoUtils classinfoUtils;

    /**
     * 列表页面
     *
     * @param model
     * @return
     * @throws java.text.ParseException
     */
    @Override
    public String query(HttpServletRequest request, Model model, DayStudent dayStudent) {


        return "/system/dayStudentList";
    }

    @Override
    @ResponseBody
    public Map<String, Object> queryByPaging(HttpServletRequest request, HttpServletResponse response, DayStudent dayStudent, Integer sEcho, Integer start, Integer length) {
        dayStudent.setPageNo((start / length) + 1);
        dayStudent.setPageSize(length);

        Response<List<DayStudent>> listResponse = dayStudentClient.find(dayStudent);
        List<DayStudent> data = listResponse.getData();
        if (data == null) {
            data = new ArrayList<>();
        }
        return convertToPaging(listResponse.getTotal(), listResponse.getTotal(), data);
    }


    @Override
    public String create(HttpServletRequest request, Model model, DayStudent dayStudent) {

        List<KeyValue> classinfoByType = classinfoUtils.getClassinfoByType(0);
        model.addAttribute("classinfoList", classinfoByType);


        return "/system/addDayStudent";
    }

    @Override
    @ResponseBody
    public String delete(HttpServletRequest request, Model model, DayStudent dayStudent) {
        return dayStudentClient.delete(dayStudent.getDayStudentId());


    }

    @Override
    public String edit(HttpServletRequest request, Model model, DayStudent dayStudent) {
        Response<DayStudent> byPrimaryKey = dayStudentClient.findByPrimaryKey(dayStudent);
        model.addAttribute("dayStudent", byPrimaryKey.getData());


        return "/system/editDayStudent";
    }


    @Override
    @ResponseBody
    public String save(HttpServletRequest request, Model model, DayStudent dayStudent) {

        dayStudent.setDeliveryDate(DateTimeUtils.getStringToDate(dayStudent.getDeliveryDateTime(),"hh:mm"));


        return dayStudentClient.save(dayStudent);
    }

    @ResponseBody
    @RequestMapping(value = "/saveAll", method = RequestMethod.POST)
    public String saveAll(HttpServletRequest request, Model model, DayStudent dayStudent) {


        String pkStudents = request.getParameter("pkStudents");
        List<String> list = JSON.parseArray(pkStudents).toJavaList(String.class);

        dayStudent.getDeliveryDateTime();
        dayStudent.setDeliveryDate(DateTimeUtils.getStringToDate(dayStudent.getDeliveryDateTime(),"hh:mm"));
        dayStudent.put(Light.IDS, list);
        return dayStudentClient.saveAll(dayStudent);
    }


}
