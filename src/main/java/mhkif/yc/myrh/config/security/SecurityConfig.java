package mhkif.yc.myrh.config.security;

import mhkif.yc.myrh.config.security.jwt.JwtAuthenticationFilter;
import mhkif.yc.myrh.service.UserDetailsServiceImpls.AdminUserDetails;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@AllArgsConstructor
public class SecurityConfig {
    private final JwtAuthenticationFilter jwtAuthFilter;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(c -> c.disable());
        http.authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers(
                                "myrh/api/v1/offers",
                                "myrh/api/v1/admin/auth/*",
                                "myrh/api/v1/companies/auth/"
                        ).permitAll()
                        .requestMatchers("myrh/api/v1/jobSeekers/auth/register").permitAll()
                        .requestMatchers(HttpMethod.GET,"myrh/api/v1/areaActivity/**","myrh/api/v1/profiles/**").permitAll()
                        .requestMatchers("myrh/api/v1/companies/**").hasAuthority("COMPANY")
                        .requestMatchers("myrh/api/v1/admin/**").hasAuthority("ADMIN")
                        //.requestMatchers("myrh/api/v1/jobSeekers/**").hasAuthority("APPLICANT")
                        .anyRequest().authenticated())

                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .cors((cors) -> cors
                        .configurationSource(corsConfigurationSource())
                );


        http.addFilterAfter(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return  configuration.getAuthenticationManager();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:4200"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }













    /*
   // @Bean
    public AuthenticationManager authenticationManager() {
        return new ProviderManager(adminAuthenticationProvider(), companyAuthenticationProvider());
    }

    //@Bean
    public AdminAuthenticationProvider adminAuthenticationProvider() {
        return new AdminAuthenticationProvider(passwordEncoder());
    }

    //@Bean
    public CompanyAuthenticationProvider companyAuthenticationProvider() {
        return new CompanyAuthenticationProvider(passwordEncoder());
    }


     */
}
