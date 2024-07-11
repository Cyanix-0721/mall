package com.mole.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mole.common.entity.product.PmsProductAttribute;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 商品属性Mapper接口
 */
@Repository
public interface PmsProductAttributeMapper extends BaseMapper<PmsProductAttribute> {

    @Select("SELECT * FROM pms_product_attribute WHERE product_attribute_category_id = #{categoryId}")
    List<PmsProductAttribute> selectAttributesByCategoryId(@Param("categoryId") Long categoryId);

}
