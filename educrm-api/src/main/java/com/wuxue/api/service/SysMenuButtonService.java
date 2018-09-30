package com.wuxue.api.service;


import com.wuxue.api.interfaces.IDeleteService;
import com.wuxue.api.interfaces.IFindService;
import com.wuxue.api.interfaces.ISaveService;
import com.wuxue.model.SysMenuButton;
import com.wuxue.model.SysMenuButtonKey;

public interface SysMenuButtonService extends ISaveService<SysMenuButton>,IFindService<SysMenuButton>,IDeleteService<SysMenuButtonKey> {
}
