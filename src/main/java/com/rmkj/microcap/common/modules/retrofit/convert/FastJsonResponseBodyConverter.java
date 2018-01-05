package com.rmkj.microcap.common.modules.retrofit.convert;

import com.alibaba.fastjson.JSON;
import okhttp3.ResponseBody;
import retrofit2.Converter;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * Created by zhangbowen on 2016/5/13.
 */
public class FastJsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Type type;

    public FastJsonResponseBodyConverter(Type type) {
        this.type = type;
    }

    /*
    * 转换方法
    */
    @Override
    public T convert(ResponseBody value) throws IOException {
        if (type == String.class) {
            return (T) value.string();
        }
        byte[] bytes = value.bytes();
        try {
            return JSON.parseObject(bytes, type);
        } catch (Exception e) {
            return (T) bytes;
        }
    }
}
