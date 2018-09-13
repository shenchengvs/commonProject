package com.holley.platform.model.sys;

import java.util.ArrayList;
import java.util.List;

public class SysModuledefExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SysModuledefExample() {
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

        public Criteria andModuleidIsNull() {
            addCriterion("MODULEID is null");
            return (Criteria) this;
        }

        public Criteria andModuleidIsNotNull() {
            addCriterion("MODULEID is not null");
            return (Criteria) this;
        }

        public Criteria andModuleidEqualTo(String value) {
            addCriterion("MODULEID =", value, "moduleid");
            return (Criteria) this;
        }

        public Criteria andModuleidNotEqualTo(String value) {
            addCriterion("MODULEID <>", value, "moduleid");
            return (Criteria) this;
        }

        public Criteria andModuleidGreaterThan(String value) {
            addCriterion("MODULEID >", value, "moduleid");
            return (Criteria) this;
        }

        public Criteria andModuleidGreaterThanOrEqualTo(String value) {
            addCriterion("MODULEID >=", value, "moduleid");
            return (Criteria) this;
        }

        public Criteria andModuleidLessThan(String value) {
            addCriterion("MODULEID <", value, "moduleid");
            return (Criteria) this;
        }

        public Criteria andModuleidLessThanOrEqualTo(String value) {
            addCriterion("MODULEID <=", value, "moduleid");
            return (Criteria) this;
        }

        public Criteria andModuleidLike(String value) {
            addCriterion("MODULEID like", value, "moduleid");
            return (Criteria) this;
        }

        public Criteria andModuleidNotLike(String value) {
            addCriterion("MODULEID not like", value, "moduleid");
            return (Criteria) this;
        }

        public Criteria andModuleidIn(List<String> values) {
            addCriterion("MODULEID in", values, "moduleid");
            return (Criteria) this;
        }

        public Criteria andModuleidNotIn(List<String> values) {
            addCriterion("MODULEID not in", values, "moduleid");
            return (Criteria) this;
        }

        public Criteria andModuleidBetween(String value1, String value2) {
            addCriterion("MODULEID between", value1, value2, "moduleid");
            return (Criteria) this;
        }

        public Criteria andModuleidNotBetween(String value1, String value2) {
            addCriterion("MODULEID not between", value1, value2, "moduleid");
            return (Criteria) this;
        }

        public Criteria andSystemidIsNull() {
            addCriterion("SYSTEMID is null");
            return (Criteria) this;
        }

        public Criteria andSystemidIsNotNull() {
            addCriterion("SYSTEMID is not null");
            return (Criteria) this;
        }

        public Criteria andSystemidEqualTo(Short value) {
            addCriterion("SYSTEMID =", value, "systemid");
            return (Criteria) this;
        }

        public Criteria andSystemidNotEqualTo(Short value) {
            addCriterion("SYSTEMID <>", value, "systemid");
            return (Criteria) this;
        }

        public Criteria andSystemidGreaterThan(Short value) {
            addCriterion("SYSTEMID >", value, "systemid");
            return (Criteria) this;
        }

        public Criteria andSystemidGreaterThanOrEqualTo(Short value) {
            addCriterion("SYSTEMID >=", value, "systemid");
            return (Criteria) this;
        }

        public Criteria andSystemidLessThan(Short value) {
            addCriterion("SYSTEMID <", value, "systemid");
            return (Criteria) this;
        }

        public Criteria andSystemidLessThanOrEqualTo(Short value) {
            addCriterion("SYSTEMID <=", value, "systemid");
            return (Criteria) this;
        }

        public Criteria andSystemidIn(List<Short> values) {
            addCriterion("SYSTEMID in", values, "systemid");
            return (Criteria) this;
        }

        public Criteria andSystemidNotIn(List<Short> values) {
            addCriterion("SYSTEMID not in", values, "systemid");
            return (Criteria) this;
        }

        public Criteria andSystemidBetween(Short value1, Short value2) {
            addCriterion("SYSTEMID between", value1, value2, "systemid");
            return (Criteria) this;
        }

        public Criteria andSystemidNotBetween(Short value1, Short value2) {
            addCriterion("SYSTEMID not between", value1, value2, "systemid");
            return (Criteria) this;
        }

