package com.mole.protal.controller;

import com.mole.protal.api.CommonPage;
import com.mole.protal.api.CommonResult;
import com.mole.common.entity.portal.SmsHomeBrand;
import com.mole.protal.service.SmsHomeBrandService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 首页品牌管理Controller

 */
@RestController
@Tag(name = "SmsHomeBrandController", description = "首页品牌管理")
@RequestMapping("/home/brand")
public class SmsHomeBrandController {
    @Autowired
    private SmsHomeBrandService homeBrandService;

    @ApiOperation("添加首页推荐品牌")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult createBrand(@RequestBody List<SmsHomeBrand> homeBrandList) {
        int count = homeBrandService.create(homeBrandList);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("修改推荐品牌排序")
    @RequestMapping(value = "/update/sort/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateSortBrand(@PathVariable Long id, Integer sort) {
        int count = homeBrandService.updateSort(id, sort);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @Operation(summary = "根据ID删除首页品牌")
    @DeleteMapping("/delete/{id}")
    public CommonResult deleteByIdBrand(
            @Parameter(description = "要删除的首页品牌ID", required = true)
            @PathVariable Long id) {
        int count = homeBrandService.delete(id);
        return CommonResult.success(count > 0);
    }

    @ApiOperation("批量修改推荐品牌状态")
    @RequestMapping(value = "/update/recommendStatus", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateRecommendStatusBrand(@RequestParam("ids") List<Long> ids, @RequestParam Integer recommendStatus) {
        int count = homeBrandService.updateRecommendStatus(ids, recommendStatus);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("分页查询推荐品牌")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<SmsHomeBrand>> listBrand(@RequestParam(value = "brandName", required = false) String brandName,
                                                       @RequestParam(value = "recommendStatus", required = false) Integer recommendStatus,
                                                       @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                       @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<SmsHomeBrand> homeBrandList = homeBrandService.list(brandName, recommendStatus, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(homeBrandList));
    }
    /**
     * 更新首页推荐品牌信息
     * @param id 品牌ID
     * @param smsHomeBrand 需要更新的品牌信息
     * @return 更新结果，通常为受影响的行数
     */
    @PutMapping("/update/{id}")
    public CommonResult<Integer> updateHomeBrand(@PathVariable Long id, @RequestBody SmsHomeBrand smsHomeBrand) {
        // 参数校验逻辑已在Service层实现
        int updateResult = homeBrandService.update(id, smsHomeBrand);

        if (updateResult > 0) {
            return CommonResult.success(updateResult);
        } else {
            // 返回具体的错误信息可能更有帮助，这里仅作为示例
            return CommonResult.failed("更新品牌信息失败，可能是ID不存在或输入信息有误。");
        }
    }
}
