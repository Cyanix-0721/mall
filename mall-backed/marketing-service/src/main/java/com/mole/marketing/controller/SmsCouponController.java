package com.mole.marketing.controller;

import com.alibaba.cloud.commons.lang.StringUtils;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mole.common.entity.marketing.SmsCoupon;
import com.mole.marketing.service.SmsCouponService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "SmsCoupon Management", description = "Endpoints for managing SMS Coupons")
@RestController
@RequestMapping("/coupons")
@RequiredArgsConstructor
public class SmsCouponController {
	private final SmsCouponService smsCouponService;

	@Operation(summary = "Get all SMS Coupons", description = "Retrieves a list of all available SMS Coupons.")
	@ApiResponse(responseCode = "200", description = "List of SMS Coupons returned successfully.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = SmsCoupon.class)))
	@GetMapping
	public List<SmsCoupon> findAll() {
		return smsCouponService.findAll();
	}

	@Operation(summary = "Create a new SMS Coupon", description = "Creates a new SMS Coupon.")
	@ApiResponse(responseCode = "200", description = "SMS Coupon created successfully.")
	@PostMapping(consumes = "application/json")
	public boolean save(@RequestBody SmsCoupon smsCoupon) {
		return smsCouponService.save(smsCoupon);
	}

	@Operation(summary = "Update an existing SMS Coupon by ID", description = "Updates the details of an SMS Coupon identified by its ID.")
	@ApiResponse(responseCode = "200", description = "SMS Coupon updated successfully.")
	@PutMapping("/{id}")
	public boolean updateById(@Parameter(description = "ID of the SMS Coupon to be updated", required = true) @PathVariable("id") Long id,
	                          @RequestBody SmsCoupon smsCoupon) {
		smsCoupon.setId(id);
		return smsCouponService.updateById(smsCoupon);
	}

	@Operation(summary = "Delete an SMS Coupon by ID", description = "Deletes an SMS Coupon based on the provided ID.")
	@ApiResponse(responseCode = "200", description = "SMS Coupon deleted successfully.")
	@DeleteMapping("/{id}")
	public boolean removeById(@Parameter(description = "ID of the SMS Coupon to delete", required = true) @PathVariable("id") Long id) {
		return smsCouponService.removeById(id);
	}

	@Operation(summary = "Get an SMS Coupon by ID", description = "Retrieves details of a specific SMS Coupon by its ID.")
	@ApiResponse(responseCode = "200", description = "SMS Coupon found successfully.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = SmsCoupon.class)))
	@GetMapping("/{id}")
	public SmsCoupon getById(@Parameter(description = "ID of the SMS Coupon to retrieve", required = true) @PathVariable("id") Long id) {
		return smsCouponService.getById(id);
	}

	@Operation(summary = "Find SMS Coupons by type", description = "Finds SMS Coupons based on the specified type.")
	@ApiResponse(responseCode = "200", description = "List of SMS Coupons filtered by type returned successfully.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = SmsCoupon.class)))
	@GetMapping("/type/{type}")
	public List<SmsCoupon> findByType(@Parameter(description = "Type of the SMS Coupon", required = true) @PathVariable("type") Integer type) {
		return smsCouponService.findByType(type);
	}

	@Operation(summary = "Find SMS Coupons by name", description = "Searches for SMS Coupons by their name. If no name is provided, returns all coupons.")
	@ApiResponse(responseCode = "200", description = "List of SMS Coupons matching the criteria returned successfully.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = SmsCoupon.class)))
	@GetMapping("/name")
	public ResponseEntity<List<SmsCoupon>> findCouponsByName(@Parameter(description = "Name of the SMS Coupon to search for", required = false) @RequestParam(value = "name", required = false) String name) {
		List<SmsCoupon> coupons;
		if (StringUtils.isNotBlank(name)) {
			coupons = smsCouponService.findByName(name);
		} else {
			coupons = smsCouponService.findAll();
		}
		return ResponseEntity.ok(coupons);
	}

	@Operation(summary = "Find SMS Coupons by platform", description = "Finds SMS Coupons based on the specified platform.")
	@ApiResponse(responseCode = "200", description = "List of SMS Coupons filtered by platform returned successfully.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = SmsCoupon.class)))
	@GetMapping("/platform/{platform}")
	public List<SmsCoupon> findByPlatform(@Parameter(description = "Platform of the SMS Coupon", required = true) @PathVariable("platform") Integer platform) {
		return smsCouponService.findByPlatform(platform);
	}

	/**
	 * 分页查询优惠券列表
	 *
	 * @param name     优惠券名称
	 * @param type     优惠券类型
	 * @param pageSize 每页数量
	 * @param pageNum  当前页码
	 * @return 分页数据
	 */
	@Operation(
			summary = "List SMS Coupons with Pagination",
			description = "Retrieves a paginated list of SMS Coupons. Supports filtering by name and type."
	)
	@ApiResponse(
			responseCode = "200",
			description = "Paginated list of SMS Coupons returned successfully.",
			content = @Content(mediaType = "application/json", schema = @Schema(implementation = IPage.class))
	)
	@GetMapping("/list")
	public IPage<SmsCoupon> listCoupons(
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "type", required = false) Integer type,
			@RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
			@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {

		return smsCouponService.list(name, type, pageSize, pageNum);
	}

}
