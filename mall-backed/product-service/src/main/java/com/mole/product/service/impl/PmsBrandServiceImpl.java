package com.mole.product.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mole.common.dto.product.PmsBrandParam;
import com.mole.common.entity.product.PmsBrand;
import com.mole.common.entity.product.PmsProduct;
import com.mole.product.mapper.PmsBrandMapper;
import com.mole.product.mapper.PmsProductMapper;
import com.mole.product.service.PmsBrandService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 商品品牌管理Service实现类
 */
@Service
public class PmsBrandServiceImpl extends ServiceImpl<PmsBrandMapper, PmsBrand> implements PmsBrandService {

    @Autowired
    private PmsBrandMapper pmsBrandMapper;

    @Autowired
    private PmsProductMapper pmsProductMapper;

    @Override
    public List<PmsBrand> listAllBrand() {
        return pmsBrandMapper.selectList(null);
    }

    @Override
    @Transactional
    public int createBrand(PmsBrandParam pmsBrand) {
        PmsBrand brand = new PmsBrand();
        copyProperties(pmsBrand, brand);
        return pmsBrandMapper.insert(brand);
    }

    @Override
    public int updateBrand(Long id, PmsBrandParam pmsBrandParam) {
        PmsBrand pmsBrand = new PmsBrand();
        BeanUtils.copyProperties(pmsBrandParam, pmsBrand);
        pmsBrand.setId(id);
        // 如果创建时首字母为空，取名称的第一个为首字母
        if (StrUtil.isEmpty(pmsBrand.getFirstLetter())) {
            pmsBrand.setFirstLetter(pmsBrand.getName().substring(0, 1));
        }

        // 更新品牌时要更新商品中的品牌名称
        PmsProduct productToUpdate = new PmsProduct();
        productToUpdate.setBrandName(pmsBrand.getName());
        productToUpdate.setBrandId(id); // 确保只更新指定品牌的商品

        // 使用UpdateWrapper来构建更新条件
        UpdateWrapper<PmsProduct> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("brand_id", id);

        // 更新商品表中的品牌名称
        pmsProductMapper.update(productToUpdate, updateWrapper);

        // 更新品牌信息
        return pmsBrandMapper.updateById(pmsBrand);
    }


    private void copyProperties(Object source, Object target) {
        BeanUtils.copyProperties(source, target);
    }


    @Override
    public int deleteBrand(Long id) {
        return pmsBrandMapper.deleteById(id);
    }

    @Override
    public int deleteBrand(List<Long> ids) {
        return pmsBrandMapper.deleteBatchIds(ids);
    }

    @Override
    public List<PmsBrand> listBrand(String keyword, Integer showStatus, Integer pageNum, Integer pageSize) {
        Page<PmsBrand> page = new Page<>(pageNum, pageSize);
        QueryWrapper<PmsBrand> queryWrapper = new QueryWrapper<>();
        if (keyword != null) {
            queryWrapper.like("name", keyword);
        }
        if (showStatus != null) {
            queryWrapper.eq("show_status", showStatus);
        }
        queryWrapper.orderByDesc("sort");
        return pmsBrandMapper.selectPage(page, queryWrapper).getRecords();
    }

    @Override
    public PmsBrand getBrand(Long id) {
        return pmsBrandMapper.selectById(id);
    }

    @Override
    public int updateShowStatus(List<Long> ids, Integer showStatus) {
        PmsBrand update = new PmsBrand();
        update.setShowStatus(showStatus);
        return pmsBrandMapper.update(update, new QueryWrapper<PmsBrand>().in("id", ids));
    }

    @Override
    public int updateFactoryStatus(List<Long> ids, Integer factoryStatus) {
        PmsBrand update = new PmsBrand();
        update.setFactoryStatus(factoryStatus);
        return pmsBrandMapper.update(update, new QueryWrapper<PmsBrand>().in("id", ids));
    }
}
