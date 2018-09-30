package com.wuxue.api.controller.shuttle;

import com.wuxue.api.service.junhwa.QrCodeService;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 接送二维码
 *
 * @author tly
 * @date 2018/07/30
 */
@RestController
@RequestMapping(value = "api/shuttle/qrcode")
public class QrCodeController {
    @Autowired
    private QrCodeService qrCodeService;
    /**
     * 获取二维码字符串 包含学号、家长编号、开始时间、结束时间
     * @return
     */
    @RequestMapping(value = "/getQrCodeString",method = RequestMethod.POST)
    public Response getQrCodeString(@RequestBody  Request<String> request){
        return qrCodeService.getlastQrCodeString(request.getData());
    }

}
