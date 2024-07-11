package com.mole.marketing.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mole.marketing.entity.SmsHomeRecommendSubject;
import com.mole.marketing.service.SmsHomeRecommendSubjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "首页推荐专题管理", description = "处理与首页推荐专题展示相关的业务逻辑")
@RestController
@RequestMapping("/api/recommend-subjects")
public class SmsHomeRecommendSubjectController {

    private final SmsHomeRecommendSubjectService smsHomeRecommendSubjectService;

    @Autowired
    public SmsHomeRecommendSubjectController(SmsHomeRecommendSubjectService smsHomeRecommendSubjectService) {
        this.smsHomeRecommendSubjectService = smsHomeRecommendSubjectService;
    }

    @Operation(summary = "添加推荐专题", description = "批量添加专题到首页推荐展示")
    @ApiResponse(responseCode = "200", description = "添加成功", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)))
    @PostMapping
    @Transactional
    public ResponseEntity<String> create(@RequestBody List<SmsHomeRecommendSubject> recommendSubjectList) {
        int result = smsHomeRecommendSubjectService.create(recommendSubjectList);
        return result > 0 ? ResponseEntity.ok("Recommended subjects added successfully.") : ResponseEntity.badRequest().body("Failed to add recommended subjects.");
    }


    @Operation(summary = "更新排序", description = "根据ID更新推荐专题的展示排序")
    @ApiResponse(responseCode = "200", description = "排序更新成功", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)))
    @PutMapping("/{id}/sort")
    public ResponseEntity<String> updateSort(@PathVariable Long id, @RequestParam Integer sort) {
        int result = smsHomeRecommendSubjectService.updateSort(id, sort);
        return result > 0 ? ResponseEntity.ok("Sort updated successfully.") : ResponseEntity.badRequest().body("Failed to update sort.");
    }

    @Operation(summary = "移除推荐专题", description = "根据ID列表批量移除专题的推荐展示")
    @ApiResponse(responseCode = "200", description = "移除成功", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)))
    @DeleteMapping
    public ResponseEntity<String> delete(@RequestBody List<Long> ids) {
        int result = smsHomeRecommendSubjectService.delete(ids);
        return result > 0 ? ResponseEntity.ok("Subjects removed from recommendation successfully.") : ResponseEntity.badRequest().body("Failed to remove subjects from recommendation.");
    }

    @Operation(summary = "更新推荐状态", description = "批量更新推荐专题的推荐状态")
    @ApiResponse(responseCode = "200", description = "状态更新成功", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)))
    @PatchMapping("/status")
    public ResponseEntity<String> updateRecommendStatus(@RequestBody List<Long> ids, @RequestParam Integer recommendStatus) {
        int result = smsHomeRecommendSubjectService.updateRecommendStatus(ids, recommendStatus);
        return result > 0 ? ResponseEntity.ok("Recommendation status updated successfully.") : ResponseEntity.badRequest().body("Failed to update recommendation status.");
    }

    @Operation(summary = "分页查询推荐专题列表", description = "根据条件分页查询推荐专题展示信息")
    @ApiResponse(responseCode = "200", description = "查询成功", content = @Content(mediaType = "application/json", schema = @Schema(implementation = IPage.class)))
    @GetMapping
    public ResponseEntity<IPage<SmsHomeRecommendSubject>> list(
            @RequestParam(required = false) String subjectName,
            @RequestParam(required = false) Integer recommendStatus,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "1") Integer pageNum) {
        IPage<SmsHomeRecommendSubject> page = smsHomeRecommendSubjectService.list(subjectName, recommendStatus, pageSize, pageNum);
        return ResponseEntity.ok(page);
    }
}
