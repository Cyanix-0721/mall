package com.mole.common.dto.product;

import com.mole.common.entity.product.validator.FlagValidator;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 商品属性参数
 */
@Data
@EqualsAndHashCode
public class PmsProductAttributeParam {

    @Schema(description = "属性分类ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "属性分类ID不能为空")
    private Long productAttributeCategoryId;

    @Schema(description = "属性名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "属性名称不能为空")
    private String name;

    @Schema(description = "属性选择类型：0->唯一；1->单选；2->多选", allowableValues = {"0", "1", "2"})
    @FlagValidator({"0","1","2"})
    private Integer selectType;

    @Schema(description = "属性录入方式：0->手工录入；1->从列表中选取", allowableValues = {"0", "1"})
    @FlagValidator({"0","1"})
    private Integer inputType;

    @Schema(description = "可选值列表，以逗号隔开")
    private String inputList;

    @Schema(description = "排序")
    private Integer sort;

    @Schema(description = "分类筛选样式：0->普通；1->颜色", allowableValues = {"0", "1"})
    @FlagValidator({"0","1"})
    private Integer filterType;

    @Schema(description = "检索类型；0->不需要进行检索；1->关键字检索；2->范围检索", allowableValues = {"0", "1", "2"})
    @FlagValidator({"0","1","2"})
    private Integer searchType;

    @Schema(description = "相同属性商品是否关联；0->不关联；1->关联", allowableValues = {"0", "1"})
    @FlagValidator({"0","1"})
    private Integer relatedStatus;

    @Schema(description = "是否支持手动新增；0->不支持；1->支持", allowableValues = {"0", "1"})
    @FlagValidator({"0","1"})
    private Integer handAddStatus;

    @Schema(description = "属性的类型；0->规格；1->参数", allowableValues = {"0", "1"})
    @FlagValidator({"0","1"})
    private Integer type;
}

