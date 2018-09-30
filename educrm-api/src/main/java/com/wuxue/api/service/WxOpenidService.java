package com.wuxue.api.service;

import com.wuxue.api.interfaces.IFindService;
import com.wuxue.api.interfaces.ISaveService;
import com.wuxue.model.WxOpenid;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;

public interface WxOpenidService extends ISaveService<WxOpenid>,IFindService<WxOpenid>{

    Response saveOpenid(Request<WxOpenid> tParams);

    Response findOpenid(Request<WxOpenid> tParams);
}
