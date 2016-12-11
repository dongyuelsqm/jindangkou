package com.kingdangkou.weixin.weixiaodan.validator;


import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by dongy on 2016-12-06.
 */
@Component
public class ShopingCartValidator implements Validator {
    public boolean supports(Class<?> aClass) {
        return false;
    }

    @Override
    public void validate(Object obj, Errors errors) {
        String param = (String) obj;
        boolean matches = param.matches("[a-zA-Z0-9]+");
        if (matches){
            errors.reject("param", "contains invalid chars");
        }
    }
}
