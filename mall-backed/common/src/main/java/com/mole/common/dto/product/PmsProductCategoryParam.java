package com.mole.common.dto.product;

import com.mole.common.entity.product.validator.FlagValidator;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * 添加更新商品分类的参数
 */
@Data
@EqualsAndHashCode
public class PmsProductCategoryParam {

    @Schema(description = "父分类的编号")
    private Long parentId;

    @Schema(description = "商品分类名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "商品分类名称不能为空")
    private String name;

    @Schema(description = "分类级别：0->1级；1->2级", allowableValues = {"0", "1"}, defaultValue = "0")
    private Integer level;

    @Schema(description = "商品数量")
    private Integer productCount;

    @Schema(description = "分类单位")
    private String productUnit;

    @Schema(description = "是否在导航栏显示", allowableValues = {"0", "1"})
    @FlagValidator(value = {"0","1"}, message = "状态只能为0或1")
    private Integer navStatus;

    @Schema(description = "是否进行显示", allowableValues = {"0", "1"})
    @FlagValidator(value = {"0","1"}, message = "状态只能为0或1")
    private Integer showStatus;

    @Schema(description = "排序", minimum = "0")
    @Min(value = 0, message = "排序值必须大于等于0")
    private Integer sort;

    @Schema(description = "图标")
    private String icon;

    @Schema(description = "关键字")
    private String keywords;

    @Schema(description = "描述")
    private String description;

    @Schema(description = "商品相关筛选属性集合")
    private List<Long> productAttributeIdList;
}

