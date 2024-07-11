package com.mole.common.dto.product;

import com.mole.common.entity.product.validator.FlagValidator;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 品牌请求参数
 */
@Data
@EqualsAndHashCode
public class PmsBrandParam {

    @Schema(description = "品牌名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "品牌名称不能为空")
    private String name;

    @Schema(description = "品牌首字母")
    private String firstLetter;

    @Schema(description = "排序字段", minimum = "0")
    @Min(value = 0, message = "排序字段必须大于等于0")
    private Integer sort;

    @Schema(description = "是否为厂家制造商", allowableValues = {"0", "1"})
    @FlagValidator(value = {"0", "1"}, message = "厂家状态不正确")
    private Integer factoryStatus;

    @Schema(description = "是否进行显示", allowableValues = {"0", "1"})
    @FlagValidator(value = {"0", "1"}, message = "显示状态不正确")
    private Integer showStatus;

    @Schema(description = "产品数量")
    private Integer productCount;

    @Schema(description = "产品评论数量")
    private Integer productCommentCount;

    @Schema(description = "品牌logo", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "品牌logo不能为空")
    private String logo;

    @Schema(description = "品牌大图")
    private String bigPic;

    @Schema(description = "品牌故事")
    private String brandStory;

}

