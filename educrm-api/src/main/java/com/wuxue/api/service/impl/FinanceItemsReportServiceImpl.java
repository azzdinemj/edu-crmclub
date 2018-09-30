package com.wuxue.api.service.impl;

import com.wuxue.api.mapper.ExpenseItemMapper;
import com.wuxue.api.mapper.FinanceItemsReportMapper;
import com.wuxue.api.service.FinanceItemsReportService;
import com.wuxue.api.service.UtilsService;
import com.wuxue.model.ExpenseItem;
import com.wuxue.model.FinanceItemsReport;
import com.wuxue.utils.contract.Light;
import com.wuxue.utils.contract.LinkEntity;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("financeItemsReportService")
public class FinanceItemsReportServiceImpl implements FinanceItemsReportService {


    @Autowired
    private FinanceItemsReportMapper financeItemsReportMapper;
    @Autowired
    private UtilsService utilsService;
    @Autowired
    private ExpenseItemMapper expenseItemMapper;

    @Override
    public Response find(Request<FinanceItemsReport> tParams) {
        Response response = Response.newResponse();
        FinanceItemsReport financeItemsReport = tParams.getData();

        List<FinanceItemsReport> select = financeItemsReportMapper.select(financeItemsReport);
        if(select.size()>0){
            for (FinanceItemsReport itemsReport : select) {
                ExpenseItem expenseItem= new ExpenseItem();
                if (itemsReport.getPkExpenseItem() != null && !"".equals(itemsReport.getPkExpenseItem())){
                    ExpenseItem expenseItem1 = expenseItemMapper.selectByPrimaryKey(itemsReport.getPkExpenseItem());
                    itemsReport.put(Light.EXP_ITEM,expenseItem1);
                }else {
                    itemsReport.put(Light.EXP_ITEM,expenseItem);
                }

            }
        }

        response.setData(select);


        return response;
    }
}
