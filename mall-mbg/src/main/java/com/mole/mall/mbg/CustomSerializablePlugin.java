package com.mole.mall.mbg;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.plugins.SerializablePlugin;

/**
 * 自定义插件，用于在生成的类中为 serialVersionUID 字段添加 @Serial 注解。
 */
public class CustomSerializablePlugin extends SerializablePlugin {

	@Override
	protected void makeSerializable(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		super.makeSerializable(topLevelClass, introspectedTable);
		addSerialAnnotation(topLevelClass);
	}

	/**
	 * 为指定类中的 serialVersionUID 字段添加 @Serial 注解。
	 *
	 * @param topLevelClass 要添加注解的类。
	 */
	private void addSerialAnnotation(TopLevelClass topLevelClass) {
		for (Field field : topLevelClass.getFields()) {
			if ("serialVersionUID".equals(field.getName())) {
				field.addAnnotation("@Serial");
				topLevelClass.addImportedType(new FullyQualifiedJavaType("java.io.Serial"));
			}
		}
	}
}