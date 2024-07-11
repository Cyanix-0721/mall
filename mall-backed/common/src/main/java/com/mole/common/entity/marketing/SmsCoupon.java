package com.mole.common.entity.marketing;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.util.Date;

@TableName("sms_coupon")
public class SmsCoupon {

	@Schema(description = "优惠券ID", example = "1")
	private Long id;

	@Schema(description = "优惠券类型", example = "1")
	private Integer type;

	@Schema(description = "优惠券名称", example = "满减券")
	private String name;

	@Schema(description = "平台类型", example = "0")
	private Integer platform;

	@Schema(description = "发行数量", example = "1000")
	private Integer count;

	@Schema(description = "金额", example = "50.00")
	private BigDecimal amount;

	@Schema(description = "每人限领张数", example = "1")
	private Integer perLimit;

	@Schema(description = "使用门槛", example = "100.00")
	private BigDecimal minPoint;

	@Schema(description = "开始时间", example = "2023-04-01T00:00:00Z", type = "string", format = "date-time")
	private Date startTime;

	@Schema(description = "结束时间", example = "2023-12-31T23:59:59Z", type = "string", format = "date-time")
	private Date endTime;

	@Schema(description = "使用类型", example = "0")
	private Integer useType;

	@Schema(description = "备注", example = "适用于全场商品")
	private String note;

	@Schema(description = "已发放数量", example = "500")
	private Integer publishCount;

	@Schema(description = "已使用数量", example = "200")
	private Integer useCount;

	@Schema(description = "领取数量", example = "300")
	private Integer receiveCount;

	@Schema(description = "生效时间", example = "2023-04-01T00:00:00Z", type = "string", format = "date-time")
	private Date enableTime;

	@Schema(description = "优惠券码", example = "DISCOUNT2023")
	private String code;

	@Schema(description = "会员等级限制", example = "1")
	private Integer memberLevel;

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
		return "SmsCoupon{" +
				"id=" + id +
				", type=" + type +
				", name='" + name + '\'' +
				", platform=" + platform +
				", count=" + count +
				", amount=" + amount +
				", perLimit=" + perLimit +
				", minPoint=" + minPoint +
				", startTime=" + startTime +
				", endTime=" + endTime +
				", useType=" + useType +
				", note='" + note + '\'' +
				", publishCount=" + publishCount +
				", useCount=" + useCount +
				", receiveCount=" + receiveCount +
				", enableTime=" + enableTime +
				", code='" + code + '\'' +
				", memberLevel=" + memberLevel +
				'}';
	}

}
