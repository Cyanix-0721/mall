
package com.mole.protal.service.impl;

import com.mole.common.entity.portal.SmsHomeBrand;
import com.mole.protal.mapper.SmsHomeBrandMapper;
import com.mole.protal.service.SmsHomeBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
 * 首页品牌管理ServiceImpl
 */
@Service
public class SmsHomeBrandServiceImpl implements SmsHomeBrandService {

    @Autowired
    private SmsHomeBrandMapper smsHomeBrandMapper;

    @Override
    @Transactional
    public int create(List<SmsHomeBrand> homeBrandList) {
        return smsHomeBrandMapper.batchInsert(homeBrandList);
    }

    @Override
    public int updateSort(Long id, Integer sort) {
        return smsHomeBrandMapper.updateSortById(id, sort);
    }

    @Override
    public int delete(Long id) {
        return smsHomeBrandMapper.deleteById(id);
    }

    @Override
    public int updateRecommendStatus(List<Long> ids, Integer recommendStatus) {
        return smsHomeBrandMapper.updateRecommendStatusByIds(ids, recommendStatus);
    }

    @Override
    public List<SmsHomeBrand> list(String brandName, Integer recommendStatus, Integer pageSize, Integer pageNum) {
        int offset = (pageNum - 1) * pageSize;
        return smsHomeBrandMapper.selectPage(brandName, recommendStatus, pageSize, offset);
    }
    @Override
    public int update(Long id, SmsHomeBrand smsHomeBrand) {
        // 确保id不为空且待更新的对象非空
        if (Objects.isNull(id) || Objects.isNull(smsHomeBrand)) {
            throw new IllegalArgumentException("Id or smsHomeBrand cannot be null.");
        }

        // 设置需要更新的实体的id，确保与参数id一致
        smsHomeBrand.setId(id);

        // 使用MyBatis Plus的updateById方法直接更新，无需手动编写SQL
        return smsHomeBrandMapper.updateById(smsHomeBrand);
    }
}