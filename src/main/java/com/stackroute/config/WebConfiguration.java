package com.stackroute.config;

import org.h2.server.web.WebServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//Declare a spring configuration class
@Configuration
public class WebConfiguration {
//  Configuration Code
// This code creates a spring bean with name ServletRegistrationBean. In that bean we added
// a URL mapping to "/console/*" for our h2 console. This is all we need to do. When we
// run our spring boot application we'll now be able to access database console.
    @Bean
    ServletRegistrationBean h2servletRegistration() {
        ServletRegistrationBean registrationBean= new ServletRegistrationBean(new WebServlet());
        registrationBean.addUrlMappings("/console/*");
        return registrationBean;
    }
}
