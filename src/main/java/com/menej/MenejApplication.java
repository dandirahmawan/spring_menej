package com.menej;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.io.File;
import java.util.Arrays;

@SpringBootApplication
@Configuration
@EnableScheduling
@Slf4j
public class MenejApplication extends SpringBootServletInitializer implements WebMvcConfigurer{

	public static void main(String[] args) {
		String userDir = new File(System.getProperty("user.dir")).getAbsolutePath();
		String rootDir = userDir.substring(0, userDir.indexOf(File.separator)+1);
		SpringApplication.run(MenejApplication.class, args);
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedMethods("*").allowedOrigins("http://localhost:3006");
	}

	@Bean
	 public InternalResourceViewResolver viewResolver() {

	     InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
	     viewResolver.setPrefix("/WEB-INF/view/");
	     viewResolver.setSuffix(".jsp");

	     return viewResolver;
	}

    @Bean
    public FilterRegistrationBean<Filter> loggingFilter(){
        FilterRegistrationBean<Filter> registrationBean
                = new FilterRegistrationBean<>();

        registrationBean.setFilter(new Filter());
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }
}
