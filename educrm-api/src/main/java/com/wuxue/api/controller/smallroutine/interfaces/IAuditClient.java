package com.wuxue.api.controller.smallroutine.interfaces;

/**
* @Description: 公用泛型client接口
* @author wanghao
* @date  15:05 2018/3/8
* @version V1.0
*/
public interface IAuditClient<TParams,TResponse> {
    TResponse audit(TParams params);

}
