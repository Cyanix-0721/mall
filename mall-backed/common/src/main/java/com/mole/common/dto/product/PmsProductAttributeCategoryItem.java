package com.mole.common.dto.product;

import com.mole.common.entity.product.PmsProductAttribute;
import com.mole.common.entity.product.PmsProductAttributeCategory;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 带有属性的商品属性分类
 */
@Getter
@Setter
public class PmsProductAttributeCategoryItem extends PmsProductAttributeCategory {

    @Schema(description = "商品属性列表")
    private List<PmsProductAttribute> productAttributeList;
}

