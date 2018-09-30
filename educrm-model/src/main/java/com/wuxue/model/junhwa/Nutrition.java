package com.wuxue.model.junhwa;

import java.io.Serializable;

/**
 *
 * TABLE  nutrition
 * MyBatis Generator Create
 */
public class Nutrition implements Serializable {
    /**
     * nutrition_id
     * 营养元素主键
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    private String nutritionId;

    /**
     * code
     * 元素编号
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    private String code;

    /**
     * nutrition_name
     * 元素名称
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    private String nutritionName;

    /**
     * unit
     * 单位
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    private String unit;

    /**
     * content
     * 含量
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    private Long content;

    /**
     * material_id
     * 原料id
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    private String materialId;


    /***********非持久化字段**********/

    private Double sumValue = 0.0d;//元素总含量

    private Double gramCount;


    public String getNutritionId() {
        return nutritionId;
    }

    public void setNutritionId(String nutritionId) {
        this.nutritionId = nutritionId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public Long getContent() {
        return content;
    }

    public void setContent(Long content) {
        this.content = content;
    }

    public String getMaterialId() {
        return materialId;
    }

    public void setMaterialId(String materialId) {
        this.materialId = materialId;
    }

    public void setSumValue(Double sumValue) {
        this.sumValue += sumValue;
    }

    public Double getGramCount() {
        return gramCount;
    }

    public void setGramCount(Double gramCount) {
        this.gramCount = gramCount;
    }

    public Double getSumValue() {
        if(this.sumValue != null) {
            return this.sumValue;
        }
        return calculate(this.gramCount, this.content);
    }

    /**
     * 公式
     * @param gram 原料重量
     * @param content 元素含量
     * @return
     */
    private  Double calculate(Double gram,Long content){
        //可食用百分比
//        Double canEatPercent= Double.valueOf(canEatValue)/ 100.0;
//        Double contentPercent = Double.valueOf(content) / 100.0;
        return gram * Double.valueOf(content);
    }
}