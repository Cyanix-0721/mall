package com.mole.common.entity.marketing;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.math.BigDecimal;

public class SmsFlashPromotionProductRelation implements Serializable {

    @Schema(description = "编号")
    private Long id;

    @Schema(description = "限时购活动ID")
    private Long flashPromotionId;

    @Schema(description = "限时购场次ID")
    private Long flashPromotionSessionId;

    @Schema(description = "商品ID")
    private Long productId;

    @Schema(description = "限时购价格")
    private BigDecimal flashPromotionPrice;

    @Schema(description = "限时购数量")
    private Integer flashPromotionCount;

    @Schema(description = "每人限购数量")
    private Integer flashPromotionLimit;

    @Schema(description = "排序")
    private Integer sort;

    private static final long serialVersionUID = 1L;

    public SmsFlashPromotionProductRelation(Long id, Long flashPromotionId, Long flashPromotionSessionId, Long productId, BigDecimal flashPromotionPrice, Integer flashPromotionCount, Integer flashPromotionLimit, Integer sort) {
        this.id = id;
        this.flashPromotionId = flashPromotionId;
        this.flashPromotionSessionId = flashPromotionSessionId;
        this.productId = productId;
        this.flashPromotionPrice = flashPromotionPrice;
        this.flashPromotionCount = flashPromotionCount;
        this.flashPromotionLimit = flashPromotionLimit;
        this.sort = sort;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFlashPromotionId() {
        return flashPromotionId;
    }

    public void setFlashPromotionId(Long flashPromotionId) {
        this.flashPromotionId = flashPromotionId;
    }

    public Long getFlashPromotionSessionId() {
        return flashPromotionSessionId;
    }

    public void setFlashPromotionSessionId(Long flashPromotionSessionId) {
        this.flashPromotionSessionId = flashPromotionSessionId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public BigDecimal getFlashPromotionPrice() {
        return flashPromotionPrice;
    }

    public void setFlashPromotionPrice(BigDecimal flashPromotionPrice) {
        this.flashPromotionPrice = flashPromotionPrice;
    }

    public Integer getFlashPromotionCount() {
        return flashPromotionCount;
    }

    public void setFlashPromotionCount(Integer flashPromotionCount) {
        this.flashPromotionCount = flashPromotionCount;
    }

    public Integer getFlashPromotionLimit() {
        return flashPromotionLimit;
    }

    public void setFlashPromotionLimit(Integer flashPromotionLimit) {
        this.flashPromotionLimit = flashPromotionLimit;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    @Override
    public String toString() {
        return "SmsFlashPromotionProductRelation{" +
                "id=" + id +
                ", flashPromotionId=" + flashPromotionId +
                ", flashPromotionSessionId=" + flashPromotionSessionId +
                ", productId=" + productId +
                ", flashPromotionPrice=" + flashPromotionPrice +
                ", flashPromotionCount=" + flashPromotionCount +
                ", flashPromotionLimit=" + flashPromotionLimit +
                ", sort=" + sort +
                '}';
    }
}
