package com.mole.order.service;

import com.mole.common.entity.order.OmsOrderSetting;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
@FeignClient("order-service")
public interface OmsOrderSettingService {
    /**
     * 获取指定订单设置
     */
    OmsOrderSetting getItem(Long id);
}
