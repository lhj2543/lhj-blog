package com.lhj.model.system;

import com.lhj.common.model.Pages;

/**
 * 系统机构角色model
 */

public class SysRolesOrgans extends Pages<SysRolesOrgans> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7988621789867139452L;

	private String sid;
	private String appId;
	private String organId;
	private String roleId;
	
	
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	
	public String getOrganId() {
		return organId;
	}
	public void setOrganId(String organId) {
		this.organId = organId;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

}