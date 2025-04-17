package by.it.dsmobile.config.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;

@Service
public class JwtProvider {

    @Value("${security.jwt.secret}")
    private String secret;

    @Value("${security.jwt.expiration-time}")
    private long jwtExpiration;

    private SecretKey key;

    @PostConstruct
    public void init() {
        this.key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
    }

    public String generateToken(final String username) {
        return Jwts.builder()
                .claims(new HashMap<>())
                .subject(username)
                .issuedAt(new Date())
                .expiration((new Date(System.currentTimeMillis() + jwtExpiration)))
                .signWith(key, Jwts.SIG.HS256)
                .compact();
    }

    public String extractUsername(final String token) {
        return extractPayload(token).getSubject();
    }

    public boolean validateToken(final String token, final UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(final String token) {
        return extractPayload(token)
                .getExpiration()
                .before(new Date());
    }

    private Claims extractPayload(final String token) {
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

}
