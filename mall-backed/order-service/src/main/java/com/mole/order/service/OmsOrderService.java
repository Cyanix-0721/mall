package com.mole.order.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mole.common.entity.order.OmsOrder;
import com.mole.order.dto.OmsOrderQueryParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Component
@FeignClient("order-service")
public interface OmsOrderService {

    //查询所有方法
    List<OmsOrder> findAll();

    //根据id查询方法
    OmsOrder findById(Long id);

    //增加方法
    int createOrder(OmsOrder omsorder);

    //删除方法（上面已经测试完不高兴修改这里改用返回值Boolean）
    boolean deleteById(Long id);

    //修改方法
    boolean updateOrder(OmsOrder omsorder);


    //分页查询订单
   IPage<OmsOrder> list(OmsOrderQueryParam queryParam, Integer pageSize, Integer pageNum);

   //批量发货
   int delivery(List<Long> orderIds);

   //批量删除
   int batchDeleteOrdersAndLogHistory(List<Long> orderIds);



}
