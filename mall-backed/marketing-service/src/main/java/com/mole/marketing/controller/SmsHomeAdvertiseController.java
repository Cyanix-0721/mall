package com.mole.marketing.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mole.marketing.entity.SmsHomeAdvertise;
import com.mole.marketing.service.SmsHomeAdvertiseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Tag(name = "首页广告管理", description = "管理首页广告的相关操作")
@RestController
@RequestMapping("/home-advertises")
public class SmsHomeAdvertiseController {

	private final SmsHomeAdvertiseService advertiseService;

	@Autowired
	public SmsHomeAdvertiseController(SmsHomeAdvertiseService advertiseService) {
		this.advertiseService = advertiseService;
	}

	@Operation(summary = "创建首页广告", description = "添加新的首页广告")
	@ApiResponse(responseCode = "200", description = "创建成功", content = @Content(schema = @Schema(type = "integer")))
	@PostMapping
	public int create(@RequestBody @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(schema = @Schema(implementation = SmsHomeAdvertise.class)))
	                  SmsHomeAdvertise advertise) {
		return advertiseService.create(advertise);
	}

	@Operation(summary = "Delete an advertiseService by ID", description = "Deletes an advertiseService based on the provided ID.")
	@ApiResponse(responseCode = "200", description = "advertiseService deleted successfully.")
	@DeleteMapping("/{id}")
	public boolean deteleById(@Parameter(description = "ID of the advertiseService to delete", required = true) @PathVariable("id") Long id) {
		return advertiseService.deteleById(id);
	}

	@Operation(summary = "更新广告状态", description = "根据广告ID更新广告状态")
	@ApiResponse(responseCode = "200", description = "状态更新成功", content = @Content(schema = @Schema(type = "integer")))
	@PatchMapping("/{id}/status")
	public int updateStatus(@Parameter(description = "广告ID", required = true) @PathVariable Long id,
	                        @Parameter(description = "新状态", required = true) @RequestParam Integer status) {
		return advertiseService.updateStatus(id, status);
	}

	@Operation(summary = "更新广告", description = "根据id更新广告")
	@ApiResponse(responseCode = "200", description = "更新成功.")
	@PutMapping("/{id}")
	public boolean updateById(@Parameter(description = "id更新", required = true) @PathVariable("id") Long id,
	                          @RequestBody SmsHomeAdvertise advertise) {
		advertise.setId(id);
		return advertiseService.updateById(advertise);
	}

	@Operation(summary = "获取广告详情", description = "根据广告ID获取广告详细信息")
	@ApiResponse(responseCode = "200", description = "查询成功", content = @Content(schema = @Schema(implementation = SmsHomeAdvertise.class)))
	@GetMapping("/{id}")
	public SmsHomeAdvertise gotById(@Parameter(description = "ID of the SmsHomeAdvertise to retrieve", required = true) @PathVariable("id") Long id) {
		return advertiseService.gotById(id);
	}

	@Operation(summary = "分页查询广告列表", description = "根据条件分页查询广告列表")
	@ApiResponse(responseCode = "200", description = "查询成功", content = @Content(schema = @Schema(implementation = IPage.class)))
	@GetMapping
	public IPage<SmsHomeAdvertise> list(
			@Parameter(description = "广告名称", required = false) @RequestParam(required = false) String name,
			@Parameter(description = "广告类型", required = false) @RequestParam(required = false) Integer type,
			@Parameter(description = "结束时间", required = false) @RequestParam(required = false) String endTime,
			@Parameter(description = "每页大小", required = false, example = "10") @RequestParam(required = false, defaultValue = "10") Integer pageSize,
			@Parameter(description = "当前页数", required = false, example = "1") @RequestParam(required = false, defaultValue = "1") Integer pageNum) {
		return advertiseService.list(name, type, endTime, pageSize, pageNum);
	}
}
