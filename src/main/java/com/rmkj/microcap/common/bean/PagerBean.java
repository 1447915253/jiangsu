package com.rmkj.microcap.common.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Created by zhangbowen on 2016/1/25.
 */
@JsonIgnoreProperties(value = {"rows", "start"})
public class PagerBean<T> {
    private int start;
    private int rows;
    private int total;
    private List<T> data;


    public PagerBean() {
    }

    public PagerBean(int start, int rows) {
        this.start = start;
        this.rows = rows;
    }

    public PagerBean(List<T> data, int total) {
        this.total = total;
        this.data = data;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }
}
