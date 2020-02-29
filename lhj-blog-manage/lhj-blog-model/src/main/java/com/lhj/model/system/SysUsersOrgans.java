package com.lhj.model.system;

import com.lhj.common.model.Pages;

/**
 * SysUsersOrgans entity. @author MyEclipse Persistence Tools
 */

public class SysUsersOrgans extends Pages<SysUsersOrgans> {

	// Fields

	/**
     * 
     */
    private static final long serialVersionUID = 111414347204601303L;
    
    
    private String sid;
    private String appId;
	private String userId;
	private String organId;

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

	public String getOrganId() {
		return this.organId;
	}

	public void setOrganId(String organId) {
		this.organId = organId;
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