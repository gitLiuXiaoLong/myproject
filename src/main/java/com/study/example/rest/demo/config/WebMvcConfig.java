package com.study.example.rest.demo.config;

import com.study.example.rest.demo.HttpMessages.PropertyPersonHttpMessageConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
		//自描述信息将xml 放置第一位
		//converters.set(0,new MappingJackson2XmlHttpMessageConverter());
		// properties +pserson 添加自描述信息
		converters.add(new PropertyPersonHttpMessageConverter());
	}

}
