package com.mole.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mole.common.dto.product.PmsProductAttributeCategoryItem;
import com.mole.product.mapper.PmsProductAttributeMapper;
import com.mole.common.entity.product.PmsProductAttribute;
import com.mole.product.mapper.PmsProductAttributeCategoryMapper;
import com.mole.common.entity.product.PmsProductAttributeCategory;
import com.mole.product.service.PmsProductAttributeCategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 商品属性分类管理Service实现类
 */
@Service
public class PmsProductAttributeCategoryServiceImpl extends ServiceImpl<PmsProductAttributeCategoryMapper, PmsProductAttributeCategory> implements PmsProductAttributeCategoryService {

    @Autowired
    private PmsProductAttributeCategoryMapper pmsProductAttributeCategoryMapper;

    @Autowired
    private PmsProductAttributeMapper pmsProductAttributeMapper;

    @Override
    @Transactional
    public int create(String name) {
        PmsProductAttributeCategory category = new PmsProductAttributeCategory();
        category.setName(name);
        return pmsProductAttributeCategoryMapper.insert(category);
    }

    @Override
    @Transactional
    public int update(Long id, String name) {
        PmsProductAttributeCategory category = new PmsProductAttributeCategory();
        category.setId(id);
        category.setName(name);
        return pmsProductAttributeCategoryMapper.updateById(category);
    }

    @Override
    @Transactional
    public int delete(Long id) {
        return pmsProductAttributeCategoryMapper.deleteById(id);
    }

    @Override
    public PmsProductAttributeCategory getItem(Long id) {
        return pmsProductAttributeCategoryMapper.selectById(id);
    }

    @Override
    public List<PmsProductAttributeCategory> getList(Integer pageSize, Integer pageNum) {
        IPage<PmsProductAttributeCategory> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<PmsProductAttributeCategory> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByAsc(PmsProductAttributeCategory::getName);
        return pmsProductAttributeCategoryMapper.selectPage(page, queryWrapper).getRecords();
    }

    @Override
    public List<PmsProductAttributeCategoryItem> getListWithAttr() {
        List<PmsProductAttributeCategory> categories = baseMapper.selectList(null);

        List<PmsProductAttributeCategoryItem> categoryItems = new ArrayList<>();
        for (PmsProductAttributeCategory category : categories) {
            PmsProductAttributeCategoryItem item = new PmsProductAttributeCategoryItem();
            BeanUtils.copyProperties(category, item);

            // 使用自定义的Mapper方法来获取属性列表
            List<PmsProductAttribute> attributes = pmsProductAttributeMapper.selectAttributesByCategoryId(category.getId());
            item.setProductAttributeList(attributes);

            categoryItems.add(item);
        }

        return categoryItems;
    }

}
