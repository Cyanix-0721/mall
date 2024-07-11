package com.mole.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mole.common.dto.product.PmsProductCategoryParam;
import com.mole.common.dto.product.PmsProductCategoryWithChildrenItem;
import com.mole.common.entity.product.PmsProductCategory;
import com.mole.common.entity.product.PmsProductCategoryAttributeRelation;
import com.mole.product.mapper.PmsProductCategoryAttributeRelationMapper;
import com.mole.product.mapper.PmsProductCategoryMapper;
import com.mole.product.service.PmsProductCategoryService;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 商品分类管理Service实现类
 */
@Service
public class PmsProductCategoryServiceImpl implements PmsProductCategoryService {

    private static final Logger log = LoggerFactory.getLogger(PmsProductCategoryServiceImpl.class);

    @Autowired
    private PmsProductCategoryMapper productCategoryMapper;
    @Autowired
    private PmsProductCategoryAttributeRelationMapper productCategoryAttributeRelationMapper;

    @Override
    @Transactional
    public int create(PmsProductCategoryParam pmsProductCategoryParam) {
        PmsProductCategory productCategory = new PmsProductCategory();
        // 显式初始化productCount为0
        productCategory.setProductCount(0);
        BeanUtils.copyProperties(pmsProductCategoryParam, productCategory);
        // 设置分类级别
        //setCategoryLevel(productCategory);

        // 使用insertSelective方法仅插入非空字段
        int count = productCategoryMapper.insert(productCategory);

        // 创建筛选属性关联
        List<Long> productAttributeIdList = pmsProductCategoryParam.getProductAttributeIdList();
        if (!CollectionUtils.isEmpty(productAttributeIdList)) {
            insertProductCategoryAttributes(productCategory.getId(), productAttributeIdList);
        }

        // 返回实际影响的行数，表示操作结果
        return count;
    }

    private void setCategoryLevel(PmsProductCategory productCategory) {
        Long parentId = productCategory.getParentId();
        if (parentId == 0L) {
            productCategory.setLevel(0);
        } else {
            PmsProductCategory parentCategory = productCategoryMapper.selectById(parentId);
            if (parentCategory != null) {
                productCategory.setLevel(parentCategory.getLevel() + 1);
            } else {
                productCategory.setLevel(0);
            }
        }
    }

    private void insertProductCategoryAttributes(Long categoryId, List<Long> attributeIds) {
        List<PmsProductCategoryAttributeRelation> relations = attributeIds.stream()
                .map(attributeId -> {
                    PmsProductCategoryAttributeRelation relation = new PmsProductCategoryAttributeRelation();
                    relation.setProductCategoryId(categoryId);
                    relation.setProductAttributeId(attributeId);
                    return relation;
                }).collect(Collectors.toList());

        productCategoryAttributeRelationMapper.insertBatch(relations);
    }

    @Override
    @Transactional
    public int update(Long id, PmsProductCategoryParam pmsProductCategoryParam) {
        // 查询分类是否存在
        PmsProductCategory productCategory = productCategoryMapper.selectById(id);
        if (productCategory == null) {
            return -1; // 分类不存在
        }

        // 复制参数到实体
        BeanUtils.copyProperties(pmsProductCategoryParam, productCategory);
        // 设置分类级别
        setCategoryLevel(productCategory);

        // 更新商品分类信息
        int categoryUpdateResult = productCategoryMapper.updateById(productCategory);
        if (categoryUpdateResult <= 0) {
            return -2; // 更新分类失败
        }

        // 删除原有的属性关联
        productCategoryAttributeRelationMapper.delete(new LambdaQueryWrapper<PmsProductCategoryAttributeRelation>()
                .eq(PmsProductCategoryAttributeRelation::getProductCategoryId, id));

        // 如果有新的属性ID列表，则重新插入关联
        if (!CollectionUtils.isEmpty(pmsProductCategoryParam.getProductAttributeIdList())) {
            insertProductCategoryAttributes(id, pmsProductCategoryParam.getProductAttributeIdList());
        }

        return 1; // 更新成功
    }

