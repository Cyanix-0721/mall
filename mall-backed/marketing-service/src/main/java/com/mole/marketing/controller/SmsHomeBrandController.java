package com.mole.marketing.controller;

import com.alibaba.cloud.commons.lang.StringUtils;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mole.marketing.entity.SmsHomeBrand;
import com.mole.marketing.service.SmsHomeBrandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "首页品牌管理", description = "处理与首页品牌展示相关的业务逻辑")
@RestController
@RequestMapping("/api/home-brands")
public class SmsHomeBrandController {

    private final SmsHomeBrandService smsHomeBrandService;

    @Autowired
    public SmsHomeBrandController(SmsHomeBrandService smsHomeBrandService) {
        this.smsHomeBrandService = smsHomeBrandService;
    }

    @Operation(summary = "创建品牌", description = "批量创建品牌展示信息")
    @ApiResponse(responseCode = "200", description = "创建成功", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)))
    @PostMapping
    @Transactional
    public ResponseEntity<String> create(@RequestBody List<SmsHomeBrand> homeBrandList) {
        int result = smsHomeBrandService.create(homeBrandList);
        return result > 0 ? ResponseEntity.ok("Brands created successfully.") : ResponseEntity.badRequest().body("Failed to create brands.");
    }

    @Operation(summary = "更新排序", description = "根据ID更新品牌的展示排序")
    @ApiResponse(responseCode = "200", description = "排序更新成功", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)))
    @PutMapping("/{id}/sort")
    public ResponseEntity<String> updateSort(@PathVariable Long id, @RequestParam Integer sort) {
        int result = smsHomeBrandService.updateSort(id, sort);
        return result > 0 ? ResponseEntity.ok("Sort updated successfully.") : ResponseEntity.badRequest().body("Failed to update sort.");
    }

    @Operation(summary = "删除品牌", description = "根据ID列表批量删除品牌展示信息")
    @ApiResponse(responseCode = "200", description = "删除成功", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)))
    @DeleteMapping
    public ResponseEntity<String> delete(@RequestBody List<Long> ids) {
        int result = smsHomeBrandService.delete(ids);
        return result > 0 ? ResponseEntity.ok("Brands deleted successfully.") : ResponseEntity.badRequest().body("Failed to delete brands.");
    }

    @Operation(summary = "更新推荐状态", description = "批量更新品牌的推荐状态")
    @ApiResponse(responseCode = "200", description = "推荐状态更新成功", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)))
    @PatchMapping("/recommend-status")
    public ResponseEntity<String> updateRecommendStatus(@RequestBody List<Long> ids, @RequestParam Integer recommendStatus) {
        int result = smsHomeBrandService.updateRecommendStatus(ids, recommendStatus);
        return result > 0 ? ResponseEntity.ok("Recommend status updated successfully.") : ResponseEntity.badRequest().body("Failed to update recommend status.");
    }

    @Operation(summary = "分页查询品牌列表", description = "根据条件分页查询品牌展示信息")
    @ApiResponse(responseCode = "200", description = "查询成功", content = @Content(mediaType = "application/json", schema = @Schema(implementation = IPage.class)))
    @GetMapping
    public ResponseEntity<IPage<SmsHomeBrand>> list(
            @RequestParam(required = false) String brandName,
            @RequestParam(required = false) Integer recommendStatus,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "1") Integer pageNum) {
        IPage<SmsHomeBrand> page = smsHomeBrandService.list(brandName, recommendStatus, pageSize, pageNum);
        return ResponseEntity.ok(page);
    }
}