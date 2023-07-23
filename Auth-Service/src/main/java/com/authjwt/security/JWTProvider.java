package com.authjwt.security;

import com.authjwt.dto.RequestDto;
import com.authjwt.entity.AuthUser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JWTProvider {
    @Value("${jwt.secret}")
    private String secret;

    @Autowired
    private RouteValidator routeValidator;

    @PostConstruct //esto se ejecuta despues de crear e bean
    public void init() {
        secret = Base64.getEncoder().encodeToString(secret.getBytes());
    }


    public String createToken(AuthUser authUser) {
        Map<String, Object> claim = new HashMap<>();
        claim = Jwts.claims().setSubject(authUser.getUserName());
        claim.put("id", authUser.getId());
        claim.put("role", authUser.getRole());
        Date now = new Date();
        Date expiration = new Date(now.getTime() + 3600000);
        return Jwts.builder()
                .setClaims(claim)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public boolean validateToken(String token, RequestDto requestDto) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
        } catch (Exception exp) {
            return false;
        }

        if (!isAdmin(token) && routeValidator.isAdminCheck(requestDto)){
            return false;
        }
        return true;
    }

    public String getUserNameFromToken(String token) {
        try {
            // con esto extraemos el usuario de los claims
            return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();

        } catch (Exception exp) {
            return "bad token";
        }
    }


    private boolean isAdmin(String token){
        //sacamos de los claims el rol y si o que viene en los claims es admin es true
    return (Jwts.parser().setSigningKey(secret)).parseClaimsJws(token).getBody().get("role").equals("admin");
    }
}
