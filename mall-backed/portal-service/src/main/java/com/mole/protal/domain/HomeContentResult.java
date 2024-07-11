package com.mole.protal.domain;

import com.mole.common.entity.portal.CmsSubject;
import com.mole.common.entity.portal.PmsBrand;
import com.mole.common.entity.portal.PmsProduct;
import com.mole.common.entity.portal.SmsHomeAdvertise;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 首页内容结果对象
 */
@Getter
@Setter
public class HomeContentResult {

    @Schema(description = "轮播广告列表")
    private List<SmsHomeAdvertise> advertiseList;

    @Schema(description = "推荐品牌列表")
    private List<PmsBrand> brandList;

    @Schema(description = "当前秒杀场次信息")
    private HomeFlashPromotion homeFlashPromotion;

    @Schema(description = "新品推荐商品列表")
    private List<PmsProduct> newProductList;

    @Schema(description = "人气推荐商品列表")
    private List<PmsProduct> hotProductList;

    @Schema(description = "推荐专题列表")
    private List<CmsSubject> subjectList;
}
