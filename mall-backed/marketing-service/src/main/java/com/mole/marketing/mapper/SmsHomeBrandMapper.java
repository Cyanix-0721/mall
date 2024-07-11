package com.mole.marketing.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mole.marketing.entity.SmsHomeBrand;
import com.mole.marketing.entity.SmsHomeNewProduct;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
@Mapper
public interface SmsHomeBrandMapper extends BaseMapper<SmsHomeBrand> {


    int batchInsert(@Param("list") List<SmsHomeBrand> homeBrandList);
}