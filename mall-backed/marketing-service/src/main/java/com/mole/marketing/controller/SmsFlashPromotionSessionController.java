package com.mole.marketing.controller;

import com.mole.common.dto.marketing.SmsFlashPromotionSessionDetail;
import com.mole.common.entity.marketing.SmsFlashPromotionSession;
import com.mole.marketing.service.SmsFlashPromotionSessionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "闪购场次管理", description = "闪购场次相关的CRUD操作")
@RestController
@RequestMapping("/flash-promotion-sessions")
public class SmsFlashPromotionSessionController {

    private final SmsFlashPromotionSessionService promotionSessionService;

    @Autowired
    public SmsFlashPromotionSessionController(SmsFlashPromotionSessionService promotionSessionService) {
        this.promotionSessionService = promotionSessionService;
    }

    @Operation(summary = "创建闪购场次", description = "新增一条闪购场次记录")
    @ApiResponse(responseCode = "200", description = "创建成功", content = @Content(schema = @Schema(type = "integer")))
    @PostMapping
    public int create(@RequestBody @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(schema = @Schema(implementation = SmsFlashPromotionSession.class)))
                              SmsFlashPromotionSession promotionSession) {
        return promotionSessionService.create(promotionSession);
    }

    @Operation(summary = "更新闪购场次", description = "根据ID更新闪购场次信息")
    @ApiResponse(responseCode = "200", description = "更新成功", content = @Content(schema = @Schema(type = "integer")))
    @PutMapping("/{id}")
    public int update(@Parameter(description = "场次ID", required = true) @PathVariable Long id,
                      @RequestBody SmsFlashPromotionSession promotionSession) {
        return promotionSessionService.update(id, promotionSession);
    }

    @Operation(summary = "更新场次状态", description = "根据ID更新闪购场次状态")
    @ApiResponse(responseCode = "200", description = "状态更新成功", content = @Content(schema = @Schema(type = "integer")))
    @PatchMapping("/{id}/status")
    public int updateStatus(@Parameter(description = "场次ID", required = true) @PathVariable Long id,
                            @Parameter(description = "新状态", required = true) @RequestParam Integer status) {
        return promotionSessionService.updateStatus(id, status);
    }

    @Operation(summary = "删除闪购场次", description = "根据ID删除闪购场次")
    @ApiResponse(responseCode = "200", description = "删除成功", content = @Content(schema = @Schema(type = "integer")))
    @DeleteMapping("/{id}")
    public int delete(@Parameter(description = "场次ID", required = true) @PathVariable Long id) {
        return promotionSessionService.delete(id);
    }

    @Operation(summary = "获取闪购场次详情", description = "根据ID获取闪购场次详细信息")
    @ApiResponse(responseCode = "200", description = "查询成功", content = @Content(schema = @Schema(implementation = SmsFlashPromotionSession.class)))
    @GetMapping("/{id}")
    public SmsFlashPromotionSession getItem(@Parameter(description = "场次ID", required = true) @PathVariable Long id) {
        return promotionSessionService.getItem(id);
    }

    @Operation(summary = "获取闪购场次列表", description = "获取所有闪购场次列表")
    @ApiResponse(responseCode = "200", description = "查询成功", content = @Content(array = @ArraySchema(schema = @Schema(implementation = SmsFlashPromotionSession.class))))
    @GetMapping
    public List<SmsFlashPromotionSession> list() {
        return promotionSessionService.list();
    }

    @Operation(summary = "根据促销活动ID查询场次详情", description = "根据促销活动ID获取所有相关的闪购场次详细信息")
    @ApiResponse(responseCode = "200", description = "查询成功", content = @Content(array = @ArraySchema(schema = @Schema(implementation = SmsFlashPromotionSessionDetail.class))))
    @GetMapping("/details")
    public ResponseEntity<List<SmsFlashPromotionSessionDetail>> selectList(@Parameter(description = "促销活动ID", required = false) @RequestParam Long id) {
        List<SmsFlashPromotionSessionDetail> details = promotionSessionService.selectList(id);
        return new ResponseEntity<>(details, HttpStatus.OK);
    }
}
