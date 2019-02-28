package com.demo.tech.springsession.controller;

import com.demo.tech.springsession.util.JwtUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * @author: luhanyu
 * @Date: 2018/7/11 15:30
 * @Description:
 */
@Controller
@RequestMapping(value = "demo")
public class DemoController {
    /**
     * 测试 获取token
     * @param
     * @return
     */
    @RequestMapping(value = "/getToken", method = GET)
    @ResponseBody
    public String getToken(HttpServletRequest request){
        request.getSession().setAttribute("test","this is test msg");
        return JwtUtil.generateToken(request.getSession().getId());
    }
    /**
     * 测试 获取token
     * @param
     * @return
     */
    @RequestMapping(value = "/getAttrbute", method = GET)
    @ResponseBody
    public Object getAttrbute(HttpServletRequest request){
        return request.getSession().getAttribute("test");
    }
}
