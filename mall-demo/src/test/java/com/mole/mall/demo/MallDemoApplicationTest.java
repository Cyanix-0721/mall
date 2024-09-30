package com.mole.mall.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mole.mall.mbg.pojo.PmsProduct;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class MallDemoApplicationTest {
	@Test
	public void contextLoads() {
	}

	@Test
	public void testLogStash() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		PmsProduct product = new PmsProduct();
		product.setId(1L);
		product.setName("小米手机");
		product.setBrandName("小米");
		log.info(mapper.writeValueAsString(product));
		log.error(mapper.writeValueAsString(product));
	}

}