package com.mole.marketing.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mole.common.entity.marketing.SmsFlashPromotionProductRelation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 限时购商品关联管理Service
 */
public interface SmsFlashPromotionProductRelationService {
    /**
     * 批量添加关联
     */
    @Transactional
    int create(List<SmsFlashPromotionProductRelation> relationList);

    /**
     * 修改关联信息
     */
    int update(Long id, SmsFlashPromotionProductRelation relation);

    /**
     * 删除关联
     */
    int delete(Long id);

    /**
     * 获取关联详情
     */
    SmsFlashPromotionProductRelation getItem(Long id);

    /**
     * 根据活动和场次id获取商品关系数量
     */
    long getCount(Long flashPromotionId, Long flashPromotionSessionId);

    /**
     * 分页查询关联信息
     *
     * @param promotionId   限时购id
     * @param sessionId     限时购场次id
     * @param pageSize      每页条数
     * @param pageNum       当前页数
     * @return 分页数据
     */
    IPage<SmsFlashPromotionProductRelation> listByPromotionIds(Long promotionId, Long sessionId, Integer pageSize, Integer pageNum);
}