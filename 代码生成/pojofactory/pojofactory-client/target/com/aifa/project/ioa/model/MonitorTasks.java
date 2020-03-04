package com.aifa.project.ioa.model;

import java.lang.Float;
import java.util.Date;
import java.lang.String;
import java.lang.Integer;

import com.aifa.model.Pages;

/**
 * table name:		MONITOR_TASKS <br/>
 * table nameCn:	 <br/>
 * entity name:		MonitorTasks <br/>
 * @author pojofactory pojo builder
 * @version 1.0
 * @created 2018-9-11 18:03:31
 */
public class MonitorTasks extends Pages<MonitorTasks>{
	
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
	 * @colName 	TARGET_TYPE
	 * @colNameCn 	字典： 1周计划任务，2日报任务
	 * @type 		Integer
	 * @isKey 		false
	 * @isForenKey 	false
	 * @len 		10
	 */
	private Integer targetType;
	/**
	 * @colName 	TARGET_ID
	 * @colNameCn 	目标ID
	 * @type 		String
	 * @isKey 		false
	 * @isForenKey 	false
	 * @len 		32
	 */
	private String targetId;
	/**
	 * @colName 	ORG_ID
	 * @colNameCn 	机构ID
	 * @type 		String
	 * @isKey 		false
	 * @isForenKey 	false
	 * @len 		32
	 */
	private String orgId;
	/**
	 * @colName 	ORG_NAME
	 * @colNameCn 	机构名称
	 * @type 		String
	 * @isKey 		false
	 * @isForenKey 	false
	 * @len 		255
	 */
	private String orgName;
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
	 * @colName 	USER_NAME
	 * @colNameCn 	用户名称
	 * @type 		String
	 * @isKey 		false
	 * @isForenKey 	false
	 * @len 		256
	 */
	private String userName;
	/**
	 * @colName 	PROJ_ID
	 * @colNameCn 	项目ID
	 * @type 		String
	 * @isKey 		false
	 * @isForenKey 	false
	 * @len 		32
	 */
	private String projId;
	/**
	 * @colName 	PROJ_NAME
	 * @colNameCn 	项目名称
	 * @type 		String
	 * @isKey 		false
	 * @isForenKey 	false
	 * @len 		256
	 */
	private String projName;
	/**
	 * @colName 	TASK_TITLE
	 * @colNameCn 	任务标题
	 * @type 		String
	 * @isKey 		false
	 * @isForenKey 	false
	 * @len 		256
	 */
	private String taskTitle;
	/**
	 * @colName 	TASK_DESCS
	 * @colNameCn 	任务描述
	 * @type 		String
	 * @isKey 		false
	 * @isForenKey 	false
	 * @len 		2,048
	 */
	private String taskDescs;
	/**
	 * @colName 	TASK_SOURCE
	 * @colNameCn 	任务来源
	 * @type 		String
	 * @isKey 		false
	 * @isForenKey 	false
	 * @len 		256
	 */
	private String taskSource;
	/**
	 * @colName 	WORK_TYPE
	 * @colNameCn 	工作类型
	 * @type 		String
	 * @isKey 		false
	 * @isForenKey 	false
	 * @len 		256
	 */
	private String workType;
	/**
	 * @colName 	TASK_TYPE
	 * @colNameCn 	任务类型
	 * @type 		String
	 * @isKey 		false
	 * @isForenKey 	false
	 * @len 		32
	 */
	private String taskType;
	/**
	 * @colName 	PLAN_TIME_USE
	 * @colNameCn 	计划工时
	 * @type 		Float
	 * @isKey 		false
	 * @isForenKey 	false
	 * @len 		12
	 */
	private Float planTimeUse;
	/**
	 * @colName 	REALITY_TIME_USE
	 * @colNameCn 	实际工时
	 * @type 		Float
	 * @isKey 		false
	 * @isForenKey 	false
	 * @len 		12
	 */
	private Float realityTimeUse;
	/**
	 * @colName 	DELAY_TIME_USE
	 * @colNameCn 	延误工时
	 * @type 		Float
	 * @isKey 		false
	 * @isForenKey 	false
	 * @len 		12
	 */
	private Float delayTimeUse;
	/**
	 * @colName 	TIME_BEGIN
	 * @colNameCn 	任务计划开始时间
	 * @type 		Date
	 * @isKey 		false
	 * @isForenKey 	false
	 * @len 		19
	 */
	private Date timeBegin;
	/**
	 * @colName 	TIME_END
	 * @colNameCn 	任务计划完成时间
	 * @type 		Date
	 * @isKey 		false
	 * @isForenKey 	false
	 * @len 		19
	 */
	private Date timeEnd;
	/**
	 * @colName 	FINISH_TIME
	 * @colNameCn 	任务实际完成时间
	 * @type 		Date
	 * @isKey 		false
	 * @isForenKey 	false
	 * @len 		19
	 */
	private Date finishTime;
	/**
	 * @colName 	PLAN_ACHIEVEMENT
	 * @colNameCn 	实际交付成果
	 * @type 		String
	 * @isKey 		false
	 * @isForenKey 	false
	 * @len 		1,024
	 */
	private String planAchievement;
	/**
	 * @colName 	OUTCOME
	 * @colNameCn 	实际交付成果
	 * @type 		String
	 * @isKey 		false
	 * @isForenKey 	false
	 * @len 		2,048
	 */
	private String outcome;
	/**
	 * @colName 	FINISH_STATUS
	 * @colNameCn 	任务完成状态
	 * @type 		String
	 * @isKey 		false
	 * @isForenKey 	false
	 * @len 		256
	 */
	private String finishStatus;
	/**
	 * @colName 	TASK_CAUSE_DESCRIPTION
	 * @colNameCn 	任务原因及说明
	 * @type 		String
	 * @isKey 		false
	 * @isForenKey 	false
	 * @len 		1,024
	 */
	private String taskCauseDescription;
	/**
	 * @colName 	CHECK_USER_ID
	 * @colNameCn 	检查人ID
	 * @type 		String
	 * @isKey 		false
	 * @isForenKey 	false
	 * @len 		32
	 */
	private String checkUserId;
	/**
	 * @colName 	CHECK_USER_NAME
	 * @colNameCn 	检查人姓名
	 * @type 		String
	 * @isKey 		false
	 * @isForenKey 	false
	 * @len 		256
	 */
	private String checkUserName;
	/**
	 * @colName 	CHECK_RESULT
	 * @colNameCn 	检查结果
	 * @type 		String
	 * @isKey 		false
	 * @isForenKey 	false
	 * @len 		1,024
	 */
	private String checkResult;
	/**
	 * @colName 	TASK_SATURABILITY
	 * @colNameCn 	工作饱和度
	 * @type 		String
	 * @isKey 		false
	 * @isForenKey 	false
	 * @len 		1,256
	 */
	private String taskSaturability;
	/**
	 * @colName 	MONITOR_DATE
	 * @colNameCn 	监控日期
	 * @type 		Date
	 * @isKey 		false
	 * @isForenKey 	false
	 * @len 		10
	 */
	private Date monitorDate;
	/**
	 * @colName 	REMARKS
	 * @colNameCn 	备注
	 * @type 		String
	 * @isKey 		false
	 * @isForenKey 	false
	 * @len 		1,024
	 */
	private String remarks;
	// fields end
	
	
	// get,set methods
	public String getSid(){
		return sid;
	}
	public void setSid(String sid){
		this.sid=sid;
	}
	
