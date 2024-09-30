package com.mole.mall.demo.dto;

import com.mole.mall.demo.validator.FlagValidator;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * 品牌传递参数
 */
@Getter
@Setter
public class PmsBrandDto {
	@Schema(title = "品牌名称")
	@NotNull(message = "名称不能为空")
	private String name;
	@Schema(title = "品牌首字母")
	@NotNull(message = "首字母不能为空")
	private String firstLetter;
	@Schema(title = "排序字段")
	@Min(value = 0, message = "排序最小为0")
	private Integer sort;
	@Schema(title = "是否为厂家制造商")
	@FlagValidator(value = {"0", "1"}, message = "厂家状态不正确")
	private Integer factoryStatus;
	@Schema(title = "是否进行显示")
	@FlagValidator(value = {"0", "1"}, message = "显示状态不正确")
	private Integer showStatus;
	@Schema(title = "品牌logo")
	private String logo;
	@Schema(title = "品牌大图")
	private String bigPic;
	@Schema(title = "品牌故事")
	private String brandStory;
}