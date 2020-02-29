package com.lhj.model.system;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lhj.common.model.Pages;

/**
 * SysLinks entity. @author MyEclipse Persistence Tools
 */

public class SysLinks extends Pages<SysLinks> {

	/**
     * 
     */
	private static final long serialVersionUID = 8685943260426902036L;
	// Fields

	private String sid;
	private String linkName;
	private String linkUrl;
	private String linkIcon;
	private String linkDesc;
	private Integer status;
	private Integer ordered;

	private String menuId;

	@JsonIgnore
	private SysApps sysApp;
	private String appId; // 系统ID
	private String appName; // 系统名称

	// Constructors

	// Property accessors

	public String getSid() {
		return this.sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getLinkName() {
		return this.linkName;
	}

	public void setLinkName(String linkName) {
		this.linkName = linkName;
	}

	public String getLinkUrl() {
		return this.linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	public String getLinkIcon() {
		return this.linkIcon;
	}

	public void setLinkIcon(String linkIcon) {
		this.linkIcon = linkIcon;
	}

	public String getLinkDesc() {
		return this.linkDesc;
	}

	public void setLinkDesc(String linkDesc) {
		this.linkDesc = linkDesc;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getOrdered() {
		return this.ordered;
	}

	public void setOrdered(Integer ordered) {
		this.ordered = ordered;
	}

	/**
	 * @return the appId
	 */
	public String getAppId() {
		if (sysApp != null) {
			appId = sysApp.getSid();
		}
		return appId;
	}

	/**
	 * @param appId
	 *            the appId to set
	 */
	public void setAppId(String appId) {
		this.appId = appId;
		if (sysApp == null) {
			sysApp = new SysApps();
		}
		sysApp.setSid(appId);
	}

	/**
	 * @return the appName
	 */
	public String getAppName() {
		if (sysApp != null) {
			appName = sysApp.getAppName();
		}
		return appName;
	}

	/**
	 * @param appName
	 *            the appName to set
	 */
	public void setAppName(String appName) {
		this.appName = appName;
		if (sysApp == null) {
			sysApp = new SysApps();
		}
		sysApp.setAppName(appName);
	}

	/**
	 * @return the sysApp
	 */
	public SysApps getSysApp() {
		return sysApp;
	}

	/**
	 * @param sysApp
	 *            the sysApp to set
	 */
	public void setSysApp(SysApps sysApp) {
		this.sysApp = sysApp;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

}