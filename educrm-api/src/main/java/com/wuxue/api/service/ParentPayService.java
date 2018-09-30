package com.wuxue.api.service;


import com.wuxue.api.interfaces.IDeleteService;
import com.wuxue.api.interfaces.IFindService;
import com.wuxue.api.interfaces.ISaveService;
import com.wuxue.base.ResultEntity;
import com.wuxue.model.ParentPay;
import com.wuxue.utils.contract.Response;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface ParentPayService extends ISaveService<ParentPay>,IFindService<ParentPay>,IDeleteService<String> {

    List<ParentPay> selectByClassinfo(Map map);

    /**
     * 通过学生主键获取现金支付总和
     * @param pkStudent
     * @return
     */
    BigDecimal getParentPaySum(String pkStudent);

    /**
     * 获取帐户余额
     * @return
     */
    Response<Double> getAccountBalance(String pkStudent);

    /**
     * 通过学生id获取账户余额
     *
     * @param pkStudent
     * @return
     */
    Double getAccountBalanceByStudentId(String pkStudent);

    /**
     * 获取支付记录列表
     * @param resultEntity 参数
     * <ul>
     *     <li>paramMap#className 班级名称</li>
     *     <li>paramMap#studentName 学生姓名</li>
     *     <li>paramMap#startTime 开始时间</li>
     *     <li>paramMap#endTime 结束时间</li>
     * </ul>
     * @return
     */
    Response getPayRecordList(ResultEntity resultEntity);

    /**
     * 批量保存
     *
     * @param parentPays
     */
    void batchSaveParentPay(List<ParentPay> parentPays);

    /**
     * 去支付
     */
    void toPay(List<String> orderIds);

    /**
     * 获取余额
     * */
    Response getBalance(String pkStudent);
}
