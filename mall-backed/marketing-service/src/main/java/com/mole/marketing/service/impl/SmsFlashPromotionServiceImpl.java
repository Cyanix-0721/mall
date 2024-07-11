package com.mole.marketing.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mole.common.entity.marketing.SmsFlashPromotion;
import com.mole.marketing.mapper.SmsFlashPromotionMapper;
import com.mole.marketing.service.SmsFlashPromotionService;
import org.springframework.stereotype.Service;

@Service
public class SmsFlashPromotionServiceImpl extends ServiceImpl<SmsFlashPromotionMapper, SmsFlashPromotion> implements SmsFlashPromotionService {

    @Override
    public int create(SmsFlashPromotion flashPromotion) {
        return baseMapper.insert(flashPromotion);
    }

    @Override
    public int update(Long id, SmsFlashPromotion flashPromotion) {
        flashPromotion.setId(id);
        return baseMapper.updateById(flashPromotion);
    }

    @Override
    public int delete(Long id) {
        return baseMapper.deleteById(id);
    }

    @Override
    public int updateStatus(Long id, Integer status) {
        UpdateWrapper<SmsFlashPromotion> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", id).set("status", status);
        return baseMapper.update(null, updateWrapper);
    }

    @Override
    public SmsFlashPromotion getItem(Long id) {
        return baseMapper.selectById(id);
    }

    @Override
    public IPage<SmsFlashPromotion> list(String keyword, Integer pageSize, Integer pageNum) {
        // 这里需要根据实际需求构建查询条件，这里仅做示例，假设你使用的是MyBatis-Plus的分页插件
        IPage<SmsFlashPromotion> page = new com.baomidou.mybatisplus.extension.plugins.pagination.Page<>(pageNum, pageSize);
        if (keyword != null && !keyword.isEmpty()) {
            // 假设title字段支持模糊搜索
            baseMapper.selectPage(page, new QueryWrapper<SmsFlashPromotion>().like("title", keyword));
        } else {
            baseMapper.selectPage(page, null);
        }
        return page;
    }
}