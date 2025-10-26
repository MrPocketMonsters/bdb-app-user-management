package com.mrpocketmonsters.bdbcapacitaciones.usermanagement.service;

import java.security.Key;
import java.util.Base64;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.springframework.core.env.Environment;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;

/**
 * Service for handling JWT operations such as token generation and validation.
 * 
 * @author Nicolás Sabogal
 */
@Service
@RequiredArgsConstructor
public class JwtService {

    /** Environment for accessing application properties */
    private final Environment environment;

    /**
     * Generates a JWT token for the given user details.
     * 
     * @param userDetails User details for whom the token is generated
     * @return the generated JWT token
     */
    public String getToken(UserDetails userDetails) {
        return getToken(new HashMap<>(), userDetails);
    }

    /**
     * Generates a JWT token with extra claims and user details.
     * 
     * @param extraClaims Extra claims to include in the token
     * @param userDetails User details for whom the token is generated
     * @return the generated JWT token
     */
    private String getToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        List<String> roles = userDetails.getAuthorities().stream()
            .map(authority -> authority.getAuthority())
            .toList();

        extraClaims.put("roles", roles);
        Long expirationTime = environment.getProperty("jwt.expiration.time", Long.class);
        return Jwts.builder()
            .setClaims(extraClaims)
            .setSubject(userDetails.getUsername())
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
            .signWith(getKey(), SignatureAlgorithm.HS256)
            .compact();
    }

    /**
     * Generates the secret key from the base64 string.
     * 
     * @return the generated secret key.
     */
    private Key getKey() {
        String secret = environment.getProperty("jwt.secret.key");
        byte[] keyBytes= Base64.getDecoder().decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);       
    }

    /**
     * Extracts the user's email from the token subject.
     * 
     * @param token the JWT token.
     * @return the user's email.
     */
    public String getEmailFromToken(String token) {
        return getClaim(token, Claims::getSubject);
    }

    public Collection<? extends GrantedAuthority> getAuthoritiesFromToken(String token) {
        List<?> rawRoles = getClaim(token, claims -> claims.get("roles", List.class));
        return rawRoles.stream()
                .map(role -> (String) role)
                .map(SimpleGrantedAuthority::new)
                .toList();
    }

    /**
     * Checks if the token is valid by comparing the email and expiration date.
     * 
     * @param token the JWT token.
     * @param userDetails the user details.
     * @return true if the token is valid, false otherwise.
     */
    public boolean isTokenValid(String token, UserDetails userDetails) {
        String email = getEmailFromToken(token);
        return (email.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    /**
     * Retrieves all claims from the token.
     * 
     * @param token the JWT token.
     * @return the claims extracted from the token.
     */
    private Claims getAllClaims(String token){
        return Jwts
            .parserBuilder()
            .setSigningKey(getKey())
            .build()
            .parseClaimsJws(token)
            .getBody();
    }

    /**
     * Extracts a specific claim from the token using a resolver function.
     * 
     * @param <T> the type of the claim to extract.
     * @param token the JWT token.
     * @param claimsResolver the claims resolver function.
     * @return the value of the extracted claim.
     */
    public <T> T getClaim(String token, Function<Claims,T> claimsResolver){
        final Claims claims=getAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Extracts the expiration date from the token.
     * 
     * @param token the JWT token.
     * @return the token's expiration date.
     */
    public Date getExpiration(String token){
        return getClaim(token, Claims::getExpiration);
    }

    /**
     * Checks if the token has expired.
     * 
     * @param token the JWT token.
     * @return true if the token has expired, false otherwise.
     */
    public boolean isTokenExpired(String token){
        return getExpiration(token).before(new Date());
    }

}
