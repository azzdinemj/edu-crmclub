package com.wuxue.api.service;


import com.wuxue.api.interfaces.IDeleteService;
import com.wuxue.api.interfaces.IFindService;
import com.wuxue.api.interfaces.ISaveService;
import com.wuxue.model. SysRole;
import com.wuxue.model.SysUser;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;

public interface SysRoleService extends ISaveService<SysRole>,IFindService<SysRole>,IDeleteService<String> {

    /**
     * 获取用户所属校区的全部角色
     * */
    Response domainRole(Request<String> sysUser);

    /**
     * 获取当前用户的关联的角色
     * */
    Response getUserRole(Request<String> sysUser);
}
