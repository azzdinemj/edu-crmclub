package com.wuxue.api.interfaces;

import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;

public interface IAuditService<TParams> {

    Response audit(Request<TParams> tParams);

}
