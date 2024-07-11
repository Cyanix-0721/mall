package com.mole.common.dto.product;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 商品分类对应属性信息
 */
@Data
@EqualsAndHashCode
public class ProductAttrInfo {

    @Schema(description = "商品属性ID")
    private Long attributeId;

    @Schema(description = "商品属性分类ID")
    private Long attributeCategoryId;
}

