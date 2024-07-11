package com.mole.order.dto;

import java.util.List;

public class UpdateStatusRequest {
    private List<Long> ids;
    private Integer status;

    // 构造方法
    public UpdateStatusRequest() {
    }

    public UpdateStatusRequest(List<Long> ids, Integer status) {
        this.ids = ids;
        this.status = status;
    }

    // Getter 和 Setter 方法
    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
