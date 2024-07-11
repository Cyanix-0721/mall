package com.mole.product.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mole.common.entity.product.PmsProduct;
import com.mole.common.dto.product.PmsPortalProductDetail;
import com.mole.common.dto.product.PmsProductCategoryNode;
import com.mole.common.entity.product.PmsProductCategory;
import com.mole.product.mapper.PmsProductCategoryMapper;
import com.mole.product.mapper.PmsProductMapper;
import com.mole.product.service.PmsPortalProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class PmsPortalProductServiceImpl extends ServiceImpl<PmsProductMapper, PmsProduct> implements PmsPortalProductService {

    @Autowired
    private PmsProductMapper productMapper;

    @Autowired
    private PmsProductCategoryMapper productCategoryMapper;

    @Override
    public List<PmsProduct> search(String keyword, Long brandId, Long productCategoryId, Integer pageNum, Integer pageSize, Integer sort) {
        LambdaQueryWrapper<PmsProduct> queryWrapper = new QueryWrapper<PmsProduct>().lambda()
                .eq(PmsProduct::getDeleteStatus, 0)
                .eq(PmsProduct::getPublishStatus, 1)
                .like(StrUtil.isNotBlank(keyword), PmsProduct::getName, keyword)
                .eq(Objects.nonNull(brandId), PmsProduct::getBrandId, brandId)
                .eq(Objects.nonNull(productCategoryId), PmsProduct::getProductCategoryId, productCategoryId);

        // 根据sort进行排序
        switch (sort) {
            case 1:
                queryWrapper.orderByDesc(PmsProduct::getId);
                break;
            case 2:
                queryWrapper.orderByDesc(PmsProduct::getSale);
                break;
            case 3:
                queryWrapper.orderByAsc(PmsProduct::getPrice);
                break;
            case 4:
                queryWrapper.orderByDesc(PmsProduct::getPrice);
                break;
            default:
                // 默认按ID升序
                queryWrapper.orderByAsc(PmsProduct::getId);
                break;
        }

        Page<PmsProduct> page = new Page<>(pageNum, pageSize);
        return productMapper.selectPage(page, queryWrapper).getRecords();
    }

    @Override
    public List<PmsProductCategoryNode> categoryTreeList() {
        List<PmsProductCategory> allCategories = productCategoryMapper.selectAll();
        return buildCategoryTree(allCategories, 0L);
    }

    private List<PmsProductCategoryNode> buildCategoryTree(List<PmsProductCategory> allCategories, Long parentId) {
        List<PmsProductCategoryNode> nodes = allCategories.stream()
                .filter(category -> category.getParentId().equals(parentId))
                .map(this::convertToNode)
                .sorted((node1, node2) -> node1.getSort().compareTo(node2.getSort()))
                .collect(Collectors.toList());

        for (PmsProductCategoryNode node : nodes) {
            node.setChildren(buildCategoryTree(allCategories, node.getId()));
        }

        return nodes;
    }

    private PmsProductCategoryNode convertToNode(PmsProductCategory category) {
        PmsProductCategoryNode node = new PmsProductCategoryNode();
        BeanUtils.copyProperties(category, node);
        return node;
    }

    @Override
    @Transactional
    public PmsPortalProductDetail detail(Long id) {
        PmsProduct product = productMapper.selectById(id);
        if (product == null) {
            return null;
        }

        PmsPortalProductDetail detail = new PmsPortalProductDetail();
        detail.setProduct(product);

        return detail;
    }
}
