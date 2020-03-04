package com.lhj.model.system;

import java.math.BigDecimal;
import java.util.Date;
import java.lang.String;

import com.lhj.common.model.Pages;

/**
 * table name:		SYS_ITEM <br/>
 * table nameCn:	 <br/>
 * entity name:		SysItem <br/>
 * author pojofactory pojo builder
 * created 2020-3-2 21:28:53
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
	 * colNameCn 	类别CD
	 * type 		String
	 * len 		100
	 */
	private String categoryCd;
	/**
	 * colNameCn 	字典CD
	 * type 		String
	 * len 		100
	 */
	private String itemCd;
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
	
	public String getItemCd(){
		return itemCd;
	}
	public void setItemCd(String itemCd){
		this.itemCd=itemCd;
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
	
	
	
	
	
	//get,set methods end
	

}
