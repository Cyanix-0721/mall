package com.mole.mall.mbg.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serial;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PmsProductAttributeCategory implements Serializable {
    private Long id;

    private String name;

    @Schema(title = "属性数量")
    private Integer attributeCount;

    @Schema(title = "参数数量")
    private Integer paramCount;

    @Serial
    private static final long serialVersionUID = 1L;
}