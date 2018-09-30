package com.wuxue.model.junhwa;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ShuttleTimeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer startIndex;

    protected Integer pageSize = 20;

    protected Integer pageNo;

    public ShuttleTimeExample() {
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
     * TABLE shuttle_time
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

        public Criteria andPkShuttleTimeIsNull() {
            addCriterion("pk_shuttle_time is null");
            return (Criteria) this;
        }

        public Criteria andPkShuttleTimeIsNotNull() {
            addCriterion("pk_shuttle_time is not null");
            return (Criteria) this;
        }

        public Criteria andPkShuttleTimeEqualTo(String value) {
            addCriterion("pk_shuttle_time =", value, "pkShuttleTime");
            return (Criteria) this;
        }

        public Criteria andPkShuttleTimeNotEqualTo(String value) {
            addCriterion("pk_shuttle_time <>", value, "pkShuttleTime");
            return (Criteria) this;
        }

        public Criteria andPkShuttleTimeGreaterThan(String value) {
            addCriterion("pk_shuttle_time >", value, "pkShuttleTime");
            return (Criteria) this;
        }

        public Criteria andPkShuttleTimeGreaterThanOrEqualTo(String value) {
            addCriterion("pk_shuttle_time >=", value, "pkShuttleTime");
            return (Criteria) this;
        }

        public Criteria andPkShuttleTimeLessThan(String value) {
            addCriterion("pk_shuttle_time <", value, "pkShuttleTime");
            return (Criteria) this;
        }

        public Criteria andPkShuttleTimeLessThanOrEqualTo(String value) {
            addCriterion("pk_shuttle_time <=", value, "pkShuttleTime");
            return (Criteria) this;
        }

        public Criteria andPkShuttleTimeLike(String value) {
            addCriterion("pk_shuttle_time like", value, "pkShuttleTime");
            return (Criteria) this;
        }

        public Criteria andPkShuttleTimeNotLike(String value) {
            addCriterion("pk_shuttle_time not like", value, "pkShuttleTime");
            return (Criteria) this;
        }

        public Criteria andPkShuttleTimeIn(List<String> values) {
            addCriterion("pk_shuttle_time in", values, "pkShuttleTime");
            return (Criteria) this;
        }

        public Criteria andPkShuttleTimeNotIn(List<String> values) {
            addCriterion("pk_shuttle_time not in", values, "pkShuttleTime");
            return (Criteria) this;
        }

        public Criteria andPkShuttleTimeBetween(String value1, String value2) {
            addCriterion("pk_shuttle_time between", value1, value2, "pkShuttleTime");
            return (Criteria) this;
        }

        public Criteria andPkShuttleTimeNotBetween(String value1, String value2) {
            addCriterion("pk_shuttle_time not between", value1, value2, "pkShuttleTime");
            return (Criteria) this;
        }

        public Criteria andPkDomainIsNull() {
            addCriterion("pk_domain is null");
            return (Criteria) this;
        }

        public Criteria andPkDomainIsNotNull() {
            addCriterion("pk_domain is not null");
            return (Criteria) this;
        }

        public Criteria andPkDomainEqualTo(String value) {
            addCriterion("pk_domain =", value, "pkDomain");
            return (Criteria) this;
        }

        public Criteria andPkDomainNotEqualTo(String value) {
            addCriterion("pk_domain <>", value, "pkDomain");
            return (Criteria) this;
        }

        public Criteria andPkDomainGreaterThan(String value) {
            addCriterion("pk_domain >", value, "pkDomain");
            return (Criteria) this;
        }

        public Criteria andPkDomainGreaterThanOrEqualTo(String value) {
            addCriterion("pk_domain >=", value, "pkDomain");
            return (Criteria) this;
        }

        public Criteria andPkDomainLessThan(String value) {
            addCriterion("pk_domain <", value, "pkDomain");
            return (Criteria) this;
        }

        public Criteria andPkDomainLessThanOrEqualTo(String value) {
            addCriterion("pk_domain <=", value, "pkDomain");
            return (Criteria) this;
        }

        public Criteria andPkDomainLike(String value) {
            addCriterion("pk_domain like", value, "pkDomain");
            return (Criteria) this;
        }

        public Criteria andPkDomainNotLike(String value) {
            addCriterion("pk_domain not like", value, "pkDomain");
            return (Criteria) this;
        }

        public Criteria andPkDomainIn(List<String> values) {
            addCriterion("pk_domain in", values, "pkDomain");
            return (Criteria) this;
        }

        public Criteria andPkDomainNotIn(List<String> values) {
            addCriterion("pk_domain not in", values, "pkDomain");
            return (Criteria) this;
        }

        public Criteria andPkDomainBetween(String value1, String value2) {
            addCriterion("pk_domain between", value1, value2, "pkDomain");
            return (Criteria) this;
        }

        public Criteria andPkDomainNotBetween(String value1, String value2) {
            addCriterion("pk_domain not between", value1, value2, "pkDomain");
            return (Criteria) this;
        }

        public Criteria andPkClassinfoIsNull() {
            addCriterion("pk_classinfo is null");
            return (Criteria) this;
        }

        public Criteria andPkClassinfoIsNotNull() {
            addCriterion("pk_classinfo is not null");
            return (Criteria) this;
        }

        public Criteria andPkClassinfoEqualTo(String value) {
            addCriterion("pk_classinfo =", value, "pkClassinfo");
            return (Criteria) this;
        }

        public Criteria andPkClassinfoNotEqualTo(String value) {
            addCriterion("pk_classinfo <>", value, "pkClassinfo");
            return (Criteria) this;
        }

        public Criteria andPkClassinfoGreaterThan(String value) {
            addCriterion("pk_classinfo >", value, "pkClassinfo");
            return (Criteria) this;
        }

        public Criteria andPkClassinfoGreaterThanOrEqualTo(String value) {
            addCriterion("pk_classinfo >=", value, "pkClassinfo");
            return (Criteria) this;
        }

        public Criteria andPkClassinfoLessThan(String value) {
            addCriterion("pk_classinfo <", value, "pkClassinfo");
            return (Criteria) this;
        }

        public Criteria andPkClassinfoLessThanOrEqualTo(String value) {
            addCriterion("pk_classinfo <=", value, "pkClassinfo");
            return (Criteria) this;
        }

        public Criteria andPkClassinfoLike(String value) {
            addCriterion("pk_classinfo like", value, "pkClassinfo");
            return (Criteria) this;
        }

        public Criteria andPkClassinfoNotLike(String value) {
            addCriterion("pk_classinfo not like", value, "pkClassinfo");
            return (Criteria) this;
        }

        public Criteria andPkClassinfoIn(List<String> values) {
            addCriterion("pk_classinfo in", values, "pkClassinfo");
            return (Criteria) this;
        }

        public Criteria andPkClassinfoNotIn(List<String> values) {
            addCriterion("pk_classinfo not in", values, "pkClassinfo");
            return (Criteria) this;
        }

        public Criteria andPkClassinfoBetween(String value1, String value2) {
            addCriterion("pk_classinfo between", value1, value2, "pkClassinfo");
            return (Criteria) this;
        }

        public Criteria andPkClassinfoNotBetween(String value1, String value2) {
            addCriterion("pk_classinfo not between", value1, value2, "pkClassinfo");
            return (Criteria) this;
        }

        public Criteria andDateTypeIsNull() {
            addCriterion("date_type is null");
            return (Criteria) this;
        }

        public Criteria andDateTypeIsNotNull() {
            addCriterion("date_type is not null");
            return (Criteria) this;
        }

        public Criteria andDateTypeEqualTo(Integer value) {
            addCriterion("date_type =", value, "dateType");
            return (Criteria) this;
        }

        public Criteria andDateTypeNotEqualTo(Integer value) {
            addCriterion("date_type <>", value, "dateType");
            return (Criteria) this;
        }

        public Criteria andDateTypeGreaterThan(Integer value) {
            addCriterion("date_type >", value, "dateType");
            return (Criteria) this;
        }

        public Criteria andDateTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("date_type >=", value, "dateType");
            return (Criteria) this;
        }

        public Criteria andDateTypeLessThan(Integer value) {
            addCriterion("date_type <", value, "dateType");
            return (Criteria) this;
        }

        public Criteria andDateTypeLessThanOrEqualTo(Integer value) {
            addCriterion("date_type <=", value, "dateType");
            return (Criteria) this;
        }

        public Criteria andDateTypeIn(List<Integer> values) {
            addCriterion("date_type in", values, "dateType");
            return (Criteria) this;
        }

        public Criteria andDateTypeNotIn(List<Integer> values) {
            addCriterion("date_type not in", values, "dateType");
            return (Criteria) this;
        }

        public Criteria andDateTypeBetween(Integer value1, Integer value2) {
            addCriterion("date_type between", value1, value2, "dateType");
            return (Criteria) this;
        }

        public Criteria andDateTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("date_type not between", value1, value2, "dateType");
            return (Criteria) this;
        }

        public Criteria andStartTimeIsNull() {
            addCriterion("start_time is null");
            return (Criteria) this;
        }

        public Criteria andStartTimeIsNotNull() {
            addCriterion("start_time is not null");
            return (Criteria) this;
        }

        public Criteria andStartTimeEqualTo(Date value) {
            addCriterion("start_time =", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotEqualTo(Date value) {
            addCriterion("start_time <>", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThan(Date value) {
            addCriterion("start_time >", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("start_time >=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThan(Date value) {
            addCriterion("start_time <", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThanOrEqualTo(Date value) {
            addCriterion("start_time <=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeIn(List<Date> values) {
            addCriterion("start_time in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotIn(List<Date> values) {
            addCriterion("start_time not in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeBetween(Date value1, Date value2) {
            addCriterion("start_time between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotBetween(Date value1, Date value2) {
            addCriterion("start_time not between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNull() {
            addCriterion("end_time is null");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNotNull() {
            addCriterion("end_time is not null");
            return (Criteria) this;
        }

        public Criteria andEndTimeEqualTo(Date value) {
            addCriterion("end_time =", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotEqualTo(Date value) {
            addCriterion("end_time <>", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThan(Date value) {
            addCriterion("end_time >", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("end_time >=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThan(Date value) {
            addCriterion("end_time <", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThanOrEqualTo(Date value) {
            addCriterion("end_time <=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIn(List<Date> values) {
            addCriterion("end_time in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotIn(List<Date> values) {
            addCriterion("end_time not in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeBetween(Date value1, Date value2) {
            addCriterion("end_time between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotBetween(Date value1, Date value2) {
            addCriterion("end_time not between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andShuttleTypeIsNull() {
            addCriterion("shuttle_type is null");
            return (Criteria) this;
        }

        public Criteria andShuttleTypeIsNotNull() {
            addCriterion("shuttle_type is not null");
            return (Criteria) this;
        }

        public Criteria andShuttleTypeEqualTo(Integer value) {
            addCriterion("shuttle_type =", value, "shuttleType");
            return (Criteria) this;
        }

        public Criteria andShuttleTypeNotEqualTo(Integer value) {
            addCriterion("shuttle_type <>", value, "shuttleType");
            return (Criteria) this;
        }

        public Criteria andShuttleTypeGreaterThan(Integer value) {
            addCriterion("shuttle_type >", value, "shuttleType");
            return (Criteria) this;
        }

        public Criteria andShuttleTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("shuttle_type >=", value, "shuttleType");
            return (Criteria) this;
        }

        public Criteria andShuttleTypeLessThan(Integer value) {
            addCriterion("shuttle_type <", value, "shuttleType");
            return (Criteria) this;
        }

        public Criteria andShuttleTypeLessThanOrEqualTo(Integer value) {
            addCriterion("shuttle_type <=", value, "shuttleType");
            return (Criteria) this;
        }

        public Criteria andShuttleTypeIn(List<Integer> values) {
            addCriterion("shuttle_type in", values, "shuttleType");
            return (Criteria) this;
        }

        public Criteria andShuttleTypeNotIn(List<Integer> values) {
            addCriterion("shuttle_type not in", values, "shuttleType");
            return (Criteria) this;
        }

        public Criteria andShuttleTypeBetween(Integer value1, Integer value2) {
            addCriterion("shuttle_type between", value1, value2, "shuttleType");
            return (Criteria) this;
        }

        public Criteria andShuttleTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("shuttle_type not between", value1, value2, "shuttleType");
            return (Criteria) this;
        }

        public Criteria andPkShuttleTimeLikeInsensitive(String value) {
            addCriterion("upper(pk_shuttle_time) like", value.toUpperCase(), "pkShuttleTime");
            return (Criteria) this;
        }

        public Criteria andPkDomainLikeInsensitive(String value) {
            addCriterion("upper(pk_domain) like", value.toUpperCase(), "pkDomain");
            return (Criteria) this;
        }

        public Criteria andPkClassinfoLikeInsensitive(String value) {
            addCriterion("upper(pk_classinfo) like", value.toUpperCase(), "pkClassinfo");
            return (Criteria) this;
        }
    }

    /**
     * TABLE shuttle_time
     * @mbg.generated MyBatis Generator 自动生成Bean，自定义属性会覆盖
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * TABLE shuttle_time
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