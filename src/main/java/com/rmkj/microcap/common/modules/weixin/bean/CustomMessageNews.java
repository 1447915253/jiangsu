package com.rmkj.microcap.common.modules.weixin.bean;

import com.rmkj.microcap.modules.weChatPublic.entity.WeChatPublicArticle2;

import java.util.List;

/**
 * Created by 123 on 2017/3/14.
 */
public class CustomMessageNews {
    List<WeChatPublicArticle2> articles;

    public List<WeChatPublicArticle2> getArticles() {
        return articles;
    }

    public void setArticles(List<WeChatPublicArticle2> articles) {
        this.articles = articles;
    }
}
