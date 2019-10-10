package com.ly.mt.home.base.handler;

import com.ly.mt.core.base.entity.ResponseCode;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.home.base.exception.MTException;
import io.jsonwebtoken.JwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
public class GlobalExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 全局异常
     *
     * @param ex
     * @param response
     * @throws IOException
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public void processException(Exception ex, HttpServletResponse response) throws IOException {
        logger.error("error:",ex);
        response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
    }

    /**
     * 参数异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public @ResponseBody
    Object processIllegalArgumentException(IllegalArgumentException ex) {
        logger.error("error:",ex);
        return ResponseUtil.getResponseMsg(ResponseCode.RESPONSE_CODE_ERROR, ex.getMessage());
    }

    /**
     * 未登录
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(JwtException.class)
    public @ResponseBody
    Object processJwtException(JwtException ex) {
        logger.error("error:",ex);
        return ResponseUtil.getResponseCode(ResponseCode.RESPONSE_CODE_NOT_LOGIN);
    }

    /**
     * 业务异常
     * @param ex
     * @return
     */
    @ExceptionHandler
    public @ResponseBody
    Object processMTException(MTException ex) {
        logger.error("error:",ex);
        ResponseJson responseJson = new ResponseJson();
        responseJson.setCode(ex.getCode());
        responseJson.setMsg(ex.getMessage());
        return responseJson;
    }

}