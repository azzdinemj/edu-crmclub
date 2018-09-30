package com.wuxue.api.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wuxue.api.mapper.*;
import com.wuxue.api.service.HyOrderService;
import com.wuxue.model.*;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


@Service("hyOrderService")
public class HyOrderServiceImpl implements HyOrderService {

    @Autowired
    HyOrderMapper hyOrderMapper;
    @Autowired
    HyOrderDetailMapper hyOrderDetailMapper;
    @Autowired
    HyUserMapper hyUserMapper;
    @Autowired
    HyLinkmanMapper hyLinkmanMapper;
    @Autowired
    HyPayMapper hyPayMapper;
    @Autowired
    HyOrderChangeMapper hyOrderChangeMapper;

   @Override
    public Response find(Request<HyOrder> tParams) {
        Response response = Response.newResponse();
        HyOrder hyUser = tParams.getData();
        if(hyUser== null){
            return  response.PARAMS_ISNULL();
        }
        if(hyUser.getId()!=null){
            HyOrder byPrimaryKey = hyOrderMapper.selectByPrimaryKey(hyUser.getId());

            if(byPrimaryKey!=null){
                HyOrderDetail hyOrderDetail=new HyOrderDetail();
                hyOrderDetail.setOrderId(byPrimaryKey.getId());
                //订单明细
                List<HyOrderDetail> detailResponse=hyOrderDetailMapper.select(hyOrderDetail);
                if(detailResponse.size()>0){
                    for(HyOrderDetail hyOrderDetail1:detailResponse){//订单明细对应的出行人
                        HyUser hyUserResponse= hyUserMapper.selectByPrimaryKey(hyOrderDetail1.getUserId());
                        hyOrderDetail1.put("hyUser",hyUserResponse);
                    }
                }
                byPrimaryKey.put("hyOrderDetail",detailResponse);
            }
            response.setData(byPrimaryKey);
        } else{ //根据订单查询出订单明细，出行人
            PageHelper.startPage(hyUser.getPageNo(), hyUser.getPageSize());
            List<HyOrder> hyOrderList = hyOrderMapper.select(hyUser);
            PageInfo page = new PageInfo(hyOrderList);
            if(hyOrderList.size()>0){
                for (HyOrder h:hyOrderList) {
                    HyOrderDetail hyOrderDetail=new HyOrderDetail();
                    hyOrderDetail.setOrderId(h.getId());
                    //订单明细
                    List<HyOrderDetail> detailResponse=hyOrderDetailMapper.select(hyOrderDetail);
                    if(detailResponse.size()>0){
                        for(HyOrderDetail hyOrderDetail1:detailResponse){//订单明细对应的出行人
                            HyUser hyUserResponse= hyUserMapper.selectByPrimaryKey(hyOrderDetail1.getUserId());
                            if(hyUserResponse!=null){
                                hyOrderDetail1.put("hyUser",hyUserResponse.getName());
                            }
                        }
                    }
                    h.put("hyOrderDetail",detailResponse);
                }

                
            }

            response.setData(hyOrderList);
            response.setTotal(page.getTotal());
        }

        return  response;
    }

