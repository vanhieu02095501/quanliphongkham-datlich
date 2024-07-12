package com.quanlyphongkhamvadatlich.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.quanlyphongkhamvadatlich.enums.EnumRole;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class AdminSecurityConfig {
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public DaoAuthenticationProvider authenticationProviderForAdmin() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);

        return authenticationProvider;
    }

    @Bean
    @Order(3)
    public SecurityFilterChain securityFilterChainForAdmin(HttpSecurity http) throws Exception {
        http
                .securityMatcher("/admin/**")
                //.csrf(configurer -> configurer.ignoringRequestMatchers("/com/quanlyphongkhamvadatlich/api/**", "/admin/**"))
                .authenticationProvider(authenticationProviderForAdmin())
                .authorizeHttpRequests(
                          authorize -> authorize
                                .requestMatchers("/admin/login")
                                .permitAll()
                                .requestMatchers("/admin/**", "/dashboard/**", "/api/medical-service/**")
                                .hasAuthority(EnumRole.ADMIN.name())
                )
                .formLogin(form -> form.loginPage("/admin/login").permitAll()
                        .usernameParameter("username")
                        .passwordParameter("password")
                        .defaultSuccessUrl("/admin/patient", true)
                        .loginProcessingUrl("/admin/login")
                )
                .logout(logout -> logout
                        .logoutRequestMatcher(
                                new AntPathRequestMatcher("/admin/logout", "GET"))
                        .logoutSuccessUrl("/admin/login")
                        .deleteCookies("JSESSIONID")
                )
                .exceptionHandling(ex -> ex.accessDeniedPage("/errors/403"));
        return http.build();
    }
}