package com.wuxue.model.junhwa;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * TABLE  student_nutrition_analysis
 * MyBatis Generator Create
 */
public class StudentNutritionAnalysis extends StudentNutritionAnalysisKey implements Serializable {
    /**
     * realmount
     * 营养实际量
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    private BigDecimal realmount;

    /**
     * standmount_low
     * 营养标准量下限
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    private BigDecimal standmountLow;

    /**
     * standmount_high
     * 营养标准量上限
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    private BigDecimal standmountHigh;

    /**
     * diffmount
     * 营养差异量，超出标准范围的部分
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    private BigDecimal diffmount;

    /**
     * diff_per
     * 营养差异比例,0表示无差异，合理
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    private BigDecimal diffPer;

    /**
     * suggest_set
     * 建议套餐,逗号区分的套餐编码+名称
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    private String suggestSet;

    /**
     * createdtime
     * 记录产生的时间
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    private Date createdtime;

    private Nutrition nutrition;



    private String yearAndMonth;

    private static final long serialVersionUID = 1L;

    public String getYearAndMonth() {
        return yearAndMonth;
    }

    public void setYearAndMonth(String yearAndMonth) {
        this.yearAndMonth = yearAndMonth;
    }

    public Nutrition getNutrition() {
        return nutrition;
    }

    public void setNutrition(Nutrition nutrition) {
        this.nutrition = nutrition;
    }

    public BigDecimal getRealmount() {
        return realmount;
    }

    public void setRealmount(BigDecimal realmount) {
        this.realmount = realmount;
    }

    public BigDecimal getStandmountLow() {
        return standmountLow;
    }

    public void setStandmountLow(BigDecimal standmountLow) {
        this.standmountLow = standmountLow;
    }

    public BigDecimal getStandmountHigh() {
        return standmountHigh;
    }

    public void setStandmountHigh(BigDecimal standmountHigh) {
        this.standmountHigh = standmountHigh;
    }

    public BigDecimal getDiffmount() {
        return diffmount;
    }

    public void setDiffmount(BigDecimal diffmount) {
        this.diffmount = diffmount;
    }

    public BigDecimal getDiffPer() {
        return diffPer;
    }

    public void setDiffPer(BigDecimal diffPer) {
        this.diffPer = diffPer;
    }

    public String getSuggestSet() {
        return suggestSet;
    }

    public void setSuggestSet(String suggestSet) {
        this.suggestSet = suggestSet;
    }

    public Date getCreatedtime() {
        return createdtime;
    }

    public void setCreatedtime(Date createdtime) {
        this.createdtime = createdtime;
    }

//    public boolean isToMax(){
//        //toMax
//        return this.realmount.compareTo(this.standmountHigh) > 0;
//    }
//
//    public boolean isLow() {
//        //low
//        return this.realmount.compareTo(this.standmountLow) < 0;
//    }
}