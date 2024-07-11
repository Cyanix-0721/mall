package com.mole.common.dto.marketing;

import com.mole.common.entity.marketing.PmsProduct;
import com.mole.common.entity.marketing.SmsFlashPromotionProductRelation;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 限时购商品信息封装
 */
public class SmsFlashPromotionProduct {

	@Schema(description = "限时购关联信息")
	private SmsFlashPromotionProductRelation relation;

	@Schema(description = "关联商品")
	private PmsProduct product;

	// Getter for relation
	public SmsFlashPromotionProductRelation getRelation() {
		return relation;
	}

	// Setter for relation
	public void setRelation(SmsFlashPromotionProductRelation relation) {
		this.relation = relation;
	}

	// Getter for product
	public PmsProduct getProduct() {
		return product;
	}

	// Setter for product
	public void setProduct(PmsProduct product) {
		this.product = product;
	}
}