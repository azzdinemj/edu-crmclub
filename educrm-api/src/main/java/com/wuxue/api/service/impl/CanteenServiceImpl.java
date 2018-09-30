package com.wuxue.api.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wuxue.api.mapper.CanteenMapper;
import com.wuxue.api.service.CanteenService;
import com.wuxue.api.utils.GuidUtils;
import com.wuxue.model.Canteen;
import com.wuxue.model.Classinfo;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("canteenService")
public class CanteenServiceImpl implements CanteenService {

    @Autowired
    private CanteenMapper canteenMapper;

    @Override
    public Response delete(Request<String> tParams) {
        Response response = Response.newResponse();

        String primaryKey = tParams.getData();
        if (primaryKey == null || "".equals(primaryKey)) {
            return response.PARAMS_ISNULL();
        }
        String message = "";
        try {
            Canteen canteen = canteenMapper.selectByPrimaryKey(primaryKey);
            if (canteen != null) {
                canteen.setIsvalid(0);
                canteenMapper.updateByPrimaryKeySelective(canteen);
            }


        } catch (Exception ex) {
            message = ex.getMessage();
            return response.DELETE_FAIL(message);
        }


        return response.DELETE_FAIL(message);
    }

    @Override
    public Response find(Request<Canteen> tParams) {
        Response response = Response.newResponse();
        Canteen canteen = tParams.getData();
        if (canteen == null) {
            return response.PARAMS_ISNULL();
        }
        String primaryKey = canteen.getPkCanteen();
        if (primaryKey != null && !"".equals(primaryKey)) {
            Canteen canteen1 = canteenMapper.selectByPrimaryKey(primaryKey);
            response.setData(canteen1);
        } else {
            PageHelper.startPage(canteen.getPageNo(), canteen.getPageSize());
            List<Canteen> list = canteenMapper.select(canteen);
            PageInfo page = new PageInfo(list);
            response.setTotal(page.getTotal());
            response.setData(list);

        }


        return response;
    }

    @Override
    public Response save(Request<Canteen> tParams) {
        Response response = Response.newResponse();
        Canteen canteen = tParams.getData();
        if (canteen == null) {
            return response.PARAMS_ISNULL();
        }
        String primaryKey = canteen.getPkCanteen();
        String message = "";
        Canteen select = null;
        if (primaryKey != null && !"".equals(primaryKey)) {
            select = canteenMapper.selectByPrimaryKey(primaryKey);
        }

        try {
            if (select != null) {
                canteen.setLasteditDate(new Date());
                canteenMapper.updateByPrimaryKeySelective(canteen);
            } else {
                canteen.setPkCanteen(GuidUtils.getGuid());
                canteen.setCreationDate(new Date());
                canteen.setLasteditDate(new Date());
                canteen.setIsvalid(1);
                canteenMapper.insertSelective(canteen);
            }

        } catch (Exception ex) {
            message = ex.getMessage();
            return response.SAVE_FAIL(message);
        }

        return response;
    }
}
