package com.mole.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.util.Date;

@TableName("oms_order")
@Schema(description = "订单实体类，表示一个订单记录")
public class Order {

    @TableId(type = IdType.AUTO, value = "id")
    @Schema(description = "订单ID", example = "13")
    private Long id;

    @Schema(description = "会员ID", example = "1")
    private Long memberId;

    @Schema(description = "优惠券ID", example = "1")
    private Long couponId;

    @Schema(description = "订单编号", example = "202106120001")
    private String orderSn;

    @Schema(description = "提交时间")
    private Date createTime;

    @Schema(description = "用户账号", example = "john_doe")
    private String memberUsername;

    @Schema(description = "订单总金额", example = "100.00")
    private BigDecimal totalAmount;

    @Schema(description = "应付金额（实际支付金额）", example = "95.00")
    private BigDecimal payAmount;

    @Schema(description = "运费金额", example = "5.00")
    private BigDecimal freightAmount;

    @Schema(description = "促销优化金额（促销价、满减、阶梯价）", example = "10.00")
    private BigDecimal promotionAmount;

    @Schema(description = "积分抵扣金额", example = "2.00")
    private BigDecimal integrationAmount;

    @Schema(description = "优惠券抵扣金额", example = "3.00")
    private BigDecimal couponAmount;

    @Schema(description = "管理员后台调整订单使用的折扣金额", example = "5.00")
    private BigDecimal discountAmount;

    @Schema(description = "支付方式：0->未支付；1->支付宝；2->微信", example = "1")
    private Integer payType;

    @Schema(description = "订单来源：0->PC订单；1->app订单", example = "1")
    private Integer sourceType;

    @Schema(description = "订单状态：0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单", example = "1")
    private Integer status;

    @Schema(description = "订单类型：0->正常订单；1->秒杀订单", example = "0")
    private Integer orderType;

    @Schema(description = "物流公司(配送方式)", example = "DHL")
    private String deliveryCompany;

    @Schema(description = "物流单号", example = "202106120001")
    private String deliverySn;

    @Schema(description = "自动确认时间（天）", example = "7")
    private Integer autoConfirmDay;

    @Schema(description = "可以获得的积分", example = "100")
    private Integer integration;

    @Schema(description = "可以获得的成长值", example = "100")
    private Integer growth;

    @Schema(description = "活动信息", example = "Summer Sale")
    private String promotionInfo;

    @Schema(description = "发票类型：0->不开发票；1->电子发票；2->纸质发票", example = "0")
    private Integer billType;

    @Schema(description = "发票抬头", example = "John Doe")
    private String billHeader;

    @Schema(description = "发票内容", example = "电子产品")
    private String billContent;

    @Schema(description = "收票人电话", example = "1234567890")
    private String billReceiverPhone;

    @Schema(description = "收票人邮箱", example = "john@example.com")
    private String billReceiverEmail;

    @Schema(description = "收货人姓名", example = "John Doe")
    private String receiverName;

    @Schema(description = "收货人电话", example = "1234567890")
    private String receiverPhone;

    @Schema(description = "收货人邮编", example = "100000")
    private String receiverPostCode;

    @Schema(description = "省份/直辖市", example = "California")
    private String receiverProvince;

    @Schema(description = "城市", example = "Los Angeles")
    private String receiverCity;

    @Schema(description = "区", example = "West")
    private String receiverRegion;

    @Schema(description = "详细地址", example = "123 Main St")
    private String receiverDetailAddress;

    @Schema(description = "订单备注", example = "请在下午5点后送达")
    private String note;

    @Schema(description = "确认收货状态：0->未确认；1->已确认", example = "1")
    private Integer confirmStatus;

    @Schema(description = "删除状态：0->未删除；1->已删除", example = "0")
    private Integer deleteStatus;

    @Schema(description = "下单时使用的积分", example = "50")
    private Integer useIntegration;

    @Schema(description = "支付时间")
    private Date paymentTime;

    @Schema(description = "发货时间")
    private Date deliveryTime;

    @Schema(description = "确认收货时间")
    private Date receiveTime;

    @Schema(description = "评价时间")
    private Date commentTime;

    @Schema(description = "修改时间")
    private Date modifyTime;

    // getters 和 setters...


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Long getCouponId() {
        return couponId;
    }

    public void setCouponId(Long couponId) {
        this.couponId = couponId;
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

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }

    public BigDecimal getFreightAmount() {
        return freightAmount;
    }

    public void setFreightAmount(BigDecimal freightAmount) {
        this.freightAmount = freightAmount;
    }