        public Criteria andDiscIsNull() {
            addCriterion("DISC is null");
            return (Criteria) this;
        }

        public Criteria andDiscIsNotNull() {
            addCriterion("DISC is not null");
            return (Criteria) this;
        }

        public Criteria andDiscEqualTo(String value) {
            addCriterion("DISC =", value, "disc");
            return (Criteria) this;
        }

        public Criteria andDiscNotEqualTo(String value) {
            addCriterion("DISC <>", value, "disc");
            return (Criteria) this;
        }

        public Criteria andDiscGreaterThan(String value) {
            addCriterion("DISC >", value, "disc");
            return (Criteria) this;
        }

        public Criteria andDiscGreaterThanOrEqualTo(String value) {
            addCriterion("DISC >=", value, "disc");
            return (Criteria) this;
        }

        public Criteria andDiscLessThan(String value) {
            addCriterion("DISC <", value, "disc");
            return (Criteria) this;
        }

        public Criteria andDiscLessThanOrEqualTo(String value) {
            addCriterion("DISC <=", value, "disc");
            return (Criteria) this;
        }

        public Criteria andDiscLike(String value) {
            addCriterion("DISC like", value, "disc");
            return (Criteria) this;
        }

        public Criteria andDiscNotLike(String value) {
            addCriterion("DISC not like", value, "disc");
            return (Criteria) this;
        }

        public Criteria andDiscIn(List<String> values) {
            addCriterion("DISC in", values, "disc");
            return (Criteria) this;
        }

        public Criteria andDiscNotIn(List<String> values) {
            addCriterion("DISC not in", values, "disc");
            return (Criteria) this;
        }

        public Criteria andDiscBetween(String value1, String value2) {
            addCriterion("DISC between", value1, value2, "disc");
            return (Criteria) this;
        }

        public Criteria andDiscNotBetween(String value1, String value2) {
            addCriterion("DISC not between", value1, value2, "disc");
            return (Criteria) this;
        }

        public Criteria andIsdblconfirmIsNull() {
            addCriterion("ISDBLCONFIRM is null");
            return (Criteria) this;
        }

        public Criteria andIsdblconfirmIsNotNull() {
            addCriterion("ISDBLCONFIRM is not null");
            return (Criteria) this;
        }

        public Criteria andIsdblconfirmEqualTo(Short value) {
            addCriterion("ISDBLCONFIRM =", value, "isdblconfirm");
            return (Criteria) this;
        }

        public Criteria andIsdblconfirmNotEqualTo(Short value) {
            addCriterion("ISDBLCONFIRM <>", value, "isdblconfirm");
            return (Criteria) this;
        }

        public Criteria andIsdblconfirmGreaterThan(Short value) {
            addCriterion("ISDBLCONFIRM >", value, "isdblconfirm");
            return (Criteria) this;
        }

        public Criteria andIsdblconfirmGreaterThanOrEqualTo(Short value) {
            addCriterion("ISDBLCONFIRM >=", value, "isdblconfirm");
            return (Criteria) this;
        }

        public Criteria andIsdblconfirmLessThan(Short value) {
            addCriterion("ISDBLCONFIRM <", value, "isdblconfirm");
            return (Criteria) this;
        }

        public Criteria andIsdblconfirmLessThanOrEqualTo(Short value) {
            addCriterion("ISDBLCONFIRM <=", value, "isdblconfirm");
            return (Criteria) this;
        }

        public Criteria andIsdblconfirmIn(List<Short> values) {
            addCriterion("ISDBLCONFIRM in", values, "isdblconfirm");
            return (Criteria) this;
        }

        public Criteria andIsdblconfirmNotIn(List<Short> values) {
            addCriterion("ISDBLCONFIRM not in", values, "isdblconfirm");
            return (Criteria) this;
        }

        public Criteria andIsdblconfirmBetween(Short value1, Short value2) {
            addCriterion("ISDBLCONFIRM between", value1, value2, "isdblconfirm");
            return (Criteria) this;
        }

