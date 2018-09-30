package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.shuttle.QrCode;

import java.util.List;

public interface QrCodeMapper extends IInsertMapper<QrCode>,ICountMapper<QrCode,Integer>,
        IUpdateMapper<QrCode>,IDeleteByPrimaryKeyMapper<String>,
        ISelectByPrimaryKeyMapper<String,QrCode>,ISelectMapper<QrCode,List<QrCode>>{

    /**
     * 根据学生id获取最新一条二维码字符串
     * @param studentId 学生id
     * @return 二维码对象
     */
    QrCode getQrCodeString(String studentId);
}