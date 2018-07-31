package com.xinchao.tech.xinchaoad.config;

import com.xinchao.tech.xinchaoad.AuthException;
import com.xinchao.tech.xinchaoad.common.api.auth.AuthService;
import com.xinchao.tech.xinchaoad.common.domain.ResponseObj;
import com.xinchao.tech.xinchaoad.common.exception.BaseException;
import com.xinchao.tech.xinchaoad.common.exception.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionAdvice {

    @ExceptionHandler(BaseException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseObj defineException(BaseException ex) {
        log.info("define exception {} {}:",ex.getCode(),ex.getMessage());
        ResponseObj response = ResponseObj.setException(ex);
        return response;
    }
    /**
     * 权限异常
     */

    @ExceptionHandler(AuthException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseObj authException(AuthException ex) {
        log.info("define exception {} {}:",ex.getCode(),ex.getMessage());
        ResponseObj response = ResponseObj.setException(new BaseException(ResultCode.FAIL_AUTH_FAIL))   ;
        return response;
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseObj bindException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();

        StringBuffer errorMesssage = new StringBuffer();

        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            errorMesssage .append(fieldError.getDefaultMessage() + ", ");
        }
        ResponseObj response = ResponseObj.setResultCode(ResultCode.FAIL_ILLEGAL_ARGUMENT);
        response.getStatus().setMessage(errorMesssage.toString());

        return response;
    }
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseObj exception(Exception ex) {
        log.error("", ex);
        ResponseObj response =ResponseObj.setResultCode(ResultCode.FAIL_UNKNOWN);
       return response;
    }

}