package com.mole.order.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mole.common.entity.order.OmsOrderOperateHistory;
import com.mole.order.service.OmsOrderOperateHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/operateHistories")
public class OmsOrderOperateHistoryController {
    private final OmsOrderOperateHistoryService orderOperateHistoryService;

    @GetMapping("/list")
    public ResponseEntity<IPage<OmsOrderOperateHistory>> operateList(@RequestParam(defaultValue = "10") Integer pageSize,
                                                              @RequestParam(defaultValue = "1") Integer pageNum) {
        IPage<OmsOrderOperateHistory> operateHistory = orderOperateHistoryService.operateList(pageSize, pageNum);
        return ResponseEntity.ok(operateHistory);
    }
}
