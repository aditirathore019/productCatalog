package com.atcs.product.BuyZone.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import javax.servlet.Filter;
import java.util.Arrays;

@Configuration
public class ApiConfig{

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    private @Autowired AutowireCapableBeanFactory beanFactory;

    @Bean
    public FilterRegistrationBean myFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        Filter myFilter = new JWTAuthorizationFilter();
        beanFactory.autowireBean(myFilter);
        registration.setFilter(myFilter);
//        registration.addUrlPatterns("/app/*");
       registration.setUrlPatterns(Arrays.asList("/app/*","/"));
        return registration;
    }
}
