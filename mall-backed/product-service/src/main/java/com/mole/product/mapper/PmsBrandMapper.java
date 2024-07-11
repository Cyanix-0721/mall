package com.mole.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mole.common.entity.product.PmsBrand;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 商品品牌Mapper接口
 */
@Repository
public interface PmsBrandMapper extends BaseMapper<PmsBrand> {

    /**
     * 查询推荐品牌列表
     * 假设showStatus值为1表示品牌被推荐
     *
     * @param offset 分页偏移量
     * @param limit 分页大小
     * @return 推荐品牌列表
     */
    @Select("SELECT * FROM pms_brand WHERE show_status = 1 ORDER BY sort DESC LIMIT #{offset}, #{limit}")
    List<PmsBrand> getRecommendBrandList(@Param("offset") Integer offset, @Param("limit") Integer limit);

}
