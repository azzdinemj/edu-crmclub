package com.wuxue.api.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wuxue.api.mapper.ProductMapper;
import com.wuxue.api.mapper.TkProductMarkMapper;
import com.wuxue.api.service.TkProductMarkService;
import com.wuxue.api.utils.GuidUtils;
import com.wuxue.model.Product;
import com.wuxue.model.TkLearnRecords;
import com.wuxue.model.TkProductMark;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Auther: wh
 * @Date: 2018/6/13 14:32
 * @Description: 产品（课程）收藏 service
 */
@Service("tkProductMarkService")
public class TkProductMarkServiceImpl implements TkProductMarkService {

    @Autowired
    TkProductMarkMapper tkProductMarkMapper;

    @Autowired
    ProductMapper productMapper;

    /**
     * 取消课程收藏
     * @param tParams
     * @return
     */
    @Override
    public Response delete(Request<TkProductMark> tParams) {
        Response response = Response.newResponse();
        TkProductMark primaryKey = tParams.getData();
        if (primaryKey.getPkProductMark() == null || primaryKey.equals("")) {
            return response.PARAMS_ISNULL();
        }
        int iReuslt = 1;
        String message = "";
        try {
            iReuslt = tkProductMarkMapper.deleteByPrimaryKey(primaryKey.getPkProductMark());
        } catch (Exception ex) {
            message = ex.getMessage();
            return response.DELETE_FAIL(message);
        }
        if (iReuslt > 0) {
            return response;
        }
        return response.DELETE_FAIL(message);
    }


    /**
     * 查找 收藏记录
     * @param tParams
     * @return
     */
    @Override
    public Response find(Request<TkProductMark> tParams) {
        Response response = Response.newResponse();
        TkProductMark tkproductOrder = tParams.getData();

        if (tkproductOrder == null) {
            return response.PARAMS_ISNULL();
        }
        String primaryKey = tkproductOrder.getPkProductMark();
        if (primaryKey != null && !primaryKey.equals("")) {
            TkProductMark byPrimaryKey = tkProductMarkMapper.selectByPrimaryKey(primaryKey);

            Product product=productMapper.selectByPrimaryKey(byPrimaryKey.getPkProduct());
            byPrimaryKey.put("product",product);

            response.setData(byPrimaryKey);
        } else {
            PageHelper.startPage(tkproductOrder.getPageNo(), tkproductOrder.getPageSize());
            List<TkProductMark> tkProductMarkList = tkProductMarkMapper.select(tkproductOrder);

            for (TkProductMark t:tkProductMarkList) {
                Product product=productMapper.selectByPrimaryKey(t.getPkProduct());
                t.put("product",product);
            }

            PageInfo page = new PageInfo(tkProductMarkList);
            response.setData(tkProductMarkList);
            response.setTotal(page.getTotal());
        }
        return response;
    }

    @Override
    public Response save(Request<TkProductMark> tParams) {
        Response response = Response.newResponse();
        TkProductMark tkProductMark = tParams.getData();
        if(tkProductMark== null){
            return  response.PARAMS_ISNULL();
        }
        int iReuslt = 1;
        String message= "";
        String pk=tkProductMark.getPkProductMark();
        try {
            if (pk != null&&!"".equals(pk)) {
                iReuslt = tkProductMarkMapper.updateByPrimaryKeySelective(tkProductMark);
            } else {
                tkProductMark.setCreationDate(new Date());
                tkProductMark.setPkProductMark(GuidUtils.getGuid());
                iReuslt = tkProductMarkMapper.insertSelective(tkProductMark);
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
