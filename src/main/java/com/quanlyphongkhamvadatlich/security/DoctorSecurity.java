package com.quanlyphongkhamvadatlich.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.quanlyphongkhamvadatlich.enums.EnumRole;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class DoctorSecurity {
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public DaoAuthenticationProvider authenticationProviderForDoctor() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);

        return authenticationProvider;
    }

    @Bean
    @Order(2)
    public SecurityFilterChain securityFilterChainForDoctor(HttpSecurity http) throws Exception {
        http.securityMatcher("/doctor/**");
        http.csrf(AbstractHttpConfigurer::disable);
        http.authenticationProvider(authenticationProviderForDoctor());
        http
                .authorizeHttpRequests(
                        (authorize) -> authorize
                               // .requestMatchers("/doctor/**") //.permitAll()
                               .requestMatchers("/doctor/**").hasAuthority(EnumRole.DOCTOR.name())
                )
                .formLogin(form -> form.loginPage("/doctor/login").permitAll()
                        .usernameParameter("username")
                        .passwordParameter("password")
                        .defaultSuccessUrl("/doctor/appointments", true)
                        .loginProcessingUrl("/doctor/login")
                )
                .logout(logout -> logout
                        .logoutRequestMatcher(
                                new AntPathRequestMatcher("/doctor/logout", "GET"))
                        .logoutSuccessUrl("/doctor/login")
                        .deleteCookies("JSESSIONID")
                )
                .exceptionHandling(ex -> ex.accessDeniedPage("/errors/403"));
        return http.build();
    }
}
