package com.lhj.model.system;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lhj.common.model.Pages;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * SysLogs entity. @author MyEclipse Persistence Tools
 */

public class SysLogs extends Pages<SysLogs>{

	// Fields

	/**
     * 
     */
    private static final long serialVersionUID = -4031715916052593253L;
    
    
    private String sid;
	private String userId;
	private String userName;
	private Integer logLevel;
	private String logName;
	private String logHost;
	private Integer status;
	private String exMessage;
	private String exCode;
	
	//查询时的开始时间
	@JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+8")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date queryDateStart;
	//查询时的结束时间
	@JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+8")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date queryDateEnd;

	/**
	 * 关联应用表
	 */
	@JsonIgnore
	private SysApps sysApps;
	private String appId;		
	private String appName;
	
	private SysLogsDetails logsDetail;
	
	/**
	 * 关联日志详情对象
	 */
	//@JsonIgnore
	private Set<SysLogsDetails> sysLogsDetails = new HashSet<SysLogsDetails>(0);

	/** 非持久化字段  */
	private Float logsFileSize;
	private String modifyTime;
	private String updateLogName;
	private Integer logsTextType;
	private String filePath;
	private String fileParentPath;
	private String pathName;
	private String topPath;
	private String secondName;
	
	public String getSid() {
		return this.sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
    public Integer getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(Integer logLevel) {
        this.logLevel = logLevel;
    }

    public String getLogName() {
		return this.logName;
	}

	public void setLogName(String logName) {
		this.logName = logName;
	}

	public String getLogHost() {
		return this.logHost;
	}

	public void setLogHost(String logHost) {
		this.logHost = logHost;
	}

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getExMessage() {
		return this.exMessage;
	}

	public void setExMessage(String exMessage) {
		this.exMessage = exMessage;
	}

	public String getExCode() {
		return this.exCode;
	}

	public void setExCode(String exCode) {
		this.exCode = exCode;
	}

	public SysApps getSysApps() {
		return sysApps;
	}

	public void setSysApps(SysApps sysApps) {
		this.sysApps = sysApps;
	}
	
    public String getAppName() {
        if(sysApps!=null){ appName=sysApps.getAppName(); }
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
        if(sysApps==null){ sysApps=new SysApps(); }
        sysApps.setAppName(appName);
    }

	public String getAppId() {
		 if(sysApps!=null){ appId=sysApps.getSid(); }
	     return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
        if(sysApps==null){ sysApps=new SysApps();  }
        sysApps.setSid(appId);
	}

	public Set<SysLogsDetails> getSysLogsDetails() {
		return sysLogsDetails;
	}

	public void setSysLogsDetails(Set<SysLogsDetails> sysLogsDetails) {
		this.sysLogsDetails = sysLogsDetails;
	}

	public Date getQueryDateStart() {
		return queryDateStart;
	}

	public void setQueryDateStart(Date queryDateStart) {
		this.queryDateStart = queryDateStart;
	}

	public Date getQueryDateEnd() {
		return queryDateEnd;
	}

	public void setQueryDateEnd(Date queryDateEnd) {
		this.queryDateEnd = queryDateEnd;
	}

	public SysLogsDetails getLogsDetail() {
		return logsDetail;
	}

	public void setLogsDetail(SysLogsDetails logsDetail) {
		this.logsDetail = logsDetail;
	}

	public Float getLogsFileSize(){
		return logsFileSize;
	}
	
	public void setLogsFileSize(Float logsFileSize){
		this.logsFileSize = logsFileSize;
	}
	
	public String getModifyTime(){
		return modifyTime;
	}
	
	public void setModifyTime(String modifyTime){
		this.modifyTime = modifyTime;
	}
	
	public String getUpdateLogName(){
		return updateLogName;
	}
	
	public void setUpdateLogName(String updateLogName){
		this.updateLogName = updateLogName;
	}
	
	public Integer getLogsTextType(){
		return logsTextType;
	}
	
	public void setLogsTextType(Integer logsTextType){
		this.logsTextType = logsTextType;
	}
	
	public String getFilePath(){
		return filePath;
	}
	
	public void setFilePath(String filePath){
		this.filePath = filePath;
	}
	
	public String getFileParentPath(){
		return fileParentPath;
	}
	
	public void setFileParentPath(String fileParentPath){
		this.fileParentPath = fileParentPath;
	}
	
	public String getPathName(){
		return pathName;
	}
	
	public void setPathName(String pathName){
		this.pathName = pathName;
	}
	
	public String getTopPath(){
		return topPath;
	}
	
	public void setTopPath(String topPath){
		this.topPath = topPath;
	}
	
	public String getSecondName(){
		return secondName;
	}
	
	public void setSecondName(String secondName){
		this.secondName = secondName;
	}
}