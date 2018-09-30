package com.wuxue.view.interfaces;

public interface IDeleteClient<TParams,TResponse> {
    TResponse delete(TParams params);
}
