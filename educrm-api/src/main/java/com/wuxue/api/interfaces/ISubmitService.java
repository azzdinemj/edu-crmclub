package com.wuxue.api.interfaces;

import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;

public interface ISubmitService<TParams> {

    Response submit(Request<TParams> tParams);

}
