package com.mole.mall.mbg;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.TopLevelClass;

import java.util.List;

/**
 * RemoveMethodsPlugin 是一个自定义的 MyBatis Generator 插件，
 * 用于移除生成的 get 和 set 方法。
 */
public class RemoveMethodsPlugin extends PluginAdapter {

	/**
	 * 验证插件配置。
	 *
	 * @param warnings 如果配置无效，添加警告信息。
	 * @return true 如果配置有效。
	 */
	@Override
	public boolean validate(List<String> warnings) {
		return true;
	}

	/**
	 * 移除基础记录类中的 get 和 set 方法。
	 *
	 * @param topLevelClass     生成的基础记录类。
	 * @param introspectedTable 从数据库中内省的表。
	 * @return true 继续处理。
	 */
	@Override
	public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		removeGettersAndSetters(topLevelClass);
		return true;
	}

	/**
	 * 移除主键类中的 get 和 set 方法。
	 *
	 * @param topLevelClass     生成的主键类。
	 * @param introspectedTable 从数据库中内省的表。
	 * @return true 继续处理。
	 */
	@Override
	public boolean modelPrimaryKeyClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		removeGettersAndSetters(topLevelClass);
		return true;
	}

	/**
	 * 移除包含 BLOB 的记录类中的 get 和 set 方法。
	 *
	 * @param topLevelClass     生成的包含 BLOB 的记录类。
	 * @param introspectedTable 从数据库中内省的表。
	 * @return true 继续处理。
	 */
	@Override
	public boolean modelRecordWithBLOBsClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		removeGettersAndSetters(topLevelClass);
		return true;
	}

	/**
	 * 移除指定类中的 get 和 set 方法。
	 *
	 * @param topLevelClass 要移除方法的类。
	 */
	private void removeGettersAndSetters(TopLevelClass topLevelClass) {
		topLevelClass.getMethods().removeIf(method -> method.getName().startsWith("get") || method.getName().startsWith("set"));
	}
}