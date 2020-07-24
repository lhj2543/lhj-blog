package com.lhj.blog.dto;

import com.lhj.common.model.Pages;
import com.lhj.model.blog.NotebookCategory;
import com.lhj.model.blog.SiteAttrs;

/**
 * table name:		NOTEBOOK_MAIN <br/>
 * table nameCn:	 <br/>
 * entity name:		NotebookMain <br/>
 * author pojofactory pojo builder
 * created 2020-3-21 17:43:35
 * @author 17822
 */
public class IndexDto extends Pages<IndexDto>{


    private SiteAttrs siteAttrs;

    private NotebookCategory notebookCategory;

    public SiteAttrs getSiteAttrs() {
        return siteAttrs;
    }

    public void setSiteAttrs(SiteAttrs siteAttrs) {
        this.siteAttrs = siteAttrs;
    }

    public NotebookCategory getNotebookCategory() {
        return notebookCategory;
    }

    public void setNotebookCategory(NotebookCategory notebookCategory) {
        this.notebookCategory = notebookCategory;
    }
}
