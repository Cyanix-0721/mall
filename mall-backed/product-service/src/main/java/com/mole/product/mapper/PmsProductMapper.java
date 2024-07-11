package com.mole.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.mole.common.entity.product.PmsProduct;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 商品Mapper接口
 */
@Repository
public interface PmsProductMapper extends BaseMapper<PmsProduct> {

    // 批量更新商品审核状态
    default int updateVerifyStatusByIds(List<Long> ids, Integer verifyStatus) {
        UpdateWrapper<PmsProduct> updateWrapper = new UpdateWrapper<>();
        updateWrapper.in("id", ids).set("verify_status", verifyStatus);
        return update(updateWrapper);
    }

}

