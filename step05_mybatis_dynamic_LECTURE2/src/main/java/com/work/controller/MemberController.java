package com.work.controller;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

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
	public MemberService memberService;
	
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
	
	@RequestMapping("/join")
	public String join(Member dto, Model model) {
		log.info("### 회원가입요청");
		log.debug("### " + dto);
		
		int result = memberService.addMember(dto);
		if (result == 1) {
			model.addAttribute("message", "[회원가입성공] 로그인 후 서비스 이용하세요");
			return "loginForm";
		} else {
			model.addAttribute("message", "[회원가입실패] 가입 정보를 다시 확인하시기 바랍니다.");
			return "result";
		}
	}

	@RequestMapping("/login")
	public String login(String memberId, String memberPw, Model model, HttpSession session) {
		log.info("### 로그인요청");
		log.debug("### " + memberId + ", " + memberPw);
		
		String grade = memberService.login(memberId, memberPw);
		
		Member dto = memberService.loginToMember(memberId, memberPw);
		
		System.out.println("loging grade : " + grade);
		
		if (grade != null && dto != null) {
			session.setAttribute("memberId", memberId); 
			session.setAttribute("grade", grade); 
			
			session.setAttribute("dto", dto); 
			
			model.addAttribute("message", "[로그인 사용자]" + memberId);
			return "main";	
		} else {
			model.addAttribute("message", "[로그인 실패] 로그인 정보를 다시 확인하시기 바랍니다.");
			return "result";
		}
	}
	
	@RequestMapping("/memberList")
	public String memberList(Model model, HttpSession session) {
		List<Member> list = memberService.memberList();
		model.addAttribute("list", list);
		return "memberList";
	}
	
	@RequestMapping("/memberDetail")
	public String memberDetail(String memberId, Model model) {
		Member dto = memberService.memberDetail(memberId);
		model.addAttribute("dto", dto);
		return "memberDetail";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		Enumeration<String> attributes = session.getAttributeNames();
		while (attributes.hasMoreElements()) {
			String attribute = attributes.nextElement();
			log.debug("### " + attribute + ":" + session.getAttribute(attribute));
			session.removeAttribute(attribute);
		}
		session.invalidate();
		return "main";
	}
	
	@RequestMapping("/multipleCondition")
	public String multipleCondition(String condition, String keyword, Model model) {
		log.debug("### multipleCondition: " + condition + ", " + keyword);
		
		List<Member> list = null;
		
		// 실습 변경 : condition 아이디인경우에 keyword에 "," 있으면 다중의 회원아이디에 대한 조건 검색 처리 
		// => List<String> 또는 String[] 배열들 회원아이디 items 추출 분리하기
		// java.lang.String API 활용
		if(condition.equals("memberId") && keyword.contains(",")) { // 검색어에 "," 포함 여부 확인
			String[] memberIdArray = keyword.split(","); // "," 구분자 토큰링 분리 => Array
			
			List<String> memberIdList = new ArrayList<String>();	// 아이디 토큰분리 ArrayList 추가
			for (String memberId : memberIdArray) {
				memberIdList.add(memberId.trim());
			}
			
			log.debug("다중 회원 조회: " + memberIdList);
			
			list = memberService.memberListByConditionToList(condition, memberIdList);
		} else {
			list = memberService.memberListByCondition(condition, keyword);
		}
		if (list.isEmpty()) {
			model.addAttribute("message", "검색 조건에 해당하는 데이터가 없습니다.");
		}
		model.addAttribute("list", list);
		return "memberList";
	}
}
