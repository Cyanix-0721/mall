package com.mole.mall.demo.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mole.mall.demo.dto.PmsBrandDto;
import com.mole.mall.demo.service.DemoService;
import com.mole.mall.mbg.mapper.PmsBrandMapper;
import com.mole.mall.mbg.pojo.PmsBrand;
import com.mole.mall.mbg.pojo.PmsBrandExample;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * DemoService实现类
 */
@Service
public class DemoServiceImpl implements DemoService {

	@Autowired
	private PmsBrandMapper brandMapper;

	/**
	 * 获取所有品牌列表
	 *
	 * @return 品牌列表
	 */
	@Override
	public List<PmsBrand> listAllBrand() {
		return brandMapper.selectByExample(new PmsBrandExample());
	}

	/**
	 * 创建品牌
	 *
	 * @param pmsBrandDto 品牌数据传输对象
	 * @return 创建结果
	 */
	@Override
	public int createBrand(PmsBrandDto pmsBrandDto) {
		PmsBrand pmsBrand = new PmsBrand();
		BeanUtils.copyProperties(pmsBrandDto, pmsBrand);
		return brandMapper.insertSelective(pmsBrand);
	}

	/**
	 * 更新品牌
	 *
	 * @param id          品牌ID
	 * @param pmsBrandDto 品牌数据传输对象
	 * @return 更新结果
	 */
	@Override
	public int updateBrand(Long id, PmsBrandDto pmsBrandDto) {
		PmsBrand pmsBrand = new PmsBrand();
		BeanUtils.copyProperties(pmsBrandDto, pmsBrand);
		pmsBrand.setId(id);
		return brandMapper.updateByPrimaryKeySelective(pmsBrand);
	}

	/**
	 * 删除品牌
	 *
	 * @param id 品牌ID
	 * @return 删除结果
	 */
	@Override
	public int deleteBrand(Long id) {
		return brandMapper.deleteByPrimaryKey(id);
	}

	/**
	 * 分页获取品牌列表
	 *
	 * @param pageNum  页码
	 * @param pageSize 每页数量
	 * @return 品牌列表
	 */
	@Override
	public List<PmsBrand> listBrand(int pageNum, int pageSize) {
		try (Page<Object> page = PageHelper.startPage(pageNum, pageSize)) {
			return brandMapper.selectByExample(new PmsBrandExample());
		}
	}

	/**
	 * 获取品牌详情
	 *
	 * @param id 品牌ID
	 * @return 品牌详情
	 */
	@Override
	public PmsBrand getBrand(Long id) {
		return brandMapper.selectByPrimaryKey(id);
	}
}