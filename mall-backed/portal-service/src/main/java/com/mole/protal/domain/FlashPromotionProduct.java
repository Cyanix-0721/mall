package com.mole.protal.domain;

import com.mole.common.entity.portal.PmsProduct;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * 秒杀信息和商品对象封装
 */

@Getter
@Setter
@Schema(description = "秒杀商品信息")
public class FlashPromotionProduct extends PmsProduct {

    @Schema(description = "秒杀价格")
    private BigDecimal flashPromotionPrice;

    @Schema(description = "秒杀库存数量")
    private Integer flashPromotionCount;

    @Schema(description = "单用户秒杀限购数量")
    private Integer flashPromotionLimit;
}