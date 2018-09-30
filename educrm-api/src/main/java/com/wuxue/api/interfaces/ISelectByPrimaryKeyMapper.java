package com.wuxue.api.interfaces;

public interface ISelectByPrimaryKeyMapper<TParams,TResult> {
    TResult selectByPrimaryKey(TParams tParams);
}
