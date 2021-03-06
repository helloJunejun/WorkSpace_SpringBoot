package com.work.controller;


import java.util.ArrayList;
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
   public String joinForm(HttpSession session, Model model) {
      //로그인 하지 않은 사용자 제공 서비스 
      //로그인한 사용자가 요청하면 "로그인 사용자가 이용할 수 없는 서비스입니다."
      if(session.getAttribute("memberId")!=null && session.getAttribute("dto")!=null) {
         model.addAttribute("message", "로그인 사용자가 이용할 수 없는 서비스입니다.");
         return "main";
      }
      return "joinForm";
   }
   
   @RequestMapping("/loginForm")
   public String loginForm() {
      //로그인 하지 않은 사용자 제공 서비스
      return "loginForm";
   }
   
   @RequestMapping("/login")
   public String login(String memberId, String memberPw, Model model, HttpSession session) {
       //HttpSession session = request.getSession();// true
      log.debug("###" + session.isNew()+ ", " + session.getCreationTime());
       
      log.info("### 로그인요청");   //
      log.debug("### " + memberId + ", "+memberPw);
      
      //로그인회원의 등급 반환
      String grade = memberservice.login(memberId, memberPw);
      
      //로그인 화원의 도메인 반환
      Member dto = memberservice.loginToMember(memberId, memberPw);
      
      System.out.println("login grade : " + grade);
      
      
      if(grade != null && dto != null) {
         //사용자 인증 처리 
         //session.invalidate();   //invalidate() : 세션 다운시킴
         
         //혹시라도 기존세션에 있는 세션정보가 존재할 수도 있으니 기존 세션 객체 삭제 // 삭제 이유 : 세션설정 정보 삭제하기 위함
         // 다시 새로운 세션 객체를 생성 => request.getSession();
         // getAttributeNames( ) : java.util.Enumeration
         session.setAttribute("memberId", memberId);
         session.setAttribute("grade", grade);
         
         session.setAttribute("dto", dto);
         
         model.addAttribute("message", "[로그인 사용자]" + memberId);
         return "main";
         
      }else {
         model.addAttribute("message", "[로그인실패] 로그인 정보를 다시 확인하시기 바랍니다.");
         return "result";
      }
   }
   
   @RequestMapping("/join")
   public String join(Member dto, Model model) {
      //System.out.println("회원가입요청"); // 개발중 debug 정보 출력, 개발완료 후에는 삭제필요.
      //System.out.println(dto);
      
      log.info("###회원가입요청");   //
      log.debug("### " + dto);
      
      int result = memberservice.addMember(dto);
      if(result == 1) {
         //회원가입 성공
         model.addAttribute("message", "[회원가입 사용자]" + dto.getMemberId() + "로그인 후 서비스를 이용하세요");
         return "loginForm";
      }else {
         //회원가입 실패
         model.addAttribute("message", "[회원가입 실패]");
        
      }
      return "result";
   }
   
   @RequestMapping("/memberList")   // 전체회원조회 
   public String memberList(Model model, HttpSession session) {
    //요청에 대한 사용자 인증 및 권한 체킹 부분이 누락
      //인증여부 체크
      if(session.getAttribute("memberId")==null || session.getAttribute("dto")==null) {
         model.addAttribute("message","로그인 인증 후 이용하시기 바랍니다.");
         return "result";
         
      }
      //관리자 권한여부 체크
      if(!session.getAttribute("grade").equals("A") ||
            !((Member)session.getAttribute("dto")).getGrade().equals("A")   ) {
         model.addAttribute("message","관리자 권한 획득 후 이용하시기 바랍니다.");
         return "main";
         
      }
      List<Member> list = memberservice.memberList();
      System.out.println("membList : " + list.size());
      model.addAttribute("list",list);
      return "memberList";
      
   }
   
   @RequestMapping("/memberDetail")   // 전체회원조회 
   public String memberDetail(String memberId, Model model) {
      Member dto = memberservice.memberDetail(memberId);
      model.addAttribute("dto",dto);
      return "memberDetail";
      
   }
   @RequestMapping("/logout") //  로그아웃
   public String logout(HttpSession session) {
      
      
      // 세션 객체 가져와서(DI) 세션설정정보 삭제하고 세션객체 삭제처리
      Enumeration<String> attributes = session.getAttributeNames();
      while (attributes.hasMoreElements()) {
         String attribute = attributes.nextElement();
         log.debug("### logout " + attribute + ":" + session.getAttribute(attribute));
         session.removeAttribute(attribute);
      }
      session.invalidate();
      return "main";
   }
   
   @RequestMapping("/multipleCondition")
   public String multipleCondition(String condition, String keyword, Model model) {
      log.debug("### multipleCondition " + condition + ", " + keyword);
      
//      List<Member> list = null;
//      //변경 : condition 아이디인 경우에 keyword에 "," 있으면 다중의 회원 아이디에 대한 조건 검색처리
//      // => List<String> 또는 String[] 배열들 회원아이디 items 추출 분리하기
//      if("," 존재여부 검사) {      // java.lang.String API이용할 것
//         String[] memberIdList = "," //구분자로 이용 토큰닝 분리;
//         list = memberservice.memberListByCondition(condition,keyword);   
//      }
      
/*      List<Member> list = memberservice.memberListByCondition(condition,keyword);
      if(list.isEmpty()){
         model.addAttribute("message","검색 조건에 해당하는 데이터가 존재하지 않습니다.");
      }
      System.out.println("membList : " + list.size());
      model.addAttribute("list",list);
      return "memberList";
   }
   */
   

   



}