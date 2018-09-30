package com.wuxue.view.controllers.setMeal;

import com.wuxue.model.*;
import com.wuxue.model.junhwa.Material;
import com.wuxue.model.junhwa.MealMaterial;
import com.wuxue.utils.contract.Light;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.junhua.JhAnswerClient;
import com.wuxue.view.client.junhua.JhQuestionClient;
import com.wuxue.view.client.setMeal.MaterialClient;
import com.wuxue.view.client.setMeal.MealMaterialClient;
import com.wuxue.view.client.setMeal.SetMealClient;
import com.wuxue.view.controllers.BaseController;
import com.wuxue.view.interfaces.*;
import com.wuxue.view.utils.JobPostEnum;
import com.wuxue.view.utils.SetMealList;
import com.wuxue.view.utils.SysDicEmnuUtils;
import com.wuxue.view.utils.SysDictUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;

/**
 * 个性化课表
 */
@Controller
@RequestMapping(value = "/setMeal/setMeal")
public class setMealController extends BaseController implements IQueryController<SetMeal, String>,IDeleteController<SetMeal, String>,
        IQueryByPagingController<SetMeal, Map<String, Object>> ,ICreateController<SetMeal, String>,ISaveController<SetMeal, String>, IEditController<SetMeal, String> {


    @Autowired
    private SetMealClient setMealClient;
    @Autowired
    private SysDictUtils sysDictUtils;
    @Autowired
    private MaterialClient materialClient;
    @Autowired
    private MealMaterialClient mealMaterialClient;

    /**
     * 列表页面
     *
     * @param model
     * @return
     * @throws ParseException
     */
    @Override
    public String query(HttpServletRequest request, Model model, SetMeal setMeal) {

        sysDictUtils.setModeAttribute(model,"englishCode",SysDicEmnuUtils.ENGLISH_CODE);
        return "/meal/mealList";
    }

    @Override
    @ResponseBody
    public Map<String, Object> queryByPaging(HttpServletRequest request, HttpServletResponse response, SetMeal setMeal, Integer sEcho, Integer start, Integer length) {

        setMeal.setPageNo((start / length) + 1);
        setMeal.setPageSize(length);
        Response<List<SetMeal>> listResponse = setMealClient.find(setMeal);
        List<SetMeal> data = listResponse.getData();
        if (data == null) {
            data = new ArrayList<>();
        }
        return convertToPaging(listResponse.getTotal(), listResponse.getTotal(), data);


    }

    @Override
    @ResponseBody
    public String save(HttpServletRequest request, Model model,SetMeal setMeal) throws ParseException {
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "saveAll", method = RequestMethod.POST)
    public String saveall(HttpServletRequest request, Model model,SetMeal setMeal,SetMealList details) throws ParseException {
        setMeal.put(Light.MEAL_MATERIAL,details.getDetails());
        return setMealClient.save(setMeal);
    }
    /**
     * 添加页面
     *
     * @return
     */
    @Override
    public String create(HttpServletRequest request, Model model, SetMeal setMeal) {
        Response<List<Material>> listResponse = materialClient.find(new Material());
        model.addAttribute("material",listResponse.getData());
        sysDictUtils.setModeAttribute(model,"englishCode",SysDicEmnuUtils.ENGLISH_CODE);
        return "/meal/meal";
    }

    /**
     * 修改页面
     *
     * @param setMeal
     * @param model
     * @return
     */
    @Override
    public String edit(HttpServletRequest request, Model model, SetMeal setMeal) {
        Response<SetMeal> byPrimaryKey = setMealClient.findByPrimaryKey(setMeal);
        Response<List<Material>> listResponse = materialClient.find(new Material());
        MealMaterial mealMaterial = new MealMaterial();
        mealMaterial.setSetMealId(setMeal.getPkSetMeal());
        Response<List<MealMaterial>> response = mealMaterialClient.find(mealMaterial);
        model.addAttribute("setMeal",byPrimaryKey.getData());
        model.addAttribute("material",listResponse.getData());
        model.addAttribute("mealMaterial",response.getData());
        sysDictUtils.setModeAttribute(model,"englishCode",SysDicEmnuUtils.ENGLISH_CODE);
        return "/meal/meal";
    }

    @RequestMapping(value = "/getMaterial",method = RequestMethod.GET)
    public String getEmployees(HttpServletRequest request, Model model, SetMeal setMeal) {

        String capid = request.getParameter("id1");
        String pkid = request.getParameter("id2");
        model.addAttribute("capid",capid);
        model.addAttribute("pkid",pkid);

        return "/model/materiallistModel";
    }

    @ResponseBody
    @RequestMapping(value = "/queryDefault",method = RequestMethod.POST)
    public String queryDefault(HttpServletRequest request, Model model, SetMeal setMeal) {

        return setMealClient.queryDefault(setMeal);
    }

    @ResponseBody
    @RequestMapping(value = "/queryCode",method = RequestMethod.POST)
    public String queryCode(HttpServletRequest request, Model model, SetMeal setMeal) {

        return setMealClient.queryCode(setMeal);
    }


    @Override
    @ResponseBody
    public String delete(HttpServletRequest request, Model model, SetMeal setMeal) {
        return setMealClient.delete(setMeal.getPkSetMeal());
    }
}
