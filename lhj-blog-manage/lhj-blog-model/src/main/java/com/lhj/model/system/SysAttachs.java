package com.lhj.model.system;

import com.lhj.common.model.Pages;

import java.math.BigDecimal;

/**
 * table name:		NOTEBOOK_MAIN <br/>
 * table nameCn:	 <br/>
 * entity name:		NotebookMain <br/>
 * author pojofactory pojo builder
 * created 2020-3-21 17:43:35
 */
public class SysAttachs extends Pages<SysAttachs>{
	
	// fields start
	/**
	 * colNameCn 	主键
	 * type 		String
	 * len 		50
	 */
	private String sid;

	private String owenrId;

	private String state;

	private String url;

	private String title ;

	private String type ;

	private BigDecimal size;

	private  int isPublic;


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

	// fields end
	
	
	// get,set methods

	@Override
	public String getSid() {
		return sid;
	}

	@Override
	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getOwenrId() {
		return owenrId;
	}

	public void setOwenrId(String owenrId) {
		this.owenrId = owenrId;
	}

	public String getState() {
		return state;
	}

	public int getIsPublic() {
		return isPublic;
	}

	public void setIsPublic(int isPublic) {
		this.isPublic = isPublic;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public BigDecimal getSize() {
		return size;
	}

	public void setSize(BigDecimal size) {
		this.size = size;
	}

	public BigDecimal getSortKey() {
		return sortKey;
	}

	public void setSortKey(BigDecimal sortKey) {
		this.sortKey = sortKey;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}


	//get,set methods end
	

}
