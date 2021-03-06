package com.work.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@Component(value="beforActionInterceptor") // Qualifier 다중 인스턴스인 경우에 지정사용
@Slf4j
public class BeforeActionInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		log.debug("## beforActionInterceptor");
		
		HttpSession session = request.getSession();
		
		String memberId = (String)session.getAttribute("memberId");
		String grade = (String)session.getAttribute("memberId");
		
		boolean isLogin = false;
		boolean isAdmin = false;
		
		// 로그인 인증여부 체킹
		 if(memberId != null) {
	           isLogin = true;
	        }
		
		// 관리자 여부 체킹
		if(grade != null && grade.equals("A")) {
			isAdmin = true;
		}
		
		request.setAttribute("isLogin", isLogin);
		request.setAttribute("isAdmin", isAdmin);
		
		log.debug("##" + isLogin + ", " + isAdmin);
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}

}
