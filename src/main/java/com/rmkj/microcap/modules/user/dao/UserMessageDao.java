package com.rmkj.microcap.modules.user.dao;

import com.rmkj.microcap.common.bean.annotation.DataSource;
import com.rmkj.microcap.modules.user.entity.UserMessage;

import java.util.List;

/**
 * Created by 123 on 2016/11/9.
 */
@DataSource
public interface UserMessageDao {
    int record(UserMessage message);

    List<UserMessage> findList(UserMessage userMessage);

    long countNewMessage(String userId);

    int readMessage(UserMessage userMessage);

    UserMessage findUserMsgDetail(UserMessage userMessage);
}
