package com.wuxue.model;

import com.wuxue.base.BaiscPage;

import java.math.BigDecimal;
import java.util.Date;

public class Student extends BaiscPage {
    private String pkStudent;

    private String pkDomain;

    private String studentClass;

    private String code;

    private String caption;

    private String shortCaption;

    private String shortCode;

    private Integer sex;

    private Date birthday;

    private String birthdayTime;

    private String nation;

    private Integer isminority;

    private Integer isreligion;

    private String religion;

    private String passportNo;

    private Date passportDate;

    private String passportDateTime;

    private BigDecimal studyAbroadFee;

    private String img;

    private Integer schoolrool;

    private String schoolroolAddress;

    private String studentId;

    private String inState;

    private String teacherAppraisal;

    private String graduationGo;

    private String mentalHealth;

    private String healthyBody;

    private String situationAnalysis;

    private String letter;

    private String planning;

    private String parentFeedback;

    private String nationality;

    private String motherLan;

    private String idKind;

    private String idCard;

    private String idAddress;

    private Integer greenCard;

    private String greenCardCountry;

    private String oldPlace;

    private String oldSclool;

    private String grade;  //届 如2018级

    private String phone;

    private String email;

    private String fax;

    private String zip;

    private String address;

    private String hobby;

    private Integer isboarding;

    private String tracksource;

    private String source;

    private String appraisal;

    private String dream;

    private String parentsExpect;

    private String memo;

    private String pkSysUser;

    private String firstSysUser;

    private Integer isvalid;

    private Integer istype;

    private String changer;

    private Date changedate;

    private Integer state;

    private String password;

    private String company;

    private String branch;

    private String powerUser;

    private Integer isSchoolrool;

    private Integer isByschoolbus;

    private String pkEmployee;

    private String dormRoom;

    private String schoolBus;

    private String pkClassinfo;

    private String program;
    
    private String willCourse;
    
    private String willRank;
    
    private String willLesson;
    
    private String target;
    
    private String courseType;
    
    private String classType;
    
    private String teacherRequest;
    
    private String subjectBasis;
    
    private String englishLevel;
    
    private String learningHabits;
    
    private String readState;

    private Integer mentality;//与访谈记录心里咨询关联

    private Date lastloginDate; //上次登录时间

    private String parentName; //父母姓名
    private String parentPhone;//父母电话
    private String stuSchool;  //学生学校
    private String qq;         //qq
    private String province;   //省
    private String city;       //市
    private String area;       //区
    private String zipCode;    //邮编
    private String moreDetail; //更多信息

    private BigDecimal waistline; //腰围

    private BigDecimal bust;  //胸围

    private BigDecimal bodyWeight;  //体重

    private BigDecimal height;  //height

    private String shoeSize;  //鞋码

    private Integer gradeNum;  //年级

    //************
    private boolean leaveStatus;//请假状态 请假中：true

    public boolean isLeaveStatus() {
        return leaveStatus;
    }

