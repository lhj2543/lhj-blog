package com.lhj.common.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Pojo<T> implements Serializable{
	
	private static final long serialVersionUID = -4658799939880991528L;
	private String id;
	private String sid;
	private String orgField = "org_id";

	private String orgId;

	private String userId;

	private String orderby;

	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date created;


	private String createdBy;

	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date lastUpdated;

	private String lastUpdatedBy;

	private boolean success;

	private String message;

	private String history;

	private Set<Object> errors = new HashSet<Object>();

	public Pojo() {}

	public Date getCreated()
	{
		return this.created;
	}



	public void setCreated(Date created)
	{
		this.created = created;
	}



	public String getCreatedBy()
	{
		return this.createdBy;
	}



	public void setCreatedBy(String createdBy)
	{
		this.createdBy = createdBy;
	}



	public Date getLastUpdated()
	{
		return this.lastUpdated;
	}



	public void setLastUpdated(Date lastUpdated)
	{
		this.lastUpdated = lastUpdated;
	}



	public String getLastUpdatedBy()
	{
		return this.lastUpdatedBy;
	}



	public void setLastUpdatedBy(String lastUpdatedBy)
	{
		this.lastUpdatedBy = lastUpdatedBy;
	}



	public boolean isSuccess()
	{
		return this.success;
	}



	public void setSuccess(boolean success)
	{
		this.success = success;
	}



	public String getMessage()
	{
		return this.message;
	}



	public void setMessage(String message)
	{
		this.message = message;
	}



	public String getHistory()
	{
		return this.history;
	}



	public void setHistory(String history)
	{
		this.history = history;
	}



	public Set<Object> getErrors()
	{
		return this.errors;
	}



	public void setErrors(Set<Object> errors)
	{
		this.errors = errors;
	}





	/*public String toString()
	{
		return ReflectionToStringBuilder.toString(this);
	}*/




	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getOrgId()
	{
		return this.orgId;
	}



	public void setOrgId(String orgId)
	{
		this.orgId = orgId;
	}



	public String getUserId()
	{
		return this.userId;
	}



	public void setUserId(String userId)
	{
		this.userId = userId;
	}



	public String getOrgField()
	{
		return this.orgField;
	}



	public void setOrgField(String orgField)
	{
		this.orgField = orgField;
	}



	public String getOrderby()
	{
		return this.orderby;
	}



	public void setOrderby(String orderby)
	{
		this.orderby = orderby;
	}
}
