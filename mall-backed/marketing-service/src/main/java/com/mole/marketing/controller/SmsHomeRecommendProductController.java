package com.mole.marketing.controller;

import com.alibaba.cloud.commons.lang.StringUtils;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mole.marketing.entity.SmsHomeRecommendProduct;
import com.mole.marketing.service.SmsHomeRecommendProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name = "首页推荐商品管理", description = "处理与首页推荐商品展示相关的业务逻辑")
@RestController
@RequestMapping("/api/home-recommend-products")
public class SmsHomeRecommendProductController {

    private final SmsHomeRecommendProductService smsHomeRecommendProductService;

    @Autowired
    public SmsHomeRecommendProductController(SmsHomeRecommendProductService smsHomeRecommendProductService) {
        this.smsHomeRecommendProductService = smsHomeRecommendProductService;
    }

    @Operation(summary = "添加推荐商品", description = "批量添加商品到首页推荐展示")
    @ApiResponse(responseCode = "200", description = "添加成功", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)))
    @PostMapping
    @Transactional
    public ResponseEntity<String> create(@RequestBody List<SmsHomeRecommendProduct> homeRecommendProductList) {
        int result = smsHomeRecommendProductService.create(homeRecommendProductList);
        return result > 0 ? ResponseEntity.ok("Recommended products added successfully.") : ResponseEntity.badRequest().body("Failed to add recommended products.");
    }

    @Operation(summary = "更新排序", description = "根据ID更新推荐商品的展示排序")
    @ApiResponse(responseCode = "200", description = "排序更新成功", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)))
    @PutMapping("/{id}/sort")
    public ResponseEntity<String> updateSort(@PathVariable Long id, @RequestParam Integer sort) {
        int result = smsHomeRecommendProductService.updateSort(id, sort);
        return result > 0 ? ResponseEntity.ok("Sort updated successfully.") : ResponseEntity.badRequest().body("Failed to update sort.");
    }

    @Operation(summary = "移除推荐商品", description = "根据ID列表批量移除商品的推荐展示")
    @ApiResponse(responseCode = "200", description = "移除成功", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)))
    @DeleteMapping
    public ResponseEntity<String> delete(@RequestBody List<Long> ids) {
        int result = smsHomeRecommendProductService.delete(ids);
        return result > 0 ? ResponseEntity.ok("Products removed from recommendation successfully.") : ResponseEntity.badRequest().body("Failed to remove products from recommendation.");
    }

    @Operation(summary = "更新推荐状态", description = "批量更新推荐商品的推荐状态")
    @ApiResponse(responseCode = "200", description = "状态更新成功", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)))
    @PatchMapping("/recommend-status")
    public ResponseEntity<String> updateRecommendStatus(@RequestBody List<Long> ids, @RequestParam Integer recommendStatus) {
        int result = smsHomeRecommendProductService.updateRecommendStatus(ids, recommendStatus);
        return result > 0 ? ResponseEntity.ok("Recommendation status updated successfully.") : ResponseEntity.badRequest().body("Failed to update recommendation status.");
    }

    @Operation(summary = "分页查询推荐商品列表", description = "根据条件分页查询推荐商品展示信息")
    @ApiResponse(responseCode = "200", description = "查询成功", content = @Content(mediaType = "application/json", schema = @Schema(implementation = IPage.class)))
    @GetMapping
    public ResponseEntity<IPage<SmsHomeRecommendProduct>> list(
            @RequestParam(required = false) String productName,
            @RequestParam(required = false) Integer recommendStatus,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "1") Integer pageNum) {
        IPage<SmsHomeRecommendProduct> page = smsHomeRecommendProductService.list(productName, recommendStatus, pageSize, pageNum);
        return ResponseEntity.ok(page);
    }
}
