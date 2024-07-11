package com.mole.marketing.service.impl;

import com.alibaba.cloud.commons.lang.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mole.common.entity.marketing.SmsCouponHistory;
import com.mole.marketing.mapper.SmsCouponHistoryMapper;
import com.mole.marketing.service.SmsCouponHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SmsCouponHistoryServiceImpl implements SmsCouponHistoryService {

    private final SmsCouponHistoryMapper smsCouponHistoryMapper;

    @Autowired
    public SmsCouponHistoryServiceImpl(SmsCouponHistoryMapper smsCouponHistoryMapper) {
        this.smsCouponHistoryMapper = smsCouponHistoryMapper;
    }

    /**
     * 分页查询优惠券领取记录
     * @param couponId 优惠券id
     * @param useStatus 使用状态
     * @param orderSn 使用订单号码
     * @param pageSize 每页大小
     * @param pageNum 当前页码
     * @return 分页后的优惠券领取记录列表
     */
    @Override
    public IPage<SmsCouponHistory> list(Long couponId, Integer useStatus, String orderSn, Integer pageSize, Integer pageNum) {
        LambdaQueryWrapper<SmsCouponHistory> queryWrapper = new LambdaQueryWrapper<>();

        if (couponId != null) {
            queryWrapper.eq(SmsCouponHistory::getCouponId, couponId);
        }
        if (useStatus != null) {
            queryWrapper.eq(SmsCouponHistory::getUseStatus, useStatus);
        }
        if (StringUtils.isNotBlank(orderSn)) {
            queryWrapper.eq(SmsCouponHistory::getOrderSn, orderSn);
        }

        Page<SmsCouponHistory> page = new Page<>(pageNum, pageSize);
        return smsCouponHistoryMapper.selectPage(page, queryWrapper);
    }

    @Override
    public List<SmsCouponHistory> listByMemberId(Long memberId) {
        LambdaQueryWrapper<SmsCouponHistory> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SmsCouponHistory::getMemberId, memberId);
        return smsCouponHistoryMapper.selectList(queryWrapper);
    }
}