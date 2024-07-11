package com.mole.order.service.Impl;

import com.mole.common.entity.order.OmsOrderSetting;
import com.mole.order.mapper.OmsOrderSettingMapper;
import com.mole.order.service.OmsOrderSettingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OmsOrderSettingServiceImpl implements OmsOrderSettingService {
    private final OmsOrderSettingMapper omsOrderSettingMapper;

    @Override
    public OmsOrderSetting getItem(Long id) {
        return omsOrderSettingMapper.selectByPrimaryKey(id);
    }

}
