package com.axcelerate.assessment.configuration;

import com.axcelerate.assessment.bean.HomeHubBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HomeHubConfiguration {
    @Bean
    public HomeHubBean homeHubBean() {
        return new HomeHubBean();
    }
}
