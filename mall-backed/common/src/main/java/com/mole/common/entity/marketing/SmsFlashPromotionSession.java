package com.mole.common.entity.marketing;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.util.Date;

public class SmsFlashPromotionSession implements Serializable {

    @Schema(description = "编号")
    private Long id;

    @Schema(description = "场次名称")
    private String name;

    @Schema(description = "每日开始时间")
    private Date startTime;

    @Schema(description = "每日结束时间")
    private Date endTime;

    @Schema(description = "启用状态：0->不启用；1->启用")
    private Integer status;

    @Schema(description = "创建时间")
    private Date createTime;

    private static final long serialVersionUID = 1L;

    public SmsFlashPromotionSession(Long id, String name, Date startTime, Date endTime, Integer status, Date createTime) {
        this.id = id;
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
        this.createTime = createTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "SmsFlashPromotionSession{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", status=" + status +
                ", createTime=" + createTime +
                '}';
    }

    public SmsFlashPromotionSession() {

    }
}
