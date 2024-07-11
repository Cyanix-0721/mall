package com.mole.user.controller;

import com.mole.common.client.marketing.MarketingClient;
import com.mole.common.entity.Response;
import com.mole.common.entity.marketing.SmsCouponHistory;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members/coupon")
@Tag(name = "Member coupon management")
public class MemberCouponController {

	private final MarketingClient marketingClient;

	@GetMapping("{memberId}")
	public ResponseEntity<Response<List<SmsCouponHistory>>> getMemberCoupons(@PathVariable Long memberId) {
		try {
			List<SmsCouponHistory> couponHistories = marketingClient.getCouponHistoriesByMemberId(memberId).getBody();
			System.out.println("couponHistories: " + couponHistories);
			return ResponseEntity.ok(Response.ok(couponHistories));
		} catch (Exception e) {
			return ResponseEntity.status(500).body(Response.error("Internal server error"));
		}
	}

	//TODO 优惠卷使用，需要先完成支付模块
/*	@PostMapping("/use/{id}")
	public boolean useCoupon(@PathVariable("id") Long id) {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = userDetails.getUsername();
		// Assuming the username is the user ID
		Long memberId = Long.parseLong(username);
		SmsCouponHistory couponHistory = marketingClient.getCouponHistoryById(id);
		if (couponHistory == null) {
			couponHistory = new SmsCouponHistory();
			couponHistory.setId(id);
			couponHistory.setMemberId(memberId);
			couponHistory.setCouponId(id);
			couponHistory.setUseStatus(1); // 1 for used
			couponHistory.setUseTime(new Date());
			return marketingClient.saveCouponHistory(couponHistory);
		} else {
			couponHistory.setUseStatus(1); // 1 for used
			couponHistory.setUseTime(new Date());
			return marketingClient.updateCouponHistoryById(id, couponHistory);
		}*/
}