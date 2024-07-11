package com.mole.common.entity.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("ums_member_level")
@Schema(description = "会员等级实体")
public class MemberLevel {
	@TableId(value = "id", type = IdType.AUTO)
	@Schema(description = "等级ID")
	private Integer id;

	@Schema(description = "等级名称")
	private String levelName;

	@Schema(description = "成长点")
	private Integer growthPoint;

	@Schema(description = "是否为默认等级", allowableValues = "0: 不是默认等级, 1: 是默认等级")
	private Integer defaultStatus;

	@Schema(description = "免运费标准")
	private Double freeFreightPoint;

	@Schema(description = "是否有生日特权", allowableValues = "0: 没有生日特权, 1: 有生日特权")
	private Integer privilegeBirthday;

	@Schema(description = "是否有优惠卷特权", allowableValues = "0: 没有优惠卷特权, 1: 有优惠卷特权")
	private Integer privilegeCoupon;
}
