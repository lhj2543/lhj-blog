package com.lhj.model.system;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lhj.common.model.Pages;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 系统组织机构
 */

public class SysOrgan extends Pages<SysOrgan> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5232947733521636988L;

	private String sid;			//主键
	private String parentId;	//父节点
	private String orgName;		//机构名称
	private String orgAlias;	//机构别名
	private String orgCode;		//机构编码
	private String orgType;		//机构类型
	private String orgLevel;	//区域级别（1：省级，2：市州级，3：县区级，4：科所级）
	private Integer isLeaf;		//是否节点（1父目录，0子节点）
	private Integer ordered;	//排序字段
	private String orgHttp;		//机构地址（个性化网站链接）
	private String orgTheme;	//机构主题（个性化主题风格）
	private String orgDesc;		//机构描述

	/**
	 * 关联区域表
	 */
	private SysAreas sysAreas;
	private String areaId;		//区域id
	private String areaName;	//区域名称
	
	
	 // 非持久化属性
    //////////////////////////////////////////
	private String groupName;
	private String createdByName;
    private String roleId;
    private String userId;
    private String groupId;
    private boolean checked=false;
    private boolean chkDisabled=false;
    
    private List<Object> children=new ArrayList<Object>();

	/**
	 * 关联职位表
	 */
	@JsonIgnore
	private Set<SysPosition> sysPositions = new HashSet<SysPosition>(0);
	/**
	 * 关联角色
	 */
	@JsonIgnore
	private Set<SysRoles> sysRoles = new HashSet<SysRoles>(0);

	/**
	 * 关联机构组别表
	 */
	private Set<SysOrganGroups> sysOrganGroupses = new HashSet<SysOrganGroups>(0);

	public Set<SysRoles> getSysRoles() {
		return sysRoles;
	}

	public void setSysRoles(Set<SysRoles> sysRoles) {
		this.sysRoles = sysRoles;
	}

	/**
	 * 关联用户表
	 */
	@JsonIgnore
	private Set<SysUsers> sysUserses = new HashSet<SysUsers>(0);

	/**
	 * 关联机构角色表
	 */
	@JsonIgnore
	private Set<SysRolesOrgans> sysRolesOrgans = new HashSet<SysRolesOrgans>(0);

	public String getSid() {
		return this.sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public SysAreas getSysAreas() {
		return sysAreas;
	}

	public void setSysAreas(SysAreas sysAreas) {
		this.sysAreas = sysAreas;
	}

	public String getParentId() {
		return this.parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getOrgName() {
		return this.orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getOrgAlias() {
		return this.orgAlias;
	}

	public void setOrgAlias(String orgAlias) {
		this.orgAlias = orgAlias;
	}

	public String getOrgCode() {
		return this.orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getOrgType() {
		return this.orgType;
	}

	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}

	public String getOrgLevel() {
		return this.orgLevel;
	}

	public void setOrgLevel(String orgLevel) {
		this.orgLevel = orgLevel;
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

	public String getOrgHttp() {
		return this.orgHttp;
	}

	public void setOrgHttp(String orgHttp) {
		this.orgHttp = orgHttp;
	}

	public String getOrgTheme() {
		return this.orgTheme;
	}

	public void setOrgTheme(String orgTheme) {
		this.orgTheme = orgTheme;
	}

	public String getOrgDesc() {
		return this.orgDesc;
	}

	public void setOrgDesc(String orgDesc) {
		this.orgDesc = orgDesc;
	}

	/**
	 * @return the sysPositions
	 */
	public Set<SysPosition> getSysPositions() {
		return sysPositions;
	}

	/**
	 * @param sysPositions
	 *            the sysPositions to set
	 */
	public void setSysPositions(Set<SysPosition> sysPositions) {
		this.sysPositions = sysPositions;
	}

	/**
	 * @return the sysUserses
	 */
	public Set<SysUsers> getSysUserses() {
		return sysUserses;
	}
	
	/**
	 * @param sysOrganGroupses the sysOrganGroupses to set
	 */
	public void setSysOrganGroupses(Set<SysOrganGroups> sysOrganGroupses) {
		this.sysOrganGroupses = sysOrganGroupses;
	}
	
	/**
	 * @return the sysOrganGroupses
	 */
	public Set<SysOrganGroups> getSysOrganGroupses() {
		return sysOrganGroupses;
	}

	/**
	 * @param sysUserses
	 *            the sysUserses to set
	 */
	public void setSysUserses(Set<SysUsers> sysUserses) {
		this.sysUserses = sysUserses;
	}



	/**
     * @return the sysRolesOrgans
     */
    public Set<SysRolesOrgans> getSysRolesOrgans() {
        return sysRolesOrgans;
    }

    /**
     * @param sysRolesOrgans the sysRolesOrgans to set
     */
    public void setSysRolesOrgans(Set<SysRolesOrgans> sysRolesOrgans) {
        this.sysRolesOrgans = sysRolesOrgans;
    }

    public String getAreaId() {
		return (sysAreas == null) ? "" : sysAreas.getSid();
	}

	public void setAreaId(String areaId) {
		if (sysAreas == null) {
			sysAreas = new SysAreas();
		}
		sysAreas.setSid(areaId);
	}

	public String getAreaName() {
		return (sysAreas == null) ? "" : sysAreas.getAreaName();
	}

	public void setAreaName(String areaName) {
		if (sysAreas == null) {
			sysAreas = new SysAreas();
		}
		sysAreas.setAreaName(areaName);
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
	 * @return the checked
	 */
	public boolean isChecked() {
		return checked;
	}

	/**
	 * @param checked the checked to set
	 */
	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	/**
	 * @return the chkDisabled
	 */
	public boolean isChkDisabled() {
		return chkDisabled;
	}

	/**
	 * @param chkDisabled the chkDisabled to set
	 */
	public void setChkDisabled(boolean chkDisabled) {
		this.chkDisabled = chkDisabled;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	/**
	 * @return the groupName
	 */
	public String getGroupName() {
		return groupName;
	}

	/**
	 * @param groupName the groupName to set
	 */
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	/**
	 * @return the createdByName
	 */
	public String getCreatedByName() {
		return createdByName;
	}

	/**
	 * @param createdByName the createdByName to set
	 */
	public void setCreatedByName(String createdByName) {
		this.createdByName = createdByName;
	}

	public List<Object> getChildren() {
		return children;
	}

	public void setChildren(List<Object> children) {
		this.children = children;
	}
	
	
}