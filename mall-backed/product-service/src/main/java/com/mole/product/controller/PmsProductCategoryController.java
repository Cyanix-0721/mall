package com.mole.product.controller;

import com.mole.common.entity.product.api.CommonPage;
import com.mole.common.entity.product.api.CommonResult;
import com.mole.common.dto.product.PmsProductCategoryParam;
import com.mole.common.dto.product.PmsProductCategoryWithChildrenItem;
import com.mole.common.entity.product.PmsProductCategory;
import com.mole.product.service.PmsProductCategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "PmsProductCategoryController", description = "商品分类管理")
@RequestMapping("/productCategory")
public class PmsProductCategoryController {

    @Autowired
    private PmsProductCategoryService productCategoryService;

    @Operation(summary = "添加商品分类")
    @PostMapping("/create")
    public CommonResult<Integer> createProductCategory(@Validated @RequestBody PmsProductCategoryParam productCategoryParam) {
        int count = productCategoryService.create(productCategoryParam);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @Operation(summary = "修改商品分类")
    @PutMapping("/update/{id}")
    public CommonResult<Integer> updateProductCategory(@Parameter(description = "分类ID") @PathVariable Long id,
                                        @Validated @RequestBody PmsProductCategoryParam productCategoryParam) {
        int count = productCategoryService.update(id, productCategoryParam);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @Operation(summary = "分页查询商品分类")
    @GetMapping("/list")
    public CommonResult<CommonPage<PmsProductCategory>> getListProductCategory(
            @Parameter(description = "搜索关键词", example = "服装") @RequestParam(required = false) String keyword,
            @Parameter(description = "显示状态", example = "1") @RequestParam(required = false) Integer showStatus,
            @Parameter(description = "每页数量", example = "5") @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
            @Parameter(description = "当前页数", example = "1") @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<PmsProductCategory> productCategoryList = productCategoryService.list(keyword, showStatus, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(productCategoryList));
    }

    @Operation(summary = "根据id获取商品分类")
    @GetMapping("/{id}")
    public CommonResult<PmsProductCategory> getItemProductCategory(@Parameter(description = "分类ID") @PathVariable Long id) {
        PmsProductCategory productCategory = productCategoryService.getItem(id);
        return CommonResult.success(productCategory);
    }

    @Operation(summary = "删除商品分类")
    @DeleteMapping("/delete/{id}")
    public CommonResult<Integer> deleteProductCategory(@Parameter(description = "分类ID") @PathVariable Long id) {
        int count = productCategoryService.delete(id);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @Operation(summary = "修改导航栏显示状态")
    @PostMapping("/update/navStatus")
    public CommonResult<Integer> updateNavStatus(
            @Parameter(description = "分类ID列表") @RequestParam List<Long> ids,
            @Parameter(description = "导航栏显示状态") @RequestParam Integer navStatus) {
        int count = productCategoryService.updateNavStatus(ids, navStatus);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @Operation(summary = "修改显示状态")
    @PostMapping("/update/showStatus")
    public CommonResult<Integer> updateShowStatus(
            @Parameter(description = "分类ID列表") @RequestParam List<Long> ids,
            @Parameter(description = "显示状态") @RequestParam Integer showStatus) {
        int count = productCategoryService.updateShowStatus(ids, showStatus);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @Operation(summary = "查询所有一级分类及子分类")
    @GetMapping("/list/withChildren")
    public CommonResult<List<PmsProductCategoryWithChildrenItem>> listWithChildren() {
        List<PmsProductCategoryWithChildrenItem> list = productCategoryService.listWithChildren();
        return CommonResult.success(list);
    }
}
