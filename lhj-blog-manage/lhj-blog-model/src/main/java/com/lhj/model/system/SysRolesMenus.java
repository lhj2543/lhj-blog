package com.lhj.model.system;

import com.lhj.common.model.Pages;

/**
 * 角色分配菜单权限模型类 @author MyEclipse Persistence Tools
 */

public class SysRolesMenus extends Pages<SysRolesMenus> 	{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6483209449538941097L;
	
	
	private String sid; //主键ID
	private String appId;
	private String roleId; //外键字段
	private String menuId; //外键字段
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
	/**
	 * @return the menuId
	 */
	public String getMenuId() {
		return menuId;
	}
	/**
	 * @param menuId the menuId to set
	 */
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

}