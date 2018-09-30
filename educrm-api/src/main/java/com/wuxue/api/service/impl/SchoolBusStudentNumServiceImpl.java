package com.wuxue.api.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wuxue.api.mapper.*;
import com.wuxue.api.service.SchoolBusStudentNumService;
import com.wuxue.api.service.UtilsService;
import com.wuxue.api.utils.GuidUtils;
import com.wuxue.model.*;
import com.wuxue.utils.contract.Light;
import com.wuxue.utils.contract.LinkEntity;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//import com.wuxue.api.utils.GuidUtils;

@Service("schoolBusStudentNumService")
public class SchoolBusStudentNumServiceImpl implements SchoolBusStudentNumService{


   @Autowired
   private SchoolBusStudentNumMapper schoolBusStudentNumMapper;
   @Autowired
   private UtilsService utilsService;

    @Override
    public Response find(Request<SchoolBusStudentNum> tParams) {
        Response response = Response.newResponse();
        SchoolBusStudentNum schoolBusStudentNum = tParams.getData();
        PageHelper.startPage(schoolBusStudentNum.getPageNo(), schoolBusStudentNum.getPageSize());
        List<SchoolBusStudentNum> select = schoolBusStudentNumMapper.select(schoolBusStudentNum);
        PageInfo page = new PageInfo(select);
        response.setTotal(page.getTotal());
        if (select != null && select.size() >0){
            for (SchoolBusStudentNum busStudentNum : select) {
                utilsService.setDomainKeyValue(busStudentNum,busStudentNum.getPkDomain(),LinkEntity.DOMAIN_ENTITY);
            }
        }

        response.setData(select);
        return response;
    }

    @Override
    public Response save(Request<SchoolBusStudentNum> tParams) {
      return null;
    }
}
