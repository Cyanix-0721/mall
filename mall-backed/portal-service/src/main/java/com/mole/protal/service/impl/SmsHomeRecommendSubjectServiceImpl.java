package com.mole.protal.service.impl;

import com.github.pagehelper.PageHelper;
import com.mole.common.entity.portal.SmsHomeRecommendSubject;
import com.mole.protal.mapper.SmsHomeRecommendSubjectMapper;
import com.mole.protal.service.SmsHomeRecommendSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class SmsHomeRecommendSubjectServiceImpl  implements SmsHomeRecommendSubjectService {

    @Autowired
    private SmsHomeRecommendSubjectMapper smsHomerecommendSubjectMapper;

    /**
     * 添加首页推荐
     * 实现批量插入逻辑
     */
    @Override
    @Transactional
    public int create(List<SmsHomeRecommendSubject> recommendSubjectList) {
        return smsHomerecommendSubjectMapper.batchInsert(recommendSubjectList);
    }

    /**
     * 修改推荐排序
     */
    @Override
    public int updateSort(Long id, Integer sort) {
        return smsHomerecommendSubjectMapper.updateSort(id, sort);
    }

    /**
     * 批量删除推荐
     */
    @Override
    public int delete(Long id) {
        return smsHomerecommendSubjectMapper.deleteById(id);
    }

    /**
     * 批量更新推荐状态
     */
    @Override
    public int updateRecommendStatus(List<Long> ids, Integer recommendStatus) {
        return smsHomerecommendSubjectMapper.batchUpdateRecommendStatus(ids, recommendStatus);
    }

    @Override
    public List<SmsHomeRecommendSubject> list(String subjectName, Integer recommendStatus, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);

        // 直接使用Mapper接口中的方法，并通过@Param注解传递参数
        return smsHomerecommendSubjectMapper.listByCondition(
                subjectName != null ? "%" + subjectName + "%" : null,
                recommendStatus,
                "sort desc"
        );
    }
    @Override
    public int update(Long id, SmsHomeRecommendSubject smsHomeRecommendSubject) {
        // 确保id不为空且待更新的对象非空
        if (Objects.isNull(id) || Objects.isNull(smsHomeRecommendSubject)) {
            throw new IllegalArgumentException("Id or homeNewProduct cannot be null.");
        }

        // 设置需要更新的实体的id，确保与参数id一致
        smsHomeRecommendSubject.setId(id);

        // 使用MyBatis Plus的updateById方法直接更新，无需手动编写SQL
        return smsHomerecommendSubjectMapper.updateById(smsHomeRecommendSubject);
    }

}
