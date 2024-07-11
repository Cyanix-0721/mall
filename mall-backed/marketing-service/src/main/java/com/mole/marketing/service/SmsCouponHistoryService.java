package com.mole.marketing.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mole.common.entity.marketing.SmsCouponHistory;

import java.util.List;

/**
 * 优惠券领取记录管理Service
 */
public interface SmsCouponHistoryService {
    /**
     * 分页查询优惠券领取记录
     * @param couponId 优惠券id
     * @param useStatus 使用状态
     * @param orderSn 使用订单号码
     */
    IPage<SmsCouponHistory> list(Long couponId, Integer useStatus, String orderSn, Integer pageSize, Integer pageNum);

    /**
     * 根据 memberId 查询优惠券领取记录
     */
    List<SmsCouponHistory> listByMemberId(Long memberId);
}
