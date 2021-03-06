package com.wuxue.model.junhwa;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RaceRecordExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer startIndex;

    protected Integer pageSize = 20;

    protected Integer pageNo;

    public RaceRecordExample() {
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
     * TABLE t_race_record
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

        public Criteria andRaceIdIsNull() {
            addCriterion("race_id is null");
            return (Criteria) this;
        }

        public Criteria andRaceIdIsNotNull() {
            addCriterion("race_id is not null");
            return (Criteria) this;
        }

        public Criteria andRaceIdEqualTo(String value) {
            addCriterion("race_id =", value, "raceId");
            return (Criteria) this;
        }

        public Criteria andRaceIdNotEqualTo(String value) {
            addCriterion("race_id <>", value, "raceId");
            return (Criteria) this;
        }

        public Criteria andRaceIdGreaterThan(String value) {
            addCriterion("race_id >", value, "raceId");
            return (Criteria) this;
        }

        public Criteria andRaceIdGreaterThanOrEqualTo(String value) {
            addCriterion("race_id >=", value, "raceId");
            return (Criteria) this;
        }

        public Criteria andRaceIdLessThan(String value) {
            addCriterion("race_id <", value, "raceId");
            return (Criteria) this;
        }

        public Criteria andRaceIdLessThanOrEqualTo(String value) {
            addCriterion("race_id <=", value, "raceId");
            return (Criteria) this;
        }

        public Criteria andRaceIdLike(String value) {
            addCriterion("race_id like", value, "raceId");
            return (Criteria) this;
        }

        public Criteria andRaceIdNotLike(String value) {
            addCriterion("race_id not like", value, "raceId");
            return (Criteria) this;
        }

        public Criteria andRaceIdIn(List<String> values) {
            addCriterion("race_id in", values, "raceId");
            return (Criteria) this;
        }

        public Criteria andRaceIdNotIn(List<String> values) {
            addCriterion("race_id not in", values, "raceId");
            return (Criteria) this;
        }

        public Criteria andRaceIdBetween(String value1, String value2) {
            addCriterion("race_id between", value1, value2, "raceId");
            return (Criteria) this;
        }

        public Criteria andRaceIdNotBetween(String value1, String value2) {
            addCriterion("race_id not between", value1, value2, "raceId");
            return (Criteria) this;
        }

        public Criteria andRaceNameIsNull() {
            addCriterion("race_name is null");
            return (Criteria) this;
        }

        public Criteria andRaceNameIsNotNull() {
            addCriterion("race_name is not null");
            return (Criteria) this;
        }

        public Criteria andRaceNameEqualTo(String value) {
            addCriterion("race_name =", value, "raceName");
            return (Criteria) this;
        }

        public Criteria andRaceNameNotEqualTo(String value) {
            addCriterion("race_name <>", value, "raceName");
            return (Criteria) this;
        }

        public Criteria andRaceNameGreaterThan(String value) {
            addCriterion("race_name >", value, "raceName");
            return (Criteria) this;
        }

        public Criteria andRaceNameGreaterThanOrEqualTo(String value) {
            addCriterion("race_name >=", value, "raceName");
            return (Criteria) this;
        }

        public Criteria andRaceNameLessThan(String value) {
            addCriterion("race_name <", value, "raceName");
            return (Criteria) this;
        }

        public Criteria andRaceNameLessThanOrEqualTo(String value) {
            addCriterion("race_name <=", value, "raceName");
            return (Criteria) this;
        }

        public Criteria andRaceNameLike(String value) {
            addCriterion("race_name like", value, "raceName");
            return (Criteria) this;
        }

        public Criteria andRaceNameNotLike(String value) {
            addCriterion("race_name not like", value, "raceName");
            return (Criteria) this;
        }

        public Criteria andRaceNameIn(List<String> values) {
            addCriterion("race_name in", values, "raceName");
            return (Criteria) this;
        }

        public Criteria andRaceNameNotIn(List<String> values) {
            addCriterion("race_name not in", values, "raceName");
            return (Criteria) this;
        }

        public Criteria andRaceNameBetween(String value1, String value2) {
            addCriterion("race_name between", value1, value2, "raceName");
            return (Criteria) this;
        }

        public Criteria andRaceNameNotBetween(String value1, String value2) {
            addCriterion("race_name not between", value1, value2, "raceName");
            return (Criteria) this;
        }

        public Criteria andSponsorIsNull() {
            addCriterion("sponsor is null");
            return (Criteria) this;
        }

        public Criteria andSponsorIsNotNull() {
            addCriterion("sponsor is not null");
            return (Criteria) this;
        }

        public Criteria andSponsorEqualTo(String value) {
            addCriterion("sponsor =", value, "sponsor");
            return (Criteria) this;
        }

        public Criteria andSponsorNotEqualTo(String value) {
            addCriterion("sponsor <>", value, "sponsor");
            return (Criteria) this;
        }

        public Criteria andSponsorGreaterThan(String value) {
            addCriterion("sponsor >", value, "sponsor");
            return (Criteria) this;
        }

        public Criteria andSponsorGreaterThanOrEqualTo(String value) {
            addCriterion("sponsor >=", value, "sponsor");
            return (Criteria) this;
        }

        public Criteria andSponsorLessThan(String value) {
            addCriterion("sponsor <", value, "sponsor");
            return (Criteria) this;
        }

        public Criteria andSponsorLessThanOrEqualTo(String value) {
            addCriterion("sponsor <=", value, "sponsor");
            return (Criteria) this;
        }

        public Criteria andSponsorLike(String value) {
            addCriterion("sponsor like", value, "sponsor");
            return (Criteria) this;
        }

        public Criteria andSponsorNotLike(String value) {
            addCriterion("sponsor not like", value, "sponsor");
            return (Criteria) this;
        }

        public Criteria andSponsorIn(List<String> values) {
            addCriterion("sponsor in", values, "sponsor");
            return (Criteria) this;
        }

        public Criteria andSponsorNotIn(List<String> values) {
            addCriterion("sponsor not in", values, "sponsor");
            return (Criteria) this;
        }

        public Criteria andSponsorBetween(String value1, String value2) {
            addCriterion("sponsor between", value1, value2, "sponsor");
            return (Criteria) this;
        }

        public Criteria andSponsorNotBetween(String value1, String value2) {
            addCriterion("sponsor not between", value1, value2, "sponsor");
            return (Criteria) this;
        }

        public Criteria andRaceDateIsNull() {
            addCriterion("race_date is null");
            return (Criteria) this;
        }

        public Criteria andRaceDateIsNotNull() {
            addCriterion("race_date is not null");
            return (Criteria) this;
        }

        public Criteria andRaceDateEqualTo(Date value) {
            addCriterion("race_date =", value, "raceDate");
            return (Criteria) this;
        }

        public Criteria andRaceDateNotEqualTo(Date value) {
            addCriterion("race_date <>", value, "raceDate");
            return (Criteria) this;
        }

        public Criteria andRaceDateGreaterThan(Date value) {
            addCriterion("race_date >", value, "raceDate");
            return (Criteria) this;
        }

        public Criteria andRaceDateGreaterThanOrEqualTo(Date value) {
            addCriterion("race_date >=", value, "raceDate");
            return (Criteria) this;
        }

        public Criteria andRaceDateLessThan(Date value) {
            addCriterion("race_date <", value, "raceDate");
            return (Criteria) this;
        }

        public Criteria andRaceDateLessThanOrEqualTo(Date value) {
            addCriterion("race_date <=", value, "raceDate");
            return (Criteria) this;
        }

        public Criteria andRaceDateIn(List<Date> values) {
            addCriterion("race_date in", values, "raceDate");
            return (Criteria) this;
        }

        public Criteria andRaceDateNotIn(List<Date> values) {
            addCriterion("race_date not in", values, "raceDate");
            return (Criteria) this;
        }

        public Criteria andRaceDateBetween(Date value1, Date value2) {
            addCriterion("race_date between", value1, value2, "raceDate");
            return (Criteria) this;
        }

        public Criteria andRaceDateNotBetween(Date value1, Date value2) {
            addCriterion("race_date not between", value1, value2, "raceDate");
            return (Criteria) this;
        }

        public Criteria andMaxHonorIsNull() {
            addCriterion("max_honor is null");
            return (Criteria) this;
        }

        public Criteria andMaxHonorIsNotNull() {
            addCriterion("max_honor is not null");
            return (Criteria) this;
        }

        public Criteria andMaxHonorEqualTo(String value) {
            addCriterion("max_honor =", value, "maxHonor");
            return (Criteria) this;
        }

        public Criteria andMaxHonorNotEqualTo(String value) {
            addCriterion("max_honor <>", value, "maxHonor");
            return (Criteria) this;
        }

        public Criteria andMaxHonorGreaterThan(String value) {
            addCriterion("max_honor >", value, "maxHonor");
            return (Criteria) this;
        }

        public Criteria andMaxHonorGreaterThanOrEqualTo(String value) {
            addCriterion("max_honor >=", value, "maxHonor");
            return (Criteria) this;
        }

        public Criteria andMaxHonorLessThan(String value) {
            addCriterion("max_honor <", value, "maxHonor");
            return (Criteria) this;
        }

        public Criteria andMaxHonorLessThanOrEqualTo(String value) {
            addCriterion("max_honor <=", value, "maxHonor");
            return (Criteria) this;
        }

        public Criteria andMaxHonorLike(String value) {
            addCriterion("max_honor like", value, "maxHonor");
            return (Criteria) this;
        }

        public Criteria andMaxHonorNotLike(String value) {
            addCriterion("max_honor not like", value, "maxHonor");
            return (Criteria) this;
        }

        public Criteria andMaxHonorIn(List<String> values) {
            addCriterion("max_honor in", values, "maxHonor");
            return (Criteria) this;
        }

        public Criteria andMaxHonorNotIn(List<String> values) {
            addCriterion("max_honor not in", values, "maxHonor");
            return (Criteria) this;
        }

        public Criteria andMaxHonorBetween(String value1, String value2) {
            addCriterion("max_honor between", value1, value2, "maxHonor");
            return (Criteria) this;
        }

        public Criteria andMaxHonorNotBetween(String value1, String value2) {
            addCriterion("max_honor not between", value1, value2, "maxHonor");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNull() {
            addCriterion("description is null");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNotNull() {
            addCriterion("description is not null");
            return (Criteria) this;
        }

        public Criteria andDescriptionEqualTo(String value) {
            addCriterion("description =", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotEqualTo(String value) {
            addCriterion("description <>", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThan(String value) {
            addCriterion("description >", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("description >=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThan(String value) {
            addCriterion("description <", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThanOrEqualTo(String value) {
            addCriterion("description <=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLike(String value) {
            addCriterion("description like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotLike(String value) {
            addCriterion("description not like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionIn(List<String> values) {
            addCriterion("description in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotIn(List<String> values) {
            addCriterion("description not in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionBetween(String value1, String value2) {
            addCriterion("description between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotBetween(String value1, String value2) {
            addCriterion("description not between", value1, value2, "description");
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

        public Criteria andRaceIdLikeInsensitive(String value) {
            addCriterion("upper(race_id) like", value.toUpperCase(), "raceId");
            return (Criteria) this;
        }

        public Criteria andRaceNameLikeInsensitive(String value) {
            addCriterion("upper(race_name) like", value.toUpperCase(), "raceName");
            return (Criteria) this;
        }

        public Criteria andSponsorLikeInsensitive(String value) {
            addCriterion("upper(sponsor) like", value.toUpperCase(), "sponsor");
            return (Criteria) this;
        }

        public Criteria andMaxHonorLikeInsensitive(String value) {
            addCriterion("upper(max_honor) like", value.toUpperCase(), "maxHonor");
            return (Criteria) this;
        }

        public Criteria andDescriptionLikeInsensitive(String value) {
            addCriterion("upper(description) like", value.toUpperCase(), "description");
            return (Criteria) this;
        }

        public Criteria andPkClassinfoLikeInsensitive(String value) {
            addCriterion("upper(pk_classinfo) like", value.toUpperCase(), "pkClassinfo");
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
     * TABLE t_race_record
     * @mbg.generated MyBatis Generator 自动生成Bean，自定义属性会覆盖
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * TABLE t_race_record
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