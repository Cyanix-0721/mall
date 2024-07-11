package com.mole.marketing.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mole.marketing.entity.SmsHomeRecommendProduct;
//import com.mole.marketing.entity.SmsHomeRecommendProductExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

//import java.util.List;
@Repository
@Mapper
public interface SmsHomeRecommendProductMapper extends BaseMapper<SmsHomeRecommendProduct> {

    int insertBatchSomeColumn(@Param("list") List<SmsHomeRecommendProduct> homeRecommendProductList);
}