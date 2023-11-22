package com.btp.rwj.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Calendar;
import java.util.Date;

public class JwtUtil {
    private final static String tokenKey = "193j'dsuid1[j9];";

    public static String getToken(String username) {
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.HOUR, 1);
//        System.out.println(instance.getTime());
        return JWT
                .create()
                .withClaim("username", username)
                .withExpiresAt(instance.getTime())
                .sign(Algorithm.HMAC256(tokenKey));
    }

    public static String getToken(Date date, String username) {
        return JWT
                .create()
                .withClaim("username", username)
                .withExpiresAt(date)
                .sign(Algorithm.HMAC256(tokenKey));
    }

    public static String getToken(String username, String permission) {
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.HOUR, 1);
//        System.out.println(instance.getTime());
        return JWT
                .create()
                .withClaim("username", username)
                .withClaim("permission", permission)
                .withExpiresAt(instance.getTime())
                .sign(Algorithm.HMAC256(tokenKey));
    }

    public static String getToken(Date date, String username, String permission) {
        return JWT
                .create()
                .withClaim("username", username)
                .withClaim("permission", permission)
                .withExpiresAt(date)
                .sign(Algorithm.HMAC256(tokenKey));
    }


    public static boolean verifyToken(String token) {
        String username = getUsername(token);
        String permission = getPermission(token);
        long deadline = getDeadline(token);
        Date date = new Date(deadline * 1000);
        String tokenByusername = getToken(date, username, permission);

        return token.equals(tokenByusername);
    }

    public static boolean verifyTokenDeadline(String token) {
        long deadline = getDeadline(token);
        long nowdate = Calendar.getInstance().getTimeInMillis() / 1000;
        return deadline > nowdate;
    }

    public static long getDeadline(String token) {
        return JWT.decode(token).getClaim("exp").asLong();
    }

    public static String getUsername(String token) {
        return JWT.decode(token).getClaim("username").asString();
    }

    public static String getPermission(String token) {
        return JWT.decode(token).getClaim("permission").asString();
    }
}
