package com.mole.protal.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mole.common.entity.portal.PmsBrand;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PmsBrandMapper extends BaseMapper<PmsBrand> {

    /**
     * 获取推荐品牌
     */
    @Select("SELECT * FROM pms_brand LIMIT #{offset}, #{limit}")
    List<PmsBrand> getRecommendBrandList(@Param("offset") Integer offset, @Param("limit") Integer limit);


}