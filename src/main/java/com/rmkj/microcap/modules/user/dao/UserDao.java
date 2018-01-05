package com.rmkj.microcap.modules.user.dao;

import com.rmkj.microcap.common.bean.annotation.DataSource;
import com.rmkj.microcap.common.modules.weixin.bean.WeiXinUserInfo;
import com.rmkj.microcap.modules.money.entity.MoneyChange;
import com.rmkj.microcap.modules.money.entity.MoneyRecord;
import com.rmkj.microcap.modules.money.entity.ReturnMoneyOut;
import com.rmkj.microcap.modules.trade.entity.Trade;
import com.rmkj.microcap.modules.user.bean.AddBankCard;
import com.rmkj.microcap.modules.user.bean.BankCodeBean;
import com.rmkj.microcap.modules.user.bean.ThirdLevelAgent;
import com.rmkj.microcap.modules.user.entity.BankCard;
import com.rmkj.microcap.modules.user.entity.User;

import javax.jws.soap.SOAPBinding;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by 123 on 2016/10/12.
 */
@DataSource
public interface UserDao {
    /**
     * 注册用户
     * @param user
     */
    void registerUser(User user);

    int reg(User user);

    String findUnitsIdByAgentInviteCode(String agentInviteCode);
    Integer findTradeStatusById(String unitsId);
    /**
     *
     * @param userId
     * @return
     */
    User findUserById(String userId);

    /**
     * 三级营销系统
     * @param userId
     * @return
     */
    User findAgent3UserById(String userId);

    /**
     * 操盘记录
     * @param user
     * @return
     */
    List<Trade> tradeList(User user);

    /**
     * 充值提现记录
     * @param user
     * @return
     */
    List<MoneyRecord> moneyRecordList(User user);

    /**
     *
     * @param moneyRecord
     * @return
     */
    MoneyRecord moneyRecord(MoneyRecord moneyRecord);

    int update(User user);

    int updateUserTradeCount(String UserId);

    int resetUserTradePwd(User user);
    /**
     * 变更用户资金
     * @param moneyChange
     * @return
     */
    int changeUserMoney(MoneyChange moneyChange);

    /**
     * 根据手机号查询用户
     * @param mobile
     * @return
     */
    User findUserByMobile(String mobile);

    int insert(User user);

    int changeUserRechargeMoney(MoneyChange moneyChange);

    int changeCouponMoney(User user);

    int updateLogin(User user);


    /**
     *通过经纪人邀请码查询三级代理商经纪人
     */
    ThirdLevelAgent findThirdLevelAgentByInviteCode(String agentInviteCode);

    /**
     *
     * @param parentUser
     * @return
     */
    int bindToParents(User parentUser);

    int createCorps(User user);

    int addBankCard(BankCard bankCard);

    /**
     * TODO 修改用户银行卡信息
     * @param bankCard
     * @return
     */
    int updateBankCard(AddBankCard bankCard);

    BankCard findBankCard(String userId);

    int subtractReturnMoney(ReturnMoneyOut returnMoneyOut);

    /**
     * 根据手机号查询代理商邀请码
     * @param mobile
     * @return
     */
    String findAgentByMobile(String mobile);

    /**
     * 更新用户微信信息
     * @param weiXinUserInfo
     * @return
     */
    int updateWeiXinInfo(WeiXinUserInfo weiXinUserInfo);

    /**
     * 代理的军团长邀请码
     * @param id
     * @return
     */
    String findAgent1InviteCodeById(String id);

    /**
     * TODO 查询用户银行卡
     * @param userId
     * @return
     */
    BankCard getUserBankAccount(String userId);

    /**
     * 查询用户持仓金额
     */
    BigDecimal selUserHoldMoney(String userId);

    /**
     * 查询用户持仓数量
     */
    Long selUserHoldCounts(String userId);

    int bindWeiXinInfo(User user);

    /**
     * TODO 根据用户id查询资金变更明细
     * @return
     */
    List<MoneyChange> findMoneyChangeByUserId(MoneyChange moneyChange);

    /**
     * TODO 查询充值资金成功的记录
     * @param user
     * @return
     */
    Integer findOutMoneyList(User user);

    /**
     * 查询用户是否第一次登录系统
     * @return
     */
    User getUserLastLoginTime(String userId);

    /**
     * 查询威鹏支付，支行信息 模糊查询提示
     * @param mainWord
     * @return
     */
    List<String> findBankCodeName(String mainWord);

    /**
     * 威鹏支付-根据支行名查询支行信息
     * @param mainWord
     * @return
     */
    BankCodeBean findBankCodeByName(String mainWord);

    int updateQrcodeTicket(User userById);

    int clearSameOpenIdUser(User user);

    User findUserByAutoId(String sceneId);

    User findUserByOpenId(String openId);

    int deleteSameOpenIdAndNoMobileUser(String id);

    /**
     * 验证银行卡是否已被绑定
     * @param bankCard
     * @return
     */
    BankCard findIsNullByAccount(AddBankCard bankCard);
    /*
    * 佣金转余额之后，变更佣金余额
    * */
    int commissionToMoney(ReturnMoneyOut returnMoneyOut);
}
