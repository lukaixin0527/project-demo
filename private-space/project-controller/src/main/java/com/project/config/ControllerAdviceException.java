package com.project.config;

import com.project.vo.ResultCode;
import com.project.vo.ServerResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @ClassName ExceptionControllerAdvice
 * @Description todo 捕获Controller全局异常
 * @Author lu
 * @Date 2020/4/9
 * @Version 1.0
 */
@RestControllerAdvice
public class ControllerAdviceException {

    /**
     * @description:todo 校验入参失败异常
     * @author: xiaolu
     * @Date: 2020/4/9
     * @return:
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ServerResult methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        ObjectError objectError = e.getBindingResult().getAllErrors().get(0);
        return ServerResult.isFailure(ResultCode.VALIDATE_PARAMETER_FAILED.getCode(), objectError.getDefaultMessage());
    }
}
