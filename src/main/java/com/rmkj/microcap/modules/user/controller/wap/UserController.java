package com.rmkj.microcap.modules.user.controller.wap;

import com.rmkj.microcap.common.base.BaseController;
import com.rmkj.microcap.common.bean.PageEntity;
import com.rmkj.microcap.common.bean.PagerBean;
import com.rmkj.microcap.common.bean.ResponseErrorEntity;
import com.rmkj.microcap.common.bean.annotation.LoginAnnot;
import com.rmkj.microcap.common.utils.AuthContext;
import com.rmkj.microcap.modules.index.service.RootService;
import com.rmkj.microcap.modules.money.entity.MoneyRecord;
import com.rmkj.microcap.modules.money.service.MoneyService;
import com.rmkj.microcap.modules.trade.entity.Trade;
import com.rmkj.microcap.modules.user.entity.User;
import com.rmkj.microcap.modules.user.entity.UserMessage;
import com.rmkj.microcap.modules.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sun.misc.BASE64Decoder;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;

/**
 * Created by 123 on 2016/10/20.
 */
@Controller
@RequestMapping("${wap}/user")
public class UserController extends BaseController{

    @Autowired
    private UserService userService;

    @Autowired
    private RootService rootService;

    @Autowired
    private MoneyService moneyService;

    @RequestMapping(value = "")
    @LoginAnnot
    public String index(Model model){
        User user = (User) userService.get().getBody();
        model.addAttribute("user", user);
        BigDecimal holdMoney = userService.selUserHoldMoney();
        model.addAttribute("holdMoney",holdMoney);
        Long holdCounts = userService.selUserHoldCounts();
        model.addAttribute("holdCounts",holdCounts);
        rootService.countNewMessage(model);
        //查询未读消息数量
        Long notReadMessage = userService.countNewMessage();
        model.addAttribute("messageCount", notReadMessage == 0L ? null : notReadMessage);
        return "wap/homepage";
    }

    @RequestMapping(value = "/tradeList")
    @LoginAnnot
    public String tradeList(PageEntity pageEntity, Model model){
        pageEntity.setStart(0);
        pageEntity.setRows(4);
        PagerBean<Trade> pb = userService.tradeList(pageEntity);
        model.addAttribute("pb", pb);
        return "wap/tradeList";
    }

    @RequestMapping(value = "/moneyList")
    @LoginAnnot
    public String moneyList(PageEntity pageEntity, Model model){
        pageEntity.setStart(0);
        pageEntity.setRows(5);
        PagerBean<MoneyRecord> pb = userService.moneyRecordList(pageEntity);
        model.addAttribute("pb", pb);
        return "wap/moneyList";
    }

    @RequestMapping(value = "/moneyOutDetail")
    @LoginAnnot
    public String moneyOutDetail(MoneyRecord moneyRecord, Model model){
        moneyRecord = userService.moneyOutInfo(moneyRecord);
        model.addAttribute("moneyRecord", moneyRecord);
        return "wap/moneyOutDetail";
    }

    @RequestMapping(value = "/modifyMobile")
    @LoginAnnot
    public String modifyMobile(Model model){
        User user = (User) userService.get().getBody();
        model.addAttribute("user", user);
        return "wap/modifyMobile";
    }

    @RequestMapping(value = "/modifyTradePwd")
    @LoginAnnot
    public String modifyTradePwd(Model model){
        User user = (User) userService.get().getBody();
        model.addAttribute("user", user);
        return "wap/user/modifyTradePwd";
    }

    @RequestMapping(value = "/userMessageList")
    @LoginAnnot
    public String userMessageList(Model model){
        UserMessage userMessage = new UserMessage();
        model.addAttribute("pb", userService.messageList(userMessage));
        return "wap/user/userMessageList";
    }

    @RequestMapping(value = "/userMessageDetail")
    @LoginAnnot
    public String userMessageDetail(UserMessage userMessage,Model model){
        userService.readMessage(userMessage.getId());
        model.addAttribute("userDetail", userService.selectUserMsgDetail(userMessage));
        return "wap/user/userMessageDetail";
    }

    @RequestMapping(value = "/bankCard")
    @LoginAnnot
    public String bankCard(Model model){
        model.addAttribute("user", userService.get().getBody());
        model.addAttribute("bankCard", userService.findBankCard());
        model.addAttribute("isWithdraw",moneyService.findUserWithdraw(AuthContext.getUserId()).size()>0?"1":"0");
        //userService.findCityUtils(model);
        userService.findBankNameList(model);
        return "wap/user/Withdrawals";
    }



    /**
     * TODO 生成带图片的微信二维码
     * @param request
     * @param response
     */
    @RequestMapping(value = "/shareImg")
    @LoginAnnot
    public void getRQCode(HttpServletRequest request, HttpServletResponse response){
        ServletOutputStream write = null;
        response.setContentType("image/jpg");
        response.setHeader("Pragma", "No-cache");
        //BufferedImage bufferedImage = userService.generateRQCodeImage(write);
        BufferedImage bufferedImage = userService.generateRQCode(write);
        try {
            write = response.getOutputStream();
            ImageIO.write(bufferedImage, "jpg", write);
            write.flush();
            write.close();
        }catch (IOException e){
            e.printStackTrace();
        }


    }

    //提现时忘记密码
    @RequestMapping(value = "/firstPartResetTradePassWordWhenMoneyOut")
    @LoginAnnot
    public String firstPartResetTradePassWordWhenMoneyOut(Model model){
        User user = (User) userService.get().getBody();
        String mobile = user.getMobile();
        model.addAttribute("mobile",mobile);
        return "wap/firstPartResetTradePassWord";
    }
    /**
     * TODO 分享朋友圈二维码
     * @param userId
     * @param request
     * @param response
     */
    @RequestMapping(value = "/getShareImgQRCode")
    @LoginAnnot
    public void getShareQRCode(HttpServletRequest request, HttpServletResponse response){
        ServletOutputStream writer = null;
        try {
            response.setContentType("image/jpg");
            writer = response.getOutputStream();
            userService.getShareImgQRCode(writer, AuthContext.getUserId());
            writer.flush();
            writer.close();
            writer = null;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(writer != null){
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * TODO 跳转关于我们
     * @return
     */
    @RequestMapping(value = "/aBout")
    public String getAbuot(){
        return "wap/aBout";
    }

    @RequestMapping(value = "/getservice")
    public String getService(){
        return "wap/service";
    }

    /**
     * TODO 跳转代金券详情页面
     * @param type
     * @param num
     * @param createTime
     * @param endTime
     * @param model
     * @return
     */
    @RequestMapping(value = "/couponInfo")
    public String getcouponInfo(String type,String money,String num,String createTime,String endTime,Model model){
        model.addAttribute("type", type);
        model.addAttribute("money",money);
        model.addAttribute("num", num);
        model.addAttribute("createTime", createTime);
        model.addAttribute("endTime", endTime);
        return "wap/user/couponInfo";
    }

}
