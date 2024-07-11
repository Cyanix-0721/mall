package com.mole.common.entity.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "会员收货地址表")
@TableName("ums_member_receive_address")
public class MemberReceiveAddress {
	@Schema(description = "ID")
	@TableId(value = "id", type = IdType.AUTO)
	private Long id;

	@Schema(description = "会员ID")
	private Long memberId;

	@Schema(description = "收货人名称")
	private String name;

	@Schema(description = "电话号码")
	private String phoneNumber;

	@Schema(description = "是否为默认地址：0->不是；1->是")
	private Integer defaultStatus = 0;

	@Schema(description = "邮政编码")
	private String postCode;

	@Schema(description = "省份/直辖市")
	private String province;

	@Schema(description = "城市")
	private String city;

	@Schema(description = "区")
	private String region;

	@Schema(description = "详细地址(街道)")
	private String detailAddress;
}