package com.mole.order.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mole.order.dto.OmsOrderQueryParam;
import com.mole.common.entity.order.OmsOrder;
import com.mole.common.entity.order.OmsOrderOperateHistory;
import com.mole.order.mapper.OmsOrderMapper;
import com.mole.order.mapper.OmsOrderOperateHistoryMapper;
import com.mole.order.service.OmsOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 *订单管理service实现类
 */
@Service
@RequiredArgsConstructor
public class OmsOrderServiceImpl implements OmsOrderService {

    private final OmsOrderMapper orderMapper;



    private final OmsOrderOperateHistoryMapper operateHistoryMapper;

    @Override
    public List<OmsOrder> findAll() {
        return orderMapper.selectList(null);
    }

    @Override
    public OmsOrder findById(Long id) {
        return orderMapper.selectById(id);
    }

    /*
    * 非空的字段：id,member_id,receiver_name,receiver_phone,delate_status
    * */
    @Override
    public int createOrder(OmsOrder order) {
        // 验证手机号是否为11位
        if (order.getReceiverPhone() == null || order.getReceiverPhone().length() != 11) {
            throw new IllegalArgumentException("手机号必须为11位");
        }
        orderMapper.insert(order);
        return order.getId().intValue();
    }

    @Override
    public boolean deleteById(Long id) {
        return orderMapper.deleteById(id) > 0;
    }

    @Override
    public boolean updateOrder(OmsOrder order) {
        return orderMapper.updateById(order) > 0;
    }

    @Override
    public IPage<OmsOrder> list(OmsOrderQueryParam queryParam, Integer pageSize, Integer pageNum) {
        // 创建分页对象
        IPage<OmsOrder> page = new Page<>(pageNum, pageSize);

        // 构建查询条件
        QueryWrapper<OmsOrder> queryWrapper = new QueryWrapper<>();

        // 设置查询条件，根据OmsOrderQueryParam填充
        if (queryParam.getOrderSn() != null) {
            queryWrapper.like("order_sn", queryParam.getOrderSn());
        }
        if (queryParam.getStatus() != null) {
            queryWrapper.eq("status", queryParam.getStatus());
        }
        if(queryParam.getReceiverKeyword() != null){
            queryWrapper.and(wrapper -> wrapper.like("receiver_name", queryParam.getReceiverKeyword())
                    .or().like("receiver_phone", queryParam.getReceiverKeyword()));
        }

        System.out.println("queryParam = " + queryParam);
        // 根据OmsOrderQueryParam的其他字段继续设置条件
        // 执行分页查询
        return orderMapper.selectPage(page, queryWrapper);
    }

    @Transactional
    @Override
    public int delivery(List<Long> orderIds) {


        // 更新订单状态
        int updatedCount = orderMapper.delivery(orderIds);

        // 添加操作记录
        for (Long orderId : orderIds) {
            OmsOrderOperateHistory history = new OmsOrderOperateHistory();
            history.setOrderId(orderId);
            history.setOperateMan("后台管理员");  // 或其他操作用户标识
            history.setCreateTime(LocalDateTime.now());
            history.setOrderStatus(2);  // 新状态
            history.setNote("完成发货");
            operateHistoryMapper.insert(history);
        }

        return updatedCount;
    }

    @Transactional
    @Override
    public int batchDeleteOrdersAndLogHistory(List<Long> orderIds) {
        // 批量删除订单
        int deletedCount = orderMapper.batchDelete(orderIds);

        // 添加操作记录
        for (Long orderId : orderIds) {
            OmsOrderOperateHistory history = new OmsOrderOperateHistory();
            history.setOrderId(orderId);
            history.setOperateMan("后台管理员");  // 或其他操作用户标识
            history.setCreateTime(LocalDateTime.now());
            history.setOrderStatus(0);  // 删除状态
            history.setNote("订单删除");
            operateHistoryMapper.insert(history);
        }

        return deletedCount;
    }

}