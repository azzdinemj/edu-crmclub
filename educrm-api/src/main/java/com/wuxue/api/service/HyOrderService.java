package com.wuxue.api.service;

import com.wuxue.api.interfaces.IFindService;
import com.wuxue.api.interfaces.ISaveService;
import com.wuxue.model.HyOrder;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;

public interface HyOrderService extends ISaveService<HyOrder>,IFindService<HyOrder> {

    Response countBy(Request<HyOrder> tParams);

    Response saveOrder(Request<String> stringRequest);

    Response updateStatus(Request<HyOrder> tParams);
}