package com.mole.protal.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 分页数据封装类
 */
@Data
@ApiModel(value = "分页结果集")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommonPage<T> {

    @ApiModelProperty(value = "当前页码")
    private Integer pageNum;

    @ApiModelProperty(value = "每页数量")
    private Integer pageSize;

    @ApiModelProperty(value = "总记录数")
    private Long total;

    @ApiModelProperty(value = "总页数")
    private Integer pages;

    @ApiModelProperty(value = "数据列表")
    private List<T> list;

    public CommonPage(List<T> list, Integer pageNum, Integer pageSize, Long total) {
        this.list = list;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.total = total;
        this.pages = (int) Math.ceil((double) total / pageSize);
    }

    /**
     * 将 List 转换成 CommonPage 对象
     * @param list 数据列表
     * @return 分页对象
     */
    public static <T> CommonPage<T> restPage(List<T> list) {
        if (list == null) {
            return new CommonPage<>(null, 1, 0, 0L);
        }
        return new CommonPage<>(list, 1, list.size(), (long) list.size());
    }
}
