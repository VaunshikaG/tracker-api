package com.tapi.trackerapi.EXPENSE.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tapi.trackerapi.EXPENSE.model.User;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    @Autowired
    private CustomUserDetailService customUserDetailService;


    @Autowired
    private JwtTokenUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String requestToken = request.getHeader("Authorization");

        String username = null;

        String token = null;

        if (requestToken != null && requestToken.startsWith("JWT ")) {
            token = requestToken.substring(4);

            try {
                username = this.jwtUtil.getUsernameFromToken(token);
                System.out.println(requestToken);

            } catch (IllegalArgumentException e) {
                System.out.println("Unable to get Jwt token");
            } catch (ExpiredJwtException e) {
                System.out.println("Jwt token has expired");
            } catch (MalformedJwtException e) {
                System.out.println("invalid jwt");
            }

        } else {
            System.out.println("Jwt token does not begin with Jwt");
        }


        // once we get the token , now validate

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.customUserDetailService.loadUserByUsername(username);

            if (this.jwtUtil.validateToken(token, userDetails)) {
                // sahi chal rha hai
                // authentication karna hai

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, null);

                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

            } else {
                System.out.println("Invalid jwt token");
            }
        } else {
            System.out.println("username is null or context is not null");
        }

        filterChain.doFilter(request, response);


//        String token = getAccessToken(request);
//
//        if (!hasAuthorizationBearer(request)) {
//            filterChain.doFilter(request, response);
//            return;
//        }
//
//        if (!jwtUtil.validateToken(token)) {
//            filterChain.doFilter(request, response);
//            return;
//        }
//
//        setAuthenticationContext(token, request);
//        filterChain.doFilter(request, response);
    }

//    private boolean hasAuthorizationBearer(HttpServletRequest request) {
//        String header = request.getHeader("Authorization");
//        if (ObjectUtils.isEmpty(header) || !header.startsWith("JWT")) {
//            return false;
//        }
//        return true;
//    }
//
//    private String getAccessToken(HttpServletRequest request) {
//        String header = request.getHeader("Authorization");
//        String token = header.split(" ")[1].trim();
//        return token;
//    }
//
//    private void setAuthenticationContext(String token, HttpServletRequest request) {
//        UserDetails userDetails = getUserDetails(token);
//
//        UsernamePasswordAuthenticationToken
//                authentication = new UsernamePasswordAuthenticationToken(userDetails, null, null);
//
//        authentication.setDetails(
//                new WebAuthenticationDetailsSource().buildDetails(request));
//
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//    }
//
//    private UserDetails getUserDetails(String token) {
//        User userDetails = new User();
//        String[] jwtSubject = jwtUtil.getSubject(token).split(",");
//
//        userDetails.setUid(Integer.parseInt(jwtSubject[0]));
//        userDetails.setEmail(jwtSubject[1]);
//
//        return userDetails;
//    }
}
