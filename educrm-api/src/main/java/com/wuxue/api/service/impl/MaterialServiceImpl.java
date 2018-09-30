package com.wuxue.api.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wuxue.api.mapper.MaterialMapper;
import com.wuxue.api.service.MaterialService;
import com.wuxue.api.service.UtilsService;
import com.wuxue.api.utils.GuidUtils;
import com.wuxue.model.junhwa.Material;
import com.wuxue.utils.contract.LinkEntity;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

//import com.wuxue.api.utils.GuidUtils;

@Service("materialService")
public class MaterialServiceImpl implements MaterialService{
    @Autowired
    MaterialMapper materialMapper;

    @Override
    public Response delete(Request<String> tParams) {
        Response response = Response.newResponse();
        String primaryKey = tParams.getData();
        if(primaryKey== null || primaryKey.equals("")){
            return  response.PARAMS_ISNULL();
        }
        int iReuslt = 1;
        String message= "";
        try {
            iReuslt=materialMapper.deleteByPrimaryKey(primaryKey);
        }catch (Exception ex){
            message = ex.getMessage();
            return response.SAVE_FAIL(message);
        }
        if(iReuslt>0) {
            return response;
        }
        return response.DELETE_FAIL(message);
    }

    @Override
    public Response find(Request<Material> tParams) {
        Response response = Response.newResponse();
        Material material = tParams.getData();

        if(material== null){
            return  response.PARAMS_ISNULL();
        }
        String primaryKey = material.getMaterialId();
        if(primaryKey !=null && !primaryKey.equals("")){
            Material byPrimaryKey = materialMapper.selectByPrimaryKey(primaryKey);
            response.setData(byPrimaryKey);
        }else{
//            material.setIsvalid(1);
            PageHelper.startPage(material.getPageNo(), material.getPageSize());
            List<Material> materialList = materialMapper.select(material);
            PageInfo page = new PageInfo(materialList);
            response.setData(materialList);
            response.setTotal(page.getTotal());

        }
        return response;
    }

    @Override
    public Response save(Request<Material> tParams) {
        Response response = Response.newResponse();
        Material material = tParams.getData();

        if(material== null){
            return  response.PARAMS_ISNULL();
        }
        String primaryKey = material.getMaterialId();
        Material select = null;
        if (primaryKey != null && !primaryKey.equals("")) {
            select = materialMapper.selectByPrimaryKey(primaryKey);
        }
        int iReuslt = 1;
        String message= "";
        try {
            if (select != null) {
                material.setLasteditDate(new Date());
                iReuslt = materialMapper.updateByPrimaryKeySelective(material);
            } else {
                material.setMaterialId(GuidUtils.getGuid());
                material.setCreator(tParams.getCurrentUser());
                material.setModifier(tParams.getCurrentUser());
                material.setCreationDate(new Date());
                material.setLasteditDate(new Date());
//                material.setIsvalid(1);
                iReuslt = materialMapper.insertSelective(material);
            }
        }catch (Exception ex){
            message = ex.getMessage();
            return response.SAVE_FAIL(message);
        }
        if(iReuslt>0) {
            return response;
        }
        return response.SAVE_FAIL(message);
    }
}
