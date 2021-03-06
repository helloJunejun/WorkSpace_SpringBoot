package com.work.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;



/** 
 * 회원 도메인 클래스 
 */
@Data //@Setter @Getter @NoArgsConstructor @AllArgsConstructor .. 이미 포함
@Builder   //@Builder이용시에는 @AllArgsConstructor가 필수로 있어야함
@AllArgsConstructor
@NoArgsConstructor
//콘솔창 출력 : Member(user01, 홍길동, user01@work.com, 2021-04-04, G, 1000, null)
public class Member {
   private String memberId;
   private String memberPw;
   private String name;
   private String mobile;
   private String email;
   private String entryDate;
   private String grade;
   private int mileage;
   private String manager;
   
   
   
   
   
   
}