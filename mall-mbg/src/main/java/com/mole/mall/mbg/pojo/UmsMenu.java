package com.mole.mall.mbg.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UmsMenu implements Serializable {
    private Long id;

    @Schema(title = "父级ID")
    private Long parentId;

    @Schema(title = "创建时间")
    private Date createTime;

    @Schema(title = "菜单名称")
    private String title;

    @Schema(title = "菜单级数")
    private Integer level;

    @Schema(title = "菜单排序")
    private Integer sort;

    @Schema(title = "前端名称")
    private String name;

    @Schema(title = "前端图标")
    private String icon;

    @Schema(title = "前端隐藏")
    private Integer hidden;

    @Serial
    private static final long serialVersionUID = 1L;
}