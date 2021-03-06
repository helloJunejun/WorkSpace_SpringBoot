package com.work.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.work.dao.MemberDao;
import com.work.dto.Member;
import com.work.util.Utility;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MemberService2 {
	@Autowired
	private MemberDao memberDao;

	/* 로그인 */
	public String login(String memberId, String memberPw) {
		String grade = memberDao.login(memberId, memberPw);
		System.out.println("dao grade : " + grade);
		
			return grade;
	}
	
	/* 회원가입 */
	public int addMember(Member dto) {
		dto.setEntryDate(Utility.getCurrentDate());
		dto.setGrade("G");
		dto.setMileage(1000);
		int result = memberDao.insertMember(dto);
		
		return result;
	}
	
	/* 전체회원조회 */
	public List<Member> memberList() {
		memberDao.memberList();
		return null;

		
	}
	
    public Member loginToMember(String memberId, String memberPw) {
        
        Member dto = memberDao.loginToMember( memberId,  memberPw) ;
        
        return null;
     }
}
