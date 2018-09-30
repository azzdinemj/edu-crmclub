package com.wuxue.model.junhwa;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ParentConfirmExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer startIndex;

    protected Integer pageSize = 20;

    protected Integer pageNo;

    public ParentConfirmExample() {
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
     * TABLE parentconfirm
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

        public Criteria andPkConfirmIsNull() {
            addCriterion("pk_confirm is null");
            return (Criteria) this;
        }

        public Criteria andPkConfirmIsNotNull() {
            addCriterion("pk_confirm is not null");
            return (Criteria) this;
        }

        public Criteria andPkConfirmEqualTo(String value) {
            addCriterion("pk_confirm =", value, "pkConfirm");
            return (Criteria) this;
        }

        public Criteria andPkConfirmNotEqualTo(String value) {
            addCriterion("pk_confirm <>", value, "pkConfirm");
            return (Criteria) this;
        }

        public Criteria andPkConfirmGreaterThan(String value) {
            addCriterion("pk_confirm >", value, "pkConfirm");
            return (Criteria) this;
        }

        public Criteria andPkConfirmGreaterThanOrEqualTo(String value) {
            addCriterion("pk_confirm >=", value, "pkConfirm");
            return (Criteria) this;
        }

        public Criteria andPkConfirmLessThan(String value) {
            addCriterion("pk_confirm <", value, "pkConfirm");
            return (Criteria) this;
        }

        public Criteria andPkConfirmLessThanOrEqualTo(String value) {
            addCriterion("pk_confirm <=", value, "pkConfirm");
            return (Criteria) this;
        }

        public Criteria andPkConfirmLike(String value) {
            addCriterion("pk_confirm like", value, "pkConfirm");
            return (Criteria) this;
        }

        public Criteria andPkConfirmNotLike(String value) {
            addCriterion("pk_confirm not like", value, "pkConfirm");
            return (Criteria) this;
        }

        public Criteria andPkConfirmIn(List<String> values) {
            addCriterion("pk_confirm in", values, "pkConfirm");
            return (Criteria) this;
        }

        public Criteria andPkConfirmNotIn(List<String> values) {
            addCriterion("pk_confirm not in", values, "pkConfirm");
            return (Criteria) this;
        }

        public Criteria andPkConfirmBetween(String value1, String value2) {
            addCriterion("pk_confirm between", value1, value2, "pkConfirm");
            return (Criteria) this;
        }

        public Criteria andPkConfirmNotBetween(String value1, String value2) {
            addCriterion("pk_confirm not between", value1, value2, "pkConfirm");
            return (Criteria) this;
        }

        public Criteria andPkLinkmanIsNull() {
            addCriterion("pk_linkman is null");
            return (Criteria) this;
        }

        public Criteria andPkLinkmanIsNotNull() {
            addCriterion("pk_linkman is not null");
            return (Criteria) this;
        }

        public Criteria andPkLinkmanEqualTo(String value) {
            addCriterion("pk_linkman =", value, "pkLinkman");
            return (Criteria) this;
        }

        public Criteria andPkLinkmanNotEqualTo(String value) {
            addCriterion("pk_linkman <>", value, "pkLinkman");
            return (Criteria) this;
        }

        public Criteria andPkLinkmanGreaterThan(String value) {
            addCriterion("pk_linkman >", value, "pkLinkman");
            return (Criteria) this;
        }

        public Criteria andPkLinkmanGreaterThanOrEqualTo(String value) {
            addCriterion("pk_linkman >=", value, "pkLinkman");
            return (Criteria) this;
        }

        public Criteria andPkLinkmanLessThan(String value) {
            addCriterion("pk_linkman <", value, "pkLinkman");
            return (Criteria) this;
        }

        public Criteria andPkLinkmanLessThanOrEqualTo(String value) {
            addCriterion("pk_linkman <=", value, "pkLinkman");
            return (Criteria) this;
        }

        public Criteria andPkLinkmanLike(String value) {
            addCriterion("pk_linkman like", value, "pkLinkman");
            return (Criteria) this;
        }

        public Criteria andPkLinkmanNotLike(String value) {
            addCriterion("pk_linkman not like", value, "pkLinkman");
            return (Criteria) this;
        }

        public Criteria andPkLinkmanIn(List<String> values) {
            addCriterion("pk_linkman in", values, "pkLinkman");
            return (Criteria) this;
        }

        public Criteria andPkLinkmanNotIn(List<String> values) {
            addCriterion("pk_linkman not in", values, "pkLinkman");
            return (Criteria) this;
        }

        public Criteria andPkLinkmanBetween(String value1, String value2) {
            addCriterion("pk_linkman between", value1, value2, "pkLinkman");
            return (Criteria) this;
        }

        public Criteria andPkLinkmanNotBetween(String value1, String value2) {
            addCriterion("pk_linkman not between", value1, value2, "pkLinkman");
            return (Criteria) this;
        }

        public Criteria andPkDebusRecordIsNull() {
            addCriterion("pk_debus_record is null");
            return (Criteria) this;
        }

        public Criteria andPkDebusRecordIsNotNull() {
            addCriterion("pk_debus_record is not null");
            return (Criteria) this;
        }

        public Criteria andPkDebusRecordEqualTo(String value) {
            addCriterion("pk_debus_record =", value, "pkDebusRecord");
            return (Criteria) this;
        }

        public Criteria andPkDebusRecordNotEqualTo(String value) {
            addCriterion("pk_debus_record <>", value, "pkDebusRecord");
            return (Criteria) this;
        }

        public Criteria andPkDebusRecordGreaterThan(String value) {
            addCriterion("pk_debus_record >", value, "pkDebusRecord");
            return (Criteria) this;
        }

        public Criteria andPkDebusRecordGreaterThanOrEqualTo(String value) {
            addCriterion("pk_debus_record >=", value, "pkDebusRecord");
            return (Criteria) this;
        }

        public Criteria andPkDebusRecordLessThan(String value) {
            addCriterion("pk_debus_record <", value, "pkDebusRecord");
            return (Criteria) this;
        }

        public Criteria andPkDebusRecordLessThanOrEqualTo(String value) {
            addCriterion("pk_debus_record <=", value, "pkDebusRecord");
            return (Criteria) this;
        }

        public Criteria andPkDebusRecordLike(String value) {
            addCriterion("pk_debus_record like", value, "pkDebusRecord");
            return (Criteria) this;
        }

        public Criteria andPkDebusRecordNotLike(String value) {
            addCriterion("pk_debus_record not like", value, "pkDebusRecord");
            return (Criteria) this;
        }

        public Criteria andPkDebusRecordIn(List<String> values) {
            addCriterion("pk_debus_record in", values, "pkDebusRecord");
            return (Criteria) this;
        }

        public Criteria andPkDebusRecordNotIn(List<String> values) {
            addCriterion("pk_debus_record not in", values, "pkDebusRecord");
            return (Criteria) this;
        }

        public Criteria andPkDebusRecordBetween(String value1, String value2) {
            addCriterion("pk_debus_record between", value1, value2, "pkDebusRecord");
            return (Criteria) this;
        }

        public Criteria andPkDebusRecordNotBetween(String value1, String value2) {
            addCriterion("pk_debus_record not between", value1, value2, "pkDebusRecord");
            return (Criteria) this;
        }

        public Criteria andPkSchoolBusIsNull() {
            addCriterion("pk_school_bus is null");
            return (Criteria) this;
        }

        public Criteria andPkSchoolBusIsNotNull() {
            addCriterion("pk_school_bus is not null");
            return (Criteria) this;
        }

        public Criteria andPkSchoolBusEqualTo(String value) {
            addCriterion("pk_school_bus =", value, "pkSchoolBus");
            return (Criteria) this;
        }

        public Criteria andPkSchoolBusNotEqualTo(String value) {
            addCriterion("pk_school_bus <>", value, "pkSchoolBus");
            return (Criteria) this;
        }

        public Criteria andPkSchoolBusGreaterThan(String value) {
            addCriterion("pk_school_bus >", value, "pkSchoolBus");
            return (Criteria) this;
        }

        public Criteria andPkSchoolBusGreaterThanOrEqualTo(String value) {
            addCriterion("pk_school_bus >=", value, "pkSchoolBus");
            return (Criteria) this;
        }

        public Criteria andPkSchoolBusLessThan(String value) {
            addCriterion("pk_school_bus <", value, "pkSchoolBus");
            return (Criteria) this;
        }

        public Criteria andPkSchoolBusLessThanOrEqualTo(String value) {
            addCriterion("pk_school_bus <=", value, "pkSchoolBus");
            return (Criteria) this;
        }

        public Criteria andPkSchoolBusLike(String value) {
            addCriterion("pk_school_bus like", value, "pkSchoolBus");
            return (Criteria) this;
        }

        public Criteria andPkSchoolBusNotLike(String value) {
            addCriterion("pk_school_bus not like", value, "pkSchoolBus");
            return (Criteria) this;
        }

        public Criteria andPkSchoolBusIn(List<String> values) {
            addCriterion("pk_school_bus in", values, "pkSchoolBus");
            return (Criteria) this;
        }

        public Criteria andPkSchoolBusNotIn(List<String> values) {
            addCriterion("pk_school_bus not in", values, "pkSchoolBus");
            return (Criteria) this;
        }

        public Criteria andPkSchoolBusBetween(String value1, String value2) {
            addCriterion("pk_school_bus between", value1, value2, "pkSchoolBus");
            return (Criteria) this;
        }

        public Criteria andPkSchoolBusNotBetween(String value1, String value2) {
            addCriterion("pk_school_bus not between", value1, value2, "pkSchoolBus");
            return (Criteria) this;
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

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andCreationDateIsNull() {
            addCriterion("creation_date is null");
            return (Criteria) this;
        }

        public Criteria andCreationDateIsNotNull() {
            addCriterion("creation_date is not null");
            return (Criteria) this;
        }

        public Criteria andCreationDateEqualTo(Date value) {
            addCriterion("creation_date =", value, "creationDate");
            return (Criteria) this;
        }

        public Criteria andCreationDateNotEqualTo(Date value) {
            addCriterion("creation_date <>", value, "creationDate");
            return (Criteria) this;
        }

        public Criteria andCreationDateGreaterThan(Date value) {
            addCriterion("creation_date >", value, "creationDate");
            return (Criteria) this;
        }

        public Criteria andCreationDateGreaterThanOrEqualTo(Date value) {
            addCriterion("creation_date >=", value, "creationDate");
            return (Criteria) this;
        }

        public Criteria andCreationDateLessThan(Date value) {
            addCriterion("creation_date <", value, "creationDate");
            return (Criteria) this;
        }

        public Criteria andCreationDateLessThanOrEqualTo(Date value) {
            addCriterion("creation_date <=", value, "creationDate");
            return (Criteria) this;
        }

        public Criteria andCreationDateIn(List<Date> values) {
            addCriterion("creation_date in", values, "creationDate");
            return (Criteria) this;
        }

        public Criteria andCreationDateNotIn(List<Date> values) {
            addCriterion("creation_date not in", values, "creationDate");
            return (Criteria) this;
        }

        public Criteria andCreationDateBetween(Date value1, Date value2) {
            addCriterion("creation_date between", value1, value2, "creationDate");
            return (Criteria) this;
        }

        public Criteria andCreationDateNotBetween(Date value1, Date value2) {
            addCriterion("creation_date not between", value1, value2, "creationDate");
            return (Criteria) this;
        }

        public Criteria andPkConfirmLikeInsensitive(String value) {
            addCriterion("upper(pk_confirm) like", value.toUpperCase(), "pkConfirm");
            return (Criteria) this;
        }

        public Criteria andPkLinkmanLikeInsensitive(String value) {
            addCriterion("upper(pk_linkman) like", value.toUpperCase(), "pkLinkman");
            return (Criteria) this;
        }

        public Criteria andPkDebusRecordLikeInsensitive(String value) {
            addCriterion("upper(pk_debus_record) like", value.toUpperCase(), "pkDebusRecord");
            return (Criteria) this;
        }

        public Criteria andPkSchoolBusLikeInsensitive(String value) {
            addCriterion("upper(pk_school_bus) like", value.toUpperCase(), "pkSchoolBus");
            return (Criteria) this;
        }

        public Criteria andPkStudentLikeInsensitive(String value) {
            addCriterion("upper(pk_student) like", value.toUpperCase(), "pkStudent");
            return (Criteria) this;
        }
    }

    /**
     * TABLE parentconfirm
     * @mbg.generated MyBatis Generator 自动生成Bean，自定义属性会覆盖
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * TABLE parentconfirm
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