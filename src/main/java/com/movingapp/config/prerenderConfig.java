package com.movingapp.config;

import com.github.greengerong.PreRenderSEOFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;

@Configuration
public class prerenderConfig {

    @Bean
    public PreRenderSEOFilter PreRenderSEOFilter(ServletContext context) {
        PreRenderSEOFilter seoFilter = new PreRenderSEOFilter();
        FilterRegistration.Dynamic filter =  context.addFilter("prerender", seoFilter);
        filter.setInitParameter("prerenderToken", "o3s8ssHbX6BZggnpAfvs");
        filter.addMappingForUrlPatterns(null , true, "/*");
        return seoFilter;
    }
}