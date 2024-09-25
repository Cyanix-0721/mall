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
public class SmsCouponProductCategoryRelation implements Serializable {
    private Long id;

    private Long couponId;

    private Long productCategoryId;

    @Schema(title = "产品分类名称")
    private String productCategoryName;

    @Schema(title = "父分类名称")
    private String parentCategoryName;

    @Serial
    private static final long serialVersionUID = 1L;
}