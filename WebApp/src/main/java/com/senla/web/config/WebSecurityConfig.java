package com.senla.web.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

// @EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class WebSecurityConfig {
    //        extends WebSecurityConfigurerAdapter {

    //    private final UserDetailsService userDetailsService;

    //    @Override
    //    protected void configure(HttpSecurity http) throws Exception {
    //        http.csrf()
    //                .disable()
    //                .sessionManagement()
    //                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    //                .and()
    //                .authorizeRequests()
    //                .antMatchers("/auth/**", "/", "/users/**")
    //                .permitAll()
    //                .anyRequest()
    //                .authenticated();
    //    }

    //    @Override
    //    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder)
    //            throws Exception {
    //        authenticationManagerBuilder
    //                .userDetailsService(userDetailsService);
    //    }
}
