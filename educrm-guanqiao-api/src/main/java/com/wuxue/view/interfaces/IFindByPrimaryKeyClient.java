package com.wuxue.view.interfaces;

/**
 * Created by Jamie on 2018/1/9.
 */
public interface IFindByPrimaryKeyClient<TParams,TResponse> {
    TResponse findByPrimaryKey(TParams params);
}
