package com.mole.protal.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mole.common.entity.portal.PmsProduct;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PmsProductMapper extends BaseMapper<PmsProduct> {
    // 使用@Select注解来编写SQL查询语句
    @Select("SELECT * FROM pms_product WHERE delete_status = #{deleteStatus} AND publish_status = #{publishStatus}")
    List<PmsProduct> selectByStatus(@Param("deleteStatus") Integer deleteStatus, @Param("publishStatus") Integer publishStatus);
    /**
     * 获取人气推荐
     */
    @Select("SELECT * FROM pms_product  ORDER BY sale DESC LIMIT #{offset}, #{limit}")
    List<PmsProduct> getHotProductList(@Param("offset") Integer offset,@Param("limit") Integer limit);
    /**
     * 获取新品推荐
     */
    @Select("SELECT * FROM pms_product WHERE new_status = 1 ORDER BY sort DESC LIMIT #{offset}, #{limit}")
    List<PmsProduct> getNewProductList(@Param("offset") Integer offset,@Param("limit") Integer limit);

}
