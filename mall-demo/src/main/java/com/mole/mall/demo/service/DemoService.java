package com.mole.mall.demo.service;

import com.mole.mall.demo.dto.PmsBrandDto;
import com.mole.mall.mbg.pojo.PmsBrand;

import java.util.List;

/**
 * DemoService接口
 */
public interface DemoService {
	/**
	 * 获取所有品牌列表
	 *
	 * @return 品牌列表
	 */
	List<PmsBrand> listAllBrand();

	/**
	 * 创建品牌
	 *
	 * @param pmsBrandDto
	 * 		品牌数据传输对象
	 * @return 创建结果
	 */
	int createBrand(PmsBrandDto pmsBrandDto);

	/**
	 * 更新品牌
	 *
	 * @param id
	 * 		品牌ID
	 * @param pmsBrandDto
	 * 		品牌数据传输对象
	 * @return 更新结果
	 */
	int updateBrand(Long id, PmsBrandDto pmsBrandDto);

	/**
	 * 删除品牌
	 *
	 * @param id
	 * 		品牌ID
	 * @return 删除结果
	 */
	int deleteBrand(Long id);

	/**
	 * 分页获取品牌列表
	 *
	 * @param pageNum
	 * 		页码
	 * @param pageSize
	 * 		每页数量
	 * @return 品牌列表
	 */
	List<PmsBrand> listBrand(int pageNum, int pageSize);

	/**
	 * 获取品牌详情
	 *
	 * @param id
	 * 		品牌ID
	 * @return 品牌详情
	 */
	PmsBrand getBrand(Long id);
}