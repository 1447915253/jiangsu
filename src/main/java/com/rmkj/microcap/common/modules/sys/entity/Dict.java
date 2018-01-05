package com.rmkj.microcap.common.modules.sys.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;


/**
 * 字典Entity
 * <p/>
 * Created by zhangbowen on 2015/12/22.
 */
public class Dict {


    private String id;

    // 数据值
    private String value;

    // 标签名
    private String label;

    // 类型
    private String type;

    // 描述
    private String description;

    // 排序
    private Integer sort;
    //父Id
    private String parentId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateDate;


    public Dict() {
    }

    public Dict(String value, String label) {
        this.value = value;
        this.label = label;
    }


    @Override
    public String toString() {
        return label;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}