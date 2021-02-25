package com.study.example.rest;

import com.study.example.rest.demo.filter.DefaultHandlerInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@RestController
@SpringBootApplication
public class SpringRestWebApplication implements WebMvcConfigurer {

	@RequestMapping("/")
	String init() {
		return "Hello word";
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringRestWebApplication.class, args);
	}

	@Override
	public void addInterceptors (InterceptorRegistry registry) {
		registry.addInterceptor(new DefaultHandlerInterceptor());
	}
}
