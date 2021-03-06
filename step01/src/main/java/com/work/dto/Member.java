package com.work.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;



/** 회원 도메인 클래스 
 * toString() 재정의 : 비밀번호, 휴대폰 제외
 * */
@Data //@Setter @Getter @NoArgsConstructor @AllArgsConstructor .. 이미 포함
@Builder   //@Builder이용시에는 @AllArgsConstructor가 필수로 있어야함
@ToString(exclude = {"memberPw","mobile"},includeFieldNames = false)
//콘솔창 출력 : Member(user01, 홍길동, user01@work.com, 2021-04-04, G, 1000, null)
public class Member {
   private String memberId;
   //@ToString.Exclude 
   private String memberPw;
   private String name;
   //@ToString.Exclude 
   private String mobile;
   private String email;
   private String entryDate;
   private String grade;
   private int mileage;
   private String manager;
   
   
   
   
   
   
}