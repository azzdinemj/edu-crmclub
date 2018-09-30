package com.wuxue.api.interfaces;

import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;

public interface IFindByParentService<TParams> {
    Response findValue(Request<TParams> tParams);
}
