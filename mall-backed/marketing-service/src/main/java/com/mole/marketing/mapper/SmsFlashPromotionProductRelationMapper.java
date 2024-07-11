package com.mole.marketing.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
//import com.mole.marketing.entity.SmsFlashPromotionProductRelationExample;
import com.mole.common.entity.marketing.SmsFlashPromotionProductRelation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
@Mapper
public interface SmsFlashPromotionProductRelationMapper extends BaseMapper<SmsFlashPromotionProductRelation> {
    // 确保此方法存在，并且参数类型与服务层调用匹配。
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertBatch(@Param("list") List<SmsFlashPromotionProductRelation> list);

    /**
     * 根据活动和场次ID获取商品关联数量
     * @param flashPromotionId 限时购活动ID
     * @param flashPromotionSessionId 限时购场次ID
     * @return 商品关联数量
     */
    long getCountByPromotion(@Param("flashPromotionId") Long flashPromotionId,
                             @Param("flashPromotionSessionId") Long flashPromotionSessionId);
}