package com.work.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.work.dto.Member;

@Service
public class MemberService {
	// ArrayList<Member> 멤버변수 선언 및 생성 : 회원들의 자료 저장구조
	private ArrayList<Member> list = new ArrayList<Member>();
	
	// initMember() 메서드에서 3명의 회원 등록 구현, 일반회원, 우수회원, 관리자
	
	public MemberService() {
		initMember();
		System.out.println("MemberService() constructor loading");
	}
	
	private void initMember() {
		Member dto1 = Member.builder().memberId("user01").memberPw("password01").
				name("홍길동").mobile("010-1234-1000").email("user01@work.com").
				entryDate("2021-05-05").grade("G").mileage(1000).build();
		
		Member dto2 = Member.builder().memberId("user02").memberPw("password02").
				name("강감찬").mobile("010-1234-2000").email("user02@work.com").
				entryDate("2021-05-04").grade("S").manager("강동원").build();
		
		Member dto3 = Member.builder().memberId("user03").memberPw("password03").
				name("유관순").mobile("010-1234-3000").email("user03@work.com").
				entryDate("2021-05-03").grade("A").mileage(1000).build();
		
		list.add(dto1);
		list.add(dto2);
		list.add(dto3);
	}
	
	public int getSize() {
		return list.size();
		
	}

	/* 로그인 */
	public String login(String memberId, String memberPw) {
			for(Member dto : list) {
				if(dto.getMemberId().equals(memberId) && dto.getMemberPw().equals(memberPw)) {
					return dto.getGrade();
				}
			}
		return null;
	}
}
