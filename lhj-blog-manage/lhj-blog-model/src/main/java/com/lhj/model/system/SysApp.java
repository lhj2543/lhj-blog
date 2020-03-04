package com.lhj.model.system;

import java.math.BigDecimal;
import java.util.Date;
import java.lang.String;

import com.lhj.common.model.Pages;

/**
 * table name:		SYS_APP <br/>
 * table nameCn:	 <br/>
 * entity name:		SysApp <br/>
 * author pojofactory pojo builder
 * created 2020-3-2 21:28:53
 */
public class SysApp extends Pages<SysApp>{
	
	// fields start
	/**
	 * colNameCn 	主键
	 * type 		String
	 * len 		50
	 */
	private String sid;
	/**
	 * colNameCn 	父节点Id
	 * type 		String
	 * len 		50
	 */
	private String parentId;
	/**
	 * colNameCn 	区域
	 * type 		String
	 * len 		20
	 */
	private String localeId;
	/**
	 * colNameCn 	应用名称
	 * type 		String
	 * len 		255
	 */
	private String appName;
	/**
	 * colNameCn 	显示名称
	 * type 		String
	 * len 		255
	 */
	private String displayName;
	/**
	 * colNameCn 	应用url
	 * type 		String
	 * len 		512
	 */
	private String url;
	/**
	 * colNameCn 	状态：0=不显示，1=显示
	 * type 		String
	 * len 		2
	 */
	private String status;
	/**
	 * colNameCn 	排序
	 * type 		BigDecimal
	 * len 		10
	 */
	private BigDecimal sortKey;
	/**
	 * colNameCn 	备注
	 * type 		String
	 * len 		512
	 */
	private String notes;
	// fields end
	
	
	// get,set methods
	public String getSid(){
		return sid;
	}
	public void setSid(String sid){
		this.sid=sid;
	}
	
	public String getParentId(){
		return parentId;
	}
	public void setParentId(String parentId){
		this.parentId=parentId;
	}
	
	public String getLocaleId(){
		return localeId;
	}
	public void setLocaleId(String localeId){
		this.localeId=localeId;
	}
	
	public String getAppName(){
		return appName;
	}
	public void setAppName(String appName){
		this.appName=appName;
	}
	
	public String getDisplayName(){
		return displayName;
	}
	public void setDisplayName(String displayName){
		this.displayName=displayName;
	}
	
	public String getUrl(){
		return url;
	}
	public void setUrl(String url){
		this.url=url;
	}
	
	public String getStatus(){
		return status;
	}
	public void setStatus(String status){
		this.status=status;
	}
	
	public BigDecimal getSortKey(){
		return sortKey;
	}
	public void setSortKey(BigDecimal sortKey){
		this.sortKey=sortKey;
	}
	
	public String getNotes(){
		return notes;
	}
	public void setNotes(String notes){
		this.notes=notes;
	}
	
	
	
	
	
	//get,set methods end
	

}
