package com.mole.protal.service.impl;

import com.alibaba.cloud.commons.lang.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mole.common.entity.portal.SmsHomeNewProduct;
import com.mole.protal.mapper.SmsHomeNewProductMapper;
import com.mole.protal.service.SmsHomeNewProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class SmsHomeNewProductServiceImpl extends ServiceImpl<SmsHomeNewProductMapper, SmsHomeNewProduct> implements SmsHomeNewProductService {

    @Autowired
    private SmsHomeNewProductMapper smsHomeNewProductMapper;

    @Override
    @Transactional
    public int create(List<SmsHomeNewProduct> homeNewProductList) {
        return smsHomeNewProductMapper.batchInsert(homeNewProductList);
    }

    @Override
    public int updateSort(Long id, Integer sort) {
        SmsHomeNewProduct product = new SmsHomeNewProduct();
        product.setId(id);
        product.setSort(sort);
        return smsHomeNewProductMapper.updateById(product);
    }

    @Override
    public int delete(Long id) {
        return smsHomeNewProductMapper.deleteById(id);
    }

    @Override
    public int updateRecommendStatus(List<Long> ids, Integer recommendStatus) {
        return smsHomeNewProductMapper.updateRecommendStatus(ids, recommendStatus);
    }

    @Override
    public List<SmsHomeNewProduct> list(String productName, Integer recommendStatus, Integer pageSize, Integer pageNum) {
        // Calculate the offset based on page size and number
        Integer offset = (pageNum - 1) * pageSize;

        LambdaQueryWrapper<SmsHomeNewProduct> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(productName), SmsHomeNewProduct::getProductName, productName)
                .eq(recommendStatus != null, SmsHomeNewProduct::getRecommendStatus, recommendStatus)
                .orderByAsc(SmsHomeNewProduct::getSort);

        // MyBatis-Plus pagination method
        IPage<SmsHomeNewProduct> page = new Page<>(pageNum, pageSize);
        page = smsHomeNewProductMapper.selectPage(page, queryWrapper);

        return page.getRecords();
    }


    @Override
    public int update(Long id, SmsHomeNewProduct homeNewProduct) {
        // 确保id不为空且待更新的对象非空
        if (Objects.isNull(id) || Objects.isNull(homeNewProduct)) {
            throw new IllegalArgumentException("Id or homeNewProduct cannot be null.");
        }

        // 设置需要更新的实体的id，确保与参数id一致
        homeNewProduct.setId(id);

        // 使用MyBatis Plus的updateById方法直接更新，无需手动编写SQL
        return smsHomeNewProductMapper.updateById(homeNewProduct);
    }

}
