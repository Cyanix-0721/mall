package com.mole.common.client.product;

import com.mole.common.entity.product.PmsBrand;
import com.mole.common.entity.product.PmsProduct;
import com.mole.common.entity.product.PmsProductCategory;
import com.mole.common.entity.product.api.CommonPage;
import com.mole.common.entity.product.api.CommonResult;
import com.mole.common.dto.product.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@FeignClient("product-service")
public interface ProductClient {

    @Operation(summary = "添加品牌")
    @PostMapping("/brand/create")
    CommonResult<Integer> createBrand(@Validated @RequestBody PmsBrandParam pmsBrand);

    @Operation(summary = "更新品牌")
    @PutMapping("/brand/update/{id}")
    CommonResult<Integer> updateBrand(@PathVariable Long id, @Validated @RequestBody PmsBrandParam pmsBrandParam);

    @Operation(summary = "删除品牌")
    @DeleteMapping("/brand/delete/{id}")
    CommonResult<Integer> deleteBrand(@PathVariable Long id);

    @Operation(summary = "根据品牌名称分页获取品牌列表")
    @GetMapping("/brand/list")
    CommonResult<CommonPage<PmsBrand>> getListBrand(
            @Parameter(description = "搜索关键词", example = "Nike") @RequestParam(required = false) String keyword,
            @Parameter(description = "显示状态", example = "1") @RequestParam(required = false) Integer showStatus,
            @Parameter(description = "页码", example = "1") @RequestParam(defaultValue = "1") Integer pageNum,
            @Parameter(description = "每页数量", example = "5") @RequestParam(defaultValue = "5") Integer pageSize);

    @Operation(summary = "根据编号查询品牌信息")
    @GetMapping("/brand/{id}")
    CommonResult<PmsBrand> getItemBrand(@PathVariable Long id);

    @Operation(summary = "综合搜索、筛选、排序商品", description = "支持关键词、品牌ID、商品分类ID、排序方式的筛选")
    @Parameter(name = "keyword", description = "搜索关键词", required = false)
    @Parameter(name = "brandId", description = "品牌ID", required = false)
    @Parameter(name = "productCategoryId", description = "商品分类ID", required = false)
    @Parameter(name = "pageNum", description = "当前页码", required = false, example = "1")
    @Parameter(name = "pageSize", description = "每页数量", required = false, example = "5")
    @Parameter(name = "sort", description = "排序字段:0->按相关度；1->按新品；2->按销量；3->价格从低到高；4->价格从高到低", required = false, example = "0")
    @GetMapping(value = "/product/search", produces = APPLICATION_JSON_VALUE)
    ResponseEntity<CommonResult<CommonPage<PmsProduct>>> search(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) Long brandId,
            @RequestParam(required = false) Long productCategoryId,
            @RequestParam(required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(required = false, defaultValue = "5") Integer pageSize,
            @RequestParam(required = false, defaultValue = "0") Integer sort);

    @Operation(summary = "添加商品分类")
    @PostMapping("/productCategory/create")
    CommonResult<Integer> createProductCategory(@Validated @RequestBody PmsProductCategoryParam productCategoryParam);

    @Operation(summary = "修改商品分类")
    @PutMapping("/productCategory/update/{id}")
    CommonResult<Integer> updateProductCategory(@Parameter(description = "分类ID") @PathVariable Long id,
                                                       @Validated @RequestBody PmsProductCategoryParam productCategoryParam);

    @Operation(summary = "分页查询商品分类")
    @GetMapping("/list")
    CommonResult<CommonPage<PmsProductCategory>> getListProductCategory(
            @Parameter(description = "搜索关键词", example = "服装") @RequestParam(required = false) String keyword,
            @Parameter(description = "显示状态", example = "1") @RequestParam(required = false) Integer showStatus,
            @Parameter(description = "每页数量", example = "5") @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
            @Parameter(description = "当前页数", example = "1") @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum);

    @Operation(summary = "根据id获取商品分类")
    @GetMapping("/productCategory/{id}")
    CommonResult<PmsProductCategory> getItemProductCategory(@Parameter(description = "分类ID") @PathVariable Long id);

    @Operation(summary = "删除商品分类")
    @DeleteMapping("/productCategory/delete/{id}")
    CommonResult<Integer> deleteProductCategory(@Parameter(description = "分类ID") @PathVariable Long id);

    @Operation(summary = "创建商品")
    @PostMapping("/product/create")
    CommonResult<Integer> createProduct(@RequestBody PmsProductParam productParam);

    @Operation(summary = "根据商品id获取商品编辑信息")
    @GetMapping("/product/updateInfo/{id}")
    CommonResult<PmsProductResult> getUpdateInfo(@PathVariable @Parameter(description = "商品ID") Long id);

    @Operation(summary = "更新商品")
    @PostMapping("/product/update/{id}")
    CommonResult<Integer> updateProduct(@PathVariable Long id, @RequestBody PmsProductParam productParam);

    @Operation(summary = "查询商品")
    @GetMapping("/product/list")
    CommonResult<CommonPage<PmsProduct>> listProduct(PmsProductQueryParam productQueryParam,
                                                            @RequestParam(defaultValue = "5") @Parameter(description = "每页数量") Integer pageSize,
                                                            @RequestParam(defaultValue = "1") @Parameter(description = "当前页码") Integer pageNum);

    @Operation(summary = "根据商品名称或货号模糊查询")
    @GetMapping("/product/simpleList")
    CommonResult<List<PmsProduct>> getListProduct(@RequestParam(required = false) String keyword);

    @Operation(summary = "删除品牌")
    @DeleteMapping("/product/delete/{id}")
    CommonResult<Integer> deleteProduct(@PathVariable Long id);
}
