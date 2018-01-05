package com.rmkj.microcap.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.awt.font.ImageGraphicAttribute;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by zhangbowen on 2015/12/4.
 */
public class Utils {
    public static final int MINUTE = 60;
    public static final int HOUR = MINUTE * 60;

    public static String uuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 根据手机号生成用户名
     *
     * @param phone
     * @return
     */
    public static String generateNameByPhone(String phone) {
        return phone.substring(0, 3) + "****" + phone.substring(7);
    }

    /**
     * 生成4位短信验证码
     *
     * @return
     */
    public static String generateSmsCode() {
        return generateSmsCode(4);
    }

    /**
     *
     * @param lenth
     * @return
     */
    public static String generateSmsCode(int lenth) {
        Random random = new Random();
        String verificationCode = "";
        for (int i = 0; i < lenth; i++) {
            verificationCode += random.nextInt(10);
        }
        return verificationCode;
    }

    /**
     * 邀请码生成工具
     */
    public static class InvitationCodeUtils {

        /**
         * 自定义进制(0,1没有加入,容易与o,l混淆)
         */
        private static final char[] r = new char[]{'Q', 'w', 'E', '8', 'a', 'S', '2', 'd', 'Z', 'x', '9', 'c', '7', 'p', 'O', '5', 'i', 'K', '3', 'm', 'j', 'U', 'f', 'r', '4', 'V', 'y', 'L', 't', 'N', '6', 'b', 'g', 'H'};
        /**
         * 自动补全组(不能与自定义进制有重复)
         */
        private static final char[] b = new char[]{'q', 'W', 'e', 'A', 's', 'D', 'z', 'X', 'C', 'P', 'o', 'I', 'k', 'M', 'J', 'u', 'F', 'R', 'v', 'Y', 'T', 'n', 'B', 'G', 'h'};
        /**
         * 进制长度
         */
        private static final int l = r.length;
        /**
         * 序列最小长度
         */
        private static final int s = 6;

        /**
         * 根据id或手机号生成六位随机码
         *
         * @param num
         * @return 随机码
         */
        public static String generateCode(long num) {
            char[] buf = new char[32];
            int charPos = 32;

            while ((num / l) > 0) {
                buf[--charPos] = r[(int) (num % l)];
                num /= l;
            }
            buf[--charPos] = r[(int) (num % l)];
            String str = new String(buf, charPos, (32 - charPos));
            //不够长度的自动随机补全
            if (str.length() < s) {
                StringBuffer sb = new StringBuffer();
                Random rnd = new Random();
                for (int i = 0; i < s - str.length(); i++) {
                    sb.append(b[rnd.nextInt(b.length - 1)]);
                }
                str += sb.toString();
            }
            return str;
        }
    }

    public static Class getSuperClassGenericType(final Class clazz, final int index) {
        //返回表示此 Class 所表示的实体（类、接口、基本类型或 void）的直接超类的 Type。
        Type genType = clazz.getGenericSuperclass();
        if (!(genType instanceof ParameterizedType)) {
            return Object.class;
        }
        //返回表示此类型实际类型参数的 Type 对象的数组。
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        if (index >= params.length || index < 0) {
            return Object.class;
        }
        if (!(params[index] instanceof Class)) {
            return Object.class;
        }
        return (Class) params[index];
    }

    public static String md5(String str) {
        return DigestUtils.md5Hex(str);
    }

    public static String sha(String str) {
        return DigestUtils.sha1Hex(str);
    }

