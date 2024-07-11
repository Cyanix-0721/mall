package com.mole.common.entity.marketing;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.util.Date;

public class SmsFlashPromotionLog implements Serializable {

    @Schema(description = "日志记录ID")
    private Integer id;

    @Schema(description = "会员ID")
    private Integer memberId;

    @Schema(description = "商品ID")
    private Long productId;

    @Schema(description = "会员电话号码")
    private String memberPhone;

    @Schema(description = "商品名称")
    private String productName;

    @Schema(description = "会员订阅时间")
    private Date subscribeTime;

    @Schema(description = "发送时间")
    private Date sendTime;

    private static final long serialVersionUID = 1L;

    public SmsFlashPromotionLog(Integer id, Integer memberId, Long productId, String memberPhone, String productName, Date subscribeTime, Date sendTime) {
        this.id = id;
        this.memberId = memberId;
        this.productId = productId;
        this.memberPhone = memberPhone;
        this.productName = productName;
        this.subscribeTime = subscribeTime;
        this.sendTime = sendTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getMemberPhone() {
        return memberPhone;
    }

    public void setMemberPhone(String memberPhone) {
        this.memberPhone = memberPhone;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Date getSubscribeTime() {
        return subscribeTime;
    }

    public void setSubscribeTime(Date subscribeTime) {
        this.subscribeTime = subscribeTime;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    @Override
    public String toString() {
        return "SmsFlashPromotionLog{" +
                "id=" + id +
                ", memberId=" + memberId +
                ", productId=" + productId +
                ", memberPhone='" + memberPhone + '\'' +
                ", productName='" + productName + '\'' +
                ", subscribeTime=" + subscribeTime +
                ", sendTime=" + sendTime +
                '}';
    }
}
