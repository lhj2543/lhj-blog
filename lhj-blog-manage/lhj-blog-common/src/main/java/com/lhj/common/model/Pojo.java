package com.lhj.common.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

//在所有相关的类前加上@JsonIgnoreProperties, 作用是json序列化时忽略bean中的一些属性序列化和反序列化时抛出的异常.
// 解决 mybatis 关联查询json 异常
@JsonIgnoreProperties(value = {"handler"})
public class Pojo<T> implements Serializable{

	private static final long serialVersionUID = -4658799939880991528L;
	private String id;
	private String sid;

	private String userCd;
	private String userName;

	private boolean isAdministrator;

	private String orderby;

	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date createdDate;

	private String createdBy;

	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date updateDate;

	private String updateBy;

	private boolean success;

	private String responseCode;

	private String message = "";

	private String history;

	private String customWhere;

	private Set<Object> errors = new HashSet<Object>();

	public Pojo() {}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public String getCreatedBy()
	{
		return this.createdBy;
	}

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public void setCreatedBy(String createdBy)
	{
		this.createdBy = createdBy;
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

	public String getUserCd() {
		return userCd;
	}

	public void setUserCd(String userCd) {
		this.userCd = userCd;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getOrderby()
	{
		return this.orderby;
	}

	public void setOrderby(String orderby)
	{
		this.orderby = orderby;
	}

	public String getCustomWhere() {
		return customWhere;
	}

	public void setCustomWhere(String customWhere) {
		this.customWhere = customWhere;
	}

	public boolean isAdministrator() {
		return isAdministrator;
	}

	public void setAdministrator(boolean administrator) {
		isAdministrator = administrator;
	}
}
