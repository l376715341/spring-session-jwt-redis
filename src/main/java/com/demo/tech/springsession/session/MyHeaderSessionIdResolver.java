package com.demo.tech.springsession.session;

import com.demo.tech.springsession.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.session.web.http.HeaderHttpSessionIdResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author: luhanyu
 *  HeaderHttpSessionIdResolver  重写 getSessionID   resolveSessionIds方法
 * @Date: 2018/7/4 17:18
 * @Description:
 */
@Slf4j
public class MyHeaderSessionIdResolver extends HeaderHttpSessionIdResolver {

    @Value("${session.headerName}")
    private String  headerName;
    @Value("${session.tokenName}")
    private String tokenName;


    public MyHeaderSessionIdResolver(String headerName) {
        super(headerName);
    }

    /**
     * 重写 resolveSessionIds 根据token 获取 session内容
     * @param request
     * @return
     */
    @Override
    public List<String> resolveSessionIds(HttpServletRequest request) {
        String token  = request.getHeader(headerName);
        try {

             if(token ==null||token.equals("")){
              token=null;
            }else{
                Map<String, Object> map = JwtUtil.validateToken(token);
                token = (String) map.get(tokenName);
                long exptime = (int)map.get("exp")*1000L;
                long nowTime =System.currentTimeMillis();
                if(nowTime>exptime) {
                    return Collections.emptyList();
                }
            }

            return token != null ? Collections.singletonList(token) : Collections.emptyList();
        }catch (Exception e){
            log.error("session jwt 异常"+e.getMessage());
            return Collections.emptyList();
        }

    }

    @Override
    public void expireSession(HttpServletRequest request, HttpServletResponse response) {
        super.expireSession(request, response);
    }
}
