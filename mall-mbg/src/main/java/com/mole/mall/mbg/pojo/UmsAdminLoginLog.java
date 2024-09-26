package com.mole.mall.mbg.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UmsAdminLoginLog implements Serializable {
    private Long id;

    private Long adminId;

    private LocalDateTime createTime;

    private String ip;

    private String address;

    @Schema(title = "浏览器登录类型")
    private String userAgent;

    @Serial
    private static final long serialVersionUID = 1L;
}