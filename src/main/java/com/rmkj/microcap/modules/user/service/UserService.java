package com.rmkj.microcap.modules.user.service;

import com.alibaba.fastjson.JSON;
import com.rmkj.microcap.common.bean.*;
import com.rmkj.microcap.common.constants.Constants;
import com.rmkj.microcap.common.constants.ProjectConstants;
import com.rmkj.microcap.common.interceptor.ContextInterceptor;
import com.rmkj.microcap.common.interceptor.MybatisPagerInterceptor;
import com.rmkj.microcap.common.modules.money.out.pojo.CheckBankNoResp;
import com.rmkj.microcap.common.modules.money.out.service.JiuBaOutService;
import com.rmkj.microcap.common.modules.pay.yizhifu.CityUtils;
import com.rmkj.microcap.common.modules.sys.service.TokenService;
import com.rmkj.microcap.common.modules.weixin.bean.ResponseToken;
import com.rmkj.microcap.common.modules.weixin.bean.WeiXinUserInfo;
import com.rmkj.microcap.common.modules.weixin.http.interceptor.WeiXinInterceptor;
import com.rmkj.microcap.common.modules.weixin.service.WeiXinLoginService;
import com.rmkj.microcap.common.modules.weixin.service.WeiXinService;
import com.rmkj.microcap.common.utils.AuthContext;
import com.rmkj.microcap.common.utils.Utils;
import com.rmkj.microcap.common.utils.ValidateCodeUtils;
import com.rmkj.microcap.modules.corps.bean.CreateCorpsBean;
import com.rmkj.microcap.modules.corps.service.CorpsService;

import com.rmkj.microcap.modules.coupon.service.CouponService;
import com.rmkj.microcap.modules.index.dao.ParameterSetDao;
import com.rmkj.microcap.modules.money.dao.MoneyDao;
import com.rmkj.microcap.modules.money.entity.MoneyRecord;

import com.rmkj.microcap.modules.trade.dao.TradeDao;
import com.rmkj.microcap.modules.trade.entity.Trade;
import com.rmkj.microcap.modules.user.bean.*;
import com.rmkj.microcap.modules.user.dao.*;
import com.rmkj.microcap.modules.user.entity.*;
import com.rmkj.microcap.modules.weChatPublic.dao.WeChatPublicDao;
import com.rmkj.microcap.modules.weChatPublic.entity.WeChatPublic;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Position;
import net.coobird.thumbnailator.geometry.Positions;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.ui.Model;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLDecoder;
import java.util.*;
import java.util.List;

/**
 * Created by 123 on 2016/10/12.
 */
@Service
@Transactional
public class UserService {

    private static Logger logger = Logger.getLogger(ContextInterceptor.class);

    static BASE64Encoder encoder = new sun.misc.BASE64Encoder();
    static BASE64Decoder decoder = new sun.misc.BASE64Decoder();

    @Autowired
    private UserDao userDao;

    @Autowired
    private TradeDao tradeDao;

    @Autowired
    private UserMessageDao userMessageDao;

    @Autowired
    private Ml3AgentDao ml3AgentDao;

    @Autowired
    private MoneyDao moneyDao;

    @Autowired
    private Ml3AgentRoleDao ml3AgentRoleDao;

    @Autowired
    private ParameterSetDao parameterSetDao;

    @Autowired
    private Ml3MemberUnitsDao ml3MemberUnitsDao;

    @Autowired
    private WeiXinLoginService weiXinLoginService;

    @Autowired
    private WeiXinService weiXinService;

    @Autowired
    private CorpsService corpsService;

    @Autowired
    private CouponService couponService;

    @Autowired
    private Ml3MemberUnitsService ml3MemberUnitsService;

    @Autowired
    private WeChatPublicDao wechatPublicDao;

