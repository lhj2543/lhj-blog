package com.lhj.model.system;

import com.lhj.common.model.Pages;

/**
 * 系统机构组别
 */

public class SysOrganGroups extends Pages<SysOrganGroups> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -39821069934390023L;

	private String sid; // 主键
	private String organId; // 机构id
	private String groupId; // 组别id
	/**
	 * 关联组别表
	 */
	private SysGroups sysGroups;
	
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
	 * @return the organId
	 */
	public String getOrganId() {
		return organId;
	}
	/**
	 * @param organId the organId to set
	 */
	public void setOrganId(String organId) {
		this.organId = organId;
	}
	/**
	 * @return the groupId
	 */
	public String getGroupId() {
		return groupId;
	}
	/**
	 * @param groupId the groupId to set
	 */
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	/**
	 * @return the sysGroups
	 */
	public SysGroups getSysGroups() {
		return sysGroups;
	}
	/**
	 * @param sysGroups the sysGroups to set
	 */
	public void setSysGroups(SysGroups sysGroups) {
		this.sysGroups = sysGroups;
	}
	
}