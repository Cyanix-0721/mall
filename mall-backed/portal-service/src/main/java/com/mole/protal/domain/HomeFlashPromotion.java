package com.mole.protal.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * 首页秒杀场次信息封装
 */
@Getter
@Setter
public class HomeFlashPromotion {

    @Schema(description = "本场开始时间")
    private Date startTime;

    @Schema(description = "本场结束时间")
    private Date endTime;

    @Schema(description = "下一场开始时间")
    private Date nextStartTime;

    @Schema(description = "下一场结束时间")
    private Date nextEndTime;

    @Schema(description = "关联的秒杀商品列表")
    private List<FlashPromotionProduct> productList;
}