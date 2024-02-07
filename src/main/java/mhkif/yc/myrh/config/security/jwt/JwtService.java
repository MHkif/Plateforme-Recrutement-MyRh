package mhkif.yc.myrh.config.security.jwt;

import mhkif.yc.myrh.config.security.authenticators.AuthenticatedAdmin;
import mhkif.yc.myrh.config.security.authenticators.AuthenticatedCompany;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
@Slf4j
public class JwtService {


    private static final String SECRET_KEY = "504E635266556A586E327234753778214125442A472D4B6150645367566B5970";

    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);

        return claimsResolver.apply(claims);
    }

    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    /**
     * Generate token for user details and claims (roles) and expiration date (1 day)
     * @param claims roles
     * @param userDetails user details
     * @return token
     */
    public String generateToken(
            Map<String, Object> claims,
            UserDetails userDetails
    ){

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 5)) // TODO : U need to change the time
                .claim("userType", userDetails instanceof AuthenticatedAdmin ? "admin" :(userDetails instanceof AuthenticatedCompany ? "company" :  "applicant"))
                .signWith(SignatureAlgorithm.HS256, getSecretKey())
                .compact();
    }

    /**
     *  Method to validate token and check if it's expired
     * @param token token
     * @return true if token is valid
     */
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUserName(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token){
        return Jwts.parser()
                .setSigningKey(getSecretKey())
                .parseClaimsJws(token)
                .getBody();
    }

    public String extractUserTypeFromToken() {
        log.info(this.getClass().getName()+" Token : "+getToken());
        Claims claims = Jwts.parser()
                .setSigningKey(getSecretKey())
                .parseClaimsJws(getToken())
                .getBody();
        return (String) claims.get("userType");
    }

    public Key getSecretKey(){
        byte [] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
