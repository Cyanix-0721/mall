package com.mole.order.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mole.order.dto.OmsOrderQueryParam;
import com.mole.common.entity.order.OmsOrder;
import com.mole.order.service.OmsOrderService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * 这是一个处理订单相关请求的Rest控制器。
 * 它使用ProductClient进行HTTP请求。
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OmsOrderController {

    //private final OrderClient orderClient;
    //private final LoadBalancerClient loadBalancerClient;
    private final OmsOrderService orderService;

    @GetMapping
    public List<OmsOrder> findAll() {
        List<OmsOrder> orders = orderService.findAll();
        return orders;
    }

    @GetMapping("/{id}")
    public OmsOrder findById(@PathVariable Long id) {
        OmsOrder order = orderService.findById(id);
        return order;
    }

    @PostMapping
    public ResponseEntity createOrder(@Valid @RequestBody OmsOrder order) {
        try {
            orderService.createOrder(order);
            return ResponseEntity.ok("订单创建成功！");
        } catch (ConstraintViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @DeleteMapping("/{id}")
    public boolean deleteById(@PathVariable Long id) {
        return orderService.deleteById(id);
    }

    @PutMapping("/{id}")
    public boolean updateOrder(@PathVariable Long id, @RequestBody OmsOrder order) {
        order.setId(id); // 设置订单的ID，确保更新的是指定ID的订单
        return orderService.updateOrder(order);
    }

    @GetMapping("/list")
    public IPage<OmsOrder> listOrders(
            @RequestParam(value = "orderSn", required = false) String orderSn,
            @RequestParam(value = "status", required = false) Integer status,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {

        OmsOrderQueryParam queryParam = new OmsOrderQueryParam();
        if (orderSn != null) {
            queryParam.setOrderSn(orderSn);
        }
        if (status != null) {
            queryParam.setStatus(status);
        }

        return orderService.list(queryParam, pageSize, pageNum);
    }

    @PostMapping("/delivery")
    public ResponseEntity<String> updateOrderStatusToDelivered(@RequestBody List<Long> orderIds) {
        int count = orderService.delivery(orderIds);
        return ResponseEntity.ok("Successfully updated status for " + count + " orders.");
    }


    @PostMapping("/batchDelete")
    public ResponseEntity<String> batchDeleteOrders(@RequestBody List<Long> orderIds) {
        int count = orderService.batchDeleteOrdersAndLogHistory(orderIds);
        return ResponseEntity.ok("Successfully deleted " + count + " orders.");
    }
}

