package com.mole.marketing.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mole.common.dto.marketing.SmsFlashPromotionSessionDetail;
import com.mole.common.entity.marketing.SmsFlashPromotionSession;
import com.mole.marketing.mapper.SmsFlashPromotionSessionMapper;
import com.mole.marketing.service.SmsFlashPromotionSessionService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SmsFlashPromotionSessionServiceImpl extends ServiceImpl<SmsFlashPromotionSessionMapper, SmsFlashPromotionSession> implements SmsFlashPromotionSessionService {

    @Resource
    private SmsFlashPromotionSessionMapper promotionSessionMapper;

    @Override
    @Transactional
    public int create(SmsFlashPromotionSession promotionSession) {
        return promotionSessionMapper.insert(promotionSession);
    }

    @Override
    @Transactional
    public int update(Long id, SmsFlashPromotionSession promotionSession) {
        promotionSession.setId(id);
        return promotionSessionMapper.updateById(promotionSession);
    }

    @Override
    @Transactional
    public int updateStatus(Long id, Integer status) {
        SmsFlashPromotionSession session = new SmsFlashPromotionSession();
        session.setId(id);
        session.setStatus(status);
        return promotionSessionMapper.updateById(session);
    }

    @Override
    @Transactional
    public int delete(Long id) {
        return promotionSessionMapper.deleteById(id);
    }

    @Override
    public SmsFlashPromotionSession getItem(Long id) {
        return promotionSessionMapper.selectById(id);
    }

    @Override
    public List<SmsFlashPromotionSession> list() {
        return promotionSessionMapper.selectList(null);
    }

    @Override
    public List<SmsFlashPromotionSessionDetail> selectList(Long id) {
        // 调用Mapper方法获取促销活动的闪购场次列表
        List<SmsFlashPromotionSession> promotionSessions = promotionSessionMapper.getSmsFlashPromotionSessionDetailsByPromotionId(id);

        // 将查询结果转换为SmsFlashPromotionSessionDetail列表
        return promotionSessions.stream()
                .map(this::convertToSessionDetail)
                .collect(Collectors.toList());
    }

    // 添加一个辅助方法用于转换实体到详细视图
    private SmsFlashPromotionSessionDetail convertToSessionDetail(SmsFlashPromotionSession session) {
        SmsFlashPromotionSessionDetail detail = new SmsFlashPromotionSessionDetail();
        // 使用BeanUtils.copyProperties复制属性，如果有额外的字段需要手动设置
        BeanUtils.copyProperties(session, detail);
        // 如果SmsFlashPromotionSessionDetail中有其他非SmsFlashPromotionSession直接映射的字段，这里需要手动设置
        return detail;
    }
}
