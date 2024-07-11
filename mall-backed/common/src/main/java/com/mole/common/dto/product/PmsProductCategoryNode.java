package com.mole.common.dto.product;

import com.mole.common.entity.product.PmsProductCategory;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 包含子分类的商品分类
 */
@Getter
@Setter
public class PmsProductCategoryNode extends PmsProductCategory {

    @Schema(description = "子分类集合")
    private List<PmsProductCategoryNode> children;
}

