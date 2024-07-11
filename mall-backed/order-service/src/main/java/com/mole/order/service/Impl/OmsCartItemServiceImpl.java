package com.mole.order.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mole.common.entity.order.OmsCartItem;
import com.mole.order.mapper.OmsCartItemMapper;
import com.mole.order.service.OmsCartItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OmsCartItemServiceImpl implements OmsCartItemService {

    private OmsCartItemMapper cartItemMapper;

    @Override
    @Transactional
    public OmsCartItem add(OmsCartItem cartItem) {
        QueryWrapper<OmsCartItem> wrapper = new QueryWrapper<>(); //创建QueryWrapper对象，用于构建查询条件
        wrapper.eq("member_id", cartItem.getMemberId()); //用member_id字段作为条件进行查询
        wrapper.eq("product_id", cartItem.getProductId()); //用product_id字段作为条件进行查询
        OmsCartItem existingCartItem = cartItemMapper.selectOne(wrapper); //使用 selectOne 方法根据构建的查询条件获取单条记录
        if(existingCartItem != null){
            existingCartItem.setQuantity(existingCartItem.getQuantity() + cartItem.getQuantity());
            cartItemMapper.update(existingCartItem, wrapper);
        }else{
            cartItem.setQuantity(cartItem.getQuantity());
            cartItemMapper.insert(cartItem);
        }
        return cartItem;
    }


}
