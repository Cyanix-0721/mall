package com.mole.common.entity.product;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.math.BigDecimal;

@Schema(description = "SKU库存实体")
public class PmsSkuStock implements Serializable {

    @Schema(description = "SKU库存ID", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "关联商品ID", example = "1001", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long productId;

    @Schema(description = "SKU编码", example = "SKU123456", requiredMode = Schema.RequiredMode.REQUIRED)
    private String skuCode;

    @Schema(description = "价格", example = "99.99")
    private BigDecimal price;

    @Schema(description = "库存数量", example = "100", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer stock;

    @Schema(description = "预警库存数量", example = "20")
    private Integer lowStock;

    @Schema(description = "展示图片URL", example = "http://example.com/sku_image.jpg")
    private String pic;

    @Schema(description = "销量", example = "50")
    private Integer sale;

    @Schema(description = "单品促销价格", example = "89.99")
    private BigDecimal promotionPrice;

    @Schema(description = "锁定库存数量", example = "0")
    private Integer lockStock;

    @Schema(description = "商品销售属性，JSON格式", example = "{\"color\":\"红色\",\"size\":\"XL\"}")
    private String spData;

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

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getLowStock() {
        return lowStock;
    }

    public void setLowStock(Integer lowStock) {
        this.lowStock = lowStock;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public Integer getSale() {
        return sale;
    }

    public void setSale(Integer sale) {
        this.sale = sale;
    }

    public BigDecimal getPromotionPrice() {
        return promotionPrice;
    }

    public void setPromotionPrice(BigDecimal promotionPrice) {
        this.promotionPrice = promotionPrice;
    }

    public Integer getLockStock() {
        return lockStock;
    }

    public void setLockStock(Integer lockStock) {
        this.lockStock = lockStock;
    }

    public String getSpData() {
        return spData;
    }

    public void setSpData(String spData) {
        this.spData = spData;
    }

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