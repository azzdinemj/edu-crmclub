package com.wuxue.view.controllers.financial;

import com.wuxue.model.ExpenseItem;
import com.wuxue.utils.contract.LinkEntity;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.finance.ExpenseItemClient;
import com.wuxue.view.controllers.BaseController;
import com.wuxue.view.interfaces.*;
import com.wuxue.view.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 费用项目管理
 */
@Controller
@RequestMapping(value = "/finance/school")
public class schoolController extends BaseController
        implements IQueryController<ExpenseItem,String>,ISaveController<ExpenseItem,String>,IQueryByPagingController<ExpenseItem,Map<String,Object>>,
        ICreateController<ExpenseItem,String>,IEditController<ExpenseItem,String>,IDeleteController<ExpenseItem,String> {


    @InitBinder("expenseItem")
    public void initBinder(WebDataBinder binder){
        binder.setFieldDefaultPrefix("exp.");
    }

    @Autowired
    private ExpenseItemClient expenseItemClient;
    @Autowired
    private DomainUtils domainUtils;
    @Autowired
    private SysAutoCodeUtils sysAutoCodeUtils;

    /**
     * 费用项目列表页面
     * @param model
     * @return
     * @throws java.text.ParseException
     */
    @Override
    public String query(HttpServletRequest request, Model model, ExpenseItem expenseItem)  {

        return "/financial/schoollist";
    }



    @Override
    @ResponseBody
    public Map<String, Object> queryByPaging(HttpServletRequest request, HttpServletResponse response, ExpenseItem expenseItem, Integer sEcho, Integer start, Integer length) {
        expenseItem.setPageNo((start/length)+1);
        expenseItem.setPageSize(length);

        Response<List<ExpenseItem>> listResponse = expenseItemClient.find(expenseItem);
        List<ExpenseItem> data = listResponse.getData();
        if(data==null) {
            data = new ArrayList<>();
        }
        return convertToPaging(listResponse.getTotal(),listResponse.getTotal(),data);
    }

    public void initPageAttribute(Model model,String pkDomain){
        domainUtils.setModeAttribute(model,"domain",pkDomain);
    }

    @Override
    public String create(HttpServletRequest request, Model model, ExpenseItem expenseItem) {

        initPageAttribute(model,SessionCache.getPkdomain());
        String sysAutocode = sysAutoCodeUtils.getSysAutocode(AutoCodeEnum.EXPENSE_ITEM);
        expenseItem.setCode(sysAutocode);
        expenseItem.put(LinkEntity.CREATOR_ENTITY,EmployeeUtils.getSysUser());
        expenseItem.put(LinkEntity.MODIFIER_ENTITY,EmployeeUtils.getSysUser());
        expenseItem.setCreationDate(new Date());
        expenseItem.setLasteditDate(new Date());
        model.addAttribute("expenseItem",expenseItem);
        return "/financial/expenseItem";
    }

    @Override
    @ResponseBody
    public String delete(HttpServletRequest request, Model model, ExpenseItem expenseItem) {

        return expenseItemClient.delete(expenseItem.getPkExpenseItem());
}
    @Override
    public String edit(HttpServletRequest request, Model model, ExpenseItem expenseItem) {
//        Response<ExpenseItem> byPrimaryKey = expenseItemClient.findByPrimaryKey(expenseItem);
//        model.addAttribute("expenseItem",byPrimaryKey.getData());
//
//        initPageAttribute(model,byPrimaryKey.getData().getPkDomain());

        return "/financial/expenseItem";
    }




    @Override
    @ResponseBody
    public String save(HttpServletRequest request, Model model, ExpenseItem expenseItem) {
        if(expenseItem.getPkExpenseItem() == null || "".equals(expenseItem.getPkExpenseItem())){
            expenseItem.setPkExpenseItem(GuidUtils.getGuid());
            expenseItem.setCreator(SessionCache.getUserCode());
            expenseItem.setModifier(SessionCache.getUserCode());
        }else {
            expenseItem.setModifier(SessionCache.getUserCode());
        }
        return expenseItemClient.save(expenseItem);
    }


}
