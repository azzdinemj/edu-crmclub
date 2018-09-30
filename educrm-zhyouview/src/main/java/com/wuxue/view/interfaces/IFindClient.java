package com.wuxue.view.interfaces;

public interface IFindClient<TParams,TResponse> {
    TResponse find(TParams params);

}
