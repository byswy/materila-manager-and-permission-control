package com.btp.rwj.interceptor;

import com.alibaba.fastjson.JSON;
import com.btp.rwj.annotation.RequireLogin;
import com.btp.rwj.annotation.RequirePermission;
import com.btp.rwj.domain.VO.ApiResult;
import com.btp.rwj.utils.CommonUtil;
import com.btp.rwj.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Stream;

public class UserInterceptor implements HandlerInterceptor {

    @Override
    //在业务处理器处理请求之前调用
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        if (method.isAnnotationPresent(RequireLogin.class) || method.isAnnotationPresent(RequirePermission.class)) {
            //含有RequireLogin或RequirePermission注解
            if (token == null || token.isEmpty()) {
                responseJson(response, ApiResult.fail(2, "无令牌或令牌为空"));
                return false;
            }
            ApiResult result = JwtUtil.verifyToken(token);
            if (method.isAnnotationPresent(RequireLogin.class)) {
                //含有RequireLogin注解
                if (result.getErrcode() == 2) {
                    responseJson(response, result);
                    return false;
                }
            }
            if (method.isAnnotationPresent(RequirePermission.class)) {
                //含有RequirePermission注解
                String[] requrePermissions = method.getAnnotation(RequirePermission.class).value();
                Claims claims = (Claims) result.getData();
                Object permissions = claims.get("permissions");
                List<String> hasPermission = CommonUtil.Object2list(permissions, String.class);
                if (hasPermission.isEmpty()) {
                    responseJson(response, ApiResult.fail(2, "无权限"));
                    return false;
                }
                if (Stream.of(requrePermissions).noneMatch(hasPermission::contains)) {
                    responseJson(response, ApiResult.fail(2, "权限不足"));
                    return false;
                }
            }
        }
        return true;
    }


    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }

    private static void responseJson(HttpServletResponse response, ApiResult result) throws IOException {
        String jsonString = JSON.toJSONString(result);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        response.getOutputStream().write(jsonString.getBytes(StandardCharsets.UTF_8));
    }
}
