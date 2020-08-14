package com.hqyj.SpringBoot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.hqyj.SpringBoot.filter.ParameterFilter;
import com.hqyj.SpringBoot.interceptor.UrlInterceptor;

@Configuration
@AutoConfigureAfter({WebMvcAutoConfiguration.class})
public class WebMvcConfig implements WebMvcConfigurer{
	@Autowired
	private UrlInterceptor urlInterceptorr;
	
	@Autowired
	private ResourceConfigBean resourceConfigBean;
	
	@Bean
	public FilterRegistrationBean<ParameterFilter> filter() {
		FilterRegistrationBean<ParameterFilter> register = new FilterRegistrationBean<ParameterFilter>();
		register.setFilter(new ParameterFilter());
		return register;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(urlInterceptorr).addPathPatterns("/**");

	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		String systemName = System.getProperty("os.name");
		if (systemName.toLowerCase().startsWith("win")) {
			registry.addResourceHandler(resourceConfigBean.getResourcePathPattern())
				.addResourceLocations(ResourceUtils.FILE_URL_PREFIX + resourceConfigBean.getLocalPathForWindow());
		} else  {
			registry.addResourceHandler(resourceConfigBean.getResourcePathPattern())
			.addResourceLocations(ResourceUtils.FILE_URL_PREFIX + resourceConfigBean.getLocalPathForLinux());
		}
	}
	
}
