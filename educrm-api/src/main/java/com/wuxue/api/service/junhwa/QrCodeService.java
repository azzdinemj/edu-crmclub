package com.wuxue.api.service.junhwa;

import com.wuxue.utils.contract.Response;

/**
 * 二维码
 * @author tly
 * @date 2018-07-30
 */
public interface QrCodeService {

    /**
     * 获取并保存二维码字符串
     * @return
     */
    Response getlastQrCodeString(String StudentId);

}
