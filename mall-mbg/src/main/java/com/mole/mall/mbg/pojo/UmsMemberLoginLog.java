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
public class UmsMemberLoginLog implements Serializable {
    private Long id;

    private Long memberId;

    private LocalDateTime createTime;

    private String ip;

    private String city;

    @Schema(title = "登录类型：0->PC；1->android;2->ios;3->小程序")
    private Integer loginType;

    private String province;

    @Serial
    private static final long serialVersionUID = 1L;
}