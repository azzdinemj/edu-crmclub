package com.wuxue.api.controller.finance;

import com.wuxue.api.interfaces.IDeleteController;
import com.wuxue.api.interfaces.IFindController;
import com.wuxue.api.interfaces.ISaveController;
import com.wuxue.model.ExpenseItem;
import com.wuxue.api.service.ExpenseItemService;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Rogue on 2018/01/02.
 */
@RestController
@RequestMapping(value = "api/finance/expenseItem")
public class ExpenseItemController implements IFindController<ExpenseItem>,
        ISaveController<ExpenseItem>,IDeleteController<String> {
    @Autowired
    private ExpenseItemService expenseItemService;

    @Override
    public Response Find(@RequestBody Request<ExpenseItem> expenseItem) {
        return expenseItemService.find(expenseItem);
    }

    @Override
    public Response Save(@RequestBody Request<ExpenseItem> expenseItem) {
        return expenseItemService.save(expenseItem);
    }
    @Override

    public Response Delete(@RequestBody Request<String> expenseItem) {
        return expenseItemService.delete(expenseItem);

    }
}
