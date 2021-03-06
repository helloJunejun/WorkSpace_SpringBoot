package com.work.controller;

import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.work.dto.Member;
import com.work.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class MemberController {
	
	@Autowired
	public MemberService memberservice;

	@RequestMapping("/main")
	public String main() {
		return "main"; 
	}
	
	@RequestMapping("/joinForm")
	public String joinForm(Model model, HttpSession session) {
		return "joinForm";
	}
	
	@RequestMapping("/loginForm")
	public String loginForm() {
		return "loginForm";
	}
	
	@RequestMapping("/login")
	  public String login(String memberId , String memberPw,  Model model, HttpSession session) {
        
        log.info("## 여기는 멤버 컨트롤러 : 로그인 요청 ");
        log.info("## 여기는 멤버 컨트롤러 "+memberId + ", "+memberPw);
        String grade = memberservice.login(memberId, memberPw);
        
        Member dto = memberservice.loginToMember(memberId,memberPw);
        
        log.debug("## 여기는 멤버 컨트롤러 "+ grade);
        
        if(grade != null) {
           
           session.setAttribute("memberId", memberId);
           session.setAttribute("grade", grade);
           session.setAttribute("dto", dto);
           
           model.addAttribute("message", "[안내] 로그인 완료"+ memberId+"님 안녕하세용");
           return "main";
        }else {
           model.addAttribute("message", "[오류] 로그인 실패하였습니다");
           return "result";
        }
     }
		
	@RequestMapping("/join")
	public String insertMember(Member dto, Model model) {
		log.info("회원가입 요청");
		int result = memberservice.memberList(dto);
		if(result == 1) {
			model.addAttribute("message", "[회원가입성공] 로그인 후 이용하세요");
			return "loginForm";
		}else {
			model.addAttribute("message", "[회원가입실패]");
			return "result";
		}
	}
	
	@RequestMapping("/memberList")
	public String memberList(Model model, HttpSession session) {
		List<Member> list = memberservice.getMember();
		System.out.println("전체회원 조회 : " + list);
		model.addAttribute("list", list);
		return "memberList";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		Enumeration<String> attributes = session.getAttributeNames();
		while(attributes.hasMoreElements()) {
			String attribute = (String) attributes.nextElement();
			log.debug("## " + attribute + " : " + session.getAttribute(attribute));
			session.removeAttribute(attribute);
		}
		session.invalidate();
		return "main";
	}
	
}
