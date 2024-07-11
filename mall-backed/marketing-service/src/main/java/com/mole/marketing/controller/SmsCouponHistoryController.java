package com.mole.marketing.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mole.common.entity.marketing.SmsCouponHistory;
import com.mole.marketing.service.SmsCouponHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SmsCouponHistoryController {

	private final SmsCouponHistoryService smsCouponHistoryService;

	@Autowired
	public SmsCouponHistoryController(SmsCouponHistoryService smsCouponHistoryService) {
		this.smsCouponHistoryService = smsCouponHistoryService;
	}

	/**
	 * 分页查询优惠券领取记录
	 *
	 * @param couponId  优惠券id
	 * @param useStatus 使用状态
	 * @param orderSn   使用订单号码
	 * @param pageSize  每页大小
	 * @param pageNum   当前页码
	 * @return 分页后的优惠券领取记录
	 */
	@GetMapping("/couponHistories")
	public IPage<SmsCouponHistory> getCouponHistories(
			@RequestParam(value = "couponId", required = false) Long couponId,
			@RequestParam(value = "useStatus", required = false) Integer useStatus,
			@RequestParam(value = "orderSn", required = false) String orderSn,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
			@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {

		return smsCouponHistoryService.list(couponId, useStatus, orderSn, pageSize, pageNum);
	}

	@GetMapping("/couponHistories/memberId/{memberId}")
	public ResponseEntity<List<SmsCouponHistory>> getCouponHistoriesByMemberId(@PathVariable(value = "memberId") Long memberId) {
		return ResponseEntity.ok().body(smsCouponHistoryService.listByMemberId(memberId));
	}
}

