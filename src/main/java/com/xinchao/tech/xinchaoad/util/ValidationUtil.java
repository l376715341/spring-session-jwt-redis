package com.xinchao.tech.xinchaoad.util;

import com.xinchao.tech.xinchaoad.common.exception.BaseException;
import com.xinchao.tech.xinchaoad.common.exception.ResultCode;
import org.aspectj.lang.JoinPoint;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

/**
 * @author: luhanyu
 * @Date: 2018/7/11 11:57
 * @Description:
 */
public class ValidationUtil {

    public static Boolean check( BindingResult bindingResult) throws BaseException {
        //检查请求bindingResult是否有异常
        if (bindingResult.hasErrors()) {
            StringBuffer errMsgs =new StringBuffer();
            for(ObjectError error : bindingResult.getAllErrors()){
                errMsgs.append(error.getDefaultMessage()+"\n");
            }
            BaseException baseException =  new BaseException(ResultCode.FAIL_DEPENDENCY_CHECK.getCode(),errMsgs.toString());
            //向外抛出异常,由异常处理器处理
            throw baseException;
        }else{
            return true;
        }
    }
}
