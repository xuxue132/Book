package com.example.springvue.Controller;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.springvue.Model.Landtable;

import java.util.Date;

/**
 * token生成，验证
 */

public class TokenUtil {
    private static final long EXPIRE_TIME = 10*60*60*1000; //token到期时间10小时
    private static final String TOKEN_SECRET = "xuxue"; //密钥
    private static Date expireAtTime;

    /**
     * 生成token
     * **/
    public static String sign(Landtable user){
        String token = null;
        try{
            Date expireAt = new Date(System.currentTimeMillis()+EXPIRE_TIME);
            expireAtTime = expireAt;
            token = JWT.create()
                    .withIssuer("auth0")   //发行人
                    .withClaim("username",user.getName())//存放数据
                    .withExpiresAt(expireAt) //过期时间
                    .sign(Algorithm.HMAC256(TOKEN_SECRET));
        }catch (IllegalArgumentException| JWTCreationException je){

        }
        return token;
    }

    /**
     * token验证
     * **/
    public static Boolean verify(String token){
        try{
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(TOKEN_SECRET)).withIssuer("auth0").build(); //创建token验证器
            DecodedJWT decodedJWT = jwtVerifier.verify(token);
//            System.out.println("认证通过: ");
//            System.out.println("username: "+ decodedJWT.getClaim("username").asString());
//            System.out.println("过期时间: "+decodedJWT.getExpiresAt());
        }catch (IllegalArgumentException|JWTCreationException je){
            //抛出错误，验证不通过
            return false;
        }
        return true;
    }
    public static String TokenUserName(String token){
        try{
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(TOKEN_SECRET)).withIssuer("auth0").build(); //创建token验证器
            DecodedJWT decodedJWT = jwtVerifier.verify(token);
//            System.out.println("认证通过: ");
//            System.out.println("username: "+ decodedJWT.getClaim("username").asString());
//            System.out.println("过期时间: "+decodedJWT.getExpiresAt());
            return decodedJWT.getClaim("username").asString();
        }catch (IllegalArgumentException|JWTCreationException je){
            //抛出错误，验证不通过
            return null;
        }
    }
//
//    /**
//     * 判断token是否过期
//     * **/
//    public static Boolean isTokenExpired(String token){
//        try {
//            return expireAtTime.before(new Date());
//        }catch (Exception e){
//            e.printStackTrace();
//            return false;
//        }
//    }
}
