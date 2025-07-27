package com.springboot.springBootDemo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{
	@Override
	public void addViewControllers(ViewControllerRegistry reg) {
		reg.addViewController("/courses").setViewName("courses");
		reg.addViewController("/about").setViewName("about");
	}
}
