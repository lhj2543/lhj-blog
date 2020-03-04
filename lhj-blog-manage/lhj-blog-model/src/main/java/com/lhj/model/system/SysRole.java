package com.lhj.model.system;

import java.math.BigDecimal;
import java.util.Date;
import java.lang.String;

import com.lhj.common.model.Pages;

/**
 * table name:		SYS_ROLE <br/>
 * table nameCn:	 <br/>
 * entity name:		SysRole <br/>
 * author pojofactory pojo builder
 * created 2020-3-2 21:28:54
 */
public class SysRole extends Pages<SysRole>{
	
	// fields start
	/**
	 * colNameCn 	主键
	 * type 		String
	 * len 		50
	 */
	private String sid;
	/**
	 * colNameCn 	区域
	 * type 		String
	 * len 		20
	 */
	private String localeId;
	/**
	 * colNameCn 	角色code
	 * type 		String
	 * len 		100
	 */
	private String roleCode;
	/**
	 * colNameCn 	角色名
	 * type 		String
	 * len 		255
	 */
	private String roleName;
	/**
	 * colNameCn 	类别
	 * type 		String
	 * len 		50
	 */
	private String category;
	/**
	 * colNameCn 	状态0=有效，1=无效
	 * type 		String
	 * len 		2
	 */
	private String status;
	/**
	 * colNameCn 	排序
	 * type 		BigDecimal
	 * len 		10
	 */
	private BigDecimal sortKey;
	/**
	 * colNameCn 	备注
	 * type 		String
	 * len 		512
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
	
	public String getLocaleId(){
		return localeId;
	}
	public void setLocaleId(String localeId){
		this.localeId=localeId;
	}
	
	public String getRoleCode(){
		return roleCode;
	}
	public void setRoleCode(String roleCode){
		this.roleCode=roleCode;
	}
	
	public String getRoleName(){
		return roleName;
	}
	public void setRoleName(String roleName){
		this.roleName=roleName;
	}
	
	public String getCategory(){
		return category;
	}
	public void setCategory(String category){
		this.category=category;
	}
	
	public String getStatus(){
		return status;
	}
	public void setStatus(String status){
		this.status=status;
	}
	
	public BigDecimal getSortKey(){
		return sortKey;
	}
	public void setSortKey(BigDecimal sortKey){
		this.sortKey=sortKey;
	}
	
	public String getNotes(){
		return notes;
	}
	public void setNotes(String notes){
		this.notes=notes;
	}
	
	
	
	
	
	//get,set methods end
	

}
