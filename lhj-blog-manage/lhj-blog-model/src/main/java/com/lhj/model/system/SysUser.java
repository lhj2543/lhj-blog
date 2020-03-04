package com.lhj.model.system;

import java.math.BigDecimal;
import java.util.Date;
import java.lang.String;

import com.lhj.common.model.Pages;

/**
 * table name:		SYS_USER <br/>
 * table nameCn:	 <br/>
 * entity name:		SysUser <br/>
 * author pojofactory pojo builder
 * created 2020-3-2 21:28:54
 */
public class SysUser extends Pages<SysUser>{
	
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
	 * colNameCn 	区域
	 * type 		String
	 * len 		20
	 */
	private String localeId;
	/**
	 * colNameCn 	姓名
	 * type 		String
	 * len 		255
	 */
	private String userName;
	/**
	 * colNameCn 	搜索名
	 * type 		String
	 * len 		100
	 */
	private String userSearchName;
	/**
	 * colNameCn 	性别（1=男，2=女）
	 * type 		String
	 * len 		2
	 */
	private String sex;
	/**
	 * colNameCn 	国家
	 * type 		String
	 * len 		100
	 */
	private String countryCd;
	/**
	 * colNameCn 	地址
	 * type 		String
	 * len 		512
	 */
	private String address;
	/**
	 * colNameCn 	手机号
	 * type 		String
	 * len 		20
	 */
	private String phoneNumber;
	/**
	 * colNameCn 	
	 * type 		String
	 * len 		100
	 */
	private String email;
	/**
	 * colNameCn 	状态
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
	
	public String getUserCd(){
		return userCd;
	}
	public void setUserCd(String userCd){
		this.userCd=userCd;
	}
	
	public String getLocaleId(){
		return localeId;
	}
	public void setLocaleId(String localeId){
		this.localeId=localeId;
	}
	
	public String getUserName(){
		return userName;
	}
	public void setUserName(String userName){
		this.userName=userName;
	}
	
	public String getUserSearchName(){
		return userSearchName;
	}
	public void setUserSearchName(String userSearchName){
		this.userSearchName=userSearchName;
	}
	
	public String getSex(){
		return sex;
	}
	public void setSex(String sex){
		this.sex=sex;
	}
	
	public String getCountryCd(){
		return countryCd;
	}
	public void setCountryCd(String countryCd){
		this.countryCd=countryCd;
	}
	
	public String getAddress(){
		return address;
	}
	public void setAddress(String address){
		this.address=address;
	}
	
	public String getPhoneNumber(){
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber){
		this.phoneNumber=phoneNumber;
	}
	
	public String getEmail(){
		return email;
	}
	public void setEmail(String email){
		this.email=email;
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
