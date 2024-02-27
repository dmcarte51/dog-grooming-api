package com.example.doggroomingapi.config;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

// Contains methods for generating tokens, extracting info from the tokens, and validating tokens.
@Service
public class JwtService {


    private static final SecretKey SECRET_KEY = Jwts.SIG.HS256.key().build();

    // Generates JWT as a string. Most commonly generates when registering, logging in,
    // or changing certain user info such as password and username.
    public String generateToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails
    ) {
        int tokenLifeSpan = 86400000; // How long the token lasts (in ms), 86,400,000 is a day.

        return Jwts
                .builder()
                .claims(extraClaims)
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + tokenLifeSpan))
                .signWith(SECRET_KEY, Jwts.SIG.HS256)
                .compact();
    }

    // Generates a JWT without extra claims.
    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }


    // Checks if token is valid, unexpired, and compares the user details of the token with the
    // provided user details as a param to see if the specified user is authorized to use the token.
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    // uses extractExpiration to determine if the token's expiration
    // is before the current date/time of calling the method.
    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // Extracts the claim from the token and returns the date from the expiration field of the claim
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // Extracts the claim from the token and returns the username from the subject field of the claim
    public String extractUsername(String token) {

        return extractClaim(token, Claims::getSubject);
    }

    // Takes a token and extracts all claims, then extracts a specified claim
    // using a claimsResolver method (i.e. Claims::getSubject to get the username).
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // Extracts entire Claims object from token, used for extracting
    // specific claims such as subject (username) and expiration.
    private Claims extractAllClaims(String token) {
        return Jwts
                .parser()
                .verifyWith(SECRET_KEY)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

}
