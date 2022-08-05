package com.tapi.trackerapi.EXPENSE.jwt;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import com.tapi.trackerapi.EXPENSE.model.User;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.LoggerFactory;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtTokenUtil {
    private static final long JWT_TOKEN_VALIDITY = 24 * 60 * 60 * 1000; // 24 hour

    @Value("${app.jwt.secret}")
    private String SECRET_KEY;


//    public static final String SECRET_KEY = "expensetrackerApiKey";

//    public String generateToken(User user) {
//        return Jwts.builder()
//                .setSubject(String.format("%s,%s", user.getUid(), user.getEmail()))
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_DURATION))
//                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
//                .compact();
//
//    }
//
////    The getLogger() method of a Logger class used find or create a logger. If there is a logger exists with the passed name then the method will return that logger else method will create a new logger with that name and return it.
//    private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenUtil.class);
//
//    public boolean validateToken(String token) {
//        try {
//            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
//            return true;
//        } catch (ExpiredJwtException ex) {
//            LOGGER.error("JWT expired", ex.getMessage());
//        } catch (IllegalArgumentException ex) {
//            LOGGER.error("Token is null, empty or only whitespace", ex.getMessage());
//        } catch (MalformedJwtException ex) {
//            LOGGER.error("JWT is invalid", ex);
//        } catch (UnsupportedJwtException ex) {
//            LOGGER.error("JWT is not supported", ex);
//        } catch (SignatureException ex) {
//            LOGGER.error("Signature validation failed");
//        }
//
//        return false;
//    }
//
//    public String getSubject(String token) {
//        return parseClaims(token).getSubject();
//    }
//
//    private Claims parseClaims(String token) {
//        return Jwts.parser()
//                .setSigningKey(SECRET_KEY)
//                .parseClaimsJws(token)
//                .getBody();
//    }


    //retrieve username from jwt token
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    //retrieve expiration date from jwt token
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    //for retrieveing any information from token we will need the secret key
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    //check if the token has expired
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    //generate token for user
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, userDetails.getUsername());
    }

    private String doGenerateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }

    //validate token
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }


}
