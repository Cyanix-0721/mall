package com.mole.product.controller;

import com.mole.common.entity.product.api.CommonPage;
import com.mole.common.entity.product.api.CommonResult;
import com.mole.common.dto.product.PmsBrandParam;
import com.mole.common.entity.product.PmsBrand;
import com.mole.product.service.PmsBrandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "PmsBrandController", description = "商品品牌管理")
@RequestMapping("/brand")
public class PmsBrandController {

    @Autowired
    private PmsBrandService brandService;

    @Operation(summary = "获取全部品牌列表")
    @GetMapping("/listAll")
    public CommonResult<List<PmsBrand>> getList() {
        return CommonResult.success(brandService.listAllBrand());
    }

    @Operation(summary = "添加品牌")
    @PostMapping("/create")
    public CommonResult<Integer> createBrand(@Validated @RequestBody PmsBrandParam pmsBrand) {
        int count = brandService.createBrand(pmsBrand);
        return count == 1 ? CommonResult.success(count) : CommonResult.failed();
    }

    @Operation(summary = "更新品牌")
    @PutMapping("/update/{id}")
    public CommonResult<Integer> updateBrand(@PathVariable Long id, @Validated @RequestBody PmsBrandParam pmsBrandParam) {
        int count = brandService.updateBrand(id, pmsBrandParam);
        return count == 1 ? CommonResult.success(count) : CommonResult.failed();
    }

    @Operation(summary = "删除品牌")
    @DeleteMapping("/delete/{id}")
    public CommonResult<Integer> deleteBrand(@PathVariable Long id) {
        int count = brandService.deleteBrand(id);
        return count == 1 ? CommonResult.success(count) : CommonResult.failed();
    }

    @Operation(summary = "根据品牌名称分页获取品牌列表")
    @GetMapping("/list")
    public CommonResult<CommonPage<PmsBrand>> getListBrand(
            @Parameter(description = "搜索关键词", example = "Nike") @RequestParam(required = false) String keyword,
            @Parameter(description = "显示状态", example = "1") @RequestParam(required = false) Integer showStatus,
            @Parameter(description = "页码", example = "1") @RequestParam(defaultValue = "1") Integer pageNum,
            @Parameter(description = "每页数量", example = "5") @RequestParam(defaultValue = "5") Integer pageSize) {
        List<PmsBrand> brandList = brandService.listBrand(keyword, showStatus, pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(brandList));
    }

    @Operation(summary = "根据编号查询品牌信息")
    @GetMapping("/{id}")
    public CommonResult<PmsBrand> getItemBrand(@PathVariable Long id) {
        return CommonResult.success(brandService.getBrand(id));
    }

    @Operation(summary = "批量删除品牌")
    @PostMapping("/delete/batch")
    public CommonResult<Integer> deleteBatch(@RequestBody List<Long> ids) {
        int count = brandService.deleteBrand(ids);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @Operation(summary = "批量更新显示状态")
    @PostMapping("/update/showStatus")
    public CommonResult<Integer> updateShowStatus(@RequestBody List<Long> ids, @RequestParam Integer showStatus) {
        int count = brandService.updateShowStatus(ids, showStatus);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @Operation(summary = "批量更新厂家制造商状态")
    @PostMapping("/update/factoryStatus")
    public CommonResult<Integer> updateFactoryStatus(@RequestBody List<Long> ids, @RequestParam Integer factoryStatus) {
        int count = brandService.updateFactoryStatus(ids, factoryStatus);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }
}
