package net.xdclass.xdvideo.utils;

import io.jsonwebtoken.*;
import net.xdclass.xdvideo.domain.User;
import org.apache.http.util.Asserts;
import org.springframework.util.Assert;

import java.util.Date;

/**
 * @Author: 杨强
 * @Date: 2019/8/26 21:20
 * @Version 1.0
 * @Discription jwt工具类
 */
public class JwtUtils {

    /**
     * subject是一个发行者==>>更像是一个声明
     */
    public static final String SUBJECT = "xdclass";

    /**
     * 过期时间 毫秒 一周
     */
    public static final long EXPIRE = 1000 * 60 * 60 * 24 * 7;

    /**
     * 对(head+payload)签名加密时的密钥==>>为什么要签名,防止伪造
     * 生产环境与开发环境通常是不同的===>>安全起见
     */
    public static final String APPSECRET = "xd666";

    /**
     * 生成Jwt
     * @param user 用户对象
     * @return
     */
    public static String genJsonWebToken(User user) {
        //校验用户信息是否完整
        if (user ==null || user.getId()==null || user.getName()==null || user.getHeadImg()==null){
            return null;
        }
        String token = Jwts.builder().setSubject(SUBJECT)
                .claim("id", user.getId())
                .claim("name", user.getName())
                .claim("img", user.getHeadImg())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))
                .signWith(SignatureAlgorithm.HS256, APPSECRET) //签名(配合密钥去签名)
                .compact();
        return token;
    }

    /**
     * 校验token
     * @param token
     * @return
     */
    public static Claims checkJWT(String token){

        Claims claims = null;
        try {
            claims = Jwts.parser().setSigningKey(APPSECRET).parseClaimsJws(token).getBody();
            return claims;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        User user =new User();
        user.setId(666);
        user.setName("测试用户");
        user.setHeadImg("图片地址");
        String jwt = JwtUtils.genJsonWebToken(user);
        System.out.println(jwt);
        Claims claims = JwtUtils.checkJWT(jwt);
        Assert.notNull(claims, "校验非空");
    }
}
