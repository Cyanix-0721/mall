package com.mole.marketing.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mole.common.entity.marketing.SmsFlashPromotionSession;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Mapper
public interface SmsFlashPromotionSessionMapper extends BaseMapper<SmsFlashPromotionSession> {



    @Select("SELECT * FROM sms_flash_promotion_session WHERE id = #{id}")
    List<SmsFlashPromotionSession> getSmsFlashPromotionSessionDetailsByPromotionId(@Param("id") Long id);
}