        public Criteria andIsdblconfirmNotBetween(Short value1, Short value2) {
            addCriterion("ISDBLCONFIRM not between", value1, value2, "isdblconfirm");
            return (Criteria) this;
        }

        public Criteria andIpgroupidIsNull() {
            addCriterion("IPGROUPID is null");
            return (Criteria) this;
        }

        public Criteria andIpgroupidIsNotNull() {
            addCriterion("IPGROUPID is not null");
            return (Criteria) this;
        }

        public Criteria andIpgroupidEqualTo(Short value) {
            addCriterion("IPGROUPID =", value, "ipgroupid");
            return (Criteria) this;
        }

        public Criteria andIpgroupidNotEqualTo(Short value) {
            addCriterion("IPGROUPID <>", value, "ipgroupid");
            return (Criteria) this;
        }

        public Criteria andIpgroupidGreaterThan(Short value) {
            addCriterion("IPGROUPID >", value, "ipgroupid");
            return (Criteria) this;
        }

        public Criteria andIpgroupidGreaterThanOrEqualTo(Short value) {
            addCriterion("IPGROUPID >=", value, "ipgroupid");
            return (Criteria) this;
        }

        public Criteria andIpgroupidLessThan(Short value) {
            addCriterion("IPGROUPID <", value, "ipgroupid");
            return (Criteria) this;
        }

        public Criteria andIpgroupidLessThanOrEqualTo(Short value) {
            addCriterion("IPGROUPID <=", value, "ipgroupid");
            return (Criteria) this;
        }

        public Criteria andIpgroupidIn(List<Short> values) {
            addCriterion("IPGROUPID in", values, "ipgroupid");
            return (Criteria) this;
        }

        public Criteria andIpgroupidNotIn(List<Short> values) {
            addCriterion("IPGROUPID not in", values, "ipgroupid");
            return (Criteria) this;
        }

        public Criteria andIpgroupidBetween(Short value1, Short value2) {
            addCriterion("IPGROUPID between", value1, value2, "ipgroupid");
            return (Criteria) this;
        }

        public Criteria andIpgroupidNotBetween(Short value1, Short value2) {
            addCriterion("IPGROUPID not between", value1, value2, "ipgroupid");
            return (Criteria) this;
        }

        public Criteria andUrlIsNull() {
            addCriterion("URL is null");
            return (Criteria) this;
        }

        public Criteria andUrlIsNotNull() {
            addCriterion("URL is not null");
            return (Criteria) this;
        }

