package com.wuxue.view.controllers.system;

import com.alibaba.fastjson.JSON;
import com.wuxue.base.KeyValue;
import com.wuxue.model.TbAllarea;
import com.wuxue.model.SysAutocode;
import com.wuxue.model.TbAllarea;
import com.wuxue.utils.common.DataUtils;
import com.wuxue.utils.contract.LinkEntity;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.system.TbAllareaClient;
import com.wuxue.view.client.system.SysAutcodeClient;
import com.wuxue.view.contract.TreeGridColumn;
import com.wuxue.view.contract.TreeGridModel;
import com.wuxue.view.controllers.BaseController;
import com.wuxue.view.interfaces.*;
import com.wuxue.view.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 校区管理
 */
@Controller
@RequestMapping(value = "/system/tbAllarea")
public class TbAllareaController extends BaseController {

    @InitBinder("tbAllarea")
    public void initBinder(WebDataBinder binder){
        binder.setFieldDefaultPrefix("domin.");
    }

    @Autowired
    private TbAllareaClient tbAllareaClient;


    /**
     * 查询省市区
     *
     * @param model
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/query",method = RequestMethod.GET)
    public List<TbAllarea> query(HttpServletRequest request, Model model, TbAllarea tbAllarea)  {

//        String provinceid = request.getParameter("provinceid");
//        tbAllarea.setParent(Integer.parseInt(provinceid));

        Response<List<TbAllarea>> listResponse = tbAllareaClient.find(tbAllarea);
        List<TbAllarea> data = listResponse.getData();
        model.addAttribute("list",data);


        return data;
    }


}
