package com.wuxue.api.service.junhwa.impl;

import com.wuxue.api.mapper.QrCodeMapper;
import com.wuxue.api.service.junhwa.QrCodeService;
import com.wuxue.model.shuttle.QrCode;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 二维码
 *
 * @author tly
 * @date 2018-07-30
 */
@Service("qrCodeService")
public class QrCodeServiceImpl implements QrCodeService {
    @Autowired
    private QrCodeMapper qrCodeMapper;

    @Override
    public Response getlastQrCodeString(String studentId) {
        Response response =Response.newResponse();
        if (null == studentId || "".equals(studentId)){
            return  response.PARAMS_ISNULL();
        }
        QrCode qrCode = qrCodeMapper.getQrCodeString(studentId);
        if (null == qrCode){
            return  response.FIND_FAIL("找不到二维码信息");
        }
        response.setData(qrCode.getParameter());
        return response;
    }
}
