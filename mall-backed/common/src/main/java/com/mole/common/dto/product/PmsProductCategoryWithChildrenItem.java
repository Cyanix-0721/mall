package com.mole.common.dto.product;

import com.mole.common.entity.product.PmsProductCategory;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

/**
 * 包含子级分类的商品分类
 */
@Getter
@Setter
public class PmsProductCategoryWithChildrenItem extends PmsProductCategory {

    @Schema(description = "子级分类")
    private List<PmsProductCategory> children;
}
