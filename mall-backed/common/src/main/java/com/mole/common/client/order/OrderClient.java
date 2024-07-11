package com.mole.common.client.order;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mole.common.dto.order.UpdateStatusRequest;
import com.mole.common.entity.Order;
import com.mole.common.entity.order.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@FeignClient("order-service")
public interface OrderClient {

    @GetMapping("/CompanyAddress")
    List<OmsCompanyAddress> addressFindAll();

    @GetMapping("/orders")
    List<Order> findAll();

    @GetMapping("/orders/{id}")
    OmsOrder findById(@PathVariable Long id);

    @PostMapping("/orders")
    ResponseEntity createOrder(@Valid @RequestBody OmsOrder order);

    @DeleteMapping("/orders/{id}")
    boolean deleteById(@PathVariable Long id);

    @PutMapping("/orders/{id}")
    boolean updateOrder(@PathVariable Long id, @RequestBody OmsOrder order);

    @GetMapping("/orders/list")
    IPage<OmsOrder> listOrders(
            @RequestParam(value = "orderSn", required = false) String orderSn,
            @RequestParam(value = "status", required = false) Integer status,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum);

    @PostMapping("/orders/delivery")
    ResponseEntity<String> updateOrderStatusToDelivered(@RequestBody List<Long> orderIds);

    @PostMapping("/orders/batchDelete")
    ResponseEntity<String> batchDeleteOrders(@RequestBody List<Long> orderIds);

    @GetMapping("/OrderItem/list")
    ResponseEntity<IPage<OmsOrderItem>> list(@RequestParam(defaultValue = "10") Integer pageSize,
                                             @RequestParam(defaultValue = "1") Integer pageNum);

    @GetMapping("/operateHistories/list")
    ResponseEntity<IPage<OmsOrderOperateHistory>> operateList(@RequestParam(defaultValue = "10") Integer pageSize,
                                                              @RequestParam(defaultValue = "1") Integer pageNum);

    @GetMapping("/returnApply/list")
    ResponseEntity<IPage<OmsOrderReturnApply>> returnApplyList(@RequestParam(defaultValue = "10") Integer pageSize,
                                                           @RequestParam(defaultValue = "1") Integer pageNum);

    @DeleteMapping("/returnApply/delete")
    boolean batchDeleteOrderReturnApplies(@RequestBody List<Long> ids);

    @PutMapping("/returnApply/update")
    boolean updateOrderReturnApplyStatus(@RequestParam Long id, @RequestParam Integer status);

    @PostMapping("/returnApply/submit")
    ResponseEntity<String> submitReturnApply(@RequestBody OmsOrderReturnApply returnApply);

    @PostMapping("/returnReasons")
    ResponseEntity<String> create(@RequestBody OmsOrderReturnReason omsOrderReturnReason);

    @PutMapping("/returnReasons/{id}")
    ResponseEntity<String> update(@PathVariable Long id, @RequestBody OmsOrderReturnReason omsOrderReturnReason);

    @PostMapping("/returnReasons/batchDelete")
    ResponseEntity<String> delete(@RequestBody List<Long> ids);

    @GetMapping("/returnReasons/list")
    ResponseEntity<IPage<OmsOrderReturnReason>> reasonList(@RequestParam(defaultValue = "10") Integer pageSize,
                                                           @RequestParam(defaultValue = "1") Integer pageNum);

    @PutMapping("/returnReasons/batchUpdateStatus")
    ResponseEntity<String> updateReasonStatus(@RequestBody UpdateStatusRequest request);

    @GetMapping("/orderSetting/{id}")
    ResponseEntity<OmsOrderSetting> getItem(@PathVariable Long id);



    }