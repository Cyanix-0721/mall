package com.mole.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mole.common.dto.product.PmsProductParam;
import com.mole.common.dto.product.PmsProductQueryParam;
import com.mole.common.dto.product.PmsProductResult;
import com.mole.common.entity.product.PmsProduct;
import com.mole.common.entity.product.PmsProductVertifyRecord;
import com.mole.product.mapper.PmsProductMapper;
import com.mole.product.mapper.PmsProductVertifyRecordMapper;
import com.mole.product.service.PmsProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 商品管理Service实现类
 */
@Service
public class PmsProductServiceImpl extends ServiceImpl<PmsProductMapper, PmsProduct> implements PmsProductService {

    @Autowired
    private PmsProductMapper pmsProductMapper;

    @Autowired
    private PmsProductVertifyRecordMapper pmsProductVertifyRecordMapper;

    @Override
    @Transactional
    public int create(PmsProductParam productParam) {
        PmsProduct product = new PmsProduct();
        BeanUtils.copyProperties(productParam, product);
        return baseMapper.insert(product);
    }

    @Override
    public PmsProductResult getUpdateInfo(Long id) {
        PmsProduct product = baseMapper.selectById(id);
        if (product == null) {
            return null;
        }
        PmsProductResult result = new PmsProductResult();
        BeanUtils.copyProperties(product, result);
        return result;
    }

    @Override
    @Transactional
    public int update(Long id, PmsProductParam productParam) {
        PmsProduct product = new PmsProduct();
        BeanUtils.copyProperties(productParam, product);
        product.setId(id);
        return baseMapper.updateById(product);
    }

    @Override
    public List<PmsProduct> list(PmsProductQueryParam productQueryParam, Integer pageSize, Integer pageNum) {
        QueryWrapper<PmsProduct> queryWrapper = new QueryWrapper<>();
        if (productQueryParam.getPublishStatus() != null) {
            queryWrapper.eq("publish_status", productQueryParam.getPublishStatus());
        }
        if (productQueryParam.getVerifyStatus() != null) {
            queryWrapper.eq("verify_status", productQueryParam.getVerifyStatus());
        }
        if (productQueryParam.getProductSn() != null) {
            queryWrapper.like("product_sn", productQueryParam.getProductSn());
        }
        if (productQueryParam.getProductCategoryId() != null) {
            queryWrapper.eq("product_category_id", productQueryParam.getProductCategoryId());
        }
        if (productQueryParam.getKeyword() != null) {
            queryWrapper.like("name", productQueryParam.getKeyword());
        }

        Page<PmsProduct> page = new Page<>(pageNum, pageSize);
        IPage<PmsProduct> iPage = baseMapper.selectPage(page, queryWrapper);
        return iPage.getRecords();
    }

    public int updateVerifyStatus(List<Long> ids, Integer verifyStatus, String detail) {
        int count = pmsProductMapper.updateVerifyStatusByIds(ids, verifyStatus);

        List<PmsProductVertifyRecord> records = new ArrayList<>();
        for (Long id : ids) {
            PmsProductVertifyRecord record = new PmsProductVertifyRecord();
            record.setProductId(id);
            record.setCreateTime(new Date());
            record.setDetail(detail);
            record.setStatus(verifyStatus);
            record.setVertifyMan("test");
            records.add(record);
        }

        // 批量插入审核记录
        pmsProductVertifyRecordMapper.insertBatch(records);

        return count;
    }

    @Override
    public int updatePublishStatus(List<Long> ids, Integer publishStatus) {
        PmsProduct product = new PmsProduct();
        product.setPublishStatus(publishStatus);
        QueryWrapper<PmsProduct> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("id", ids);
        return baseMapper.update(product, queryWrapper);
    }

    @Override
    public int updateRecommendStatus(List<Long> ids, Integer recommendStatus) {
        PmsProduct product = new PmsProduct();
        product.setRecommandStatus(recommendStatus);
        QueryWrapper<PmsProduct> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("id", ids);
        return baseMapper.update(product, queryWrapper);
    }

    @Override
    public int updateNewStatus(List<Long> ids, Integer newStatus) {
        PmsProduct product = new PmsProduct();
        product.setNewStatus(newStatus);
        QueryWrapper<PmsProduct> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("id", ids);
        return baseMapper.update(product, queryWrapper);
    }

    @Override
    public int updateDeleteStatus(List<Long> ids, Integer deleteStatus) {
        PmsProduct product = new PmsProduct();
        product.setDeleteStatus(deleteStatus);
        QueryWrapper<PmsProduct> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("id", ids);
        return baseMapper.update(product, queryWrapper);
    }

    @Override
    public List<PmsProduct> list(String keyword) {
        QueryWrapper<PmsProduct> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", keyword).or().like("product_sn", keyword);
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public int deleteProduct(Long id) {
        return pmsProductMapper.deleteById(id);
    }
}
