package com.mole.order.dto;

import com.mole.order.entity.PmsProduct;
import com.mole.order.entity.PmsProductAttribute;
import com.mole.order.entity.PmsSkuStock;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class CartProduct extends PmsProduct {

	@Schema(description = "商品属性列表")
	private List<PmsProductAttribute> productAttributeList;

	@Schema(description = "商品SKU库存列表")
	private List<PmsSkuStock> skuStockList;

}