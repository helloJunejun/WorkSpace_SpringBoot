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
   
   // 로그인페이지[action="login/param", name="id", name="pw"]
   @RequestMapping("/login/param")
   public ModelAndView loginMap(
		   @RequestParam(value = "id") String memberId,
		   @RequestParam(value = "pw") String memberPw
		  ) {
	   System.out.println(memberId + ", " + memberPw);
	   
	   // 응답위한 객체 생성
	   ModelAndView mav = new ModelAndView();
	   mav.addObject("message", "로그인정보");
	   mav.addObject("loginId", memberId);
	   mav.setViewName("result");
	   
	   return mav;
   }
   
   @RequestMapping("/login/null")
   public String loginNull(
		   @RequestParam(required = true, defaultValue = "user01") String memberId,
		   @RequestParam(required = true, defaultValue = "password01") String memberPw
		   ) {
	   System.out.println(memberId + ", " + memberPw);
	   return "main";
   }
   
//   @RequestMapping("/login")
//   
//   public void login(Member dto) {
//	   System.out.println("로그인요청");
//	   System.out.println(dto.getMemberId() + ", " + dto.getMemberPw());
//	   
//   }   
//   @RequestMapping("/login")
//   public void login(Map<String, Object> map) {
//	   System.out.println("로그인요청");
//	   System.out.println(map.get("memberId") + ", " + map.get("memberPw"));
//   }   
   
   @RequestMapping("/join")
   public String join(Member dto) {
	   System.out.println("회원가입요청");
	   System.out.println(dto);
	   return null;
	   
   }   
   
//   @RequestMapping("/member/size")
//   @ResponseBody
//   public int size() {
//	  return memberService.getSize();
//   }
//   @RequestMapping(value = "/member/size", method = RequestMethod.POST)
//   @ResponseBody
//   public int size() {
//	   return memberService.getSize();
//   }
//   @PostMapping("/member/size")
   @GetMapping("/member/size")
   @ResponseBody
   public int size() {
	   return memberService.getSize();
   }
   
   
   
   public String join() {
      return null;
   }
   
   public String login() {
      return null;
   }
}