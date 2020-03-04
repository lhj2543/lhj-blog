package com.lhj.model.system;

import java.math.BigDecimal;
import java.util.Date;
import java.lang.String;

import com.lhj.common.model.Pages;

/**
 * table name:		SYS_ACCOUNT <br/>
 * table nameCn:	 <br/>
 * entity name:		SysAccount <br/>
 * author pojofactory pojo builder
 * created 2020-3-2 21:28:53
 */
public class SysAccount extends Pages<SysAccount>{
	
	// fields start
	/**
	 * colNameCn 	主键
	 * type 		String
	 * len 		50
	 */
	private String sid;
	/**
	 * colNameCn 	用户名
	 * type 		String
	 * len 		100
	 */
	private String userCd;
	/**
	 * colNameCn 	密码
	 * type 		String
	 * len 		255
	 */
	private String password;
	/**
	 * colNameCn 	区域
	 * type 		String
	 * len 		20
	 */
	private String localeId;
	/**
	 * colNameCn 	许可证
	 * type 		String
	 * len 		100
	 */
	private String licence;
	/**
	 * colNameCn 	账户类型:（01=普通用户，02=管理员）
	 * type 		String
	 * len 		10
	 */
	private String type;
	/**
	 * colNameCn 	有效开始时间
	 * type 		Date
	 * len 		19
	 */
	private Date startDate;
	/**
	 * colNameCn 	有效结束时间
	 * type 		Date
	 * len 		19
	 */
	private Date endDate;
	/**
	 * colNameCn 	锁住时间
	 * type 		Date
	 * len 		19
	 */
	private Date lockDate;
	/**
	 * colNameCn 	登录失败次数
	 * type 		BigDecimal
	 * len 		10
	 */
	private BigDecimal loginFailureCount;
	/**
	 * colNameCn 	备注
	 * type 		String
	 * len 		255
	 */
	private String notes;
	// fields end
	
	
	// get,set methods
	public String getSid(){
		return sid;
	}
	public void setSid(String sid){
		this.sid=sid;
	}
	
	public String getUserCd(){
		return userCd;
	}
	public void setUserCd(String userCd){
		this.userCd=userCd;
	}
	
	public String getPassword(){
		return password;
	}
	public void setPassword(String password){
		this.password=password;
	}
	
	public String getLocaleId(){
		return localeId;
	}
	public void setLocaleId(String localeId){
		this.localeId=localeId;
	}
	
	public String getLicence(){
		return licence;
	}
	public void setLicence(String licence){
		this.licence=licence;
	}
	
	public String getType(){
		return type;
	}
	public void setType(String type){
		this.type=type;
	}
	
	public Date getStartDate(){
		return startDate;
	}
	public void setStartDate(Date startDate){
		this.startDate=startDate;
	}
	
	public Date getEndDate(){
		return endDate;
	}
	public void setEndDate(Date endDate){
		this.endDate=endDate;
	}
	
	public Date getLockDate(){
		return lockDate;
	}
	public void setLockDate(Date lockDate){
		this.lockDate=lockDate;
	}
	
	public BigDecimal getLoginFailureCount(){
		return loginFailureCount;
	}
	public void setLoginFailureCount(BigDecimal loginFailureCount){
		this.loginFailureCount=loginFailureCount;
	}
	
	public String getNotes(){
		return notes;
	}
	public void setNotes(String notes){
		this.notes=notes;
	}
	
	
	
	
	
	//get,set methods end
	

}
