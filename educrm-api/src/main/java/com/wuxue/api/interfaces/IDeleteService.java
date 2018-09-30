package com.wuxue.api.interfaces;

import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;

public interface IDeleteService<TParams> {
    Response delete(Request<TParams> tParams);
}
