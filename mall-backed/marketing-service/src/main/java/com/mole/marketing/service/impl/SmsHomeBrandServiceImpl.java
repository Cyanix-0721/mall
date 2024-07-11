package com.mole.marketing.service.impl;

import com.alibaba.cloud.commons.lang.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mole.marketing.entity.SmsHomeBrand;
import com.mole.marketing.mapper.SmsHomeBrandMapper;
import com.mole.marketing.service.SmsHomeBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SmsHomeBrandServiceImpl extends ServiceImpl<SmsHomeBrandMapper, SmsHomeBrand> implements SmsHomeBrandService {

    @Autowired
    private SmsHomeBrandMapper homeBrandMapper;

    @Override
    @Transactional
    public int create(List<SmsHomeBrand> homeBrandList) {
        return homeBrandMapper.batchInsert(homeBrandList);
    }

    @Override
    public int updateSort(Long id, Integer sort) {
        SmsHomeBrand brand = new SmsHomeBrand();
        brand.setId(id);
        brand.setSort(sort);
        return homeBrandMapper.updateById(brand);
    }

    @Override
    public int delete(List<Long> ids) {
        return homeBrandMapper.deleteBatchIds(ids);
    }

    @Override
    public int updateRecommendStatus(List<Long> ids, Integer recommendStatus) {
        UpdateWrapper<SmsHomeBrand> updateWrapper = new UpdateWrapper<>();
        updateWrapper.in("id", ids).set("recommend_status", recommendStatus);
        return homeBrandMapper.update(null, updateWrapper);
    }

    @Override
    public IPage<SmsHomeBrand> list(String brandName, Integer recommendStatus, Integer pageSize, Integer pageNum) {
        Page<SmsHomeBrand> page = new Page<>(pageNum, pageSize);
        QueryWrapper<SmsHomeBrand> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(brandName), "name", brandName)
                .eq(recommendStatus != null, "recommend_status", recommendStatus);
        return homeBrandMapper.selectPage(page, queryWrapper);
    }
}
