package com.wuxue.api.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wuxue.api.mapper.EmployeeCertificateMapper;
import com.wuxue.api.service.SysAutoCodeService;
import com.wuxue.api.service.UtilsService;
import com.wuxue.model.EmployeeCertificate;
import com.wuxue.api.service.EmployeeCertificateService;
import com.wuxue.api.utils.GuidUtils;
import com.wuxue.utils.contract.LinkEntity;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

//import com.wuxue.api.utils.GuidUtils;

@Service("employeeCertificateService")
public class EmployeeCertificateServiceImpl implements EmployeeCertificateService{
    @Autowired
    EmployeeCertificateMapper employeeCertificateMapper;
    @Autowired
    SysAutoCodeService sysAutocodeService;
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
            iReuslt=employeeCertificateMapper.deleteByPrimaryKey(primaryKey);
        }catch (Exception ex){
            message = ex.getMessage();
            return response.DELETE_FAIL(message);
        }
        if(iReuslt>0) {
            return response;
        }
        return response.DELETE_FAIL(message);
    }

    @Override
    public Response find(Request<EmployeeCertificate> tParams) {
        Response response = Response.newResponse();
        EmployeeCertificate employeeCertificate = tParams.getData();

        if(employeeCertificate== null){
            return  response.PARAMS_ISNULL();
        }
        String primaryKey = employeeCertificate.getPkEmployeeCertificate();
        if(primaryKey !=null && !primaryKey.equals("")){
            EmployeeCertificate byPrimaryKey = employeeCertificateMapper.selectByPrimaryKey(primaryKey);
            utilsService.setSysUserKeyValue(byPrimaryKey,byPrimaryKey.getCreator(), LinkEntity.CREATOR_ENTITY);
            utilsService.setSysUserKeyValue(byPrimaryKey,byPrimaryKey.getModifier(),LinkEntity.MODIFIER_ENTITY);
            response.setData(byPrimaryKey);
        }else{
            PageHelper.startPage(employeeCertificate.getPageNo(),employeeCertificate.getPageSize());
            List<EmployeeCertificate> certificateList = employeeCertificateMapper.select(employeeCertificate);
            PageInfo pageInfo = new PageInfo(certificateList);
            response.setTotal(pageInfo.getTotal());
            if (certificateList.size() > 0) {
                for (EmployeeCertificate list : certificateList) {
                    utilsService.setSysUserKeyValue(list, list.getCreator(), LinkEntity.CREATOR_ENTITY);
                    utilsService.setSysUserKeyValue(list, list.getModifier(), LinkEntity.MODIFIER_ENTITY);
                }
            }
            response.setData(certificateList);
            //response.setTotal(employeeCertificateMapper.countBy(employeeCertificate));

        }
        return response;
    }

    @Override
    public Response save(Request<EmployeeCertificate> tParams) {
        Response response = Response.newResponse();
        EmployeeCertificate employeeCertificate = tParams.getData();

        if(employeeCertificate== null){
            return  response.PARAMS_ISNULL();
        }
        String primaryKey = employeeCertificate.getPkEmployeeCertificate();
        EmployeeCertificate select = null;
        if (primaryKey != null && !primaryKey.equals("")) {
            select = employeeCertificateMapper.selectByPrimaryKey(primaryKey);
        }
        int iReuslt = 1;
        String message= "";
        try {
            if (select != null) {
                employeeCertificate.setLasteditDate(new Date());
                iReuslt = employeeCertificateMapper.updateByPrimaryKeySelective(employeeCertificate);
            } else {
                employeeCertificate.setPkEmployeeCertificate(GuidUtils.getGuid());
                employeeCertificate.setCreationDate(new Date());
                employeeCertificate.setLasteditDate(new Date());
                iReuslt = employeeCertificateMapper.insertSelective(employeeCertificate);
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
