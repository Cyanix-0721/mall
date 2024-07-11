package com.mole.common.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 游戏实体类
 * 使用了Lombok库的@Data，@AllArgsConstructor，@NoArgsConstructor注解来自动生成getter，setter，构造函数等常用方法
 * 使用了MyBatis Plus的@TableId，@TableName注解来指定数据库中的表名和主键
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("tab_game") // 指定对应的数据库表名为"tab_game"
public class Product {

 @TableId("g_id") // 指定主键字段名为"g_id"
 private Integer gId; // 游戏ID
 private String gName; // 游戏名称
 private String gDesc; // 游戏描述
 private Double gPrice; // 游戏价格
 private Date gDate; // 游戏发布日期
 private Integer gStatus; // 游戏状态

}