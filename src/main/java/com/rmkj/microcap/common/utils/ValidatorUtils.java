package com.rmkj.microcap.common.utils;

import com.rmkj.microcap.common.handler.SpringContextHolder;
import org.apache.log4j.Logger;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;

import java.util.List;

/**
 * Created by 123 on 2016/10/28.
 */
public class ValidatorUtils {

    private static final Logger Log = Logger.getLogger(ValidatorUtils.class);

    private static Validator validator;

    static {
        validator = SpringContextHolder.getBean(Validator.class);
    }

    /**
     *
     * @param bean
     * @return
     */
    public static boolean valid(Object bean){
        DataBinder dataBinder = new DataBinder(bean);
        dataBinder.setValidator(validator);
        dataBinder.validate();
        BindingResult bindingResult = dataBinder.getBindingResult();
        if(bindingResult.hasErrors()){
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            Log.error(allErrors.get(0).getObjectName());
            return false;
        }
        return true;
    }

}
