package com.lhj.model.system;

import com.lhj.common.model.Pages;

/**
 * SysHols entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class SysHols extends Pages<SysHols> {

	// Fields

	private String sid;
	private String holName;
	private String holYear;
	private String holMonth;
	private String holDay;

	// Property accessors

	public String getSid() {
		return this.sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getHolName() {
		return this.holName;
	}

	public void setHolName(String holName) {
		this.holName = holName;
	}

	public String getHolYear() {
		return this.holYear;
	}

	public void setHolYear(String holYear) {
		this.holYear = holYear;
	}

	public String getHolMonth() {
		return this.holMonth;
	}

	public void setHolMonth(String holMonth) {
		this.holMonth = holMonth;
	}

	public String getHolDay() {
		return this.holDay;
	}

	public void setHolDay(String holDay) {
		this.holDay = holDay;
	}

}