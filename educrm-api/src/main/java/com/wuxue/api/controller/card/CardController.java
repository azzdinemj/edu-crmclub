package com.wuxue.api.controller.card;

import com.wuxue.api.interfaces.IDeleteController;
import com.wuxue.api.interfaces.IFindController;
import com.wuxue.api.interfaces.ISaveController;
import com.wuxue.api.service.CardService;
import com.wuxue.api.service.ClassTimeService;
import com.wuxue.model.ClassTime;
import com.wuxue.model.Recharge;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 一卡通所用接口
 * Created by Rogue on 2018/08/29.
 */
@RestController
@RequestMapping(value = "api/card/card")
public class CardController implements ISaveController<Recharge>{
    @Autowired
    private CardService cardService;

    @RequestMapping(value = "/selectByRechargeList",method = RequestMethod.POST)
    public Response selectByRechargeList(@RequestBody Recharge request) {
        return cardService.selectByRechargeList(request);
    }

    @Override
    public Response Save(@RequestBody Request<Recharge> request) {
        return cardService.save(request);
    }
}
