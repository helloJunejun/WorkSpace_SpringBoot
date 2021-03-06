package com.work.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.work.dto.Member;
import com.work.service.MemberService;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@Slf4j //log 어노테이션 주입
public class MemberController {
   
      //서비스 객체 생성
      @Autowired
      public MemberService service;
   
         //응답 페이지 이동 : jsp
         @RequestMapping("/main")
         public String main() {
            return "main"; // > WEB-INF/해당 문자열 뒤에 jsp 붙여줌
         }
         
         //응답 페이지 이동 : jsp
         @RequestMapping("/joinForm")
         public String joinForm(Model model, HttpSession session) {
        	 if(session.getAttribute("memberId") != null && session.getAttribute("dto") != null) {
         		model.addAttribute("message", "로그인 사용자가 이용할 수 없는 서비스 입니다.");
         		return "main";
         	}
         	
                	 
            return "joinForm"; // > WEB-INF/해당 문자열 뒤에 jsp 붙여줌
         }
         
         //응답 페이지 이동 : jsp
         @RequestMapping("/loginForm")
         public String login() {
            return "loginForm"; // > WEB-INF/해당 문자열 뒤에 jsp 붙여줌
         }
         
         @RequestMapping("/login")
         public String login(String memberId , String memberPw,  Model model,HttpSession session) {
            
            //내 요청에 대한 세션 가져오기 
            log.debug("##여기는 컨트롤러의 "+session.isNew()+ ", "+session.getCreationTime());
            log.info("## 여기는 멤버 컨트롤러 : 로그인 요청 ");
            log.info("## 여기는 멤버 컨트롤러 "+memberId + ", "+memberPw);
         
            String grade = service.login(memberId, memberPw);
            
            //로그인 회원의 도메인 반환
            Member dto = service.loginToMember(memberId,memberPw);
            
            //개발 중에 디버그 정보 출력, 개발 완료 후 삭제 , 개불편..
            log.debug("## 여기는 멤버 컨트롤러 "+grade);
            
            if(grade != null) {
               
               //혹시라도 기존 세션에 로그인 정보가 남아있다면 삭제
               //session.invalidate();
               //다시 새로운 객체 생성
               session.setAttribute("memberId", memberId);
               session.setAttribute("dto", dto);
               
               model.addAttribute("message", "[안내] 로그인 완료"+ memberId+"님 안녕하세용");
               return "main";
            }else {
               model.addAttribute("message", "[오류] 로그인 실패하였습니다");
               return "result";
            }
            
         }
         
         //전체 회원 조회
         @RequestMapping("/memberList")
         public String memberAll(Model model, HttpSession session) {
            
        	 //인증여부 체크
        	if(session.getAttribute("memberId") == null || session.getAttribute("dto") == null) {
        		model.addAttribute("message", "로그인 인증 후 이용하시기 바랍니다.");
        		return "result";
        	}
        	
        	// 관리자 권한 여부 체크
        	if(!session.getAttribute("grade").equals("A") ||
        			!((Member)session.getAttribute("dto")).getGrade().equals("A")) {
        		model.addAttribute("message", "관리자 권한 획득 후 이용하시기 바랍니다.");
        		return "main";
        	}
        	 
            List<Member> list = service.memberList(); 
            model.addAttribute("list",list);
             
            return "memberList";
         }
         
         
         //회원 가입 요청 
         @RequestMapping("/join")
         public String insertMember(Member dto, Model model)  {
            log.info("##여기는 컨트롤러의 ## insertMember 메소드 ");
            
            int result = service.addMember(dto);
            log.debug("## 여기는 멤버 컨트롤러 "+dto);
            
            if(result == 1) {
               //회원 가입 성공
               model.addAttribute("message", "[안내] 횐 가입 성공");
               return "loginForm";
            }else {
               //회원 가입 실패
               model.addAttribute("message", "[안내] 횐 가입 실패");
               return "result";
            }

         }
}   