package com.lhj.model.system;

import com.lhj.common.model.Pages;

/**
 * SysHistoryDetail entity. @author MyEclipse Persistence Tools
 */

public class SysHistoryDetail extends Pages<SysHistoryDetail>{

	// Fields

	/**
     * 
     */
    private static final long serialVersionUID = 7232274304166343860L;
    
    private String sid;
    private String histId;
	private String fieldName;
	private String fieldNameCn;
	private String fieldOldValue;
	private String fieldValue;
	private String fieldOldText;
    private String fieldText;
    
    
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
	 * @return the histId
	 */
	public String getHistId() {
		return histId;
	}
	/**
	 * @param histId the histId to set
	 */
	public void setHistId(String histId) {
		this.histId = histId;
	}
	/**
	 * @return the fieldName
	 */
	public String getFieldName() {
		return fieldName;
	}
	/**
	 * @param fieldName the fieldName to set
	 */
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	/**
	 * @return the fieldNameCn
	 */
	public String getFieldNameCn() {
		return fieldNameCn;
	}
	/**
	 * @param fieldNameCn the fieldNameCn to set
	 */
	public void setFieldNameCn(String fieldNameCn) {
		this.fieldNameCn = fieldNameCn;
	}
	/**
	 * @return the fieldOldValue
	 */
	public String getFieldOldValue() {
		return fieldOldValue;
	}
	/**
	 * @param fieldOldValue the fieldOldValue to set
	 */
	public void setFieldOldValue(String fieldOldValue) {
		this.fieldOldValue = fieldOldValue;
	}
	/**
	 * @return the fieldValue
	 */
	public String getFieldValue() {
		return fieldValue;
	}
	/**
	 * @param fieldValue the fieldValue to set
	 */
	public void setFieldValue(String fieldValue) {
		this.fieldValue = fieldValue;
	}
	/**
	 * @return the fieldOldText
	 */
	public String getFieldOldText() {
		return fieldOldText;
	}
	/**
	 * @param fieldOldText the fieldOldText to set
	 */
	public void setFieldOldText(String fieldOldText) {
		this.fieldOldText = fieldOldText;
	}
	/**
	 * @return the fieldText
	 */
	public String getFieldText() {
		return fieldText;
	}
	/**
	 * @param fieldText the fieldText to set
	 */
	public void setFieldText(String fieldText) {
		this.fieldText = fieldText;
	}
}