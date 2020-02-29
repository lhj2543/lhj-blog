package com.lhj.model.system;

import com.lhj.common.model.Pages;

/**
 * 用户角色分配模型类 . @author MyEclipse Persistence Tools
 */

public class SysUsersRoles extends Pages<SysUsersRoles> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1452371417591849806L;
	
	private String sid; // 主键ID
	private String appId;
	private String userId;// 外键字段
	private String roleId; // 外键字段
	
	
	/**
	 * @return the sid
	 */
	public String getSid() {
		return sid;
	}
	/**
	 * @param sid the sid to set
	 */
	public void setSid(String sid) {
		this.sid = sid;
	}
	/**
	 * @return the appId
	 */
	public String getAppId() {
		return appId;
	}
	/**
	 * @param appId the appId to set
	 */
	public void setAppId(String appId) {
		this.appId = appId;
	}
	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * @return the roleId
	 */
	public String getRoleId() {
		return roleId;
	}
	/**
	 * @param roleId the roleId to set
	 */
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	
	

}