package org.example.task.config;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.task.security.JwtRequest;
import org.example.task.security.JwtTokenUtils;
import org.example.task.service.UserServiceImpl;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.security.SignatureException;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {

    private final JwtTokenUtils jwtTokenUtils;
    private final UserServiceImpl userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        String jwtToken = null;
        String username = null;

        if(authHeader != null && authHeader.startsWith("Bearer ")){
            jwtToken = authHeader.substring(7);
            try {
                username = jwtTokenUtils.extractUsername(jwtToken);
            } catch (ExpiredJwtException e){
                log.debug("Expired jwtToken");
            } catch (Exception ex) {
                log.error("Uncorrected signature");
            }
        }

        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
//            UsernamePasswordAuthenticationToken token =
//                    new UsernamePasswordAuthenticationToken(
//                            username,
//                            null,
//                            jwtTokenUtils.getRoles(jwtToken).stream().map(SimpleGrantedAuthority::new).toList()
//                    );

            UserDetails userDetails = userService.loadUserByUsername(username);

            if (jwtTokenUtils.validateToken(jwtToken, userDetails)) {
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response);
    }

}

