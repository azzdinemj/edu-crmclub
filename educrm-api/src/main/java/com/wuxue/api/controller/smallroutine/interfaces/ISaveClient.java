package com.wuxue.api.controller.smallroutine.interfaces;

public interface ISaveClient<TParams,TResponse> {

    TResponse save(TParams params);

}
