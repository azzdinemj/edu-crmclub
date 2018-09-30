package com.wuxue.api.service;

import com.wuxue.api.interfaces.IFindService;
import com.wuxue.api.interfaces.ISaveService;
import com.wuxue.model.HyLinkman;
import com.wuxue.model.HyUser;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;

public interface HyUserService extends ISaveService<HyUser>,IFindService<HyUser>{
    Response countBy(Request<HyUser> tParams);

}
