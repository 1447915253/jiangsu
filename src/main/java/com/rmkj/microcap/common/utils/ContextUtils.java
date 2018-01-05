package com.rmkj.microcap.common.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by zhangbowen on 2016/1/21.
 */
public class ContextUtils {
    private static ThreadLocal<HttpServletRequest> httpServletRequestThreadLocal = new ThreadLocal<>();
    private static ThreadLocal<HttpServletResponse> httpServletResponseThreadLocal = new ThreadLocal<>();

    public static HttpServletResponse getResponse() {
        return httpServletResponseThreadLocal.get();
    }

    public static HttpServletRequest getRequest() {
        return httpServletRequestThreadLocal.get();
    }

    public static void removeReqRes() {
        httpServletRequestThreadLocal.remove();
        httpServletResponseThreadLocal.remove();
    }


    public static void setReqRes(HttpServletRequest request, HttpServletResponse response) {
        httpServletRequestThreadLocal.set(request);
        httpServletResponseThreadLocal.set(response);
    }
}
