package com.mole.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mole.common.dto.product.PmsProductAttributeParam;
import com.mole.common.dto.product.ProductAttrInfo;
import com.mole.common.entity.product.PmsProductAttribute;
import com.mole.common.entity.product.PmsProductAttributeCategory;
import com.mole.product.mapper.PmsProductAttributeCategoryMapper;
import com.mole.product.mapper.PmsProductAttributeMapper;
import com.mole.product.service.PmsProductAttributeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 商品属性管理Service实现类
 */
@Service
public class PmsProductAttributeServiceImpl implements PmsProductAttributeService {

    @Autowired
    private PmsProductAttributeMapper productAttributeMapper;
    @Autowired
    private PmsProductAttributeCategoryMapper productAttributeCategoryMapper;

    @Override
    public List<PmsProductAttribute> getList(Long cid, Integer type, Integer pageSize, Integer pageNum) {
        Page<PmsProductAttribute> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<PmsProductAttribute> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(PmsProductAttribute::getProductAttributeCategoryId, cid)
                .eq(PmsProductAttribute::getType, type)
                .orderByAsc(PmsProductAttribute::getSort);
        return productAttributeMapper.selectPage(page, queryWrapper).getRecords();
    }

    @Override
    @Transactional
    public int create(PmsProductAttributeParam pmsProductAttributeParam) {
        PmsProductAttribute attribute = new PmsProductAttribute();
        BeanUtils.copyProperties(pmsProductAttributeParam, attribute);

        // 插入新属性
        int insertResult = productAttributeMapper.insert(attribute);

        if (insertResult > 0) {
            // 获取属性分类ID
            Long productAttributeCategoryId = pmsProductAttributeParam.getProductAttributeCategoryId();

            // 查询商品属性分类
            PmsProductAttributeCategory category = productAttributeCategoryMapper.selectById(productAttributeCategoryId);
            if (category == null) {
                throw new RuntimeException("商品属性分类不存在，ID: " + productAttributeCategoryId);
            }

            // 根据属性类型更新计数
            Integer type = pmsProductAttributeParam.getType();
            if (type.equals(0)) { // 规格
                category.setAttributeCount(category.getAttributeCount() + 1);
            } else if (type.equals(1)) { // 参数
                category.setParamCount(category.getParamCount() + 1);
            }

            // 更新商品属性分类
            productAttributeCategoryMapper.updateById(category);
        }

        return insertResult;
    }

    @Override
    public int update(Long id, PmsProductAttributeParam productAttributeParam) {
        PmsProductAttribute attribute = new PmsProductAttribute();
        BeanUtils.copyProperties(productAttributeParam, attribute);
        attribute.setId(id);
        return productAttributeMapper.updateById(attribute);
    }

    @Override
    public PmsProductAttribute getItem(Long id) {
        return productAttributeMapper.selectById(id);
    }

    @Override
    public int delete(List<Long> ids) {
        // 直接使用deleteBatchIds进行批量删除
        int deletedCount = productAttributeMapper.deleteBatchIds(ids);

        // 如果有属性被删除，则更新分类计数
        if (deletedCount > 0) {
            // 准备一个Map来存储每个分类ID及其对应的属性和参数减少数量
            Map<Long, Integer[]> categoryCountChanges = new HashMap<>();

            // 遍历被删除的属性ID，统计每个分类受影响的数量
            for (Long id : ids) {
                PmsProductAttribute attribute = productAttributeMapper.selectById(id);
                if (attribute != null) {
                    Long categoryId = attribute.getProductAttributeCategoryId();
                    Integer[] changes = categoryCountChanges.computeIfAbsent(categoryId, k -> new Integer[]{0, 0});

                    if (attribute.getType() == 0) {
                        changes[0] += 1; // 属性计数减一
                    } else if (attribute.getType() == 1) {
                        changes[1] += 1; // 参数计数减一
                    }
                }
            }

            // 根据计算结果更新每个分类的计数
            for (Map.Entry<Long, Integer[]> entry : categoryCountChanges.entrySet()) {
                Long categoryId = entry.getKey();
                Integer[] changes = entry.getValue();
                PmsProductAttributeCategory category = productAttributeCategoryMapper.selectById(categoryId);
                if (category != null) {
                    category.setAttributeCount(Math.max(category.getAttributeCount() - changes[0], 0));
                    category.setParamCount(Math.max(category.getParamCount() - changes[1], 0));
                    productAttributeCategoryMapper.updateById(category);
                }
            }
        }

        return deletedCount;
    }

    // 辅助方法用于更新分类的计数
    private void updateCategoryCounts(Long categoryId, int delta, int type) {
        PmsProductAttributeCategory category = productAttributeCategoryMapper.selectById(categoryId);
        if (category != null) {
            if (type == 0) {
                int newAttributeCount = Math.max(category.getAttributeCount() - delta, 0);
                category.setAttributeCount(newAttributeCount);
            } else if (type == 1) {
                int newParamCount = Math.max(category.getParamCount() - delta, 0);
                category.setParamCount(newParamCount);
            }
            productAttributeCategoryMapper.updateById(category);
        }
    }

    @Override
    public List<ProductAttrInfo> getProductAttrInfo(Long productCategoryId) {
        List<PmsProductAttribute> attributes = productAttributeMapper.selectList(
                Wrappers.lambdaQuery(PmsProductAttribute.class)
                        .eq(PmsProductAttribute::getProductAttributeCategoryId, productCategoryId));

        List<ProductAttrInfo> attrInfos = attributes.stream().map(attr -> {
            ProductAttrInfo info = new ProductAttrInfo();
            info.setAttributeId(attr.getId());
            info.setAttributeCategoryId(attr.getProductAttributeCategoryId()); // 注意这里根据实际字段名调整
            return info;
        }).collect(Collectors.toList());

        return attrInfos;
    }

}
