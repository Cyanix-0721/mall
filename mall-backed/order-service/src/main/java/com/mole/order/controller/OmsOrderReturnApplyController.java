package com.mole.order.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mole.common.entity.order.OmsOrderReturnApply;
import com.mole.common.entity.order.OmsOrderReturnReason;
import com.mole.order.service.OmsOrderReturnApplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/returnApply")
public class OmsOrderReturnApplyController {

    private final OmsOrderReturnApplyService orderReturnApplyService;

    @GetMapping("/list")
    public ResponseEntity<IPage<OmsOrderReturnApply>> returnApplyList(@RequestParam(defaultValue = "10") Integer pageSize,
                                                            @RequestParam(defaultValue = "1") Integer pageNum) {
        IPage<OmsOrderReturnApply> returnReasons = orderReturnApplyService.returnApplyList(pageSize, pageNum);
        return ResponseEntity.ok(returnReasons);
    }

    @DeleteMapping("/delete")
    public boolean batchDeleteOrderReturnApplies(@RequestBody List<Long> ids) {
        return orderReturnApplyService.batchDeleteApplys(ids);
    }

    @PutMapping("/update")
    public boolean updateOrderReturnApplyStatus(@RequestParam Long id, @RequestParam Integer status) {
        return orderReturnApplyService.updateApplyStatus(id, status);
    }
    @PostMapping("/submit")
    public ResponseEntity<String> submitReturnApply(@RequestBody OmsOrderReturnApply returnApply) {
        boolean result = orderReturnApplyService.submitReturnApply(returnApply);
        if (result) {
            return ResponseEntity.ok("退货申请提交成功！");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("提交退货申请失败！");
        }
    }
}
