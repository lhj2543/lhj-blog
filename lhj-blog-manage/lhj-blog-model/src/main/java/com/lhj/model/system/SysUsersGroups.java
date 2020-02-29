package com.lhj.model.system;

import com.lhj.common.model.Pages;


public class SysUsersGroups extends Pages<SysUsersGroups> {

    private static final long serialVersionUID = -1591853499231568458L;

    //主键
    private String sid;
	private String appId;
    private String groupId;
    private String userId;
    /**
	 * 关联组别表
	 */
	private SysGroups sysGroups;
    
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
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public SysGroups getSysGroups() {
		return sysGroups;
	}
	public void setSysGroups(SysGroups sysGroups) {
		this.sysGroups = sysGroups;
	}
    
}