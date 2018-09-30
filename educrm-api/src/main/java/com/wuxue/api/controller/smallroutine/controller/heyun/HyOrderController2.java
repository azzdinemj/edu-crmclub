package com.wuxue.api.controller.smallroutine.controller.heyun;

import com.wuxue.api.controller.smallroutine.client.heyun.HyLinkmanClient;
import com.wuxue.api.controller.smallroutine.client.heyun.HyOrderClient;
import com.wuxue.api.controller.smallroutine.client.heyun.HyPayClient;
import com.wuxue.api.controller.smallroutine.controller.BaseController;
import com.wuxue.model.HyLinkman;
import com.wuxue.model.HyOrder;
import com.wuxue.model.HyPay;
import com.wuxue.utils.contract.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * 订单
 */
@Controller
@RequestMapping("/hyOrder")
public class HyOrderController2 extends BaseController {
    @Autowired
    private HyOrderClient hyOrderClient;

    @Autowired
    private HyLinkmanClient hyLinkmanClient;

    @Autowired
    private HyPayClient hyPayClient;

    /**
     *
     * 查询 该用户的所有订单
     * @param openid
     * @return
     */
    @RequestMapping("/queryOrder")
    @ResponseBody
    public Response<List<HyOrder>> queryOrder(String openid) {
        Response response = Response.newResponse();
        List<HyOrder> hyOrderList=new ArrayList<>();

        if(openid== null||openid.equals("")){
            return  response.PARAMS_ISNULL();
        }

        HyLinkman hyLinkman=new HyLinkman();
        hyLinkman.setWxOpenid(openid);
        hyLinkman.setPageSize(100);
        Response<List<HyLinkman>> listResponse=hyLinkmanClient.find(hyLinkman);//openid 绑定的联系人集合
        if(listResponse.getCode()==0){
            List<HyLinkman> hyLinkmanList=listResponse.getData();
            if(hyLinkmanList.size()>0){
                for(HyLinkman hy:hyLinkmanList){
                    HyOrder hyOrder=new HyOrder();
                    hyOrder.setLinkmanId(hy.getId());
                    Response<List<HyOrder>> listResponse1= hyOrderClient.find(hyOrder);//openid下的所有订单集合
                    hyOrderList.addAll(listResponse1.getData());
                }
            }
        }

        if(hyOrderList.size()>0){
            response.setData(hyOrderList);
        }
        response.setCode(listResponse.getCode());
        response.setMessage(listResponse.getMessage());
        return response;
    }

    /**
     * 订单详情
     * @param id   订单主键
     * @return
     */
    @ResponseBody
    @RequestMapping("/queryDetail")
    public Response queryDetail(Integer id){
        Response response=Response.newResponse();
        if(id==null){
            return  response.PARAMS_ISNULL();
        }
        HyOrder hyOrder=new HyOrder();
        hyOrder.setId(id);
        Response<HyOrder> hyOrderResponse=hyOrderClient.findByPrimaryKey(hyOrder);
        return hyOrderResponse;
    }

    /**
     *
     * 事务 保存订单
     * @return
     */
    @RequestMapping("/save")
    @ResponseBody
    public Response save(@RequestBody String StrJSon){
       return hyOrderClient.saveOrder(StrJSon);
    }

    /**
     *
     * 保存订单  '保存订单明细
     * @param   StrJSon  json数据
     *                   联系人   出行人
     * @return
     */
//    @RequestMapping("/save")
//    @ResponseBody
//    public Response save(@RequestBody String StrJSon){
//        Response response = Response.newResponse();
//
//        //数据JSON字符串
//        JSONObject jsonObject = JSON.parseObject(StrJSon);
//
//        // 联系人
//        HyLinkman hyLinkman = JSON.parseObject(StrJSon, new TypeReference<HyLinkman>() {});
//
//        // 订单
//        Object jsonObjectOrder=jsonObject.get("hyOrder");
//        HyOrder hyOrder = JSON.parseObject(jsonObjectOrder.toString(), new TypeReference<HyOrder>() {});
//
//        // 出行人对象集合
//        Object jsonObjectUser=jsonObject.get("hyUserList");
//        List<HyUser> hyUserList = JSON.parseObject(jsonObjectUser.toString(),new TypeReference<List<HyUser>>(){});
//
//        if(StrJSon==null){
//            return  response.PARAMS_ISNULL();
//        }
//        String message= "";
//
//        Response respPay = new Response();
//        Response respLink = new Response();
//        Response respOrder = new Response();
////        try {
//            //保存联系人
//            respLink=hyLinkmanClient.save(hyLinkman);
//
//            //保存订单
//            if(respLink.getCode()==0){
//                hyOrder.setLinkmanId((Integer) respLink.getData()); //联系人id 外键
//                hyOrder.setStatus("0"); //订单状态  进行中
//                respOrder=hyOrderClient.save(hyOrder);
//            }
//            //给支付记录添加订单号
//            if(respOrder.getCode()==0){
//                HyPay hyPay=new HyPay();
//                hyPay.setOrderId((Integer)respOrder.getData());
//                hyPay.setStatus("0"); //未支付
//                hyPay.setPayment("0");//微信支付
//                hyPay.setPaymentType("0");//流入
//                hyPay.setAmount(hyOrder.getAdvance());
//                respPay=hyPayClient.save(hyPay);
//            }
//
//            //保存出行人,保存订单明细
//            if(respOrder.getCode()==0){
//                for (HyUser hyUser:hyUserList) {
//                    response=hyUserClient.save(hyUser);
//
//                    HyOrderDetail hyOrderDetail=new HyOrderDetail();
//                    hyOrderDetail.setOrderId((Integer)respOrder.getData()); //订单id 外键
//                    hyOrderDetail.setUserId((Integer) response.getData());//出行人id 外键
//                    hyOrderDetail.setStatus("2");//订单明细状态   2进行中
//                    response=hyOrderDetailClient.save(hyOrderDetail);
//                }
//            }
////        }catch (Exception ex){
////            message = ex.getMessage();
////            return response.SAVE_FAIL(message);
////        }
//        if(response.getCode()==0){
//            Map<String,Object> map=new HashMap<>();
//            map.put("payId",respPay.getData());
//            response.setData(map);
//            return response;
//        }
//        return response.SAVE_FAIL(message);
//    }

    /**
     *  修改订单状态
     * @param id
     * @return
     */
    @RequestMapping("/payUpdateStatus")
    @ResponseBody
    public  Response payUpdateStatus(String id){
        Response response=Response.newResponse();
        if(id==null||id.equals("")){
           return response.PARAMS_ISNULL();
        }
        HyPay hyPay=new HyPay();
        hyPay.setId(Integer.parseInt(id));
        hyPay.setStatus("1");//支付成功
        response=hyPayClient.save(hyPay);
        if(response.getCode()==0){
            return  response;
        }
        return response.SAVE_FAIL(response.getMessage());
    }

    /**
     *  支付余款
     * @param hyPay(orderid ,amount)
     * @return
     */
    @RequestMapping("/payAmount")
    @ResponseBody
    public  Response payAmount(HyPay hyPay){
        Response response=Response.newResponse();
        if(hyPay.getOrderId()==null||hyPay.getOrderId().equals("")){
            return response.PARAMS_ISNULL();
        }
        hyPay.setStatus("0"); //未支付
        hyPay.setPayment("0");//微信支付
        hyPay.setPaymentType("0");//流入
        response=hyPayClient.save(hyPay);
        if(response.getCode()==0){
            return response;
        }
        return  response.SAVE_FAIL(response.getMessage());
    }

}
