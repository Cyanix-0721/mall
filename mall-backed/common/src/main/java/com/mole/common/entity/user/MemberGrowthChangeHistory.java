package com.mole.common.entity.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("ums_growth_change_history")
@Schema(description = "成长值变化历史记录表")
public class MemberGrowthChangeHistory {
	@Schema(description = "ID")
	@TableId(value = "id", type = IdType.AUTO)
	private Long id;

	@Schema(description = "会员ID")
	private Long memberId;

	@Schema(description = "创建时间")
	private Timestamp createTime;

	@Schema(description = "改变类型：0->增加；1->减少")
	private Integer changeType;

	@Schema(description = "积分改变数量")
	private Integer changeCount;

	@Schema(description = "操作人员")
	private String operateMan;

	@Schema(description = "操作备注")
	private String operateNote;

	@Schema(description = "积分来源：0->购物；1->管理员修改")
	private Integer sourceType;
}