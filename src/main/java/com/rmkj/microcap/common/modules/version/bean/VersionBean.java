package com.rmkj.microcap.common.modules.version.bean;


import com.rmkj.microcap.common.bean.DataEntity;

/**
 * Created by zhangbowen on 2016/1/12.
 * 版本管理
 */
public class VersionBean extends DataEntity {
    //标题
    private String title;
    //版本号
    private String code;
    //版本摘要
    private String summary;
    //下载地址
    private String downloadUrl;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }
}
