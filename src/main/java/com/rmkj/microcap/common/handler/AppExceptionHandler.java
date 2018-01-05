package com.rmkj.microcap.common.handler;

import com.alibaba.fastjson.JSON;
import com.rmkj.microcap.common.bean.ResultError;
import com.rmkj.microcap.common.bean.StatusCode;
import org.apache.log4j.Logger;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerMethodExceptionResolver;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;

/**
 */
public class AppExceptionHandler extends AbstractHandlerMethodExceptionResolver {

    private final Logger Log = Logger.getLogger(AppExceptionHandler.class);
    private String defaultErrorView;
    private List<HttpMessageConverter<?>> messageConverters;

    public List<HttpMessageConverter<?>> getMessageConverters() {
        return messageConverters;
    }

    public void setMessageConverters(List<HttpMessageConverter<?>> messageConverters) {
        this.messageConverters = messageConverters;
    }

    public String getDefaultErrorView() {
        return defaultErrorView;
    }

    public void setDefaultErrorView(String defaultErrorView) {
        this.defaultErrorView = defaultErrorView;
    }

    @Override
    protected ModelAndView doResolveHandlerMethodException(HttpServletRequest request, HttpServletResponse response, HandlerMethod handlerMethod, Exception exception) {
        Log.error("", exception);
        if (handlerMethod == null) {
            return null;
        }
        Method method = handlerMethod.getMethod();
        if (method == null) {
            return null;
        }
        ModelAndView returnValue = new ModelAndView();
        Class clazz = handlerMethod.getBean().getClass();
        ResponseBody responseBodyAnn = AnnotationUtils.findAnnotation(method, ResponseBody.class);
        RestController restControllerAnn = AnnotationUtils.findAnnotation(clazz, RestController.class);
        if (responseBodyAnn != null || restControllerAnn != null) {
            try {
                return handleResponseBody(request, response, exception);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (returnValue.getViewName() == null) {
            returnValue.addObject("error", StatusCode.SERVER_ERROR.getDescription());
            returnValue.setViewName(defaultErrorView);
        }
        return returnValue;
    }

    /**
     * 通过messageConvert处理responseBody
     *
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    private ModelAndView handleResponseBody(HttpServletRequest request, HttpServletResponse response, Exception exception) throws ServletException, IOException {

        //exception.printStackTrace();
        Log.error(exception);
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        HttpInputMessage inputMessage = new ServletServerHttpRequest(request);
        List<MediaType> acceptedMediaTypes = inputMessage.getHeaders().getAccept();
        if (acceptedMediaTypes.isEmpty()) {
            acceptedMediaTypes = Collections.singletonList(MediaType.ALL);
        }
        MediaType.sortByQualityValue(acceptedMediaTypes);
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        String responseValue = JSON.toJSONString(new ResultError("服务器正忙，请稍后再试！"));
        if (messageConverters != null) {
            for (MediaType acceptedMediaType : acceptedMediaTypes) {
                for (HttpMessageConverter messageConverter : messageConverters) {
                    if (messageConverter.canWrite(responseValue.getClass(), acceptedMediaType)) {
                        messageConverter.write(responseValue, acceptedMediaType, outputMessage);
                        return new ModelAndView();
                    }
                }
            }
        }
        return null;
    }
}
