package com.wuxue.api.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wuxue.api.mapper.CanteenSetmealMapper;
import com.wuxue.api.mapper.MealMaterialMapper;
import com.wuxue.api.mapper.SetMealMapper;
import com.wuxue.api.mapper.StudentAllergensMapper;
import com.wuxue.api.service.SetMealService;
import com.wuxue.api.utils.GuidUtils;
import com.wuxue.model.CanteenSetmeal;
import com.wuxue.model.junhwa.MealMaterial;
import com.wuxue.model.SetMeal;
import com.wuxue.model.StudentAllergens;
import com.wuxue.utils.common.DataUtils;
import com.wuxue.utils.common.DateTimeUtils;
import com.wuxue.utils.common.DateUtils;
import com.wuxue.utils.contract.Light;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("setMealService")
public class SetMealServiceImpl implements SetMealService {

    @Autowired
    private SetMealMapper setMealMapper;
    @Autowired
    private StudentAllergensMapper studentAllergensMapper;
    @Autowired
    private MealMaterialMapper mealMaterialMapper;
    @Autowired
    private CanteenSetmealMapper canteenSetmealMapper;

    @Override
    public Response delete(Request<String> tParams) {
        Response response = Response.newResponse();

        String primaryKey = tParams.getData();
        if (primaryKey == null || "".equals(primaryKey)) {
            return response.PARAMS_ISNULL();
        }
        String message = "";
        try {
            SetMeal setMeal = setMealMapper.selectByPrimaryKey(primaryKey);
            setMeal.setIsvalid(3);
            if(setMeal.getIsvalid() == 0){
                setMeal.setDefaults(0);
            }
            setMeal.setLasteditDate(new Date());
            setMealMapper.updateByPrimaryKeySelective(setMeal);
        } catch (Exception ex) {
            message = ex.getMessage();
            return response.DELETE_FAIL(message);
        }


        return response;
    }

    @Override
    public Response find(Request<SetMeal> tParams) {
        Response response = Response.newResponse();
        SetMeal setMeal = tParams.getData();
        if (setMeal == null) {
            return response.PARAMS_ISNULL();
        }
        String primaryKey = setMeal.getPkSetMeal();
        if (primaryKey != null && !"".equals(primaryKey)) {
            SetMeal setMeal1 = setMealMapper.selectByPrimaryKey(primaryKey);
            List<SetMeal> setMealList = setMealMapper.selectNutrition(setMeal);
            //          套餐原料
            MealMaterial mealMaterial = new MealMaterial();

            mealMaterial.setSetMealId(setMeal1.getPkSetMeal());
            List<MealMaterial> mealMaterialList = mealMaterialMapper.select(mealMaterial);

            setMeal1.put(Light.MEAL_MATERIAL, mealMaterialList);
            setMeal1.put(Light.MEAL_NUTRITION, setMealList);
            response.setData(setMeal1);
        } else {
            setMeal.setPkCanteen("001");
            if (setMeal.getMealDateTime()!= null && !"".equals(setMeal.getMealDateTime())){
                int i = DateUtils.dayForWeek(DateTimeUtils.stringToDate(setMeal.getMealDateTime()));
                setMeal.setWeekDay(i-1);
            }
//            PageHelper.startPage(setMeal.getPageNo(), setMeal.getPageSize());
            List<SetMeal> list = setMealMapper.select(setMeal);
//            PageInfo page = new PageInfo(list);
//            response.setTotal(page.getTotal());

//            学生过敏源查询
            if (list.size() > 0) {
                for (SetMeal meal : list) {
                    setMeal.setPkSetMeal(meal.getPkSetMeal());
                    List<SetMeal> setMealList = setMealMapper.findAllergy(setMeal);
                    if(setMealList.size() > 0){
                        meal.setAllergy(1);
                    }else{
                        meal.setAllergy(0);
                    }
                }
            }

            response.setData(list);

        }
        return response;
    }

