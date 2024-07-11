package com.mole.product.controller;

import com.mole.common.entity.product.api.CommonResult;
import com.mole.common.entity.product.PmsSkuStock;
import com.mole.product.service.PmsSkuStockService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品SKU库存管理Controller
 */
@RestController
@Tag(name = "PmsSkuStockController", description = "sku商品库存管理")
@RequestMapping("/sku")
public class PmsSkuStockController {

    @Autowired
    private PmsSkuStockService skuStockService;

    @Operation(summary = "根据商品ID及sku编码模糊搜索sku库存")
    @GetMapping("/{pid}")
    public ResponseEntity<CommonResult<List<PmsSkuStock>>> getList(
            @Parameter(description = "商品ID", required = true) @PathVariable Long pid,
            @Parameter(description = "搜索关键词，可选") @RequestParam(value = "keyword", required = false) String keyword) {
        List<PmsSkuStock> skuStockList = skuStockService.getList(pid, keyword);
        return ResponseEntity.ok(CommonResult.success(skuStockList));
    }

    @Operation(summary = "批量更新sku库存信息")
    @PostMapping("/update/{pid}")
    public ResponseEntity<CommonResult<Integer>> update(
            @Parameter(description = "商品ID", required = true) @PathVariable Long pid,
            @Parameter(description = "sku库存列表", required = true) @RequestBody List<PmsSkuStock> skuStockList) {
        int count = skuStockService.update(pid, skuStockList);
        return count > 0
                ? ResponseEntity.ok(CommonResult.success(count))
                : ResponseEntity.ok(CommonResult.failed());
    }
}

