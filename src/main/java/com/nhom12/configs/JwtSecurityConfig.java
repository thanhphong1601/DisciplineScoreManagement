/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhom12.configs;

import com.nhom12.filters.CustomAccessDeniedHandler;
import com.nhom12.filters.JwtAuthenticationTokenFilter;
import com.nhom12.filters.RestAuthenticationEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 * @author ACER
 */
@Configuration
@EnableWebSecurity
@EnableTransactionManagement
@ComponentScan(basePackages = {
    "com.epm.controllers",
    "com.epm.repositories",
    "com.epm.services",
    "com.epm.components",})
@Order(1)
public class JwtSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter() throws Exception {
        JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter = new JwtAuthenticationTokenFilter();
        jwtAuthenticationTokenFilter.setAuthenticationManager(authenticationManager());
        return jwtAuthenticationTokenFilter;
    }

    @Bean
    public RestAuthenticationEntryPoint restServicesEntryPoint() {
        return new RestAuthenticationEntryPoint();
    }

    @Bean
    public CustomAccessDeniedHandler customAccessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().ignoringAntMatchers("/api/**");
        http.authorizeRequests().antMatchers("/api/login/").permitAll();
        http.authorizeRequests().antMatchers("/api/activities/").permitAll();
        http.authorizeRequests().antMatchers("/api/users/").permitAll();
        http.authorizeRequests().antMatchers("/api/current-user/").permitAll();
        http.authorizeRequests().antMatchers("/api/faculties/").permitAll();
        http.authorizeRequests().antMatchers("/api/classes/").permitAll();
        http.authorizeRequests().antMatchers("/api/activities/{activityId}/").permitAll();
        http.authorizeRequests().antMatchers("/api/reports/").permitAll();
        http.authorizeRequests().antMatchers("/api/stats/class/").permitAll();

        http.antMatcher("/api/**").httpBasic().authenticationEntryPoint(restServicesEntryPoint()).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
                .antMatchers(HttpMethod.POST, "/api/comments/").access("hasRole('ROLE_ChuyenVienCTSV') or hasRole('ROLE_TroLySinhVien') or hasRole('ROLE_SinhVien')")
                .antMatchers(HttpMethod.GET, "/api/activities/joined/").access("hasRole('ROLE_ChuyenVienCTSV') or hasRole('ROLE_TroLySinhVien') or hasRole('ROLE_SinhVien')")
                .antMatchers(HttpMethod.POST, "/api/reports/").access("hasRole('ROLE_ChuyenVienCTSV') or hasRole('ROLE_TroLySinhVien')")
                .antMatchers(HttpMethod.POST, "/api/comments/").access("hasRole('ROLE_ChuyenVienCTSV') or hasRole('ROLE_TroLySinhVien') or hasRole('ROLE_SinhVien')")
                .antMatchers(HttpMethod.GET, "/api/criterions/").access("hasRole('ROLE_ChuyenVienCTSV') or hasRole('ROLE_TroLySinhVien') or hasRole('ROLE_SinhVien')")
                .antMatchers(HttpMethod.GET, "/api/comments/").access("hasRole('ROLE_SinhVien') or hasRole('ROLE_ChuyenVienCTSV') or hasRole('ROLE_TroLySinhVien')")
                .antMatchers(HttpMethod.GET, "/api/comments/").access("hasRole('ROLE_SinhVien') or hasRole('ROLE_ChuyenVienCTSV') or hasRole('ROLE_TroLySinhVien')")
                .antMatchers(HttpMethod.GET, "/api/stats/class/pdf/").access("hasRole('ROLE_ChuyenVienCTSV') or hasRole('ROLE_TroLySinhVien') or hasRole('ROLE_SinhVien')")
                .antMatchers(HttpMethod.POST, "/api/activities/upload-csv/").access("hasRole('ROLE_ChuyenVienCTSV') or hasRole('ROLE_TroLySinhVien')")
                .antMatchers(HttpMethod.DELETE, "/api/reports/").access("hasRole('ROLE_ChuyenVienCTSV') or hasRole('ROLE_TroLySinhVien')")                
                .antMatchers(HttpMethod.GET, "/api/**/").access("hasRole('ROLE_ChuyenVienCTSV') or hasRole('ROLE_TroLySinhVien')")
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling().accessDeniedHandler(customAccessDeniedHandler());
    }
}
