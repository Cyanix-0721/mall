package com.mole.marketing.service.impl;

import com.alibaba.nacos.common.utils.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mole.marketing.entity.SmsHomeRecommendSubject;
import com.mole.marketing.mapper.SmsHomeRecommendSubjectMapper;
import com.mole.marketing.service.SmsHomeRecommendSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SmsHomeRecommendSubjectServiceImpl extends ServiceImpl<SmsHomeRecommendSubjectMapper, SmsHomeRecommendSubject> implements SmsHomeRecommendSubjectService {

    @Autowired
    private SmsHomeRecommendSubjectMapper recommendSubjectMapper;

    @Override
    @Transactional
    public int create(List<SmsHomeRecommendSubject> recommendSubjectList) {
        return recommendSubjectMapper.insertBatch(recommendSubjectList);
    }

    @Override
    public int updateSort(Long id, Integer sort) {
        SmsHomeRecommendSubject subject = new SmsHomeRecommendSubject();
        subject.setId(id);
        subject.setSort(sort);
        return recommendSubjectMapper.updateById(subject);
    }

    @Override
    public int delete(List<Long> ids) {
        return recommendSubjectMapper.deleteBatchIds(ids);
    }

    @Override
    public int updateRecommendStatus(List<Long> ids, Integer recommendStatus) {
        UpdateWrapper<SmsHomeRecommendSubject> updateWrapper = new UpdateWrapper<>();
        updateWrapper.in("id", ids).set("recommend_status", recommendStatus);
        return recommendSubjectMapper.update(null, updateWrapper);
    }

    @Override
    public IPage<SmsHomeRecommendSubject> list(String subjectName, Integer recommendStatus, Integer pageSize, Integer pageNum) {
        Page<SmsHomeRecommendSubject> page = new Page<>(pageNum, pageSize);
        return recommendSubjectMapper.selectPage(page, new QueryWrapper<SmsHomeRecommendSubject>()
                .like(StringUtils.isNotBlank(subjectName), "subject_name", subjectName)
                .eq(recommendStatus != null, "recommend_status", recommendStatus));
    }
}
