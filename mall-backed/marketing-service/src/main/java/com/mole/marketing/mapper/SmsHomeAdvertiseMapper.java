package com.mole.marketing.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mole.marketing.entity.SmsHomeAdvertise;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface SmsHomeAdvertiseMapper extends BaseMapper<SmsHomeAdvertise> {

}