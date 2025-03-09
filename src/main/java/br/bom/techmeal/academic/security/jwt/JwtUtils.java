package br.bom.techmeal.academic.security.jwt;

import br.bom.techmeal.academic.service.UserDetailImpl;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import java.security.Key;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Jwts;


import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtils {
    @Value("${TechMeal.jwdSecret}")
    private String jwtSecret;
    @Value("$${TechMeal.jweExpirationsMs}")
    private int jwtExpirationMs;

    @Autowired


    public String generateTokenFromUserDetailsImpl(UserDetailImpl userDetail){
        return Jwts.builder().setSubject(userDetail.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime()+ jwtExpirationMs))
                .signWith(getSigninKey(), SignatureAlgorithm.HS512).compact();

    }
    public Key getSigninKey() {
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
        return key;
    }


}
