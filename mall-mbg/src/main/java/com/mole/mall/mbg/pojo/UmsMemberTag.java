package com.mole.mall.mbg.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UmsMemberTag implements Serializable {
    private Long id;

    private String name;

    @Schema(title = "自动打标签完成订单数量")
    private Integer finishOrderCount;

    @Schema(title = "自动打标签完成订单金额")
    private BigDecimal finishOrderAmount;

    @Serial
    private static final long serialVersionUID = 1L;
}