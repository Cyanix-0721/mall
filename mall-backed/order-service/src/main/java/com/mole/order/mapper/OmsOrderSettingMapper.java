package com.mole.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mole.common.entity.order.OmsOrderSetting;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface OmsOrderSettingMapper extends BaseMapper<OmsOrderSetting> {

    @Select("SELECT * FROM oms_order_setting WHERE id = #{id}")
    OmsOrderSetting selectByPrimaryKey(Long id);

}
