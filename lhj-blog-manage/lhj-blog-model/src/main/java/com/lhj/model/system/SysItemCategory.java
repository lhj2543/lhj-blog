package com.lhj.model.system;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.lang.String;
import java.util.LinkedList;
import java.util.List;

import com.lhj.common.model.Pages;

/**
 * table name:		SYS_ITEM_CATEGORY <br/>
 * table nameCn:	 <br/>
 * entity name:		SysItemCategory <br/>
 * author pojofactory pojo builder
 * created 2020-3-17 22:50:05
 */
public class SysItemCategory extends Pages<SysItemCategory>{
	
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

	private List<SysItemCategory> children = new LinkedList<SysItemCategory>();

	private List<SysItem> sysItems = new ArrayList<SysItem>();

	//是否展开
	private boolean expand;

	// fields end
	
	
	// get,set methods

	public List<SysItemCategory> getChildren() {
		return children;
	}

	public void setChildren(List<SysItemCategory> children) {
		this.children = children;
	}

	public boolean isExpand() {
		return expand;
	}

	public void setExpand(boolean expand) {
		this.expand = expand;
	}

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

	public List<SysItem> getSysItems() {
		return sysItems;
	}

	public void setSysItems(List<SysItem> sysItems) {
		this.sysItems = sysItems;
	}


	//get,set methods end
	

}
