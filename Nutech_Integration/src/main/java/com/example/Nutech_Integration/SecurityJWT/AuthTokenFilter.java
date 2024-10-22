package com.example.Nutech_Integration.SecurityJWT;


import com.example.Nutech_Integration.Service.Auth.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Map;
import java.util.logging.Logger;

@Component
@RequiredArgsConstructor
public class AuthTokenFilter extends OncePerRequestFilter {

    private final  JwtUtil jwtUtil;
    private final UserService userService;
    private static final Logger logger = Logger.getLogger(AuthTokenFilter.class.getName());
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        try{
            String headerAuth = request.getHeader("Authorization");
            String token = null;

            if (headerAuth != null && headerAuth.startsWith("Bearer ")){
                token = headerAuth.substring(7);
            }
            if (token != null && jwtUtil.verifyJwtToken(token)){
                Map<String, String> userInfo = jwtUtil.getUserInfoByToken(token);
                UserDetails userDetails = userService.loadUserByUsername(userInfo.get("username"));



                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }catch (Exception e){
            logger.severe("Invalid token: " + e.getMessage());
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().write("{\"status\": 108, \"message\": \"Token tidak valid atau kadaluwarsa\", \"data\": null}");
            return;
        }
        filterChain.doFilter(request,response);
    }
}
