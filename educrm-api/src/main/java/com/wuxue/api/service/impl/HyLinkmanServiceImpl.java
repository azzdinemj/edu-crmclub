package com.wuxue.api.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wuxue.api.mapper.HyLinkmanMapper;
import com.wuxue.api.mapper.HyOrderDetailMapper;
import com.wuxue.api.mapper.HyOrderMapper;
import com.wuxue.api.mapper.HyUserMapper;
import com.wuxue.api.service.HyLinkmanService;
import com.wuxue.model.HyLinkman;
import com.wuxue.model.HyOrder;
import com.wuxue.model.HyOrderDetail;
import com.wuxue.model.HyUser;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service("hyLinkmanService")
public class HyLinkmanServiceImpl implements HyLinkmanService {


    @Autowired
    HyLinkmanMapper hyLinkmanMapper;
    @Autowired
    HyOrderMapper hyOrderMapper;
    @Autowired
    HyUserMapper hyUserMapper;
    @Autowired
    HyOrderDetailMapper hyOrderDetailMapper;

    @Override
    public Response find(Request<HyLinkman> tParams) {
        Response response = Response.newResponse();
        HyLinkman hyLinkman = tParams.getData();
        if(hyLinkman== null){
            return  response.PARAMS_ISNULL();
        }

        if(hyLinkman.getId()!=null){ //查找联系人绑定的 订单 订单明细，出行人
            HyLinkman byPrimaryKey = hyLinkmanMapper.selectByPrimaryKey(hyLinkman.getId());
            if(byPrimaryKey!=null){

                HyOrder hyOrder=new HyOrder();
                hyOrder.setLinkmanId(byPrimaryKey.getId());
                List<HyOrder> hyOrderList=hyOrderMapper.select(hyOrder);
                if(hyOrderList.size()>0){

                    HyOrderDetail hyOrderDetail=new HyOrderDetail();
                    hyOrderDetail.setOrderId(hyOrderList.get(0).getId());
                    List<HyOrderDetail> hyOrderDetailList=hyOrderDetailMapper.select(hyOrderDetail);
                    if(hyOrderDetailList.size()>0){
                        for (HyOrderDetail h:hyOrderDetailList) {
                            HyUser hyUser=hyUserMapper.selectByPrimaryKey(h.getUserId());
                            h.put("HyUser",hyUser);
                        }
                        hyOrderList.get(0).put("HyOrderDetailList",hyOrderDetailList);
                    }
                    byPrimaryKey.put("HyOrder",hyOrderList.get(0));
                }
            }
            response.setData(byPrimaryKey);
        } else{
            PageHelper.startPage(hyLinkman.getPageNo(), hyLinkman.getPageSize());
            List<HyLinkman> hyLinkmanList = hyLinkmanMapper.select(hyLinkman);
            PageInfo page = new PageInfo(hyLinkmanList);
            if(hyLinkmanList.size()>0){//查找联系人绑定的订单
                for(HyLinkman hyLinkman1:hyLinkmanList ){
                    HyOrder  hyOrder=new HyOrder();
                    hyOrder.setLinkmanId(hyLinkman1.getId());
                    List<HyOrder> hyOrders= hyOrderMapper.select(hyOrder);
                    if(hyOrders.size()>0){
                        hyLinkman1.put("hyOrder",hyOrders.get(0));
                    }
                }

            }
            response.setData(hyLinkmanList);
            response.setTotal(page.getTotal());
        }

        return  response;
    }

    @Override
    public Response save(Request<HyLinkman> tParams) {
        Response response = Response.newResponse();
        HyLinkman hyLinkman = tParams.getData();
        if(hyLinkman== null){
            return  response.PARAMS_ISNULL();
        }
        int iReuslt = 1;
        String message= "";
        Integer id=hyLinkman.getId();
        try {
            if (id != null) {
                iReuslt = hyLinkmanMapper.updateByPrimaryKeySelective(hyLinkman);
            } else {
                hyLinkman.setCreatorDate(new Date());
                iReuslt = hyLinkmanMapper.insertSelective(hyLinkman);
                response.setData(hyLinkman.getId());
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
