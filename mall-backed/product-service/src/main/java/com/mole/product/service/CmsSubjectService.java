package com.mole.product.service;

import com.mole.common.entity.product.CmsSubject;

import java.util.List;

/**
 * 商品专题管理Service
 */
public interface CmsSubjectService {
    /**
     * 查询所有专题
     */
    List<CmsSubject> listAll();

    /**
     * 分页查询专题
     */
    List<CmsSubject> list(String keyword, Integer pageNum, Integer pageSize);
}
