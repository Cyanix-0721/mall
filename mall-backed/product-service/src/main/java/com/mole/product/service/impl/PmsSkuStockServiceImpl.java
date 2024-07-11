package com.mole.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.mole.common.entity.product.PmsSkuStock;
import com.mole.product.mapper.PmsSkuStockMapper;
import com.mole.product.service.PmsSkuStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 商品SKU库存管理Service实现类
 */
@Service
public class PmsSkuStockServiceImpl implements PmsSkuStockService {

    @Autowired
    private PmsSkuStockMapper pmsSkuStockMapper;

    /**
     * 根据商品id和skuCode关键字模糊搜索
     *
     * @param productId 商品ID
     * @param keyword   SKU Code关键词
     * @return 符合条件的SKU库存列表
     */
    @Override
    public List<PmsSkuStock> getList(Long productId, String keyword) {
        LambdaQueryWrapper<PmsSkuStock> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(PmsSkuStock::getProductId, productId);
        if (keyword != null && !keyword.isEmpty()) {
            queryWrapper.like(PmsSkuStock::getSkuCode, keyword);
        }
        return pmsSkuStockMapper.selectList(queryWrapper);
    }

    /**
     * 批量更新商品库存信息
     *
     * @param pid         商品ID
     * @param skuStockList 需要更新的SKU库存列表
     * @return 受影响的行数
     */
    @Override
    @Transactional
    public int update(Long pid, List<PmsSkuStock> skuStockList) {
        for (PmsSkuStock skuStock : skuStockList) {
            skuStock.setProductId(pid); // 确保每个库存记录的商品ID与传入的商品ID一致
            pmsSkuStockMapper.updateById(skuStock);
        }
        return skuStockList.size(); // 返回更新的记录数，实际应用中可能需要更精确的逻辑来统计影响行数
    }
}
