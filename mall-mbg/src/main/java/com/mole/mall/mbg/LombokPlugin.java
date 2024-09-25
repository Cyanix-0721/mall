package com.mole.mall.mbg;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.TopLevelClass;

import java.util.List;

/**
 * LombokPlugin 是一个自定义的 MyBatis Generator 插件，
 * 用于在生成的模型类中添加 Lombok 注解 (@Data, @NoArgsConstructor, @AllArgsConstructor)。
 */
public class LombokPlugin extends PluginAdapter {

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
     * 在基础记录类中添加 Lombok 注解。
     *
     * @param topLevelClass 生成的基础记录类。
     * @param introspectedTable 从数据库中内省的表。
     * @return true 继续处理。
     */
    @Override
    public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        addLombokAnnotations(topLevelClass);
        return true;
    }

    /**
     * 在主键类中添加 Lombok 注解。
     *
     * @param topLevelClass 生成的主键类。
     * @param introspectedTable 从数据库中内省的表。
     * @return true 继续处理。
     */
    @Override
    public boolean modelPrimaryKeyClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        addLombokAnnotations(topLevelClass);
        return true;
    }

    /**
     * 在包含 BLOB 的记录类中添加 Lombok 注解。
     *
     * @param topLevelClass 生成的包含 BLOB 的记录类。
     * @param introspectedTable 从数据库中内省的表。
     * @return true 继续处理。
     */
    @Override
    public boolean modelRecordWithBLOBsClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        addLombokAnnotations(topLevelClass);
        return true;
    }

    /**
     * 为指定的类添加 Lombok 注解 (@Data, @NoArgsConstructor, @AllArgsConstructor)。
     *
     * @param topLevelClass 要添加注解的类。
     */
    private void addLombokAnnotations(TopLevelClass topLevelClass) {
        topLevelClass.addImportedType(new FullyQualifiedJavaType("lombok.Data"));
        topLevelClass.addImportedType(new FullyQualifiedJavaType("lombok.NoArgsConstructor"));
        topLevelClass.addImportedType(new FullyQualifiedJavaType("lombok.AllArgsConstructor"));
        topLevelClass.addAnnotation("@Data");
        topLevelClass.addAnnotation("@NoArgsConstructor");
        topLevelClass.addAnnotation("@AllArgsConstructor");
    }
}