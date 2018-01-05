package com.rmkj.microcap.modules.index.entity;

import com.rmkj.microcap.common.bean.DataEntity;

/**
 * Created by 123 on 2016/10/18.
 */
public class Broadcast extends DataEntity{

    /**
     * id : 140d5967766043d2941dfde964efec33
     * sortNo : 序号
     * title : 标题
     */

    private String id;
    private String sortNo;
    private String title;
    private String content;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSortNo() {
        return sortNo;
    }

    public void setSortNo(String sortNo) {
        this.sortNo = sortNo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
