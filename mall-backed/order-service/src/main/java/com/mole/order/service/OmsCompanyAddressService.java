package com.mole.order.service;

import com.mole.common.entity.order.OmsCompanyAddress;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 收货地址管理Service
 */
@Service
@Component
@FeignClient("order-service")
public interface OmsCompanyAddressService {
    /**
     * 获取全部收货地址
     * @return
     */
    List<OmsCompanyAddress> addressFindAll();
}

