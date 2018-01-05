package com.rmkj.microcap.common.interceptor;

import com.alibaba.fastjson.JSON;
import com.rmkj.microcap.common.bean.AuthEntity;
import com.rmkj.microcap.common.bean.annotation.LoginAnnot;
import com.rmkj.microcap.common.bean.annotation.WeiXinLogin;
import com.rmkj.microcap.common.constants.Constants;
import com.rmkj.microcap.common.constants.ProjectConstants;
import com.rmkj.microcap.common.modules.cache.CacheFacade;
import com.rmkj.microcap.common.modules.sys.service.TokenService;
import com.rmkj.microcap.common.modules.weixin.http.interceptor.WeiXinInterceptor;
import com.rmkj.microcap.common.utils.AuthContext;
import com.rmkj.microcap.common.utils.ContextUtils;
import com.rmkj.microcap.common.utils.Utils;
import com.rmkj.microcap.modules.index.service.ScheduleService;
import com.rmkj.microcap.modules.user.entity.User;
import com.rmkj.microcap.modules.user.service.UserService;
import com.rmkj.microcap.modules.weChatPublic.entity.WeChatPublic;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * 处理登录信息
 * 处理controller的方法上的注解标识
 */
public class ContextInterceptor extends HandlerInterceptorAdapter {

    private static Logger logger = Logger.getLogger(ContextInterceptor.class);

    @Autowired
    private UserService userService;

    @Autowired
    private ScheduleService scheduleService;

    /**
     *
     * @param request
     * @return
     */
    public static boolean isWeiXin(HttpServletRequest request){
        String ua = request.getHeader("user-agent").toLowerCase();
        if (ua.indexOf("micromessenger") > 0) {// 是微信浏览器
            return true;
        }
        return false;
    }

    static boolean isWap(HttpServletRequest request){
        // 微信wap端 从cookie中取
        // 规则：wap端的页面请求和来自wap端页面的ajax请求（通过referer判断）
        String referer = request.getHeader("Referer") == null ? "" : request.getHeader("Referer");
        if(!referer.equals("") && referer.indexOf("/", referer.indexOf("//")+2) != -1){
            referer = referer.substring(referer.indexOf("/", referer.indexOf("//")+2));
        }
        return request.getRequestURI().startsWith(request.getContextPath().concat(ProjectConstants.WAP))
                || referer.startsWith(request.getContextPath().concat(ProjectConstants.WAP));
    }

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        ContextUtils.setReqRes(request, response);
        WeiXinInterceptor.clearAppId();

