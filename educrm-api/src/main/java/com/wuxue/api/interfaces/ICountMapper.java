package com.wuxue.api.interfaces;

public interface ICountMapper<TParams,TResult> {
    TResult countBy(TParams tParams);
}
