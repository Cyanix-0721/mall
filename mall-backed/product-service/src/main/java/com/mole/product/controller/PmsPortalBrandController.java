package com.mole.product.controller;

import com.mole.common.entity.product.api.CommonPage;
import com.mole.common.entity.product.api.CommonResult;
import com.mole.common.entity.product.PmsBrand;
import com.mole.common.entity.product.PmsProduct;
import com.mole.product.service.PmsPortalBrandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 首页品牌推荐管理Controller
 */
@RestController
@RequestMapping("/brand")
@Tag(name = "PmsPortalBrandController", description = "前台品牌管理")
public class PmsPortalBrandController {

    @Autowired
    private PmsPortalBrandService portalBrandService;

    /**
     * 分页获取推荐品牌
     *
     * @param pageSize 页面大小，默认6
     * @param pageNum  当前页，默认1
     * @return 品牌列表
     */
    @Operation(summary = "分页获取推荐品牌", description = "根据分页参数获取推荐品牌列表")
    @Parameters({
            @Parameter(name = "pageSize", description = "页面大小", example = "6"),
            @Parameter(name = "pageNum", description = "当前页数", example = "1")
    })
    @GetMapping("/recommendList")
    public ResponseEntity<CommonResult<List<PmsBrand>>> recommendList(
            @RequestParam(value = "pageSize", defaultValue = "6") Integer pageSize,
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<PmsBrand> brandList = portalBrandService.recommendList(pageNum, pageSize);
        return ResponseEntity.ok(CommonResult.success(brandList));
    }

    /**
     * 获取品牌详情
     *
     * @param brandId 品牌ID
     * @return 品牌详情
     */
    @Operation(summary = "获取品牌详情", description = "根据品牌ID获取品牌详细信息")
    @Parameter(name = "brandId", description = "品牌ID", required = true, example = "1")
    @GetMapping("/detail/{brandId}")
    public ResponseEntity<CommonResult<PmsBrand>> detail(@PathVariable Long brandId) {
        PmsBrand brand = portalBrandService.detail(brandId);
        return ResponseEntity.ok(CommonResult.success(brand));
    }

    /**
     * 分页获取品牌相关商品
     *
     * @param brandId  品牌ID
     * @param pageNum  当前页，默认1
     * @param pageSize 页面大小，默认6
     * @return 商品列表分页信息
     */
    @Operation(summary = "分页获取品牌相关商品", description = "根据品牌ID和分页参数获取品牌相关商品列表")
    @Parameters({
            @Parameter(name = "brandId", description = "品牌ID", required = true, example = "1"),
            @Parameter(name = "pageNum", description = "当前页数", example = "1"),
            @Parameter(name = "pageSize", description = "页面大小", example = "6")
    })
    @GetMapping("/{brandId}/productList")
    public ResponseEntity<CommonResult<CommonPage<PmsProduct>>> productList(
            @PathVariable Long brandId,
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "6") Integer pageSize) {
        CommonPage<PmsProduct> result = portalBrandService.productList(brandId, pageNum, pageSize);
        return ResponseEntity.ok(CommonResult.success(result));
    }
}
