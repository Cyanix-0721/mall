package com.mole.protal.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mole.common.entity.portal.SmsHomeAdvertise;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SmsHomeAdvertiseMapper extends BaseMapper<SmsHomeAdvertise> {
   /**
    * 查询类型为特定值且状态为启用的首页广告列表，并按排序字段降序排列。
    *
    * @param type 广告类型，这里固定为1
    * @param status 广告状态，这里固定为1表示启用
    * @return 符合条件的首页广告列表
    */

   @Select("SELECT * FROM sms_home_advertise WHERE type = #{type} AND status = #{status} ORDER BY id DESC")
   List<SmsHomeAdvertise> selectByTypeAndStatusOrderByIdDesc(@Param("type") Integer type,
                                                             @Param("status") Integer status);
}