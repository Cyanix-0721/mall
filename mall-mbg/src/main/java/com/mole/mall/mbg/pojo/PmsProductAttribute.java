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
public class PmsProductAttribute implements Serializable {
    private Long id;

    private Long productAttributeCategoryId;

    private String name;

    @Schema(title = "属性选择类型：0->唯一；1->单选；2->多选")
    private Integer selectType;

    @Schema(title = "属性录入方式：0->手工录入；1->从列表中选取")
    private Integer inputType;

    @Schema(title = "可选值列表，以逗号隔开")
    private String inputList;

    @Schema(title = "排序字段：最高的可以单独上传图片")
    private Integer sort;

    @Schema(title = "分类筛选样式：1->普通；1->颜色")
    private Integer filterType;

    @Schema(title = "检索类型；0->不需要进行检索；1->关键字检索；2->范围检索")
    private Integer searchType;

    @Schema(title = "相同属性产品是否关联；0->不关联；1->关联")
    private Integer relatedStatus;

    @Schema(title = "是否支持手动新增；0->不支持；1->支持")
    private Integer handAddStatus;

    @Schema(title = "属性的类型；0->规格；1->参数")
    private Integer type;

    @Serial
    private static final long serialVersionUID = 1L;
}