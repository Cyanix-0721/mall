package com.mole.order.service.Impl;

import com.alibaba.nacos.client.naming.utils.CollectionUtils;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mole.common.entity.order.OmsOrderReturnReason;
import com.mole.order.mapper.OmsOrderReturnReasonMapper;
import com.mole.order.service.OmsOrderReturnReasonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
@RequiredArgsConstructor
public class OmsOrderReturnReasonServiceImpl implements OmsOrderReturnReasonService {

    private final OmsOrderReturnReasonMapper omsOrderReturnReasonMapper;

    @Override
    public int create(OmsOrderReturnReason omsOrderReturnReason) {
        omsOrderReturnReason.setCreateTime(new Date());
        return omsOrderReturnReasonMapper.insert(omsOrderReturnReason);
    }

    @Override
    public int update(Long id, OmsOrderReturnReason omsOrderReturnReason) {
          omsOrderReturnReason.setId(id);
          return omsOrderReturnReasonMapper.updateById(omsOrderReturnReason);
    }

    @Override
    public int delete(List<Long> ids) {
        return omsOrderReturnReasonMapper.deleteBatchIds(ids);
    }

    @Override
    public IPage<OmsOrderReturnReason> reasonList(Integer pageSize, Integer pageNum) {
        Page<OmsOrderReturnReason> page = new Page<>(pageNum, pageSize);
        return omsOrderReturnReasonMapper.selectPage(page, null);
    }


    @Override
    public int updateStatus(List<Long> ids, Integer status) {
        if (CollectionUtils.isEmpty(ids)) {
            return 0;
        }
            return omsOrderReturnReasonMapper.updateStatus(ids, status);
        }


    @Override
    public OmsOrderReturnReason getItem(Long id) {
        return omsOrderReturnReasonMapper.selectById(id);
    }
}
