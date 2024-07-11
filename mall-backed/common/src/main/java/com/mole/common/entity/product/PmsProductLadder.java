package com.mole.common.entity.product;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.math.BigDecimal;

@Schema(description = "商品阶梯价格实体")
public class PmsProductLadder implements Serializable {

    @Schema(description = "阶梯价格ID", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "关联商品ID", example = "1001", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long productId;

    @Schema(description = "满足的商品数量", example = "5", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer count;

    @Schema(description = "折扣", example = "0.9", requiredMode = Schema.RequiredMode.REQUIRED)
    private BigDecimal discount;

    @Schema(description = "折后价格", example = "99.99", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private BigDecimal price;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", productId=").append(productId);
        sb.append(", count=").append(count);
        sb.append(", discount=").append(discount);
        sb.append(", price=").append(price);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
