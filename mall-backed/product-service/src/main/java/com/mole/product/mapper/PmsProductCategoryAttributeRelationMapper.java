package com.mole.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mole.common.entity.product.PmsProductCategoryAttributeRelation;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 商品分类与属性关系Mapper接口
 */
@Repository
public interface PmsProductCategoryAttributeRelationMapper extends BaseMapper<PmsProductCategoryAttributeRelation> {

    // 批量插入商品分类属性关系的方法
    @Insert("<script>"
            + "INSERT INTO pms_product_category_attribute_relation(product_category_id, product_attribute_id)"
            + " VALUES "
            + "<foreach item='relation' collection='relations' separator=','>"
            + "(#{relation.productCategoryId}, #{relation.productAttributeId})"
            + "</foreach>"
            + "</script>")
    int insertBatch(@Param("relations") List<PmsProductCategoryAttributeRelation> relations);

}
