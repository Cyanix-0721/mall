package com.mole.order.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mole.common.entity.order.OmsOrderOperateHistory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
@FeignClient("order-service")
public interface OmsOrderOperateHistoryService {
    IPage<OmsOrderOperateHistory> operateList(Integer pageSize, Integer pageNum);
}