    public User loginWeiXin(String code) throws Exception {
        String openId = null;
        ResponseToken responseTokenBean = null;
        // 获取openid
        if (ProjectConstants.PRO_DEBUG) {
            openId = code;
        } else {
            responseTokenBean = weiXinLoginService.getOAuthToken(code);
            openId = responseTokenBean.getOpenid();
            if (openId == null || "".equals(openId)) {
                logger.error("找不到微信用户");
                throw new Exception("找不到微信用户");
            }
        }

        // 根据openid在数据库中查找用户
        User user = userDao.findUserByOpenId(openId);
        if (user == null) {
            user = new User();
            if (ProjectConstants.PRO_DEBUG) {
                user.setUserHeader("http://wx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/46");
            }
            user.setOpenId(openId);
            WeChatPublic weChatPublic = wechatPublicDao.findByAppId(WeiXinInterceptor.appId());
            user.setWechatPublicId(weChatPublic.getId());
            if(StringUtils.isNotBlank(weChatPublic.getAgentInviteCode())){
                user.setAgentInviteCode(weChatPublic.getAgentInviteCode());
            }
            user.preInsert();
            userDao.registerUser(user);
        } else if (Constants.USER_STATUE.INVALID.equals(user.getStatus().toString())) {
            // 停用
            return user;
        }

        // 每次网页授权登录 更新用户微信信息
        if (!ProjectConstants.PRO_DEBUG) {
            WeiXinUserInfo weiXinUserInfo = weiXinLoginService.userinfo(responseTokenBean);
            if (weiXinUserInfo != null) {
                userDao.updateWeiXinInfo(weiXinUserInfo);
            }
        }

        user = userDao.findUserById(user.getId());

        if (StringUtils.isNotBlank(user.getMobile())) {
            String token = TokenService.handleToken(AuthContext.getCurAuth().getTerminal(), user.getId());
            user.setToken(token);
        }
        return user;
    }

    /**
     * 网页授权途径绑定微信（用户已经登录）
     *
     * @param code
     */
    public void bindWeiXin(String code) throws Exception {
        String userId = AuthContext.getUserId();
        User user = userDao.findUserById(userId);

        // 更新用户微信信息
        if (!ProjectConstants.PRO_DEBUG) {
            ResponseToken responseTokenBean = weiXinLoginService.getOAuthToken(code);
            String openId = responseTokenBean.getOpenid();
            if (openId == null || "".equals(openId)) {
                logger.error("找不到微信用户");
                throw new Exception("找不到微信用户");
            }

            WeiXinUserInfo weiXinUserInfo = weiXinLoginService.userinfo(responseTokenBean);
            if (weiXinUserInfo != null) {
                if (openId.equals(user.getOpenId())) {
                    userDao.updateWeiXinInfo(weiXinUserInfo);
                } else {
                    user.setOpenId(openId);
                    user.setUserHeader(weiXinUserInfo.getHeadimgurl());
                    user.setWechatPublicId(wechatPublicDao.findByAppId(WeiXinInterceptor.appId()).getId());

                    // 绑定之前清理之前绑定过的用户
                    User userT = userDao.findUserByOpenId(openId);
                    if (userT != null) {
                        if (StringUtils.isNotBlank(userT.getMobile())) {
                            userDao.clearSameOpenIdUser(user);
                        } else {
                            userDao.deleteSameOpenIdAndNoMobileUser(userT.getId());
                        }
                    }
                    userDao.bindWeiXinInfo(user);
                }
            }
        }
    }

    /**
     * 更新用户微信信息
     *
     * @param openId
     */
    public void updateUserWeiXinInfo(String openId) {
        WeiXinUserInfo weiXinUserInfo = weiXinService.userInfo(openId);
        if (weiXinUserInfo != null) {
            userDao.updateWeiXinInfo(weiXinUserInfo);
        }
    }

