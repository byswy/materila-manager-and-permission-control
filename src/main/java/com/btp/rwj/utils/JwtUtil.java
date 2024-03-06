package com.btp.rwj.utils;

import com.btp.rwj.domain.VO.ApiResult;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class JwtUtil {
    private static String tokenKey;
    private static long expire;

    //Value不能注入static及final，不能在非注册类中使用
    @Value("${static.jwt.tokenKey}")
    private void setTokenKey(String tokenKey) {
        JwtUtil.tokenKey = tokenKey;
    }

    @Value("${static.jwt.expire}")
    private void setExpire(long expire) {
        JwtUtil.expire = expire;
    }

    public static String getToken(String username) {
        long currentTime = System.currentTimeMillis();
        long expireTime = currentTime + expire;
        Date expireDate = new Date(expireTime);
        Map<String, Object> map = new HashMap<>();
        map.put("username", username);
        return Jwts.builder()
                .setClaims(map)
//                .setIssuer("")
                .setIssuedAt(new Date(currentTime))
                .signWith(SignatureAlgorithm.HS256, tokenKey)
                .setExpiration(expireDate)
                .compact();
    }

    public static String getToken(String username, List<String> permissions) {
        long currentTime = System.currentTimeMillis();
        long expireTime = currentTime + expire;
        Date expireDate = new Date(expireTime);
        Map<String, Object> map = new HashMap<>();
        map.put("username", username);
        map.put("permissions", permissions);
        return Jwts.builder()
                .setClaims(map)
//                .setIssuer("")
                .setIssuedAt(new Date(currentTime))
                .signWith(SignatureAlgorithm.HS256, tokenKey)
                .setExpiration(expireDate)
                .compact();
    }

    public static String getToken(String username, List<String> roles, List<String> permissions) {
        long currentTime = System.currentTimeMillis();
        long expireTime = currentTime + expire;
        Date expireDate = new Date(expireTime);
        Map<String, Object> map = new HashMap<>();
        map.put("username", username);
        map.put("permissions", permissions);
        map.put("roles", roles);
        return Jwts.builder()
                .setClaims(map)
//                .setIssuer("")
                .setIssuedAt(new Date(currentTime))
                .signWith(SignatureAlgorithm.HS256, tokenKey)
                .setExpiration(expireDate)
                .compact();
    }

    public static ApiResult verifyToken(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(tokenKey)
                    .parseClaimsJwt(token)
                    .getBody();
            return ApiResult.success(claims);
        } catch (ExpiredJwtException e) {
            return ApiResult.fail(2, "令牌过期");
        } catch (UnsupportedJwtException e) {
            return ApiResult.fail(2, "令牌不支持");
        } catch (MalformedJwtException e) {
            return ApiResult.fail(2, "令牌格式错误");
        } catch (SignatureException e) {
            return ApiResult.fail(2, "令牌签名错误");
        } catch (Exception e) {
            return ApiResult.fail(2, "令牌异常");
        }
    }
}
