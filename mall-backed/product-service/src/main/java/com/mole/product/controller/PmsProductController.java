package com.mole.product.controller;

import com.mole.common.entity.product.api.CommonPage;
import com.mole.common.entity.product.api.CommonResult;
import com.mole.common.dto.product.PmsProductParam;
import com.mole.common.dto.product.PmsProductQueryParam;
import com.mole.common.dto.product.PmsProductResult;
import com.mole.common.entity.product.PmsProduct;
import com.mole.product.service.PmsProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品管理Controller
 */
@RestController
@Tag(name = "商品管理", description = "商品相关的API")
@RequestMapping("/product")
public class PmsProductController {

    @Autowired
    private PmsProductService productService;

    @Operation(summary = "创建商品")
    @PostMapping("/create")
    public CommonResult<Integer> createProduct(@RequestBody PmsProductParam productParam) {
        int count = productService.create(productParam);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @Operation(summary = "根据商品id获取商品编辑信息")
    @GetMapping("/updateInfo/{id}")
    public CommonResult<PmsProductResult> getUpdateInfo(@PathVariable @Parameter(description = "商品ID") Long id) {
        PmsProductResult productResult = productService.getUpdateInfo(id);
        return CommonResult.success(productResult);
    }

    @Operation(summary = "更新商品")
    @PostMapping("/update/{id}")
    public CommonResult<Integer> updateProduct(@PathVariable Long id, @RequestBody PmsProductParam productParam) {
        int count = productService.update(id, productParam);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @Operation(summary = "查询商品")
    @GetMapping("/list")
    public CommonResult<CommonPage<PmsProduct>> listProduct(PmsProductQueryParam productQueryParam,
                                                        @RequestParam(defaultValue = "5") @Parameter(description = "每页数量") Integer pageSize,
                                                        @RequestParam(defaultValue = "1") @Parameter(description = "当前页码") Integer pageNum) {
        List<PmsProduct> productList = productService.list(productQueryParam, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(productList));
    }

    @Operation(summary = "根据商品名称或货号模糊查询")
    @GetMapping("/simpleList")
    public CommonResult<List<PmsProduct>> getListProduct(@RequestParam(required = false) String keyword) {
        List<PmsProduct> productList = productService.list(keyword);
        return CommonResult.success(productList);
    }

    @Operation(summary = "批量修改审核状态")
    @PostMapping("/update/verifyStatus")
    public CommonResult<Integer> updateVerifyStatus(@RequestParam("ids") List<Long> ids,
                                                    @RequestParam("verifyStatus") @Parameter(description = "审核状态") Integer verifyStatus,
                                                    @RequestParam("detail") String detail) {
        int count = productService.updateVerifyStatus(ids, verifyStatus, detail);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @Operation(summary = "批量上下架商品")
    @PostMapping("/update/publishStatus")
    public CommonResult<Integer> updatePublishStatus(@RequestParam("ids") List<Long> ids,
                                                     @RequestParam("publishStatus") @Parameter(description = "上架状态") Integer publishStatus) {
        int count = productService.updatePublishStatus(ids, publishStatus);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @Operation(summary = "批量推荐商品")
    @PostMapping("/update/recommendStatus")
    public CommonResult<Integer> updateRecommendStatus(@RequestParam("ids") List<Long> ids,
                                                       @RequestParam("recommendStatus") @Parameter(description = "推荐状态") Integer recommendStatus) {
        int count = productService.updateRecommendStatus(ids, recommendStatus);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @Operation(summary = "批量设为新品")
    @PostMapping("/update/newStatus")
    public CommonResult<Integer> updateNewStatus(@RequestParam("ids") List<Long> ids,
                                                 @RequestParam("newStatus") @Parameter(description = "新品状态") Integer newStatus) {
        int count = productService.updateNewStatus(ids, newStatus);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @Operation(summary = "批量修改删除状态")
    @PostMapping("/update/deleteStatus")
    public CommonResult<Integer> updateDeleteStatus(@RequestParam("ids") List<Long> ids,
                                                    @RequestParam("deleteStatus") @Parameter(description = "删除状态") Integer deleteStatus) {
        int count = productService.updateDeleteStatus(ids, deleteStatus);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @Operation(summary = "删除品牌")
    @DeleteMapping("/delete/{id}")
    public CommonResult<Integer> deleteProduct(@PathVariable Long id) {
        int count = productService.deleteProduct(id);
        return count == 1 ? CommonResult.success(count) : CommonResult.failed();
    }
}
