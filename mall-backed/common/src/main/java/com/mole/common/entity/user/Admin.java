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
@Schema(description = "管理员表")
@TableName("ums_admin")
public class Admin {
	@Schema(description = "ID")
	@TableId(value = "id", type = IdType.AUTO)
	private Long id;

	@Schema(description = "用户名")
	private String username;

	@Schema(description = "密码")
	private String password;

	@Schema(description = "头像")
	private String icon;

	@Schema(description = "邮箱")
	private String email;

	@Schema(description = "昵称")
	private String nickname;

	@Schema(description = "创建时间")
	private Timestamp createTime;

	@Schema(description = "帐号启用状态：0->禁用；1->启用")
	private Integer status = 1;
}