package com.lhj.model.system;

import com.lhj.common.model.Pages;

/**
 * SysUsersMenus entity. @author MyEclipse Persistence Tools
 */

public class SysUsersMenus extends Pages<SysUsersMenus>{

	/**
     * 
     */
    private static final long serialVersionUID = -2082290184868121208L;
    // Fields

	private String sid;
	private String appId;
	private String userId;
	private String menuId;

	// Constructors


	// Property accessors

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

	public String getMenuId() {
		return this.menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
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


}