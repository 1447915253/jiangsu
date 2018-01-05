package com.rmkj.microcap.common.interceptor;

import com.alibaba.fastjson.JSON;
import com.rmkj.microcap.common.bean.annotation.WeiXinLogin;
import com.rmkj.microcap.common.constants.Constants;
import com.rmkj.microcap.common.constants.ProjectConstants;
import com.rmkj.microcap.common.modules.cache.CacheFacade;
import com.rmkj.microcap.common.modules.sys.service.TokenService;
import com.rmkj.microcap.common.bean.AuthEntity;
import com.rmkj.microcap.common.bean.annotation.LoginAnnot;
import com.rmkj.microcap.common.utils.AuthContext;
import com.rmkj.microcap.common.utils.Utils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * 登录权限验证
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {
    /**
     * 日志对象
     */
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            // 验证请求头中的用户id和token是否匹配
            boolean success = TokenService.checkToken();

            // 是否需要登录
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();

            LoginAnnot annotation = method.getAnnotation(LoginAnnot.class);
            //
            String loginType = annotation == null ? "" : annotation.value();

            String regUrl = Constants.LoginType.AGENT.equals(loginType) ?
                    ProjectConstants.WAP.concat(ProjectConstants.WAP_AGENT).concat("/reg") : ProjectConstants.WAP.concat("/reg");
            String homeUrl = Utils.formatStr(ProjectConstants.HOME_URL, request.getServerName());
            String loginUrl = Utils.formatStr(ProjectConstants.LOGIN_URL, request.getServerName());;

            // 访问注册或者登录页面时，已经登录的话，跳转到首页
            if(success && (request.getRequestURI().startsWith(request.getContextPath().concat(regUrl))
                    || request.getRequestURI().startsWith(loginUrl))){
                response.sendRedirect(homeUrl);
                return false;
            }

            // 如果请求的接口需要验证token
            if (annotation != null) {
                if(ContextInterceptor.isWap(request)){
                    if (!success) {
                        if(ProjectConstants.WEI_XIN_LOGIN){
                            // 返回首页，走微信网页授权
                            response.sendRedirect(homeUrl);
                            return false;
                        }else {
                            response.sendRedirect(loginUrl);
                            return false;
                        }
                    }
                }else {
                    //如果上面验证的结果为错误
                    if (!success) {
                        //处理验证结果，返回没有权限
                        response.setStatus(HttpStatus.UNAUTHORIZED.value());
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
