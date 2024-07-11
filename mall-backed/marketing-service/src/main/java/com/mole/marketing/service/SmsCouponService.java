package com.mole.marketing.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mole.common.entity.marketing.SmsCoupon;

import java.util.List;
public interface SmsCouponService {
   List<SmsCoupon> findAll();
   boolean save(SmsCoupon smsCoupon);

   boolean updateById(SmsCoupon smsCoupon);

   boolean removeById(Long id);

   SmsCoupon getById(Long id);
   /**
    * 根据优惠券类型查找优惠券
    *
    * @param type 优惠券类型
    * @return 匹配类型的优惠券列表
    */
   List<SmsCoupon> findByType(Integer type);

   /**
    * 根据优惠券名称查找优惠券
    *
    * @param name 优惠券名称
    * @return 匹配名称的优惠券列表
    */
   List<SmsCoupon> findByName(String name);

   /**
    * 根据平台类型查找优惠券
    *
    * @param platform 平台类型
    * @return 匹配平台类型的优惠券列表
    */
   List<SmsCoupon> findByPlatform(Integer platform);
   /**
    * 分页获取优惠券列表
    */
   IPage<SmsCoupon> list(String name, Integer type, Integer pageSize, Integer pageNum);
}
