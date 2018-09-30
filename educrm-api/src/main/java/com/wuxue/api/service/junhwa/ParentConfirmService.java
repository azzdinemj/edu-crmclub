package com.wuxue.api.service.junhwa;

import com.wuxue.model.junhwa.ParentConfirm;
import com.wuxue.utils.contract.Response;

/**
 * 家长确认
 * @author tly
 * @date 2018-07-30
 */
public interface ParentConfirmService {

    /**
     * 家长确认接送
     * @param parentConfirm
     * @return
     */
    Response saveParentConfirmInfo(ParentConfirm parentConfirm);
}
