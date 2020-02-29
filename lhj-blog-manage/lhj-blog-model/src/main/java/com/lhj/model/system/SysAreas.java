package com.lhj.model.system;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lhj.common.model.Pages;

import java.util.HashSet;
import java.util.Set;

/**
 * 系统区域模型类.
 * @author Jonny zou.
 * @since 2015.10.08
 */
public class SysAreas extends Pages<SysAreas> {

    /**
	 * 
	 */
	private static final long serialVersionUID = -1681722196411113395L;
	
	// 区域编号
	private String sid;
	
	// 区域编号（父级）
	private String parentId;
	
	// 区域编码
	private String areaCode;
	
	// 区域名称
	private String areaName;
	
	// 区域节点（树型）
	private Integer isLeaf;
	
	// 区域排序（最大排前）
	private Integer ordered;
	
	// 区域排序（最大排前）
	private Integer areaLevel;
	
	/**
	 * 关联机构对象
	 */
	@JsonIgnore
	private Set<SysOrgan> sysOrgans = new HashSet<SysOrgan>(0);

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

	public String getAreaCode() {
		return this.areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getAreaName() {
		return this.areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
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

	public Integer getAreaLevel() {
		return areaLevel;
	}

	public void setAreaLevel(Integer areaLevel) {
		this.areaLevel = areaLevel;
	}

	public Set<SysOrgan> getSysOrgans() {
		return this.sysOrgans;
	}

	public void setSysOrgans(Set<SysOrgan> sysOrgans) {
		this.sysOrgans = sysOrgans;
	}

}