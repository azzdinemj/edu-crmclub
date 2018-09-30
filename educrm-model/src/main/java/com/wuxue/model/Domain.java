package com.wuxue.model;

import com.wuxue.base.BaiscPage;

import java.util.List;

public class Domain extends BaiscPage {
    private String pkDomain;

    private String pkParent;

    private String caption;

    private String shortCode;

    private String shortCaption;

    private String person;

    private String invoiceUnit;

    private String invoiceCode;

    private String invoiceAddress;

    private String invoicePhone;

    private String invoiceBank;

    private String invoiceBankAccount;

    private String phone;

    private String fax;

    private String email;

    private String zip;

    private String address;

    private Integer kind;

    private Integer isvalid;

    private Integer sort;

    private String memo;

    private String logo;

    private String creationDateTime;

    private String lasteditDateTime;

    public String getPkDomain() {
        return pkDomain;
    }

    public void setPkDomain(String pkDomain) {
        this.pkDomain = pkDomain;
    }

    public String getPkParent() {
        return pkParent;
    }

    public void setPkParent(String pkParent) {
        this.pkParent = pkParent;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getShortCode() {
        return shortCode;
    }

    public void setShortCode(String shortCode) {
        this.shortCode = shortCode;
    }

    public String getShortCaption() {
        return shortCaption;
    }

    public void setShortCaption(String shortCaption) {
        this.shortCaption = shortCaption;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getInvoiceUnit() {
        return invoiceUnit;
    }

    public void setInvoiceUnit(String invoiceUnit) {
        this.invoiceUnit = invoiceUnit;
    }

    public String getInvoiceCode() {
        return invoiceCode;
    }

    public void setInvoiceCode(String invoiceCode) {
        this.invoiceCode = invoiceCode;
    }

    public String getInvoiceAddress() {
        return invoiceAddress;
    }

    public void setInvoiceAddress(String invoiceAddress) {
        this.invoiceAddress = invoiceAddress;
    }

    public String getInvoicePhone() {
        return invoicePhone;
    }

    public void setInvoicePhone(String invoicePhone) {
        this.invoicePhone = invoicePhone;
    }

    public String getInvoiceBank() {
        return invoiceBank;
    }

    public void setInvoiceBank(String invoiceBank) {
        this.invoiceBank = invoiceBank;
    }

    public String getInvoiceBankAccount() {
        return invoiceBankAccount;
    }

    public void setInvoiceBankAccount(String invoiceBankAccount) {
        this.invoiceBankAccount = invoiceBankAccount;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getKind() {
        return kind;
    }

    public void setKind(Integer kind) {
        this.kind = kind;
    }

    public Integer getIsvalid() {
        return isvalid;
    }

    public void setIsvalid(Integer isvalid) {
        this.isvalid = isvalid;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public List<Domain> getChildren() {
        return children;
    }

    public void setChildren(List<Domain> children) {
        this.children = children;
    }

    public List<Domain> children;

    public String getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(String creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    public String getLasteditDateTime() {
        return lasteditDateTime;
    }

    public void setLasteditDateTime(String lasteditDateTime) {
        this.lasteditDateTime = lasteditDateTime;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
}