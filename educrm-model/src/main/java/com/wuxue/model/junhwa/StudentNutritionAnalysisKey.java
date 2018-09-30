package com.wuxue.model.junhwa;

import java.io.Serializable;

/**
 *
 * TABLE  student_nutrition_analysis
 * MyBatis Generator Create
 */
public class StudentNutritionAnalysisKey implements Serializable {
    /**
     * pk_student
     * 学生主键
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    private String pkStudent;

    /**
     * nutrition_id
     * 营养元素主键
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    private String nutritionId;

    /**
     * period_type
     * 分析周期类型，1=周，2=月，3=季度，4=年
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    private Byte periodType;

    /**
     * calc_year
     * 计算年份
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    private String calcYear;

    /**
     * calc_month
     * 计算月份
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    private Byte calcMonth;

    /**
     * calc_season
     * 计算季度
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    private Byte calcSeason;

    /**
     * calc_week
     * 计算周
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    private Byte calcWeek;

    private static final long serialVersionUID = 1L;

    public String getPkStudent() {
        return pkStudent;
    }

    public void setPkStudent(String pkStudent) {
        this.pkStudent = pkStudent;
    }

    public String getNutritionId() {
        return nutritionId;
    }

    public void setNutritionId(String nutritionId) {
        this.nutritionId = nutritionId;
    }

    public Byte getPeriodType() {
        return periodType;
    }

    public void setPeriodType(Byte periodType) {
        this.periodType = periodType;
    }

    public String getCalcYear() {
        return calcYear;
    }

    public void setCalcYear(String calcYear) {
        this.calcYear = calcYear;
    }

    public Byte getCalcMonth() {
        return calcMonth;
    }

    public void setCalcMonth(Byte calcMonth) {
        this.calcMonth = calcMonth;
    }

    public Byte getCalcSeason() {
        return calcSeason;
    }

    public void setCalcSeason(Byte calcSeason) {
        this.calcSeason = calcSeason;
    }

    public Byte getCalcWeek() {
        return calcWeek;
    }

    public void setCalcWeek(Byte calcWeek) {
        this.calcWeek = calcWeek;
    }
}