package com.wuxue.view.interfaces;

public interface IAuditClient<TParams,TResponse> {
    TResponse audit(TParams params);

}
