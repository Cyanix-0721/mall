package com.mole.mall.mbg.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CmsSubject implements Serializable {
    private Long id;

    private Long categoryId;

    private String title;

    @Schema(title = "专题主图")
    private String pic;

    @Schema(title = "关联产品数量")
    private Integer productCount;

    private Integer recommendStatus;

    private LocalDateTime createTime;

    private Integer collectCount;

    private Integer readCount;

    private Integer commentCount;

    @Schema(title = "画册图片用逗号分割")
    private String albumPics;

    private String description;

    @Schema(title = "显示状态：0->不显示；1->显示")
    private Integer showStatus;

    @Schema(title = "转发数")
    private Integer forwardCount;

    @Schema(title = "专题分类名称")
    private String categoryName;

    private String content;

    @Serial
    private static final long serialVersionUID = 1L;
}