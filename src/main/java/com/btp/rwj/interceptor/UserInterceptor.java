package com.btp.rwj.interceptor;

import com.alibaba.fastjson.JSON;
import com.btp.rwj.annotation.RequireLogin;
import com.btp.rwj.annotation.RequirePermission;
import com.btp.rwj.utils.CommonUtil;
import com.btp.rwj.utils.JwtUtil;
import com.btp.rwj.vo.ApiResult;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class UserInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        String url = request.getRequestURI().substring(request.getContextPath().length());
//        System.out.println(url);
        // 登录和注册等请求不需要令牌
//        if (url.equals("/login")) {
//            return true;
//        }
        String token = request.getHeader("Authorization");
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        if (method.isAnnotationPresent(RequireLogin.class) || method.isAnnotationPresent(RequirePermission.class)) {
            if (token == null) {
                String jsonString = JSON.toJSONString(ApiResult.fail(2, "无令牌"));
                response.getOutputStream().write(jsonString.getBytes(StandardCharsets.UTF_8));
                return false;
            } else {
                token = token.substring(1, token.length() - 1);
                if (!JwtUtil.verifyToken(token)) {
                    String jsonString = JSON.toJSONString(ApiResult.fail(2, "令牌无效"));
                    response.getOutputStream().write(jsonString.getBytes(StandardCharsets.UTF_8));
                    return false;
                }
                if (!JwtUtil.verifyTokenDeadline(token)) {
                    String jsonString = JSON.toJSONString(ApiResult.fail(2, "令牌过期"));
                    response.getOutputStream().write(jsonString.getBytes(StandardCharsets.UTF_8));
                    return false;
                }
            }
        }
        if (method.isAnnotationPresent(RequirePermission.class)) {
            //从token中取permission与需要的权限比较
            String[] requrePermission = method.getAnnotation(RequirePermission.class).value();
            String[] hasPermission = CommonUtil.String2array(JwtUtil.getPermission(token));
            int count = 0;
            for (String permission : hasPermission) {
                if (Arrays.asList(requrePermission).contains(permission)) {
                    count++;
                }
            }
            if (count == requrePermission.length)
                return true;
            else {
                String jsonString = JSON.toJSONString(ApiResult.fail("没有权限或权限不足"));
                response.getOutputStream().write(jsonString.getBytes(StandardCharsets.UTF_8));
                return false;
            }
        }
        return true;
    }
}
