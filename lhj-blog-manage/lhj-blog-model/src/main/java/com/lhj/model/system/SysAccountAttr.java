package com.lhj.model.system;

import java.util.Date;
import java.lang.String;

import com.lhj.common.model.Pages;

/**
 * table name:		SYS_ACCOUNT_ATTR <br/>
 * table nameCn:	 <br/>
 * entity name:		SysAccountAttr <br/>
 * author pojofactory pojo builder
 * created 2020-3-2 21:28:53
 */
public class SysAccountAttr extends Pages<SysAccountAttr>{
	
	// fields start
	/**
	 * colNameCn 	主键
	 * type 		String
	 * len 		50
	 */
	private String sid;
	/**
	 * colNameCn 	用户名
	 * type 		String
	 * len 		100
	 */
	private String userCd;
	/**
	 * colNameCn 	密码
	 * type 		String
	 * len 		255
	 */
	private String attrName;
	/**
	 * colNameCn 	区域
	 * type 		String
	 * len 		255
	 */
	private String attrValue;
	/**
	 * colNameCn 	备注
	 * type 		String
	 * len 		255
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
	
	public String getUserCd(){
		return userCd;
	}
	public void setUserCd(String userCd){
		this.userCd=userCd;
	}
	
	public String getAttrName(){
		return attrName;
	}
	public void setAttrName(String attrName){
		this.attrName=attrName;
	}
	
	public String getAttrValue(){
		return attrValue;
	}
	public void setAttrValue(String attrValue){
		this.attrValue=attrValue;
	}
	
	public String getNotes(){
		return notes;
	}
	public void setNotes(String notes){
		this.notes=notes;
	}
	
	
	
	
	
	//get,set methods end
	

}
