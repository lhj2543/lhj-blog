package com.lhj.model.system;

import java.math.BigDecimal;
import java.util.*;
import java.lang.String;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lhj.common.model.Pages;
import org.springframework.format.annotation.DateTimeFormat;

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

	/*密码*/
	private String password;
	/**
	 * colNameCn 	许可证
	 */
	private String licence;
	/**
	 * colNameCn 	账户类型:（01=普通用户，02=管理员）
	 */
	private String type;
	/**
	 * colNameCn 	有效开始时间
	 */
	@JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+8")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date startDate;
	/**
	 * colNameCn 	有效结束时间
	 */
	@JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+8")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date endDate;
	/**
	 * colNameCn 	锁住时间
	 */
	@JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+8")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date lockDate;
	/**
	 * colNameCn 	登录失败次数
	 */
	private BigDecimal loginFailureCount;

	/*
	* 非持久化属性
	* 用户token
	 */
	private String token;

	/*
	*	非持久化属性
	*	是否强制登录
	**/
	private String isForceLogin;

	//账户
	private SysAccount sysAccount = new SysAccount();

	/**
	 *  关联账户角色表
	 */
	private Set<SysUserRole> userRoles = new HashSet<SysUserRole>(0);

	//角色状态
	private String roleStatus;

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

	public SysAccount getSysAccount() {
		return sysAccount;
	}

	public void setSysAccount(SysAccount sysAccount) {
		this.sysAccount = sysAccount;
	}

	public Set<SysUserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Set<SysUserRole> userRoles) {
		this.userRoles = userRoles;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLicence() {
		return licence;
	}

	public void setLicence(String licence) {
		this.licence = licence;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getLockDate() {
		return lockDate;
	}

	public void setLockDate(Date lockDate) {
		this.lockDate = lockDate;
	}

	public BigDecimal getLoginFailureCount() {
		return loginFailureCount;
	}

	public void setLoginFailureCount(BigDecimal loginFailureCount) {
		this.loginFailureCount = loginFailureCount;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getIsForceLogin() {
		return isForceLogin;
	}

	public void setIsForceLogin(String isForceLogin) {
		this.isForceLogin = isForceLogin;
	}

	public String getRoleStatus() {
		return roleStatus;
	}

	public void setRoleStatus(String roleStatus) {
		this.roleStatus = roleStatus;
	}
	//get,set methods end
	

}
