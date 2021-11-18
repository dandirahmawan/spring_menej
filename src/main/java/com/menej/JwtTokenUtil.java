package com.menej;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.menej.model.db.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;

@Component
public class JwtTokenUtil implements Serializable {

    private static final long serialVersionUID = -2550185165626007488L;
    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;
    private String secret = "mnj-31102121-wwb";

    public String generateToken(User userDetails) {
        User claims = userDetails;
        ObjectMapper oMapper = new ObjectMapper();
        Map<String, Object> map = oMapper.convertValue(userDetails, Map.class);
        return doGenerateToken(map, userDetails);
    }

    public String doGenerateToken(Map<String, Object> map, User claims) {
        return Jwts.builder().setClaims(map)
                .setSubject(claims.getUserName())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    public User readToken(String token) {
        String tk = token.split(" ")[1];
        System.out.println(tk);
        try {
            Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(tk).getBody();

            /*set data user*/
            User user = new User();
            user.setEmailUser(claims.get("userName").toString());
            user.setUser_id(Integer.parseInt(claims.get("userId").toString()));
            user.setEmailUser(claims.get("emailUser").toString());

            Integer exp = Integer.parseInt(claims.get("exp").toString());
            System.out.println(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000));
            System.out.println("--------------------");
            System.out.println(exp);
            System.out.println(System.currentTimeMillis());
            System.out.println("");
            Timestamp ts = new Timestamp(exp);
            Date date = new Date(ts.getTime());
            System.out.println(date);
            return user;
        }catch (ExpiredJwtException e){
            return null;
        }
    }
}
