package com.mole.common.entity.product;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Schema(description = "短信优惠券实体类")
public class SmsCoupon implements Serializable {

    @Schema(description = "优惠券ID", example = "1")
    private Long id;

    @Schema(description = "优惠券类型；0->全场赠券；1->会员赠券；2->购物赠券；3->注册赠券", allowableValues = {"0", "1", "2", "3"})
    private Integer type;

    @Schema(description = "优惠券名称", example = "新年特惠券")
    private String name;

    @Schema(description = "使用平台；0->全部；1->移动；2->PC", allowableValues = {"0", "1", "2"})
    private Integer platform;

    @Schema(description = "数量", example = "100")
    private Integer count;

    @Schema(description = "金额", example = "50.00")
    private BigDecimal amount;

    @Schema(description = "每人限领张数", example = "1")
    private Integer perLimit;

    @Schema(description = "使用门槛；0表示无门槛", example = "0")
    private BigDecimal minPoint;

    @Schema(description = "开始时间", format = "date-time", example = "2023-04-01T00:00:00Z")
    private Date startTime;

    @Schema(description = "结束时间", format = "date-time", example = "2023-06-30T23:59:59Z")
    private Date endTime;

    @Schema(description = "使用类型；0->全场通用；1->指定分类；2->指定商品", allowableValues = {"0", "1", "2"})
    private Integer useType;

    @Schema(description = "备注信息", example = "适用于所有商品")
    private String note;

    @Schema(description = "发行数量", example = "200")
    private Integer publishCount;

    @Schema(description = "已使用数量", example = "50")
    private Integer useCount;

    @Schema(description = "领取数量", example = "75")
    private Integer receiveCount;

    @Schema(description = "可以领取的日期", format = "date-time", example = "2023-08-01T00:00:00Z")
    private Date enableTime;

    @Schema(description = "优惠码", example = "NEWYEAR2023")
    private String code;

    @Schema(description = "可领取的会员类型；0->无限时", allowableValues = {"0"})
    private Integer memberLevel;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPlatform() {
        return platform;
    }

    public void setPlatform(Integer platform) {
        this.platform = platform;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getPerLimit() {
        return perLimit;
    }

    public void setPerLimit(Integer perLimit) {
        this.perLimit = perLimit;
    }

    public BigDecimal getMinPoint() {
        return minPoint;
    }

    public void setMinPoint(BigDecimal minPoint) {
        this.minPoint = minPoint;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getUseType() {
        return useType;
    }

    public void setUseType(Integer useType) {
        this.useType = useType;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getPublishCount() {
        return publishCount;
    }

    public void setPublishCount(Integer publishCount) {
        this.publishCount = publishCount;
    }

    public Integer getUseCount() {
        return useCount;
    }

    public void setUseCount(Integer useCount) {
        this.useCount = useCount;
    }

    public Integer getReceiveCount() {
        return receiveCount;
    }

    public void setReceiveCount(Integer receiveCount) {
        this.receiveCount = receiveCount;
    }

    public Date getEnableTime() {
        return enableTime;
    }

    public void setEnableTime(Date enableTime) {
        this.enableTime = enableTime;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getMemberLevel() {
        return memberLevel;
    }

    public void setMemberLevel(Integer memberLevel) {
        this.memberLevel = memberLevel;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", type=").append(type);
        sb.append(", name=").append(name);
        sb.append(", platform=").append(platform);
        sb.append(", count=").append(count);
        sb.append(", amount=").append(amount);
        sb.append(", perLimit=").append(perLimit);
        sb.append(", minPoint=").append(minPoint);
        sb.append(", startTime=").append(startTime);
        sb.append(", endTime=").append(endTime);
        sb.append(", useType=").append(useType);
        sb.append(", note=").append(note);
        sb.append(", publishCount=").append(publishCount);
        sb.append(", useCount=").append(useCount);
        sb.append(", receiveCount=").append(receiveCount);
        sb.append(", enableTime=").append(enableTime);
        sb.append(", code=").append(code);
        sb.append(", memberLevel=").append(memberLevel);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}