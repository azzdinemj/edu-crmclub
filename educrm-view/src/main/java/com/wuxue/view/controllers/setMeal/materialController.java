package com.wuxue.view.controllers.setMeal;

import com.wuxue.model.Student;
import com.wuxue.model.junhwa.Material;
import com.wuxue.model.junhwa.MealMaterial;
import com.wuxue.utils.contract.Light;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.setMeal.MaterialClient;
import com.wuxue.view.controllers.BaseController;
import com.wuxue.view.interfaces.*;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 个性化课表
 */
@Controller
@RequestMapping(value = "/material/material")
public class materialController extends BaseController implements IQueryController<Material, String>,
        IQueryByPagingController<Material, Map<String, Object>> ,ICreateController<Material, String>,ISaveController<Material, String>, IEditController<Material, String> {


    @Autowired
    private MaterialClient materialClient;

    /**
     * 列表页面
     *
     * @param model
     * @return
     * @throws ParseException
     */
    @Override
    public String query(HttpServletRequest request, Model model, Material material) {

        return "/meal/mealList";
    }

    @Override
    @ResponseBody
    public Map<String, Object> queryByPaging(HttpServletRequest request, HttpServletResponse response, Material material, Integer sEcho, Integer start, Integer length) {

        material.setPageNo((start / length) + 1);
        material.setPageSize(length);
        Response<List<Material>> listResponse = materialClient.find(material);
        List<Material> data = listResponse.getData();
        if (data == null) {
            data = new ArrayList<>();
        }
        return convertToPaging(listResponse.getTotal(), listResponse.getTotal(), data);


    }

    @Override
    @ResponseBody
    public String save(HttpServletRequest request, Model model,Material material) throws ParseException {
        return null;
    }

    /**
     * 添加页面
     *
     * @return
     */
    @Override
    public String create(HttpServletRequest request, Model model, Material material) {
        return "/meal/meal";
    }

    /**
     * 修改页面
     *
     * @param material
     * @param model
     * @return
     */
    @Override
    public String edit(HttpServletRequest request, Model model, Material material) {
        return "/meal/meal";
    }

}
