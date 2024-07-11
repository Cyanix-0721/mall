package com.mole.user.controller;

import com.mole.common.entity.Response;
import com.mole.common.entity.user.Member;
import com.mole.user.service.MemberService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
public class MemberControllerTest {
 @Autowired
 private MemberController memberController;

 @MockBean
 private MemberService memberService;

 @Test
 public void testCreate() {
  Member member = new Member();
  member.setUsername("test1111");
  member.setPassword("pw114514");
  member.setPhone("13345678901");

  Mockito.when(memberService.create(any(Member.class))).thenReturn(true);

  ResponseEntity<Response<Member>> response = memberController.create(member);

  assertEquals(201, response.getStatusCodeValue());
  Mockito.verify(memberService, Mockito.times(1)).create(any(Member.class));
 }
}