package com.mole.order.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mole.common.entity.order.OmsOrderItem;
import com.mole.common.entity.order.OmsOrderReturnApply;
import com.mole.order.service.OmsOrderItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/OrderItem")
@RequiredArgsConstructor
public class OmsOrderItemController {
    private final OmsOrderItemService orderReturnApplyService;
    @GetMapping("/list")
    public ResponseEntity<IPage<OmsOrderItem>> list(@RequestParam(defaultValue = "10") Integer pageSize,
                                                    @RequestParam(defaultValue = "1") Integer pageNum) {
        IPage<OmsOrderItem> returnReasons = orderReturnApplyService.listOrderItem(pageSize, pageNum);
        return ResponseEntity.ok(returnReasons);
    }
}
