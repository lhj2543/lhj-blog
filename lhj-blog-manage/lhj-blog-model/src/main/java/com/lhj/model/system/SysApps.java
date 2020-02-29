package com.lhj.model.system;

import com.lhj.common.model.Pages;

/**
 * 系统应用类
 */

public class SysApps extends Pages<SysApps> {

    private static final long serialVersionUID = 1327908998065170515L;

	private String sid;            //主键	
	private String parentId;       //父节点代码
	private String appName;        //应用名称
	private String appShort;       //应用简称（应用英文名或首字母）
	private String appHttp;        //应用主机（http://192.168.2.xxx）
	private String appUrl;         //系统主页（访问的系统主页）
	private String appDesc;        //应用描述（可以介绍应用与功能说明）
	private Integer isLeaf;        //是否节点（1父目录，0子节点）
	private Integer ordered;       //排序字段


	public String getSid() {
		return this.sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getParentId() {
		return this.parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getAppName() {
		return this.appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getAppShort() {
		return this.appShort;
	}

	public void setAppShort(String appShort) {
		this.appShort = appShort;
	}

	public String getAppHttp() {
		return this.appHttp;
	}

	public void setAppHttp(String appHttp) {
		this.appHttp = appHttp;
	}

	public String getAppUrl() {
		return this.appUrl;
	}

	public void setAppUrl(String appUrl) {
		this.appUrl = appUrl;
	}

	public String getAppDesc() {
		return this.appDesc;
	}

	public void setAppDesc(String appDesc) {
		this.appDesc = appDesc;
	}

	public Integer getIsLeaf() {
		return this.isLeaf;
	}

	public void setIsLeaf(Integer isLeaf) {
		this.isLeaf = isLeaf;
	}

	public Integer getOrdered() {
		return this.ordered;
	}

	public void setOrdered(Integer ordered) {
		this.ordered = ordered;
	}


}