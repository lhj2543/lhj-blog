package com.lhj.model.system;

import com.lhj.common.model.Pages;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 系统菜单模型类. @author MyEclipse Persistence Tools
 */

public class SysMenus extends Pages<SysMenus> {
	
	private static final long serialVersionUID = -1681722196411113395L;

	private String sid;  //菜单编号
	private String appId;	//系统ID
	private String parentId; //菜单编号（父级）
	private String menuName; //菜单名称
	private String menuAlias;//菜单别名（授权树区别重复菜单名称）MENU_ALIAS
	private String menuUrl; //菜单地址
	private String menuIcon; //菜单图标
	private byte[] menuPic; //菜单大图标
	private String grouped; //分组名称
	private Integer isLeaf; //菜单节点（树形）
	private Integer isShow; //是否显示
	private Integer ordered; //排序字段
	
	// 关联应用对象
	private SysApps sysApp;
    
    // 非持久化属性
    //////////////////////////////////////////
    private String roleId;
    private String userId;
    private boolean checked=false;
    private boolean chkDisabled=false;
    private boolean onlymenu=false;	 // 只加载菜单不加载按钮
    private boolean loadLinks=true;  // 菜单加载时是否加载首页（快捷操作）
    
    
	/**
	 * 关联角色对象
	 */
	private Set<SysRolesMenus> sysRolesMenuses = new HashSet<SysRolesMenus>(0);
	
	/**
	 * 页面按钮
	 */
	private List<SysButtons> buttons=new ArrayList<SysButtons>();
	
	
	private List<Object> children=new ArrayList<Object>();
	
	
	public SysMenus(){}
	
	
	public SysMenus(byte[] menuPic){
	    this.menuPic=menuPic;
	}
	

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

	public String getMenuName() {
		return this.menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuUrl() {
		return this.menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	public String getMenuIcon() {
		return this.menuIcon;
	}

	public void setMenuIcon(String menuIcon) {
		this.menuIcon = menuIcon;
	}



	/**
     * @return the menuAlias
     */
    public String getMenuAlias() {
        return menuAlias;
    }


    /**
     * @param menuAlias the menuAlias to set
     */
    public void setMenuAlias(String menuAlias) {
        this.menuAlias = menuAlias;
    }



    public String getGrouped() {
		return this.grouped;
	}

	public void setGrouped(String grouped) {
		this.grouped = grouped;
	}

	public Integer getIsLeaf() {
		return this.isLeaf;
	}

	public void setIsLeaf(Integer isLeaf) {
		this.isLeaf = isLeaf;
	}

	public Integer getIsShow() {
		return this.isShow;
	}

	public void setIsShow(Integer isShow) {
		this.isShow = isShow;
	}

	public Integer getOrdered() {
		return this.ordered;
	}

	public void setOrdered(Integer ordered) {
		this.ordered = ordered;
	}

	public Set<SysRolesMenus> getSysRolesMenuses() {
		return this.sysRolesMenuses;
	}

	public void setSysRolesMenuses(Set<SysRolesMenus> sysRolesMenuses) {
		this.sysRolesMenuses = sysRolesMenuses;
	}


    /**
     * @return the menuPic
     */
    public byte[] getMenuPic() {
        return menuPic;
    }

    /**
     * @param menuPic the menuPic to set
     */
    public void setMenuPic(byte[] menuPic) {
        this.menuPic = menuPic;
    }

    /**
     * @return the appId
     */
    public String getAppId() {
        return appId;
    }

    /**
     * @param appId the appId to set
     */
    public void setAppId(String appId) {
        this.appId = appId;
    }

    /**
     * @return the sysApp
     */
    public SysApps getSysApp() {
        return sysApp;
    }

    /**
     * @param sysApp the sysApp to set
     */
    public void setSysApp(SysApps sysApp) {
        this.sysApp = sysApp;
    }

    /**
     * @return the roleId
     */
    public String getRoleId() {
        return roleId;
    }

    /**
     * @param roleId the roleId to set
     */
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    /**
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public boolean isLoadLinks() {
		return loadLinks;
	}
	public void setLoadLinks(boolean loadLinks) {
		this.loadLinks = loadLinks;
	}


	/**
     * @return the buttons
     */
    public List<SysButtons> getButtons() {
        return buttons;
    }


    /**
     * @param buttons the buttons to set
     */
    public void setButtons(List<SysButtons> buttons) {
        this.buttons = buttons;
    }


	/**
	 * @return the onlymenu
	 */
	public boolean isOnlymenu() {
		return onlymenu;
	}


	/**
	 * @param onlymenu the onlymenu to set
	 */
	public void setOnlymenu(boolean onlymenu) {
		this.onlymenu = onlymenu;
	}

	 /**
     * @return the url
     */
    public String getUrl() {
        return this.getMenuUrl();
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.setMenuUrl(url);
    }

    /**
     * @return the children
     */
    public List<Object> getChildren() {
        return children;
    }

    /**
     * @param children the children to set
     */
    public void setChildren(List<Object> children) {
        this.children = children;
    }

    /**
     * @return the text
     */
    public String getText() {
        return this.getMenuName();
    }

    /**
     * @param text the text to set
     */
    public void setText(String text) {
        this.setMenuName(text);
    }

	public String getIcon() {
		return this.getMenuIcon();
	}

	public void setIcon(String icon) {
		this.setMenuIcon(icon);
	}


	/**
	 * @return the checked
	 */
	public boolean isChecked() {
		return checked;
	}


	/**
	 * @param checked the checked to set
	 */
	public void setChecked(boolean checked) {
		this.checked = checked;
	}


	/**
	 * @return the chkDisabled
	 */
	public boolean isChkDisabled() {
		return chkDisabled;
	}


	/**
	 * @param chkDisabled the chkDisabled to set
	 */
	public void setChkDisabled(boolean chkDisabled) {
		this.chkDisabled = chkDisabled;
	}

	public String getTitle(){
		return this.menuName;
	}

}