package com.rmkj.microcap.modules.money.dao;

import com.rmkj.microcap.common.bean.annotation.DataSource;
import com.rmkj.microcap.common.modules.pay.mobao.bean.MoBaoPerBankCardBean;
import com.rmkj.microcap.modules.money.entity.MoneyChange;
import com.rmkj.microcap.modules.money.entity.MoneyRecord;
import com.rmkj.microcap.modules.money.entity.ReturnMoneyOut;
import com.rmkj.microcap.modules.user.bean.AddBankCard;
import com.rmkj.microcap.modules.user.entity.User;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

/**
 * Created by 123 on 2016/10/19.
 */
@DataSource
public interface MoneyDao {
    /**
     * 记录用户资金变更
     * @param moneyChange
     * @return
     */
    int recordChangeMoney(MoneyChange moneyChange);

    /**
     * 充值或者提现
     * @param moneyRecord
     * @return
     */
    int record(MoneyRecord moneyRecord);

    int updateRecord(MoneyRecord moneyRecord);

    int updateNoThirdNoRecord(MoneyRecord moneyRecord);

    MoneyRecord findMoneyRecordBySerialNoWithLock(String serialNo);

    int deletePrePayMoneyRecord(Map<String, String> map);

    int returnMoneyOut(ReturnMoneyOut returnMoneyOut);

    /*
     * TODO 查询用户交易提现记录
     * @param userId 用户id
     */
    List<MoneyRecord> findUserMoneyRecord(String userId);
    /**
     * TODO 查询用户当天收益余额提现
     * @param userId
     * @return
     */
    ReturnMoneyOut findUserOutMoneyRecord(String userId);

    //查询某个时间段用户充值处理充的交易记录
    List<MoneyRecord> selChargeResult();

    /**
     * TODO 查询用户充值记录
     * @param moneyRecord
     * @return
     */
    List<MoneyRecord> findUserPayMoneyRecord(MoneyRecord moneyRecord);

    /**
    *
    * 查询用户是否提现成功过
    *@param userId
    * @return
    */
    List<MoneyRecord> findUserWithdraw(String userId);

    /**
     * 根据用户id查询该用户，是否有当天充值处理中的订单
     * @param user
     * @return
     */
    int findMoneyRecordStatusByUserId(User user);

    /**
     * 根据银行卡号查询第一条提现记录
     * @param addBankCard
     * @return
     */
    MoneyRecord findMoneyRecordByBankAccount(AddBankCard addBankCard);

    MoBaoPerBankCardBean moBaoPayQueryBack(String userId);

    int personageBankCardUpdate( MoBaoPerBankCardBean moBaoPerBankCardBeanUpdate);

    int personageBankCard(MoBaoPerBankCardBean moBaoPerBankCardBean);
}
