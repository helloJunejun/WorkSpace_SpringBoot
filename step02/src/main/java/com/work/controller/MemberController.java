package com.work.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.work.dto.Member;
import com.work.service.MemberService;

@Controller
public class MemberController {
	
	@Autowired
	public MemberService memberService;

   @RequestMapping("/main")
   
   public String main() {
      return "main"; 
   }
   
   @RequestMapping("/joinForm")
   
   public String joinForm() {
      return "joinForm";
   }
   
   @RequestMapping("/join")
   public String join(Member dto, Model model) {
	   System.out.println("회원가입요청");
	   System.out.println(dto);
	   
	   int result = memberService.addMember(dto);
	   if(result == 1) {
		   // 회원가입 성공
		  model.addAttribute("message", "[회원가입성공] 로그인 후 서비스 이용하세요");
		  return "loginForm";
	   } else {
		   // 회원가입 실패
		   model.addAttribute("message", "[회원가입성공] 로그인 후 서비스 이용하세요");
		   return "result";
	   }
	   
   }
   
   @RequestMapping("/loginForm")
   
   public String loginForm() {
      return "loginForm";
   }
   
   @RequestMapping("/login")
   
   public String login(String memberId, String memberPw, Model model) {
	   System.out.println("로그인요청");
	   System.out.println(memberId + ", " + memberPw);
	   
	   String grade = memberService.login(memberId, memberPw);
	   System.out.println("login grade : " + grade);
	   if(grade != null) {
		   model.addAttribute("message", "[로그인 사용자]" + memberId);		   
	   } else {
		   model.addAttribute("message", "[로그인 실패] 로그인 정보를 다시 확인하시기 바랍니다.");	
	   }
	   
	   return "result";
   }
   
   @RequestMapping("/memberList")
   public void memberList() {
	 	   	   
   }
}