/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhom12.configs;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 * @author Admin
 */
@Configuration
@EnableWebSecurity
@EnableTransactionManagement
@ComponentScan(basePackages = {
    "com.nhom12.controllers",
    "com.nhom12.repositories",
    "com.nhom12.services",
    "com.nhom12.components"
})
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http)
            throws Exception {
        http.formLogin()
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/")
                .failureUrl("/login?error");
       
        
        http.logout().logoutSuccessUrl("/login");

        http.exceptionHandling()
                .accessDeniedPage("/login?accessDenied");
        http.authorizeRequests()
                .antMatchers("/", "/activities/attending", "/activities", "/reports", "/points", "/points/list", "/stats/faculty", "/stats/class", "/stats/faculty/{type-id}/pdf", "/stats/class/{type-id}/pdf", "/users", "/users/add", "users/{userId}")
                .hasAuthority("ROLE_ChuyenVienCTSV")
                .antMatchers("/api/**").hasAuthority("ROLE_ChuyenVienCTSV")
                .anyRequest().authenticated();

        http.csrf().disable();
    }
    
    @Bean
    public Cloudinary cloudinary() {
        Cloudinary cloudinary
                = new Cloudinary(ObjectUtils.asMap(
                        "cloud_name", "dstjar2iy",
                        "api_key", "929341466922158",
                        "api_secret", "ZydOWhhvZkUfCfoOIwuTYObfmbM",
                        "secure", true));
        return cloudinary;
    }
}
