package com.wuxue.api.interfaces;

import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;

public interface ISaveService<TParams> {

    Response save(Request<TParams> tParams);

}
