package com.wuxue.api.service;


import com.wuxue.api.interfaces.ISaveService;
import com.wuxue.model.Recharge;
import com.wuxue.model.card.Attendance;
import com.wuxue.model.card.Doors;
import com.wuxue.model.card.Student;
import com.wuxue.model.card.Trans;
import com.wuxue.utils.contract.Response;

public interface CardsService {

    /**
     * 新增充值记录
     * */
    Response recharge(Trans trans) throws Exception;

    /**
     * 获取所有的消费记录
     * */
    void getTransList(Trans trans) throws Exception;

    /**
     * 獲取所有的門禁記錄
     * */
    void getDoorsList(Doors doors) throws Exception;

    /**
     * 獲取所有的考勤記錄
     *
     * */
    void getAttendanceList(Attendance attendance) throws Exception;

    /**
     *獲取賬戶信息
     *
     * @param accounts*/
    Response getAccounts(Student accounts) throws Exception;

    /**
     *新增賬戶信息
     *
     * @param student*/
    Response addAccounts(Student student);
}