    /**
     * 必须归到某个代理下面
     *
     * @param regBean
     */
    public ResponseEntity reg(RegBean regBean) {
        AuthEntity auth = AuthContext.getCurAuth();
        User _user = userDao.findUserByMobile(regBean.getMobile());
        if (_user != null) {
            return new ResponseErrorEntity(StatusCode.MOBILE_EXIST, HttpStatus.BAD_REQUEST);
        }
        if (!ValidateCodeUtils.mobileValid(regBean.getMobile(), regBean.getValidCode(), Constants.ValidateCode.REG)) {
            return new ResponseErrorEntity(StatusCode.VALIDATE_CODE_ERROR, HttpStatus.BAD_REQUEST);
        }

        // 校验邀请码，确保邀请码是6位
        if (StringUtils.isNotBlank(regBean.getAgentInviteCode())) {
            if (regBean.getAgentInviteCode().length() != 6) {
                return new ResponseErrorEntity(StatusCode.AGENT_INVITE_CODE_ERROR, HttpStatus.BAD_REQUEST);
            } else {
                ThirdLevelAgent thirdLevelAgent = userDao.findThirdLevelAgentByInviteCode(regBean.getAgentInviteCode());
                // 校验6位邀请码是否存在
                if (thirdLevelAgent == null) {
                    return new ResponseErrorEntity(StatusCode.AGENT_INVITE_CODE_ERROR, HttpStatus.BAD_REQUEST);
                }
            }
        } else {
            return new ResponseErrorEntity(StatusCode.AGENT_VALID_CODE_REQUIRE, HttpStatus.BAD_REQUEST);
        }

        User user = new User();
        WeChatPublic weChatPublic = wechatPublicDao.findByAppId(WeiXinInterceptor.appId());
        //String agentInviteCode=weChatPublic.getAgentInviteCode();

        //String agentInviteCode=regBean.getAgentInviteCode();
       String unitsId = userDao.findUnitsIdByAgentInviteCode("000000");
       Integer tradeStatus=userDao.findTradeStatusById(unitsId);
        logger.info("邀请码："+"000000"+","+"会员单位ID："+unitsId+","+"会员单位新会员交易状态："+tradeStatus);
        if(1 == tradeStatus){
            user.setStatus(2);
        }else{
            user.setStatus(0);
        }

        user.setChnName(regBean.getMobile());
        user.setMobile(regBean.getMobile());
        //user.setAgentInviteCode(weChatPublic.getAgentInviteCode());
        user.setTradePassword(Utils.md5(regBean.getTradePassword()));
        user.setMoney(BigDecimal.valueOf(0.00));

        // 关联军团关系
        if (StringUtils.isNotBlank(regBean.getUserId())) {
            User userById = userDao.findUserById(regBean.getUserId());
            user.setParent2Id(userById.getId());
            user.setParent3Id(userById.getParent2Id());

            regSendMsg(user.getParent2Id(), user.getMobile(), "骑兵团");
            regSendMsg(user.getParent3Id(), user.getMobile(), "步兵团");
        }

        // 第三方注册，微信
        if (StringUtils.isNotBlank(auth.getUserId())) {
            if (StringUtils.isBlank(auth.getUserId())) {
                return new ResponseErrorEntity(StatusCode.VALIDATION_FAILED, HttpStatus.BAD_REQUEST);
            }
            user.setId(auth.getUserId());
            if (userDao.reg(user) != 1) {
                return new ResponseErrorEntity(StatusCode.VALIDATION_FAILED, HttpStatus.BAD_REQUEST);
            }
        } else {
            user.preInsert();
            if (userDao.insert(user) != 1) {
                return new ResponseErrorEntity(StatusCode.VALIDATION_FAILED, HttpStatus.BAD_REQUEST);
            }
            auth.setUserId(user.getId());
            auth.setTerminal(regBean.getTerminal());
        }

        // 注册之后直接创建军团
        CreateCorpsBean createCorpsBean = new CreateCorpsBean();
        createCorpsBean.setIdCard("001");
        createCorpsBean.setTradePassword(user.getTradePassword());
        corpsService.create(createCorpsBean);

        // 送券
        couponService.reg(user.getId());

        ValidateCodeUtils.removeMobileValidateCode(regBean.getMobile(), Constants.ValidateCode.REG);
        auth.setToken(TokenService.handleToken(regBean.getTerminal(), auth.getUserId()));

        return new ResponseEntity(auth, HttpStatus.OK);
    }

    private void regSendMsg(String userId, String mobile, String msg) {
        if (userId == null || "".equals(userId)) {
            return;
        }
        UserMessage userMessage = new UserMessage();
        userMessage.setUserId(userId);
        userMessage.setTitle("新会员加入");
        userMessage.setContent(Utils.formatStr("{0}加入你的{1}",
                mobile.substring(0, 3).concat("*****").concat(mobile.substring(8, 11)), msg));
        userMessage.preInsert();
        userMessageDao.record(userMessage);
    }

