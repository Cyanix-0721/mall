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
@TableName("ums_integration_consume_setting")
@Schema(description = "积分消费设置")
public class MemberIntegrationConsumeSetting {
	@Schema(description = "ID")
	@TableId(value = "id", type = IdType.AUTO)
	private Long id;

	@Schema(description = "每一元需要抵扣的积分数量，例如，如果这个值是10，那么每一元需要10积分来抵扣")
	private Integer deductionPerAmount;

	@Schema(description = "每笔订单最高可以抵扣的百分比，这个值是一个0-100之间的整数，表示可以抵扣的最高百分比")
	private Integer maxPercentPerOrder;

	@Schema(description = "每次使用积分的最小单位，例如，如果这个值是100，那么每次使用积分的数量必须是100的倍数")
	private Integer useUnit;

	@Schema(description = "是否可以和优惠券同用", allowableValues = {"0->不可以", "1->可以"})
	private Integer couponStatus;
}