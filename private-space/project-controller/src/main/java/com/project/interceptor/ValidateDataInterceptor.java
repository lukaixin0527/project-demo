package com.project.interceptor;

import cn.hutool.json.JSONObject;
import com.project.consts.ConfigConsts;
import com.project.vo.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.DigestUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @ClassName ValidateDataInterceptor
 * @Description todo 数据校验
 * @Author lu
 * @Date 2020/4/13
 * @Version 1.0
 */
@Configuration
public class ValidateDataInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(ValidateDataInterceptor.class);

    /**
     * @description:todo api接口签名验证，校验数据的安全一致性
     * 1、分配对应的key、secret
     * 2、前台将参数按照指定格式加密，传输未加密参数、key、加密后的密文
     * 必需参数 key、sign、timestamp(yyyyMMddHHmmss)
     * 加密格式：secret(秘钥前置) + parameter1=1&parameter2=2&parameter3=3  parameter参数集合字母正序
     * 在线加密网站：https://md5jiami.51240.com/
     * 3、后台根据key，使用对应的secret加密对比
     * @author: xiaolu
     * @Date: 2020/4/13
     * @return: boolean
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String key = request.getParameter(ConfigConsts.REQUEST_PARAMETER_KEY);
        String sign = request.getParameter(ConfigConsts.REQUEST_PARAMETER_SIGN);
        String timestamp = request.getParameter(ConfigConsts.REQUEST_PARAMETER_TIMESTAMP);


        if (key.isEmpty() || sign.isEmpty() || timestamp.isEmpty()) {
            logger.error("【project-controller_intercept_ValidateData】Validate parameter is empty,key:{},sign:{},timestamp:{}", key, sign, timestamp);
            response403(response);
            return false;
        }

        // 签名是否过期(1分钟)
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        Date timestampDate = simpleDateFormat.parse(timestamp);
        if (timestampDate.getTime() <= System.currentTimeMillis() - 60 * 1000) {
            logger.error("【project-controller_intercept_ValidateData】Validate timestamp expired,simpleDateFormat:{}", timestampDate);
            response403(response);
            return false;
        }

        // 获取所有请求参数key
        List<String> keys = new ArrayList<String>(request.getParameterMap().keySet());
        if (keys.isEmpty() || keys.size() <= 3) {
            logger.error("【project-controller_intercept_ValidateData】Validate parameter is empty");
            response403(response);
            return false;
        }
        // 排序sign参数
        keys.remove(ConfigConsts.REQUEST_PARAMETER_SIGN);
        // 排序
        Collections.sort(keys);
        StringBuilder sb = new StringBuilder();
        for (String keyStr : keys) {
            sb.append(keyStr).append("=").append(request.getParameter(keyStr)).append("&");
        }
        String linkString = sb.toString();
        // 去除最后一个&
        linkString = StringUtils.substring(linkString, 0, linkString.length() - 1);
        // todo 秘钥
        String secret = "secret";
        // MD5加密
        StringBuilder digestAsHex = DigestUtils.appendMd5DigestAsHex((secret + linkString).getBytes(), new StringBuilder());
        if (!StringUtils.equals(sign, digestAsHex)) {
            logger.error("【project-controller_intercept_ValidateData】Validate fail");
        }
        if (!StringUtils.equals(sign, digestAsHex)) {
            response403(response);
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion");
    }

    /**
     * @description:todo 验证失败, 自定义返回值
     * @author: xiaolu
     * @Date: 2020/4/14
     * @return:
     */
    private void response403(HttpServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = null;
        JSONObject res = new JSONObject();
        res.put("code", ResultCode.VALIDATE_DATA_FAILED.getCode());
        res.put("msg", ResultCode.VALIDATE_DATA_FAILED.getMsg());
        try {
            out = response.getWriter();
            out.append(res.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
