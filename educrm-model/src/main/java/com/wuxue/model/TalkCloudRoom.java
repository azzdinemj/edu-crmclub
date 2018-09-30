package com.wuxue.model;

import com.wuxue.base.BaiscPage;

import java.util.Date;

/**
 * 
* @Title: TalkCloudRoom.java  - 拓课云教室 
* @Package com.wuxue.model   
* @author admin  
* @date 2018年5月30日
 */
public class TalkCloudRoom extends BaiscPage {
	
	/**
	 * 主键
	 */
	private String pkTalkCloudRoom;
	
	/**
	 * 教室名
	 */
	private String roomname;

	/**
	 * 老师地址
	 */
	private String teacherUrl;
	
	/**
	 * 学员地址
	 */
	private String studentUrl;
	
	/**
	 * 教室号
	 */
	private String serial;
	
	/**
	 * 教室类型(0:1 对 1  3：1 对多 )
	 */
	private String roomtype;
	
	/**
	 * 教室开始时间 (时间戳,精确到秒), 例：1429242959 )
	 */
	private Integer starttime;

	/**
	 * 教室结束时间(时间戳), 例：1429261807 )
	 */
	private Integer endtime;
	
	/**
	 * 老师密码（4=<长度<=16 ）
	 */
	private String chairmanpwd;
	
	/**
	 * 助教密码（4=<长度<=16 ）
	 */
	private String assistantpwd;
	
	/**
	 * 巡课密码（4=<长度<=16 ）
	 */
	private String patrolpwd;
	
	/**
	 * 学生进入教室是否需要密码（0否 1是）
	 */
	private String passwordrequired;
	
	/**
	 * 学生密码 passwordrequired = 1 时必填 
	 */
	private String confuserpwd;
	
	/**
	 * 视频分辨率(0：176x144   1：320x240 2：640x480   3：1280x720 4：1920x1080 )
	 */
	private Integer videotype;
	
	/**
	 * 视频帧率10,15,20,25,30 
	 */
	private Integer videoframerate;
	
	/**
	 * 教室点数 （选填）
	 */
	private Integer confusernum;
	
	/**
	 * 自动开启音视频(0: 不自动开启  1：自动开启) 
	 */
	private Integer autoopenav;
	
	/**
	 * 创建人名称
	 */
	private String createName;

	/**
	 * 修改人名称
	 */
	private String updateName;
	/**
	 * 状态 1:可见 0:不可见 9删除
	 */
	private String status;
	
	
	/**
	 * 聊天类型   类型 0:公聊; 2:私聊（目前只有 0）
	 */
	private String chattype;
	
	/**
	 * 教室状态  0当前,1未来,2历史
	 */
	private String roomStatus;
	
	/**
	 * 创建时间
	 */
	private Date createTime;
	
	/**
	 * 更新时间
	 */
	private Date updateTime;

	/**
	 * 0: 当前在线人数 1：登录人数
	 */
	private String type;

	/**
	 * 教师课件（数量）
	 */
	private Integer filenum;

	public Integer getFilenum() {
		return filenum;
	}

	public void setFilenum(Integer filenum) {
		this.filenum = filenum;
	}

	public String getUpdateName() {
		return updateName;
	}

	public void setUpdateName(String updateName) {
		this.updateName = updateName;
	}
	public String getPkTalkCloudRoom() {
		return pkTalkCloudRoom;
	}

	public void setPkTalkCloudRoom(String pkTalkCloudRoom) {
		this.pkTalkCloudRoom = pkTalkCloudRoom;
	}

	public String getRoomname() {
		return roomname;
	}

	public void setRoomname(String roomname) {
		this.roomname = roomname;
	}

	public String getTeacherUrl() {
		return teacherUrl;
	}

	public void setTeacherUrl(String teacherUrl) {
		this.teacherUrl = teacherUrl;
	}

	public String getStudentUrl() {
		return studentUrl;
	}

	public void setStudentUrl(String studentUrl) {
		this.studentUrl = studentUrl;
	}

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public String getRoomtype() {
		return roomtype;
	}

	public void setRoomtype(String roomtype) {
		this.roomtype = roomtype;
	}

	public Integer getStarttime() {
		return starttime;
	}

	public void setStarttime(Integer starttime) {
		this.starttime = starttime;
	}

	public Integer getEndtime() {
		return endtime;
	}

	public void setEndtime(Integer endtime) {
		this.endtime = endtime;
	}

	public String getChairmanpwd() {
		return chairmanpwd;
	}

	public void setChairmanpwd(String chairmanpwd) {
		this.chairmanpwd = chairmanpwd;
	}

	public String getAssistantpwd() {
		return assistantpwd;
	}

	public void setAssistantpwd(String assistantpwd) {
		this.assistantpwd = assistantpwd;
	}

	public String getPatrolpwd() {
		return patrolpwd;
	}

	public void setPatrolpwd(String patrolpwd) {
		this.patrolpwd = patrolpwd;
	}

	public String getPasswordrequired() {
		return passwordrequired;
	}

	public void setPasswordrequired(String passwordrequired) {
		this.passwordrequired = passwordrequired;
	}

	public String getConfuserpwd() {
		return confuserpwd;
	}

	public void setConfuserpwd(String confuserpwd) {
		this.confuserpwd = confuserpwd;
	}

	public Integer getVideotype() {
		return videotype;
	}

	public void setVideotype(Integer videotype) {
		this.videotype = videotype;
	}

	public Integer getVideoframerate() {
		return videoframerate;
	}

	public void setVideoframerate(Integer videoframerate) {
		this.videoframerate = videoframerate;
	}

	public Integer getConfusernum() {
		return confusernum;
	}

	public void setConfusernum(Integer confusernum) {
		this.confusernum = confusernum;
	}

	public Integer getAutoopenav() {
		return autoopenav;
	}

	public void setAutoopenav(Integer autoopenav) {
		this.autoopenav = autoopenav;
	}

	public String getCreateName() {
		return createName;
	}

	public void setCreateName(String createName) {
		this.createName = createName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getChattype() {
		return chattype;
	}

	public void setChattype(String chattype) {
		this.chattype = chattype;
	}

	public String getRoomStatus() {
		return roomStatus;
	}

	public void setRoomStatus(String roomStatus) {
		this.roomStatus = roomStatus;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
		
}