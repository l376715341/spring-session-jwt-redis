package com.xinchao.tech.xinchaoad;

import com.xinchao.tech.xinchaoad.common.exception.BaseException;
import com.xinchao.tech.xinchaoad.common.exception.ResultCode;
import lombok.Getter;
import lombok.Setter;

/**
 * @author: luhanyu
 * @Date: 2018/7/13 9:07
 * @Description:
 */
public class AuthException extends RuntimeException {
    @Getter
    @Setter
    int                 code;

    public AuthException(int code,String msg){
        super(msg);
        this.code=code;
    }

    public AuthException(int code,String msg,Exception ex){
        super(msg,ex);
        this.code=code;
    }

    public AuthException(ResultCode resultCode){
        super(resultCode.getDesc());
        this.code=resultCode.getCode();
    }

}
