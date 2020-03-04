package com.lhj.model.test;

import java.lang.String;
import java.lang.Integer;

import com.aifa.model.Pages;

/**
 * table name:		TEST_SHIWU <br/>
 * table nameCn:	 <br/>
 * entity name:		TestShiwu <br/>
 * @author pojofactory pojo builder
 * @version 1.0
 * @created 2019-3-31 12:45:35
 */
public class TestShiwu extends Pages<TestShiwu>{
	
	// fields start
	/**
	 * @colName 	sid
	 * @colNameCn 	
	 * @type 		String
	 * @isKey 		true
	 * @isForenKey 	false
	 * @len 		32
	 */
	private String sid;
	/**
	 * @colName 	name
	 * @colNameCn 	
	 * @type 		String
	 * @isKey 		false
	 * @isForenKey 	false
	 * @len 		255
	 */
	private String name;
	/**
	 * @colName 	count
	 * @colNameCn 	
	 * @type 		Integer
	 * @isKey 		false
	 * @isForenKey 	false
	 * @len 		10
	 */
	private Integer count;
	// fields end
	
	
	// get,set methods
	public String getSid(){
		return sid;
	}
	public void setSid(String sid){
		this.sid=sid;
	}
	
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name=name;
	}
	
	public Integer getCount(){
		return count;
	}
	public void setCount(Integer count){
		this.count=count;
	}
	
	//get,set methods end
	

}
