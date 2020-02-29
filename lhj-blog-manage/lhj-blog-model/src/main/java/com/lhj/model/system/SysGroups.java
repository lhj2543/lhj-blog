package com.lhj.model.system;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lhj.common.model.Pages;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 系统组别model
 */

public class SysGroups extends Pages<SysGroups> {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = -3109507889807812668L;

	private String sid; // 主键
	private String parentId; // 父节点
	private String groupCode; // 组别编码
	private String groupName; // 组别名称
	private String groupType; // 组别类型：00其他,01机构组,02用户组,03菜单组,04配置组
	private String groupDesc; // 组别描述
	private Integer isLeaf; // 是否节点（1父目录，0子节点）
	private Integer ordered; // 排序字段
	
	// 非持久化属性
    //////////////////////////////////////////
    private boolean checked=false;
    private boolean chkDisabled=false;
    private boolean onlymenu=false;	 // 只加载菜单不加载按钮
    private boolean loadLinks=true;  // 菜单加载时是否加载首页（快捷操作）

	/**
	 * 关联用户组别表
	 */
	@JsonIgnore
	private Set<SysGroups> sysUsersGroupses = new HashSet<SysGroups>(0);

	/**
	 * 关联机构组别表
	 */
	@JsonIgnore
	private Set<SysOrganGroups> sysOrganGroupses = new HashSet<SysOrganGroups>(0);
	
	private List<Object> children=new ArrayList<Object>();

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

	public String getGroupCode() {
		return this.groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public String getGroupName() {
		return this.groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getGroupDesc() {
		return this.groupDesc;
	}

	public void setGroupDesc(String groupDesc) {
		this.groupDesc = groupDesc;
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

	public Set<SysGroups> getSysUsersGroupses() {
		return this.sysUsersGroupses;
	}

	public void setSysUsersGroupses(Set<SysGroups> sysUsersGroupses) {
		this.sysUsersGroupses = sysUsersGroupses;
	}

	public Set<SysOrganGroups> getSysOrganGroupses() {
		return this.sysOrganGroupses;
	}

	public void setSysOrganGroupses(Set<SysOrganGroups> sysOrganGroupses) {
		this.sysOrganGroupses = sysOrganGroupses;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public boolean isChkDisabled() {
		return chkDisabled;
	}

	public void setChkDisabled(boolean chkDisabled) {
		this.chkDisabled = chkDisabled;
	}

	public boolean isOnlymenu() {
		return onlymenu;
	}

	public void setOnlymenu(boolean onlymenu) {
		this.onlymenu = onlymenu;
	}

	public boolean isLoadLinks() {
		return loadLinks;
	}

	public void setLoadLinks(boolean loadLinks) {
		this.loadLinks = loadLinks;
	}

	public List<Object> getChildren() {
		return children;
	}

	public void setChildren(List<Object> children) {
		this.children = children;
	}

	/**
     * @return the groupType
     */
    public String getGroupType() {
        return groupType;
    }

    /**
     * @param groupType the groupType to set
     */
    public void setGroupType(String groupType) {
        this.groupType = groupType;
    }

}