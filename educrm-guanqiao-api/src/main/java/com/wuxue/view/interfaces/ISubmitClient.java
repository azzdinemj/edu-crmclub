package com.wuxue.view.interfaces;

public interface ISubmitClient<TParams,TResponse> {
    TResponse submit(TParams params);

}
