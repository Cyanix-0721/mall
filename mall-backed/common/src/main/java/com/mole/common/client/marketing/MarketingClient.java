package com.mole.common.client.marketing;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mole.common.dto.marketing.SmsFlashPromotionSessionDetail;
import com.mole.common.entity.SmsCoupon;
import com.mole.common.entity.marketing.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("marketing-service")
public interface MarketingClient {

	@GetMapping("/coupons")
	List<SmsCoupon> findAll();

	@GetMapping("/coupons/{id}")
	SmsCoupon getById(@PathVariable("id") Long id);

	@PostMapping("/coupons")
	boolean save(@RequestBody SmsCoupon smsCoupon);

	@PutMapping("/coupons/{id}")
	boolean updateById(@PathVariable("id") Long id, @RequestBody SmsCoupon smsCoupon);

	@DeleteMapping("/coupons/{id}")
	boolean removeById(@PathVariable("id") Long id);

	@GetMapping("/coupons/type/{type}")
	List<SmsCoupon> findByType(@PathVariable("type") Integer type);

	@GetMapping("/coupons/name")
	List<SmsCoupon> findCouponsByName(@RequestParam(value = "name", required = false) String name);

	@GetMapping("/coupons/platform/{platform}")
	List<SmsCoupon> findByPlatform(@PathVariable("platform") Integer platform);

	@GetMapping("/coupons/list")
	List<SmsCoupon> listCoupons(
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "type", required = false) Integer type,
			@RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
			@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum);

	@GetMapping("/couponHistories")
	IPage<SmsCouponHistory> getCouponHistories(
			@RequestParam(value = "couponId", required = false) Long couponId,
			@RequestParam(value = "useStatus", required = false) Integer useStatus,
			@RequestParam(value = "orderSn", required = false) String orderSn,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
			@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum);

	@GetMapping("/couponHistories/memberId/{memberId}")
	ResponseEntity<List<SmsCouponHistory>> getCouponHistoriesByMemberId(@PathVariable Long memberId);

	@PostMapping("/flashPromotions")
	int createFlashPromotion(@RequestBody SmsFlashPromotion flashPromotion);

	@PutMapping("/flashPromotions/{id}")
	int updateFlashPromotion(@PathVariable Long id, @RequestBody SmsFlashPromotion flashPromotion);

	@DeleteMapping("/flashPromotions/{id}")
	int deleteFlashPromotion(@PathVariable Long id);

	@PatchMapping("/flashPromotions/{id}/status")
	int updateFlashPromotionStatus(@PathVariable Long id, @RequestParam Integer status);

	@GetMapping("/flashPromotions/{id}")
	SmsFlashPromotion getFlashPromotionItem(@PathVariable Long id);

	@GetMapping("/flashPromotions")
	IPage<SmsFlashPromotion> listFlashPromotions(@RequestParam(required = false) String keyword,
	                                             @RequestParam(required = false, defaultValue = "10") Integer pageSize,
	                                             @RequestParam(required = false, defaultValue = "1") Integer pageNum);

	// SmsFlashPromotionSessionController
	@PostMapping("/flash-promotion-sessions")
	int createFlashPromotionSession(@RequestBody SmsFlashPromotionSession promotionSession);

	@PutMapping("/flash-promotion-sessions/{id}")
	int updateFlashPromotionSession(@PathVariable Long id, @RequestBody SmsFlashPromotionSession promotionSession);

	@PatchMapping("/flash-promotion-sessions/{id}/status")
	int updateFlashPromotionSessionStatus(@PathVariable Long id, @RequestParam Integer status);

	@DeleteMapping("/flash-promotion-sessions/{id}")
	int deleteFlashPromotionSession(@PathVariable Long id);

	@GetMapping("/flash-promotion-sessions/{id}")
	SmsFlashPromotionSession getFlashPromotionSessionItem(@PathVariable Long id);

	@GetMapping("/flash-promotion-sessions")
	List<SmsFlashPromotionSession> listFlashPromotionSessions();

	@GetMapping("/flash-promotion-sessions/details")
	List<SmsFlashPromotionSessionDetail> selectFlashPromotionSessionList(@RequestParam Long id);

	// SmsFlashPromotionProductRelationController methods
	@PostMapping("/flashpromotionproductrelation/create")
	String createFlashPromotionProductRelation(@RequestBody List<SmsFlashPromotionProductRelation> relationList);

	@PutMapping("/flashpromotionproductrelation/{id}")
	String updateFlashPromotionProductRelation(@PathVariable Long id, @RequestBody SmsFlashPromotionProductRelation relation);

	@DeleteMapping("/flashpromotionproductrelation/{id}")
	String deleteFlashPromotionProductRelation(@PathVariable Long id);

	@GetMapping("/flashpromotionproductrelation/{id}")
	SmsFlashPromotionProductRelation getFlashPromotionProductRelationItem(@PathVariable Long id);

	@GetMapping("/flashpromotionproductrelation/count")
	Long getFlashPromotionProductRelationCount(@RequestParam Long flashPromotionId, @RequestParam Long flashPromotionSessionId);

	@GetMapping("/flashpromotionproductrelation/list")
	IPage<SmsFlashPromotionProductRelation> listFlashPromotionProductRelations(
			@RequestParam(required = false) Long promotionId,
			@RequestParam(required = false) Long sessionId,
			@RequestParam(defaultValue = "10") Integer pageSize,
			@RequestParam(defaultValue = "1") Integer pageNum);

	@PostMapping("/home-advertises")
	int create(@RequestBody SmsHomeAdvertise advertise);

	@DeleteMapping("/home-advertises/{id}")
	boolean deteleById(@PathVariable Long id);

	@PutMapping("/home-advertises/{id}")
	boolean updateById(@RequestBody SmsHomeAdvertise advertise);

	@GetMapping("/home-advertises/{id}")
	SmsHomeAdvertise gotById(@PathVariable Long id);

	@GetMapping("/home-advertises")
	IPage<SmsHomeAdvertise> list(
			@RequestParam(required = false) String name,
			@RequestParam(required = false) Integer type,
			@RequestParam(required = false) String endTime,
			@RequestParam(required = false, defaultValue = "10") Integer pageSize,
			@RequestParam(required = false, defaultValue = "1") Integer pageNum);
}