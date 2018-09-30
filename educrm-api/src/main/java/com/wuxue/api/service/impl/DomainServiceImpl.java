package com.wuxue.api.service.impl;

import com.wuxue.api.mapper.DomainMapper;
import com.wuxue.api.service.UtilsService;
import com.wuxue.model.Domain;
import com.wuxue.api.service.DomainService;
import com.wuxue.api.utils.GuidUtils;
import com.wuxue.utils.contract.LinkEntity;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

//import com.wuxue.api.utils.GuidUtils;

@Service("domainService")
public class DomainServiceImpl implements DomainService{
    @Autowired
    DomainMapper domainMapper;
    @Autowired
    UtilsService utilsService;

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
            iReuslt=domainMapper.deleteByPrimaryKey(primaryKey);
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
    public Response find(Request<Domain> tParams) {
        Response response = Response.newResponse();
        Domain domain = tParams.getData();

        if(domain== null){
            return  response.PARAMS_ISNULL();
        }
        String primaryKey = domain.getPkDomain();
        if(primaryKey !=null && !primaryKey.equals("")){
            Domain byPrimaryKey = domainMapper.selectByPrimaryKey(primaryKey);
            utilsService.setSysUserKeyValue(byPrimaryKey,byPrimaryKey.getCreator(), LinkEntity.CREATOR_ENTITY);
            utilsService.setSysUserKeyValue(byPrimaryKey,byPrimaryKey.getModifier(),LinkEntity.MODIFIER_ENTITY);
            response.setData(byPrimaryKey);
        }else{
            domain.setIsvalid(1);
            List<Domain> domainList = domainMapper.select(domain);
            if (domainList.size() > 0) {
                for (Domain list : domainList) {
                    utilsService.setSysUserKeyValue(list, list.getCreator(), LinkEntity.CREATOR_ENTITY);
                    utilsService.setSysUserKeyValue(list, list.getModifier(), LinkEntity.MODIFIER_ENTITY);
                }
            }
            response.setData(domainList);
            //response.setTotal(domainMapper.countBy(domain));

        }
        return response;
    }

    @Override
    public Response save(Request<Domain> tParams) {
        Response response = Response.newResponse();
        Domain domain = tParams.getData();

        if(domain== null){
            return  response.PARAMS_ISNULL();
        }
        String primaryKey = domain.getPkDomain();
        Domain select = null;
        if (primaryKey != null && !primaryKey.equals("")) {
            select = domainMapper.selectByPrimaryKey(primaryKey);
        }
        int iReuslt = 1;
        String message= "";
        try {
            if (select != null) {
                domain.setLasteditDate(new Date());
                iReuslt = domainMapper.updateByPrimaryKeySelective(domain);
            } else {
                domain.setPkDomain(GuidUtils.getGuid());
                domain.setCreationDate(new Date());
                domain.setLasteditDate(new Date());
                domain.setIsvalid(1);
                iReuslt = domainMapper.insertSelective(domain);
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

    @Override
    public Response getUrl(Request<Domain> tParams) {
        Response response = Response.newResponse();
        Domain domain = domainMapper.selectByPrimaryKey("1");
        response.setData(domain);
        return response;
    }
}
