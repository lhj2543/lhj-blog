package com.lhj.model.system;

import java.math.BigDecimal;
import java.util.Date;
import java.lang.String;
import java.util.HashSet;
import java.util.Set;

import com.lhj.common.model.Pages;

/**
 * table name:		SYS_MENU <br/>
 * table nameCn:	 <br/>
 * entity name:		SysMenu <br/>
 * author pojofactory pojo builder
 * created 2020-3-8 21:08:52
 */
public class SysMenu extends Pages<SysMenu>{
	
	// fields start
	/**
	 * colNameCn 	主键
	 * type 		String
	 * len 		50
	 */
	private String sid;
	/**
	 * colNameCn 	应用ID
	 * type 		String
	 * len 		50
	 */
	private String appId;
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
	 * colNameCn 	菜单名
	 * type 		String
	 * len 		100
	 */
	private String menuName;
	/**
	 * colNameCn 	菜单显示名
	 * type 		String
	 * len 		255
	 */
	private String displayName;
	/**
	 * colNameCn 	url
	 * type 		String
	 * len 		512
	 */
	private String url;
	/**
	 * colNameCn 	页面文件路径
	 * type 		String
	 * len 		512
	 */
	private String pagePath;
	/**
	 * colNameCn 	图标
	 * type 		String
	 * len 		100
	 */
	private String icon;
	/**
	 * colNameCn 	类别
	 * type 		String
	 * len 		20
	 */
	private String category;
	/**
	 * colNameCn 	类型：0=菜单，1=iframe，2=按钮
	 * type 		String
	 * len 		2
	 */
	private String type;
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

	private Set<SysRole> userRoles = new HashSet<SysRole>(0);
	// fields end
	
	
	// get,set methods
	public String getSid(){
		return sid;
	}
	public void setSid(String sid){
		this.sid=sid;
	}
	
	public String getAppId(){
		return appId;
	}
	public void setAppId(String appId){
		this.appId=appId;
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
	
	public String getMenuName(){
		return menuName;
	}
	public void setMenuName(String menuName){
		this.menuName=menuName;
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
	
	public String getPagePath(){
		return pagePath;
	}
	public void setPagePath(String pagePath){
		this.pagePath=pagePath;
	}
	
	public String getIcon(){
		return icon;
	}
	public void setIcon(String icon){
		this.icon=icon;
	}
	
	public String getCategory(){
		return category;
	}
	public void setCategory(String category){
		this.category=category;
	}
	
	public String getType(){
		return type;
	}
	public void setType(String type){
		this.type=type;
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

	public Set<SysRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Set<SysRole> userRoles) {
		this.userRoles = userRoles;
	}


	//get,set methods end
	

}
