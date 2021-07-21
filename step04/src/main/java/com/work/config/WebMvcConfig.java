package com.work.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	
	// DI 인터셉터 객체 : 다중의 인스턴스인경우에는 Qualifier 사용하여 해당 instance 지정
	@Autowired
	@Qualifier("beforeActionInterceptor")
	HandlerInterceptor beforeActionInterceptor;
	
	@Autowired
	@Qualifier("needToLoginInterceptor")
	HandlerInterceptor needToLoginInterceptor;
	
	@Autowired
	@Qualifier("needToLogoutInterceptor")
	HandlerInterceptor needToLogoutInterceptor;
	
	@Autowired
	@Qualifier("needToAdminInterceptor")
	HandlerInterceptor needToAdminInterceptor;
	
	// 인터셉터 클래스와 자원 및 요청들에 대한 인터셉터를 연결추가 설정
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		// 1. beforeActionInterceptor : 사용자인증, 관리자권한 정보 설정위한 인터셉터
		registry.addInterceptor(beforeActionInterceptor).addPathPatterns("/**")
			.excludePathPatterns("/css/**").excludePathPatterns("/js/**").excludePathPatterns("/img/**");
		
		// 2. needToLoginInterceptor : 로그인 체킹 인터셉터
		registry.addInterceptor(needToLoginInterceptor).addPathPatterns("/**")
			.excludePathPatterns("/css/**").excludePathPatterns("/js/**").excludePathPatterns("/img/**")
			.excludePathPatterns("/").excludePathPatterns("/main").addPathPatterns("loginForm")
			.excludePathPatterns("login").excludePathPatterns("joinForm").excludePathPatterns("join");
			
		// 3. needToLogoutInterceptor : 로그인 상태에서는 사용해서는 안됨
		registry.addInterceptor(needToLogoutInterceptor).addPathPatterns("/**")
		.excludePathPatterns("/loginForm").excludePathPatterns("/login")
		.excludePathPatterns("/joinForm").excludePathPatterns("/join");
		
		// 4. needToAdminInterceptor
		registry.addInterceptor(needToAdminInterceptor)
		.excludePathPatterns("/memberList").excludePathPatterns("/memberDetail");
		
		WebMvcConfigurer.super.addInterceptors(registry);
		
		
	}
	
	
}
