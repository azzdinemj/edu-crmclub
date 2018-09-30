package com.wuxue.view.interfaces;

import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;

public interface IDeleteClient<TParams,TResponse> {
    TResponse delete(TParams params);
}
