package com.wuxue.view.controllers.system;

import com.wuxue.model.TkPayRecords;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.system.TkPayRecordsClient;
import com.wuxue.view.controllers.BaseController;
import com.wuxue.view.interfaces.*;
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
import java.util.*;

/**
 * Created by Rogue on 2018/01/03.
 */
@Controller
@RequestMapping(value = "/system/tkPayRecords")
public class TkPayRecordsController extends BaseController implements ISaveController<TkPayRecords,String>,IQueryController<TkPayRecords,String>,
        IDeleteController<TkPayRecords,String>,IQueryByPagingController<TkPayRecords,Map<String,Object>>,ICreateController<TkPayRecords,Map<String,Object>>,IEditController<TkPayRecords,Response> {


    @Autowired
    private TkPayRecordsClient tkPayRecordsClient;

    @Override
    @ResponseBody
    public String delete(HttpServletRequest request, Model model, TkPayRecords tkPayRecords) {

        return tkPayRecordsClient.delete(tkPayRecords.getPkPayRecords());
    }
    @Override
    public String query(HttpServletRequest request, Model model, TkPayRecords tkPayRecords) {

        return "systemhtml/tkPayRecordsList";
    }



    @Override
    @ResponseBody
    public Map<String, Object> queryByPaging(HttpServletRequest request, HttpServletResponse response, TkPayRecords tkPayRecords, Integer sEcho, Integer start, Integer length) {
        tkPayRecords.setPageNo((start/length)+1);
        tkPayRecords.setPageSize(length);



        Response<List<TkPayRecords>> listResponse = tkPayRecordsClient.find(tkPayRecords);
        List<TkPayRecords> data = listResponse.getData();
        if(data==null) {
            data = new ArrayList<>();
        }
        return convertToPaging(listResponse.getTotal(),listResponse.getTotal(),data);
    }

    @Override
    @ResponseBody
    public String save(HttpServletRequest request, Model model, TkPayRecords tkPayRecords) throws ParseException {

        return tkPayRecordsClient.save(tkPayRecords);
    }

    @Override
    @ResponseBody
    public Map<String,Object> create(HttpServletRequest request, Model model, TkPayRecords tkPayRecords) {

        Map<String,Object> map = new HashMap<>();

        map.put("tkPayRecords",tkPayRecords);

        return map;
    }

    @Override
    @ResponseBody
    public Response edit(HttpServletRequest request, Model model, TkPayRecords tkPayRecords) {

        Map<String,Object> map = new HashMap<>();

        Response<TkPayRecords> byPrimaryKey = tkPayRecordsClient.findByPrimaryKey(tkPayRecords);
        map.put("tkPayRecords",byPrimaryKey.getData());

        return byPrimaryKey;
    }
}
