package com.wuxue.api.service;


import com.wuxue.api.interfaces.IDeleteService;
import com.wuxue.api.interfaces.IFindService;
import com.wuxue.api.interfaces.ISaveService;
import com.wuxue.model.SysAutocode;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;

public interface SysAutoCodeService extends ISaveService<SysAutocode>,IFindService<SysAutocode>,IDeleteService<String> {
    Response getAutocode(Request<SysAutocode> sysAutocode);

    Response updateCodeNum(SysAutocode sysAutocode);

    String getCode(String key);
}
