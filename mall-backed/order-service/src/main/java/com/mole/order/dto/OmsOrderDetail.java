package com.mole.order.dto;

import com.mole.common.entity.order.OmsOrder;
import com.mole.common.entity.order.OmsOrderItem;
import com.mole.common.entity.order.OmsOrderOperateHistory;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 订单详情信息
 */
@Schema(description = "订单详情信息")
public class OmsOrderDetail extends OmsOrder {

    @Schema(description = "订单商品列表")
    @Getter
    @Setter
    private List<OmsOrderItem> orderItemList;

    @Schema(description = "订单操作记录列表")
    @Getter
    @Setter
    private List<OmsOrderOperateHistory> historyList;
}
