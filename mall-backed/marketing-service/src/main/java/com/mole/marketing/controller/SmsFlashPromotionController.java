package com.mole.marketing.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mole.common.entity.marketing.SmsFlashPromotion;
import com.mole.marketing.service.SmsFlashPromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/flashPromotions")
public class SmsFlashPromotionController {

    private final SmsFlashPromotionService smsFlashPromotionService;

    @Autowired
    public SmsFlashPromotionController(SmsFlashPromotionService smsFlashPromotionService) {
        this.smsFlashPromotionService = smsFlashPromotionService;
    }

    /**
     * 添加活动
     */
    @PostMapping
    public int create(@RequestBody SmsFlashPromotion flashPromotion) {
        return smsFlashPromotionService.create(flashPromotion);
    }

    /**
     * 修改指定活动
     */
    @PutMapping("/{id}")
    public int update(@PathVariable Long id, @RequestBody SmsFlashPromotion flashPromotion) {
        return smsFlashPromotionService.update(id, flashPromotion);
    }

    /**
     * 删除单个活动
     */
    @DeleteMapping("/{id}")
    public int delete(@PathVariable Long id) {
        return smsFlashPromotionService.delete(id);
    }

    /**
     * 修改上下线状态
     */
    @PatchMapping("/{id}/status")
    public int updateStatus(@PathVariable Long id, @RequestParam Integer status) {
        return smsFlashPromotionService.updateStatus(id, status);
    }

    /**
     * 获取活动详情
     */
    @GetMapping("/{id}")
    public SmsFlashPromotion getItem(@PathVariable Long id) {
        return smsFlashPromotionService.getItem(id);
    }

    /**
     * 分页查询活动
     */
    @GetMapping
    public IPage<SmsFlashPromotion> list(@RequestParam(required = false) String keyword,
                                         @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                                         @RequestParam(required = false, defaultValue = "1") Integer pageNum) {
        return smsFlashPromotionService.list(keyword, pageSize, pageNum);
    }
}
