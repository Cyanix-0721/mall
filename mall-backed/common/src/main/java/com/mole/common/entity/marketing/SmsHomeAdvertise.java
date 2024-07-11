package com.mole.common.entity.marketing;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.util.Date;

public class SmsHomeAdvertise implements Serializable {
    @Schema(description = "广告ID")
    private Long id;

    @Schema(description = "广告名称")
    private String name;

    @Schema(description = "轮播位置：0->PC首页轮播；1->app首页轮播")
    private Integer type;

    @Schema(description = "图片地址")
    private String pic;

    @Schema(description = "开始时间", type = "string", format = "date-time")
    private Date startTime;

    @Schema(description = "结束时间", type = "string", format = "date-time")
    private Date endTime;

    @Schema(description = "上下线状态：0->下线；1->上线")
    private Integer status;

    @Schema(description = "点击数")
    private Integer clickCount;

    @Schema(description = "下单数")
    private Integer orderCount;

    @Schema(description = "链接地址")
    private String url;

    @Schema(description = "备注")
    private String note;

    @Schema(description = "排序")
    private Integer sort;

    private static final long serialVersionUID = 1L;

    public SmsHomeAdvertise(Long id, String name, Integer type, String pic, Date startTime, Date endTime, Integer status, Integer clickCount, Integer orderCount, String url, String note, Integer sort) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.pic = pic;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
        this.clickCount = clickCount;
        this.orderCount = orderCount;
        this.url = url;
        this.note = note;
        this.sort = sort;
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
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

    public Integer getClickCount() {
        return clickCount;
    }

    public void setClickCount(Integer clickCount) {
        this.clickCount = clickCount;
    }

    public Integer getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(Integer orderCount) {
        this.orderCount = orderCount;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    @Override
    public String toString() {
        return "SmsHomeAdvertise{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", pic='" + pic + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", status=" + status +
                ", clickCount=" + clickCount +
                ", orderCount=" + orderCount +
                ", url='" + url + '\'' +
                ", note='" + note + '\'' +
                ", sort=" + sort +
                '}';
    }

    public SmsHomeAdvertise() {

    }
}
