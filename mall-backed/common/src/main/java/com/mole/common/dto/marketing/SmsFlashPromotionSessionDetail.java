package com.mole.common.dto.marketing;

import com.mole.common.entity.marketing.SmsFlashPromotionSession;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 包含商品数量的场次信息
 */
public class SmsFlashPromotionSessionDetail extends SmsFlashPromotionSession{
    @Schema(description ="商品数量")
    private Long productCount;

    public Long getProductCount() {
        return productCount;
    }

    public void setProductCount(Long productCount) {
        this.productCount = productCount;
    }
}
