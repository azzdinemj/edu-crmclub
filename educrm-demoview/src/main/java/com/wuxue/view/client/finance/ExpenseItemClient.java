package com.wuxue.view.client.finance;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.wuxue.model.ExpenseItem;
import com.wuxue.model.ExpenseItem;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.FinanceBaseClient;
import com.wuxue.view.enums.ActionEnum;
import com.wuxue.view.interfaces.IDeleteClient;
import com.wuxue.view.interfaces.IFindByPrimaryKeyClient;
import com.wuxue.view.interfaces.IFindClient;
import com.wuxue.view.interfaces.ISaveClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseItemClient extends FinanceBaseClient
        implements IFindClient<ExpenseItem,Response<List<ExpenseItem>>>,ISaveClient<ExpenseItem,String>,
        IDeleteClient<String,Object>,IFindByPrimaryKeyClient<ExpenseItem,Response<ExpenseItem>> {




    /**
     * 删除
     * @param pkId 主键
     * @return
     */
    @Override
    public String delete(String pkId) {
        String responseXml = POST(getSendUrl(ActionEnum.DELETE),pkId);
//        Response<Object> response = JSON.parseObject(responseXml,new TypeReference<Response<Object>>(){});
        return responseXml;
    }

    /**
     * 查询
     * @param expenseItem
     * @return
     */
    @Override
    public Response<List<ExpenseItem>> find(ExpenseItem expenseItem) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),expenseItem);
        Response<List<ExpenseItem>> response = JSON.parseObject(responseXml,new TypeReference<Response<List<ExpenseItem>>>(){});
        return response;
    }

    /**
     *保存
     * @param expenseItem
     * @return
     */
    @Override
    public String save(ExpenseItem expenseItem){
        String responseXml = POST(getSendUrl(ActionEnum.SAVE),expenseItem);
        //Response<Object> response = JSON.parseObject(responseXml, new TypeReference<Response<Object>>(){});
        return responseXml;
    }

    /**
     * 所属页面名称
     * @return
     */
    @Override
    protected String getPageName() {
        return "/expenseItem";
    }


    @Override
    public Response<ExpenseItem> findByPrimaryKey(ExpenseItem expenseItem) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),expenseItem);
        Response<ExpenseItem> response = JSON.parseObject(responseXml,new TypeReference<Response<ExpenseItem>>(){});
        return response;
    }
}
