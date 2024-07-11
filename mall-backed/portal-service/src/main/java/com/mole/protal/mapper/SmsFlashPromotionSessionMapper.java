package com.mole.protal.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mole.common.entity.portal.SmsFlashPromotionSession;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;

@Mapper
public interface SmsFlashPromotionSessionMapper extends BaseMapper<SmsFlashPromotionSessionMapper> {


        @Select("SELECT * FROM sms_flash_promotion_session WHERE start_time > #{date} ORDER BY start_time ASC LIMIT 1")
        SmsFlashPromotionSession getNextFlashPromotionSessionByDate(@Param("date") Date date);

    @Select("SELECT * FROM sms_flash_promotion_session " +
            "WHERE start_time <= #{currTime} " +
            "AND end_time >= #{currTime} " +
            "ORDER BY start_time ASC LIMIT 1")
    SmsFlashPromotionSession findOngoingSession(@Param("currTime") Date currTime);
}


