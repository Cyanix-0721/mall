package com.mole.order.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mole.order.dto.UpdateStatusRequest;
import com.mole.common.entity.order.OmsOrderReturnReason;
import com.mole.order.service.OmsOrderReturnReasonService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 处理退货原因相关请求的Rest控制器。
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/returnReasons")
public class OmsOrderReturnReasonController {

    private final OmsOrderReturnReasonService omsOrderReturnReasonService;

    @Operation(summary = "创建退货原因")
    @PostMapping
    public ResponseEntity<String> create(@RequestBody OmsOrderReturnReason omsOrderReturnReason) {
        int count = omsOrderReturnReasonService.create(omsOrderReturnReason);
        return count > 0 ? ResponseEntity.ok("退货原因创建成功。") : ResponseEntity.status(500).body("退货原因创建失败。");
    }

    @Operation(summary = "更新退货原因")
    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody OmsOrderReturnReason omsOrderReturnReason) {
        int count = omsOrderReturnReasonService.update(id, omsOrderReturnReason);
        return count > 0 ? ResponseEntity.ok("退货原因更新成功。") : ResponseEntity.status(500).body("退货原因更新失败。");
    }

    @Operation(summary = "批量删除退货原因")
    @PostMapping("/batchDelete")
    public ResponseEntity<String> delete(@RequestBody List<Long> ids) {
        int count = omsOrderReturnReasonService.delete(ids);
        return count > 0 ? ResponseEntity.ok("成功删除了 " + count + " 个退货原因。") : ResponseEntity.status(500).body("删除退货原因失败。");
    }

    @Operation(summary = "分页获取退货原因列表")
    @GetMapping("/list")
    public ResponseEntity<IPage<OmsOrderReturnReason>> reasonList(@RequestParam(defaultValue = "10") Integer pageSize,
                                                            @RequestParam(defaultValue = "1") Integer pageNum) {
        IPage<OmsOrderReturnReason> returnReasons = omsOrderReturnReasonService.reasonList(pageSize, pageNum);
        return ResponseEntity.ok(returnReasons);
    }

    @Operation(summary = "批量更新退货原因状态")
    @PutMapping("/batchUpdateStatus")
    public ResponseEntity<String> updateReasonStatus(@RequestBody UpdateStatusRequest request) {
        List<Long> ids = request.getIds();
        Integer status = request.getStatus();

        try {
            int updatedCount = omsOrderReturnReasonService.updateStatus(ids, status);

            if (updatedCount > 0) {
                return ResponseEntity.ok("成功更新了 " + updatedCount + " 个退货原因的状态。");
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            // 记录日志或处理异常，这里简化处理
            return ResponseEntity.status(500).body("更新过程中发生错误：" + e.getMessage());
        }
    }

    @Operation(summary = "获取单个退货原因详情")
    @GetMapping("/{id}")
    public ResponseEntity<OmsOrderReturnReason> getItem(@PathVariable Long id) {
        OmsOrderReturnReason omsOrderReturnReason = omsOrderReturnReasonService.getItem(id);
        return omsOrderReturnReason != null ? ResponseEntity.ok(omsOrderReturnReason) : ResponseEntity.status(404).body(null);
    }
}
