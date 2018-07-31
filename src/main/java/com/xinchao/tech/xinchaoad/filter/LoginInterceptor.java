package com.xinchao.tech.xinchaoad.filter;

import com.xinchao.tech.xinchaoad.AuthException;
import com.xinchao.tech.xinchaoad.common.exception.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Service
public class LoginInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 包含字段过滤拦截
        String[] passUrl = {"login", "demo", "swagger", "api-docs", "configuration", "png", "SMS"};
        String uri = request.getRequestURI();
        for (String str : passUrl) {
            if (uri.contains(str)) {

                return true;
            }
        }

        String token = request.getHeader("token");
        if (StringUtils.isBlank(token)) {
            log.info("uri : " + uri);
            throw new AuthException(ResultCode.FAIL_AUTH_FAIL.getCode(), "token为空");
        }
        if (request.getSession().getAttribute("userInfo") == null) {
            throw new AuthException(ResultCode.FAIL_AUTH_FAIL.getCode(), "身份信息失效,请重新登录");
        }
        return true;
    }
}
