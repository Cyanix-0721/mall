package com.mole.protal.client;

import com.mole.protal.api.CommonPage;
import com.mole.protal.api.CommonResult;
import com.mole.common.entity.portal.SmsHomeBrand;
import com.mole.common.entity.portal.SmsHomeNewProduct;
import com.mole.common.entity.portal.SmsHomeRecommendSubject;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("portal-service")
public interface portal {
    @ApiOperation("添加首页推荐品牌")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult createBrand(@RequestBody List<SmsHomeBrand> homeBrandList);
    @ApiOperation("修改推荐品牌排序")
    @ResponseBody
    @RequestMapping(value = "/home/brand/update/sort/{id}", method = RequestMethod.POST)
    CommonResult updateSortBrand(@PathVariable Long id, Integer sort);
    @Operation(summary = "根据ID删除首页品牌")
    @ResponseBody
    @DeleteMapping("/home/brand/delete/{id}")
   CommonResult deleteByIdBrand(@Parameter(description = "要删除的首页品牌ID", required = true) @PathVariable Long id);
    @ApiOperation("批量修改推荐品牌状态")
    @ResponseBody
    @RequestMapping(value = "/home/brand/update/recommendStatus", method = RequestMethod.POST)
    CommonResult updateRecommendStatusBrand(@RequestParam("ids") List<Long> ids, @RequestParam Integer recommendStatus);
    @ApiOperation("分页查询推荐品牌")
    @ResponseBody
    @RequestMapping(value = "/home/brand/list", method = RequestMethod.GET)
    CommonResult<CommonPage<SmsHomeBrand>> listBrand(@RequestParam(value = "brandName", required = false) String brandName,
                                                       @RequestParam(value = "recommendStatus", required = false) Integer recommendStatus,
                                                       @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                       @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum);
    @PutMapping("/home/brand/update/{id}")
    CommonResult<Integer> updateHomeBrand(@PathVariable Long id, @RequestBody SmsHomeBrand smsHomeBrand);

    @ApiOperation("添加首页新品")
    @RequestMapping(value = "/home/newProduct/create", method = RequestMethod.POST)
    @ResponseBody
    CommonResult createNewproductNewproduct(@RequestBody List<SmsHomeNewProduct> homeNewProductList);
    @ApiOperation("修改首页新品排序")
    @RequestMapping(value = "/home/newProduct/update/sort/{id}", method = RequestMethod.POST)
    @ResponseBody
    CommonResult updateSortNewproduct(@PathVariable Long id, Integer sort);
    @Operation(summary = "批量修改首页新品状态")
    @PostMapping("/home/newProduct/update/recommendStatus")
    CommonResult updateRecommendStatus(
            @Parameter(description = "新品ID列表", required = true)
            @RequestParam List<Long> ids,
            @Parameter(description = "推荐状态", required = true)
            @RequestParam Integer recommendStatus);
    @Operation(summary = "分页查询首页新品")
    @GetMapping("/home/newProduct/list")
    CommonResult<CommonPage<SmsHomeNewProduct>> listNewproduct(
            @Parameter(description = "产品名称", required = false)
            @RequestParam(required = false) String productName,
            @Parameter(description = "推荐状态", required = false)
            @RequestParam(required = false) Integer recommendStatus,
            @Parameter(description = "每页数量", example = "5")
            @RequestParam(defaultValue = "5") Integer pageSize,
            @Parameter(description = "当前页数", example = "1")
            @RequestParam(defaultValue = "1") Integer pageNum);
    @PutMapping("update/{id}")
     CommonResult<Integer> updateHomeNewProduct(@PathVariable Long id, @RequestBody SmsHomeNewProduct homeNewProduct);

    @ApiOperation("添加首页推荐专题")
    @RequestMapping(value = "/home/recommendSubject/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult createRecommendsubject(@RequestBody List<SmsHomeRecommendSubject> homeRecommendSubjectList);
    @ApiOperation("修改推荐排序")
    @RequestMapping(value = "/home/recommendSubject/update/sort/{id}", method = RequestMethod.POST)
    @ResponseBody
     CommonResult updateSortRecommendsubject(@PathVariable Long id, Integer sort);
    @ApiOperation("批量删除推荐")
    @RequestMapping(value = "/home/recommendSubject/delete", method = RequestMethod.POST)
    @ResponseBody
     CommonResult deleteRecommendsubject(@RequestParam("ids") List<Long> ids);
    @Operation(summary = "根据ID删除专题新品")
    @DeleteMapping("/home/recommendSubject/delete/{id}")
     CommonResult deleteByIdRecommendsubject(
            @Parameter(description = "要删除的首页专题ID", required = true)
            @PathVariable Long id);
    @ApiOperation("分页查询推荐")
    @RequestMapping(value = "/home/recommendSubject/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<SmsHomeRecommendSubject>> listRecommendsubject(@RequestParam(value = "subjectName", required = false) String subjectName,
                                                                  @RequestParam(value = "recommendStatus", required = false) Integer recommendStatus,
                                                                  @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                                  @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum);
    @PutMapping("/home/recommendSubject/update/{id}")
    @ResponseBody
     CommonResult<Integer> updateHomeRecommendSubject(@PathVariable Long id, @RequestBody SmsHomeRecommendSubject smsHomeRecommendSubject);
}
