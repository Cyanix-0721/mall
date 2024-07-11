package com.mole.marketing.service.impl;

import com.alibaba.cloud.commons.lang.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mole.marketing.entity.SmsHomeNewProduct;
import com.mole.marketing.mapper.SmsHomeNewProductMapper;
import com.mole.marketing.service.SmsHomeNewProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SmsHomeNewProductServiceImpl extends ServiceImpl<SmsHomeNewProductMapper, SmsHomeNewProduct> implements SmsHomeNewProductService {

    @Autowired
    private SmsHomeNewProductMapper homeNewProductMapper;

    @Override
    @Transactional
    public int create(List<SmsHomeNewProduct> homeNewProductList) {
        return homeNewProductMapper.insertBatch(homeNewProductList);
    }

    @Override
    public int updateSort(Long id, Integer sort) {
        SmsHomeNewProduct product = new SmsHomeNewProduct();
        product.setId(id);
        product.setSort(sort);
        return homeNewProductMapper.updateById(product);
    }

    @Override
    public int delete(List<Long> ids) {
        return homeNewProductMapper.deleteBatchIds(ids);
    }

    @Override
    public int updateRecommendStatus(List<Long> ids, Integer recommendStatus) {
        UpdateWrapper<SmsHomeNewProduct> updateWrapper = new UpdateWrapper<>();
        updateWrapper.in("id", ids).set("recommend_status", recommendStatus);
        return homeNewProductMapper.update(null, updateWrapper);
    }

    @Override
    public IPage<SmsHomeNewProduct> list(String productName, Integer recommendStatus, Integer pageSize, Integer pageNum) {
        Page<SmsHomeNewProduct> page = new Page<>(pageNum, pageSize);
        return homeNewProductMapper.selectPage(page, new QueryWrapper<SmsHomeNewProduct>()
                .like(StringUtils.isNotBlank(productName), "product_name", productName)
                .eq(recommendStatus != null, "recommend_status", recommendStatus));
    }
}
