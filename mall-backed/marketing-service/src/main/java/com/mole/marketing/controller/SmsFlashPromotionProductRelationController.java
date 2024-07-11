package com.mole.marketing.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mole.common.entity.marketing.SmsFlashPromotionProductRelation;
import com.mole.marketing.service.SmsFlashPromotionProductRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/flashpromotionproductrelation")
public class SmsFlashPromotionProductRelationController {

    private final SmsFlashPromotionProductRelationService smsFlashPromotionProductRelationService;

    @Autowired
    public SmsFlashPromotionProductRelationController(SmsFlashPromotionProductRelationService smsFlashPromotionProductRelationService) {
        this.smsFlashPromotionProductRelationService = smsFlashPromotionProductRelationService;
    }

    /**
     * 批量添加关联
     */
    @PostMapping("/create")
    @ResponseBody
    @Transactional
    public String create(@RequestBody List<SmsFlashPromotionProductRelation> relationList) {
        int result = smsFlashPromotionProductRelationService.create(relationList);
        return "{\"status\":\"success\", \"message\":\"成功添加 " + result + " 条关联记录\"}";
    }

    /**
     * 修改关联信息
     */
    @PutMapping("/{id}")
    @ResponseBody
    public String update(@PathVariable Long id, @RequestBody SmsFlashPromotionProductRelation relation) {
        int result = smsFlashPromotionProductRelationService.update(id, relation);
        return "{\"status\":\"success\", \"message\":\"成功更新关联记录\"}";
    }

    /**
     * 删除关联
     */
    @DeleteMapping("/{id}")
    @ResponseBody
    public String delete(@PathVariable Long id) {
        int result = smsFlashPromotionProductRelationService.delete(id);
        return "{\"status\":\"success\", \"message\":\"成功删除关联记录\"}";
    }

    /**
     * 获取关联详情
     */
    @GetMapping("/{id}")
    @ResponseBody
    public SmsFlashPromotionProductRelation getItem(@PathVariable Long id) {
        return smsFlashPromotionProductRelationService.getItem(id);
    }

    /**
     * 根据活动和场次id获取商品关系数量
     */
    @GetMapping("/count")
    @ResponseBody
    public Long getCount(@RequestParam Long flashPromotionId, @RequestParam Long flashPromotionSessionId) {
        return smsFlashPromotionProductRelationService.getCount(flashPromotionId, flashPromotionSessionId);
    }

    /**
     * 分页查询关联信息
     */
    @GetMapping("/list")
    @ResponseBody
    public IPage<SmsFlashPromotionProductRelation> listByPromotionIds(
            @RequestParam(required = false) Long promotionId,
            @RequestParam(required = false) Long sessionId,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "1") Integer pageNum) {
        return smsFlashPromotionProductRelationService.listByPromotionIds(promotionId, sessionId, pageSize, pageNum);
    }
}
