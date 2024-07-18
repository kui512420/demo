package com.example.entity;


import com.example.pojo.User;
import io.jsonwebtoken.*;
import lombok.val;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class JwtLogin {

    private static long time = 1000*60*60;
    private static String sign = "512420";
    public  String jwtBd(User u) {
        JwtBuilder jwtBuilder = Jwts.builder();
        String token = jwtBuilder
                .setHeaderParam("alg","HS256")
                .setHeaderParam("type","JWT")
                .claim("username",u.getUsername())
                .claim("id",u.getId())
                .setExpiration(new Date(System.currentTimeMillis()+time))
                .signWith(SignatureAlgorithm.HS256,sign)
                .compact();
        return token;
    }

    public Map<Object,Object> jwtPe(String token){
        System.out.println(token);
        JwtParser jwtParser = Jwts.parser();
        Jws<Claims> claimsJwts = jwtParser.setSigningKey(sign).parseClaimsJws(token);
        Claims claims = claimsJwts.getBody();
        Map<Object,Object> map = new LinkedHashMap();
        map.put("id", claims.get("id"));
        map.put("username", claims.get("username"));
        map.put("imgsrc", "/userheader/"+claims.get("id")+".jpg");
        return map;
    }




}
