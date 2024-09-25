package com.mole.mall.mbg.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CmsHelp implements Serializable {
	private Long id;

	private Long categoryId;

	private String icon;

	private String title;

	private Integer showStatus;

	private Date createTime;

	private Integer readCount;

	private String content;

	@Serial
	private static final long serialVersionUID = 1L;
}