    /**
     * 登录
     *
     * @param loginBean
     * @return
     */
    public ResponseEntity login(LoginBean loginBean, String path, Model model) {
        User user = userDao.findUserByMobile(loginBean.getMobile());

        if (user == null) {
            return new ResponseErrorEntity(StatusCode.ACCOUNT_NO_EXIST, HttpStatus.BAD_REQUEST);
        }

        //判断会员登录请求的二级域名
//        Ml3Agent ml3Agent = new Ml3Agent();
//        if(null == user.getAgentInviteCode()) {
//            ml3Agent.setUserId(user.getId());
//        }else{
//            ml3Agent.setAgentInviteCode(user.getAgentInviteCode());
//        }
//        ml3Agent = ml3AgentDao.findMl3AgentSelectived(ml3Agent);
//        if(null == ml3Agent){
//            return new ResponseErrorEntity(StatusCode.VALIDATION_FAILED, HttpStatus.BAD_REQUEST);
//        }
//        if(!ProjectConstants.TWO_LEVEL_DOMAIN_DEBUG) {
//            Ml3MemberUnits ml3MemberUnits = new Ml3MemberUnits();
//            ml3MemberUnits.setId(ml3Agent.getUnitsId());
//            String twoLevelDomain = ml3MemberUnitsDao.findMl3MemberUnits(ml3MemberUnits).getTwoLevelDomain();
//            if (!path.contains(twoLevelDomain)) {
//                model.addAttribute("mobile", loginBean.getMobile());
//                return new ResponseErrorEntity(StatusCode.BAD_REQUEST_ADDRESS, HttpStatus.NOT_FOUND);
//            }
//        }

        if (!user.getTradePassword().equals(loginBean.getPassword())) {
            model.addAttribute("mobile", loginBean.getMobile());
            return new ResponseErrorEntity(StatusCode.PASSWORD_ERROR, HttpStatus.BAD_REQUEST);
        }

        if (Constants.USER_STATUE.INVALID.equals(user.getStatus().toString())) {
            return new ResponseErrorEntity(StatusCode.USER_CLOSE, HttpStatus.BAD_REQUEST);
        }

        AuthEntity authEntity = new AuthEntity();
        authEntity.setUserId(user.getId());
        authEntity.setTerminal(loginBean.getTerminal());

        authEntity.setToken(TokenService.handleToken(authEntity.getTerminal(), user.getId()));
        return new ResponseEntity(authEntity, HttpStatus.OK);
    }


    /**
     * @return
     */
    public ResponseEntity get() {
        AuthEntity auth = AuthContext.getCurAuth();
        User user = userDao.findUserById(auth.getUserId());
        user.setTradePassword(null);
        return new ResponseEntity(user, HttpStatus.OK);
    }

    /**
     * @param pageEntity
     * @return
     */
    public PagerBean<Trade> tradeList(PageEntity pageEntity) {
        User user = toUser(pageEntity);
        List<Trade> list = userDao.tradeList(user);
        return new PagerBean<Trade>(list, MybatisPagerInterceptor.getTotal());
    }

    private User toUser(PageEntity pageEntity) {
        AuthEntity curAuth = AuthContext.getCurAuth();

        User user = new User();
        user.setStart(pageEntity.getStart());
        user.setRows(pageEntity.getRows());
        user.setId(curAuth.getUserId());
        return user;
    }

    /**
     * @param pageEntity
     * @return
     */
    public PagerBean<MoneyRecord> moneyRecordList(PageEntity pageEntity) {
        User user = toUser(pageEntity);
        List<MoneyRecord> list = userDao.moneyRecordList(user);
        return new PagerBean<MoneyRecord>(list, MybatisPagerInterceptor.getTotal());
    }

    /**
     * @param moneyRecord
     * @return
     */
    public MoneyRecord moneyOutInfo(MoneyRecord moneyRecord) {
        moneyRecord.setUserId(AuthContext.getUserId());
        return userDao.moneyRecord(moneyRecord);
    }

