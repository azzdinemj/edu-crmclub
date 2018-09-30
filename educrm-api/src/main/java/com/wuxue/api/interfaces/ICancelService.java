package com.wuxue.api.interfaces;

import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;

public interface ICancelService<TParams> {

    Response cancel(Request<TParams> tParams);

}
