package com.mole.user.client;

import com.mole.common.client.marketing.MarketingClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SmsCouponHistoryClientTest {
 @Autowired
 private MarketingClient marketingClient;

 @Test
 public void test() {
  System.out.println(marketingClient.getCouponHistoriesByMemberId(1L));
 }
}