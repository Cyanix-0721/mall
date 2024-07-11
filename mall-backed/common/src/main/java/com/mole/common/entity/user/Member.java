package com.mole.common.entity.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("ums_member")
@Schema(description = "会员实体")
public class Member {
	@TableId(value = "id", type = IdType.AUTO)
	@Schema(description = "用户ID")
	private Long id;

	@Schema(description = "会员等级ID", allowableValues = {"0: 普通会员", "1: 黄金会员", "2: 白金会员", "3: 钻石会员"})
	private Integer memberLevelId = 0;

	@Schema(description = "用户名")
	private String username;

	@Schema(description = "密码")
	private String password;

	@Schema(description = "昵称")
	private String nickname;

	@Schema(description = "手机号码")
	private String phone;

	@Schema(description = "账号启用状态", allowableValues = {"0: 禁用", "1: 启用"})
	private Integer status = 1;

	@Schema(description = "注册时间，时间戳类型")
	private Timestamp createTime;

	@Schema(description = "头像")
	private String icon;

	@Schema(description = "性别", allowableValues = {"0: 未知", "1: 男", "2: 女"})
	private Integer gender = 0;

	@Schema(description = "生日", example = "1990-01-01")
	private Date birthday;

	@Schema(description = "所在城市")
	private String city;

	@Schema(description = "个性签名")
	private String personalizedSignature;

	@Schema(description = "积分（兑换优惠卷），支付获得一定比例的积分，积分（订单退款会导致该订单获得的积分回收），每天登录自动完成签到增加积分")
	private Integer integration = 0;

	@Schema(description = "成长值（会员等级提升），支付获得一定比例的成长值（订单单退款会导致该订单获得的成长值回收）")
	private Integer growth = 0;

	@Schema(description = "历史积分数量")
	private Integer historyIntegration = 0;
}