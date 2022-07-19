package com.dora.assignment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

  @Autowired SuccessHandler successHandler;

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public UserDetailsService userDetailsService() {
    List<UserDetails> userDetailsList = new ArrayList<>();
    userDetailsList.add(
        User.builder()
            .username("user")
            .password(passwordEncoder().encode("password"))
            .roles("USER")
            .build());
    userDetailsList.add(
        User.builder()
            .username("admin")
            .password(passwordEncoder().encode("admin"))
            .roles("ADMIN")
            .build());
    return new InMemoryUserDetailsManager(userDetailsList);
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.headers()
        .frameOptions()
        .disable()
        .and()
        .authorizeRequests()
        .antMatchers("/admin**")
        .hasRole("ADMIN")
        .antMatchers("/user**")
        .hasRole("USER")
        .antMatchers("/secure**")
        .authenticated()
        .and()
        .formLogin()
        .loginPage("/secure/hello")
        .successHandler(successHandler)
        .permitAll()
        .and()
        .logout()
        .logoutUrl("/logout")
        .permitAll();

    return http.build();
  }
}
