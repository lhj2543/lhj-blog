package com.lhj.model.system;

import java.math.BigDecimal;
import java.util.Date;
import java.lang.String;
import java.util.List;
import java.util.Map;

import com.lhj.common.model.Pages;

/**
 * table name:		SYS_ITEM <br/>
 * table nameCn:	 <br/>
 * entity name:		SysItem <br/>
 * author pojofactory pojo builder
 * created 2020-3-17 22:50:05
 */
public class SysItem extends Pages<SysItem>{
	
	// fields start
	/**
	 * colNameCn 	主键
	 * type 		String
	 * len 		50
	 */
	private String sid;
	/**
	 * colNameCn 	类别id
	 * type 		String
	 * len 		100
	 */
	private String categoryId;
	/**
	 * colNameCn 	区域
	 * type 		String
	 * len 		20
	 */
	private String localeId;
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

	private String categoryName;

	private List<String> categoryNames;

	//字典集合
	private Map<String,Object> itemMap;


	// fields end
	
	
	// get,set methods
	public String getSid(){
		return sid;
	}
	public void setSid(String sid){
		this.sid=sid;
	}
	
	public String getCategoryId(){
		return categoryId;
	}
	public void setCategoryId(String categoryId){
		this.categoryId=categoryId;
	}
	
	public String getLocaleId(){
		return localeId;
	}
	public void setLocaleId(String localeId){
		this.localeId=localeId;
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

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public List<String> getCategoryNames() {
		return categoryNames;
	}

	public void setCategoryNames(List<String> categoryNames) {
		this.categoryNames = categoryNames;
	}

	public Map<String, Object> getItemMap() {
		return itemMap;
	}

	public void setItemMap(Map<String, Object> itemMap) {
		this.itemMap = itemMap;
	}

	//get,set methods end
	

}
