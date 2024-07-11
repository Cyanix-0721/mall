package com.mole.common.entity.marketing;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;

public class SmsCouponProductCategoryRelation implements Serializable {

    @Schema(description = "唯一标识")
    private Long id;

    @Schema(description = "优惠券ID")
    private Long couponId;

    @Schema(description = "产品分类ID")
    private Long productCategoryId;

    @Schema(description = "产品分类名称")
    private String productCategoryName;

    @Schema(description = "父分类名称")
    private String parentCategoryName;

    private static final long serialVersionUID = 1L;

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

    public Long getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(Long productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public String getProductCategoryName() {
        return productCategoryName;
    }

    public void setProductCategoryName(String productCategoryName) {
        this.productCategoryName = productCategoryName;
    }

    public String getParentCategoryName() {
        return parentCategoryName;
    }

    public void setParentCategoryName(String parentCategoryName) {
        this.parentCategoryName = parentCategoryName;
    }

    @Override
    public String toString() {
        return "SmsCouponProductCategoryRelation{" +
                "id=" + id +
                ", couponId=" + couponId +
                ", productCategoryId=" + productCategoryId +
                ", productCategoryName='" + productCategoryName + '\'' +
                ", parentCategoryName='" + parentCategoryName + '\'' +
                '}';
    }

    public SmsCouponProductCategoryRelation(Long id, Long couponId, Long productCategoryId, String productCategoryName, String parentCategoryName) {
        this.id = id;
        this.couponId = couponId;
        this.productCategoryId = productCategoryId;
        this.productCategoryName = productCategoryName;
        this.parentCategoryName = parentCategoryName;
    }
}
