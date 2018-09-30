package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.Receipt;
import com.wuxue.model.StudentSignup;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface ReceiptMapper extends IInsertMapper<Receipt>,ICountMapper<Receipt,Integer>,
        IUpdateMapper<Receipt>,IDeleteByPrimaryKeyMapper<String>,
        ISelectByPrimaryKeyMapper<String,Receipt>,ISelectMapper<Receipt,List<Receipt>> {
    void updateByPrimaryKey(Receipt byPrimaryKey);

    /**
     * 总金额
     * */
    BigDecimal countMoney(Receipt byPrimaryKey);

    BigDecimal selectSum(StudentSignup studentSignup);

    List<Map<String,Object>> selectFroRefund(Receipt receipt);
}