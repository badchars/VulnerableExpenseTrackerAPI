package com.oy.expensetrackerapi.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtTokenUtil {

    private static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

    @Value("${jwt.secret}")
    private String secret;

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();

        return Jwts.builder()
                .setClaims(claims)
                // TODO: setSubject alaninda kullaniciya ait parola da saklanabilir.
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+ JWT_TOKEN_VALIDITY * 1000))
                // TODO: VULNERABILITY-10:  1./2 JWT authentication bypass via flawed signature verification
                // TODO: Secure Code: Asagidaki signWith fonksiyonu kullanilmasi gerekir.
//                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();

    }



    // JWT Validation
    public String getUsernameFromToken(String jwtToken){
        return getClaimFromToken(jwtToken, Claims::getSubject);
    }

    private <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver ){
        // TODO: VULNERABILITY: 2./2 JWT authentication bypass via flawed signature verification
        final Claims claims = (Claims)Jwts.parser().parse(token).getBody();
        // TODO: Secure Code
//        final Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        return claimsResolver.apply(claims);

    }


    // TODO: VULNERABILITY: TOKEN VALIDATION-3: Token expire suresinin uzun olmasi
    // TODO: VULNERABILITY: TOKEN VALIDATION-4: Farkli validation da eklenebilir. Orn. kullanici 'user' auth iken 'admin' olarak degistirildiginde bunu kontrol etmez. Bunu yapmak icin token olustururken authorities de olusturulmalidir.
    public boolean validateToken(String jwtToken, UserDetails userDetails) {

        final String username = getUsernameFromToken(jwtToken);

        return username.equals(userDetails.getUsername()) && !isTokenExpired(jwtToken);

    }


    private boolean isTokenExpired(String jwtToken) {
        final Date expiration = getExpirationDateFromToken(jwtToken);

        return expiration.before(new Date());
    }

    private Date getExpirationDateFromToken(String jwtToken) {
        return getClaimFromToken(jwtToken, Claims::getExpiration);
    }
}
