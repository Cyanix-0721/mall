package com.mole.product.controller;

import com.mole.common.entity.product.api.CommonPage;
import com.mole.common.entity.product.api.CommonResult;
import com.mole.common.entity.product.CmsSubject;
import com.mole.product.service.CmsSubjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "CmsSubjectController", description = "商品专题管理相关接口")
@RequestMapping("/subject")
public class CmsSubjectController {

    @Autowired
    private CmsSubjectService subjectService;

    @Operation(summary = "获取全部商品专题")
    @GetMapping("/listAll")
    public CommonResult<List<CmsSubject>> listAll() {
        List<CmsSubject> subjectList = subjectService.listAll();
        return CommonResult.success(subjectList);
    }

    @Operation(summary = "根据专题名称分页获取商品专题",
            description = "根据关键词分页查询商品专题列表")
    @GetMapping("/list")
    public CommonResult<CommonPage<CmsSubject>> getList(
            @Parameter(description = "搜索关键词", example = "夏装") @RequestParam(required = false) String keyword,
            @Parameter(description = "当前页数", example = "1") @RequestParam(defaultValue = "1") Integer pageNum,
            @Parameter(description = "每页显示数量", example = "10") @RequestParam(defaultValue = "10") Integer pageSize) {
        List<CmsSubject> subjectList = subjectService.list(keyword, pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(subjectList));
    }
}
