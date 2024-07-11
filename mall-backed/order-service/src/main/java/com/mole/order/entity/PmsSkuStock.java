package com.mole.order.entity;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.math.BigDecimal;

public class PmsSkuStock implements Serializable {
    private Long id;

    private Long productId;

    @Schema(description = "SKU编码")
    private String skuCode;

    private BigDecimal price;

    @Schema(description = "库存")
    private Integer stock;

    @Schema(description = "预警库存")
    private Integer lowStock;

    @Schema(description = "展示图片")
    private String pic;

    @Schema(description = "销量")
    private Integer sale;

    @Schema(description = "单品促销价格")
    private BigDecimal promotionPrice;

    @Schema(description = "锁定库存")
    private Integer lockStock;

    @Schema(description = "商品销售属性，JSON格式")
    private String spData;

    private static final long serialVersionUID = 1L;

    // Getters and setters omitted for brevity

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", productId=").append(productId);
        sb.append(", skuCode=").append(skuCode);
        sb.append(", price=").append(price);
        sb.append(", stock=").append(stock);
        sb.append(", lowStock=").append(lowStock);
        sb.append(", pic=").append(pic);
        sb.append(", sale=").append(sale);
        sb.append(", promotionPrice=").append(promotionPrice);
        sb.append(", lockStock=").append(lockStock);
        sb.append(", spData=").append(spData);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
