package com.lhj.model.system;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lhj.common.model.Pages;

import java.util.HashSet;
import java.util.Set;

public class SysPosition extends Pages<SysPosition> {

	private static final long serialVersionUID = -1612524470323680096L;
	//职位id
	private String sid;
	//职位编码
	private String posCode;
	//职位名称
	private String posName;
	//排序字段
	private Integer ordered;
	//职位描述
	private String posDesc;
	
	//关联机构对象
	private String orgId;
	private String orgName;
	private SysOrgan sysOrg;
	private SysUsers username;
	
	//关联的用户职位表
	@JsonIgnore
	private Set<SysUsersPosition>sysUsersPositions = new HashSet<SysUsersPosition>(0);

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getPosCode() {
		return posCode;
	}

	public void setPosCode(String posCode) {
		this.posCode = posCode;
	}

	public String getPosName() {
		return posName;
	}

	public void setPosName(String posName) {
		this.posName = posName;
	}

	public Integer getOrdered() {
		return ordered;
	}

	public void setOrdered(Integer ordered) {
		this.ordered = ordered;
	}

	public String getPosDesc() {
		return posDesc;
	}

	public void setPosDesc(String posDesc) {
		this.posDesc = posDesc;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public Set<SysUsersPosition> getSysUsersPositions() {
		return sysUsersPositions;
	}

	public void setSysUsersPositions(Set<SysUsersPosition> sysUsersPositions) {
		this.sysUsersPositions = sysUsersPositions;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public SysOrgan getSysOrg() {
		return sysOrg;
	}

	public void setSysOrg(SysOrgan sysOrg) {
		this.sysOrg = sysOrg;
	}

	public SysUsers getUsername() {
		return username;
	}

	public void setUsername(SysUsers username) {
		this.username = username;
	}

}