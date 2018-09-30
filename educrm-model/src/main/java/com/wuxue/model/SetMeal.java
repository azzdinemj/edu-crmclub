package com.wuxue.model;

import com.wuxue.base.BusinessPage;
import com.wuxue.model.junhwa.Material;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 套餐表
 */
public class SetMeal extends BusinessPage {
    /**
     * 套餐主键
     */
    private String pkSetMeal;
    /**
     * 套餐编号
     */
    private String code;
    /**
     * 套餐名
     */
    private String setMealName;
    /**
     * 套餐类型:早、中、晚
     */
    private Integer type;
    /**
     * 套餐价格
     */
    private BigDecimal price;
    /**
     * 状态
     */
    private Integer isvalid;
    /**
     * 备注
     */
    private String memo;

    /**
     * 套餐数量
     */
    private Integer mealNumber;

    /**
     * 是否过敏 0未过敏  1已过敏
     * */
    private Integer allergy;

    /**
     * 学生主键
     * */
    private String pkStudent;

    /**
     * 营养名称
     * */
    private String nutritionName;

    /**
     * 营养单位
     * */
    private String unit;

    /**
     * 营养含量
     * */
    private String content;

    /**
     * 食堂主键
     * */
    private String pkCanteen;

    private Integer defaults;
    /**
     * 周几数字
     */
    private Integer weekDay;

    /***********非持久化字段***********/

    private Date mealDate;

    private String mealDateTime;

    /*
     * 原料集合
     */
    private List<Material> Materials;

    public String getPkSetMeal() {
        return pkSetMeal;
    }

    public void setPkSetMeal(String pkSetMeal) {
        this.pkSetMeal = pkSetMeal;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSetMealName() {
        return setMealName;
    }

    public void setSetMealName(String setMealName) {
        this.setMealName = setMealName;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getIsvalid() {
        return isvalid;
    }

    public void setIsvalid(Integer isvalid) {
        this.isvalid = isvalid;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public List<Material> getMaterials() {
        return Materials;
    }

    public void setMaterials(List<Material> materials) {
        Materials = materials;
    }

    public Integer getMealNumber() {
        return mealNumber;
    }

    public void setMealNumber(Integer mealNumber) {
        this.mealNumber = mealNumber;
    }

    public Integer getAllergy() {
        return allergy;
    }

    public void setAllergy(Integer allergy) {
        this.allergy = allergy;
    }

    public String getPkStudent() {
        return pkStudent;
    }

    public void setPkStudent(String pkStudent) {
        this.pkStudent = pkStudent;
    }

    public String getNutritionName() {
        return nutritionName;
    }

    public void setNutritionName(String nutritionName) {
        this.nutritionName = nutritionName;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPkCanteen() {
        return pkCanteen;
    }

    public void setPkCanteen(String pkCanteen) {
        this.pkCanteen = pkCanteen;
    }

    public Integer getDefaults() {
        return defaults;
    }

    public void setDefaults(Integer defaults) {
        this.defaults = defaults;
    }

    public Date getMealDate() {
        return mealDate;
    }

    public void setMealDate(Date mealDate) {
        this.mealDate = mealDate;
    }

    public Integer getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(Integer weekDay) {
        this.weekDay = weekDay;
    }

    public String getMealDateTime() {
        return mealDateTime;
    }

    public void setMealDateTime(String mealDateTime) {
        this.mealDateTime = mealDateTime;
    }
}