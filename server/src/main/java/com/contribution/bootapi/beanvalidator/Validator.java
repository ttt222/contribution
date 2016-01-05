package com.contribution.bootapi.beanvalidator;

import org.springframework.stereotype.Component;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.ParameterNameProvider;
import javax.validation.executable.ExecutableValidator;

/**
 * Validator.java
 * Created on  2016-1-5 上午10:05
 * <p/>
 * 版本       修改时间              作者             修改内容
 * V1.0.0     2016-1-5    jiangjianfeng@352.com    初始版本
 */
@Component
public class Validator extends LocalValidatorFactoryBean {
    @Override
    public ExecutableValidator forExecutables() {
        return null;
    }

    @Override
    public ParameterNameProvider getParameterNameProvider() {
        return null;
    }
}
