package com.mole.protal.service;

import com.mole.common.entity.portal.SmsHomeNewProduct;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 首页新品推荐管理Service

 */
public interface SmsHomeNewProductService {
    /**
     * 添加首页推荐
     */
    @Transactional
    int create(List<SmsHomeNewProduct> homeNewProductList);

    /**
     * 修改推荐排序
     */
    int updateSort(Long id, Integer sort);

    /**
     * 删除推荐
     */
    int delete(Long id);

    /**
     * 批量更新推荐状态
     */
    int updateRecommendStatus(List<Long> ids, Integer recommendStatus);

    /**
     * 分页查询推荐
     */
    List<SmsHomeNewProduct> list(String productName, Integer recommendStatus, Integer pageSize, Integer pageNum);

    int update(Long id, SmsHomeNewProduct homeNewProduct);
}
