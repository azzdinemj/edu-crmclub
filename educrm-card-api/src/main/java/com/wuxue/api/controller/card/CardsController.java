package com.wuxue.api.controller.card;

import com.wuxue.api.service.CardsService;
import com.wuxue.model.card.Attendance;
import com.wuxue.model.card.Doors;
import com.wuxue.model.card.Student;
import com.wuxue.model.card.Trans;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 一卡通所用接口
 * Created by Rogue on 2018/08/29.
 */
@RestController
@RequestMapping(value = "api/card/card")
public class CardsController {
    @Autowired
    private CardsService cardsService;

    /**
     * 新增充值记录
     */
    @RequestMapping(value = "/recharge", method = RequestMethod.POST)
    public Response recharge(HttpServletRequest request, HttpServletResponse response, Trans trans) throws Exception {
        return cardsService.recharge(trans);
    }

    /**
     * 获取消费记录
     */
    @RequestMapping(value = "/getTransList", method = {RequestMethod.GET, RequestMethod.POST})
    public void getTransList(Trans trans) throws Exception {
        cardsService.getTransList(trans);
    }

    /**
     * 获取門禁记录
     */
    @RequestMapping(value = "/getDoorsList", method = {RequestMethod.GET, RequestMethod.POST})
    public void getDoorsList(Doors doors) throws Exception {
        cardsService.getDoorsList(doors);
    }

    /**
     * 获取考勤记录
     */
    @RequestMapping(value = "/getAttendanceList", method = {RequestMethod.GET, RequestMethod.POST})
    public void getAttendanceList(Attendance attendance) throws Exception {
        cardsService.getAttendanceList(attendance);
    }

    /**
     * 获取賬戶信息
     */
    @RequestMapping(value = "/getAccounts", method = {RequestMethod.GET, RequestMethod.POST})
    public Response getAccounts(Student student) throws Exception {

        return cardsService.getAccounts(student);
    }

    /**
     * 新增账号
     */
    @RequestMapping(value = "/addAccounts", method = RequestMethod.POST)
    public Response addAccounts(HttpServletRequest request, HttpServletResponse response,Student student) throws Exception {
        return cardsService.addAccounts(student);
    }
}
