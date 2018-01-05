package com.rmkj.microcap.common.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.rmkj.microcap.common.modules.sys.utils.DictUtils;

import java.util.Date;

/**
 * Created by 123 on 2016/10/12.
 */
public class PageEntity {

    //分页开始
    protected Integer start;
    //分页数量
    protected Integer rows;

    protected String remarks;

    protected String query;

    protected String orderBy;

    public PageEntity() {
        this.rows = Integer.valueOf(DictUtils.getDictValue("page_rows", "page", "10"));
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        if (rows != null) {
            this.rows = rows;
        }
    }

    public void setPager(int pageNum, int pageSize) {
        this.rows = pageSize;
        this.start = (pageNum - 1) * pageSize;

    }
}