    public ResponseEntity modifyChnName(ModifyChnNameBean modifyChnNameBean) {
        User user = new User();
        user.setChnName(modifyChnNameBean.getChnName());
        user.setId(AuthContext.getUserId());
        if (userDao.update(user) != 1) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    public ResponseEntity modifyMobile(ModifyMobile modifyMobile) {
        User _user = userDao.findUserByMobile(modifyMobile.getMobile());
        if (_user != null && !_user.getId().equals(AuthContext.getUserId())) {
            return new ResponseErrorEntity(StatusCode.MOBILE_EXIST, HttpStatus.BAD_REQUEST);
        }

        if (!ValidateCodeUtils.mobileValid(modifyMobile.getMobile(), modifyMobile.getValidCode(), Constants.ValidateCode.M_MOBILE)) {
            return new ResponseErrorEntity(StatusCode.VALIDATE_CODE_ERROR, HttpStatus.BAD_REQUEST);
        }

        User userById = userDao.findUserById(AuthContext.getUserId());
        if (userById == null || !userById.getTradePassword().equals(modifyMobile.getTradePassword())) {
            return new ResponseErrorEntity(StatusCode.TRADE_PASSWORD_INVALID, HttpStatus.BAD_REQUEST);
        }

        User user = new User();
        user.setMobile(modifyMobile.getMobile());
        user.setId(AuthContext.getUserId());
        if (userDao.update(user) != 1) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        ValidateCodeUtils.removeMobileValidateCode(modifyMobile.getMobile(), Constants.ValidateCode.M_MOBILE);
        return new ResponseEntity(HttpStatus.OK);
    }

    public ResponseEntity modifyTradePwd(ModifyTradePwd modifyTradePwd) {
        User userById = userDao.findUserById(AuthContext.getUserId());
/*        if(!ValidateCodeUtils.mobileValid(userById.getMobile(), modifyTradePwd.getValidCode(), Constants.ValidateCode.M_TRADE_PWD)){
            return new ResponseErrorEntity(StatusCode.VALIDATE_CODE_ERROR, HttpStatus.BAD_REQUEST);
        }*/
        //验证原密码
        modifyTradePwd.setPreTradePassword(Utils.md5(modifyTradePwd.getPreTradePassword()));
        if (!userById.getTradePassword().equals(modifyTradePwd.getPreTradePassword())) {
            return new ResponseErrorEntity(StatusCode.PASSWORD_ERROR, HttpStatus.BAD_REQUEST);
        }

        User user = new User();
        user.setTradePassword(Utils.md5(modifyTradePwd.getTradePassword()));
        user.setId(AuthContext.getUserId());
        if (userDao.update(user) != 1) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        //ValidateCodeUtils.removeMobileValidateCode(userById.getMobile(), Constants.ValidateCode.M_TRADE_PWD);
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * 重置用户密码
     */
    public ResponseEntity validateMobileCode(FirstPartResetTradePwd firstPartResetTradePwd) {
        User user = userDao.findUserByMobile(firstPartResetTradePwd.getMobile());
        if (user == null) {
            return new ResponseErrorEntity(StatusCode.MOBILE_NOT_EXIST, HttpStatus.BAD_REQUEST);
        }
        if (!ValidateCodeUtils.mobileValid(firstPartResetTradePwd.getMobile(), firstPartResetTradePwd.getValidCode(), Constants.ValidateCode.M_TRADE_PWD)) {
            return new ResponseErrorEntity(StatusCode.VALIDATE_CODE_ERROR, HttpStatus.BAD_REQUEST);
        }
        //第二次验证清理验证码
        //ValidateCodeUtils.removeMobileValidateCode(user.getMobile(), Constants.ValidateCode.M_TRADE_PWD);
        return new ResponseEntity(HttpStatus.OK);
    }

    public ResponseEntity restTradePass(SecondPartResetTradePwd secondPartResetTradePwd) {
        User user = userDao.findUserByMobile(secondPartResetTradePwd.getMobile());
        user.setTradePassword(Utils.md5(secondPartResetTradePwd.getTradePassword()));
        if (!ValidateCodeUtils.mobileValid(secondPartResetTradePwd.getMobile(), secondPartResetTradePwd.getValidCode(), Constants.ValidateCode.M_TRADE_PWD)) {
            return new ResponseErrorEntity(StatusCode.VALIDATE_CODE_ERROR, HttpStatus.BAD_REQUEST);
        }
        ValidateCodeUtils.removeMobileValidateCode(user.getMobile(), Constants.ValidateCode.M_TRADE_PWD);
        if (userDao.resetUserTradePwd(user) != 1) {
            return new ResponseErrorEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * 用户消息查询
     *
     * @param userMessage
     * @return
     */
    public PagerBean<UserMessage> messageList(UserMessage userMessage) {
        userMessage.setUserId(AuthContext.getUserId());
        List<UserMessage> list = userMessageDao.findList(userMessage);
        return new PagerBean<UserMessage>(list, MybatisPagerInterceptor.getTotal());
    }

    /**
     * 查询用户信息详情
     */
    public UserMessage selectUserMsgDetail(UserMessage userMessage) {
        userMessage.setUserId(AuthContext.getUserId());
        UserMessage um = userMessageDao.findUserMsgDetail(userMessage);
        return um;
    }

    /**
     * 用户新消息数量
     *
     * @return
     */
    public long countNewMessage() {
        return userMessageDao.countNewMessage(AuthContext.getUserId());
    }

    /**
     * 标记用户消息为已读
     *
     * @param id
     * @return
     */
    public ResponseEntity readMessage(String id) {
        UserMessage userMessage = new UserMessage();
        userMessage.setUserId(AuthContext.getUserId());
        userMessage.setId(id);
        if (userMessageDao.readMessage(userMessage) != 1) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * 更新登录
     *
     * @param clientIp
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    public void updateLogin(String clientIp) {
        User user = new User();
        user.setLastLoginIp(clientIp);
        user.setId(AuthContext.getUserId());
        user.setLastLoginTime(new Date());
        userDao.updateLogin(user);
    }

    /**
     * TODO 查询用户是否第一次登录系统
     *
     * @return
     */
    public boolean getUserLastLoginTime(String userId) {
        if (null != userDao.getUserLastLoginTime(userId)) {
            return true;
        }
        return false;
    }

    /**
     * 绑定银行卡
     *
     * @param addBankCard
     * @return
     */
    public ResponseEntity addBankCard(AddBankCard addBankCard) {
        return addBank(addBankCard);
    }

    @Autowired
    JiuBaOutService jiuBaOutService;

    /**
     * 支付绑定银行卡
     *
     * @param addBankCard
     * @return
     */
    public ResponseEntity addBank(AddBankCard addBankCard) {
        String userId = AuthContext.getUserId();
        addBankCard.setUserId(userId);
        if (userDao.findBankCard(userId) != null) {
            return new ResponseErrorEntity(StatusCode.BANK_CARD_IS_BOUND, HttpStatus.BAD_REQUEST);
        }
        //威鹏支付验证银行支行
        BankCodeBean bankCodeByName = userDao.findBankCodeByName(addBankCard.getOpenBankName());
        String bankNo = bankCodeByName == null ? null : bankCodeByName.getBankNo();
//        if (StringUtils.isBlank(bankNo)) {
//            return new ResponseErrorEntity(StatusCode.BANK_NOT_EXIST, HttpStatus.BAD_REQUEST);
//        }
        //验证银行卡是否已被绑定
/*        if (null != userDao.findIsNullByAccount(addBankCard)) {
            return new ResponseErrorEntity(StatusCode.BANK_CARD_EXIST, HttpStatus.BAD_REQUEST);
        }*/
        //根据银行卡号查询此卡是否有提现记录
/*        MoneyRecord moneyRecord = moneyDao.findMoneyRecordByBankAccount(addBankCard);
        if(null != moneyRecord){
            if((moneyRecord.getBankAccount().equals(addBankCard.getBankAccount()) && !moneyRecord.getUserId().equals(addBankCard.getUserId()))){
                return new ResponseErrorEntity(StatusCode.BANK_CARD_EXIST, HttpStatus.BAD_REQUEST);
            }
        }*/
        CheckBankNoResp checkBankNoResp = jiuBaOutService.checkBankNo(addBankCard.getBankAccount());

        if(checkBankNoResp != null){
            String str = JSON.toJSONString(addBankCard);
            BankCard bankCard = JSON.parseObject(str, BankCard.class);
            bankCard.setUserId(userId);
            bankCard.setLianHangNo(bankNo);
            bankCard.setBankCode(checkBankNoResp.getHead_code());

            bankCard.preInsert();
            userDao.addBankCard(bankCard);
            return new ResponseEntity(HttpStatus.OK);
        }else{

            return new ResponseEntity(StatusCode.NOT_FIND_BANK_NO, HttpStatus.BAD_REQUEST);
        }
    }



    /**
     * TODO 修改当前用户银行卡信息
     *
     * @param addBankCard
     * @return
     */
    public ResponseEntity updateBankCard(AddBankCard addBankCard) {
        String userId = AuthContext.getUserId();
        BankCard bankCard = userDao.findBankCard(userId);
        addBankCard.setUserId(userId);
        if (null != bankCard) {
            //验证银行支行
            /*String bankNo = userDao.findBankNoByOpenBankName(addBankCard.getOpenBankName());
            if(StringUtils.isBlank(bankNo)){
                return new ResponseErrorEntity(StatusCode.BANK_NOT_EXIST, HttpStatus.BAD_REQUEST);
            }*/
            //威鹏支付验证银行支行
//            String bankNo = userDao.findBankCodeByName(addBankCard.getOpenBankName()).getBankNo();
//            if (StringUtils.isBlank(bankNo)) {
//                return new ResponseErrorEntity(StatusCode.BANK_NOT_EXIST, HttpStatus.BAD_REQUEST);
//            }
            //验证银行卡是否已被绑定
/*            if (null != userDao.findIsNullByAccount(addBankCard)) {
                return new ResponseErrorEntity(StatusCode.BANK_CARD_EXIST, HttpStatus.BAD_REQUEST);
            }*/
            //根据银行卡号查询此卡是否有提现记录
/*            MoneyRecord moneyRecord = moneyDao.findMoneyRecordByBankAccount(addBankCard);
            if(null != moneyRecord){
                if((moneyRecord.getBankAccount().equals(addBankCard.getBankAccount()) && !moneyRecord.getUserId().equals(addBankCard.getUserId()))){
                    return new ResponseErrorEntity(StatusCode.BANK_CARD_EXIST, HttpStatus.BAD_REQUEST);
                }
            }*/
            //验证密码
            User userById = userDao.findUserById(userId);
            if (!userById.getTradePassword().equals(addBankCard.getPassword())) {
                return new ResponseErrorEntity(StatusCode.PASSWORD_ERROR, HttpStatus.BAD_REQUEST);
            }
            //修改信息
            addBankCard.setUserId(userId);
            userDao.updateBankCard(addBankCard);
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseErrorEntity(HttpStatus.BAD_REQUEST);
    }

    /**
     * @return
     */
    public BankCard findBankCard() {
        return userDao.findBankCard(AuthContext.getUserId());
    }

    /**
     * TODO 生成微信二维码zxing
     */
    public BufferedImage generateRQCode(ServletOutputStream write) {
        String userId = AuthContext.getUserId();
        User user = userDao.findUserById(userId);
        Ml3Agent ml3Agent = null;
        if (null != user.getAgentInviteCode()) {
            //查询登录用户的代理的邀请码 : agent_invite_code
            ml3Agent = ml3AgentDao.findMl3AgentSelective(user.getAgentInviteCode());
        } else {
            ml3Agent = new Ml3Agent();
            ml3Agent.setUserId(user.getId());
            ml3Agent = ml3AgentDao.findMl3AgentSelectived(ml3Agent);
        }

        //根据会员的代理信息中的unitsid查询会员单位全部信息 ： two_level_domain
        Ml3MemberUnits ml3MemberUnits = new Ml3MemberUnits();
        ml3MemberUnits.setId(ml3Agent.getUnitsId());
        ml3MemberUnits = ml3MemberUnitsDao.findMl3MemberUnits(ml3MemberUnits);
        String url = parameterSetDao.findParameterSet().getQrCodeUrl();
        if (!StringUtils.isBlank(url)) {
            url = Utils.formatStr(url, ml3MemberUnits.getTwoLevelDomain(), ml3Agent.getAgentInviteCode(), user.getId());
        }
        byte[] imageByte = Utils.generateOrCode(url);

        return base64StringToImage(encoder.encode(imageByte).trim(), write);

        /*return encoder.encodeBuffer(imageByte).trim();*/
    }

    /**
     * TODO 转换byte字节流-->微信图片
     *
     * @param base64String
     * @param write
     * @return
     */
    public BufferedImage base64StringToImage(String base64String, ServletOutputStream write) {
        try {
            byte[] bytes1 = decoder.decodeBuffer(base64String);

            ByteArrayInputStream bais = new ByteArrayInputStream(bytes1);
            BufferedImage bufferedImage = ImageIO.read(bais);

            //ImageIO.write(bi1, "jpg", write);//不管输出什么格式图片，此处不需改动
            return bufferedImage;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getAgentInviteCode(String mobile) {
        User user = userDao.findUserByMobile(mobile);
        Ml3Agent ml3Agent = ml3AgentDao.findMl3AgentSelective(user.getAgentInviteCode());
        Ml3MemberUnits ml3MemberUnits = new Ml3MemberUnits();
        ml3MemberUnits.setId(ml3Agent.getUnitsId());
        return ml3MemberUnitsDao.findMl3MemberUnits(ml3MemberUnits).getId();
    }

    /**
     * 查询用户持仓金额
     */
    public BigDecimal selUserHoldMoney() {
        AuthEntity auth = AuthContext.getCurAuth();
        return userDao.selUserHoldMoney(auth.getUserId());
    }

    /**
     * 查询用户持仓数量
     */
    public Long selUserHoldCounts() {
        return userDao.selUserHoldCounts(AuthContext.getUserId());
    }

    /**
     * 临时二维码有效时间
     */
    private int QRCODE_TICKET_EXPIRE_ECONDS = 30 * 24 * 3600;

    /**
     * 邀请二维码
     *
     * @param writer
     */
    public void getShareImgQRCode(ServletOutputStream writer, String userId) {
        User userById = userDao.findUserById(userId);
        Calendar instance = Calendar.getInstance();
        File file;
        try {
            file = new File(URLDecoder.decode(UserService.class.getResource("/").getPath(), "utf-8").concat("shareImg.jpg"));
            // ticket 为空或者过期或者与现在登录的公众号不同时，去微信获取
            if (StringUtils.isBlank(userById.getTicket()) || userById.getTicketExpireTime().before(instance.getTime())
                    || !userById.getWechatPublicId().equals(userById.getTicketWechatPublicId())) {
                String tempTicket = weiXinService.qrcodeCreateTemp(userById.getAutoId(), QRCODE_TICKET_EXPIRE_ECONDS);
                if (StringUtils.isNotBlank(tempTicket)) {
                    userById.setTicket(tempTicket);
                    userById.setTicketWechatPublicId(wechatPublicDao.findByAppId(WeiXinInterceptor.appId()).getId());
                    instance.add(Calendar.SECOND, QRCODE_TICKET_EXPIRE_ECONDS);
                    userById.setTicketExpireTime(instance.getTime());

                    userDao.updateQrcodeTicket(userById);
                } else {

                }
            }

            if (userById.getTicket() != null) {
                Thumbnails.of(file)
                        .scale(1f)
                        .watermark((int i, int i1, int i2, int i3, int i4, int i5, int i6, int i7) -> new Point(160,500),
                                Thumbnails.of(ImageIO.read(new URL("https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=".concat(userById.getTicket()))))
                                        .size(130, 130)
                                        .watermark(Positions.CENTER, Thumbnails.of(
                                                userById.getUserHeader() == null || userById.getUserHeader().equals("") ?
                                                        ImageIO.read(new FileInputStream(new File(UserService.class.getResource("/").getPath().concat("logo.jpg")))) :
                                                        ImageIO.read(new URL(userById.getUserHeader()))
                                        ).size(35, 35).asBufferedImage(), 1f)
                                        .outputQuality(1f).imageType(1).asBufferedImage(), 1f)
                        .outputQuality(1f).outputFormat("jpg").toOutputStream(writer);
            } else {
                Thumbnails.of(file).scale(1f).outputQuality(1f).outputFormat("jpg").toOutputStream(writer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public User getUserMoney(String tradeId) {
        if (Integer.parseInt(tradeId) > 0) {
            if (Integer.parseInt(tradeId) < findUserTradeCount()) {
                try {
                    Thread.currentThread().sleep(1000);
//                    getUserMoney(tradeId);
                    return null;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        User user = userDao.findUserById(AuthContext.getUserId());
        return user;
    }

    /**
     * TODO 查询用户持仓单数
     *
     * @return
     */
    public int findUserTradeCount() {
        int num = tradeDao.findUserTradeCount(AuthContext.getUserId()).size();
        return num;
    }

    public void findCityUtils(Model model) {
        Map<String, String> backName = CityUtils.getLianHangHaoMap();
        List<String> backNameList = new ArrayList();
        /*for (String key : backName.keySet()){
            backNameList.add(key);
        }*/
        for (Map.Entry<String, String> entry : backName.entrySet()) {
            backNameList.add(entry.getKey());
        }
        model.addAttribute("bankNameList", backNameList);
    }

    /**
     * TODO 获取全国银行排名前15
     *
     * @param model
     */
    public void findBankNameList(Model model) {
        List<String> bankNameList = CityUtils.getBankNameList();
        model.addAttribute("bankNameList", bankNameList);
    }

    /**
     * TODO 提现页面，查询用户银行卡是否存在
     *
     * @param model
     */
    public void getUserBank(Model model) {
        BankCard bankCard = findBankCard();
        model.addAttribute("bankCard", bankCard);
        model.addAttribute("bankCardStatus", JSON.toJSONString(bankCard));
        findBankNameList(model);
    }

    /**
     * TODO 查询支行信息--模糊查询提示
     *
     * @param mainWord
     * @return
     */
    public List<String> findOpenBankName(String mainWord) {
        if (StringUtils.isEmpty(mainWord)) {
            return null;
        }
        String[] strs = mainWord.trim().split("");
        StringBuffer sb = new StringBuffer();
        for (String s : strs) {
            sb.append("%").append(s);
        }
        sb.append("%");
        mainWord = sb.toString();

        //阿拉支付查询支行名称
        /*userDao.findOpenBankName(mainWord);*/
        //威鹏支付查询支行名称
        return userDao.findBankCodeName(mainWord);
    }
}
