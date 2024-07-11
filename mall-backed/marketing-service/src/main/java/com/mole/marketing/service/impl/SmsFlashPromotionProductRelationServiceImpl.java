package com.mole.marketing.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mole.common.entity.marketing.SmsFlashPromotionProductRelation;
import com.mole.marketing.mapper.SmsFlashPromotionProductRelationMapper;
import com.mole.marketing.service.SmsFlashPromotionProductRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SmsFlashPromotionProductRelationServiceImpl implements SmsFlashPromotionProductRelationService {

    private final SmsFlashPromotionProductRelationMapper smsFlashPromotionProductRelationMapper;

    @Autowired
    public SmsFlashPromotionProductRelationServiceImpl(SmsFlashPromotionProductRelationMapper smsFlashPromotionProductRelationMapper) {
        this.smsFlashPromotionProductRelationMapper = smsFlashPromotionProductRelationMapper;
    }

    @Override
    @Transactional
    public int create(List<SmsFlashPromotionProductRelation> relationList) {
        return smsFlashPromotionProductRelationMapper.insertBatch(relationList);
    }

    @Override
    public int update(Long id, SmsFlashPromotionProductRelation relation) {
        relation.setId(id);
        return smsFlashPromotionProductRelationMapper.updateById(relation);
    }

    @Override
    public int delete(Long id) {
        return smsFlashPromotionProductRelationMapper.deleteById(id);
    }

    @Override
    public SmsFlashPromotionProductRelation getItem(Long id) {
        return smsFlashPromotionProductRelationMapper.selectById(id);
    }

    @Override
    public long getCount(Long flashPromotionId, Long flashPromotionSessionId) {
        LambdaQueryWrapper<SmsFlashPromotionProductRelation> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SmsFlashPromotionProductRelation::getFlashPromotionId, flashPromotionId)
                .eq(SmsFlashPromotionProductRelation::getFlashPromotionSessionId, flashPromotionSessionId);

        return smsFlashPromotionProductRelationMapper.selectCount(queryWrapper);
    }

    // 假设新增一个分页查询方法
    public IPage<SmsFlashPromotionProductRelation> listByPromotionIds(Long promotionId, Long sessionId, Integer pageSize, Integer pageNum) {
        LambdaQueryWrapper<SmsFlashPromotionProductRelation> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(promotionId != null, SmsFlashPromotionProductRelation::getFlashPromotionId, promotionId)
                .eq(sessionId != null, SmsFlashPromotionProductRelation::getFlashPromotionSessionId, sessionId);

        Page<SmsFlashPromotionProductRelation> page = new Page<>(pageNum, pageSize);
        return smsFlashPromotionProductRelationMapper.selectPage(page, queryWrapper);
    }
}