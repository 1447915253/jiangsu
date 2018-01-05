package com.rmkj.microcap.modules.money.controller.wap;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.rmkj.microcap.common.base.BaseController;
import com.rmkj.microcap.common.bean.annotation.LoginAnnot;
import com.rmkj.microcap.common.constants.Constants;
import com.rmkj.microcap.common.constants.ProjectConstants;
import com.rmkj.microcap.common.modules.pay.shande.bean.ShanDeH5OpenIdBean;
import com.rmkj.microcap.common.modules.pay.shande2.bean.ShanDeH5OpenIdBean2;
import com.rmkj.microcap.common.modules.weixin.face.NotifyForPayService;
import com.rmkj.microcap.common.utils.AuthContext;
import com.rmkj.microcap.common.utils.Utils;
import com.rmkj.microcap.modules.index.service.ParameterService;
import com.rmkj.microcap.modules.money.entity.CashCoupon;
import com.rmkj.microcap.modules.money.entity.MoneyRecord;
import com.rmkj.microcap.modules.money.service.CashCouponService;
import com.rmkj.microcap.modules.money.service.MoneyService;
import com.rmkj.microcap.modules.user.entity.User;
import com.rmkj.microcap.modules.user.service.UserService;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Parameter;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 123 on 2016/10/24.
 */
@Controller
@RequestMapping("${wap}/money")
public class MoneyController extends BaseController{

