package com.wuxue.api.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wuxue.api.mapper.DormRoomPerNumMapper;
import com.wuxue.api.service.DormRoomPerNumService;
import com.wuxue.api.service.UtilsService;
import com.wuxue.model.DormRoomPerNum;
import com.wuxue.model.DormRoomPerNum;
import com.wuxue.utils.contract.LinkEntity;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//import com.wuxue.api.utils.GuidUtils;

@Service("dormRoomPerNumService")
public class DormRoomPerNumServiceImpl implements DormRoomPerNumService{


   @Autowired
   private DormRoomPerNumMapper dormRoomPerNumMapper;
   @Autowired
   private UtilsService utilsService;

    @Override
    public Response find(Request<DormRoomPerNum> tParams) {
        Response response = Response.newResponse();
        DormRoomPerNum dormRoomPerNum = tParams.getData();
        PageHelper.startPage(dormRoomPerNum.getPageNo(), dormRoomPerNum.getPageSize());
        List<DormRoomPerNum> select = dormRoomPerNumMapper.select(dormRoomPerNum);
        PageInfo page = new PageInfo(select);
        response.setTotal(page.getTotal());
        if (select != null && select.size() >0){
            for (DormRoomPerNum busStudentNum : select) {
                utilsService.setDomainKeyValue(busStudentNum,busStudentNum.getPkDomain(),LinkEntity.DOMAIN_ENTITY);
                if (busStudentNum.getKind() != null && busStudentNum.getKind() ==0){
                    busStudentNum.setNum(busStudentNum.getStunum());
                }
                if (busStudentNum.getKind() != null && busStudentNum.getKind() ==1){
                    busStudentNum.setNum(busStudentNum.getEmpnum());
                }
            }
        }

        response.setData(select);
        return response;
    }

}
