package com.lhj.model.system;

import com.lhj.common.model.Pages;

public class SysAttachs extends Pages<SysAttachs> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9204557475922774142L;
	
	
	private String sid;
	private String appId;
	private String ownerId;		// 附件所有者ID
	private String attachTitle;	// 附件标题
	private String attachName;	// 附件名称（form表单中的name）
	private String attachType;	// 附件类型：jpg,txt,png,ppt
	private Long attachSize;	// 附件大小(字节)
	private String attachPath;	// 附件path
	private Integer isPublic;	// 是否公开，0否，1是
	private Integer ordered;	// 附件显示顺序
	
	
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}
	public String getAttachTitle() {
		return attachTitle;
	}
	public void setAttachTitle(String attachTitle) {
		this.attachTitle = attachTitle;
	}
	public String getAttachName() {
		return attachName;
	}
	public void setAttachName(String attachName) {
		this.attachName = attachName;
	}
	public String getAttachType() {
		return attachType;
	}
	public void setAttachType(String attachType) {
		this.attachType = attachType;
	}
	public Long getAttachSize() {
		return attachSize;
	}
	public void setAttachSize(Long attachSize) {
		this.attachSize = attachSize;
	}
	public String getAttachPath() {
		return this.attachPath;
	}
	public void setAttachPath(String attachPath) {
		this.attachPath = attachPath;
	}
	
	/**
	 * 获得附件在ftp服务器上的真实路径
	 * @return
	 */
	public String getRealPath(){
		if(this.attachPath==null){
			return null;
		}
		if(this.isPublic!=null && 1==this.isPublic){
			return "/public"+this.attachPath;
		}else{
			return "/private"+this.attachPath;
		}
	}
	public Integer getIsPublic() {
		return isPublic;
	}
	public void setIsPublic(Integer isPublic) {
		this.isPublic = isPublic;
	}
	public Integer getOrdered() {
		return ordered;
	}
	public void setOrdered(Integer ordered) {
		this.ordered = ordered;
	}

}
