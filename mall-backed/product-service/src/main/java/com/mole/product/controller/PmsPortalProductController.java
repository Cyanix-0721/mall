package com.mole.product.controller;

import com.mole.common.entity.product.api.CommonPage;
import com.mole.common.entity.product.api.CommonResult;
import com.mole.common.entity.product.PmsProduct;
import com.mole.common.dto.product.PmsPortalProductDetail;
import com.mole.common.dto.product.PmsProductCategoryNode;
import com.mole.product.service.PmsPortalProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * 前台商品管理Controller
 */
@RestController
@RequestMapping("/product")
public class PmsPortalProductController {

    @Autowired
    private PmsPortalProductService pmsPortalProductService;

    @Operation(summary = "综合搜索、筛选、排序商品", description = "支持关键词、品牌ID、商品分类ID、排序方式的筛选")
    @Parameter(name = "keyword", description = "搜索关键词", required = false)
    @Parameter(name = "brandId", description = "品牌ID", required = false)
    @Parameter(name = "productCategoryId", description = "商品分类ID", required = false)
    @Parameter(name = "pageNum", description = "当前页码", required = false, example = "1")
    @Parameter(name = "pageSize", description = "每页数量", required = false, example = "5")
    @Parameter(name = "sort", description = "排序字段:0->按相关度；1->按新品；2->按销量；3->价格从低到高；4->价格从高到低", required = false, example = "0")
    @GetMapping(value = "/search", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<CommonResult<CommonPage<PmsProduct>>> search(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) Long brandId,
            @RequestParam(required = false) Long productCategoryId,
            @RequestParam(required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(required = false, defaultValue = "5") Integer pageSize,
            @RequestParam(required = false, defaultValue = "0") Integer sort) {
        List<PmsProduct> productList = pmsPortalProductService.search(keyword, brandId, productCategoryId, pageNum, pageSize, sort);
        return ResponseEntity.ok(CommonResult.success(CommonPage.restPage(productList)));
    }

    @Operation(summary = "获取所有商品分类的树形结构")
    @GetMapping(value = "/categoryTreeList", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<CommonResult<List<PmsProductCategoryNode>>> categoryTreeList() {
        List<PmsProductCategoryNode> list = pmsPortalProductService.categoryTreeList();
        return ResponseEntity.ok(CommonResult.success(list));
    }

    @Operation(summary = "获取前台商品详情")
    @GetMapping(value = "/detail/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<CommonResult<PmsPortalProductDetail>> detail(@PathVariable Long id) {
        PmsPortalProductDetail productDetail = pmsPortalProductService.detail(id);
        return ResponseEntity.ok(CommonResult.success(productDetail));
    }
}
