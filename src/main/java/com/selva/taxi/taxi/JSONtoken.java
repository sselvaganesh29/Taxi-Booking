package com.selva.taxi.taxi;




import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;


public class JSONtoken
{

    private static final String secKey = "myownkeyselvagvbnuwetharazikhgvvhg";

    private static final Key key = Keys.hmacShaKeyFor(secKey.getBytes(StandardCharsets.UTF_8));



    public static String getUsername(String token) throws Exception{

        Claims claims = Jwts.parser()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }


    public static String getrole(String token) throws Exception
    {
        Claims claims = Jwts.parser().setSigningKey(key).build().parseClaimsJws(token).getBody();

        return  claims.get("role",String.class);

    }



}

