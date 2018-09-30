package com.wuxue.view.controllers.system;

import com.wuxue.model.TkSetMeal;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.system.TkSetMealClient;
import com.wuxue.view.controllers.BaseController;
import com.wuxue.view.interfaces.*;
import com.wuxue.view.utils.GuidUtils;
import com.wuxue.view.utils.SessionCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Rogue on 2018/01/03.
 */
@Controller
@RequestMapping(value = "/system/tkSetMeal")
public class TkSetMealController extends BaseController implements IQueryController<TkSetMeal,String>,ISaveController<TkSetMeal,String>,
        IDeleteController<TkSetMeal,String>,IQueryByPagingController<TkSetMeal,Map<String,Object>>,ICreateController<TkSetMeal,Map<String,Object>>,IEditController<TkSetMeal,Response> {


    @Autowired
    private TkSetMealClient tkSetMealClient;

    @Override
    @ResponseBody
    public String delete(HttpServletRequest request, Model model, TkSetMeal tkSetMeal) {
        String pkSetMeal = tkSetMeal.getPkSetMeal();

        return tkSetMealClient.delete(pkSetMeal);
    }

    @Override
    public String query(HttpServletRequest request, Model model, TkSetMeal tkSetMeal) {

        return "systemhtml/tkSetMealList";
    }

    @Override
    @ResponseBody
    public Map<String, Object> queryByPaging(HttpServletRequest request, HttpServletResponse response, TkSetMeal tkSetMeal, Integer sEcho, Integer start, Integer length) {
        tkSetMeal.setPageNo((start/length)+1);
        tkSetMeal.setPageSize(length);



        Response<List<TkSetMeal>> listResponse = tkSetMealClient.find(tkSetMeal);
        List<TkSetMeal> data = listResponse.getData();
        if(data==null) {
            data = new ArrayList<>();
        }
        return convertToPaging(listResponse.getTotal(),listResponse.getTotal(),data);
    }

    @Override
    @ResponseBody
    public String save(HttpServletRequest request, Model model, TkSetMeal tkSetMeal) throws ParseException {

        if (tkSetMeal.getPkSetMeal()==null || "".equals(tkSetMeal.getPkSetMeal())){
            tkSetMeal.setPkSetMeal(GuidUtils.getGuid());
            tkSetMeal.setCreator(SessionCache.getUserCode());

        }
        tkSetMeal.setModifier(SessionCache.getUserCode());

        return tkSetMealClient.save(tkSetMeal);
    }

    @Override
    @ResponseBody
    public Map<String, Object> create(HttpServletRequest request, Model model, TkSetMeal tkSetMeal) {

        Map<String,Object> map = new HashMap<>();
        map.put("tkSetMeal",tkSetMeal);
        return map;
    }
    @RequestMapping(value = "/createHtml",method = RequestMethod.GET)
    public String createHtml(HttpServletRequest request) {

        return "systemhtml/addTkSetMeal";
    }

    @Override
    @ResponseBody
    public Response edit(HttpServletRequest request, Model model, TkSetMeal tkSetMeal) {
        Response<TkSetMeal> byPrimaryKey = tkSetMealClient.findByPrimaryKey(tkSetMeal);


        return byPrimaryKey;
    }
    @RequestMapping(value = "/editHtml",method = RequestMethod.GET)
    public String editHtml(HttpServletRequest request) {


        return "systemhtml/editSetMeal";
    }
}
