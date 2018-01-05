package com.rmkj.microcap.modules.weChatPublic.dao;

import com.rmkj.microcap.common.bean.annotation.DataSource;
import com.rmkj.microcap.modules.weChatPublic.entity.WeChatPublicArticle;
import com.rmkj.microcap.modules.weChatPublic.entity.WeChatPublicArticle2;
import com.rmkj.microcap.modules.weChatPublic.entity.WeiXinEventClickBean;

import java.util.List;

/**
 * Created by 123 on 2017/3/14.
 */
@DataSource
public interface WeChatPublicArticlesDao {

    List<WeChatPublicArticle> list(WeChatPublicArticle weChatPublicArticle);

    WeChatPublicArticle findById(String id);

    int add(WeChatPublicArticle weChatPublicArticle);

    int update(WeChatPublicArticle weChatPublicArticle);

    int del(String id);

    WeChatPublicArticle2 findById2(String content);

    WeiXinEventClickBean getContentById(String id);
}
