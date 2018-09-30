package com.wuxue.api.service;


import com.wuxue.api.interfaces.IDeleteService;
import com.wuxue.api.interfaces.IFindService;
import com.wuxue.api.interfaces.ISaveService;
import com.wuxue.model.SysUser;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;

public interface SysUserService extends ISaveService<SysUser>,IFindService<SysUser>,IDeleteService<String> {
    Response login(Request<SysUser> tParams);

    /**
     * 修改密码
     * */
    Response updatePassword(Request<SysUser> sysUser);
}
