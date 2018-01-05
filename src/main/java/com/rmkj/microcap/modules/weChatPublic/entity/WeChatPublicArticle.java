package com.rmkj.microcap.modules.weChatPublic.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rmkj.microcap.common.bean.DataEntity;

import java.util.Date;

/**
 * Created by 123 on 2017/3/14.
 */
public class WeChatPublicArticle extends DataEntity {

    private String title;
    private String description;
    private String content;
    private String url;
    private String picurl;
    private Date createTime;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPicurl() {
        return picurl;
    }

    public void setPicurl(String picurl) {
        this.picurl = picurl;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
