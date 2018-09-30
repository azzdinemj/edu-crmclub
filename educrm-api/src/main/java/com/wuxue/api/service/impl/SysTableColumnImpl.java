package com.wuxue.api.service.impl;

import com.wuxue.api.mapper.SysTableColumnMapper;
import com.wuxue.api.mapper.SysTableColumnsMapper;
import com.wuxue.api.service.SysTableColumnService;
import com.wuxue.model.SysTableColumn;
import com.wuxue.model.SysTableColumns;
import com.wuxue.utils.contract.Light;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Jamie on 2018/4/1.
 */
@Service("sysTableColumn")
public class SysTableColumnImpl implements SysTableColumnService{
    @Autowired
    SysTableColumnMapper sysTableColumnMapper;
    @Autowired
    SysTableColumnsMapper sysTableColumnsMapper;

    @Override
    public Response find(Request<SysTableColumn> tParams) {
        Response response = Response.newResponse();
        SysTableColumn sysTableColumn = tParams.getData();

        SysTableColumn sysTableColumnTmp = sysTableColumnMapper.selectByPrimaryKey(sysTableColumn.getPkSysTableColumn());
        List<SysTableColumns> list = sysTableColumnsMapper.selectByParentKey(sysTableColumn.getPkSysTableColumn());
        sysTableColumnTmp.put(Light.CHILD,list);
        response.setData(sysTableColumnTmp);
        return response;

    }
}
