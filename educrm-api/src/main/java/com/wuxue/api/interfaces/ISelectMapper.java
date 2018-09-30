package com.wuxue.api.interfaces;

public interface ISelectMapper<TParams,TResult> {
    TResult select(TParams tParams);
}