    @Override
    public List<PmsProductCategory> list(String keyword, Integer showStatus, Integer pageNum, Integer pageSize) {
        Page<PmsProductCategory> page = new Page<>(pageNum, pageSize);
        QueryWrapper<PmsProductCategory> queryWrapper = new QueryWrapper<>();
        if (keyword != null) {
            queryWrapper.like("name", keyword);
        }
        if (showStatus != null) {
            queryWrapper.eq("show_status", showStatus);
        }
        queryWrapper.orderByDesc("sort");
        return productCategoryMapper.selectPage(page, queryWrapper).getRecords();
    }

    @Override
    public int delete(Long id) {
        // Implement logic to handle cascading deletion or constraints if necessary
        return productCategoryMapper.deleteById(id);
    }

    @Override
    public PmsProductCategory getItem(Long id) {
        return productCategoryMapper.selectById(id);
    }

    @Override
    @Transactional
    public int updateNavStatus(List<Long> ids, Integer navStatus) {
        // 使用LambdaUpdateWrapper进行批量更新
        LambdaUpdateWrapper<PmsProductCategory> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.in(PmsProductCategory::getId, ids).set(PmsProductCategory::getNavStatus, navStatus);

        try {
            // 执行更新操作
            int updatedRows = productCategoryMapper.update(null, updateWrapper);
            return updatedRows;
        } catch (Exception e) {
            // 异常处理逻辑
            log.error("Error updating nav status: ", e);
            throw new RuntimeException("Failed to update navigation status", e);
        }
    }

    @Override
    public int updateShowStatus(List<Long> ids, Integer showStatus) {
        // 创建LambdaUpdateWrapper来设置更新条件和更新值
        LambdaUpdateWrapper<PmsProductCategory> updateWrapper = new LambdaUpdateWrapper<>();
        // 设置需要更新的字段
        updateWrapper.set(PmsProductCategory::getShowStatus, showStatus);
        // 设置更新的条件，即ID在给定的集合中
        updateWrapper.in(PmsProductCategory::getId, ids);

        try {
            // 执行批量更新操作
            return productCategoryMapper.update(null, updateWrapper);
        } catch (Exception e) {
            // 异常处理逻辑，可根据需要进行日志记录或抛出异常
            log.error("Error updating show status for product categories with IDs: {}", ids, e);
            throw e; // 或者使用自定义异常处理
        }
    }

    @Override
    public List<PmsProductCategoryWithChildrenItem> listWithChildren() {
        List<PmsProductCategory> allCategories = productCategoryMapper.selectAll();

        // 使用Map存储所有分类，键为分类ID，值为PmsProductCategoryWithChildrenItem对象
        Map<Long, PmsProductCategoryWithChildrenItem> categoryMap = new HashMap<>();
        // 存储所有根分类（顶级分类）
        List<PmsProductCategoryWithChildrenItem> roots = new ArrayList<>();

        // 遍历所有分类，构造Map并找出根分类
        for (PmsProductCategory category : allCategories) {
            PmsProductCategoryWithChildrenItem item = new PmsProductCategoryWithChildrenItem();
            BeanUtils.copyProperties(category, item);
            categoryMap.put(category.getId(), item);

            if (category.getParentId() == null || category.getParentId() == 0L) {
                roots.add(item);
            }
        }

        // 为每个非根分类找到其父分类，并添加到父分类的children列表中
        for (PmsProductCategoryWithChildrenItem item : categoryMap.values()) {
            if (item.getParentId() != null && item.getParentId() != 0L) {
                PmsProductCategoryWithChildrenItem parent = categoryMap.get(item.getParentId());
                if (parent != null) {
                    if (parent.getChildren() == null) {
                        parent.setChildren(new ArrayList<>());
                    }
                    parent.getChildren().add(item);
                }
            }
        }

        return roots;
    }


}
