package com.mole.marketing.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.mole.common.entity.marketing.SmsFlashPromotion;
//import com.mole.marketing.entity.SmsFlashPromotionExample;
//import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
//import java.util.List;
@Repository
@Mapper
public interface SmsFlashPromotionMapper extends BaseMapper<SmsFlashPromotion>{

}