    private static final Logger Log = Logger.getLogger(MoneyController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private CashCouponService cashCouponService;

    @Autowired
    private MoneyService moneyService;

    @Autowired
    private ParameterService parameterService;

    @RequestMapping("/out")
    @LoginAnnot
    public String moneyOut(Model model){
        User user = (User) userService.get().getBody();
        model.addAttribute("user", user);
        model.addAttribute("parameterSet", parameterService.findParameterSetList());
        userService.getUserBank(model);
        return "wap/user/moneyOut";
    }

    @RequestMapping(value = "/cashCoupon")
    @LoginAnnot
    public String lookCashCoupon(Model model){
        List<CashCoupon> cashCouponList = cashCouponService.findUserCashCouponByUserId();
        model.addAttribute("cashCouponList",cashCouponList);
        return "/wap/cashCoupon";
    }

    @RequestMapping(value = "/qrcode")
    @LoginAnnot
    public String qrcodePage(String url, Model model,String payType) throws UnsupportedEncodingException {
        model.addAttribute("url", URLEncoder.encode(url, "utf-8"));
        //判断支付类型，跳转不同页面
        if("wechat".equals(payType)){
            return "wap/pay/nineEightWeiXinQrcode";
        }else if("alipay".equals(payType)){
            return "wap/pay/nineEightAliQrcode";
        }else if("jingdong".equals(payType)){
            return "wap/pay/nineEightJDQrcode";
        }else{
            return "wap/pay/nineEightQQQrcode";
        }
    }

    /**
     * 带图片的微信支付二维码
     * @param request
     * @param response
     */
    @RequestMapping(value = "/qrcodeImg")
    @LoginAnnot
    public void getRQCode(HttpServletRequest request, HttpServletResponse response){
        ServletOutputStream writer = null;
        response.setContentType("image/jpg");
        response.setHeader("Pragma", "No-cache");
        String content = request.getParameter("url");// 内容
        int width = 300; // 图像宽度
        int height = 300; // 图像高度
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        BitMatrix bitMatrix = null;// 生成矩阵
        FileOutputStream fos = null;
        File file = null;
        try {
            bitMatrix = new MultiFormatWriter().encode(content,
                    BarcodeFormat.QR_CODE, width, height, hints);
            String filePath = UserService.class.getResource("/").getPath();
            filePath = filePath.substring(0, filePath.length()-1);
            String fileName = Utils.uuid().concat(".jpg");
            file = new File(filePath, fileName);
            fos = new FileOutputStream(file);
            MatrixToImageWriter.writeToStream(bitMatrix, "jpg", fos);

            writer = response.getOutputStream();
            Thumbnails.of(file).size(width, height)
                    .watermark(Positions.CENTER, Thumbnails.of(new File(filePath, "logo.jpg")).scale(0.3).asBufferedImage(), 1f)
                    .outputQuality(1f).imageType(1).outputFormat("jpg").toOutputStream(writer);
            writer.flush();
            writer.close();
        } catch (WriterException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            if(fos != null){
                try {
                    fos.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(file != null){
                file.delete();
            }
        }

    }
    static BASE64Encoder encoder = new sun.misc.BASE64Encoder();
    static BASE64Decoder decoder = new sun.misc.BASE64Decoder();

    /**
     * 生成纯文本二维码
     * @param request url 微信扫码url
     * @param response
     */
    @RequestMapping(value = "/qrcodeImgNotImage")
    @LoginAnnot
    public void getRQCodeNotImage(HttpServletRequest request, HttpServletResponse response){
        ServletOutputStream write = null;
        response.setContentType("image/jpg");
        response.setHeader("Pragma", "No-cache");
        String content = request.getParameter("url");// 内容
        byte [] imageByte = Utils.generateOrCode(content);
        BufferedImage bufferedImage = userService.base64StringToImage(encoder.encode(imageByte).trim(), write);
        try {
            write = response.getOutputStream();
            ImageIO.write(bufferedImage, "jpg", write);
            write.flush();
            write.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * TODO 跳转充值记录页面
     * @return
     */
    @RequestMapping(value = "/payRecord")
    @LoginAnnot
    public String payRecord(MoneyRecord moneyRecord, Model model){
        model.addAttribute("list", moneyService.findUserPayMoneyRecord(moneyRecord));
        if("1".equals(moneyRecord.getType())){
            model.addAttribute("type",1);
        }else if("0".equals(moneyRecord.getType())){
            model.addAttribute("type",0);
        }else{
            model.addAttribute("type",null);
        }
        return "wap/pay/payRecord";
    }

    /**
     * TODO 跳转账单记录页面
     * @return
     */
    @RequestMapping(value = "/getBilling", method = RequestMethod.GET)
    public String getBillingDetails(Model model, String flag){
        moneyService.findMoneyChangeByUserId(model, flag);
        return "wap/user/billing";
    }

    /**
     * 杉德支付，拿到openId，去微信公众号支付
     * @param openIdBean
     * @return
     */
    @RequestMapping(value = "/getOpenId",method = RequestMethod.GET)
    @LoginAnnot
    public String getOpenId(ShanDeH5OpenIdBean openIdBean, Model model,HttpServletRequest request){
        //获取到openid，去微信支付
        if(StringUtils.isEmpty(openIdBean.getOpen_id()) || StringUtils.isEmpty(openIdBean.getSub_open_id())){
            return null;
        }
        //String perPayId = moneyService.shanDeH5Pay(openIdBean);
        //String url = "https://shq-api.51fubei.com/paypage?prepay_id="+perPayId+"&callback_url=http://"+request.getServerName()+"/front/wap/user"+"&cancel_url=http://"+request.getServerName()+"/front/wap/user";
        //String url = "http://shq-api.51fpay.com/paypage?prepay_id="+perPayId+"&callback_url=http://"+request.getServerName()+"/front/wap/user"+"&cancel_url=http://"+request.getServerName()+"/front/wap/user";
        String url = moneyService.shanDeH5Pay(openIdBean,request);
        model.addAttribute("url",url);
        return "wap/pay/shandePay";
    }

    /**
     * 杉德支付，拿到openId，去微信公众号支付2
     * @param openIdBean2
     * @return
     */
    @RequestMapping(value = "/getOpenId2",method = RequestMethod.GET)
    @LoginAnnot
    public String getOpenId2(ShanDeH5OpenIdBean2 openIdBean2, Model model, HttpServletRequest request){
        //获取到openid，去微信支付
        if(StringUtils.isEmpty(openIdBean2.getOpen_id()) || StringUtils.isEmpty(openIdBean2.getSub_open_id())){
            return null;
        }
        String perPayId = moneyService.shanDeH5Pay2(openIdBean2);
        String url = "https://shq-api.51fubei.com/paypage?prepay_id="+perPayId+"&callback_url=http://"+request.getServerName()+"/front/wap/user"+"&cancel_url=http://"+request.getServerName()+"/front/wap/user";
        Log.info("杉德公众号支付url："+url);
        model.addAttribute("url",url);
        return "wap/pay/shandePay";
    }
}
