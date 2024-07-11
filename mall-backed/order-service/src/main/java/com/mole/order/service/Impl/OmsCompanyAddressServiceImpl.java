package com.mole.order.service.Impl;

import com.mole.common.entity.order.OmsCompanyAddress;
import com.mole.order.mapper.OmsCompanyAddressMapper;
import com.mole.order.service.OmsCompanyAddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 收货地址管理Service实现类
 */
@Service
@RequiredArgsConstructor
public class OmsCompanyAddressServiceImpl implements OmsCompanyAddressService {

    private final OmsCompanyAddressMapper companyAddressMapper;

    @Override
    public List<OmsCompanyAddress> addressFindAll() {
        return companyAddressMapper.selectList(null);
    }
}
