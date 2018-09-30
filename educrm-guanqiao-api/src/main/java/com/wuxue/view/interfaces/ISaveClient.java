package com.wuxue.view.interfaces;

import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;

public interface ISaveClient<TParams,TResponse> {

    TResponse save(TParams params);

}
