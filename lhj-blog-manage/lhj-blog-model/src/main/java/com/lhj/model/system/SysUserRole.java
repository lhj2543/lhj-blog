package com.lhj.model.system;

import java.util.Date;
import java.lang.String;

import com.lhj.common.model.Pages;

/**
 * table name:		SYS_USER_ROLE <br/>
 * table nameCn:	 <br/>
 * entity name:		SysUserRole <br/>
 * author pojofactory pojo builder
 * created 2020-3-2 21:28:54
 */
public class SysUserRole extends Pages<SysUserRole>{
	
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
	 * colNameCn 	角色ID
	 * type 		String
	 * len 		50
	 */
	private String roleId;
	/**
	 * colNameCn 	状态0=有效，1=无效
	 * type 		String
	 * len 		2
	 */
	private String status;
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
	
	public String getRoleId(){
		return roleId;
	}
	public void setRoleId(String roleId){
		this.roleId=roleId;
	}
	
	public String getStatus(){
		return status;
	}
	public void setStatus(String status){
		this.status=status;
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
	
	public String getNotes(){
		return notes;
	}
	public void setNotes(String notes){
		this.notes=notes;
	}
	
	
	
	
	
	//get,set methods end
	

}
