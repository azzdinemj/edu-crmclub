package com.wuxue.api.interfaces;

import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;

public interface IFindAllService<TParams> {
    Response findAll(Request<TParams> tParams);
}
