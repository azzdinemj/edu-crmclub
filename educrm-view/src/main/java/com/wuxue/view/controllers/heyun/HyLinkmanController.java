package com.wuxue.view.controllers.heyun;

import com.alibaba.fastjson.JSON;
import com.wuxue.model.*;
import com.wuxue.utils.common.DataUtils;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.heyun.*;
import com.wuxue.view.controllers.BaseController;
import com.wuxue.view.interfaces.IQueryByPagingController;
import com.wuxue.view.interfaces.IQueryController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * 联系人
 */
@Controller
@RequestMapping("/heyun/hyLinkman")
public class HyLinkmanController extends BaseController implements IQueryByPagingController<HyLinkman,Map<String,Object>>,IQueryController<HyLinkman,String> {
    @Autowired
    private HyOrderClient hyOrderClient;

    @Autowired
    private HyLinkmanClient hyLinkmanClient;

    @Autowired
    private HyOrderDetailClient hyOrderDetailClient;

    @Autowired
    private HyUserClient hyUserClient;

    @Override
    @ResponseBody
    public Map<String, Object> queryByPaging(HttpServletRequest request, HttpServletResponse response, HyLinkman hyLinkman, Integer sEcho, Integer start, Integer length) {
        hyLinkman.setPageNo((start/length)+1);
        hyLinkman.setPageSize(length);

        Response<List<HyLinkman>> resp=hyLinkmanClient.find(hyLinkman);
        List<HyLinkman> data = resp.getData();
        if(data==null) {
            data = new ArrayList<>();
        }
        return convertToPaging(resp.getTotal(),resp.getTotal(),data);
    }


    @Override
    public String query(HttpServletRequest request, Model model, HyLinkman hyLinkman){

        HyOrder hyOrder=new HyOrder();
        Response responseAll=hyOrderClient.countby(hyOrder);
        model.addAttribute("hyOrderNum",responseAll.getData()); //订单总数量

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date=new Date();
        String dateStr = sdf.format(date);
        hyOrder.setCreateDate(dateStr);//模拟日期字段

        Response responseToday=hyOrderClient.countby(hyOrder);
        model.addAttribute("hyOrderNumToday",responseToday.getData()); //当日订单数量

        HyUser hyUser=new HyUser();
        Response responseHyUserNum=hyUserClient.countby(hyUser);
        model.addAttribute("hyUserNum",responseHyUserNum.getData()); //出行人总数量


        return "/heyun/hyLinkmanList";
    }

    /**
     * 根据联系人查找出订单，订单明细，出行人
     * @param request
     * @param hyLinkman
     * @return
     */
    @RequestMapping("/hyLinkmanEdit")
    public String edit(HttpServletRequest request,HyLinkman hyLinkman,Model model){
         Response<HyLinkman> response=hyLinkmanClient.findByPrimaryKey(hyLinkman);

         HyLinkman hyLinkman1=response.getData();
         Map<String,Object> map= hyLinkman1.getMap();

        //订单
        HyOrder hyOrder = DataUtils.objectToObject(map.get("HyOrder"), HyOrder.class);
        model.addAttribute("HyOrder",hyOrder);

        Map<String,Object> map2=hyOrder.getMap();

        //订单明细列表
        List<HyOrderDetail> hyOrderDetailList = DataUtils.objectToList(map2.get("HyOrderDetailList"), HyOrderDetail.class);
        model.addAttribute("HyOrderDetailList",hyOrderDetailList);

        //联系人
        model.addAttribute("HyLinkman",response.getData());

         return "/heyun/hyLinkmanEdit";
    }


    /**
     * 修改出行人状态(实际为订单明细状态，订单明细关联到出行人)
     * @param request
     * @param hyOrderDetail
     * @return
     */
    @RequestMapping("/updateStatusOrderDetail")
    @ResponseBody
    public String updateStatusOrderDetail(HttpServletRequest request,HyOrderDetail hyOrderDetail){

        String[] strs=request.getParameterValues("HyUser[]");
        String s="";
        Map<String,Object> map=new HashMap<>();
        map.put("code",0);
        for (String str:strs) {
            String [] strs2=str.split(",");
            if(strs2.length==2){
                hyOrderDetail.setStatus(strs2[0]);
                hyOrderDetail.setId(Integer.parseInt(strs2[1]));
                try {
                    s=hyOrderDetailClient.save(hyOrderDetail);
                }catch (Exception e){
                    map.put("code",1);
                }
            }
        }

        if((Integer)map.get("code")==1){
         return JSON.toJSONString(map);
        }
        return s;
    }


    /**
     * 修改订单状态 (退款，完成)，每变更一次增加一条订单变更记录。
     * @param request
     * @param hyOrder
     * @return
     */
    @RequestMapping("/updateStatusOrder")
    public String updateStatusOrder(HttpServletRequest request,HyOrder hyOrder){
        Response response=Response.newResponse();
        response.SAVE_FAIL("出错");
        if(hyOrder.getId()!=null){
            return  hyOrderClient.updateStatus(hyOrder);
        }
        return  JSON.toJSONString(response);
    }



}
