package com.wuxue.api.interfaces;

import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;

public interface IFindService<TParams> {
    Response find(Request<TParams> tParams);
}
