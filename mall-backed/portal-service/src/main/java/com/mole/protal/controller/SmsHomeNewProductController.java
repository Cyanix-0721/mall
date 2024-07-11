package com.mole.protal.controller;

import com.mole.protal.api.CommonPage;
import com.mole.protal.api.CommonResult;
import com.mole.common.entity.portal.SmsHomeNewProduct;
import com.mole.protal.service.SmsHomeNewProductService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "SmsHomeNewProductController", description = "首页新品管理")
@RestController // 更改为@RestController以简化@ResponseBody的重复使用
@RequestMapping("/home/newProduct")
public class SmsHomeNewProductController {

    @Autowired
    private SmsHomeNewProductService homeNewProductService;

    @ApiOperation("添加首页新品")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult createNewproduct(@RequestBody List<SmsHomeNewProduct> homeNewProductList) {
        int count = homeNewProductService.create(homeNewProductList);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("修改首页新品排序")
    @RequestMapping(value = "/update/sort/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateSortNewproduct(@PathVariable Long id, Integer sort) {
        int count = homeNewProductService.updateSort(id, sort);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }


    @Operation(summary = "根据ID删除首页新品")
    @DeleteMapping("/delete/{id}")
    public CommonResult deleteByIdNewproduct(
            @Parameter(description = "要删除的首页新品ID", required = true)
            @PathVariable Long id) {
        int count = homeNewProductService.delete(id);
        return CommonResult.success(count > 0);
    }
    @Operation(summary = "批量修改首页新品状态")
    @PostMapping("/update/recommendStatus")
    public CommonResult updateRecommendStatusNewproduct(
            @Parameter(description = "新品ID列表", required = true)
            @RequestParam List<Long> ids,
            @Parameter(description = "推荐状态", required = true)
            @RequestParam Integer recommendStatus) {
        int count = homeNewProductService.updateRecommendStatus(ids, recommendStatus);
        return CommonResult.success(count > 0);
    }

    @Operation(summary = "分页查询首页新品")
    @GetMapping("/list")
    public CommonResult<CommonPage<SmsHomeNewProduct>> listNewproduct(
            @Parameter(description = "产品名称", required = false)
            @RequestParam(required = false) String productName,
            @Parameter(description = "推荐状态", required = false)
            @RequestParam(required = false) Integer recommendStatus,
            @Parameter(description = "每页数量", example = "5")
            @RequestParam(defaultValue = "5") Integer pageSize,
            @Parameter(description = "当前页数", example = "1")
            @RequestParam(defaultValue = "1") Integer pageNum) {
        List<SmsHomeNewProduct> homeNewProductList = homeNewProductService.list(productName, recommendStatus, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(homeNewProductList));

    }

    /**
     * 更新首页新品信息
     * @param id 新品ID
     * @param homeNewProduct 需要更新的首页新品信息
     * @return 更新结果，通常为受影响的行数
     */
    @PutMapping("update/{id}")
    public CommonResult<Integer> updateHomeNewProduct(@PathVariable Long id, @RequestBody SmsHomeNewProduct homeNewProduct) {
        int updateResult = homeNewProductService.update(id, homeNewProduct);
        if (updateResult > 0) {
            return CommonResult.success(updateResult);
        } else {
            // 根据实际业务逻辑决定返回状态，这里示例为未找到或更新失败
            return CommonResult.failed();
        }
    }

}