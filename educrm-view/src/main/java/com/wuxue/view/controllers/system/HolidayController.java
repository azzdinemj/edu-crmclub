package com.wuxue.view.controllers.system;

import com.alibaba.fastjson.JSON;
import com.wuxue.model.junhwa.Holiday;
import com.wuxue.utils.common.DateTimeUtils;
import com.wuxue.utils.contract.Light;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.system.HolidayClient;
import com.wuxue.view.controllers.BaseController;
import com.wuxue.view.interfaces.*;
import com.wuxue.view.utils.SysDicEmnuUtils;
import com.wuxue.view.utils.SysDictUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 节假日配置
 */
@Controller
@RequestMapping(value = "/system/holiday")
public class HolidayController extends BaseController
        implements IQueryController<Holiday, String>, ISaveController<Holiday, String>, IQueryByPagingController<Holiday, Map<String, Object>>,
        ICreateController<Holiday, String>, IEditController<Holiday, String>, IDeleteController<Holiday, String> {


    @Autowired
    private HolidayClient holidayClient;
    @Autowired
    private SysDictUtils sysDictUtils;

    /**
     * 列表页面
     *
     * @param model
     * @return
     * @throws java.text.ParseException
     */
    @Override
    public String query(HttpServletRequest request, Model model, Holiday holiday) {


        return "/system/holidayList";
    }

    @Override
    @ResponseBody
    public Map<String, Object> queryByPaging(HttpServletRequest request, HttpServletResponse response, Holiday holiday, Integer sEcho, Integer start, Integer length) {
        holiday.setPageNo((start / length) + 1);
        holiday.setPageSize(length);

        Response<List<Holiday>> listResponse = holidayClient.find(holiday);
        List<Holiday> data = listResponse.getData();
        if (data == null) {
            data = new ArrayList<>();
        }
        return convertToPaging(listResponse.getTotal(), listResponse.getTotal(), data);
    }


    @Override
    public String create(HttpServletRequest request, Model model, Holiday holiday) {

        sysDictUtils.setModeAttribute(model,"holidayList", SysDicEmnuUtils.HOLIDAY);

        return "/system/addHoliday";
    }

    @Override
    @ResponseBody
    public String delete(HttpServletRequest request, Model model, Holiday holiday) {
        return holidayClient.delete(holiday.getId());


    }

    @Override
    public String edit(HttpServletRequest request, Model model, Holiday holiday) {
        Response<Holiday> byPrimaryKey = holidayClient.findByPrimaryKey(holiday);
        model.addAttribute("holiday", byPrimaryKey.getData());

        sysDictUtils.setModeAttribute(model,"holidayList", SysDicEmnuUtils.HOLIDAY);
        return "/system/editHoliday";
    }


    @Override
    @ResponseBody
    public String save(HttpServletRequest request, Model model, Holiday holiday) {

        holiday.setHolidayDay(DateTimeUtils.stringToDate(holiday.getHolidayDayTime()));

        return holidayClient.save(holiday);
    }

    @ResponseBody
    @RequestMapping(value = "/saveAll", method = RequestMethod.POST)
    public String saveAll(HttpServletRequest request, Model model, Holiday holiday) {

        String dateTimes = request.getParameter("dateTimes");

        if (dateTimes != null && !"".equals(dateTimes)){
            List<Date> list1 = DateTimeUtils.StringSubToDate(dateTimes);

            List<Date> datesBetweenTwoDate = DateTimeUtils.getDatesBetweenTwoDate(list1.get(0), list1.get(1));
            holiday.put(Light.DATELIST, datesBetweenTwoDate);
        }





        return holidayClient.saveAll(holiday);
    }


}
