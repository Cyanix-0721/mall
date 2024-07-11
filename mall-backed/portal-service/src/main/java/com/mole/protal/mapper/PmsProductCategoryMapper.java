package com.mole.protal.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mole.common.entity.portal.PmsProductCategory;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PmsProductCategoryMapper extends BaseMapper<PmsProductCategory>{
    /**
     * 根据父类别ID和展示状态获取商品分类列表，并按sort字段降序排列
     *
     * @param parentId 父类别ID
     * @return 商品分类列表
     */
    @Select("SELECT * FROM pms_product_category WHERE show_status = 1 AND parent_id = #{parentId} ORDER BY sort DESC")
    List<PmsProductCategory> getProductCateList(@Param("parentId") Long parentId);

}
