package com.lhj.model.system;

import java.math.BigDecimal;
import java.util.Date;
import java.lang.String;

import com.lhj.common.model.Pages;

/**
 * table name:		SYS_ITEM_CATEGORY_LOCATION <br/>
 * table nameCn:	 <br/>
 * entity name:		SysItemCategoryLocation <br/>
 * author pojofactory pojo builder
 * created 2020-3-2 21:28:53
 */
public class SysItemCategoryLocation extends Pages<SysItemCategoryLocation>{
	
	// fields start
	/**
	 * colNameCn 	主键
	 * type 		String
	 * len 		50
	 */
	private String sid;
	/**
	 * colNameCn 	类别CD
	 * type 		String
	 * len 		100
	 */
	private String categoryCd;
	/**
	 * colNameCn 	区域
	 * type 		String
	 * len 		20
	 */
	private String localeId;
	/**
	 * colNameCn 	类别名称
	 * type 		String
	 * len 		255
	 */
	private String categoryName;
	/**
	 * colNameCn 	类别显示名
	 * type 		String
	 * len 		255
	 */
	private String displayName;
	/**
	 * colNameCn 	图标
	 * type 		String
	 * len 		255
	 */
	private String icon;
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
	
	public String getCategoryCd(){
		return categoryCd;
	}
	public void setCategoryCd(String categoryCd){
		this.categoryCd=categoryCd;
	}
	
	public String getLocaleId(){
		return localeId;
	}
	public void setLocaleId(String localeId){
		this.localeId=localeId;
	}
	
	public String getCategoryName(){
		return categoryName;
	}
	public void setCategoryName(String categoryName){
		this.categoryName=categoryName;
	}
	
	public String getDisplayName(){
		return displayName;
	}
	public void setDisplayName(String displayName){
		this.displayName=displayName;
	}
	
	public String getIcon(){
		return icon;
	}
	public void setIcon(String icon){
		this.icon=icon;
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
