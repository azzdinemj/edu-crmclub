package com.wuxue.api.controller.smallroutine.interfaces;

public interface ISubmitClient<TParams,TResponse> {
    TResponse submit(TParams params);

}
