package com.wuxue.view.controllers.system;

import com.wuxue.model.*;
import com.wuxue.utils.common.DataUtils;
import com.wuxue.utils.contract.Light;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.classInfo.ClassInfoClient;
import com.wuxue.view.client.system.DivisionGradeClient;
import com.wuxue.view.client.system.SysUserClient;
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
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 学部年级
 */
@Controller
@RequestMapping(value = "/system/divisionGrade")
public class DivisionGradeController extends BaseController
        implements IQueryController<DivisionGrade,String>,ISaveController<DivisionGrade,String>,IQueryByPagingController<DivisionGrade,Map<String,Object>>,
        ICreateController<DivisionGrade,String>,IEditController<DivisionGrade,String>,IDeleteController<DivisionGrade,String> {


    @InitBinder("divisionGrade")
    public void initBinder(WebDataBinder binder){
        binder.setFieldDefaultPrefix("room.");
    }

    @Autowired
    private DivisionGradeClient divisionGradeClient;
    @Autowired
    private SysAutoCodeUtils sysAutoCodeUtils;
    @Autowired
    private SysDictUtils sysDictUtils;

    /**
     * 宿舍列表页面
     * @param model
     * @return
     * @throws java.text.ParseException
     */
    @Override
    public String query(HttpServletRequest request, Model model, DivisionGrade divisionGrade)  {
//        Response<List<DivisionGrade>> listResponse = divisionGradeClient.find(divisionGrade);
//
//        model.addAttribute("dormlist",listResponse.getData() );
//        String jsonString = JSON.toJSONString(listResponse.getData());
//        model.addAttribute("dorm",jsonString);

        return "/system/divisionGradeList";
    }

    @Override
    @ResponseBody
    public Map<String, Object> queryByPaging(HttpServletRequest request, HttpServletResponse response, DivisionGrade divisionGrade, Integer sEcho, Integer start, Integer length) {
        divisionGrade.setPageNo((start/length)+1);
        divisionGrade.setPageSize(length);

        Response<List<DivisionGrade>> listResponse = divisionGradeClient.find(divisionGrade);
        List<DivisionGrade> data = listResponse.getData();
        if(data==null) {
            data = new ArrayList<>();
        }
        return convertToPaging(listResponse.getTotal(),listResponse.getTotal(),data);
    }



    @Override
    public String create(HttpServletRequest request, Model model, DivisionGrade divisionGrade) {

        sysDictUtils.setModeAttribute(model,"divisionList","division");


        return "/system/divisionGrade";
    }

    @Override
    @ResponseBody
    public String delete(HttpServletRequest request, Model model, DivisionGrade divisionGrade) {
        return divisionGradeClient.delete(divisionGrade.getPkDivisionGrade());
    }

    @Override
    public String edit(HttpServletRequest request, Model model, DivisionGrade divisionGrade) {
        Response<DivisionGrade> byPrimaryKey = divisionGradeClient.findByPrimaryKey(divisionGrade);
        model.addAttribute("division",byPrimaryKey.getData());
        sysDictUtils.setModeAttribute(model,"divisionList","division");

        return "/system/divisionGrade";
    }




    @Override
    @ResponseBody
    public String save(HttpServletRequest request, Model model, DivisionGrade divisionGrade) {
        if(divisionGrade.getPkDivisionGrade() == null || divisionGrade.getPkDivisionGrade().equals("")){
            divisionGrade.setCreator(SessionCache.getUserCode());
        }
        divisionGrade.setModifier(SessionCache.getUserCode());

        return divisionGradeClient.save(divisionGrade);
    }





}
