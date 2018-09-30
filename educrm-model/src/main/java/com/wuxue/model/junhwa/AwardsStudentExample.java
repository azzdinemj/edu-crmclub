package com.wuxue.model.junhwa;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AwardsStudentExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer startIndex;

    protected Integer pageSize = 20;

    protected Integer pageNo;

    public AwardsStudentExample() {
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
     * TABLE t_awards_student
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

        public Criteria andAwardsStudentIdIsNull() {
            addCriterion("awards_student_id is null");
            return (Criteria) this;
        }

        public Criteria andAwardsStudentIdIsNotNull() {
            addCriterion("awards_student_id is not null");
            return (Criteria) this;
        }

        public Criteria andAwardsStudentIdEqualTo(String value) {
            addCriterion("awards_student_id =", value, "awardsStudentId");
            return (Criteria) this;
        }

        public Criteria andAwardsStudentIdNotEqualTo(String value) {
            addCriterion("awards_student_id <>", value, "awardsStudentId");
            return (Criteria) this;
        }

        public Criteria andAwardsStudentIdGreaterThan(String value) {
            addCriterion("awards_student_id >", value, "awardsStudentId");
            return (Criteria) this;
        }

        public Criteria andAwardsStudentIdGreaterThanOrEqualTo(String value) {
            addCriterion("awards_student_id >=", value, "awardsStudentId");
            return (Criteria) this;
        }

        public Criteria andAwardsStudentIdLessThan(String value) {
            addCriterion("awards_student_id <", value, "awardsStudentId");
            return (Criteria) this;
        }

        public Criteria andAwardsStudentIdLessThanOrEqualTo(String value) {
            addCriterion("awards_student_id <=", value, "awardsStudentId");
            return (Criteria) this;
        }

        public Criteria andAwardsStudentIdLike(String value) {
            addCriterion("awards_student_id like", value, "awardsStudentId");
            return (Criteria) this;
        }

        public Criteria andAwardsStudentIdNotLike(String value) {
            addCriterion("awards_student_id not like", value, "awardsStudentId");
            return (Criteria) this;
        }

        public Criteria andAwardsStudentIdIn(List<String> values) {
            addCriterion("awards_student_id in", values, "awardsStudentId");
            return (Criteria) this;
        }

        public Criteria andAwardsStudentIdNotIn(List<String> values) {
            addCriterion("awards_student_id not in", values, "awardsStudentId");
            return (Criteria) this;
        }

        public Criteria andAwardsStudentIdBetween(String value1, String value2) {
            addCriterion("awards_student_id between", value1, value2, "awardsStudentId");
            return (Criteria) this;
        }

        public Criteria andAwardsStudentIdNotBetween(String value1, String value2) {
            addCriterion("awards_student_id not between", value1, value2, "awardsStudentId");
            return (Criteria) this;
        }

        public Criteria andAwardsIdIsNull() {
            addCriterion("awards_id is null");
            return (Criteria) this;
        }

        public Criteria andAwardsIdIsNotNull() {
            addCriterion("awards_id is not null");
            return (Criteria) this;
        }

        public Criteria andAwardsIdEqualTo(String value) {
            addCriterion("awards_id =", value, "awardsId");
            return (Criteria) this;
        }

        public Criteria andAwardsIdNotEqualTo(String value) {
            addCriterion("awards_id <>", value, "awardsId");
            return (Criteria) this;
        }

        public Criteria andAwardsIdGreaterThan(String value) {
            addCriterion("awards_id >", value, "awardsId");
            return (Criteria) this;
        }

        public Criteria andAwardsIdGreaterThanOrEqualTo(String value) {
            addCriterion("awards_id >=", value, "awardsId");
            return (Criteria) this;
        }

        public Criteria andAwardsIdLessThan(String value) {
            addCriterion("awards_id <", value, "awardsId");
            return (Criteria) this;
        }

        public Criteria andAwardsIdLessThanOrEqualTo(String value) {
            addCriterion("awards_id <=", value, "awardsId");
            return (Criteria) this;
        }

        public Criteria andAwardsIdLike(String value) {
            addCriterion("awards_id like", value, "awardsId");
            return (Criteria) this;
        }

        public Criteria andAwardsIdNotLike(String value) {
            addCriterion("awards_id not like", value, "awardsId");
            return (Criteria) this;
        }

        public Criteria andAwardsIdIn(List<String> values) {
            addCriterion("awards_id in", values, "awardsId");
            return (Criteria) this;
        }

        public Criteria andAwardsIdNotIn(List<String> values) {
            addCriterion("awards_id not in", values, "awardsId");
            return (Criteria) this;
        }

        public Criteria andAwardsIdBetween(String value1, String value2) {
            addCriterion("awards_id between", value1, value2, "awardsId");
            return (Criteria) this;
        }

        public Criteria andAwardsIdNotBetween(String value1, String value2) {
            addCriterion("awards_id not between", value1, value2, "awardsId");
            return (Criteria) this;
        }

        public Criteria andStudentIdIsNull() {
            addCriterion("student_id is null");
            return (Criteria) this;
        }

        public Criteria andStudentIdIsNotNull() {
            addCriterion("student_id is not null");
            return (Criteria) this;
        }

        public Criteria andStudentIdEqualTo(String value) {
            addCriterion("student_id =", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdNotEqualTo(String value) {
            addCriterion("student_id <>", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdGreaterThan(String value) {
            addCriterion("student_id >", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdGreaterThanOrEqualTo(String value) {
            addCriterion("student_id >=", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdLessThan(String value) {
            addCriterion("student_id <", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdLessThanOrEqualTo(String value) {
            addCriterion("student_id <=", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdLike(String value) {
            addCriterion("student_id like", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdNotLike(String value) {
            addCriterion("student_id not like", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdIn(List<String> values) {
            addCriterion("student_id in", values, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdNotIn(List<String> values) {
            addCriterion("student_id not in", values, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdBetween(String value1, String value2) {
            addCriterion("student_id between", value1, value2, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdNotBetween(String value1, String value2) {
            addCriterion("student_id not between", value1, value2, "studentId");
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

        public Criteria andCreatorIsNull() {
            addCriterion("creator is null");
            return (Criteria) this;
        }

        public Criteria andCreatorIsNotNull() {
            addCriterion("creator is not null");
            return (Criteria) this;
        }

        public Criteria andCreatorEqualTo(String value) {
            addCriterion("creator =", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotEqualTo(String value) {
            addCriterion("creator <>", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorGreaterThan(String value) {
            addCriterion("creator >", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorGreaterThanOrEqualTo(String value) {
            addCriterion("creator >=", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLessThan(String value) {
            addCriterion("creator <", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLessThanOrEqualTo(String value) {
            addCriterion("creator <=", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLike(String value) {
            addCriterion("creator like", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotLike(String value) {
            addCriterion("creator not like", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorIn(List<String> values) {
            addCriterion("creator in", values, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotIn(List<String> values) {
            addCriterion("creator not in", values, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorBetween(String value1, String value2) {
            addCriterion("creator between", value1, value2, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotBetween(String value1, String value2) {
            addCriterion("creator not between", value1, value2, "creator");
            return (Criteria) this;
        }

        public Criteria andLasteditDateIsNull() {
            addCriterion("lastedit_date is null");
            return (Criteria) this;
        }

        public Criteria andLasteditDateIsNotNull() {
            addCriterion("lastedit_date is not null");
            return (Criteria) this;
        }

        public Criteria andLasteditDateEqualTo(Date value) {
            addCriterion("lastedit_date =", value, "lasteditDate");
            return (Criteria) this;
        }

        public Criteria andLasteditDateNotEqualTo(Date value) {
            addCriterion("lastedit_date <>", value, "lasteditDate");
            return (Criteria) this;
        }

        public Criteria andLasteditDateGreaterThan(Date value) {
            addCriterion("lastedit_date >", value, "lasteditDate");
            return (Criteria) this;
        }

        public Criteria andLasteditDateGreaterThanOrEqualTo(Date value) {
            addCriterion("lastedit_date >=", value, "lasteditDate");
            return (Criteria) this;
        }

        public Criteria andLasteditDateLessThan(Date value) {
            addCriterion("lastedit_date <", value, "lasteditDate");
            return (Criteria) this;
        }

        public Criteria andLasteditDateLessThanOrEqualTo(Date value) {
            addCriterion("lastedit_date <=", value, "lasteditDate");
            return (Criteria) this;
        }

        public Criteria andLasteditDateIn(List<Date> values) {
            addCriterion("lastedit_date in", values, "lasteditDate");
            return (Criteria) this;
        }

        public Criteria andLasteditDateNotIn(List<Date> values) {
            addCriterion("lastedit_date not in", values, "lasteditDate");
            return (Criteria) this;
        }

        public Criteria andLasteditDateBetween(Date value1, Date value2) {
            addCriterion("lastedit_date between", value1, value2, "lasteditDate");
            return (Criteria) this;
        }

        public Criteria andLasteditDateNotBetween(Date value1, Date value2) {
            addCriterion("lastedit_date not between", value1, value2, "lasteditDate");
            return (Criteria) this;
        }

        public Criteria andModifierIsNull() {
            addCriterion("modifier is null");
            return (Criteria) this;
        }

        public Criteria andModifierIsNotNull() {
            addCriterion("modifier is not null");
            return (Criteria) this;
        }

        public Criteria andModifierEqualTo(String value) {
            addCriterion("modifier =", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierNotEqualTo(String value) {
            addCriterion("modifier <>", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierGreaterThan(String value) {
            addCriterion("modifier >", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierGreaterThanOrEqualTo(String value) {
            addCriterion("modifier >=", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierLessThan(String value) {
            addCriterion("modifier <", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierLessThanOrEqualTo(String value) {
            addCriterion("modifier <=", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierLike(String value) {
            addCriterion("modifier like", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierNotLike(String value) {
            addCriterion("modifier not like", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierIn(List<String> values) {
            addCriterion("modifier in", values, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierNotIn(List<String> values) {
            addCriterion("modifier not in", values, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierBetween(String value1, String value2) {
            addCriterion("modifier between", value1, value2, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierNotBetween(String value1, String value2) {
            addCriterion("modifier not between", value1, value2, "modifier");
            return (Criteria) this;
        }

        public Criteria andAwardsStudentIdLikeInsensitive(String value) {
            addCriterion("upper(awards_student_id) like", value.toUpperCase(), "awardsStudentId");
            return (Criteria) this;
        }

        public Criteria andAwardsIdLikeInsensitive(String value) {
            addCriterion("upper(awards_id) like", value.toUpperCase(), "awardsId");
            return (Criteria) this;
        }

        public Criteria andStudentIdLikeInsensitive(String value) {
            addCriterion("upper(student_id) like", value.toUpperCase(), "studentId");
            return (Criteria) this;
        }

        public Criteria andCreatorLikeInsensitive(String value) {
            addCriterion("upper(creator) like", value.toUpperCase(), "creator");
            return (Criteria) this;
        }

        public Criteria andModifierLikeInsensitive(String value) {
            addCriterion("upper(modifier) like", value.toUpperCase(), "modifier");
            return (Criteria) this;
        }
    }

    /**
     * TABLE t_awards_student
     * @mbg.generated MyBatis Generator 自动生成Bean，自定义属性会覆盖
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * TABLE t_awards_student
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