        public Criteria andUrlEqualTo(String value) {
            addCriterion("URL =", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotEqualTo(String value) {
            addCriterion("URL <>", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlGreaterThan(String value) {
            addCriterion("URL >", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlGreaterThanOrEqualTo(String value) {
            addCriterion("URL >=", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLessThan(String value) {
            addCriterion("URL <", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLessThanOrEqualTo(String value) {
            addCriterion("URL <=", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLike(String value) {
            addCriterion("URL like", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotLike(String value) {
            addCriterion("URL not like", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlIn(List<String> values) {
            addCriterion("URL in", values, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotIn(List<String> values) {
            addCriterion("URL not in", values, "url");
            return (Criteria) this;
        }

        public Criteria andUrlBetween(String value1, String value2) {
            addCriterion("URL between", value1, value2, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotBetween(String value1, String value2) {
            addCriterion("URL not between", value1, value2, "url");
            return (Criteria) this;
        }

        public Criteria andParentmoduleidIsNull() {
            addCriterion("PARENTMODULEID is null");
            return (Criteria) this;
        }

        public Criteria andParentmoduleidIsNotNull() {
            addCriterion("PARENTMODULEID is not null");
            return (Criteria) this;
        }

        public Criteria andParentmoduleidEqualTo(String value) {
            addCriterion("PARENTMODULEID =", value, "parentmoduleid");
            return (Criteria) this;
        }

        public Criteria andParentmoduleidNotEqualTo(String value) {
            addCriterion("PARENTMODULEID <>", value, "parentmoduleid");
            return (Criteria) this;
        }

        public Criteria andParentmoduleidGreaterThan(String value) {
            addCriterion("PARENTMODULEID >", value, "parentmoduleid");
            return (Criteria) this;
        }

        public Criteria andParentmoduleidGreaterThanOrEqualTo(String value) {
            addCriterion("PARENTMODULEID >=", value, "parentmoduleid");
            return (Criteria) this;
        }

        public Criteria andParentmoduleidLessThan(String value) {
            addCriterion("PARENTMODULEID <", value, "parentmoduleid");
            return (Criteria) this;
        }

        public Criteria andParentmoduleidLessThanOrEqualTo(String value) {
            addCriterion("PARENTMODULEID <=", value, "parentmoduleid");
            return (Criteria) this;
        }

        public Criteria andParentmoduleidLike(String value) {
            addCriterion("PARENTMODULEID like", value, "parentmoduleid");
            return (Criteria) this;
        }

        public Criteria andParentmoduleidNotLike(String value) {
            addCriterion("PARENTMODULEID not like", value, "parentmoduleid");
            return (Criteria) this;
        }

        public Criteria andParentmoduleidIn(List<String> values) {
            addCriterion("PARENTMODULEID in", values, "parentmoduleid");
            return (Criteria) this;
        }

        public Criteria andParentmoduleidNotIn(List<String> values) {
            addCriterion("PARENTMODULEID not in", values, "parentmoduleid");
            return (Criteria) this;
        }

        public Criteria andParentmoduleidBetween(String value1, String value2) {
            addCriterion("PARENTMODULEID between", value1, value2, "parentmoduleid");
            return (Criteria) this;
        }

        public Criteria andParentmoduleidNotBetween(String value1, String value2) {
            addCriterion("PARENTMODULEID not between", value1, value2, "parentmoduleid");
            return (Criteria) this;
        }

        public Criteria andSortnoIsNull() {
            addCriterion("SORTNO is null");
            return (Criteria) this;
        }

        public Criteria andSortnoIsNotNull() {
            addCriterion("SORTNO is not null");
            return (Criteria) this;
        }

        public Criteria andSortnoEqualTo(Integer value) {
            addCriterion("SORTNO =", value, "sortno");
            return (Criteria) this;
        }

        public Criteria andSortnoNotEqualTo(Integer value) {
            addCriterion("SORTNO <>", value, "sortno");
            return (Criteria) this;
        }

        public Criteria andSortnoGreaterThan(Integer value) {
            addCriterion("SORTNO >", value, "sortno");
            return (Criteria) this;
        }

        public Criteria andSortnoGreaterThanOrEqualTo(Integer value) {
            addCriterion("SORTNO >=", value, "sortno");
            return (Criteria) this;
        }

        public Criteria andSortnoLessThan(Integer value) {
            addCriterion("SORTNO <", value, "sortno");
            return (Criteria) this;
        }

        public Criteria andSortnoLessThanOrEqualTo(Integer value) {
            addCriterion("SORTNO <=", value, "sortno");
            return (Criteria) this;
        }

        public Criteria andSortnoIn(List<Integer> values) {
            addCriterion("SORTNO in", values, "sortno");
            return (Criteria) this;
        }

        public Criteria andSortnoNotIn(List<Integer> values) {
            addCriterion("SORTNO not in", values, "sortno");
            return (Criteria) this;
        }

        public Criteria andSortnoBetween(Integer value1, Integer value2) {
            addCriterion("SORTNO between", value1, value2, "sortno");
            return (Criteria) this;
        }

        public Criteria andSortnoNotBetween(Integer value1, Integer value2) {
            addCriterion("SORTNO not between", value1, value2, "sortno");
            return (Criteria) this;
        }

        public Criteria andIconIsNull() {
            addCriterion("ICON is null");
            return (Criteria) this;
        }

        public Criteria andIconIsNotNull() {
            addCriterion("ICON is not null");
            return (Criteria) this;
        }

        public Criteria andIconEqualTo(String value) {
            addCriterion("ICON =", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconNotEqualTo(String value) {
            addCriterion("ICON <>", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconGreaterThan(String value) {
            addCriterion("ICON >", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconGreaterThanOrEqualTo(String value) {
            addCriterion("ICON >=", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconLessThan(String value) {
            addCriterion("ICON <", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconLessThanOrEqualTo(String value) {
            addCriterion("ICON <=", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconLike(String value) {
            addCriterion("ICON like", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconNotLike(String value) {
            addCriterion("ICON not like", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconIn(List<String> values) {
            addCriterion("ICON in", values, "icon");
            return (Criteria) this;
        }

        public Criteria andIconNotIn(List<String> values) {
            addCriterion("ICON not in", values, "icon");
            return (Criteria) this;
        }

        public Criteria andIconBetween(String value1, String value2) {
            addCriterion("ICON between", value1, value2, "icon");
            return (Criteria) this;
        }

        public Criteria andIconNotBetween(String value1, String value2) {
            addCriterion("ICON not between", value1, value2, "icon");
            return (Criteria) this;
        }

        public Criteria andNoteIsNull() {
            addCriterion("NOTE is null");
            return (Criteria) this;
        }

        public Criteria andNoteIsNotNull() {
            addCriterion("NOTE is not null");
            return (Criteria) this;
        }

        public Criteria andNoteEqualTo(String value) {
            addCriterion("NOTE =", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteNotEqualTo(String value) {
            addCriterion("NOTE <>", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteGreaterThan(String value) {
            addCriterion("NOTE >", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteGreaterThanOrEqualTo(String value) {
            addCriterion("NOTE >=", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteLessThan(String value) {
            addCriterion("NOTE <", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteLessThanOrEqualTo(String value) {
            addCriterion("NOTE <=", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteLike(String value) {
            addCriterion("NOTE like", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteNotLike(String value) {
            addCriterion("NOTE not like", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteIn(List<String> values) {
            addCriterion("NOTE in", values, "note");
            return (Criteria) this;
        }

        public Criteria andNoteNotIn(List<String> values) {
            addCriterion("NOTE not in", values, "note");
            return (Criteria) this;
        }

        public Criteria andNoteBetween(String value1, String value2) {
            addCriterion("NOTE between", value1, value2, "note");
            return (Criteria) this;
        }

        public Criteria andNoteNotBetween(String value1, String value2) {
            addCriterion("NOTE not between", value1, value2, "note");
            return (Criteria) this;
        }

        public Criteria andIcon32IsNull() {
            addCriterion("ICON32 is null");
            return (Criteria) this;
        }

        public Criteria andIcon32IsNotNull() {
            addCriterion("ICON32 is not null");
            return (Criteria) this;
        }

        public Criteria andIcon32EqualTo(String value) {
            addCriterion("ICON32 =", value, "icon32");
            return (Criteria) this;
        }

        public Criteria andIcon32NotEqualTo(String value) {
            addCriterion("ICON32 <>", value, "icon32");
            return (Criteria) this;
        }

        public Criteria andIcon32GreaterThan(String value) {
            addCriterion("ICON32 >", value, "icon32");
            return (Criteria) this;
        }

        public Criteria andIcon32GreaterThanOrEqualTo(String value) {
            addCriterion("ICON32 >=", value, "icon32");
            return (Criteria) this;
        }

        public Criteria andIcon32LessThan(String value) {
            addCriterion("ICON32 <", value, "icon32");
            return (Criteria) this;
        }

        public Criteria andIcon32LessThanOrEqualTo(String value) {
            addCriterion("ICON32 <=", value, "icon32");
            return (Criteria) this;
        }

        public Criteria andIcon32Like(String value) {
            addCriterion("ICON32 like", value, "icon32");
            return (Criteria) this;
        }

        public Criteria andIcon32NotLike(String value) {
            addCriterion("ICON32 not like", value, "icon32");
            return (Criteria) this;
        }

        public Criteria andIcon32In(List<String> values) {
            addCriterion("ICON32 in", values, "icon32");
            return (Criteria) this;
        }

        public Criteria andIcon32NotIn(List<String> values) {
            addCriterion("ICON32 not in", values, "icon32");
            return (Criteria) this;
        }

        public Criteria andIcon32Between(String value1, String value2) {
            addCriterion("ICON32 between", value1, value2, "icon32");
            return (Criteria) this;
        }

        public Criteria andIcon32NotBetween(String value1, String value2) {
            addCriterion("ICON32 not between", value1, value2, "icon32");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

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