	public Integer getTargetType(){
		return targetType;
	}
	public void setTargetType(Integer targetType){
		this.targetType=targetType;
	}
	
	public String getTargetId(){
		return targetId;
	}
	public void setTargetId(String targetId){
		this.targetId=targetId;
	}
	
	public String getOrgId(){
		return orgId;
	}
	public void setOrgId(String orgId){
		this.orgId=orgId;
	}
	
	public String getOrgName(){
		return orgName;
	}
	public void setOrgName(String orgName){
		this.orgName=orgName;
	}
	
	public String getUserId(){
		return userId;
	}
	public void setUserId(String userId){
		this.userId=userId;
	}
	
	public String getUserName(){
		return userName;
	}
	public void setUserName(String userName){
		this.userName=userName;
	}
	
	public String getProjId(){
		return projId;
	}
	public void setProjId(String projId){
		this.projId=projId;
	}
	
	public String getProjName(){
		return projName;
	}
	public void setProjName(String projName){
		this.projName=projName;
	}
	
	public String getTaskTitle(){
		return taskTitle;
	}
	public void setTaskTitle(String taskTitle){
		this.taskTitle=taskTitle;
	}
	
	public String getTaskDescs(){
		return taskDescs;
	}
	public void setTaskDescs(String taskDescs){
		this.taskDescs=taskDescs;
	}
	
