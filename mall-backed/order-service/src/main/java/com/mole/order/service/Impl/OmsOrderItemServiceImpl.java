package com.mole.order.service.Impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mole.common.entity.order.OmsOrderItem;
import com.mole.order.mapper.OmsOrderItemMapper;
import com.mole.order.service.OmsOrderItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OmsOrderItemServiceImpl implements OmsOrderItemService {

    private final OmsOrderItemMapper omsOrderItemMapper;

    @Override
    public IPage<OmsOrderItem> listOrderItem(Integer pageSize, Integer pageNum) {
        Page<OmsOrderItem> page = new Page<>(pageNum, pageSize);
        return omsOrderItemMapper.selectPage(page, null);
    }
}
