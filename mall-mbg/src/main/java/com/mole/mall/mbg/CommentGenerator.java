package com.mole.mall.mbg;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.internal.DefaultCommentGenerator;
import org.mybatis.generator.internal.util.StringUtility;

import java.util.Properties;

/**
 * 自定义注释生成器
 * Created by macro on 2018/4/26.
 * Modified by Cyanix-0721 on 2024/9/25.
 */
public class CommentGenerator extends DefaultCommentGenerator {
	private boolean addRemarkComments = false;
	private static final String EXAMPLE_SUFFIX = "Example";
	private static final String MAPPER_SUFFIX = "Mapper";
	private static final String API_MODEL_PROPERTY_FULL_CLASS_NAME = "io.swagger.v3.oas.annotations.media.Schema";

	/**
	 * 设置用户配置的参数
	 *
	 * @param properties 用户配置的属性
	 */
	@Override
	public void addConfigurationProperties(Properties properties) {
		super.addConfigurationProperties(properties);
		this.addRemarkComments = StringUtility.isTrue(properties.getProperty("addRemarkComments"));
	}

	/**
	 * 给字段添加注释
	 *
	 * @param field              字段
	 * @param introspectedTable  内省表
	 * @param introspectedColumn 内省列
	 */
	@Override
	public void addFieldComment(Field field, IntrospectedTable introspectedTable,
	                            IntrospectedColumn introspectedColumn) {
		String remarks = introspectedColumn.getRemarks();
		// 根据参数和备注信息判断是否添加 Swagger 注解信息
		if (addRemarkComments && StringUtility.stringHasValue(remarks)) {
			// 数据库中特殊字符需要转义
			if (remarks.contains("\"")) {
				remarks = remarks.replace("\"", "'");
			}
			// 给模型的字段添加 Swagger 注解
			field.addJavaDocLine("@Schema(title = \"" + remarks + "\")");
		}
	}

	/**
	 * 给模型的字段添加注释
	 *
	 * @param field   字段
	 * @param remarks 备注信息
	 */
	private void addFieldJavaDoc(Field field, String remarks) {
		// 文档注释开始
		field.addJavaDocLine("/**");
		// 获取数据库字段的备注信息
		String[] remarkLines = remarks.split(System.lineSeparator());
		for (String remarkLine : remarkLines) {
			field.addJavaDocLine(" * " + remarkLine);
		}
		addJavadocTag(field, false);
		field.addJavaDocLine(" */");
	}

	/**
	 * 给 Java 文件添加注释
	 *
	 * @param compilationUnit 编译单元
	 */
	@Override
	public void addJavaFileComment(CompilationUnit compilationUnit) {
		super.addJavaFileComment(compilationUnit);
		// 只在模型文件中添加 Swagger 注解类的导入，不在 Example 或 Mapper 文件中添加
		if (! compilationUnit.getType().getFullyQualifiedName().contains(MAPPER_SUFFIX) &&
				! compilationUnit.getType().getFullyQualifiedName().contains(EXAMPLE_SUFFIX)) {
			compilationUnit.addImportedType(new FullyQualifiedJavaType(API_MODEL_PROPERTY_FULL_CLASS_NAME));
		}
	}
}