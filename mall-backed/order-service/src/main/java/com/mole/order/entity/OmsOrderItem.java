package com.mole.order.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.math.BigDecimal;

@TableName("oms_order_item")
@Schema(description = "订单项")
public class OmsOrderItem implements Serializable {

    @TableId(type = IdType.AUTO, value = "id")
    private Long id;

    @Schema(description = "订单id")
    private Long orderId;

    @Schema(description = "订单编号")
    private String orderSn;

    private Long productId;

    private String productPic;

    public String getMemberUsername() {
        return memberUsername;
    }

    public void setMemberUsername(String memberUsername) {
        this.memberUsername = memberUsername;
    }

    @Schema(description = "会员用户名")
    private String memberUsername;

    private String productName;

    private String productBrand;

    private String productSn;

    @Schema(description = "销售价格")
    private BigDecimal productPrice;

    @Schema(description = "购买数量")
    private Integer productQuantity;

    @Schema(description = "商品sku编号")
    private Long productSkuId;

    @Schema(description = "商品sku条码")
    private String productSkuCode;

    @Schema(description = "商品分类id")
    private Long productCategoryId;

    @Schema(description = "商品促销名称")
    private String promotionName;

    @Schema(description = "商品促销分解金额")
    private BigDecimal promotionAmount;

    @Schema(description = "优惠券优惠分解金额")
    private BigDecimal couponAmount;

    @Schema(description = "积分优惠分解金额")
    private BigDecimal integrationAmount;

    @Schema(description = "该商品经过优惠后的分解金额")
    private BigDecimal realAmount;

    private Integer giftIntegration;

    private Integer giftGrowth;

    @Schema(description = "应付金额（实际支付金额）", example = "95.00")
    private BigDecimal payAmount;

    @Schema(description = "订单状态：0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单", example = "1")
    private Integer status;

    @Schema(description = "订单备注", example = "请在下午5点后送达")
    private String note;


    @Schema(description = "商品销售属性:[{'key':'颜色','value':'颜色'},{'key':'容量','value':'4G'}]")
    private String productAttr;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getOrderSn() {
        return orderSn;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductPic() {
        return productPic;
    }

    public void setProductPic(String productPic) {
        this.productPic = productPic;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductBrand() {
        return productBrand;
    }

    public void setProductBrand(String productBrand) {
        this.productBrand = productBrand;
    }

    public String getProductSn() {
        return productSn;
    }

    public void setProductSn(String productSn) {
        this.productSn = productSn;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public Integer getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(Integer productQuantity) {
        this.productQuantity = productQuantity;
    }

    public Long getProductSkuId() {
        return productSkuId;
    }

    public void setProductSkuId(Long productSkuId) {
        this.productSkuId = productSkuId;
    }

    public String getProductSkuCode() {
        return productSkuCode;
    }

    public void setProductSkuCode(String productSkuCode) {
        this.productSkuCode = productSkuCode;
    }

    public Long getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(Long productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public String getPromotionName() {
        return promotionName;
    }

    public void setPromotionName(String promotionName) {
        this.promotionName = promotionName;
    }

    public BigDecimal getPromotionAmount() {
        return promotionAmount;
    }

    public void setPromotionAmount(BigDecimal promotionAmount) {
        this.promotionAmount = promotionAmount;
    }

    public BigDecimal getCouponAmount() {
        return couponAmount;
    }

    public void setCouponAmount(BigDecimal couponAmount) {
        this.couponAmount = couponAmount;
    }

    public BigDecimal getIntegrationAmount() {
        return integrationAmount;
    }

    public void setIntegrationAmount(BigDecimal integrationAmount) {
        this.integrationAmount = integrationAmount;
    }

    public BigDecimal getRealAmount() {
        return realAmount;
    }

    public void setRealAmount(BigDecimal realAmount) {
        this.realAmount = realAmount;
    }

    public Integer getGiftIntegration() {
        return giftIntegration;
    }

    public void setGiftIntegration(Integer giftIntegration) {
        this.giftIntegration = giftIntegration;
    }

    public Integer getGiftGrowth() {
        return giftGrowth;
    }

    public void setGiftGrowth(Integer giftGrowth) {
        this.giftGrowth = giftGrowth;
    }

    public String getProductAttr() {
        return productAttr;
    }

    public void setProductAttr(String productAttr) {
        this.productAttr = productAttr;
    }

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "OmsOrderItem{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", orderSn='" + orderSn + '\'' +
                ", productId=" + productId +
                ", productPic='" + productPic + '\'' +
                ", memberUsername='" + memberUsername + '\'' +
                ", productName='" + productName + '\'' +
                ", productBrand='" + productBrand + '\'' +
                ", productSn='" + productSn + '\'' +
                ", productPrice=" + productPrice +
                ", productQuantity=" + productQuantity +
                ", productSkuId=" + productSkuId +
                ", productSkuCode='" + productSkuCode + '\'' +
                ", productCategoryId=" + productCategoryId +
                ", promotionName='" + promotionName + '\'' +
                ", promotionAmount=" + promotionAmount +
                ", couponAmount=" + couponAmount +
                ", integrationAmount=" + integrationAmount +
                ", realAmount=" + realAmount +
                ", giftIntegration=" + giftIntegration +
                ", giftGrowth=" + giftGrowth +
                ", payAmount=" + payAmount +
                ", status=" + status +
                ", note='" + note + '\'' +
                ", productAttr='" + productAttr + '\'' +
                '}';
    }
}
