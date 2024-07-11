package com.mole.common.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.util.Date;

@TableName("sms_coupon")
public class SmsCoupon {
    @TableId(value = "id")
    private Long id;

    @TableField(value = "type")
    private Integer type;
    @TableField(value = "name")
    private String name;

    @TableField(value = "platform")
    private Integer platform;

    @TableField(value = "count")
    private Integer count;

    @TableField(value = "amount")
    private BigDecimal amount;

    @TableField(value = "per_limit")
    private Integer perLimit;

    @TableField(value = "min_point")
    private BigDecimal minPoint;

    @TableField(value = "start_time")
    private Date startTime;
    @TableField(value = "end_time")
    private Date endTime;

    @TableField(value = "use_type")
    private Integer useType;

    @TableField(value = "note")
    private String note;

    @TableField(value = "publish_count")
    private Integer publishCount;

    @TableField(value = "use_count")
    private Integer useCount;

    @TableField(value = "receive_count")
    private Integer receiveCount;

    @TableField(value = "enable_time")
    private Date enableTime;

    @TableField(value = "code")
    private String code;

    @TableField(value = "member_level")
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
    public String toString() {
        return "SmsCoupon{gid=" + id + ",type=" + type + ",name=" + name + ",platform=" + platform + ",count=" + count + ",amount=" + amount + ",perLimit=" + perLimit + ",minPoint=" + minPoint + ",startTime=" + startTime + ",endTime=" + endTime + ",useType=" + useType + ",note=" + note + ",publishCount=" + publishCount + ",useCount=" + useCount + ",receiveCount=" + receiveCount +
                ",enableTime=" + enableTime + ",code=" + code + ",memberLevel=" + memberLevel + "}";
    }
    public SmsCoupon(Long id, Integer type, String name, Integer platform, Integer count, BigDecimal amount, Integer perLimit, BigDecimal minPoint, Date startTime, Date endTime, Integer useType, String note, Integer publishCount, Integer useCount, Integer receiveCount, Date enableTime, String code, Integer memberLevel){
        this.id = id;
        this.type = type;
        this.name = name;
        this.platform = platform;
        this.count = count;
        this.amount = amount;
        this.perLimit = perLimit;
        this.minPoint = minPoint;
        this.startTime = startTime;
        this.endTime = endTime;
        this.useType = useType;
        this.note = note;
        this.publishCount = publishCount;
        this.useCount = useCount;
        this.receiveCount = receiveCount;
        this.enableTime = enableTime;
        this.code = code;
        this.memberLevel = memberLevel;
    }
}
