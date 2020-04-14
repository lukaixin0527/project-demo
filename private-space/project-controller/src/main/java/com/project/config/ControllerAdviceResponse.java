package com.project.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.vo.ResultCode;
import com.project.vo.ServerResult;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @ClassName ResponseControllerAdvice
 * @Description todo 全局处理响应数据
 * @Author lu
 * @Date 2020/4/9
 * @Version 1.0
 */
@RestControllerAdvice(basePackages = {"com.project.controller"})
public class ControllerAdviceResponse implements ResponseBodyAdvice<Object> {

    /**
     * @description:todo
     * @author: xiaolu
     * @Date: 2020/4/9
     * @return: 只有返回true, 才会执行beforeBodyWrite方法
     */
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        // 如果返回值已经是ServerResult类型,就不需要再进行封装了
        return !methodParameter.getGenericParameterType().equals(ServerResult.class);
    }

    @Override
    public Object beforeBodyWrite(Object data, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {

        // 判断返回值类型
        if (methodParameter.getGenericParameterType().equals(String.class)) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                return objectMapper.writeValueAsString(ServerResult.isSuccess(data, ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMsg()));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }

        // 其他返回类型封装
        return ServerResult.isSuccess(data, ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMsg());
    }
}
