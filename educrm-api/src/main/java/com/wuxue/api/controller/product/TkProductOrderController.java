package com.wuxue.api.controller.product;

import com.alibaba.fastjson.JSONObject;
import com.wuxue.api.interfaces.IDeleteController;
import com.wuxue.api.interfaces.IFindController;
import com.wuxue.api.interfaces.ISaveController;
import com.wuxue.api.service.TkProductOrderService;
import com.wuxue.model.TkLearnRecords;
import com.wuxue.model.TkProductOrder;
import com.wuxue.utils.contract.Head;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * description:  产品（课程）预约表
 * @auther: wh
 * @date: 2018/6/13 14:56
 */
@RestController
@RequestMapping(value = "api/product/tkproductorder")
public class TkProductOrderController implements IFindController<TkProductOrder>,
        ISaveController<TkProductOrder>,IDeleteController<TkProductOrder> {


    @Autowired
    TkProductOrderService tkProductOrderService;

   //{"data":{"itemsCount":0,"map":{},"pageNo":1,"pageSize":10,"pkLearnRecords":"1"},"head":{"appKey":"wuxueapp","salt":0},"itemsCount":0,"pageCount":1,"pageNo":1,"pageSize":20,"start":0}

    @Override
    public Response Find(@RequestBody Request<TkProductOrder> request) { return tkProductOrderService.find(request); }

    @Override
    public Response Save(@RequestBody Request<TkProductOrder> request) {
        return tkProductOrderService.save(request);
    }

    @Override
    public Response Delete(@RequestBody Request<TkProductOrder> request) { return tkProductOrderService.delete(request); }

    public static void main(String[] args) {
        Request request1=new Request();

        TkLearnRecords tkLearnRecords=new TkLearnRecords();
        tkLearnRecords.setPkLearnRecords("1");

        Head head=new Head();
        head.setAppKey("wuxueapp");

        request1.setData(tkLearnRecords);
        request1.setHead(head);

        System.out.println(JSONObject.toJSONString(request1));
    }

}