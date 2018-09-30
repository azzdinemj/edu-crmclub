package com.wuxue.api.controller.finance;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.Receipt;
import com.wuxue.api.service.ReceiptService;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Rogue on 2018/01/02.
 */
@RestController
@RequestMapping(value = "api/finance/receipt")
public class ReceiptController implements IFindController<Receipt>,
        ISaveController<Receipt>,IDeleteController<String>,ISubmitController<Receipt>,IAuditController<Receipt> {
    @Autowired
    private ReceiptService receiptService;

    @Override
    public Response Find(@RequestBody Request<Receipt> receipt) {
        return receiptService.find(receipt);
    }


    @Override
    public Response Save(@RequestBody Request<Receipt> receipt) {
        return receiptService.save(receipt);
    }
    @Override

    public Response Delete(@RequestBody Request<String> receipt) {
        return receiptService.delete(receipt);

    }

    @Override
    public Response Audit(@RequestBody Request<Receipt> receipt) {
        return receiptService.audit(receipt);
    }

    @Override
    public Response Submit(@RequestBody Request<Receipt> receipt) {
        return receiptService.submit(receipt);
    }

    @RequestMapping(value = "/retreat",method = RequestMethod.POST)
    public Response Retreat(@RequestBody Request<Receipt> receipt){
        return receiptService.retreat(receipt);
    }

    @RequestMapping(value = "/findfrorefund",method = RequestMethod.POST)
    public Response findFroRefund(@RequestBody Request<Receipt> receipt) {
        return receiptService.findFroRefund(receipt);
    }
}
