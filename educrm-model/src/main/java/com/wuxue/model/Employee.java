package com.wuxue.model;

import com.wuxue.base.BaiscPage;

import java.math.BigDecimal;
import java.util.Date;

public class Employee extends BaiscPage {
    private String pkEmployee;

    private String pkDomain;

    private String pkDepartment;

    private String code;

    private String caption;

    private String shortCode;

    private Integer sex;

    private String nation;

    private String accountLoction;

    private String politicalStatus;

    private String healthStatus;

    private String maritalStatus;

    private Integer isfertility;

    private Integer isfertilityplan;

    private String jobPost;

    private Integer kind;

    private Date enterdate;

    private String enterdates;

    private Integer isleave;

    private Date leavedate;

    private String leavedates;

    private String graduatedSchool;

    private String theProfession;

    private String education;

    private String jobTitle;

    private String foreignLanguage;

    private String computerSkill;

    private Integer isstay;

    private Integer canheadteacher;

    private BigDecimal expectedSalary;

    private String fileLocation;

    private Date birth;

    private String birthTime;

    private String bank;

    private String bankAccount;

    private String phone;

    private String mobilePhone;

    private String fax;

    private String email;

    private String zip;

    private String address;

    private String idKind;

    private String idCard;

    private String idAddress;

    private String emergencyContact;

    private String relationship;

    private String emergencyContactPhone;

    private Integer isvalid;

    private String careerPlanning;

    private String memo;
    private String sysUser;
    private Integer sort;

    private String dateTime;

    private String dormRoom;

    private String nationality;//国籍
    private String  img; //图片
    private String audio;//音频
    private Double price;//老师上一节课的价格

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }


    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }



    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    private String types;



    public String getAllergen() {
        return allergen;
    }

    public void setAllergen(String allergen) {
        this.allergen = allergen;
    }

    public String getTaboofood() {
        return taboofood;
    }

    public void setTaboofood(String taboofood) {
        this.taboofood = taboofood;
    }

    private String allergen;
    private String taboofood;

    public String getPkEmployee() {
        return pkEmployee;
    }

    public void setPkEmployee(String pkEmployee) {
        this.pkEmployee = pkEmployee;
    }

    public String getPkDomain() {
        return pkDomain;
    }

    public void setPkDomain(String pkDomain) {
        this.pkDomain = pkDomain;
    }

    public String getPkDepartment() {
        return pkDepartment;
    }

    public void setPkDepartment(String pkDepartment) {
        this.pkDepartment = pkDepartment;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getAccountLoction() {
        return accountLoction;
    }

    public void setAccountLoction(String accountLoction) {
        this.accountLoction = accountLoction;
    }

    public String getPoliticalStatus() {
        return politicalStatus;
    }

    public void setPoliticalStatus(String politicalStatus) {
        this.politicalStatus = politicalStatus;
    }

    public String getHealthStatus() {
        return healthStatus;
    }

    public void setHealthStatus(String healthStatus) {
        this.healthStatus = healthStatus;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public Integer getIsfertility() {
        return isfertility;
    }

    public void setIsfertility(Integer isfertility) {
        this.isfertility = isfertility;
    }

    public Integer getIsfertilityplan() {
        return isfertilityplan;
    }

    public void setIsfertilityplan(Integer isfertilityplan) {
        this.isfertilityplan = isfertilityplan;
    }

    public String getJobPost() {
        return jobPost;
    }

    public void setJobPost(String jobPost) {
        this.jobPost = jobPost;
    }

    public Integer getKind() {
        return kind;
    }

    public void setKind(Integer kind) {
        this.kind = kind;
    }

    public Date getEnterdate() {
        return enterdate;
    }

    public void setEnterdate(Date enterdate) {
        this.enterdate = enterdate;
    }

    public Integer getIsleave() {
        return isleave;
    }

    public void setIsleave(Integer isleave) {
        this.isleave = isleave;
    }

    public Date getLeavedate() {
        return leavedate;
    }

    public void setLeavedate(Date leavedate) {
        this.leavedate = leavedate;
    }

    public String getGraduatedSchool() {
        return graduatedSchool;
    }

    public void setGraduatedSchool(String graduatedSchool) {
        this.graduatedSchool = graduatedSchool;
    }

    public String getTheProfession() {
        return theProfession;
    }

    public void setTheProfession(String theProfession) {
        this.theProfession = theProfession;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getForeignLanguage() {
        return foreignLanguage;
    }

    public void setForeignLanguage(String foreignLanguage) {
        this.foreignLanguage = foreignLanguage;
    }

    public String getComputerSkill() {
        return computerSkill;
    }

    public void setComputerSkill(String computerSkill) {
        this.computerSkill = computerSkill;
    }

    public Integer getIsstay() {
        return isstay;
    }

    public void setIsstay(Integer isstay) {
        this.isstay = isstay;
    }

    public Integer getCanheadteacher() {
        return canheadteacher;
    }

    public void setCanheadteacher(Integer canheadteacher) {
        this.canheadteacher = canheadteacher;
    }

    public BigDecimal getExpectedSalary() {
        return expectedSalary;
    }

    public void setExpectedSalary(BigDecimal expectedSalary) {
        this.expectedSalary = expectedSalary;
    }

    public String getFileLocation() {
        return fileLocation;
    }

    public void setFileLocation(String fileLocation) {
        this.fileLocation = fileLocation;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
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

    public String getIdKind() {
        return idKind;
    }

    public void setIdKind(String idKind) {
        this.idKind = idKind;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getIdAddress() {
        return idAddress;
    }

    public void setIdAddress(String idAddress) {
        this.idAddress = idAddress;
    }

    public String getEmergencyContact() {
        return emergencyContact;
    }

    public void setEmergencyContact(String emergencyContact) {
        this.emergencyContact = emergencyContact;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getEmergencyContactPhone() {
        return emergencyContactPhone;
    }

    public void setEmergencyContactPhone(String emergencyContactPhone) {
        this.emergencyContactPhone = emergencyContactPhone;
    }

    public Integer getIsvalid() {
        return isvalid;
    }

    public void setIsvalid(Integer isvalid) {
        this.isvalid = isvalid;
    }

    public String getCareerPlanning() {
        return careerPlanning;
    }

    public void setCareerPlanning(String careerPlanning) {
        this.careerPlanning = careerPlanning;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
    public String getSysUser() {
        return sysUser;
    }

    public void setSysUser(String sysUser) {
        this.sysUser = sysUser;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getEnterdates() {
        return enterdates;
    }

    public void setEnterdates(String enterdates) {
        this.enterdates = enterdates;
    }

    public String getLeavedates() {
        return leavedates;
    }

    public void setLeavedates(String leavedates) {
        this.leavedates = leavedates;
    }

    public String getBirthTime() {
        return birthTime;
    }

    public void setBirthTime(String birthTime) {
        this.birthTime = birthTime;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getDormRoom() {
        return dormRoom;
    }

    public void setDormRoom(String dormRoom) {
        this.dormRoom = dormRoom;
    }
}