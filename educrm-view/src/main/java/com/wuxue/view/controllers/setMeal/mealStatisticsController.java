package com.wuxue.view.controllers.setMeal;

import com.wuxue.model.SetMeal;
import com.wuxue.model.junhwa.Material;
import com.wuxue.model.junhwa.MealMaterial;
import com.wuxue.utils.contract.Light;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.setMeal.MaterialClient;
import com.wuxue.view.client.setMeal.MealMaterialClient;
import com.wuxue.view.client.setMeal.SetMealClient;
import com.wuxue.view.controllers.BaseController;
import com.wuxue.view.interfaces.*;
import com.wuxue.view.utils.SetMealList;
import com.wuxue.view.utils.SysDicEmnuUtils;
import com.wuxue.view.utils.SysDictUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 个性化课表
 */
@Controller
@RequestMapping(value = "/setMeal/mealStatistics")
public class mealStatisticsController extends BaseController implements IQueryController<SetMeal, String>,
        IQueryByPagingController<SetMeal, Map<String, Object>> ,ICreateController<SetMeal, String>,ISaveController<SetMeal, String>, IEditController<SetMeal, String> {


    @Autowired
    private SetMealClient setMealClient;

    /**
     * 列表页面
     *
     * @param model
     * @return
     * @throws ParseException
     */
    @Override
    public String query(HttpServletRequest request, Model model, SetMeal setMeal) {

        return "/meal/mealStatisticsList";
    }

    @Override
    @ResponseBody
    public Map<String, Object> queryByPaging(HttpServletRequest request, HttpServletResponse response, SetMeal setMeal, Integer sEcho, Integer start, Integer length) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        setMeal.setPageNo((start / length) + 1);
        setMeal.setPageSize(length);
        try {
            if(setMeal.getFromDateString() != null && !setMeal.getFromDateString().equals("")){
                setMeal.setFromDate(sdf.parse(setMeal.getFromDateString()));
            }else{
                setMeal.setFromDate(new Date());
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Response<List<SetMeal>> listResponse = setMealClient.mealStatistics(setMeal);
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

    /**
     * 添加页面
     *
     * @return
     */
    @Override
    public String create(HttpServletRequest request, Model model, SetMeal setMeal) {
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
        return "/meal/meal";
    }

}
