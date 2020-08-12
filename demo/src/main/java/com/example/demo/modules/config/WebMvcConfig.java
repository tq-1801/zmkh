package com.example.demo.modules.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.demo.modules.interceptor.UrlInterceptor;

/**
 * 自定义配置类，来覆盖原始配置，加载自己写的其他功能类
 * 
 * @author ZengShiqi
 *
 */

@Configuration // 表示配置类
@AutoConfigureAfter({ WebMvcAutoConfiguration.class }) // 覆盖系统的配置类
public class WebMvcConfig implements WebMvcConfigurer {

	@Autowired
	private UrlInterceptor urlInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(urlInterceptor).addPathPatterns("/**"); // 添加自定义的得拦截器，并设置匹配规则为(/**):匹配字符串和路径
	}

}