    public void setLeaveStatus(boolean leaveStatus) {
        this.leaveStatus = leaveStatus;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getParentPhone() {
        return parentPhone;
    }

    public void setParentPhone(String parentPhone) {
        this.parentPhone = parentPhone;
    }

    public String getStuSchool() {
        return stuSchool;
    }

    public void setStuSchool(String stuSchool) {
        this.stuSchool = stuSchool;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getMoreDetail() {
        return moreDetail;
    }

    public void setMoreDetail(String moreDetail) {
        this.moreDetail = moreDetail;
    }

    public Date getLastloginDate() {
        return lastloginDate;
    }

    public void setLastloginDate(Date lastloginDate) {
        this.lastloginDate = lastloginDate;
    }

    public String getWillCourse() {
		return willCourse;
	}

	public void setWillCourse(String willCourse) {
		this.willCourse = willCourse;
	}

	public String getWillRank() {
		return willRank;
	}

	public void setWillRank(String willRank) {
		this.willRank = willRank;
	}

	public String getWillLesson() {
		return willLesson;
	}

	public void setWillLesson(String willLesson) {
		this.willLesson = willLesson;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getCourseType() {
		return courseType;
	}

	public void setCourseType(String courseType) {
		this.courseType = courseType;
	}

	public String getClassType() {
		return classType;
	}

	public void setClassType(String classType) {
		this.classType = classType;
	}

	public String getTeacherRequest() {
		return teacherRequest;
	}

	public void setTeacherRequest(String teacherRequest) {
		this.teacherRequest = teacherRequest;
	}

	public String getSubjectBasis() {
		return subjectBasis;
	}

	public void setSubjectBasis(String subjectBasis) {
		this.subjectBasis = subjectBasis;
	}

	public String getEnglishLevel() {
		return englishLevel;
	}

	public void setEnglishLevel(String englishLevel) {
		this.englishLevel = englishLevel;
	}

	public String getLearningHabits() {
		return learningHabits;
	}

	public void setLearningHabits(String learningHabits) {
		this.learningHabits = learningHabits;
	}

	public String getReadState() {
		return readState;
	}

	public void setReadState(String readState) {
		this.readState = readState;
	}

	public String getPkStudent() {
        return pkStudent;
    }

    public void setPkStudent(String pkStudent) {
        this.pkStudent = pkStudent;
    }

    public String getPkDomain() {
        return pkDomain;
    }

    public void setPkDomain(String pkDomain) {
        this.pkDomain = pkDomain;
    }

    public String getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(String studentClass) {
        this.studentClass = studentClass;
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

    public String getShortCaption() {
        return shortCaption;
    }

    public void setShortCaption(String shortCaption) {
        this.shortCaption = shortCaption;
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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public Integer getIsminority() {
        return isminority;
    }

    public void setIsminority(Integer isminority) {
        this.isminority = isminority;
    }

    public Integer getIsreligion() {
        return isreligion;
    }

    public void setIsreligion(Integer isreligion) {
        this.isreligion = isreligion;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getPassportNo() {
        return passportNo;
    }

    public void setPassportNo(String passportNo) {
        this.passportNo = passportNo;
    }

    public Date getPassportDate() {
        return passportDate;
    }

    public void setPassportDate(Date passportDate) {
        this.passportDate = passportDate;
    }

    public BigDecimal getStudyAbroadFee() {
        return studyAbroadFee;
    }

    public void setStudyAbroadFee(BigDecimal studyAbroadFee) {
        this.studyAbroadFee = studyAbroadFee;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Integer getSchoolrool() {
        return schoolrool;
    }

    public void setSchoolrool(Integer schoolrool) {
        this.schoolrool = schoolrool;
    }

    public String getSchoolroolAddress() {
        return schoolroolAddress;
    }

    public void setSchoolroolAddress(String schoolroolAddress) {
        this.schoolroolAddress = schoolroolAddress;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getInState() {
        return inState;
    }

    public void setInState(String inState) {
        this.inState = inState;
    }

    public String getTeacherAppraisal() {
        return teacherAppraisal;
    }

    public void setTeacherAppraisal(String teacherAppraisal) {
        this.teacherAppraisal = teacherAppraisal;
    }

    public String getGraduationGo() {
        return graduationGo;
    }

    public void setGraduationGo(String graduationGo) {
        this.graduationGo = graduationGo;
    }

    public String getMentalHealth() {
        return mentalHealth;
    }

    public void setMentalHealth(String mentalHealth) {
        this.mentalHealth = mentalHealth;
    }

    public String getHealthyBody() {
        return healthyBody;
    }

    public void setHealthyBody(String healthyBody) {
        this.healthyBody = healthyBody;
    }

    public String getSituationAnalysis() {
        return situationAnalysis;
    }

    public void setSituationAnalysis(String situationAnalysis) {
        this.situationAnalysis = situationAnalysis;
    }

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    public String getPlanning() {
        return planning;
    }

    public void setPlanning(String planning) {
        this.planning = planning;
    }

    public String getParentFeedback() {
        return parentFeedback;
    }

    public void setParentFeedback(String parentFeedback) {
        this.parentFeedback = parentFeedback;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getMotherLan() {
        return motherLan;
    }

    public void setMotherLan(String motherLan) {
        this.motherLan = motherLan;
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

    public Integer getGreenCard() {
        return greenCard;
    }

    public void setGreenCard(Integer greenCard) {
        this.greenCard = greenCard;
    }

    public String getGreenCardCountry() {
        return greenCardCountry;
    }

    public void setGreenCardCountry(String greenCardCountry) {
        this.greenCardCountry = greenCardCountry;
    }

    public String getOldPlace() {
        return oldPlace;
    }

    public void setOldPlace(String oldPlace) {
        this.oldPlace = oldPlace;
    }

    public String getOldSclool() {
        return oldSclool;
    }

    public void setOldSclool(String oldSclool) {
        this.oldSclool = oldSclool;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
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

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public Integer getIsboarding() {
        return isboarding;
    }

    public void setIsboarding(Integer isboarding) {
        this.isboarding = isboarding;
    }

    public String getTracksource() {
        return tracksource;
    }

    public void setTracksource(String tracksource) {
        this.tracksource = tracksource;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getAppraisal() {
        return appraisal;
    }

    public void setAppraisal(String appraisal) {
        this.appraisal = appraisal;
    }

    public String getDream() {
        return dream;
    }

    public void setDream(String dream) {
        this.dream = dream;
    }

    public String getParentsExpect() {
        return parentsExpect;
    }

    public void setParentsExpect(String parentsExpect) {
        this.parentsExpect = parentsExpect;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getPkSysUser() {
        return pkSysUser;
    }

    public void setPkSysUser(String pkSysUser) {
        this.pkSysUser = pkSysUser;
    }

    public String getFirstSysUser() {
        return firstSysUser;
    }

    public void setFirstSysUser(String firstSysUser) {
        this.firstSysUser = firstSysUser;
    }

    public Integer getIsvalid() {
        return isvalid;
    }

    public void setIsvalid(Integer isvalid) {
        this.isvalid = isvalid;
    }

    public Integer getIstype() {
        return istype;
    }

    public void setIstype(Integer istype) {
        this.istype = istype;
    }

    public String getChanger() {
        return changer;
    }

    public void setChanger(String changer) {
        this.changer = changer;
    }

    public Date getChangedate() {
        return changedate;
    }

    public void setChangedate(Date changedate) {
        this.changedate = changedate;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getBirthdayTime() {
        return birthdayTime;
    }

    public void setBirthdayTime(String birthdayTime) {
        this.birthdayTime = birthdayTime;
    }

    public String getPassportDateTime() {
        return passportDateTime;
    }

    public void setPassportDateTime(String passportDateTime) {
        this.passportDateTime = passportDateTime;
    }

    public String getPowerUser() {
        return powerUser;
    }

    public void setPowerUser(String powerUser) {
        this.powerUser = powerUser;
    }

    public Integer getIsSchoolrool() {
        return isSchoolrool;
    }

    public void setIsSchoolrool(Integer isSchoolrool) {
        this.isSchoolrool = isSchoolrool;
    }

    public Integer getIsByschoolbus() {
        return isByschoolbus;
    }

    public void setIsByschoolbus(Integer isByschoolbus) {
        this.isByschoolbus = isByschoolbus;
    }

    public String getPkEmployee() {
        return pkEmployee;
    }

    public void setPkEmployee(String pkEmployee) {
        this.pkEmployee = pkEmployee;
    }

    public String getDormRoom() {
        return dormRoom;
    }

    public void setDormRoom(String dormRoom) {
        this.dormRoom = dormRoom;
    }

    public String getSchoolBus() {
        return schoolBus;
    }

    public void setSchoolBus(String schoolBus) {
        this.schoolBus = schoolBus;
    }

    public String getPkClassinfo() {
        return pkClassinfo;
    }

    public void setPkClassinfo(String pkClassinfo) {
        this.pkClassinfo = pkClassinfo;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public Integer getMentality() {
        return mentality;
    }

    public void setMentality(Integer mentality) {
        this.mentality = mentality;
    }

    public BigDecimal getWaistline() {
        return waistline;
    }

    public void setWaistline(BigDecimal waistline) {
        this.waistline = waistline;
    }

    public BigDecimal getBust() {
        return bust;
    }

    public void setBust(BigDecimal bust) {
        this.bust = bust;
    }

    public BigDecimal getBodyWeight() {
        return bodyWeight;
    }

    public void setBodyWeight(BigDecimal bodyWeight) {
        this.bodyWeight = bodyWeight;
    }

    public BigDecimal getHeight() {
        return height;
    }

    public void setHeight(BigDecimal height) {
        this.height = height;
    }

    public Integer getGradeNum() {
        return gradeNum;
    }

    public void setGradeNum(Integer gradeNum) {
        this.gradeNum = gradeNum;
    }

    public String getShoeSize() {
        return shoeSize;
    }

    public void setShoeSize(String shoeSize) {
        this.shoeSize = shoeSize;
    }
}