package com.wuxue.model.junhwa;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StudentNutritionAnalysisExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer startIndex;

    protected Integer pageSize = 20;

    protected Integer pageNo;

    public StudentNutritionAnalysisExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    public void setStartIndex(Integer startIndex) {
        this.startIndex = startIndex -1;
    }

    public Integer getStartIndex() {
        if (this.pageNo != null && this.pageNo > 0) {
            this.startIndex = (this.pageNo - 1) * this.pageSize;
        }
        return this.startIndex;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageSize() {
        return this.pageSize;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageNo() {
        return this.pageNo;
    }

    /**
     * TABLE student_nutrition_analysis
     * @mbg.generated
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andPkStudentIsNull() {
            addCriterion("pk_student is null");
            return (Criteria) this;
        }

        public Criteria andPkStudentIsNotNull() {
            addCriterion("pk_student is not null");
            return (Criteria) this;
        }

        public Criteria andPkStudentEqualTo(String value) {
            addCriterion("pk_student =", value, "pkStudent");
            return (Criteria) this;
        }

        public Criteria andPkStudentNotEqualTo(String value) {
            addCriterion("pk_student <>", value, "pkStudent");
            return (Criteria) this;
        }

        public Criteria andPkStudentGreaterThan(String value) {
            addCriterion("pk_student >", value, "pkStudent");
            return (Criteria) this;
        }

        public Criteria andPkStudentGreaterThanOrEqualTo(String value) {
            addCriterion("pk_student >=", value, "pkStudent");
            return (Criteria) this;
        }

        public Criteria andPkStudentLessThan(String value) {
            addCriterion("pk_student <", value, "pkStudent");
            return (Criteria) this;
        }

        public Criteria andPkStudentLessThanOrEqualTo(String value) {
            addCriterion("pk_student <=", value, "pkStudent");
            return (Criteria) this;
        }

        public Criteria andPkStudentLike(String value) {
            addCriterion("pk_student like", value, "pkStudent");
            return (Criteria) this;
        }

        public Criteria andPkStudentNotLike(String value) {
            addCriterion("pk_student not like", value, "pkStudent");
            return (Criteria) this;
        }

        public Criteria andPkStudentIn(List<String> values) {
            addCriterion("pk_student in", values, "pkStudent");
            return (Criteria) this;
        }

        public Criteria andPkStudentNotIn(List<String> values) {
            addCriterion("pk_student not in", values, "pkStudent");
            return (Criteria) this;
        }

        public Criteria andPkStudentBetween(String value1, String value2) {
            addCriterion("pk_student between", value1, value2, "pkStudent");
            return (Criteria) this;
        }

        public Criteria andPkStudentNotBetween(String value1, String value2) {
            addCriterion("pk_student not between", value1, value2, "pkStudent");
            return (Criteria) this;
        }

        public Criteria andNutritionIdIsNull() {
            addCriterion("nutrition_id is null");
            return (Criteria) this;
        }

        public Criteria andNutritionIdIsNotNull() {
            addCriterion("nutrition_id is not null");
            return (Criteria) this;
        }

        public Criteria andNutritionIdEqualTo(String value) {
            addCriterion("nutrition_id =", value, "nutritionId");
            return (Criteria) this;
        }

        public Criteria andNutritionIdNotEqualTo(String value) {
            addCriterion("nutrition_id <>", value, "nutritionId");
            return (Criteria) this;
        }

        public Criteria andNutritionIdGreaterThan(String value) {
            addCriterion("nutrition_id >", value, "nutritionId");
            return (Criteria) this;
        }

        public Criteria andNutritionIdGreaterThanOrEqualTo(String value) {
            addCriterion("nutrition_id >=", value, "nutritionId");
            return (Criteria) this;
        }

        public Criteria andNutritionIdLessThan(String value) {
            addCriterion("nutrition_id <", value, "nutritionId");
            return (Criteria) this;
        }

        public Criteria andNutritionIdLessThanOrEqualTo(String value) {
            addCriterion("nutrition_id <=", value, "nutritionId");
            return (Criteria) this;
        }

        public Criteria andNutritionIdLike(String value) {
            addCriterion("nutrition_id like", value, "nutritionId");
            return (Criteria) this;
        }

        public Criteria andNutritionIdNotLike(String value) {
            addCriterion("nutrition_id not like", value, "nutritionId");
            return (Criteria) this;
        }

        public Criteria andNutritionIdIn(List<String> values) {
            addCriterion("nutrition_id in", values, "nutritionId");
            return (Criteria) this;
        }

        public Criteria andNutritionIdNotIn(List<String> values) {
            addCriterion("nutrition_id not in", values, "nutritionId");
            return (Criteria) this;
        }

        public Criteria andNutritionIdBetween(String value1, String value2) {
            addCriterion("nutrition_id between", value1, value2, "nutritionId");
            return (Criteria) this;
        }

        public Criteria andNutritionIdNotBetween(String value1, String value2) {
            addCriterion("nutrition_id not between", value1, value2, "nutritionId");
            return (Criteria) this;
        }

        public Criteria andPeriodTypeIsNull() {
            addCriterion("period_type is null");
            return (Criteria) this;
        }

        public Criteria andPeriodTypeIsNotNull() {
            addCriterion("period_type is not null");
            return (Criteria) this;
        }

        public Criteria andPeriodTypeEqualTo(Byte value) {
            addCriterion("period_type =", value, "periodType");
            return (Criteria) this;
        }

        public Criteria andPeriodTypeNotEqualTo(Byte value) {
            addCriterion("period_type <>", value, "periodType");
            return (Criteria) this;
        }

        public Criteria andPeriodTypeGreaterThan(Byte value) {
            addCriterion("period_type >", value, "periodType");
            return (Criteria) this;
        }

        public Criteria andPeriodTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("period_type >=", value, "periodType");
            return (Criteria) this;
        }

        public Criteria andPeriodTypeLessThan(Byte value) {
            addCriterion("period_type <", value, "periodType");
            return (Criteria) this;
        }

        public Criteria andPeriodTypeLessThanOrEqualTo(Byte value) {
            addCriterion("period_type <=", value, "periodType");
            return (Criteria) this;
        }

        public Criteria andPeriodTypeIn(List<Byte> values) {
            addCriterion("period_type in", values, "periodType");
            return (Criteria) this;
        }

        public Criteria andPeriodTypeNotIn(List<Byte> values) {
            addCriterion("period_type not in", values, "periodType");
            return (Criteria) this;
        }

        public Criteria andPeriodTypeBetween(Byte value1, Byte value2) {
            addCriterion("period_type between", value1, value2, "periodType");
            return (Criteria) this;
        }

        public Criteria andPeriodTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("period_type not between", value1, value2, "periodType");
            return (Criteria) this;
        }

        public Criteria andCalcYearIsNull() {
            addCriterion("calc_year is null");
            return (Criteria) this;
        }

        public Criteria andCalcYearIsNotNull() {
            addCriterion("calc_year is not null");
            return (Criteria) this;
        }

        public Criteria andCalcYearEqualTo(String value) {
            addCriterion("calc_year =", value, "calcYear");
            return (Criteria) this;
        }

        public Criteria andCalcYearNotEqualTo(String value) {
            addCriterion("calc_year <>", value, "calcYear");
            return (Criteria) this;
        }

        public Criteria andCalcYearGreaterThan(String value) {
            addCriterion("calc_year >", value, "calcYear");
            return (Criteria) this;
        }

        public Criteria andCalcYearGreaterThanOrEqualTo(String value) {
            addCriterion("calc_year >=", value, "calcYear");
            return (Criteria) this;
        }

        public Criteria andCalcYearLessThan(String value) {
            addCriterion("calc_year <", value, "calcYear");
            return (Criteria) this;
        }

        public Criteria andCalcYearLessThanOrEqualTo(String value) {
            addCriterion("calc_year <=", value, "calcYear");
            return (Criteria) this;
        }

        public Criteria andCalcYearLike(String value) {
            addCriterion("calc_year like", value, "calcYear");
            return (Criteria) this;
        }

        public Criteria andCalcYearNotLike(String value) {
            addCriterion("calc_year not like", value, "calcYear");
            return (Criteria) this;
        }

        public Criteria andCalcYearIn(List<String> values) {
            addCriterion("calc_year in", values, "calcYear");
            return (Criteria) this;
        }

        public Criteria andCalcYearNotIn(List<String> values) {
            addCriterion("calc_year not in", values, "calcYear");
            return (Criteria) this;
        }

        public Criteria andCalcYearBetween(String value1, String value2) {
            addCriterion("calc_year between", value1, value2, "calcYear");
            return (Criteria) this;
        }

        public Criteria andCalcYearNotBetween(String value1, String value2) {
            addCriterion("calc_year not between", value1, value2, "calcYear");
            return (Criteria) this;
        }

        public Criteria andCalcMonthIsNull() {
            addCriterion("calc_month is null");
            return (Criteria) this;
        }

        public Criteria andCalcMonthIsNotNull() {
            addCriterion("calc_month is not null");
            return (Criteria) this;
        }

        public Criteria andCalcMonthEqualTo(Byte value) {
            addCriterion("calc_month =", value, "calcMonth");
            return (Criteria) this;
        }

        public Criteria andCalcMonthNotEqualTo(Byte value) {
            addCriterion("calc_month <>", value, "calcMonth");
            return (Criteria) this;
        }

        public Criteria andCalcMonthGreaterThan(Byte value) {
            addCriterion("calc_month >", value, "calcMonth");
            return (Criteria) this;
        }

        public Criteria andCalcMonthGreaterThanOrEqualTo(Byte value) {
            addCriterion("calc_month >=", value, "calcMonth");
            return (Criteria) this;
        }

        public Criteria andCalcMonthLessThan(Byte value) {
            addCriterion("calc_month <", value, "calcMonth");
            return (Criteria) this;
        }

        public Criteria andCalcMonthLessThanOrEqualTo(Byte value) {
            addCriterion("calc_month <=", value, "calcMonth");
            return (Criteria) this;
        }

        public Criteria andCalcMonthIn(List<Byte> values) {
            addCriterion("calc_month in", values, "calcMonth");
            return (Criteria) this;
        }

        public Criteria andCalcMonthNotIn(List<Byte> values) {
            addCriterion("calc_month not in", values, "calcMonth");
            return (Criteria) this;
        }

        public Criteria andCalcMonthBetween(Byte value1, Byte value2) {
            addCriterion("calc_month between", value1, value2, "calcMonth");
            return (Criteria) this;
        }

        public Criteria andCalcMonthNotBetween(Byte value1, Byte value2) {
            addCriterion("calc_month not between", value1, value2, "calcMonth");
            return (Criteria) this;
        }

        public Criteria andCalcSeasonIsNull() {
            addCriterion("calc_season is null");
            return (Criteria) this;
        }

        public Criteria andCalcSeasonIsNotNull() {
            addCriterion("calc_season is not null");
            return (Criteria) this;
        }

        public Criteria andCalcSeasonEqualTo(Byte value) {
            addCriterion("calc_season =", value, "calcSeason");
            return (Criteria) this;
        }

        public Criteria andCalcSeasonNotEqualTo(Byte value) {
            addCriterion("calc_season <>", value, "calcSeason");
            return (Criteria) this;
        }

        public Criteria andCalcSeasonGreaterThan(Byte value) {
            addCriterion("calc_season >", value, "calcSeason");
            return (Criteria) this;
        }

        public Criteria andCalcSeasonGreaterThanOrEqualTo(Byte value) {
            addCriterion("calc_season >=", value, "calcSeason");
            return (Criteria) this;
        }

        public Criteria andCalcSeasonLessThan(Byte value) {
            addCriterion("calc_season <", value, "calcSeason");
            return (Criteria) this;
        }

        public Criteria andCalcSeasonLessThanOrEqualTo(Byte value) {
            addCriterion("calc_season <=", value, "calcSeason");
            return (Criteria) this;
        }

        public Criteria andCalcSeasonIn(List<Byte> values) {
            addCriterion("calc_season in", values, "calcSeason");
            return (Criteria) this;
        }

        public Criteria andCalcSeasonNotIn(List<Byte> values) {
            addCriterion("calc_season not in", values, "calcSeason");
            return (Criteria) this;
        }

        public Criteria andCalcSeasonBetween(Byte value1, Byte value2) {
            addCriterion("calc_season between", value1, value2, "calcSeason");
            return (Criteria) this;
        }

        public Criteria andCalcSeasonNotBetween(Byte value1, Byte value2) {
            addCriterion("calc_season not between", value1, value2, "calcSeason");
            return (Criteria) this;
        }

        public Criteria andCalcWeekIsNull() {
            addCriterion("calc_week is null");
            return (Criteria) this;
        }

        public Criteria andCalcWeekIsNotNull() {
            addCriterion("calc_week is not null");
            return (Criteria) this;
        }

        public Criteria andCalcWeekEqualTo(Byte value) {
            addCriterion("calc_week =", value, "calcWeek");
            return (Criteria) this;
        }

        public Criteria andCalcWeekNotEqualTo(Byte value) {
            addCriterion("calc_week <>", value, "calcWeek");
            return (Criteria) this;
        }

        public Criteria andCalcWeekGreaterThan(Byte value) {
            addCriterion("calc_week >", value, "calcWeek");
            return (Criteria) this;
        }

        public Criteria andCalcWeekGreaterThanOrEqualTo(Byte value) {
            addCriterion("calc_week >=", value, "calcWeek");
            return (Criteria) this;
        }

        public Criteria andCalcWeekLessThan(Byte value) {
            addCriterion("calc_week <", value, "calcWeek");
            return (Criteria) this;
        }

        public Criteria andCalcWeekLessThanOrEqualTo(Byte value) {
            addCriterion("calc_week <=", value, "calcWeek");
            return (Criteria) this;
        }

        public Criteria andCalcWeekIn(List<Byte> values) {
            addCriterion("calc_week in", values, "calcWeek");
            return (Criteria) this;
        }

        public Criteria andCalcWeekNotIn(List<Byte> values) {
            addCriterion("calc_week not in", values, "calcWeek");
            return (Criteria) this;
        }

        public Criteria andCalcWeekBetween(Byte value1, Byte value2) {
            addCriterion("calc_week between", value1, value2, "calcWeek");
            return (Criteria) this;
        }

        public Criteria andCalcWeekNotBetween(Byte value1, Byte value2) {
            addCriterion("calc_week not between", value1, value2, "calcWeek");
            return (Criteria) this;
        }

        public Criteria andRealmountIsNull() {
            addCriterion("realmount is null");
            return (Criteria) this;
        }

        public Criteria andRealmountIsNotNull() {
            addCriterion("realmount is not null");
            return (Criteria) this;
        }

        public Criteria andRealmountEqualTo(BigDecimal value) {
            addCriterion("realmount =", value, "realmount");
            return (Criteria) this;
        }

        public Criteria andRealmountNotEqualTo(BigDecimal value) {
            addCriterion("realmount <>", value, "realmount");
            return (Criteria) this;
        }

        public Criteria andRealmountGreaterThan(BigDecimal value) {
            addCriterion("realmount >", value, "realmount");
            return (Criteria) this;
        }

        public Criteria andRealmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("realmount >=", value, "realmount");
            return (Criteria) this;
        }

        public Criteria andRealmountLessThan(BigDecimal value) {
            addCriterion("realmount <", value, "realmount");
            return (Criteria) this;
        }

        public Criteria andRealmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("realmount <=", value, "realmount");
            return (Criteria) this;
        }

        public Criteria andRealmountIn(List<BigDecimal> values) {
            addCriterion("realmount in", values, "realmount");
            return (Criteria) this;
        }

        public Criteria andRealmountNotIn(List<BigDecimal> values) {
            addCriterion("realmount not in", values, "realmount");
            return (Criteria) this;
        }

        public Criteria andRealmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("realmount between", value1, value2, "realmount");
            return (Criteria) this;
        }

        public Criteria andRealmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("realmount not between", value1, value2, "realmount");
            return (Criteria) this;
        }

        public Criteria andStandmountLowIsNull() {
            addCriterion("standmount_low is null");
            return (Criteria) this;
        }

        public Criteria andStandmountLowIsNotNull() {
            addCriterion("standmount_low is not null");
            return (Criteria) this;
        }

        public Criteria andStandmountLowEqualTo(BigDecimal value) {
            addCriterion("standmount_low =", value, "standmountLow");
            return (Criteria) this;
        }

        public Criteria andStandmountLowNotEqualTo(BigDecimal value) {
            addCriterion("standmount_low <>", value, "standmountLow");
            return (Criteria) this;
        }

        public Criteria andStandmountLowGreaterThan(BigDecimal value) {
            addCriterion("standmount_low >", value, "standmountLow");
            return (Criteria) this;
        }

        public Criteria andStandmountLowGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("standmount_low >=", value, "standmountLow");
            return (Criteria) this;
        }

        public Criteria andStandmountLowLessThan(BigDecimal value) {
            addCriterion("standmount_low <", value, "standmountLow");
            return (Criteria) this;
        }

        public Criteria andStandmountLowLessThanOrEqualTo(BigDecimal value) {
            addCriterion("standmount_low <=", value, "standmountLow");
            return (Criteria) this;
        }

        public Criteria andStandmountLowIn(List<BigDecimal> values) {
            addCriterion("standmount_low in", values, "standmountLow");
            return (Criteria) this;
        }

        public Criteria andStandmountLowNotIn(List<BigDecimal> values) {
            addCriterion("standmount_low not in", values, "standmountLow");
            return (Criteria) this;
        }

        public Criteria andStandmountLowBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("standmount_low between", value1, value2, "standmountLow");
            return (Criteria) this;
        }

        public Criteria andStandmountLowNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("standmount_low not between", value1, value2, "standmountLow");
            return (Criteria) this;
        }

        public Criteria andStandmountHighIsNull() {
            addCriterion("standmount_high is null");
            return (Criteria) this;
        }

        public Criteria andStandmountHighIsNotNull() {
            addCriterion("standmount_high is not null");
            return (Criteria) this;
        }

        public Criteria andStandmountHighEqualTo(BigDecimal value) {
            addCriterion("standmount_high =", value, "standmountHigh");
            return (Criteria) this;
        }

        public Criteria andStandmountHighNotEqualTo(BigDecimal value) {
            addCriterion("standmount_high <>", value, "standmountHigh");
            return (Criteria) this;
        }

        public Criteria andStandmountHighGreaterThan(BigDecimal value) {
            addCriterion("standmount_high >", value, "standmountHigh");
            return (Criteria) this;
        }

        public Criteria andStandmountHighGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("standmount_high >=", value, "standmountHigh");
            return (Criteria) this;
        }

        public Criteria andStandmountHighLessThan(BigDecimal value) {
            addCriterion("standmount_high <", value, "standmountHigh");
            return (Criteria) this;
        }

        public Criteria andStandmountHighLessThanOrEqualTo(BigDecimal value) {
            addCriterion("standmount_high <=", value, "standmountHigh");
            return (Criteria) this;
        }

        public Criteria andStandmountHighIn(List<BigDecimal> values) {
            addCriterion("standmount_high in", values, "standmountHigh");
            return (Criteria) this;
        }

        public Criteria andStandmountHighNotIn(List<BigDecimal> values) {
            addCriterion("standmount_high not in", values, "standmountHigh");
            return (Criteria) this;
        }

        public Criteria andStandmountHighBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("standmount_high between", value1, value2, "standmountHigh");
            return (Criteria) this;
        }

        public Criteria andStandmountHighNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("standmount_high not between", value1, value2, "standmountHigh");
            return (Criteria) this;
        }

        public Criteria andDiffmountIsNull() {
            addCriterion("diffmount is null");
            return (Criteria) this;
        }

        public Criteria andDiffmountIsNotNull() {
            addCriterion("diffmount is not null");
            return (Criteria) this;
        }

        public Criteria andDiffmountEqualTo(BigDecimal value) {
            addCriterion("diffmount =", value, "diffmount");
            return (Criteria) this;
        }

        public Criteria andDiffmountNotEqualTo(BigDecimal value) {
            addCriterion("diffmount <>", value, "diffmount");
            return (Criteria) this;
        }

        public Criteria andDiffmountGreaterThan(BigDecimal value) {
            addCriterion("diffmount >", value, "diffmount");
            return (Criteria) this;
        }

        public Criteria andDiffmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("diffmount >=", value, "diffmount");
            return (Criteria) this;
        }

        public Criteria andDiffmountLessThan(BigDecimal value) {
            addCriterion("diffmount <", value, "diffmount");
            return (Criteria) this;
        }

        public Criteria andDiffmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("diffmount <=", value, "diffmount");
            return (Criteria) this;
        }

        public Criteria andDiffmountIn(List<BigDecimal> values) {
            addCriterion("diffmount in", values, "diffmount");
            return (Criteria) this;
        }

        public Criteria andDiffmountNotIn(List<BigDecimal> values) {
            addCriterion("diffmount not in", values, "diffmount");
            return (Criteria) this;
        }

        public Criteria andDiffmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("diffmount between", value1, value2, "diffmount");
            return (Criteria) this;
        }

        public Criteria andDiffmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("diffmount not between", value1, value2, "diffmount");
            return (Criteria) this;
        }

        public Criteria andDiffPerIsNull() {
            addCriterion("diff_per is null");
            return (Criteria) this;
        }

        public Criteria andDiffPerIsNotNull() {
            addCriterion("diff_per is not null");
            return (Criteria) this;
        }

        public Criteria andDiffPerEqualTo(BigDecimal value) {
            addCriterion("diff_per =", value, "diffPer");
            return (Criteria) this;
        }

        public Criteria andDiffPerNotEqualTo(BigDecimal value) {
            addCriterion("diff_per <>", value, "diffPer");
            return (Criteria) this;
        }

        public Criteria andDiffPerGreaterThan(BigDecimal value) {
            addCriterion("diff_per >", value, "diffPer");
            return (Criteria) this;
        }

        public Criteria andDiffPerGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("diff_per >=", value, "diffPer");
            return (Criteria) this;
        }

        public Criteria andDiffPerLessThan(BigDecimal value) {
            addCriterion("diff_per <", value, "diffPer");
            return (Criteria) this;
        }

        public Criteria andDiffPerLessThanOrEqualTo(BigDecimal value) {
            addCriterion("diff_per <=", value, "diffPer");
            return (Criteria) this;
        }

        public Criteria andDiffPerIn(List<BigDecimal> values) {
            addCriterion("diff_per in", values, "diffPer");
            return (Criteria) this;
        }

        public Criteria andDiffPerNotIn(List<BigDecimal> values) {
            addCriterion("diff_per not in", values, "diffPer");
            return (Criteria) this;
        }

        public Criteria andDiffPerBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("diff_per between", value1, value2, "diffPer");
            return (Criteria) this;
        }

        public Criteria andDiffPerNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("diff_per not between", value1, value2, "diffPer");
            return (Criteria) this;
        }

        public Criteria andSuggestSetIsNull() {
            addCriterion("suggest_set is null");
            return (Criteria) this;
        }

        public Criteria andSuggestSetIsNotNull() {
            addCriterion("suggest_set is not null");
            return (Criteria) this;
        }

        public Criteria andSuggestSetEqualTo(String value) {
            addCriterion("suggest_set =", value, "suggestSet");
            return (Criteria) this;
        }

        public Criteria andSuggestSetNotEqualTo(String value) {
            addCriterion("suggest_set <>", value, "suggestSet");
            return (Criteria) this;
        }

        public Criteria andSuggestSetGreaterThan(String value) {
            addCriterion("suggest_set >", value, "suggestSet");
            return (Criteria) this;
        }

        public Criteria andSuggestSetGreaterThanOrEqualTo(String value) {
            addCriterion("suggest_set >=", value, "suggestSet");
            return (Criteria) this;
        }

        public Criteria andSuggestSetLessThan(String value) {
            addCriterion("suggest_set <", value, "suggestSet");
            return (Criteria) this;
        }

        public Criteria andSuggestSetLessThanOrEqualTo(String value) {
            addCriterion("suggest_set <=", value, "suggestSet");
            return (Criteria) this;
        }

        public Criteria andSuggestSetLike(String value) {
            addCriterion("suggest_set like", value, "suggestSet");
            return (Criteria) this;
        }

        public Criteria andSuggestSetNotLike(String value) {
            addCriterion("suggest_set not like", value, "suggestSet");
            return (Criteria) this;
        }

        public Criteria andSuggestSetIn(List<String> values) {
            addCriterion("suggest_set in", values, "suggestSet");
            return (Criteria) this;
        }

        public Criteria andSuggestSetNotIn(List<String> values) {
            addCriterion("suggest_set not in", values, "suggestSet");
            return (Criteria) this;
        }

        public Criteria andSuggestSetBetween(String value1, String value2) {
            addCriterion("suggest_set between", value1, value2, "suggestSet");
            return (Criteria) this;
        }

        public Criteria andSuggestSetNotBetween(String value1, String value2) {
            addCriterion("suggest_set not between", value1, value2, "suggestSet");
            return (Criteria) this;
        }

        public Criteria andCreatedtimeIsNull() {
            addCriterion("createdtime is null");
            return (Criteria) this;
        }

        public Criteria andCreatedtimeIsNotNull() {
            addCriterion("createdtime is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedtimeEqualTo(Date value) {
            addCriterion("createdtime =", value, "createdtime");
            return (Criteria) this;
        }

        public Criteria andCreatedtimeNotEqualTo(Date value) {
            addCriterion("createdtime <>", value, "createdtime");
            return (Criteria) this;
        }

        public Criteria andCreatedtimeGreaterThan(Date value) {
            addCriterion("createdtime >", value, "createdtime");
            return (Criteria) this;
        }

        public Criteria andCreatedtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("createdtime >=", value, "createdtime");
            return (Criteria) this;
        }

        public Criteria andCreatedtimeLessThan(Date value) {
            addCriterion("createdtime <", value, "createdtime");
            return (Criteria) this;
        }

        public Criteria andCreatedtimeLessThanOrEqualTo(Date value) {
            addCriterion("createdtime <=", value, "createdtime");
            return (Criteria) this;
        }

        public Criteria andCreatedtimeIn(List<Date> values) {
            addCriterion("createdtime in", values, "createdtime");
            return (Criteria) this;
        }

        public Criteria andCreatedtimeNotIn(List<Date> values) {
            addCriterion("createdtime not in", values, "createdtime");
            return (Criteria) this;
        }

        public Criteria andCreatedtimeBetween(Date value1, Date value2) {
            addCriterion("createdtime between", value1, value2, "createdtime");
            return (Criteria) this;
        }

        public Criteria andCreatedtimeNotBetween(Date value1, Date value2) {
            addCriterion("createdtime not between", value1, value2, "createdtime");
            return (Criteria) this;
        }

        public Criteria andPkStudentLikeInsensitive(String value) {
            addCriterion("upper(pk_student) like", value.toUpperCase(), "pkStudent");
            return (Criteria) this;
        }

        public Criteria andNutritionIdLikeInsensitive(String value) {
            addCriterion("upper(nutrition_id) like", value.toUpperCase(), "nutritionId");
            return (Criteria) this;
        }

        public Criteria andCalcYearLikeInsensitive(String value) {
            addCriterion("upper(calc_year) like", value.toUpperCase(), "calcYear");
            return (Criteria) this;
        }

        public Criteria andSuggestSetLikeInsensitive(String value) {
            addCriterion("upper(suggest_set) like", value.toUpperCase(), "suggestSet");
            return (Criteria) this;
        }
    }

    /**
     * TABLE student_nutrition_analysis
     * @mbg.generated MyBatis Generator 自动生成Bean，自定义属性会覆盖
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * TABLE student_nutrition_analysis
     * @mbg.generated
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}