	public String getTaskSource(){
		return taskSource;
	}
	public void setTaskSource(String taskSource){
		this.taskSource=taskSource;
	}
	
	public String getWorkType(){
		return workType;
	}
	public void setWorkType(String workType){
		this.workType=workType;
	}
	
	public String getTaskType(){
		return taskType;
	}
	public void setTaskType(String taskType){
		this.taskType=taskType;
	}
	
	public Float getPlanTimeUse(){
		return planTimeUse;
	}
	public void setPlanTimeUse(Float planTimeUse){
		this.planTimeUse=planTimeUse;
	}
	
	public Float getRealityTimeUse(){
		return realityTimeUse;
	}
	public void setRealityTimeUse(Float realityTimeUse){
		this.realityTimeUse=realityTimeUse;
	}
	
	public Float getDelayTimeUse(){
		return delayTimeUse;
	}
	public void setDelayTimeUse(Float delayTimeUse){
		this.delayTimeUse=delayTimeUse;
	}
	
	public Date getTimeBegin(){
		return timeBegin;
	}
	public void setTimeBegin(Date timeBegin){
		this.timeBegin=timeBegin;
	}
	
	public Date getTimeEnd(){
		return timeEnd;
	}
	public void setTimeEnd(Date timeEnd){
		this.timeEnd=timeEnd;
	}
	
	public Date getFinishTime(){
		return finishTime;
	}
	public void setFinishTime(Date finishTime){
		this.finishTime=finishTime;
	}
	
	public String getPlanAchievement(){
		return planAchievement;
	}
	public void setPlanAchievement(String planAchievement){
		this.planAchievement=planAchievement;
	}
	
	public String getOutcome(){
		return outcome;
	}
	public void setOutcome(String outcome){
		this.outcome=outcome;
	}
	
	public String getFinishStatus(){
		return finishStatus;
	}
	public void setFinishStatus(String finishStatus){
		this.finishStatus=finishStatus;
	}
	
	public String getTaskCauseDescription(){
		return taskCauseDescription;
	}
	public void setTaskCauseDescription(String taskCauseDescription){
		this.taskCauseDescription=taskCauseDescription;
	}
	
	public String getCheckUserId(){
		return checkUserId;
	}
	public void setCheckUserId(String checkUserId){
		this.checkUserId=checkUserId;
	}
	
	public String getCheckUserName(){
		return checkUserName;
	}
	public void setCheckUserName(String checkUserName){
		this.checkUserName=checkUserName;
	}
	
	public String getCheckResult(){
		return checkResult;
	}
	public void setCheckResult(String checkResult){
		this.checkResult=checkResult;
	}
	
	public String getTaskSaturability(){
		return taskSaturability;
	}
	public void setTaskSaturability(String taskSaturability){
		this.taskSaturability=taskSaturability;
	}
	
	public Date getMonitorDate(){
		return monitorDate;
	}
	public void setMonitorDate(Date monitorDate){
		this.monitorDate=monitorDate;
	}
	
	public String getRemarks(){
		return remarks;
	}
	public void setRemarks(String remarks){
		this.remarks=remarks;
	}
	
	
	
	
	
	//get,set methods end
	

}
