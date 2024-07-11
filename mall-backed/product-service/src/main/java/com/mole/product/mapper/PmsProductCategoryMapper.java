package com.mole.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mole.common.entity.product.PmsProductCategory;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 商品分类Mapper接口
 */
@Repository
public interface PmsProductCategoryMapper extends BaseMapper<PmsProductCategory> {

    /**
     * 查询所有商品分类
     *
     * @return 商品分类列表
     */
    @Select("SELECT * FROM pms_product_category")
    List<PmsProductCategory> selectAll();

}