    /**
     * 生成二维码
     *
     * @return
     */
    public static byte[] generateOrCode(String text) {
        int width = 300;
        int height = 300;
        Hashtable hints = new Hashtable();
        String format = "png";
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, width, height, hints);
            MatrixToImageWriter.writeToStream(bitMatrix, format, byteArrayOutputStream);
        } catch (WriterException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return byteArrayOutputStream.toByteArray();
    }

    /**
     * TODO 生成带图片的二维码zxing
     * @param url
     * @param srcImagePath
     * @return
     */
    public static BufferedImage generateORCodeImage(String url, String srcImagePath) {
        // 图片宽度的一般
        int IMAGE_WIDTH = 80;
        int IMAGE_HEIGHT = 80;
        int IMAGE_HALF_WIDTH = IMAGE_WIDTH / 2;
        int FRAME_WIDTH = 2;
        try {
            // 读取源图像
            BufferedImage scaleImage = new Utils().scale(srcImagePath, IMAGE_WIDTH, IMAGE_HEIGHT, true);
            int[][] srcPixels = new int[IMAGE_WIDTH][IMAGE_HEIGHT];
            for (int i = 0; i < scaleImage.getWidth(); i++) {
                for (int j = 0; j < scaleImage.getHeight(); j++) {
                    srcPixels[i][j] = scaleImage.getRGB(i, j);
                }
            }

            //二维码宽高
            int width = 500;
            int height = 500;


            Map<EncodeHintType, Object> hint = new HashMap<EncodeHintType, Object>();
            hint.put(EncodeHintType.CHARACTER_SET, "utf-8");
            hint.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
            //生成二维码

            BitMatrix matrix = new MultiFormatWriter().encode(url, BarcodeFormat.QR_CODE, width, height, hint);

            // 二维矩阵转为一维像素数组
            int halfW = matrix.getWidth() / 2;
            int halfH = matrix.getHeight() / 2;
            int[] pixels = new int[width * height];

            for (int y = 0; y < matrix.getHeight(); y++) {
                for (int x = 0; x < matrix.getWidth(); x++) {
                    // 读取图片
                    if (x > halfW - IMAGE_HALF_WIDTH
                            && x < halfW + IMAGE_HALF_WIDTH
                            && y > halfH - IMAGE_HALF_WIDTH
                            && y < halfH + IMAGE_HALF_WIDTH) {
                        pixels[y * width + x] = srcPixels[x - halfW
                                + IMAGE_HALF_WIDTH][y - halfH + IMAGE_HALF_WIDTH];
                    }
                    // 在图片四周形成边框
                    else if ((x > halfW - IMAGE_HALF_WIDTH - FRAME_WIDTH
                            && x < halfW - IMAGE_HALF_WIDTH + FRAME_WIDTH
                            && y > halfH - IMAGE_HALF_WIDTH - FRAME_WIDTH && y < halfH
                            + IMAGE_HALF_WIDTH + FRAME_WIDTH)
                            || (x > halfW + IMAGE_HALF_WIDTH - FRAME_WIDTH
                            && x < halfW + IMAGE_HALF_WIDTH + FRAME_WIDTH
                            && y > halfH - IMAGE_HALF_WIDTH - FRAME_WIDTH && y < halfH
                            + IMAGE_HALF_WIDTH + FRAME_WIDTH)
                            || (x > halfW - IMAGE_HALF_WIDTH - FRAME_WIDTH
                            && x < halfW + IMAGE_HALF_WIDTH + FRAME_WIDTH
                            && y > halfH - IMAGE_HALF_WIDTH - FRAME_WIDTH && y < halfH
                            - IMAGE_HALF_WIDTH + FRAME_WIDTH)
                            || (x > halfW - IMAGE_HALF_WIDTH - FRAME_WIDTH
                            && x < halfW + IMAGE_HALF_WIDTH + FRAME_WIDTH
                            && y > halfH + IMAGE_HALF_WIDTH - FRAME_WIDTH && y < halfH
                            + IMAGE_HALF_WIDTH + FRAME_WIDTH)) {
                        pixels[y * width + x] = 0xfffffff;
                    } else {
                        // 此处可以修改二维码的颜色，可以分别制定二维码和背景的颜色；
                        pixels[y * width + x] = matrix.get(x, y) ? 0xff000000
                                : 0xfffffff;
                    }
                }
            }

            BufferedImage image = new BufferedImage(width, height,
                    BufferedImage.TYPE_INT_RGB);
            image.getRaster().setDataElements(0, 0, width, height, pixels);
            return image;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 获得classpath下的文件流
     *
     * @param path
     * @return
     */
    public static InputStream getClassPathFileStream(String path) {
        Resource resource = new ClassPathResource(path);
        if (resource.isReadable()) {
            //每次都会打开一个新的流
            try {
                return resource.getInputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    /**
     * 获取ip地址,防止集群、代理
     *
     * @param request
     * @return ip
     */
    public static String getClientIp(HttpServletRequest request) {
        return request.getRemoteAddr();
    }

    public static String getCookieValue(HttpServletRequest request, String cookieName) {
        Cookie[] cookies = request.getCookies();
        if (null != cookies) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(cookieName)) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    /**
     * 格式化字符串：
     * 你好，{0}，我是你孩子的老师{1}，请你{2}来参加家长会
     * {0} 奥巴马
     * {1} 川普
     * {2} 2016-12-12早8点
     * 你好，奥巴马，我是你孩子的老师川普，请你2016-12-12早8点来参加家长会
     * @param resource
     * @param str
     * @return
     */
    public static String formatStr(String resource, String ... str){
        for (int i = 0; i < str.length; i++){
            resource = resource.replace("{"+i+"}", "%"+(i+1)+"$s");
        }
        return String.format(resource, str);
    }

    /**
     * 查询值得位置，并获取相同位置的数组中的数据
     * @param a
     * @param b
     * @param val
     * @param <T>
     * @return
     */
    public static <T,M> T samePosition(M[] a, T[] b, M val){
        if(val != null && a != null && b != null && a.length != 0 && a.length == b.length){
            for (int i = 0; i < a.length; i++){
                if(val.equals(a[i])){
                    return b[i];
                }
            }
        }
        return null;
    }

    /**
     *
     * @param str
     * @return
     */
    public static BigDecimal toBigDecimal(String str) {
        return BigDecimal.valueOf(Double.valueOf(str));
    }

    /**
     * 将对象不为空的属性转化为http参数链，ASCII字典序排序
     * key1=value1&key2=value2
     * @param obj
     * @return
     */
    public static String param(Object obj){
        JSONObject o = JSON.parseObject(JSON.toJSONString(obj));
        Set<String> set = new TreeSet<>(o.keySet());
        if(set.isEmpty()){
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (String key : set){
            if(sb.length()!=0){
                sb.append("&");
            }
            if(StringUtils.isNotBlank(o.get(key).toString())){
                sb.append(key.concat("=").concat(o.get(key).toString()));
            }
        }
        return sb.toString();
    }

    /**
     * 将对象不为空的属性转化为http参数链，ASCII字典序排序
     * key1value1key2value2
     * @param obj
     * @return
     */
    public static String param2(Object obj){
        JSONObject o = JSON.parseObject(JSON.toJSONString(obj));
        Set<String> set = new TreeSet<>(o.keySet());
        if(set.isEmpty()){
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (String key : set){
            sb.append(key.concat(o.get(key).toString()));
        }
        return sb.toString();
    }

    /**
     * TODO 把传入的原始图像按高度和宽度进行缩放，生成符合要求的图标
     *
     * @param srcImageFile
     *            源文件地址
     * @param height
     *            目标高度
     * @param width
     *            目标宽度
     * @param hasFiller
     *            比例不对时是否需要补白：true为补白; false为不补白;
     * @throws IOException
     */
    private BufferedImage scale(String srcImageFile, int height, int width, boolean hasFiller) throws IOException {
        double ratio = 0.0; // 缩放比例
        File file = new File(srcImageFile);
        BufferedImage srcImage = ImageIO.read(file);
        Image destImage = srcImage.getScaledInstance(width, height,
                BufferedImage.SCALE_SMOOTH);
        // 计算比例
        if ((srcImage.getHeight() > height) || (srcImage.getWidth() > width)) {
            if (srcImage.getHeight() > srcImage.getWidth()) {
                ratio = (new Integer(height)).doubleValue()
                        / srcImage.getHeight();
            } else {
                ratio = (new Integer(width)).doubleValue()
                        / srcImage.getWidth();
            }
            AffineTransformOp op = new AffineTransformOp(
                    AffineTransform.getScaleInstance(ratio, ratio), null);
            destImage = op.filter(srcImage, null);
        }
        if (hasFiller) {// 补白
            BufferedImage image = new BufferedImage(width, height,
                    BufferedImage.TYPE_INT_RGB);
            Graphics2D graphic = image.createGraphics();
            graphic.setColor(Color.white);
            graphic.fillRect(0, 0, width, height);
            if (width == destImage.getWidth(null))
                graphic.drawImage(destImage, 0,
                        (height - destImage.getHeight(null)) / 2,
                        destImage.getWidth(null), destImage.getHeight(null),
                        Color.white, null);
            else
                graphic.drawImage(destImage,
                        (width - destImage.getWidth(null)) / 2, 0,
                        destImage.getWidth(null), destImage.getHeight(null),
                        Color.white, null);
            graphic.dispose();
            destImage = image;
        }
        return (BufferedImage) destImage;
    }
}
