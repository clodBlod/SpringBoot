package com.kuang.config;

import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;
import org.thymeleaf.util.StringUtils;

public class MyLocaleResolver implements LocaleResolver {

    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        // 获取请求的语言参数
        String language = request.getParameter("l");
        System.out.println(language);
        // 如果没有就使用默认
        Locale locale = Locale.getDefault();
        // 如果请求的连接携带了国际化的参数
        if(!StringUtils.isEmpty(language)){
            String[] s = language.split("_");
            locale = new Locale(s[0], s[1]);

        }
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {

    }
}
