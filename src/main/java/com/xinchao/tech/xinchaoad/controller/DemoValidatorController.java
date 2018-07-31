package com.xinchao.tech.xinchaoad.controller;

import com.xinchao.tech.xinchaoad.common.domain.ResponseObj;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * @author: luhanyu
 * @Date: 2018/7/11 15:30
 * @Description:
 */
@Controller
@RequestMapping(value = "demo")
public class DemoValidatorController extends BaseController{
    /**
     * 自定义参数校验
     * @param testValidatorVO
     * @return
     */
    @RequestMapping(value = "test", method = POST)
    @ResponseBody
    public ResponseObj test(@RequestBody @Valid TestValidatorVO testValidatorVO){
        return success();
    }
}
