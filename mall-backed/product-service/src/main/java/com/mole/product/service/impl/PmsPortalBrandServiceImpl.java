package com.mole.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.mole.common.entity.product.api.CommonPage;
import com.mole.product.mapper.PmsBrandMapper;
import com.mole.product.mapper.PmsProductMapper;
import com.mole.common.entity.product.PmsBrand;
import com.mole.common.entity.product.PmsProduct;
import com.mole.product.service.PmsPortalBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 前台品牌管理Service实现类
 */
@Service
public class PmsPortalBrandServiceImpl implements PmsPortalBrandService {

    @Autowired
    private PmsBrandMapper pmsBrandMapper;
    @Autowired
    private PmsProductMapper psmProductMapper;

    @Override
    public List<PmsBrand> recommendList(Integer pageNum, Integer pageSize) {
        int offset = (pageNum - 1) * pageSize;
        return pmsBrandMapper.getRecommendBrandList(offset, pageSize);
    }

    @Override
    public PmsBrand detail(Long brandId) {
        return pmsBrandMapper.selectById(brandId);
    }

    @Override
    public CommonPage<PmsProduct> productList(Long brandId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        // 直接在查询方法中设置条件，这里以假设的方法签名为例，实际应根据您的mapper接口调整
        List<PmsProduct> productList = psmProductMapper.selectList(new QueryWrapper<PmsProduct>()
                .eq("delete_status", 0)
                .eq("publish_status", 1)
                .eq("brand_id", brandId));

        return CommonPage.restPage(productList);
    }


}
