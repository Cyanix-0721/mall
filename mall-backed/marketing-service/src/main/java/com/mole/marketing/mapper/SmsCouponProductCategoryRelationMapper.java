package com.mole.marketing.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mole.common.entity.marketing.SmsCouponProductCategoryRelation;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface SmsCouponProductCategoryRelationMapper extends BaseMapper<SmsCouponProductCategoryRelation> {

}