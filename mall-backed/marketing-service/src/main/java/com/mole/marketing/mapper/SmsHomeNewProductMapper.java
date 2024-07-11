package com.mole.marketing.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mole.marketing.entity.SmsHomeNewProduct;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import org.springframework.stereotype.Repository;
@Repository
@Mapper
public interface SmsHomeNewProductMapper extends BaseMapper<SmsHomeNewProduct> {
    int insertBatch(@Param("list") List<SmsHomeNewProduct> homeNewProductList);
}