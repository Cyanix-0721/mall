package com.mole.product.controller;

import com.mole.common.entity.product.api.CommonPage;
import com.mole.common.entity.product.api.CommonResult;
import com.mole.common.dto.product.PmsProductAttributeParam;
import com.mole.common.dto.product.ProductAttrInfo;
import com.mole.common.entity.product.PmsProductAttribute;
import com.mole.product.service.PmsProductAttributeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "PmsProductAttributeController", description = "商品属性管理")
@RequestMapping("/productAttribute")
public class PmsProductAttributeController {

    @Autowired
    private PmsProductAttributeService productAttributeService;

    @Operation(summary = "根据分类查询属性列表或参数列表")
    @GetMapping("/list/{cid}")
    public CommonResult<CommonPage<PmsProductAttribute>> getList(
            @Parameter(name = "cid", description = "分类ID", required = true) @PathVariable Long cid,
            @Parameter(name = "type", description = "0表示属性，1表示参数", required = true) @RequestParam(defaultValue = "1") Integer type,
            @Parameter(name = "pageSize", description = "每页数量", example = "5") @RequestParam(defaultValue = "5") Integer pageSize,
            @Parameter(name = "pageNum", description = "当前页数", example = "1") @RequestParam(defaultValue = "1") Integer pageNum) {
        List<PmsProductAttribute> productAttributeList = productAttributeService.getList(cid, type, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(productAttributeList));
    }


    @Operation(summary = "添加商品属性信息")
    @PostMapping("/create")
    public CommonResult<Integer> create(@RequestBody PmsProductAttributeParam productAttributeParam) {
        int count = productAttributeService.create(productAttributeParam);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @Operation(summary = "修改商品属性信息")
    @PutMapping("/update/{id}")
    public CommonResult<Integer> update(@PathVariable Long id, @RequestBody PmsProductAttributeParam productAttributeParam) {
        int count = productAttributeService.update(id, productAttributeParam);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @Operation(summary = "查询单个商品属性")
    @GetMapping("/{id}")
    public CommonResult<PmsProductAttribute> getItem(@PathVariable Long id) {
        PmsProductAttribute productAttribute = productAttributeService.getItem(id);
        return CommonResult.success(productAttribute);
    }

    @Operation(summary = "批量删除商品属性")
    @PostMapping("/delete")
    public CommonResult<Integer> delete(@RequestBody List<Long> ids) {
        int count = productAttributeService.delete(ids);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @Operation(summary = "根据商品分类的id获取商品属性及属性分类")
    @GetMapping("/attrInfo/{productCategoryId}")
    public CommonResult<List<ProductAttrInfo>> getAttrInfo(@PathVariable Long productCategoryId) {
        List<ProductAttrInfo> productAttrInfoList = productAttributeService.getProductAttrInfo(productCategoryId);
        return CommonResult.success(productAttrInfoList);
    }
}
