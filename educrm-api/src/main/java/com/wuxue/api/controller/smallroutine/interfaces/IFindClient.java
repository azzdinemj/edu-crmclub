package com.wuxue.api.controller.smallroutine.interfaces;

public interface IFindClient<TParams,TResponse> {
    TResponse find(TParams params);

}
