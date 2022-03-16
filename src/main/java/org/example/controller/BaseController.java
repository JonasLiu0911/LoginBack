package org.example.controller;

import org.example.error.BusinessException;
import org.example.error.EmBusinessError;
import org.example.response.CommonReturnType;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class BaseController {

    public static final String CONTENT_TYPE_FORMED = "application/x-www-form-urlencoded";

    //定义exceptionhandler解决未被controller层吸收的exception
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Object handlerException(HttpServletRequest request, Exception exception) {

        if (exception instanceof BusinessException) {
            BusinessException businessException = (BusinessException) exception;
            return CommonReturnType.create(businessException.getErrCode(), businessException.getErrMsg());
        } else {
            return CommonReturnType.create(EmBusinessError.UNKNOWN_ERROR.getErrCode(), EmBusinessError.UNKNOWN_ERROR.getErrMsg());
        }

    }
}
