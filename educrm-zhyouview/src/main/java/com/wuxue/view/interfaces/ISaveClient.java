package com.wuxue.view.interfaces;

public interface ISaveClient<TParams,TResponse> {

    TResponse save(TParams params);

}
