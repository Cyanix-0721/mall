package com.mole.common.dto.product;

import com.mole.common.entity.product.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 前台商品详情
 */
@Getter
@Setter
public class PmsPortalProductDetail {

    @Schema(description = "商品信息")
    private PmsProduct product;

    @Schema(description = "商品品牌")
    private PmsBrand brand;

    @Schema(description = "商品属性与参数列表")
    private List<PmsProductAttribute> productAttributeList;

    @Schema(description = "手动录入的商品属性与参数值列表")
    private List<PmsProductAttributeValue> productAttributeValueList;

    @Schema(description = "商品的sku库存信息列表")
    private List<PmsSkuStock> skuStockList;

    @Schema(description = "商品阶梯价格设置列表")
    private List<PmsProductLadder> productLadderList;

    @Schema(description = "商品满减价格设置列表")
    private List<PmsProductFullReduction> productFullReductionList;

    @Schema(description = "商品可用优惠券列表")
    private List<SmsCoupon> couponList;
}