        if (handler instanceof HandlerMethod) {
            // 禁止缓存
            response.setHeader("Cache-Control", "no-cache");
            response.setHeader("Pragma", "no-cache");

            //
            WeChatPublic weChatPublic = scheduleService.getWeChatPublic(request.getServerName());

            if(weChatPublic != null){
                WeiXinInterceptor.setAppId(weChatPublic.getAppId());
            }

//            logger.info("handler " + request.getRequestURI());
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            LoginAnnot annotation = method.getAnnotation(LoginAnnot.class);
            //
            String loginType = annotation == null ? "" : annotation.value();

            /********************** 抓取session  ***************************/
            // 从header中取
            String headerName = Constants.LoginType.AGENT.equals(loginType) ? Constants.Http.AUTHORIZATION_AGENT : Constants.Http.AUTHORIZATION;
            String authStr = request.getHeader(headerName);

            if(isWap(request)){
                authStr = null;
                Cookie[] cookies = request.getCookies();
                if(cookies != null){
                    for(Cookie cookie : cookies){
                        if(headerName.equals(cookie.getName())){
                            authStr = URLDecoder.decode(cookie.getValue(), "utf-8");
                            break;
                        }
                    }
                }
            }

            AuthEntity authEntity = null;
            if (authStr != null) {
                // 设置当前请求者的权限标识
                try {
                    authEntity = JSON.parseObject(authStr, AuthEntity.class);
                    authEntity.setIp(Utils.getClientIp(request));
                    AuthContext.setCurAuth(authEntity);
                } catch (Exception e) {

                }
            } else {
                authEntity = new AuthEntity();
                authEntity.setIp(Utils.getClientIp(request));
                AuthContext.setCurAuth(authEntity);
            }
            /********************** 抓取session  ***************************/

            boolean success = TokenService.checkToken();

            // 不是微信访问时，
            if(ProjectConstants.ONLY_WEIXIN_LOGIN && isWap(request) && !isWeiXin(request)){
                if(request.getRequestURL().toString().endsWith(request.getContextPath().concat(ProjectConstants.WAP.concat("/notweixin")))){

                }else if(request.getRequestURL().toString().endsWith(request.getContextPath().concat(ProjectConstants.V1.concat("/validate")))){

                }else if(request.getRequestURL().toString().endsWith(request.getContextPath().concat(ProjectConstants.WAP.concat("/login")))
                        && (Constants.Terminal.TERMINAL_IOS.equals(request.getParameter("terminal"))
                        || Constants.Terminal.TERMINAL_ANDROID.equals(request.getParameter("terminal")) || Constants.Terminal.TERMINAL_WEB.equals(request.getParameter("terminal")))){

                }else if(success && (AuthContext.getCurAuth().getTerminal().equals(Constants.Terminal.TERMINAL_IOS)
                        || AuthContext.getCurAuth().getTerminal().equals(Constants.Terminal.TERMINAL_ANDROID) || AuthContext.getCurAuth().getTerminal().equals(Constants.Terminal.TERMINAL_WEB)) ){

                }else{
//                    toWeiXinLogin(response, weChatPublic);
                    response.sendRedirect(request.getContextPath().concat(ProjectConstants.WAP.concat("/notweixin")));
                    return false;
                }
//
            }

            // 处理微信第三方登录
            // 获取openid并登录（注册）
            WeiXinLogin weiXinLogin = method.getAnnotation(WeiXinLogin.class);

            if (ProjectConstants.WEI_XIN_LOGIN && weiXinLogin != null
                    && (!success || (success && Constants.Terminal.TERMINAL_WAP.equals(AuthContext.getCurAuth().getTerminal())))) {
                String code = request.getParameter("code");
                logger.info("weiXinLogin code=" + code);
                String id = request.getParameter("tokenId");
                if(StringUtils.isNotBlank(id)){
                    if("renrenbao".equals(id)){

                    }else{
                        String value = CacheFacade.getObject(id);
                        if(!"0".equals(value)){
                            response.sendRedirect(request.getContextPath().concat(ProjectConstants.WAP).concat("/error/404"));
                            return false;
                        }
                    }
                }else{
                    if(!success){
                        response.sendRedirect(request.getContextPath().concat(ProjectConstants.WAP).concat("/error/404"));
                        return false;
                    }
                }
                if (code != null && !"".equals(code)) {
                    if(success){
                        userService.bindWeiXin(code);
                    }else{
                        // 重新认证
                        authEntity = new AuthEntity();
                        authEntity.setIp(Utils.getClientIp(request));
                        AuthContext.setCurAuth(authEntity);

                        authEntity.setTerminal(Constants.Terminal.TERMINAL_WAP);

                        Object obj = null;
                        if(Constants.LoginType.AGENT.equals(loginType)){

                        }else{
                            obj = userService.loginWeiXin(code);
                        }
                        if(obj != null){
                            boolean isReg = false;
                            String token = null;

                            if(Constants.LoginType.AGENT.equals(loginType)){

                            }else{
                                User user = (User) obj;
                                if(Constants.USER_STATUE.INVALID.equals(user.getStatus().toString())){
                                    response.sendRedirect(request.getContextPath().concat(ProjectConstants.WAP.concat("/invalid")));
                                    return false;
                                }else{
                                    // 自动登录
                                    authEntity.setUserId(user.getId());
                                    authEntity.setOpenId(user.getOpenId());

                                    isReg = user.getMobile() != null && !"".equals(user.getMobile().trim());
                                    token = user.getToken();
                                }
                            }

                            AuthEntity entity = new AuthEntity();
                            // 平台已注册
                            if(isReg){
                                authEntity.setToken(token);
                                entity.setToken(token);
                            }
                            // 加入cookie
                            entity.setUserId(authEntity.getUserId());
                            entity.setTerminal(authEntity.getTerminal());
                            Cookie cookie = new Cookie(Constants.LoginType.AGENT.equals(loginType)
                                    ? Constants.Http.AUTHORIZATION_AGENT : Constants.Http.AUTHORIZATION, URLEncoder.encode(JSON.toJSONString(entity), "utf-8"));
                            cookie.setPath(request.getContextPath().concat("/"));
                            response.addCookie(cookie);

                            // 平台未注册
                            if(!isReg){
                                response.sendRedirect(request.getContextPath().concat(ProjectConstants.WAP).concat("/home/nologin?tokenId=").concat(id));
                                return false;
                            }else {
                                userService.updateLogin(Utils.getClientIp(request));
                            }
                        }else{
                            response.sendRedirect(Utils.formatStr(ProjectConstants.LOGIN_URL, request.getServerName()));
                            return false;
                        }
                    }
                }else{
                    // 没有code时，去微信取code
                    if(ProjectConstants.PRO_DEBUG){
                        if(!success){
                            // 去登陆页面登录
                            response.sendRedirect(request.getContextPath().concat(ProjectConstants.WAP.concat("/login")));
                            return false;
                        }
                    }else {
                        // 去微信网页授权，获取用户信息
                        if(!success || weiXinLogin.bind()){
                            toWeiXinLogin(response, weChatPublic,id);
                            return false;
                        }
                    }
                }
            }
        }

        return true;
    }

    private void toWeiXinLogin(HttpServletResponse response, WeChatPublic weChatPublic,String id) throws IOException {
        String homeUrl = null;
        if(StringUtils.isEmpty(id)){
            homeUrl = URLEncoder.encode(Utils.formatStr(ProjectConstants.HOME_URL, weChatPublic.getDomainName()), "utf-8");
        }else{
            homeUrl = URLEncoder.encode(Utils.formatStr(ProjectConstants.HOME_URL.concat("?tokenId=").concat(id), weChatPublic.getDomainName()), "utf-8");
        }
        response.sendRedirect(Utils.formatStr(ProjectConstants.WEI_XIN_PAGE_ACCESS_URL, weChatPublic.getAppId(),homeUrl));
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        ContextUtils.removeReqRes();
        //删除当前请求者的权限标识
        AuthContext.removeCurAuth();
    }
}
