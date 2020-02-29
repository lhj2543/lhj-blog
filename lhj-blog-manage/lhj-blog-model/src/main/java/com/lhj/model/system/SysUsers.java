package com.lhj.model.system;

import com.lhj.common.model.Pages;

import java.util.HashSet;
import java.util.Set;

/**
 * 系统用户
 */

public class SysUsers extends Pages<SysUsers> {

	private static final long serialVersionUID = -208761155565024478L;
	
	private String sid;        //主键
	private String orgId;     //机构ID
	private String username;   //登录用户（登录唯一用户/帐号）
	private String password;   //登录口令
	private String realName;   //用户姓名
	private String userAlias;  //用户别名
	private String userType;   //用户类型(01管理员用户、02监察用户、03审批用户、04业务用户、05外部用户、06临时用户)
	private String overTime;   //过期时间（使用按天计算，过期无效为-1，使用状态为03注销，默认0永久使用）
	private String photo;
	private String enabled;    //是否可用（01正常,02禁用,03注销）
	
	//非持久化属性
	private String groupId;
	private String groupName;
	private String createdByName;
	
	/**
	 * 用户所属机构
	 */
	private SysOrgan organ;
	
	
	/**
     *  关联用户职位表
     */
	private Set<SysPosition> sysUsersPositions = new HashSet<SysPosition>(0);
	
	/**
     *  关联用户组别表
     */
	private Set<SysGroups> sysUsersGroupses = new HashSet<SysGroups>(0);
	
	/**
     *  关联用户角色表
     */
	private Set<SysRoles> sysUsersRoleses = new HashSet<SysRoles>(0);

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getUserAlias() {
		return userAlias;
	}

	public void setUserAlias(String userAlias) {
		this.userAlias = userAlias;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getOverTime() {
		return overTime;
	}

	public void setOverTime(String overTime) {
		this.overTime = overTime;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getEnabled() {
		return enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public Set<SysPosition> getSysUsersPositions() {
		return sysUsersPositions;
	}

	public void setSysUsersPositions(Set<SysPosition> sysUsersPositions) {
		this.sysUsersPositions = sysUsersPositions;
	}

	public Set<SysGroups> getSysUsersGroupses() {
		return sysUsersGroupses;
	}

	public void setSysUsersGroupses(Set<SysGroups> sysUsersGroupses) {
		this.sysUsersGroupses = sysUsersGroupses;
	}

	public Set<SysRoles> getSysUsersRoleses() {
		return sysUsersRoleses;
	}

	public void setSysUsersRoleses(Set<SysRoles> sysUsersRoleses) {
		this.sysUsersRoleses = sysUsersRoleses;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public SysOrgan getOrgan() {
		return organ;
	}

	public void setOrgan(SysOrgan organ) {
		this.organ = organ;
	}

	public String getCreatedByName() {
		return createdByName;
	}

	public void setCreatedByName(String createdByName) {
		this.createdByName = createdByName;
	}

}