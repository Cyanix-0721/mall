package com.mole.protal.service.impl;

import com.alibaba.cloud.commons.lang.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mole.common.entity.portal.SmsHomeRecommendProduct;
import com.mole.protal.mapper.SmsHomeRecommendProductMapper;
import com.mole.protal.service.SmsHomeRecommendProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class SmsHomeRecommendProductServiceImpl extends ServiceImpl<SmsHomeRecommendProductMapper, SmsHomeRecommendProduct> implements SmsHomeRecommendProductService {

    @Autowired
    private SmsHomeRecommendProductMapper smsHomeRecommendProductMapper;

    @Override
    @Transactional
    public int create(List<SmsHomeRecommendProduct> homeRecommendProductList) {
        return smsHomeRecommendProductMapper.batchInsert(homeRecommendProductList);
    }

    @Override
    public int updateSort(Long id, Integer sort) {
        SmsHomeRecommendProduct product = new SmsHomeRecommendProduct();
        product.setId(id);
        product.setSort(sort);
        return smsHomeRecommendProductMapper.updateById(product);
    }

    @Override
    public int delete(List<Long> ids) {
        return smsHomeRecommendProductMapper.deleteByIds(ids);
    }

    @Override
    public int updateRecommendStatus(List<Long> ids, Integer recommendStatus) {
        return smsHomeRecommendProductMapper.updateRecommendStatus(ids, recommendStatus);
    }

    @Override
    public List<SmsHomeRecommendProduct> list(String productName, Integer recommendStatus, Integer pageSize, Integer pageNum) {
        // Calculate the offset based on page size and number
        Integer offset = (pageNum - 1) * pageSize;

        LambdaQueryWrapper<SmsHomeRecommendProduct> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(productName), SmsHomeRecommendProduct::getProductName, productName)
                .eq(recommendStatus != null, SmsHomeRecommendProduct::getRecommendStatus, recommendStatus)
                .orderByAsc(SmsHomeRecommendProduct::getSort);

        // MyBatis-Plus pagination method
        IPage<SmsHomeRecommendProduct> page = new Page<>(pageNum, pageSize);
        page = smsHomeRecommendProductMapper.selectPage(page, queryWrapper);

        return page.getRecords();
    }
    @Override
    public int update(Long id, SmsHomeRecommendProduct smsHomeRecommendProduct) {
        // 确保id不为空且待更新的对象非空
        if (Objects.isNull(id) || Objects.isNull(smsHomeRecommendProduct)) {
            throw new IllegalArgumentException("Id or homeNewProduct cannot be null.");
        }

        // 设置需要更新的实体的id，确保与参数id一致
        smsHomeRecommendProduct.setId(id);

        // 使用MyBatis Plus的updateById方法直接更新，无需手动编写SQL
        return smsHomeRecommendProductMapper.updateById(smsHomeRecommendProduct);
    }
}
