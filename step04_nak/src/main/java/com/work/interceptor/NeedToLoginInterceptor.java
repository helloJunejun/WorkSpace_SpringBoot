package com.work.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@Component("needToLoginInterceptor")
@Slf4j
public class NeedToLoginInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 인터셉터에서 요청객체 설정해 놓은
		boolean isLogin = (boolean)request.getAttribute("isLogin");
		log.debug("##" + isLogin);
		
		if(!isLogin) {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.append("<script>");
			out.append("alert('로그인 인증후에 이용하시기 바랍니다.');");
			out.append("location.replace('/loginForm');");
			out.append("</script>");
			
			//response.sendRedirect("/loginForm");
			return false;
		}
		
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}


}
