package com.lhj.model.system;

import com.lhj.common.model.Pojo;

/**
 * SysLogsDetails entity. @author MyEclipse Persistence Tools
 */

public class SysLogsDetails extends Pojo<SysLogsDetails>{

	/**
     * 
     */
    private static final long serialVersionUID = -872696855645047996L;
    // Fields

	private String sid;
	private String logId;
	private Integer detailIndex;
	private String detailClass;
	private String detailMethod;
	private String detailParams;
	private String detailResualt;
	private Integer status;
	private String exCode;
	private String exMessage;
	

	// Constructors


	// Property accessors

	public String getSid() {
		return this.sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}


	public Integer getDetailIndex() {
		return this.detailIndex;
	}

	public void setDetailIndex(Integer detailIndex) {
		this.detailIndex = detailIndex;
	}

	public String getDetailParams() {
		return this.detailParams;
	}

	public void setDetailParams(String detailParams) {
		this.detailParams = detailParams;
	}

	public String getDetailResualt() {
		return this.detailResualt;
	}

	public void setDetailResualt(String detailResualt) {
		this.detailResualt = detailResualt;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getExCode() {
		return this.exCode;
	}

	public void setExCode(String exCode) {
		this.exCode = exCode;
	}

	public String getExMessage() {
		return this.exMessage;
	}

	public void setExMessage(String exMessage) {
		this.exMessage = exMessage;
	}

    /**
     * @return the detailClass
     */
    public String getDetailClass() {
        return detailClass;
    }

    /**
     * @param detailClass the detailClass to set
     */
    public void setDetailClass(String detailClass) {
        this.detailClass = detailClass;
    }

    /**
     * @return the detailMethod
     */
    public String getDetailMethod() {
        return detailMethod;
    }

    /**
     * @param detailMethod the detailMethod to set
     */
    public void setDetailMethod(String detailMethod) {
        this.detailMethod = detailMethod;
    }

	public String getLogId() {
		return logId;
	}

	public void setLogId(String logId) {
		this.logId = logId;
	}



}