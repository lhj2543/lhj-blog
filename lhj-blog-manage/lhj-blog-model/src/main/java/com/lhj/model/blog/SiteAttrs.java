package com.lhj.model.blog;

import java.math.BigDecimal;
import java.util.Date;
import java.lang.String;

import com.lhj.common.model.Pages;

/**
 * table name:		SITE_ATTRS <br/>
 * table nameCn:	 <br/>
 * entity name:		SiteAttrs <br/>
 * author pojofactory pojo builder
 * created 2020-4-19 19:28:03
 */
public class SiteAttrs extends Pages<SiteAttrs>{
	
	// fields start
	/**
	 * colNameCn 	区域
	 * type 		String
	 * len 		20
	 */
	private String localeId;
	/**
	 * colNameCn 	属性key
	 * type 		String
	 * len 		512
	 */
	private String attrKey;
	/**
	 * colNameCn 	属性值
	 * type 		String
	 * len 		2,147,483,647
	 */
	private String attrValue;
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
	
	public String getLocaleId(){
		return localeId;
	}
	public void setLocaleId(String localeId){
		this.localeId=localeId;
	}
	
	public String getAttrKey(){
		return attrKey;
	}
	public void setAttrKey(String attrKey){
		this.attrKey=attrKey;
	}
	
	public String getAttrValue(){
		return attrValue;
	}
	public void setAttrValue(String attrValue){
		this.attrValue=attrValue;
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
