package com.lhj.model.system;

import java.util.Date;
import java.lang.String;

import com.lhj.common.model.Pages;

/**
 * table name:		SYS_ROLE_MENU <br/>
 * table nameCn:	 <br/>
 * entity name:		SysRoleMenu <br/>
 * author pojofactory pojo builder
 * created 2020-3-2 21:28:54
 */
public class SysRoleMenu extends Pages<SysRoleMenu>{
	
	// fields start
	/**
	 * colNameCn 	主键
	 * type 		String
	 * len 		50
	 */
	private String sid;
	/**
	 * colNameCn 	角色ID
	 * type 		String
	 * len 		50
	 */
	private String roleId;
	/**
	 * colNameCn 	菜单ID
	 * type 		String
	 * len 		50
	 */
	private String menuId;
	/**
	 * colNameCn 	状态0=有效，1=无效
	 * type 		String
	 * len 		2
	 */
	private String status;
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
	
	public String getRoleId(){
		return roleId;
	}
	public void setRoleId(String roleId){
		this.roleId=roleId;
	}
	
	public String getMenuId(){
		return menuId;
	}
	public void setMenuId(String menuId){
		this.menuId=menuId;
	}
	
	public String getStatus(){
		return status;
	}
	public void setStatus(String status){
		this.status=status;
	}
	
	public String getNotes(){
		return notes;
	}
	public void setNotes(String notes){
		this.notes=notes;
	}
	
	
	
	
	
	//get,set methods end
	

}
