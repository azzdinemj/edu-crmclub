package com.wuxue.api.service;

import com.wuxue.api.interfaces.IDeleteService;
import com.wuxue.api.interfaces.IFindService;
import com.wuxue.api.interfaces.ISaveService;
import com.wuxue.model.SysMenu;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;

public interface SysMenuService extends ISaveService<SysMenu>,IFindService<SysMenu>,IDeleteService<String>{

    /**
     * 查询 父主键不为空的数据
     * @param request
     * @return
     */
    Response findparentisnotnull(Request<SysMenu> request);

    /**
     * 查询 父主键为空的数据
     * @param request
     * @return
     */
    Response findparentisnull(Request<SysMenu> request);
}
