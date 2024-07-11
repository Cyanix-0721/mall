package com.mole.order.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mole.common.entity.order.OmsOrderItem;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
@FeignClient("order-service")
public interface OmsOrderItemService {
    //获取我的订单
    IPage<OmsOrderItem> listOrderItem(Integer pageSize, Integer pageNum);

}
