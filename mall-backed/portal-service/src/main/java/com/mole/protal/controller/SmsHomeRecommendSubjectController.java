package com.mole.protal.controller;

import com.mole.protal.api.CommonPage;
import com.mole.protal.api.CommonResult;
import com.mole.common.entity.portal.SmsHomeRecommendSubject;
import com.mole.protal.service.SmsHomeRecommendSubjectService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 首页专题推荐管理Controller

 */
@RestController
@Tag(name = "SmsHomeRecommendSubjectController", description = "首页专题推荐管理")
@RequestMapping("/home/recommendSubject")
public class SmsHomeRecommendSubjectController {
    @Autowired
    private SmsHomeRecommendSubjectService recommendSubjectService;

    @ApiOperation("添加首页推荐专题")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult createRecommendsubject(@RequestBody List<SmsHomeRecommendSubject> homeRecommendSubjectList) {
        int count = recommendSubjectService.create(homeRecommendSubjectList);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("修改推荐排序")
    @RequestMapping(value = "/update/sort/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateSortRecommendsubject(@PathVariable Long id, Integer sort) {
        int count = recommendSubjectService.updateSort(id, sort);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @Operation(summary = "根据ID删除专题新品")
    @DeleteMapping("/delete/{id}")
    public CommonResult deleteByIdRecommendsubject(
            @Parameter(description = "要删除的首页专题ID", required = true)
            @PathVariable Long id) {
        int count = recommendSubjectService.delete(id);
        return CommonResult.success(count > 0);
    }

    @ApiOperation("批量修改推荐状态")
    @RequestMapping(value = "/update/recommendStatus", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateRecommendStatusRecommendsubject(@RequestParam("ids") List<Long> ids, @RequestParam Integer recommendStatus) {
        int count = recommendSubjectService.updateRecommendStatus(ids, recommendStatus);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("分页查询推荐")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<SmsHomeRecommendSubject>> listRecommendsubject(@RequestParam(value = "subjectName", required = false) String subjectName,
                                                                  @RequestParam(value = "recommendStatus", required = false) Integer recommendStatus,
                                                                  @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                                  @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<SmsHomeRecommendSubject> homeRecommendSubjectList = recommendSubjectService.list(subjectName, recommendStatus, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(homeRecommendSubjectList));
    }
    /**
     * 更新首页推荐专题信息
     * @param id 推荐专题ID
     * @param smsHomeRecommendSubject 需要更新的推荐专题信息
     * @return 更新结果，通常为受影响的行数
     */
    @PutMapping("/update/{id}")
    @ResponseBody
    public CommonResult<Integer> updateHomeRecommendSubject(@PathVariable Long id, @RequestBody SmsHomeRecommendSubject smsHomeRecommendSubject) {
        // 参数校验逻辑通常放在Controller层，但此处假设Service层已做检查
        int updateResult = recommendSubjectService.update(id, smsHomeRecommendSubject);

        if (updateResult > 0) {
            return CommonResult.success(updateResult);
        } else {
            // 返回具体的错误信息可能更有帮助，这里仅作为示例
            return CommonResult.failed("更新推荐专题信息失败，可能是ID不存在或输入信息有误。");
        }
    }
}

