package com.mole.user.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AdminMapperTest {
	@Autowired
	AdminMapper adminMapper;
	@Test
public void testSelectList() {
		System.out.println("testSelectList");
		System.out.println(adminMapper.selectList(null));
	}
}
