package com.mole.product.controller;

import com.mole.common.entity.product.api.CommonPage;
import com.mole.common.entity.product.api.CommonResult;
import com.mole.common.dto.product.PmsProductAttributeCategoryItem;
import com.mole.common.entity.product.PmsProductAttributeCategory;
import com.mole.product.service.PmsProductAttributeCategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "PmsProductAttributeCategoryController", description = "商品属性分类管理")
@RequestMapping("/productAttribute/category")
public class PmsProductAttributeCategoryController {

    @Autowired
    private PmsProductAttributeCategoryService productAttributeCategoryService;

    @Operation(summary = "添加商品属性分类")
    @PostMapping("/create")
    public CommonResult<Integer> create(@RequestParam String name) {
        int count = productAttributeCategoryService.create(name);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @Operation(summary = "修改商品属性分类")
    @PutMapping("/update/{id}")
    public CommonResult<Integer> update(@PathVariable Long id, @RequestParam String name) {
        int count = productAttributeCategoryService.update(id, name);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @Operation(summary = "删除单个商品属性分类")
    @DeleteMapping("/delete/{id}")
    public CommonResult<Integer> delete(@PathVariable Long id) {
        int count = productAttributeCategoryService.delete(id);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @Operation(summary = "获取单个商品属性分类信息")
    @GetMapping("/{id}")
    public CommonResult<PmsProductAttributeCategory> getItem(@PathVariable Long id) {
        PmsProductAttributeCategory productAttributeCategory = productAttributeCategoryService.getItem(id);
        return CommonResult.success(productAttributeCategory);
    }

    @Operation(summary = "分页获取所有商品属性分类")
    @GetMapping("/list")
    public CommonResult<CommonPage<PmsProductAttributeCategory>> getList(
            @Parameter(description = "每页数量，默认5", example = "5") @RequestParam(defaultValue = "5") Integer pageSize,
            @Parameter(description = "当前页数，默认1", example = "1") @RequestParam(defaultValue = "1") Integer pageNum) {
        List<PmsProductAttributeCategory> productAttributeCategoryList = productAttributeCategoryService.getList(pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(productAttributeCategoryList));
    }

    @Operation(summary = "获取所有商品属性分类及其下属性")
    @GetMapping("/list/withAttr")
    public CommonResult<List<PmsProductAttributeCategoryItem>> getListWithAttr() {
        List<PmsProductAttributeCategoryItem> productAttributeCategoryResultList = productAttributeCategoryService.getListWithAttr();
        return CommonResult.success(productAttributeCategoryResultList);
    }
}