    @Override
    public Response save(Request<SetMeal> tParams) {
        Response response = Response.newResponse();
        SetMeal setMeal = tParams.getData();
        if (setMeal == null) {
            return response.PARAMS_ISNULL();
        }
        String primaryKey = setMeal.getPkSetMeal();
        String message = "";
        SetMeal select = null;
        if (primaryKey != null && !"".equals(primaryKey)) {
            select = setMealMapper.selectByPrimaryKey(primaryKey);
        }

        try {
            if (select != null) {
                if(setMeal.getIsvalid() == 0){
                    setMeal.setDefaults(0);
                }
                if(setMeal.getDefaults() == 1){
                    SetMeal setMeal1 = new SetMeal();
                    setMeal1.setType(setMeal.getType());
                    setMeal1.setDefaults(1);
                    setMeal1.setWeekDay(setMeal.getWeekDay());
                    List<SetMeal> select2 = setMealMapper.select(setMeal1);
                    if (select2.size() > 0) {
                        if(!select2.get(0).getCode().equals(select.getCode())) {
                            return response.DEFAULTS_DOUBLE();
                        }
                    }
                }
                setMeal.setLasteditDate(new Date());
                setMealMapper.updateByPrimaryKeySelective(setMeal);

                //删除套餐绑定原料
                mealMaterialMapper.deleteByMeal(setMeal.getPkSetMeal());
            } else {
//                验证编号
                SetMeal setMeal1 = new SetMeal();
                setMeal1.setCode(setMeal.getCode());
                List<SetMeal> select1 = setMealMapper.select(setMeal1);
                if(select1.size() > 0){
                    return response.CODE_DOUBLE();
                }
                if(setMeal.getDefaults() == 1){
                    setMeal1 = new SetMeal();
                    setMeal1.setType(setMeal.getType());
                    setMeal1.setDefaults(1);
                    setMeal1.setWeekDay(setMeal.getWeekDay());
                    List<SetMeal> select2 = setMealMapper.select(setMeal1);
                    if (select2.size() > 0) {
                        return response.DEFAULTS_DOUBLE();
                    }
                }
                if(setMeal.getIsvalid() == 0){
                    setMeal.setDefaults(0);
                }
                setMeal.setPkSetMeal(GuidUtils.getGuid());
                setMeal.setCreationDate(new Date());
                setMeal.setCreator(tParams.getCurrentUser());
                setMeal.setModifier(tParams.getCurrentUser());
                setMeal.setLasteditDate(new Date());
                setMeal.setIsvalid(1);
                setMealMapper.insertSelective(setMeal);

                CanteenSetmeal canteenSetmeal = new CanteenSetmeal();
                canteenSetmeal.setPkCanteen("001");
                canteenSetmeal.setPkCanteenMeal(GuidUtils.getGuid());
                canteenSetmeal.setCode("001");
                canteenSetmeal.setPkSetMeal(setMeal.getPkSetMeal());
                canteenSetmeal.setIsvalid(1);
                canteenSetmealMapper.insertSelective(canteenSetmeal);

            }

            //            保存套餐的原料
            Map<String, Object> map = setMeal.getMap();
            if (map != null) {
//                套餐原料
                List<MealMaterial> mealMaterialList = DataUtils.objectToList(map.get(Light.MEAL_MATERIAL), MealMaterial.class);
                if (mealMaterialList != null && mealMaterialList.size() > 0) {
                    for (MealMaterial mealMaterial : mealMaterialList) {
                        mealMaterial.setMealMaterialId(GuidUtils.getGuid());
                        mealMaterial.setSetMealId(setMeal.getPkSetMeal());
                        mealMaterial.setCreator(tParams.getCurrentUser());
                        mealMaterial.setModifier(tParams.getCurrentUser());
                        mealMaterial.setCreationDate(new Date());
                        mealMaterial.setLasteditDate(new Date());
                        mealMaterialMapper.insertSelective(mealMaterial);
                    }
                }
            }

        } catch (Exception ex) {
            message = ex.getMessage();
            return response.SAVE_FAIL(message);
        }

        return response;
    }

    @Override
    public List<SetMeal> findByStudent(SetMeal setMeal,String pkStudent) {

        List<SetMeal> list = setMealMapper.select(setMeal);
        if (list.size()>0){
            for (SetMeal meal : list) {
                List<StudentAllergens> studentAllergens = studentAllergensMapper.selectByStudent(pkStudent);
                if (studentAllergens.size() >0){
                    meal.put("isAllergens",1);
                }

            }
        }

        return list;
    }

    @Override
    public Response mealStatistics(Request<SetMeal> tParams) {
        Response response = Response.newResponse();
        SetMeal setMeal = tParams.getData();
        if (setMeal == null) {
            return response.PARAMS_ISNULL();
        }
        String message;
        try {
            List<SetMeal> setMealList = setMealMapper.mealStatistics(setMeal);
            response.setData(setMealList);
        }catch (Exception ex){
            message = ex.getMessage();
            return response.FIND_FAIL(message);
        }
        return response;
    }

    @Override
    public SetMeal findOnlySetMealById(String id) {
        return setMealMapper.selectByPrimaryKey(id);
    }

    @Override
    public Map<String, SetMeal> getMealIdANdSetMealMap(List<String> ids) {
        List<SetMeal> meals = setMealMapper.findSetMealByIds(ids);
        if(CollectionUtils.isEmpty(meals)){
            return new HashMap<>(0);
        }
        Map<String, SetMeal> map = new HashMap<>();
        for(SetMeal setMeal:meals){
            map.put(setMeal.getPkSetMeal(), setMeal);
        }
        return map;
    }

    @Override
    public Response queryDefault(Request<SetMeal> tParams) {
        Response response = Response.newResponse();
        SetMeal setMeal = tParams.getData();
        if (setMeal == null) {
            return response.PARAMS_ISNULL();
        }
        String message;
        try {
            setMeal.setDefaults(1);
            List<SetMeal> setMealList = setMealMapper.select(setMeal);
            if (setMealList.size() > 0) {
                return response.SERVER_ERROR(setMealList.get(0).getCode());
            }
        }catch (Exception ex){
            message = ex.getMessage();
            return response.FIND_FAIL(message);
        }
        return response;
    }

    @Override
    public Response queryCode(Request<SetMeal> tParams) {
        Response response = Response.newResponse();
        SetMeal setMeal = tParams.getData();
        if (setMeal == null) {
            return response.PARAMS_ISNULL();
        }
        String message;
        try {
            List<SetMeal> setMealList = setMealMapper.select(setMeal);
            if (setMealList.size() > 0) {
                return response.CODE_DOUBLE();
            }
        }catch (Exception ex){
            message = ex.getMessage();
            return response.FIND_FAIL(message);
        }
        return response;
    }
}
