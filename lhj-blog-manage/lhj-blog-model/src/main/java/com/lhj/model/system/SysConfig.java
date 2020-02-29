package com.lhj.model.system;

import com.lhj.common.model.Pages;

/**
 * table name:		SYS_CONFIG <br/>
 * table nameCn:	 <br/>
 * entity name:		SysConfig <br/>
 * @author pojofactory pojo builder
 * @version 1.0
 * @created 2018-1-9 11:17:50
 */
public class SysConfig extends Pages<SysConfig>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7990198845954110297L;
	// fields start
	/**
	 * @colName 	SID
	 * @colNameCn 	
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
	 * @len 		50
	 */
	private String appId;
	/**
	 * @colName 	USER_ID
	 * @colNameCn 	用户ID
	 * @type 		String
	 * @isKey 		false
	 * @isForenKey 	false
	 * @len 		32
	 */
	private String userId;
	/**
	 * @colName 	PARENT_ID
	 * @colNameCn 	上层ID
	 * @type 		String
	 * @isKey 		false
	 * @isForenKey 	false
	 * @len 		32
	 */
	private String parentId;
	/**
	 * @colName 	CFG_NAME
	 * @colNameCn 	配置名称
	 * @type 		String
	 * @isKey 		false
	 * @isForenKey 	false
	 * @len 		100
	 */
	private String cfgName;
	/**
	 * @colName 	CFG_KEY
	 * @colNameCn 	配置KEY（唯一），系统配置SYS_开头，用户配置USER_开头
	 * @type 		String
	 * @isKey 		false
	 * @isForenKey 	false
	 * @len 		50
	 */
	private String cfgKey;
	/**
	 * @colName 	CFG_VALUE
	 * @colNameCn 	配置内容
	 * @type 		String
	 * @isKey 		false
	 * @isForenKey 	false
	 * @len 		65,535
	 */
	private String cfgValue;
	/**
	 * @colName 	CFG_REMARKS
	 * @colNameCn 	配置说明
	 * @type 		String
	 * @isKey 		false
	 * @isForenKey 	false
	 * @len 		65,535
	 */
	private String cfgRemarks;
	/**
	 * @colName 	IS_GLOBAL
	 * @colNameCn 	是否全局配置，字典 TUREORFALSE 1是，0否
	 * @type 		Integer
	 * @isKey 		false
	 * @isForenKey 	false
	 * @len 		10
	 */
	private Integer isGlobal;
	/**
	 * @colName 	IS_LEAF
	 * @colNameCn 	是否叶子节点
	 * @type 		Integer
	 * @isKey 		false
	 * @isForenKey 	false
	 * @len 		10
	 */
	private Integer isLeaf;
	/**
	 * @colName 	STATUS
	 * @colNameCn 	配置状态，是否启用，字典 USEORNOT 1使用，0停用
	 * @type 		Integer
	 * @isKey 		false
	 * @isForenKey 	false
	 * @len 		10
	 */
	private Integer status;
	/**
	 * @colName 	ORDERED
	 * @colNameCn 	显示顺序
	 * @type 		String
	 * @isKey 		false
	 * @isForenKey 	false
	 * @len 		10
	 */
	private String ordered;
	// fields end
	
	//////////////////////////////
	// 非持久化属性
    private SysApps sysApp;
    private String appName; // 系统名称
	
	
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
	
	public String getUserId(){
		return userId;
	}
	public void setUserId(String userId){
		this.userId=userId;
	}
	
	public String getParentId(){
		return parentId;
	}
	public void setParentId(String parentId){
		this.parentId=parentId;
	}
	
	public String getCfgName(){
		return cfgName;
	}
	public void setCfgName(String cfgName){
		this.cfgName=cfgName;
	}
	
	public String getCfgKey(){
		return cfgKey;
	}
	public void setCfgKey(String cfgKey){
		this.cfgKey=cfgKey;
	}
	
	public String getCfgValue(){
		return cfgValue;
	}
	public void setCfgValue(String cfgValue){
		this.cfgValue=cfgValue;
	}
	
	public String getCfgRemarks(){
		return cfgRemarks;
	}
	public void setCfgRemarks(String cfgRemarks){
		this.cfgRemarks=cfgRemarks;
	}
	
	public Integer getIsGlobal(){
		return isGlobal;
	}
	public void setIsGlobal(Integer isGlobal){
		this.isGlobal=isGlobal;
	}
	
	public Integer getIsLeaf(){
		return isLeaf;
	}
	public void setIsLeaf(Integer isLeaf){
		this.isLeaf=isLeaf;
	}
	
	public Integer getStatus(){
		return status;
	}
	public void setStatus(Integer status){
		this.status=status;
	}
	
	public String getOrdered(){
		return ordered;
	}
	public void setOrdered(String ordered){
		this.ordered=ordered;
	}
	public SysApps getSysApp() {
		return sysApp;
	}
	public void setSysApp(SysApps sysApp) {
		this.sysApp = sysApp;
	}
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	
	
	
	
	
	//get,set methods end
	

}
