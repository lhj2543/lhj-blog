package com.lhj.model.javaview;

import java.util.Date;
import java.lang.String;

import com.aifa.model.Pages;

/**
 * table name:		JAVA_SUBJECT <br/>
 * table nameCn:	 <br/>
 * entity name:		JavaSubject <br/>
 * @author pojofactory pojo builder
 * @version 1.0
 * @created 2019-4-1 18:18:32
 */
public class JavaSubject extends Pages<JavaSubject>{
	
	// fields start
	/**
	 * @colName 	SID
	 * @colNameCn 	
	 * @type 		String
	 * @isKey 		true
	 * @isForenKey 	false
	 * @len 		50
	 */
	private String sid;
	/**
	 * @colName 	TITLE
	 * @colNameCn 	
	 * @type 		String
	 * @isKey 		false
	 * @isForenKey 	false
	 * @len 		255
	 */
	private String title;
	/**
	 * @colName 	ANSWER
	 * @colNameCn 	
	 * @type 		String
	 * @isKey 		false
	 * @isForenKey 	false
	 * @len 		2,147,483,647
	 */
	private String answer;
	/**
	 * @colName 	DESC
	 * @colNameCn 	
	 * @type 		String
	 * @isKey 		false
	 * @isForenKey 	false
	 * @len 		1,024
	 */
	private String desc;
	/**
	 * @colName 	ORDER
	 * @colNameCn 	
	 * @type 		String
	 * @isKey 		false
	 * @isForenKey 	false
	 * @len 		32
	 */
	private String order;
	// fields end
	
	
	// get,set methods
	public String getSid(){
		return sid;
	}
	public void setSid(String sid){
		this.sid=sid;
	}
	
	public String getTitle(){
		return title;
	}
	public void setTitle(String title){
		this.title=title;
	}
	
	public String getAnswer(){
		return answer;
	}
	public void setAnswer(String answer){
		this.answer=answer;
	}
	
	public String getDesc(){
		return desc;
	}
	public void setDesc(String desc){
		this.desc=desc;
	}
	
	public String getOrder(){
		return order;
	}
	public void setOrder(String order){
		this.order=order;
	}
	
	
	
	
	
	//get,set methods end
	

}
