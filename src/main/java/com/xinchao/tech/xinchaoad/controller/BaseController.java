package com.xinchao.tech.xinchaoad.controller;

import com.xinchao.tech.xinchaoad.common.domain.ResponseObj;
import com.xinchao.tech.xinchaoad.common.exception.BaseException;
import com.xinchao.tech.xinchaoad.common.exception.ResultCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: luhanyu
 * @Date: 2018/7/5 14:24
 * @Description:
 */
@Controller
@RequestMapping(value = "zone")
public class BaseController {
    public ResponseObj success(){
        return new ResponseObj();
    }
    public ResponseObj authFail(){
        return ResponseObj.authFail();
    }
    public ResponseObj setException(BaseException e){
        return  ResponseObj.setException(e);
    }

}
