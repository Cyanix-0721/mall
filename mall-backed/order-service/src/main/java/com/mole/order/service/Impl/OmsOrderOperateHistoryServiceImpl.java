package com.mole.order.service.Impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mole.common.entity.order.OmsOrderOperateHistory;
import com.mole.order.mapper.OmsOrderOperateHistoryMapper;
import com.mole.order.service.OmsOrderOperateHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OmsOrderOperateHistoryServiceImpl implements OmsOrderOperateHistoryService {
    private final OmsOrderOperateHistoryMapper omsOrderOperateHistoryMapper;

    @Override
    public IPage<OmsOrderOperateHistory> operateList(Integer pageSize, Integer pageNum) {
        Page<OmsOrderOperateHistory> page = new Page<>(pageNum, pageSize);
        return omsOrderOperateHistoryMapper.selectPage(page, null);
    }
}
