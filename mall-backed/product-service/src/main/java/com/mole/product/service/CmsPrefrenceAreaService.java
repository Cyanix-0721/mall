package com.mole.product.service;

import com.mole.common.entity.product.CmsPrefrenceArea;

import java.util.List;

/**
 * 商品优选管理Service
 */
public interface CmsPrefrenceAreaService {
    /**
     * 获取所有商品优选
     */
    List<CmsPrefrenceArea> listAll();
}
