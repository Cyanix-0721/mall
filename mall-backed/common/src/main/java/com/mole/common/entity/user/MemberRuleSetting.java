package com.mole.common.entity.user;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "会员积分成长规则表")
@TableName("ums_member_rule_setting")
public class MemberRuleSetting {
	@Schema(description = "ID")
	@TableId(value = "id", type = IdType.AUTO)
	private Long id;

	@Schema(description = "连续签到天数")
	private Integer continueSignDay;

	@Schema(description = "连续签到赠送数量")
	private Integer continueSignPoint;

	@Schema(description = "每消费多少元获取1个点")
	private BigDecimal consumePerPoint;

	@Schema(description = "最低获取点数的订单金额")
	private BigDecimal lowOrderAmount;

	@Schema(description = "每笔订单最高获取点数")
	private Integer maxPointPerOrder;

	@Schema(description = "类型,签到固定只给积分", allowableValues = {"0->积分规则", "1->成长值规则"})
	private Integer type;
}
