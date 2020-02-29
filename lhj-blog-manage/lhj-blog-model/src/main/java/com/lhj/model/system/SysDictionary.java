package com.lhj.model.system;

import com.lhj.common.model.Pages;


/**
 * 系统字典类
 */

public class SysDictionary extends Pages<SysDictionary> {

    private static final long serialVersionUID = -2232117072156725183L;
    
    
	private String sid;            //主键
	private String parentId;       //父节点
	private String dictName;       //字典名称
	private String dictKey;        //字典键
	private String dictValue;      //字典值
	private Integer status;        //字典状态：1使用，0不使用
	private Integer isLeaf;        //是否节点：0子节点，1父目录
	private Integer ordered;       //字典显示顺序
	
	public String getSid() {
		return this.sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getParentId() {
		return this.parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

    public String getDictName() {
        return dictName;
    }

    public void setDictName(String dictName) {
        this.dictName = dictName;
    }

    public String getDictKey() {
		return this.dictKey;
	}

	public void setDictKey(String dictKey) {
		this.dictKey = dictKey;
	}

	public String getDictValue() {
		return this.dictValue;
	}

	public void setDictValue(String dictValue) {
		this.dictValue = dictValue;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getIsLeaf() {
		return this.isLeaf;
	}

	public void setIsLeaf(Integer isLeaf) {
		this.isLeaf = isLeaf;
	}

	public Integer getOrdered() {
		return this.ordered;
	}

	public void setOrdered(Integer ordered) {
		this.ordered = ordered;
	}
	
}