    @Override
    public Response save(Request<HyOrder> tParams) {
        Response response = Response.newResponse();
        HyOrder hyUser = tParams.getData();
        if(hyUser== null){
            return  response.PARAMS_ISNULL();
        }
        int iReuslt = 1;
        String message= "";
        Integer id=hyUser.getId();
        try {
            if (id != null) {
                iReuslt = hyOrderMapper.updateByPrimaryKeySelective(hyUser);
            } else {
                hyUser.setCreatorDate(new Date());
                iReuslt = hyOrderMapper.insertSelective(hyUser);
                response.setData(hyUser.getId());
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
    public Response countBy(Request<HyOrder> tParams) {
        Response response=Response.newResponse();
        HyOrder hyOrder=tParams.getData();
        Integer  i= hyOrderMapper.countBy(hyOrder);
        response.setData(i);
        return response;
    }

    /**
     * 下订单 保存订单  '保存订单明细   联系人   出行人
     * @param stringRequest
     * @return
     */
    @Transactional(rollbackFor =Exception.class)
    @Override
    public Response saveOrder(Request<String> stringRequest) {
        Response response = Response.newResponse();
        String StrJSon = stringRequest.getData();
        if(StrJSon== null){
            return  response.PARAMS_ISNULL();
        }
        //数据JSON字符串
        JSONObject jsonObject = JSON.parseObject(StrJSon);

        // 联系人
        HyLinkman hyLinkman = JSON.parseObject(StrJSon, new TypeReference<HyLinkman>() {});

        // 订单
        Object jsonObjectOrder=jsonObject.get("hyOrder");
        HyOrder hyOrder = JSON.parseObject(jsonObjectOrder.toString(), new TypeReference<HyOrder>() {});

        // 出行人对象集合
        Object jsonObjectUser=jsonObject.get("hyUserList");
        List<HyUser> hyUserList = JSON.parseObject(jsonObjectUser.toString(),new TypeReference<List<HyUser>>(){});

        int iReuslt = 1;
        String message= "失败";

            hyLinkman.setCreatorDate(new Date());
            iReuslt=hyLinkmanMapper.insertSelective(hyLinkman);
            //保存订单
            if(iReuslt>0){
                hyOrder.setLinkmanId(hyLinkman.getId());
                hyOrder.setStatus("0"); //订单状态  进行中
                hyOrder.setCreatorDate(new Date()); //订单状态  进行中
                iReuslt=hyOrderMapper.insertSelective(hyOrder);
            }
            //添加支付记录
            if(iReuslt>0){
                HyPay hyPay=new HyPay();
                hyPay.setCeratorDate(new Date());
                hyPay.setOrderId(hyOrder.getId());
                hyPay.setStatus("0"); //未支付
                hyPay.setPayment("0");//微信支付
                hyPay.setPaymentType("0");//流入
                hyPay.setAmount(hyOrder.getAdvance());
                iReuslt=hyPayMapper.insertSelective(hyPay);
                response.setData(hyPay.getId());
            }

            //保存出行人,保存订单明细
            if(iReuslt>0){
                for (HyUser hyUser:hyUserList) {
                    String productid=hyOrder.getProductId().toString();
                    if(productid!=null){
                        hyUser.setProductId(Integer.parseInt(productid.substring(0,1)));
                    }
                    hyUser.setCreatorDate(new Date());
                    iReuslt=hyUserMapper.insertSelective(hyUser);
                    if(iReuslt>0){
                        HyOrderDetail hyOrderDetail=new HyOrderDetail();
                        hyOrderDetail.setOrderId(hyOrder.getId()); //订单id 外键
                        hyOrderDetail.setUserId(hyUser.getId());//出行人id 外键
                        hyOrderDetail.setStatus("0");//订单明细状态   2进行中
                        hyOrderDetail.setCreatorDate(new Date());
                        iReuslt=hyOrderDetailMapper.insertSelective(hyOrderDetail);
                    }
                }
            }

        if(iReuslt>0) {
            return response;
        }
        return response.SAVE_FAIL(message);
    }

    /**
     *修改订单状态 (退款，完成)，每变更一次增加一条订单变更记录。若是退款，添加退款记录
     * @param tParams
     * @return
     */
    @Transactional(rollbackFor =Exception.class)
    @Override
    public Response updateStatus(Request<HyOrder> tParams) {
        Response response = Response.newResponse();
        HyOrder hyOrder = tParams.getData();
        if(hyOrder== null){
            return  response.PARAMS_ISNULL();
        }

        int iReuslt = 1;
        String message= "失败";

        //保存订单修改记录
        HyOrderChange hyOrderChange=new HyOrderChange();
        hyOrderChange.setOrderId(hyOrder.getId());
        hyOrderChange.setStatus(hyOrder.getStatus());
        iReuslt=hyOrderChangeMapper.insertSelective(hyOrderChange);

        //修改订单状态 (退款，完成)
        if(iReuslt>0){
            iReuslt=hyOrderMapper.updateByPrimaryKeySelective(hyOrder);
        }

        if(iReuslt>0) {
            return response;
        }
        return response.SAVE_FAIL(message);
    }




}
