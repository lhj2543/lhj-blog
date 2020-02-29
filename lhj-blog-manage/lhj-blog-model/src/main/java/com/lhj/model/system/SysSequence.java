package com.lhj.model.system;

public class SysSequence {
	
	private String sysId;
	private String currentDate;
	private String currentValue;
	private Integer increment;
	
	
	/**
	 * @return the sysId
	 */
	public String getSysId() {
		return sysId;
	}
	/**
	 * @param sysId the sysId to set
	 */
	public void setSysId(String sysId) {
		this.sysId = sysId;
	}
	/**
	 * @return the currentDate
	 */
	public String getCurrentDate() {
		return currentDate;
	}
	/**
	 * @param currentDate the currentDate to set
	 */
	public void setCurrentDate(String currentDate) {
		this.currentDate = currentDate;
	}
	/**
	 * @return the currentValue
	 */
	public String getCurrentValue() {
		return currentValue;
	}
	/**
	 * @param currentValue the currentValue to set
	 */
	public void setCurrentValue(String currentValue) {
		this.currentValue = currentValue;
	}
	/**
	 * @return the increment
	 */
	public Integer getIncrement() {
		return increment;
	}
	/**
	 * @param increment the increment to set
	 */
	public void setIncrement(Integer increment) {
		this.increment = increment;
	}
}
