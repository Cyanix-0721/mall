package com.mole.protal.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mole.protal.domain.FlashPromotionProduct;
import com.mole.common.entity.portal.SmsFlashPromotion;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

@Mapper
public interface SmsFlashPromotionMapper extends BaseMapper<SmsFlashPromotionMapper> {

    /**
     * 获取秒杀商品
     */
    List<FlashPromotionProduct> getFlashProductList(@Param("flashPromotionId") Long flashPromotionId, @Param("sessionId") Long sessionId);



        @Select("SELECT * FROM sms_flash_promotion " +
                "WHERE status = 1 " +
                "AND start_date <= #{currDate} " +
                "AND end_date >= #{currDate} " +
                "ORDER BY start_date DESC LIMIT 1")
        SmsFlashPromotion getActiveFlashPromotionByDate(@Param("currDate") Date currDate);
    }
