package com.chilly;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.jsonwebtoken.*;
import org.junit.jupiter.api.Test;

import java.util.*;

//@SpringBootTest
class SpringbootJwt2020ApplicationTests {


    /**
     * Java JWT 类库对比及使用 https://www.jianshu.com/p/e7caedab9a94
     */
    /**
     * java-jwt是Java语言中推荐的JWT实现库
     */
    //令牌获取
    @Test
    void contextLoads() {
        Map<String, Object> map = new HashMap<>();

        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.SECOND, 2000);

        String token = JWT.create().withHeader(map) //header
                .withClaim("userId", 21)//payload
                .withClaim("username", "xiaochen")//payload
                .withExpiresAt(instance.getTime())//指定令牌的过期时间
                .sign(Algorithm.HMAC256("!Q@W#E$R")) //签名
                ;
        System.out.println(token);
    }

    //令牌验证:根据令牌和签名解析数据
    //常见异常：
    //SignatureVerificationException 签名不一致异常
    //TokenExpiredException 令牌过期异常
    //AlgorithmMismatchException 算法不匹配异常
    //InvalidClaimException 失效的payload异常
    @Test
    void test() {
//        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1OTk2NDkxMzMsInVzZXJJZCI6MjEsInVzZXJuYW1lIjoieGlhb2NoZW4ifQ.LmTERviRHnmKpOeXO0f9K2nR1C7AovGfAV6Fmx7tcw0";
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1OTk2NTEzOTgsInVzZXJJZCI6MjEsInVzZXJuYW1lIjoieGlhb2NoZW4ifQ.KgJhjqs0T2WCijB9MDJQE9pCoKaC_eO3H6ILDmrhz1A";

        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("!Q@W#E$R")).build();
        DecodedJWT decodedJWT = jwtVerifier.verify(token);
        System.out.println("用户Id：" + decodedJWT.getClaim("userId").asInt());
        System.out.println("用户名：" + decodedJWT.getClaim("username"));
        System.out.println("过期时间：" + decodedJWT.getExpiresAt());

//        用户Id：21
//        用户名：com.auth0.jwt.impl.JsonNodeClaim@236e3f4e
//        过期时间：Wed Sep 09 19:36:38 CST 2020

    }

    /**
     * JJWT是一个提供端到端的JWT创建和验证的Java库
     */
    public static final long EXPIRE = 1000 * 60 * 60 * 24;
    public static final String SECRET = "ukc8BDbRigUDaY6pZFfWus2jZWLPHO";

    @Test
    public void jwt() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", "zkc");
        map.put("role", "admin");
//        JwtBuilder jwtBuilder = Jwts.builder();
        String jwt = Jwts.builder()
                // header
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS256")
                //payload
                .setClaims(map)
                .setSubject("admin-test")
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))
                .setId(UUID.randomUUID().toString())
                // signature
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
        System.out.println(jwt); // eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoiZ2V0In0.RiKlMMNgv3XRDcZtxgukAEpOHv_Q9pQQjDPPuPa-Dw0
    }

    @Test
    public void getJwtToken() {
        String JwtToken = Jwts.builder()
                //JWT头信息
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS2256")
                //设置分类；设置过期时间 一个当前时间，一个加上设置的过期时间常量
                .setSubject("lin-user")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))
                //设置token主体信息，存储用户信息
                .claim("id", "id")
                .claim("nickname", "nickname")
                //.signWith(SignatureAlgorithm.ES256, SECRET)
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
        System.out.println(JwtToken);
    }


    @Test
    public void getMemberIdByJwtToken() {
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbi10ZXN0Iiwicm9sZSI6ImFkbWluIiwibmFtZSI6ImdldCIsImV4cCI6MTY3MDA2MTQ0MCwianRpIjoiYTU0MmQ0YmQtNDFkYy00YmUxLTg1OTEtY2U4N2Y5N2E3MDM2In0.2DWXAx9c6FgrJ9zbXb3mmg_PhV7QJEtltkFEYSiyKTg";
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
        Claims body = claimsJws.getBody();
        System.out.println(body.toString());
    }
}
