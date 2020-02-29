package com.lhj.model.system;


import com.lhj.common.model.Pages;

import java.util.HashSet;
import java.util.Set;

public class SysRoles extends Pages<SysRoles> {

	private static final long serialVersionUID = -5313378891386287712L;
	// 角色id
	private String sid;
	// 角色编码
	private String roleCode;
	// 角色名称
	private String roleName;
	// 角色描述
	private String roleDesc;
	// 关联应用对象
	private String appId;
	//关联机构对象
	private String orgId;
	
	
	
	// 非持久化属性
	private String userId;
    private boolean checked=false;
    private boolean chkDisabled=false;
	
	
	// 关联的用户角色表
	private Set<SysUsersRoles> sysUsersRoleses = new HashSet<SysUsersRoles>(0);
	// 关联的角色菜单表
	private Set<SysRolesMenus> sysRolesMenuses = new HashSet<SysRolesMenus>(0);
	// 关联的机构角色表
	private Set<SysRolesOrgans> sysRolesOrganes = new HashSet<SysRolesOrgans>(0);
	
	
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getRoleCode() {
		return roleCode;
	}
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getRoleDesc() {
		return roleDesc;
	}
	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public Set<SysUsersRoles> getSysUsersRoleses() {
		return sysUsersRoleses;
	}
	public void setSysUsersRoleses(Set<SysUsersRoles> sysUsersRoleses) {
		this.sysUsersRoleses = sysUsersRoleses;
	}
	public Set<SysRolesMenus> getSysRolesMenuses() {
		return sysRolesMenuses;
	}
	public void setSysRolesMenuses(Set<SysRolesMenus> sysRolesMenuses) {
		this.sysRolesMenuses = sysRolesMenuses;
	}
	public Set<SysRolesOrgans> getSysRolesOrganes() {
		return sysRolesOrganes;
	}
	public void setSysRolesOrganes(Set<SysRolesOrgans> sysRolesOrganes) {
		this.sysRolesOrganes = sysRolesOrganes;
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
	
}