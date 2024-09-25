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
public class PmsProductCategory implements Serializable {
    private Long id;

    @Schema(title = "上机分类的编号：0表示一级分类")
    private Long parentId;

    private String name;

    @Schema(title = "分类级别：0->1级；1->2级")
    private Integer level;

    private Integer productCount;

    private String productUnit;

    @Schema(title = "是否显示在导航栏：0->不显示；1->显示")
    private Integer navStatus;

    @Schema(title = "显示状态：0->不显示；1->显示")
    private Integer showStatus;

    private Integer sort;

    @Schema(title = "图标")
    private String icon;

    private String keywords;

    @Schema(title = "描述")
    private String description;

    @Serial
    private static final long serialVersionUID = 1L;
}