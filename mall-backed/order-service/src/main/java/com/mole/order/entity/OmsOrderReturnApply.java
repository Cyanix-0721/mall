package com.mole.order.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@TableName("oms_order_return_apply")
@Schema(description = "订单退货申请")
public class OmsOrderReturnApply implements Serializable {

    @TableId(type = IdType.AUTO, value = "id")
    @Schema(description = "ID")
    private Long id;

    @Schema(description = "订单id")
    private Long orderId;

    @Schema(description = "收货地址表id")
    private Long companyAddressId;

    @Schema(description = "退货商品id")
    private Long productId;

    @Schema(description = "订单编号")
    private String orderSn;

    @Schema(description = "申请时间")
    private Date createTime;

    @Schema(description = "会员用户名")
    private String memberUsername;

    @Schema(description = "退款金额")
    private BigDecimal returnAmount;

    @Schema(description = "退货人姓名")
    private String returnName;

    @Schema(description = "退货人电话")
    private String returnPhone;

    @Schema(description = "申请状态：0->待处理；1->退货中；2->已完成；3->已拒绝")
    private Integer status;

    @Schema(description = "处理时间")
    private Date handleTime;

    @Schema(description = "商品图片")
    private String productPic;

    @Schema(description = "商品名称")
    private String productName;

    @Schema(description = "商品品牌")
    private String productBrand;

    @Schema(description = "商品销售属性：颜色：红色；尺码：xl;")
    private String productAttr;

    @Schema(description = "退货数量")
    private Integer productCount;

    @Schema(description = "商品单价")
    private BigDecimal productPrice;

    @Schema(description = "商品实际支付单价")
    private BigDecimal productRealPrice;

    @Schema(description = "原因")
    private String reason;

    @Schema(description = "描述")
    private String description;

    @Schema(description = "凭证图片，以逗号隔开")
    private String proofPics;

    @Schema(description = "处理备注")
    private String handleNote;

    @Schema(description = "处理人员")
    private String handleMan;

    @Schema(description = "收货人")
    private String receiveMan;

    @Schema(description = "收货时间")
    private Date receiveTime;

    @Schema(description = "收货备注")
    private String receiveNote;

    private static final long serialVersionUID = 1L;

    // Getter 和 Setter 方法
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

    public Long getCompanyAddressId() {
        return companyAddressId;
    }

    public void setCompanyAddressId(Long companyAddressId) {
        this.companyAddressId = companyAddressId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getOrderSn() {
        return orderSn;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getMemberUsername() {
        return memberUsername;
    }

    public void setMemberUsername(String memberUsername) {
        this.memberUsername = memberUsername;
    }

    public BigDecimal getReturnAmount() {
        return returnAmount;
    }

    public void setReturnAmount(BigDecimal returnAmount) {
        this.returnAmount = returnAmount;
    }

    public String getReturnName() {
        return returnName;
    }

    public void setReturnName(String returnName) {
        this.returnName = returnName;
    }

    public String getReturnPhone() {
        return returnPhone;
    }

    public void setReturnPhone(String returnPhone) {
        this.returnPhone = returnPhone;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getHandleTime() {
        return handleTime;
    }

    public void setHandleTime(Date handleTime) {
        this.handleTime = handleTime;
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

    public String getProductAttr() {
        return productAttr;
    }

    public void setProductAttr(String productAttr) {
        this.productAttr = productAttr;
    }

    public Integer getProductCount() {
        return productCount;
    }

    public void setProductCount(Integer productCount) {
        this.productCount = productCount;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public BigDecimal getProductRealPrice() {
        return productRealPrice;
    }

    public void setProductRealPrice(BigDecimal productRealPrice) {
        this.productRealPrice = productRealPrice;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProofPics() {
        return proofPics;
    }

    public void setProofPics(String proofPics) {
        this.proofPics = proofPics;
    }

    public String getHandleNote() {
        return handleNote;
    }

    public void setHandleNote(String handleNote) {
        this.handleNote = handleNote;
    }

    public String getHandleMan() {
        return handleMan;
    }

    public void setHandleMan(String handleMan) {
        this.handleMan = handleMan;
    }

    public String getReceiveMan() {
        return receiveMan;
    }

    public void setReceiveMan(String receiveMan) {
        this.receiveMan = receiveMan;
    }

    public Date getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(Date receiveTime) {
        this.receiveTime = receiveTime;
    }

    public String getReceiveNote() {
        return receiveNote;
    }

    public void setReceiveNote(String receiveNote) {
        this.receiveNote = receiveNote;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", orderId=").append(orderId);
        sb.append(", companyAddressId=").append(companyAddressId);
        sb.append(", productId=").append(productId);
        sb.append(", orderSn=").append(orderSn);
        sb.append(", createTime=").append(createTime);
        sb.append(", memberUsername=").append(memberUsername);
        sb.append(", returnAmount=").append(returnAmount);
        sb.append(", returnName=").append(returnName);
        sb.append(", returnPhone=").append(returnPhone);
        sb.append(", status=").append(status);
        sb.append(", handleTime=").append(handleTime);
        sb.append(", productPic=").append(productPic);
        sb.append(", productName=").append(productName);
        sb.append(", productBrand=").append(productBrand);
        sb.append(", productAttr=").append(productAttr);
        sb.append(", productCount=").append(productCount);
        sb.append(", productPrice=").append(productPrice);
        sb.append(", productRealPrice=").append(productRealPrice);
        sb.append(", reason=").append(reason);
        sb.append(", description=").append(description);
        sb.append(", proofPics=").append(proofPics);
        sb.append(", handleNote=").append(handleNote);
        sb.append(", handleMan=").append(handleMan);
        sb.append(", receiveMan=").append(receiveMan);
        sb.append(", receiveTime=").append(receiveTime);
        sb.append(", receiveNote=").append(receiveNote);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
