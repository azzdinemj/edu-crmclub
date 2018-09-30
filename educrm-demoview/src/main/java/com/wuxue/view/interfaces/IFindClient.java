package com.wuxue.view.interfaces;

import com.wuxue.utils.contract.Response;
import java.util.List;

public interface IFindClient<TParams,TResponse> {
    TResponse find(TParams params);

}
