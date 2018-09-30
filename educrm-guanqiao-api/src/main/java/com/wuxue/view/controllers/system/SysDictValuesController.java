package com.wuxue.view.controllers.system;

import com.wuxue.model.SysDict;
import com.wuxue.model.SysDictValues;
import com.wuxue.utils.contract.LinkEntity;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.system.SysDicClient;
import com.wuxue.view.client.system.SysDictValuesClient;
import com.wuxue.view.controllers.BaseController;
import com.wuxue.view.enums.SysDictEnum;
import com.wuxue.view.interfaces.*;
import com.wuxue.view.utils.EmployeeUtils;
import com.wuxue.view.utils.GuidUtils;
import com.wuxue.view.utils.SessionCache;
import com.wuxue.view.utils.SysDictUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * 数据字典枚举值
 */
@Controller
@RequestMapping(value = "/system/sysDictValues")
public class SysDictValuesController extends BaseController
        implements IQueryController<SysDictValues,String>,ISaveController<SysDictValues,String>,
        ICreateController<SysDictValues,Response>,IEditController<SysDictValues,Response>,IDeleteController<SysDictValues,String> ,IQueryByPagingController<SysDictValues,Map<String,Object>>{


    @InitBinder("sysDictValues")
    public void initBinder(WebDataBinder binder){
        binder.setFieldDefaultPrefix("sds.");
    }

    @Autowired
    private SysDictValuesClient sysDictValuesClient;
    @Autowired
    private SysDicClient sysDicClient;
    @Autowired
    private SysDictUtils sysDictUtils;

    /**
     * 列表页面
     * @param model
     * @return
     * @throws java.text.ParseException
     */
    @Override
    public String query(HttpServletRequest request, Model model, SysDictValues sysDictValues)  {

        return "systemhtml/SysDictValuesList";
    }


    @ResponseBody
    @RequestMapping("/qyerysys")
    public Response qyerysys(){
        SysDict sysDict = new SysDict();
        return sysDicClient.find(sysDict);
    }


    @Override
    @ResponseBody
    public Response create(HttpServletRequest request, Model model, SysDictValues sysDictValues) {

        SysDict sysDict = new SysDict();
        sysDict.setPkSysDict(sysDictValues.getPkSysDict());
        Response<SysDict> sysD = sysDicClient.findByPrimaryKey(sysDict);

        return sysD;
    }

    @Override
    @ResponseBody
    public String delete(HttpServletRequest request, Model model, SysDictValues sysDictValues) {
        return sysDictValuesClient.delete(sysDictValues.getPkSysDictValues());
    }

    @Override
    @ResponseBody
    public Response edit(HttpServletRequest request, Model model, SysDictValues sysDictValues) {

        Response<SysDictValues> byPrimaryKey = sysDictValuesClient.findByPrimaryKey(sysDictValues);

        return byPrimaryKey;
    }

    @RequestMapping(value = "/editHtml")
    public String editHtml(HttpServletRequest request, String pkSysDictValues) {

        return "systemhtml/SysDicValues";
    }
    @RequestMapping(value = "/createHtml")
    public String createHtml(HttpServletRequest request, String pkSysDictValues) {

        return "systemhtml/addSysDicValues";
    }




    @Override
    @ResponseBody
    public String save(HttpServletRequest request, Model model, SysDictValues sysDictValues) {
       // String pkDomain = request.getParameter("pkDomain");

        String userName = SessionCache.getUserName();
//        String bykey = request.getParameter("bykey");

        if(sysDictValues.getPkSysDictValues() == null || "".equals(sysDictValues.getPkSysDictValues())) {
            sysDictValues.setPkSysDictValues(GuidUtils.getGuid());
            sysDictValues.setCreator(SessionCache.getUserCode());
        }
        sysDictValues.setModifier(SessionCache.getUserCode());

        return sysDictValuesClient.save(sysDictValues);
    }


    @Override
    @ResponseBody
    public Map<String, Object> queryByPaging(HttpServletRequest request, HttpServletResponse response,SysDictValues sysDictValues, Integer sEcho, Integer start,Integer length) {
        sysDictValues.setPageNo((start/length)+1);
        sysDictValues.setPageSize(length);
        Response<List<SysDictValues>> listResponse = sysDictValuesClient.find(sysDictValues);
        List<SysDictValues> data = listResponse.getData();
        if(data==null) {
            data = new ArrayList<>();
        }
        SysDict sysDict = new SysDict();
        sysDict.setPkSysDict(sysDictValues.getPkSysDict());
        Response<SysDict> sysD = sysDicClient.findByPrimaryKey(sysDict);
        Map<String, Object> map = new HashMap<>();
        map.put("data",data);
        map.put("sysDicts",sysD);

        return map;
    }




}
