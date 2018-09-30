package com.wuxue.api.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wuxue.api.mapper.ParentOrderEditMapper;
import com.wuxue.api.service.ParentOrderEditService;
import com.wuxue.api.service.ParentPayService;
import com.wuxue.api.utils.GuidUtils;
import com.wuxue.model.ParentOrderEdit;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("parentOrderEditService")
public class ParentOrderEditServiceImpl implements ParentOrderEditService {

    @Autowired
    private ParentOrderEditMapper parentOrderEditMapper;
    @Autowired
    private ParentPayService parentPayService;

    @Override
    public Response delete(Request<String> tParams) {
        Response response = Response.newResponse();

        String primaryKey = tParams.getData();
        if (primaryKey == null || "".equals(primaryKey)) {
            return response.PARAMS_ISNULL();
        }
        String message = "";
        try {
            ParentOrderEdit parentOrderEdit = parentOrderEditMapper.selectByPrimaryKey(primaryKey);
            if (parentOrderEdit != null) {
                parentOrderEditMapper.updateByPrimaryKeySelective(parentOrderEdit);
            }


        } catch (Exception ex) {
            message = ex.getMessage();
            return response.DELETE_FAIL(message);
        }


        return response.DELETE_FAIL(message);
    }

    @Override
    public Response find(Request<ParentOrderEdit> tParams) {
        Response response = Response.newResponse();
        ParentOrderEdit parentOrderEdit = tParams.getData();
        if (parentOrderEdit == null) {
            return response.PARAMS_ISNULL();
        }
        String primaryKey = parentOrderEdit.getPkOrderEdit();
        if (primaryKey != null && !"".equals(primaryKey)) {
            ParentOrderEdit parentOrderEdit1 = parentOrderEditMapper.selectByPrimaryKey(primaryKey);
            response.setData(parentOrderEdit1);
        } else {
            PageHelper.startPage(parentOrderEdit.getPageNo(), parentOrderEdit.getPageSize());
            List<ParentOrderEdit> list = parentOrderEditMapper.select(parentOrderEdit);
            PageInfo page = new PageInfo(list);
            response.setTotal(page.getTotal());

        }


        return response;
    }

    @Override
    public Response save(Request<ParentOrderEdit> tParams) {
        Response response = Response.newResponse();
        ParentOrderEdit parentOrderEdit = tParams.getData();
        if (parentOrderEdit == null) {
            return response.PARAMS_ISNULL();
        }
        String primaryKey = parentOrderEdit.getPkOrderEdit();
        String message = "";
        ParentOrderEdit select = null;
        if (primaryKey != null && !"".equals(primaryKey)) {
            select = parentOrderEditMapper.selectByPrimaryKey(primaryKey);
        }

        try {
            if (select != null) {//修改
                parentOrderEdit.setLasteditDate(new Date());
                parentOrderEditMapper.updateByPrimaryKeySelective(parentOrderEdit);
            } else {//新增
                parentOrderEdit.setPkOrderEdit(GuidUtils.getGuid());
                parentOrderEdit.setCreationDate(new Date());
                parentOrderEditMapper.insertSelective(parentOrderEdit);
            }

        } catch (Exception ex) {
            message = ex.getMessage();
            return response.SAVE_FAIL(message);
        }

        return response.SAVE_FAIL(message);
    }

}
