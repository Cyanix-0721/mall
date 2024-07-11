package com.mole.user.service;

import com.mole.common.entity.user.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MemberServiceTest {
	@Autowired
	private MemberService memberService;

	@Test
	public void testCreate() {
		Member member = new Member();
		member.setUsername("test1111");
		member.setPassword("pw114514");
		member.setPhone("13345678901");
		memberService.create(member);
	}

	@Test
	public void testFindAll() {
		System.out.println("testFindAll");
		System.out.println(memberService.findAll());
	}
}
