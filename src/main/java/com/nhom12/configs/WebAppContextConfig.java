/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhom12.configs;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.nhom12.formatters.DisciplineScoreFormatter;
import com.nhom12.formatters.FacultyFormatter;
import com.nhom12.formatters.RoleFormatter;
import com.nhom12.formatters.StudentClassFormatter;
import com.nhom12.formatters.UserFormatter;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.format.FormatterRegistry;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 *
 * @author Admin
 */
@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan(basePackages = {
    "com.nhom12.controllers",
    "com.nhom12.repositories",
    "com.nhom12.services",
    "com.nhom12.components"
})
public class WebAppContextConfig implements WebMvcConfigurer {

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

//    @Bean
//    public InternalResourceViewResolver internalResourceViewResolver () {
//        InternalResourceViewResolver r = new InternalResourceViewResolver();
//        r.setViewClass(JstlView.class);
//        r.setPrefix("/WEB-INF/pages/");
//        r.setSuffix(".jsp");
//        
//        return r;
//    }
    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource m = new ResourceBundleMessageSource();
        m.setBasename("messages");

        return m;
    }
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/js/**").addResourceLocations("/resources/js/");
    }

    @Bean(name = "validator")
    public LocalValidatorFactoryBean validator() {
        LocalValidatorFactoryBean bean
                = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource());
        return bean;
    }

    @Override
    public Validator getValidator() {
        return validator();
    }

    @Bean
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver resolver
                = new CommonsMultipartResolver();
        resolver.setDefaultEncoding("UTF-8");
        return resolver;
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

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addFormatter(new RoleFormatter());
        registry.addFormatter(new StudentClassFormatter());
        registry.addFormatter(new FacultyFormatter());
        registry.addFormatter(new DisciplineScoreFormatter());
        registry.addFormatter(new UserFormatter());
    }

}
