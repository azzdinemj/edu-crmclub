package com.wuxue.api.service;

import com.wuxue.api.interfaces.IDeleteService;
import com.wuxue.api.interfaces.IFindService;
import com.wuxue.api.interfaces.ISaveService;
import com.wuxue.model.Schedule;
import com.wuxue.model.SysLog;

import java.util.List;

/**
 * Created by Jamie on 2018/4/15.
 */
public interface SysLogService extends IFindService<SysLog> {
    void insert(List<SysLog> list);
}
