package com.wuxue.api.controller.smallroutine.interfaces;

/**
 * Created by Jamie on 2018/1/9.
 */
public interface IFindByPrimaryKeyClient<TParams,TResponse> {
    TResponse findByPrimaryKey(TParams params);
}
