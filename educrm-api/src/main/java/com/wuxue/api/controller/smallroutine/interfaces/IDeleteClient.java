package com.wuxue.api.controller.smallroutine.interfaces;

public interface IDeleteClient<TParams,TResponse> {
    TResponse delete(TParams params);
}
