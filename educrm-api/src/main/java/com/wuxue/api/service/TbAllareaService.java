package com.wuxue.api.service;


import com.wuxue.api.interfaces.IDeleteService;
import com.wuxue.api.interfaces.IFindService;
import com.wuxue.api.interfaces.ISaveService;
import com.wuxue.model.SysUser;
import com.wuxue.model.TbAllarea;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;

public interface TbAllareaService extends ISaveService<TbAllarea>,IFindService<TbAllarea>,IDeleteService<String> {


}
