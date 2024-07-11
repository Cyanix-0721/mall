package com.mole.common.entity.marketing;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;

@TableName("sms_coupon_product_relation") // 确保表名拼写正确
@Schema(description = "优惠券与商品关联关系实体")
public class SmsCouponProductRelation {

    @TableId(value = "id")
    @Schema(description = "主键ID", example = "1")
    private Long id;

    @TableField(value = "coupon_id")
    @Schema(description = "优惠券ID", example = "1001")
    private Long couponId;

    @TableField(value = "product_id")
    @Schema(description = "商品ID", example = "2001")
    private Long productId;

    @TableField(value = "product_name")
    @Schema(description = "商品名称", example = "Sample Product")
    private String productName;

    @TableField(value = "product_sn")
    @Schema(description = "商品货号/序列号", example = "SP001")
    private String productSn;

    private static final long serialVersionUID = 1L;

    public SmsCouponProductRelation(Long id, Long couponId, Long productId, String productName, String productSn) {
        this.id = id;
        this.couponId = couponId;
        this.productId = productId;
        this.productName = productName;
        this.productSn = productSn;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCouponId() {
        return couponId;
    }

    public void setCouponId(Long couponId) {
        this.couponId = couponId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductSn() {
        return productSn;
    }

    public void setProductSn(String productSn) {
        this.productSn = productSn;
    }

    @Override
    public String toString() {
        return "SmsCouponProductRelation{" +
                "id=" + id +
                ", couponId=" + couponId +
                ", productId=" + productId +
                ", productName='" + productName + '\'' +
                ", productSn='" + productSn + '\'' +
                '}';
    }
}
