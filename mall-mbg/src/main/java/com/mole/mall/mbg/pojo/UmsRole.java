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
public class UmsRole implements Serializable {
    private Long id;

    @Schema(title = "名称")
    private String name;

    @Schema(title = "描述")
    private String description;

    @Schema(title = "后台用户数量")
    private Integer adminCount;

    @Schema(title = "创建时间")
    private LocalDateTime createTime;

    @Schema(title = "启用状态：0->禁用；1->启用")
    private Integer status;

    private Integer sort;

    @Serial
    private static final long serialVersionUID = 1L;
}