package com.lhj.model.blog;

import java.math.BigDecimal;
import java.util.Date;
import java.lang.String;

import com.lhj.common.model.Pages;

/**
 * table name:		NOTEBOOK_MAIN <br/>
 * table nameCn:	 <br/>
 * entity name:		NotebookMain <br/>
 * author pojofactory pojo builder
 * created 2020-3-21 17:43:35
 */
public class NotebookMain extends Pages<NotebookMain>{
	
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
	 * colNameCn 	分类ID
	 * type 		String
	 * len 		100
	 */
	private String categoryId;
	/**
	 * colNameCn 	笔记标题
	 * type 		String
	 * len 		512
	 */
	private String title;
	/**
	 * colNameCn 	笔记内容
	 * type 		String
	 * len 		2,147,483,647
	 */
	private String bodys;
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

	//统配检索
	private String searchValue;
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
	
	public String getCategoryId(){
		return categoryId;
	}
	public void setCategoryId(String categoryId){
		this.categoryId=categoryId;
	}
	
	public String getTitle(){
		return title;
	}
	public void setTitle(String title){
		this.title=title;
	}
	
	public String getBodys(){
		return bodys;
	}
	public void setBodys(String bodys){
		this.bodys=bodys;
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

	public String getSearchValue() {
		return searchValue;
	}

	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}


	//get,set methods end
	

}
