package com.lhj.model.system;

import java.math.BigDecimal;
import java.util.Date;
import java.lang.String;

import com.lhj.common.model.Pages;

/**
 * table name:		SYS_ITEM_LOCATION <br/>
 * table nameCn:	 <br/>
 * entity name:		SysItemLocation <br/>
 * author pojofactory pojo builder
 * created 2020-3-2 21:28:53
 */
public class SysItemLocation extends Pages<SysItemLocation>{
	
	// fields start
	/**
	 * colNameCn 	主键
	 * type 		String
	 * len 		50
	 */
	private String sid;
	/**
	 * colNameCn 	区域
	 * type 		String
	 * len 		20
	 */
	private String localeId;
	/**
	 * colNameCn 	字典CD
	 * type 		String
	 * len 		100
	 */
	private String itemCd;
	/**
	 * colNameCn 	字典key
	 * type 		String
	 * len 		255
	 */
	private String itemKey;
	/**
	 * colNameCn 	字典值
	 * type 		String
	 * len 		512
	 */
	private String itemValue;
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
	
	public String getLocaleId(){
		return localeId;
	}
	public void setLocaleId(String localeId){
		this.localeId=localeId;
	}
	
	public String getItemCd(){
		return itemCd;
	}
	public void setItemCd(String itemCd){
		this.itemCd=itemCd;
	}
	
	public String getItemKey(){
		return itemKey;
	}
	public void setItemKey(String itemKey){
		this.itemKey=itemKey;
	}
	
	public String getItemValue(){
		return itemValue;
	}
	public void setItemValue(String itemValue){
		this.itemValue=itemValue;
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
