package com.mole.order.service;

import com.mole.order.dto.CartProduct;
import com.mole.order.dto.CartPromotionItem;
import com.mole.common.entity.order.OmsCartItem;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 购物车模块
 */

public interface OmsCartItemService {
    /**
     * 查询购物车中是否包含该商品，有增加数量，无添加到购物车
     */
    @Transactional
    OmsCartItem add(OmsCartItem cartItem);

}
