package com.mole.common.dto.product;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * 查询单个商品修改后返回的结果
 */
@Getter
@Setter
public class PmsProductResult extends PmsProductParam {

    @Schema(description = "商品所选分类的父id")
    private Long cateParentId;
}

