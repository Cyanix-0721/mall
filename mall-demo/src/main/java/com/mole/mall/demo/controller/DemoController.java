package com.mole.mall.demo.controller;

import com.mole.mall.common.api.CommonPage;
import com.mole.mall.common.api.CommonResult;
import com.mole.mall.demo.dto.PmsBrandDto;
import com.mole.mall.demo.service.DemoService;
import com.mole.mall.mbg.pojo.PmsBrand;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品管理示例Controller
 */
@Tag(name = "DemoController", description = "商品管理示例接口")
@Slf4j
@Controller
public class DemoController {
	@Autowired
	private DemoService demoService;

	/**
	 * 获取全部品牌列表
	 *
	 * @return 品牌列表
	 */
	@Operation(summary = "获取全部品牌列表")
	@RequestMapping(value = "/brand/listAll", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<List<PmsBrand>> getBrandList() {
		return CommonResult.success(demoService.listAllBrand());
	}

	/**
	 * 添加品牌
	 *
	 * @param pmsBrand 品牌数据传输对象
	 * @return 操作结果
	 */
	@Operation(summary = "添加品牌")
	@RequestMapping(value = "/brand/create", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<?> createBrand(@Validated @RequestBody PmsBrandDto pmsBrand) {
		CommonResult<?> commonResult;
		int count = demoService.createBrand(pmsBrand);
		if (count == 1) {
			commonResult = CommonResult.success(pmsBrand);
			log.debug("createBrand success:{}", pmsBrand);
		} else {
			commonResult = CommonResult.failed("操作失败");
			log.debug("createBrand failed:{}", pmsBrand);
		}
		return commonResult;
	}

	/**
	 * 更新品牌
	 *
	 * @param id          品牌ID
	 * @param pmsBrandDto 品牌数据传输对象
	 * @return 操作结果
	 */
	@Operation(summary = "更新品牌")
	@RequestMapping(value = "/brand/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<?> updateBrand(@PathVariable("id") Long id, @Validated @RequestBody PmsBrandDto pmsBrandDto) {
		CommonResult<?> commonResult;
		int count = demoService.updateBrand(id, pmsBrandDto);
		if (count == 1) {
			commonResult = CommonResult.success(pmsBrandDto);
			log.debug("updateBrand success:{}", pmsBrandDto);
		} else {
			commonResult = CommonResult.failed("操作失败");
			log.debug("updateBrand failed:{}", pmsBrandDto);
		}
		return commonResult;
	}

	/**
	 * 删除品牌
	 *
	 * @param id 品牌ID
	 * @return 操作结果
	 */
	@Operation(summary = "删除品牌")
	@RequestMapping(value = "/brand/delete/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<?> deleteBrand(@PathVariable("id") Long id) {
		int count = demoService.deleteBrand(id);
		if (count == 1) {
			log.debug("deleteBrand success :id={}", id);
			return CommonResult.success(null);
		} else {
			log.debug("deleteBrand failed :id={}", id);
			return CommonResult.failed("操作失败");
		}
	}

	/**
	 * 分页获取品牌列表
	 *
	 * @param pageNum  页码
	 * @param pageSize 每页数量
	 * @return 品牌列表
	 */
	@Operation(summary = "分页获取品牌列表")
	@RequestMapping(value = "/brand/list", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<CommonPage<PmsBrand>> listBrand(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
	                                                    @RequestParam(value = "pageSize", defaultValue = "3") Integer pageSize) {
		List<PmsBrand> brandList = demoService.listBrand(pageNum, pageSize);
		return CommonResult.success(CommonPage.restPage(brandList));
	}

	/**
	 * 根据编号查询品牌信息
	 *
	 * @param id 品牌ID
	 * @return 品牌信息
	 */
	@Operation(summary = "根据编号查询品牌信息")
	@RequestMapping(value = "/brand/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<PmsBrand> brand(@PathVariable("id") Long id) {
		return CommonResult.success(demoService.getBrand(id));
	}
}