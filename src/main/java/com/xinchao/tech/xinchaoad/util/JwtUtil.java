package com.xinchao.tech.xinchaoad.util;

import com.alibaba.fastjson.JSON;
import com.xinchao.tech.xinchaoad.common.exception.BaseException;
import com.xinchao.tech.xinchaoad.common.exception.ResultCode;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtil {
    static final String SECRET = "ThisIsASecret";

    public static String generateToken(String username) {
            HashMap<String, Object> map = new HashMap<>();
        //you can put any data in the map
        map.put("username", username);
        String jwt = Jwts.builder()
                .setClaims(map)
                .setExpiration(new Date(System.currentTimeMillis() + 3600_000_000L))// 1000 hour
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
        return jwt; //jwt前面一般都会加Bearer
    }

    public static Map validateToken(String token) {
        try {
            // parse the token.
            Map<String, Object> body = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token.replace("Bearer ",""))
                    .getBody();
            return body;
        }catch (Exception e){
            throw new BaseException(ResultCode.FAIL_AUTH_FAIL.getCode(),"token错误："+token,e);
        }
    }

    public static void main(String[] args) {
       Map map= validateToken("Bearer eyJhbGciOiJIUzUxMiJ9.eyJleHAiOjE1MzQyOTg0OTAsInVzZXJuYW1lIjoiYTFlOWY5YWQtMTRmZC00OTU4LTkzMWUtNTBjNmM4NmY1Y2YwIn0.cr6S2VmDsb3wclVYt1CDPMrlAeVW046fA3LcsgKf9ffjc44t9ktTmQ6chlf28NcoiB2RZU838iuJaVYlvUxRww");
        System.out.println(JSON.toJSON(map).toString());
    }
}