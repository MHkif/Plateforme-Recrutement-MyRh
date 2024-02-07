package mhkif.yc.myrh.config.security.jwt;

import mhkif.yc.myrh.service.UserDetailsServiceImpls.AdminUserDetails;
import mhkif.yc.myrh.service.UserDetailsServiceImpls.CompanyUserDetails;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final AdminUserDetails adminUserDetails;
    private final CompanyUserDetails companyUserDetails;



    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,@NonNull HttpServletResponse response,@NonNull FilterChain filterChain)
            throws ServletException, IOException {
        String header = request.getHeader("Authorization");
        final String token;
        final String userEmail;
        final String userType;
        if (header == null || !header.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        token = header.replace("Bearer ", "");
        jwtService.setToken(token);
        userEmail = jwtService.extractUserName(token);
        userType = jwtService.extractUserTypeFromToken();
        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            if (userType.equals("company")) {
                UserDetails userDetails =  this.companyUserDetails.loadUserByUsername(userEmail);
                if (jwtService.validateToken(token, userDetails)) {
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities()
                    );
                    authenticationToken.setDetails(
                            new WebAuthenticationDetailsSource().buildDetails(request)
                    );
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            } else if (userType.equals("admin")) {
                UserDetails userDetails =  this.adminUserDetails.loadUserByUsername(userEmail);
                if (jwtService.validateToken(token, userDetails)) {
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities()
                    );
                    authenticationToken.setDetails(
                            new WebAuthenticationDetailsSource().buildDetails(request)
                    );
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }            } else {
                throw new UsernameNotFoundException("Invalid user type: " + userType);
            }

        }
        filterChain.doFilter(request, response);
    }


}