    public BigDecimal getPromotionAmount() {
        return promotionAmount;
    }

    public void setPromotionAmount(BigDecimal promotionAmount) {
        this.promotionAmount = promotionAmount;
    }

    public BigDecimal getIntegrationAmount() {
        return integrationAmount;
    }

    public void setIntegrationAmount(BigDecimal integrationAmount) {
        this.integrationAmount = integrationAmount;
    }

    public BigDecimal getCouponAmount() {
        return couponAmount;
    }

    public void setCouponAmount(BigDecimal couponAmount) {
        this.couponAmount = couponAmount;
    }

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public Integer getSourceType() {
        return sourceType;
    }

    public void setSourceType(Integer sourceType) {
        this.sourceType = sourceType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public String getDeliveryCompany() {
        return deliveryCompany;
    }

    public void setDeliveryCompany(String deliveryCompany) {
        this.deliveryCompany = deliveryCompany;
    }

    public String getDeliverySn() {
        return deliverySn;
    }

    public void setDeliverySn(String deliverySn) {
        this.deliverySn = deliverySn;
    }

    public Integer getAutoConfirmDay() {
        return autoConfirmDay;
    }

    public void setAutoConfirmDay(Integer autoConfirmDay) {
        this.autoConfirmDay = autoConfirmDay;
    }

    public Integer getIntegration() {
        return integration;
    }

    public void setIntegration(Integer integration) {
        this.integration = integration;
    }

    public Integer getGrowth() {
        return growth;
    }

    public void setGrowth(Integer growth) {
        this.growth = growth;
    }

    public String getPromotionInfo() {
        return promotionInfo;
    }

    public void setPromotionInfo(String promotionInfo) {
        this.promotionInfo = promotionInfo;
    }

    public Integer getBillType() {
        return billType;
    }

    public void setBillType(Integer billType) {
        this.billType = billType;
    }

    public String getBillHeader() {
        return billHeader;
    }

    public void setBillHeader(String billHeader) {
        this.billHeader = billHeader;
    }

    public String getBillContent() {
        return billContent;
    }

    public void setBillContent(String billContent) {
        this.billContent = billContent;
    }

    public String getBillReceiverPhone() {
        return billReceiverPhone;
    }

    public void setBillReceiverPhone(String billReceiverPhone) {
        this.billReceiverPhone = billReceiverPhone;
    }

    public String getBillReceiverEmail() {
        return billReceiverEmail;
    }

    public void setBillReceiverEmail(String billReceiverEmail) {
        this.billReceiverEmail = billReceiverEmail;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    public String getReceiverPostCode() {
        return receiverPostCode;
    }

    public void setReceiverPostCode(String receiverPostCode) {
        this.receiverPostCode = receiverPostCode;
    }

    public String getReceiverProvince() {
        return receiverProvince;
    }

    public void setReceiverProvince(String receiverProvince) {
        this.receiverProvince = receiverProvince;
    }

    public String getReceiverCity() {
        return receiverCity;
    }

    public void setReceiverCity(String receiverCity) {
        this.receiverCity = receiverCity;
    }

    public String getReceiverRegion() {
        return receiverRegion;
    }

    public void setReceiverRegion(String receiverRegion) {
        this.receiverRegion = receiverRegion;
    }

    public String getReceiverDetailAddress() {
        return receiverDetailAddress;
    }

    public void setReceiverDetailAddress(String receiverDetailAddress) {
        this.receiverDetailAddress = receiverDetailAddress;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getConfirmStatus() {
        return confirmStatus;
    }

    public void setConfirmStatus(Integer confirmStatus) {
        this.confirmStatus = confirmStatus;
    }

    public Integer getDeleteStatus() {
        return deleteStatus;
    }

    public void setDeleteStatus(Integer deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    public Integer getUseIntegration() {
        return useIntegration;
    }

    public void setUseIntegration(Integer useIntegration) {
        this.useIntegration = useIntegration;
    }

    public Date getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(Date paymentTime) {
        this.paymentTime = paymentTime;
    }

    public Date getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(Date deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public Date getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(Date receiveTime) {
        this.receiveTime = receiveTime;
    }

    public Date getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Date commentTime) {
        this.commentTime = commentTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", memberId=" + memberId +
                ", couponId=" + couponId +
                ", orderSn='" + orderSn + '\'' +
                ", createTime=" + createTime +
                ", memberUsername='" + memberUsername + '\'' +
                ", totalAmount=" + totalAmount +
                ", payAmount=" + payAmount +
                ", freightAmount=" + freightAmount +
                ", promotionAmount=" + promotionAmount +
                ", integrationAmount=" + integrationAmount +
                ", couponAmount=" + couponAmount +
                ", discountAmount=" + discountAmount +
                ", payType=" + payType +
                ", sourceType=" + sourceType +
                ", status=" + status +
                ", orderType=" + orderType +
                ", deliveryCompany='" + deliveryCompany + '\'' +
                ", deliverySn='" + deliverySn + '\'' +
                ", autoConfirmDay=" + autoConfirmDay +
                ", integration=" + integration +
                ", growth=" + growth +
                ", promotionInfo='" + promotionInfo + '\'' +
                ", billType=" + billType +
                ", billHeader='" + billHeader + '\'' +
                ", billContent='" + billContent + '\'' +
                ", billReceiverPhone='" + billReceiverPhone + '\'' +
                ", billReceiverEmail='" + billReceiverEmail + '\'' +
                ", receiverName='" + receiverName + '\'' +
                ", receiverPhone='" + receiverPhone + '\'' +
                ", receiverPostCode='" + receiverPostCode + '\'' +
                ", receiverProvince='" + receiverProvince + '\'' +
                ", receiverCity='" + receiverCity + '\'' +
                ", receiverRegion='" + receiverRegion + '\'' +
                ", receiverDetailAddress='" + receiverDetailAddress + '\'' +
                ", note='" + note + '\'' +
                ", confirmStatus=" + confirmStatus +
                ", deleteStatus=" + deleteStatus +
                ", useIntegration=" + useIntegration +
                ", paymentTime=" + paymentTime +
                ", deliveryTime=" + deliveryTime +
                ", receiveTime=" + receiveTime +
                ", commentTime=" + commentTime +
                ", modifyTime=" + modifyTime +
                '}';
    }

    public  Order() {

    }

    public Order(Long id, Long memberId, Long couponId, String orderSn, Date createTime, String memberUsername, BigDecimal totalAmount, BigDecimal payAmount, BigDecimal freightAmount, BigDecimal promotionAmount, BigDecimal integrationAmount, BigDecimal couponAmount, BigDecimal discountAmount, Integer payType, Integer sourceType, Integer status, Integer orderType, String deliveryCompany, String deliverySn, Integer autoConfirmDay, Integer integration, Integer growth, String promotionInfo, Integer billType, String billHeader, String billContent, String billReceiverPhone, String billReceiverEmail, String receiverName, String receiverPhone, String receiverPostCode, String receiverProvince, String receiverCity, String receiverRegion, String receiverDetailAddress, String note, Integer confirmStatus, Integer deleteStatus, Integer useIntegration, Date paymentTime, Date deliveryTime, Date receiveTime, Date commentTime, Date modifyTime) {
        this.id = id;
        this.memberId = memberId;
        this.couponId = couponId;
        this.orderSn = orderSn;
        this.createTime = createTime;
        this.memberUsername = memberUsername;
        this.totalAmount = totalAmount;
        this.payAmount = payAmount;
        this.freightAmount = freightAmount;
        this.promotionAmount = promotionAmount;
        this.integrationAmount = integrationAmount;
        this.couponAmount = couponAmount;
        this.discountAmount = discountAmount;
        this.payType = payType;
        this.sourceType = sourceType;
        this.status = status;
        this.orderType = orderType;
        this.deliveryCompany = deliveryCompany;
        this.deliverySn = deliverySn;
        this.autoConfirmDay = autoConfirmDay;
        this.integration = integration;
        this.growth = growth;
        this.promotionInfo = promotionInfo;
        this.billType = billType;
        this.billHeader = billHeader;
        this.billContent = billContent;
        this.billReceiverPhone = billReceiverPhone;
        this.billReceiverEmail = billReceiverEmail;
        this.receiverName = receiverName;
        this.receiverPhone = receiverPhone;
        this.receiverPostCode = receiverPostCode;
        this.receiverProvince = receiverProvince;
        this.receiverCity = receiverCity;
        this.receiverRegion = receiverRegion;
        this.receiverDetailAddress = receiverDetailAddress;
        this.note = note;
        this.confirmStatus = confirmStatus;
        this.deleteStatus = deleteStatus;
        this.useIntegration = useIntegration;
        this.paymentTime = paymentTime;
        this.deliveryTime = deliveryTime;
        this.receiveTime = receiveTime;
        this.commentTime = commentTime;
        this.modifyTime = modifyTime;
    }
}


