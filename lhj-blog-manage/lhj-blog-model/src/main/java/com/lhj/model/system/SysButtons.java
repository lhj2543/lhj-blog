package com.lhj.model.system;

import com.lhj.common.model.Pages;

/**
 * table name:		SYS_BUTTONS <br/>
 * table nameCn:	 <br/>
 * entity name:		SysButtons <br/>
 * @author pojofactory pojo builder
 * @version 1.0
 * @created 2018-1-5 8:57:56
 */
public class SysButtons extends Pages<SysButtons>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8366111625402425738L;
	// fields start
	/**
	 * @colName 	SID
	 * @colNameCn 	主键
	 * @type 		String
	 * @isKey 		true
	 * @isForenKey 	false
	 * @len 		32
	 */
	private String sid;
	/**
	 * @colName 	APP_ID
	 * @colNameCn 	应用ID
	 * @type 		String
	 * @isKey 		false
	 * @isForenKey 	false
	 * @len 		32
	 */
	private String appId;
	/**
	 * @colName 	MENU_ID
	 * @colNameCn 	菜单ID
	 * @type 		String
	 * @isKey 		false
	 * @isForenKey 	false
	 * @len 		32
	 */
	private String menuId;
	/**
	 * @colName 	NAME
	 * @colNameCn 	按钮名称(一个菜单下唯一)
	 * @type 		String
	 * @isKey 		false
	 * @isForenKey 	false
	 * @len 		64
	 */
	private String name;
	/**
	 * @colName 	TITLE
	 * @colNameCn 	按钮标题（显示名称）
	 * @type 		String
	 * @isKey 		false
	 * @isForenKey 	false
	 * @len 		64
	 */
	private String title;
	/**
	 * @colName 	ALIAS
	 * @colNameCn 	按钮别名（授权树中显示别名）
	 * @type 		String
	 * @isKey 		false
	 * @isForenKey 	false
	 * @len 		64
	 */
	private String alias;
	/**
	 * @colName 	ACT_URL
	 * @colNameCn 	请求路径(用户url授权）
	 * @type 		String
	 * @isKey 		false
	 * @isForenKey 	false
	 * @len 		512
	 */
	private String actUrl;
	/**
	 * @colName 	ICON
	 * @colNameCn 	按钮图标
	 * @type 		String
	 * @isKey 		false
	 * @isForenKey 	false
	 * @len 		64
	 */
	private String icon;
	/**
	 * @colName 	IS_SHOW
	 * @colNameCn 	是否默认显示：0否，1是
	 * @type 		Integer
	 * @isKey 		false
	 * @isForenKey 	false
	 * @len 		10
	 */
	private Integer isShow;
	/**
	 * @colName 	POSITION
	 * @colNameCn 	按钮显示位置（组）
	 * @type 		String
	 * @isKey 		false
	 * @isForenKey 	false
	 * @len 		200
	 */
	private String position;
	/**
	 * @colName 	SETTINGS
	 * @colNameCn 	按钮设置（）
	 * @type 		String
	 * @isKey 		false
	 * @isForenKey 	false
	 * @len 		1,024
	 */
	private String settings;
	/**
	 * @colName 	ORDERED
	 * @colNameCn 	显示顺序
	 * @type 		Integer
	 * @isKey 		false
	 * @isForenKey 	false
	 * @len 		10
	 */
	private Integer ordered;
	// fields end
	
	
	
	// 非持久化属性
    //////////////////////////////////////////
    private String roleId;
    private String userId;
    private boolean checked=false;
    private boolean chkDisabled=false;
    
	
	
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
	
	public String getMenuId(){
		return menuId;
	}
	public void setMenuId(String menuId){
		this.menuId=menuId;
	}
	
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name=name;
	}
	
	public String getTitle(){
		return title;
	}
	public void setTitle(String title){
		this.title=title;
	}
	
	public String getAlias(){
		return alias;
	}
	public void setAlias(String alias){
		this.alias=alias;
	}
	
	public String getActUrl(){
		return actUrl;
	}
	public void setActUrl(String actUrl){
		this.actUrl=actUrl;
	}
	
	public String getIcon(){
		return icon;
	}
	public void setIcon(String icon){
		this.icon=icon;
	}
	
	public Integer getIsShow(){
		return isShow;
	}
	public void setIsShow(Integer isShow){
		this.isShow=isShow;
	}
	
	public String getPosition(){
		return position;
	}
	public void setPosition(String position){
		this.position=position;
	}
	
	public String getSettings(){
		return settings;
	}
	public void setSettings(String settings){
		this.settings=settings;
	}
	
	public Integer getOrdered(){
		return ordered;
	}
	public void setOrdered(Integer ordered){
		this.ordered=ordered;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public boolean isChkDisabled() {
		return chkDisabled;
	}
	public void setChkDisabled(boolean chkDisabled) {
		this.chkDisabled = chkDisabled;
	}
	
	
	//get,set methods end
	

}
