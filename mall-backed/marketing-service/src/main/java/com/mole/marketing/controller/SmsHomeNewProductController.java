package com.mole.marketing.controller;

import com.alibaba.cloud.commons.lang.StringUtils;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mole.marketing.entity.SmsHomeNewProduct;
import com.mole.marketing.service.SmsHomeNewProductService;
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

@Tag(name = "首页新品管理", description = "处理与首页新品展示相关的业务逻辑")
@RestController
@RequestMapping("/api/home-new-products")
public class SmsHomeNewProductController {

    private final SmsHomeNewProductService smsHomeNewProductService;

    @Autowired
    public SmsHomeNewProductController(SmsHomeNewProductService smsHomeNewProductService) {
        this.smsHomeNewProductService = smsHomeNewProductService;
    }

    @Operation(summary = "添加新品", description = "批量添加新品到首页展示")
    @ApiResponse(responseCode = "200", description = "添加成功", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)))
    @PostMapping
    @Transactional
    public ResponseEntity<String> create(@RequestBody List<SmsHomeNewProduct> homeNewProductList) {
        int result = smsHomeNewProductService.create(homeNewProductList);
        return result > 0 ? ResponseEntity.ok("New products added successfully.") : ResponseEntity.badRequest().body("Failed to add new products.");
    }

    @Operation(summary = "更新排序", description = "根据ID更新新品的展示排序")
    @ApiResponse(responseCode = "200", description = "排序更新成功", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)))
    @PutMapping("/{id}/sort")
    public ResponseEntity<String> updateSort(@PathVariable Long id, @RequestParam Integer sort) {
        int result = smsHomeNewProductService.updateSort(id, sort);
        return result > 0 ? ResponseEntity.ok("Sort updated successfully.") : ResponseEntity.badRequest().body("Failed to update sort.");
    }

    @Operation(summary = "删除新品", description = "根据ID列表批量删除新品展示信息")
    @ApiResponse(responseCode = "200", description = "删除成功", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)))
    @DeleteMapping
    public ResponseEntity<String> delete(@RequestBody List<Long> ids) {
        int result = smsHomeNewProductService.delete(ids);
        return result > 0 ? ResponseEntity.ok("Products deleted successfully.") : ResponseEntity.badRequest().body("Failed to delete products.");
    }

    @Operation(summary = "更新推荐状态", description = "批量更新新品的推荐状态")
    @ApiResponse(responseCode = "200", description = "推荐状态更新成功", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)))
    @PatchMapping("/recommend-status")
    public ResponseEntity<String> updateRecommendStatus(@RequestBody List<Long> ids, @RequestParam Integer recommendStatus) {
        int result = smsHomeNewProductService.updateRecommendStatus(ids, recommendStatus);
        return result > 0 ? ResponseEntity.ok("Recommend status updated successfully.") : ResponseEntity.badRequest().body("Failed to update recommend status.");
    }

    @Operation(summary = "分页查询新品列表", description = "根据条件分页查询新品展示信息")
    @ApiResponse(responseCode = "200", description = "查询成功", content = @Content(mediaType = "application/json", schema = @Schema(implementation = IPage.class)))
    @GetMapping
    public ResponseEntity<IPage<SmsHomeNewProduct>> list(
            @RequestParam(required = false) String productName,
            @RequestParam(required = false) Integer recommendStatus,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "1") Integer pageNum) {
        IPage<SmsHomeNewProduct> page = smsHomeNewProductService.list(productName, recommendStatus, pageSize, pageNum);
        return ResponseEntity.ok(page);
    }
}
