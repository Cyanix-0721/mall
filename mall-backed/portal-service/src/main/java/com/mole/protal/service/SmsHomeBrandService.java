package com.mole.protal.service;

import com.mole.common.entity.portal.SmsHomeBrand;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 首页品牌管理Service

 */
public interface SmsHomeBrandService {
    /**
     * 添加首页品牌推荐
     */
    @Transactional
    int create(List<SmsHomeBrand> homeBrandList);

    /**
     * 修改品牌推荐排序
     */
    int updateSort(Long id, Integer sort);

    /**
     * 批量删除品牌推荐
     */
    int delete(Long id);

    /**
     * 批量更新推荐状态
     */
    int updateRecommendStatus(List<Long> ids, Integer recommendStatus);

    /**
     * 分页查询品牌推荐
     */
    List<SmsHomeBrand> list(String brandName, Integer recommendStatus, Integer pageSize, Integer pageNum);

    int update(Long id, SmsHomeBrand smsHomeBrand);
}
