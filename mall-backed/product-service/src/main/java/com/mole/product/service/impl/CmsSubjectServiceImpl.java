package com.mole.product.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mole.product.mapper.CmsSubjectMapper;
import com.mole.common.entity.product.CmsSubject;
import com.mole.product.service.CmsSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品专题管理Service实现类
 */
@Service
public class CmsSubjectServiceImpl extends ServiceImpl<CmsSubjectMapper, CmsSubject> implements CmsSubjectService {
    @Autowired
    private CmsSubjectMapper subjectMapper;

    @Override
    public List<CmsSubject> listAll() {
        return subjectMapper.selectList(null);
    }

    @Override
    public List<CmsSubject> list(String keyword, Integer pageNum, Integer pageSize) {
        // 创建分页对象
        Page<CmsSubject> page = new Page<>(pageNum, pageSize);

        // 构建查询条件
        QueryWrapper<CmsSubject> queryWrapper = new QueryWrapper<>();

        // 添加关键词模糊搜索条件，如果keyword不为空
        if (!StrUtil.isEmpty(keyword)) {
            queryWrapper.like("title", keyword);
        }

        // 执行分页查询
        return subjectMapper.selectPage(page, queryWrapper).getRecords();
    }

}
