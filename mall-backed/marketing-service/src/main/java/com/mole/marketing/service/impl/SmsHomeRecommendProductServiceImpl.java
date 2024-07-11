package com.mole.marketing.service.impl;

import com.alibaba.nacos.common.utils.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mole.marketing.entity.SmsHomeRecommendProduct;
import com.mole.marketing.mapper.SmsHomeRecommendProductMapper;
import com.mole.marketing.service.SmsHomeRecommendProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SmsHomeRecommendProductServiceImpl extends ServiceImpl<SmsHomeRecommendProductMapper, SmsHomeRecommendProduct> implements SmsHomeRecommendProductService {

    @Autowired
    private SmsHomeRecommendProductMapper recommendProductMapper;

    @Override
    @Transactional
    public int create(List<SmsHomeRecommendProduct> homeRecommendProductList) {
        return recommendProductMapper.insertBatchSomeColumn(homeRecommendProductList);
    }

    @Override
    public int updateSort(Long id, Integer sort) {
        SmsHomeRecommendProduct product = new SmsHomeRecommendProduct();
        product.setId(id);
        product.setSort(sort);
        return recommendProductMapper.updateById(product);
    }

    @Override
    public int delete(List<Long> ids) {
        return recommendProductMapper.deleteBatchIds(ids);
    }

    @Override
    public int updateRecommendStatus(List<Long> ids, Integer recommendStatus) {
        UpdateWrapper<SmsHomeRecommendProduct> updateWrapper = new UpdateWrapper<>();
        updateWrapper.in("id", ids).set("recommend_status", recommendStatus);
        return recommendProductMapper.update(null, updateWrapper);
    }

    @Override
    public IPage<SmsHomeRecommendProduct> list(String productName, Integer recommendStatus, Integer pageSize, Integer pageNum) {
        Page<SmsHomeRecommendProduct> page = new Page<>(pageNum, pageSize);
        return recommendProductMapper.selectPage(page, new QueryWrapper<SmsHomeRecommendProduct>()
                .like(StringUtils.isNotBlank(productName), "product_name", productName)
                .eq(recommendStatus != null, "recommend_status", recommendStatus));
    }
}
